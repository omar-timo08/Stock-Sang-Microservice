package com.example.stocksang.model.repositories;

import com.example.stocksang.model.BanqueSang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanqueRepo extends JpaRepository<BanqueSang,Long> {
    BanqueSang findByNom(String nom);
    void deleteByNom(String nom);
}
