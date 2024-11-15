package com.cheminat.buvetteabc.views.moncompte;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("MON COMPTE")
@Route("")
@Menu(order = 0, icon = "line-awesome/svg/user.svg")
@PermitAll
public class MONCOMPTEView extends VerticalLayout {

    public MONCOMPTEView() {
        // Configuration générale
        setSpacing(false);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        // Image de profil et titre
        addProfileSection();

        // Section avec les cadres pour le solde et les informations personnelles
        addContentSection();
    }

    // Méthode pour ajouter la section du profil
    private void addProfileSection() {
        Image profileImage = new Image("images/profile-placeholder.png", "Profil");
        profileImage.setWidth("150px");

        H2 header = new H2("Bienvenue sur votre compte");

        HorizontalLayout profileLayout = new HorizontalLayout(profileImage, header);
        profileLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        profileLayout.setSpacing(true);
        profileLayout.getStyle().set("margin-bottom", "30px");

        add(profileLayout);
    }

    // Méthode pour ajouter la section principale avec les cadres
    private void addContentSection() {
        // Cadre pour le solde
        VerticalLayout soldeLayout = new VerticalLayout();
        soldeLayout.add(new H2("Solde actuel"));
        
        TextField soldeField = new TextField();
        soldeField.setReadOnly(true);
        soldeField.setValue("50.00€"); // Valeur par défaut, à remplacer par des données réelles
        
        soldeLayout.add(soldeField);

        // Cadre pour les informations personnelles et changement de mot de passe
        VerticalLayout infoLayout = new VerticalLayout();
        infoLayout.add(new H2("Informations personnelles"));

        TextField nameField = new TextField("Nom");
        nameField.setReadOnly(true);
        nameField.setValue("Votre nom"); // Valeur par défaut

        TextField emailField = new TextField("Email");
        emailField.setReadOnly(true);
        emailField.setValue("email@exemple.com"); // Valeur par défaut

        infoLayout.add(nameField, emailField);

        // Changement de mot de passe
        infoLayout.add(new H2("Changer votre mot de passe"));
        PasswordField newPasswordField = new PasswordField("Nouveau mot de passe");
        PasswordField confirmPasswordField = new PasswordField("Confirmez le mot de passe");

        Button changePasswordButton = new Button("Changer le mot de passe", event -> {
            // Logique de changement de mot de passe
        });

        infoLayout.add(newPasswordField, confirmPasswordField, changePasswordButton);

        // Organisation des sections côte à côte
        HorizontalLayout contentLayout = new HorizontalLayout(soldeLayout, infoLayout);
        contentLayout.setSpacing(true);
        contentLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        add(contentLayout);
    }
}
