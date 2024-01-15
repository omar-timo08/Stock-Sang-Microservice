package com.example.stocksang.controller;

import com.example.stocksang.model.BanqueSang;
import com.example.stocksang.service.BanqueService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BanqueController {

    @Autowired
    BanqueService banqueService;

    @GetMapping("/findBanqueSangAll")
    public List<BanqueSang> findAll(){
        return banqueService.findAll();
    }

    @GetMapping("/findBanqueSangByNom/{nom}")
    public BanqueSang findByNom(@PathVariable("nom") String nom){
        return banqueService.findByNom(nom);
    }

    @PostMapping("/ajouterBanque")
    public int ajouterBanque(@RequestBody BanqueSang banqueSang){
        return banqueService.ajoutBanque(banqueSang);
    }

    @Transactional
    @DeleteMapping("/delete/{nom}")
    public void deleteBanque(@PathVariable("nom")String nom){
         banqueService.delete(nom);
    }
}
