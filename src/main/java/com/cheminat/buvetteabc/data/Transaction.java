package com.cheminat.buvetteabc.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
@Table(name = "HISTORIQUECREATIONSCOMPTES")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long utilisateurId;
    private String action;
    private LocalDateTime dateAction;

    // Constructeur par défaut requis par JPA
    public Transaction() {
    }

    // Constructeur avec paramètres pour faciliter l'initialisation
    public Transaction(Long utilisateurId, String action, LocalDateTime dateAction) {
        this.utilisateurId = utilisateurId;
        this.action = action;
        this.dateAction = dateAction;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getDateAction() {
        return dateAction;
    }

    public void setDateAction(LocalDateTime dateAction) {
        this.dateAction = dateAction;
    }
}
