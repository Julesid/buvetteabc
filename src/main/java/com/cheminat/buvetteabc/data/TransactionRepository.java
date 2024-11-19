package com.cheminat.buvetteabc.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Tu peux ajouter des méthodes personnalisées ici si nécessaire
}
