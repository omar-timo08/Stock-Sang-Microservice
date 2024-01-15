package com.example.stocksang.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class BanqueSang {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;
    private int nbrePochesSang;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<PocheSang> pocheSangs;
}
