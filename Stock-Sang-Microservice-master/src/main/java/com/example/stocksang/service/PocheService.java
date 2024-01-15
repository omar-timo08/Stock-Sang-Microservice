package com.example.stocksang.service;

import com.example.stocksang.event.Transfusion;
import com.example.stocksang.model.BanqueSang;
import com.example.stocksang.model.PocheSang;
import com.example.stocksang.model.repositories.BanqueRepo;
import com.example.stocksang.model.repositories.PocheRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PocheService {


    @Autowired
    PocheRepo pocheRepo;
    @Autowired
    BanqueRepo banqueRepo;
    private final KafkaTemplate<String, Transfusion> kafkaTemplate;

    public PocheService(KafkaTemplate<String, Transfusion> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public PocheSang findByRef(String ref) {
        return pocheRepo.findByReference(ref);
    }

    public List<PocheSang> findAll() {
        return pocheRepo.findAll();
    }


    /** Valeurs de retour :
    0 => Pas de poche avec la référence renseignée
    1 => Ajout de poche de sang réussi
    2 => Pas de banque de sang avec le nom renseigné

    Une dropdown list est à affichée à l'utilisateur pour choisir également la banque de sang associée
    * */
    public int ajouterPoche(PocheSang pocheSang,String nom_banque) {
        if (findByRef(pocheSang.getReference()) != null) return 0;
        else{
            BanqueSang banqueSang=banqueRepo.findByNom(nom_banque);
            if(banqueSang==null) return 2;
            else{
                banqueSang.setNbrePochesSang(banqueSang.getNbrePochesSang()+1);
                pocheSang.setBanqueSang(banqueSang);
                pocheRepo.save(pocheSang);
                return 1;
            }
        }
    }


    /**Transfusion de sang (Une dropdown list est à affiché à l'utilisateur pour choisir une poche de sang selon la référence ainsi que la
     banque associée)

     Valeurs de retour :
     0 => La quantité des poches de sang dans la banque de sang associeé est inférieure au seuil minimal, donc un appel aux dons est nécessaire
     1 => La transfusion est réussie sans problème
     **/
    @Transactional
    public void transfusion(String pocheSangRef, String banqueNom,String groupe){
        BanqueSang banqueSang=banqueRepo.findByNom(banqueNom);
        banqueSang.setNbrePochesSang(banqueSang.getNbrePochesSang()-1);
        pocheRepo.deleteByReference(pocheSangRef);
        if (pocheRepo.findByBanqueSangNomAndGroupeSanguin(banqueNom,groupe).size()<10){
            Transfusion transfusion=new Transfusion();
            transfusion.setStatutStock(groupe);
            kafkaTemplate.send("Statut",transfusion);
        }

        else {
            Transfusion transfusion=new Transfusion();
            transfusion.setStatutStock("Normal");
            kafkaTemplate.send("Statut",transfusion);
        }
    }

}
