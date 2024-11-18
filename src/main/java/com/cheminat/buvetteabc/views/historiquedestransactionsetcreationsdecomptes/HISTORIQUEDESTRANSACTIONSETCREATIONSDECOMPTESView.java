package com.cheminat.buvetteabc.views.historiquedestransactionsetcreationsdecomptes;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@PageTitle("HISTORIQUE DES TRANSACTIONS ET CRÉATIONS DE COMPTES")
@Route("historique-transactions")
@Menu(order = 3, icon = "line-awesome/svg/exchange-alt-solid.svg")
@RolesAllowed("ADMIN")
public class HISTORIQUEDESTRANSACTIONSETCREATIONSDECOMPTESView extends VerticalLayout {

    private Grid<Transaction> transactionGrid;

    public HISTORIQUEDESTRANSACTIONSETCREATIONSDECOMPTESView() {
        setSpacing(true);
        setPadding(true);
        setSizeFull();

        // Titre de la page
        H2 header = new H2("Historique des Transactions et Créations de Comptes");
        header.getStyle().set("margin-top", "20px");
        add(header);

        // Boutons d'action pour la gestion des transactions
        Button exportButton = new Button("Exporter CSV");
        Button addTransactionButton = new Button("Ajouter Transaction");
        HorizontalLayout actionsLayout = new HorizontalLayout(exportButton, addTransactionButton);
        actionsLayout.setSpacing(true);

        add(actionsLayout);

        // Grille pour afficher l'historique des transactions
        transactionGrid = new Grid<>(Transaction.class);
        transactionGrid.setColumns("date", "utilisateur", "type", "montant", "description");
        transactionGrid.setSizeFull();

        // Chargement des données fictives pour l'exemple
        List<Transaction> transactions = getTransactions();
        transactionGrid.setItems(transactions);

        // Ajout de la grille à la vue
        add(transactionGrid);

        // Action sur le bouton "Exporter CSV" (à personnaliser selon tes besoins)
        exportButton.addClickListener(e -> {
            // Logique pour exporter les données en CSV
            System.out.println("Export des transactions en CSV.");
        });

        // Action sur le bouton "Ajouter Transaction"
        addTransactionButton.addClickListener(e -> {
            // Logique pour ajouter une transaction (peut ouvrir un formulaire de création)
        });
    }

    // Méthode pour générer des données fictives (à remplacer par des données réelles)
    private List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(LocalDateTime.now().minusDays(1), "Jean Dupont", "Crédit", 50.0, "Paiement abonnement"));
        transactions.add(new Transaction(LocalDateTime.now().minusDays(3), "Marie Durand", "Débit", -10.0, "Achat boisson"));
        transactions.add(new Transaction(LocalDateTime.now().minusWeeks(1), "Paul Martin", "Création de compte", 0.0, "Nouveau compte créé"));
        // Ajouter d'autres transactions ici
        return transactions;
    }

    // Classe interne pour représenter une transaction
    public static class Transaction {
        private LocalDateTime date;
        private String utilisateur;
        private String type;
        private double montant;
        private String description;

        public Transaction(LocalDateTime date, String utilisateur, String type, double montant, String description) {
            this.date = date;
            this.utilisateur = utilisateur;
            this.type = type;
            this.montant = montant;
            this.description = description;
        }

        // Getters et setters nécessaires pour la grille
        public LocalDateTime getDate() {
            return date;
        }

        public void setDate(LocalDateTime date) {
            this.date = date;
        }

        public String getUtilisateur() {
            return utilisateur;
        }

        public void setUtilisateur(String utilisateur) {
            this.utilisateur = utilisateur;
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
