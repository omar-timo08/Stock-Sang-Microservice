package com.example.stocksang.model.repositories;

import com.example.stocksang.model.PocheSang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PocheRepo extends JpaRepository<PocheSang,Long> {
    List<PocheSang> findByGroupeSanguin(String groupeSanguin);
    List<PocheSang> findByBanqueSangNom(String nom);
    PocheSang findByReference(String ref);
    void deleteByReference(String ref);
    List<PocheSang> findByBanqueSangNomAndGroupeSanguin(String nomBanque, String groupe);
}
