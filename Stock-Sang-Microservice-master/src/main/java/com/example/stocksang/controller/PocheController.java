package com.example.stocksang.controller;

import com.example.stocksang.model.PocheSang;
import com.example.stocksang.service.PocheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PocheController {

    @Autowired
    PocheService pocheService;

    @GetMapping("/findPocheAll")
    public List<PocheSang> findAll(){
        return pocheService.findAll();
    }

    @GetMapping("/findPocheByRef/{ref}")
    public PocheSang findByRef(@PathVariable("ref") String ref){
        return pocheService.findByRef(ref);
    }

    @PostMapping("/ajouterPoche/{nomBanque}")
    public int ajouterPoche(@RequestBody PocheSang pocheSang,@PathVariable("nomBanque") String nomBanque){
        return pocheService.ajouterPoche(pocheSang,nomBanque);
    }

    @DeleteMapping("/transfusion/{pocheSangRef}/{banqueNom}/{groupe}")
    public void transfusion(@PathVariable("pocheSangRef") String pocheSangRef,@PathVariable("banqueNom") String banqueNom,@PathVariable("groupe") String groupe){
         pocheService.transfusion(pocheSangRef,banqueNom,groupe);
    }
}
