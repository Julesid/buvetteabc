package com.cheminat.buvetteabc.views.mestransactions;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.List;
import java.util.ArrayList;

@PageTitle("MES TRANSACTIONS")
@Route("mes-transactions")
@Menu(order = 1, icon = "line-awesome/svg/money-bill-alt-solid.svg")
@PermitAll
public class MESTRANSACTIONSView extends VerticalLayout {

    public MESTRANSACTIONSView() {
        setSpacing(false);
        setSizeFull();
        setPadding(true);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        // Titre de la page
        H2 header = new H2("Mes Transactions");
        header.getStyle().set("margin-top", "20px");
        add(header);

        // Création de la grille pour afficher les transactions
        Grid<Transaction> transactionGrid = new Grid<>(Transaction.class);
        transactionGrid.setColumns("date", "type", "montant", "description");

        // Chargement des données fictives pour l'exemple
        List<Transaction> transactions = getTransactions();
        transactionGrid.setItems(transactions);

        // Ajout de la grille à la vue
        add(transactionGrid);
    }

    // Méthode pour générer des données fictives (à remplacer par des données réelles)
    private List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("2024-11-15", "Crédit", 50.0, "Ajout par l'administrateur"));
        transactions.add(new Transaction("2024-11-14", "Débit", -5.0, "Achat de boisson lors d'un match"));
        transactions.add(new Transaction("2024-11-13", "Débit", -12.0, "Consommation à la buvette"));
        // Ajouter d'autres transactions ici
        return transactions;
    }

    // Classe interne pour représenter une transaction
    public static class Transaction {
        private String date;
        private String type;
        private double montant;
        private String description;

        public Transaction(String date, String type, double montant, String description) {
            this.date = date;
            this.type = type;
            this.montant = montant;
            this.description = description;
        }

        // Getters et setters nécessaires pour la grille
        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getMontant() {
            return montant;
        }

        public void setMontant(double montant) {
            this.montant = montant;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
