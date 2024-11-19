package com.cheminat.buvetteabc.data;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SoldeRepository extends JpaRepository<Solde, Long> {
    Optional<Solde> findByUserId(Long userId);
}
