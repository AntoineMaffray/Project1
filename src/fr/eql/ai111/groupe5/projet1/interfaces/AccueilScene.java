package fr.eql.ai111.groupe5.projet1.interfaces;


import fr.eql.ai111.groupe5.projet1.methodsback.User;
import fr.eql.ai111.groupe5.projet1.methodsback.UserDAO;
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

import java.io.IOException;


public class AccueilScene {

    User user;
    UserDAO dao = new UserDAO();

    public AccueilScene(Stage primaryStage) {

        Label label= new Label("Bienvenue dans l'annuaire EQL BOOK! ");
        Label label1 = new Label("Vous êtes un :");
        label.setFont(new Font("Montserrat", 20));
        label.setOpacity(0.9);
        label.setStyle("-fx-text-fill: black");
        label1.setFont(new Font("Montserrat", 20));
        label1.setOpacity(0.9);
        label1.setStyle("-fx-text-fill: black");
        label.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label, 0.0);
        AnchorPane.setRightAnchor(label, 0.0);
        label.setAlignment(Pos.CENTER);
        label1.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label1, 0.0);
        AnchorPane.setRightAnchor(label1, 0.0);
        label1.setAlignment(Pos.CENTER);

        GridPane grille = new GridPane();
        grille.setAlignment(Pos.CENTER);
        grille.setHgap(10);
        grille.setVgap(10);
        grille.setPadding(new Insets(5, 10, 5, 10));

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
        Button btnAdmin = new Button("Admin");
        btnAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                connexion(primaryStage);
            }
        });

        Button btnSuperAdmin = new Button("SuperAdmin");
        btnSuperAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    new SuperAdminScene(primaryStage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        HBox hbBtnUser = new HBox(10);
        hbBtnUser.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnUser.getChildren().add(btnUser);

        HBox hbBtnAdmin = new HBox(12);
        hbBtnAdmin.setAlignment(Pos.BOTTOM_CENTER);
        hbBtnAdmin.getChildren().add(btnAdmin);

        HBox hbBtnSuperAdmin = new HBox(12);
        hbBtnSuperAdmin.setAlignment(Pos.BOTTOM_LEFT);
        hbBtnSuperAdmin.getChildren().add(btnSuperAdmin);

        grille.add(label, 2, 1);
        grille.add(label1, 2, 2);
        grille.add(hbBtnUser, 1, 4);
        grille.add(hbBtnAdmin, 2, 4);
        grille.add(hbBtnSuperAdmin, 3, 4);

        Scene accueil = new Scene(grille, 700, 400);
        accueil.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(accueil);
        primaryStage.setTitle("Accueil");
        primaryStage.show();
    }

    private void connexion(Stage primaryStage) {
        //cr�er une grille (GridPane Layout)
        GridPane grille = new GridPane();
        grille.setAlignment(Pos.CENTER);
        grille.setHgap(10);
        grille.setVgap(10);
        grille.setPadding(new Insets(20, 20, 20, 20));

        //Remplir la grille
        Text titre = new Text("Connexion");
        titre.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        //Utilis? dans le fichier css pour identifier le noeud
        titre.setId("titreText");

        grille.add(titre, 0, 0, 2, 1);

        Label login = new Label("Login:");
        grille.add(login, 0, 1);

        TextField loginTextField = new TextField("");
        grille.add(loginTextField, 1, 1);

        Label pswd = new Label("Mot de passe :");
        grille.add(pswd, 0, 2);

        PasswordField pswdPasswordField = new PasswordField();
        grille.add(pswdPasswordField, 1, 2);

        //Ajout du bouton ? la grille
        Button btnValidation = new Button("Validez");
        btnValidation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                user = new User(loginTextField.getText(),
                        pswdPasswordField.getText());
                connexionUser();
                try {
                    new AdminScene(primaryStage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        Button btnRedirectionInscription = new Button("Premi�re connexion ");
        btnRedirectionInscription.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                inscription(primaryStage);
            }
        });

        Button btnRetourAccueil = new Button("Retour accueil");
        btnRetourAccueil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new AccueilScene(primaryStage);
            }
        });

        HBox hbBtnValidation = new HBox(10);
        hbBtnValidation.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnValidation.getChildren().add(btnValidation);

        HBox hbBtnRedirectionInscription = new HBox(10);
        hbBtnRedirectionInscription.setAlignment(Pos.BOTTOM_LEFT);
        hbBtnRedirectionInscription.getChildren().add(btnRedirectionInscription);

        HBox hbBtnRedirectionAccueil = new HBox(1);
        hbBtnRedirectionInscription.setAlignment(Pos.BOTTOM_CENTER);
        hbBtnRedirectionInscription.getChildren().add(btnRetourAccueil);

        grille.add(hbBtnValidation, 1, 4);
        grille.add(hbBtnRedirectionInscription, 1, 6);
        grille.add(hbBtnRedirectionAccueil, 1, 8);


        Scene connexion = new Scene(grille, 400, 350);
        connexion.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(connexion);
        primaryStage.setTitle("Connexion");
        primaryStage.show();
    }

    private Scene inscription(Stage primaryStage) {
        //cr?er une grille (GridPane Layout)
        GridPane grille = new GridPane();
        grille.setAlignment(Pos.CENTER);
        grille.setHgap(10);
        grille.setVgap(10);
        grille.setPadding(new Insets(20, 20, 20, 20));

        //Remplir la grille
        Text titre = new Text("Bienvenue dans l'annuaire");
        titre.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        //Utilis? dans le fichier css pour identifier le noeud
        titre.setId("titreText");

        grille.add(titre, 0, 0, 2, 1);

        Label log = new Label("Login:");
        grille.add(log, 0, 1);

        TextField loginTextField = new TextField("");
        grille.add(loginTextField, 1, 1);

        Label nom = new Label("Nom:");
        grille.add(nom, 0, 2);

        TextField nomTextField = new TextField("");
        grille.add(nomTextField, 1, 2);

        Label prenom = new Label("Pr?nom :");
        grille.add(prenom, 0, 3);

        TextField prenomTextField = new TextField("");
        grille.add(prenomTextField, 1, 3);

        Label pswd = new Label("Mot de passe :");
        grille.add(pswd, 0, 4);

        PasswordField pswdPasswordField = new PasswordField();
        grille.add(pswdPasswordField, 1, 4);

        Label role = new Label("Role:");
        grille.add(role, 0, 5);

        TextField roleTextField = new TextField();

        roleTextField.setStyle("-fx-fill:gray");
        roleTextField.setPromptText("Role: Admin");
        grille.add(roleTextField, 1, 5);


        //Ajout du bouton ? la grille
        Button btnValidate = new Button("Validez");
        btnValidate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                user = new User(nomTextField.getText(),
                        prenomTextField.getText(),
                        loginTextField.getText(),
                        pswdPasswordField.getText(),
                        roleTextField.getText());
                inscriptionUser(user);
                connexion(primaryStage);
            }
        });
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnValidate);
        grille.add(hbBtn, 1, 7);

        Button btnReturn = new Button("Retour");
        btnReturn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                connexion(primaryStage);
            }
        });
        HBox hbBtn1 = new HBox(5);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(btnReturn);
        grille.add(hbBtn1, 3, 9);

        Scene inscription = new Scene(grille, 500, 450);
        inscription.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(inscription);
        primaryStage.setTitle("Inscription");
        primaryStage.show();
        return inscription;
    }

    private void inscriptionUser(User user) {

                    /*
                    Si la m?thode 'createAccount' retourne false, alors le fichier utilisateur
                    n'as pas ?t? cr?? car un autre portant le m?me nom (correspondant au login
                    entr?) existe d?j?.
                     */
        boolean isCreated = dao.createAccount(user.getName(),
                user.getSurname(),
                user.getLogin(),
                user.getPassword(),
                user.getRole());
        if (!isCreated) {
            idendifiantsDejaCrees();
        }
    }

    private void connexionUser() {
        user = dao.connect(user.getLogin(), user.getPassword());
        if (user == null) {
            idendifiantsIncorrects();
        } else {
            messageBienvenue();
        }
    }


    private void idendifiantsDejaCrees() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Message d'erreur");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Attention ces identifiants existent d?j?!");

        alert.showAndWait();

    }  private void idendifiantsIncorrects() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Message d'erreur");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Votre identifiant et mot de passe sont incorrects");

        alert.showAndWait();

    }  private void messageBienvenue() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Message de Bienvenue");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Bienvenue " +  user.getSurname() + " " + user.getName() +  " " + " ! " + " ;)");

        alert.showAndWait();
    }

    public String loginMemory (User user) {
        return user.getLogin();
    }

}

