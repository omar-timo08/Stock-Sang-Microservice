package com.example.stocksang.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class PocheSang {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String reference;
    private String groupeSanguin;
    @ManyToOne
    private BanqueSang banqueSang;
}
