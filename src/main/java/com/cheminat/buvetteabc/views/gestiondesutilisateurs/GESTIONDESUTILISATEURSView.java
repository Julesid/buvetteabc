package com.cheminat.buvetteabc.views.gestiondesutilisateurs;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;
import java.util.List;

@PageTitle("GESTION DES UTILISATEURS")
@Route("gestion-des-utilisateurs")
@Menu(order = 2, icon = "line-awesome/svg/user-edit-solid.svg")
@RolesAllowed("ADMIN")
public class GESTIONDESUTILISATEURSView extends VerticalLayout {

    private Grid<User> userGrid;

    public GESTIONDESUTILISATEURSView() {
        setSpacing(true);
        setPadding(true);
        setSizeFull();

        // Titre de la page
        H2 header = new H2("Gestion des Utilisateurs");
        header.getStyle().set("margin-top", "20px");
        add(header);

        // Boutons d'action pour la gestion des utilisateurs
        Button addUserButton = new Button("Ajouter Utilisateur");
        Button deleteUserButton = new Button("Supprimer Sélection");
        HorizontalLayout actionsLayout = new HorizontalLayout(addUserButton, deleteUserButton);
        actionsLayout.setSpacing(true);

        add(actionsLayout);

        // Grille pour afficher les utilisateurs
        userGrid = new Grid<>(User.class);
        userGrid.setColumns("id", "nom", "email", "role");
        userGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        // Chargement des données fictives pour l'exemple
        List<User> users = getUsers();
        userGrid.setItems(users);

        // Ajout de la grille à la vue
        add(userGrid);

        // Action sur le bouton "Ajouter Utilisateur" (à personnaliser selon tes besoins)
        addUserButton.addClickListener(e -> {
            // Logique pour ajouter un utilisateur (peut ouvrir un formulaire de création)
        });

        // Action sur le bouton "Supprimer Sélection"
        deleteUserButton.addClickListener(e -> {
            // Logique pour supprimer les utilisateurs sélectionnés
            userGrid.getSelectedItems().forEach(user -> {
                // Supprime l'utilisateur de la liste ou de la base de données
                // Ici, nous utilisons simplement la console pour montrer l'action
                System.out.println("Supprimer utilisateur : " + user.getNom());
            });
        });
    }

    // Méthode pour générer des données fictives (à remplacer par des données réelles)
    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Jean Dupont", "jean.dupont@example.com", "Utilisateur"));
        users.add(new User(2, "Marie Durand", "marie.durand@example.com", "Administrateur"));
        users.add(new User(3, "Paul Martin", "paul.martin@example.com", "Utilisateur"));
        // Ajouter d'autres utilisateurs ici
        return users;
    }

    // Classe interne pour représenter un utilisateur
    public static class User {
        private int id;
        private String nom;
        private String email;
        private String role;

        public User(int id, String nom, String email, String role) {
            this.id = id;
            this.nom = nom;
            this.email = email;
            this.role = role;
        }

        // Getters et setters nécessaires pour la grille
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
