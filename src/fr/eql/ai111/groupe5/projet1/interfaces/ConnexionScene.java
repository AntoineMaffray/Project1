package fr.eql.ai111.groupe5.projet1.interfaces;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import fr.eql.ai111.groupe5.projet1.methodsback.Methods;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConnexionScene {
    User user;
    UserDAO dao = new UserDAO();
    Methods methods = new Methods();

    public ConnexionScene(Stage primaryStage) throws IOException {

        //////////////////// LABEL - TITRE DE LA SCENE CONNEXION //////////////////////////////
        /*
        Création du titre du fichier en texte avec son style.
        Création du formulaire de connexion avec le login et password.
         */
        Text titre = new Text("Connexion");
        titre.setFont(Font.font("Roboto", FontWeight.BOLD, 20));
        Label login = new Label("Login:");
        TextField loginTextField = new TextField("");
        Label pswd = new Label("Mot de passe :");
        PasswordField pswdPasswordField = new PasswordField();
        //////////////////////////////////////////////////////////////////////////////////////


        ///////////////////////////// REDIRECTIONS PAGES ////////////////////////////////////
        /*
        Pour faire appara�tre les diff�rentes interfaces, on utilise des boutons afin que
        l'administrateur puisse acc�der au fichier correspondant.
        Les boutons sont plac�s dans une HBox.
        Afin que l'administrateur puisse acc�der au fichier, il doit d'abord s'inscrire s'il n'a pas de compte
        ou se connecter si son compte est cr��.
        Pour le SuperAdmin, il ne peut que se connecter via la page de connexion via ses identifiants, sinon il
        ne peut acc�der au fichier correspondant.
         */
        //Cr�ation du bouton de validation et du bouton de redirection
        // vers la page d'inscription s'il n'est pas inscrit//
        Button btnValidation = new Button("Validez");
        btnValidation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                User user = new User(loginTextField.getText());
                if (loginTextField.getText().equals("SUPERADMIN")) {
                    File readLogin = new File("C://theEqlbook/AdminInfo/SuperAdmin/SUPERADMIN.txt");
                    FileReader fr = null;
                    try {
                        fr = new FileReader(readLogin);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    BufferedReader br = new BufferedReader(fr);
                    String verif = "";
                    try {
                        verif = br.readLine();
                        fr.close();
                        br.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (verif.equals(methods.hashage(pswdPasswordField.getText()))) {
                        try {
                            new SuperAdminScene(primaryStage);
                            messageBienvenue(user);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        idendifiantsIncorrects();
                    }

                } else { File readLogin = new File("C://theEqlbook/AdminInfo/" + loginTextField.getText() + ".txt");
                    FileReader fr = null;
                    try {
                        fr = new FileReader(readLogin);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    BufferedReader br = new BufferedReader(fr);
                    String verif = "";
                    try {
                        verif = br.readLine();
                        fr.close();
                        br.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (verif.equals(methods.hashage(pswdPasswordField.getText()))) {
                        try {
                            new AdminScene(primaryStage);
                            keepLoginWriting(user.getLogin());
                            messageBienvenue(user);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        idendifiantsIncorrects();
                    }
                }}
        });

        Button btnRedirectionInscription = new Button("Première connexion ");
        btnRedirectionInscription.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                new InscriptionScene(primaryStage);
            }
        });

                Button btnRetourAccueil = new Button("Déconnexion");
                btnRetourAccueil.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        new AccueilScene(primaryStage);
                    }
                });
                //////////////////////////////////////////////////////////////////////////////////////


                ///////////////////////////// AFFICHAGE DES ELEMENTS //////////////////////////////////
        /*
        On affiche tous les �l�ments dans une GridPane, que l'on int�gre dans une sc�ne et ensuite un stage.
         */
        HBox hbBtnValidation = new HBox(10);
        hbBtnValidation.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnValidation.getChildren().add(btnValidation);

        HBox hbBtnRedirectionInscription = new HBox(10);
        hbBtnRedirectionInscription.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnRedirectionInscription.getChildren().add(btnRedirectionInscription);

        HBox hbBtnRedirectionAccueil = new HBox(10);
        hbBtnRedirectionAccueil.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnRedirectionAccueil.getChildren().add(btnRetourAccueil);

        GridPane grille = new GridPane();
        grille.setAlignment(Pos.CENTER);
        grille.setHgap(10);
        grille.setVgap(10);
        grille.setPadding(new Insets(10, 10, 10, 10));
        grille.add(titre, 1, 0, 2, 1);
        grille.add(login, 0, 1);
        grille.add(loginTextField, 1, 1);
        grille.add(pswd, 0, 2);
        grille.add(pswdPasswordField, 1, 2);
        grille.add(hbBtnValidation, 0, 4);
                   grille.add(hbBtnRedirectionInscription, 1, 4);
        grille.add(hbBtnRedirectionAccueil, 1, 8);

        Scene connexion = new Scene(grille, 400, 350);
        connexion.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(connexion);
        primaryStage.setTitle("Connexion");
        primaryStage.show();
        }
    ////////////////////////////////////////////////////////////////////////////////////////



    private void idendifiantsIncorrects() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Message d'erreur");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Votre identifiant et mot de passe sont incorrects");

        alert.showAndWait();
    }

    private void messageBienvenue(User user) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Message de Bienvenue");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Bienvenue " + user.getLogin() + " " + " ! ");

        alert.showAndWait();
    }
    private void keepLoginWriting(String login) throws IOException {

        File keepLoginFolder = new File("c://theEqlBook/AdminInfo/Persistance/");
        if (!keepLoginFolder.exists()){
            keepLoginFolder.mkdirs();
        }
        File keepLoginFile = new File("c://theEqlBook/AdminInfo/Persistance/Login.txt");
        if (keepLoginFile.exists()){
            keepLoginFile.delete();
            keepLoginFile.createNewFile();
        }
        FileWriter fw = new FileWriter(keepLoginFile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(login);
        fw.close();
        bw.close();
    }
    public void readKeepLogin(String login) throws FileNotFoundException {
        File keepLogin = new File("c://theEqlBook/AdminInfo/Persistance/"+login+".txt");
        boolean created = keepLogin.mkdir();
        FileReader fr = new FileReader(keepLogin);
    }
}

