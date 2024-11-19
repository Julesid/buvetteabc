package com.cheminat.buvetteabc.views.moncompte;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import com.cheminat.buvetteabc.security.AuthenticatedUser;
import com.cheminat.buvetteabc.services.UserService;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;


@PageTitle("MON COMPTE")
@Route("")
@Menu(order = 0, icon = "line-awesome/svg/user.svg")
@PermitAll
public class MONCOMPTEView extends VerticalLayout {

    private final UserService userService;
    private TextField nameField;
    private TextField emailField;
    private TextField balanceField; // Nouveau champ pour le solde
    private Image profileImage;

    private final AuthenticatedUser authenticatedUser;

    @Autowired
    public MONCOMPTEView(UserService userService, AuthenticatedUser authenticatedUser) {
        this.userService = userService;
        this.authenticatedUser = authenticatedUser;

        // Configuration générale
        configureLayout();
        
        // Image de profil et titre
        addProfileSection();

        // Section avec les cadres pour le solde et les informations personnelles
        addContentSection();

        // Charger les données de l'utilisateur connecté
        loadUserData();
    }

    private void configureLayout() {
        setSpacing(false);
        setPadding(true);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("background-color", "#f5f5f5"); // Couleur de fond douce
    }

    private void loadUserData() {
        // Obtenir l'utilisateur connecté
        authenticatedUser.get().ifPresent(user -> {
            nameField.setValue(user.getName());
            emailField.setValue(user.getUsername());
            
            // Charger le solde de l'utilisateur
            Double balance = userService.getBalanceForUser(user.getId());
            balanceField.setValue(String.format("%.2f €", balance));

            // Chargement de l'image de profil si elle existe
            if (user.getProfilePicture() != null) {
                // Convertir les bytes hexadécimaux en une chaîne Base64 pour l'affichage dans un <img>
                String hexString = new String(user.getProfilePicture(), StandardCharsets.UTF_8);
                byte[] imageBytes = hexStringToByteArray(hexString);

                // Convertir en Base64 pour l'affichage dans un <img> HTML
                String base64Image = "data:image/png;base64," + java.util.Base64.getEncoder().encodeToString(imageBytes);
                profileImage.setSrc(base64Image);
            }
        });
    }

    // Méthode utilitaire pour convertir une chaîne hexadécimale en un tableau de bytes
    private byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                                + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    private void addContentSection() {
        // Cadre pour les informations personnelles
        VerticalLayout infoLayout = new VerticalLayout();
        infoLayout.setWidth("300px");
        infoLayout.setPadding(false);
        infoLayout.addClassName("info-layout");
    
        H2 infoHeader = new H2("Informations personnelles");
        infoHeader.getStyle().set("margin-bottom", "10px");
        infoHeader.addClassName("info-header");
    
        nameField = new TextField("Nom");
        nameField.addClassName("custom-text-field");
    
        emailField = new TextField("Email");
        emailField.addClassName("custom-text-field");
    
        balanceField = new TextField("Solde");
        balanceField.addClassName("custom-text-field");
    
        infoLayout.add(infoHeader, nameField, emailField, balanceField);
        add(infoLayout);
    }
    

    private void addProfileSection() {
        profileImage = new Image("images/profile-placeholder.png", "Profil"); // Image par défaut
        profileImage.setWidth("120px");
        profileImage.setHeight("120px");
        profileImage.getStyle().set("border-radius", "50%"); // Image de profil ronde
        profileImage.getStyle().set("border", "3px solid #4CAF50"); // Bordure autour de l'image
        profileImage.getStyle().set("margin-bottom", "20px");

        H2 header = new H2("Bienvenue sur votre compte");
        header.getStyle().set("color", "#333333");

        VerticalLayout profileLayout = new VerticalLayout(profileImage, header);
        profileLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        profileLayout.setSpacing(true);
        profileLayout.getStyle().set("margin-bottom", "30px");

        add(profileLayout);
    }
}
