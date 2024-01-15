package com.example.stocksang.service;

import com.example.stocksang.model.BanqueSang;
import com.example.stocksang.model.repositories.BanqueRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@Service
public class BanqueService {
    @Autowired
    BanqueRepo banqueRepo;

    public BanqueSang findByNom(String nom){
        return banqueRepo.findByNom(nom);
    }


    /* Valeurs de retour :
    0 => Pas de banque de sang avec le nom spécifié
    1 => Ajout réussi
    */
    public int ajoutBanque(BanqueSang banqueSang){
        if(findByNom(banqueSang.getNom())!=null) return 0;
        else {
            banqueRepo.save(banqueSang);
            return 1;
        }
    }

    public List<BanqueSang> findAll(){
        return banqueRepo.findAll();
    }
    @Transactional
    public void delete(String nom){
        banqueRepo.deleteByNom(nom);
    }
}
