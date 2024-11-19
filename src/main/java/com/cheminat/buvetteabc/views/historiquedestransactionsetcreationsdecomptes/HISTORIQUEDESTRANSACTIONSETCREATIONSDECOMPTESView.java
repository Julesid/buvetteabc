package com.cheminat.buvetteabc.views.historiquedestransactionsetcreationsdecomptes;

import com.cheminat.buvetteabc.data.Transaction;
import com.cheminat.buvetteabc.services.TransactionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageTitle("HISTORIQUE DES TRANSACTIONS ET CRÉATIONS DE COMPTES")
@Route("historique-transactions")
@Menu(order = 3, icon = "line-awesome/svg/exchange-alt-solid.svg")
@RolesAllowed("ADMIN")
public class HISTORIQUEDESTRANSACTIONSETCREATIONSDECOMPTESView extends VerticalLayout {

    private Grid<Transaction> transactionGrid;
    private final TransactionService transactionService;

    @Autowired
    public HISTORIQUEDESTRANSACTIONSETCREATIONSDECOMPTESView(TransactionService transactionService) {
        this.transactionService = transactionService;
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
        transactionGrid.setColumns("dateAction", "utilisateurId", "action");
        transactionGrid.setSizeFull();

        // Charger les données réelles depuis la base de données
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

    // Méthode pour obtenir des transactions réelles depuis la base de données
    private List<Transaction> getTransactions() {
        return transactionService.getAllTransactions();
    }
}
