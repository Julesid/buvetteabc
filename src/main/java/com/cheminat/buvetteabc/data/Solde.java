package com.cheminat.buvetteabc.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "soldes")
public class Solde {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Liaison avec l'utilisateur
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Montant du solde
    private Double SOLDE;

    // Constructeurs
    public Solde() {
    }

    public Solde(User user, Double SOLDE) {
        this.user = user;
        this.SOLDE = SOLDE;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getSOLDE() {
        return SOLDE;
    }

    public void setSOLDE(Double SOLDE) {
        this.SOLDE = SOLDE;
    }
}
