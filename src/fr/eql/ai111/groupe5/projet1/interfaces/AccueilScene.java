package fr.eql.ai111.groupe5.projet1.interfaces;


import fr.eql.ai111.groupe5.projet1.methodsback.User;
import fr.eql.ai111.groupe5.projet1.methodsback.UserDAO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class AccueilScene {

    User user;
    UserDAO dao = new UserDAO();

    public AccueilScene(Stage primaryStage) {

        //////////////////// LABEL - TITRE DE LA SCENE ACCUEIL //////////////////////////////
        /*
        Cr?ation du titre du fichier en label avec son style.
        Pour l'affichage, on utilise un AnchorPane.
         */
        Label label = new Label("Bienvenue dans l'annuaire EQL BOOK! ");
        label.setFont(new Font("Montserrat", 20));
        label.setOpacity(0.9);
        label.setStyle("-fx-text-fill: black");
        label.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label, 0.0);
        AnchorPane.setRightAnchor(label, 0.0);
        label.setAlignment(Pos.CENTER);

        Label label1 = new Label("Vous ?tes un :");
        label1.setFont(new Font("Montserrat", 20));
        label1.setOpacity(0.9);
        label1.setStyle("-fx-text-fill: black");
        label1.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label1, 0.0);
        AnchorPane.setRightAnchor(label1, 0.0);
        label1.setAlignment(Pos.CENTER);
        //////////////////////////////////////////////////////////////////////////////////


        ///////////////////////////// REDIRECTIONS PAGES /////////////////////////////////
        /*
        Pour faire appara?tre les diff?rentes interfaces, on utilise des boutons afin que l'utilisateur
        ou l'administrateur puisse acc?der au fichier correspondant.
        Les boutons sont plac?s dans une HBox.
        Afin que l'administrateur puisse acc?der au fichier, il doit d'abord s'inscrire s'il n'a pas de compte
        ou se connecter si son compte est cr??.
        Pour le SuperAdmin, il ne peut que se connecter via la page de connexion via ses identifiants, sinon il
        ne peut acc?der au fichier correspondant.
         */

        //Cr?ation du bouton User avec l'?v?nement permettant la redirection vers son fichier//
        Button btnUser = new Button("User");
        btnUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    new UserScene(primaryStage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Cr?ation du bouton Admin avec l'?v?nement permettant la redirection vers son fichier//
        Button btnAdmin = new Button("Admin");
        btnAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    new ConnexionScene(primaryStage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        HBox hbBtnUser = new HBox(10);
        hbBtnUser.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnUser.getChildren().add(btnUser);

        HBox hbBtnAdmin = new HBox(10);
        hbBtnAdmin.setAlignment(Pos.BOTTOM_CENTER);
        hbBtnAdmin.getChildren().add(btnAdmin);
        ////////////////////////////////////////////////////////////////////////////////////////



        ///////////////////////////// AFFICHAGE DES ELEMENTS //////////////////////////////////
        /*
        On affiche tous les ?l?ments dans une GridPane, que l'on int?gre dans une sc?ne et ensuite un stage.
         */
        GridPane grille = new GridPane();
        grille.setAlignment(Pos.CENTER);
        grille.setHgap(10);
        grille.setVgap(10);
        grille.setPadding(new Insets(5, 10, 5, 10));
        grille.add(label, 2, 1);
        grille.add(label1, 2, 2);
        grille.add(hbBtnUser, 1, 4);
        grille.add(hbBtnAdmin, 3, 4);

        Scene accueil = new Scene(grille, 700, 400);
        accueil.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(accueil);
        primaryStage.setTitle("Accueil");
        primaryStage.show();
    }
    ////////////////////////////////////////////////////////////////////////////////////////

}

