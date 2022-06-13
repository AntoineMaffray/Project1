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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.geometry.Pos.CENTER;

public class InscriptionScene {
    User user;
    UserDAO dao = new UserDAO();

    public InscriptionScene(Stage primaryStage) {

        //////////////////// LABEL - TITRE DE LA SCENE INSCRIPTION //////////////////////////////
        /*
        Cr?ation du titre du fichier en texte avec son style.
        Cr?ation du formulaire d'inscription avec le login, le nom, le pr?nom, le password et le r?le.
         */
        Text titre = new Text("Inscription");
        titre.setFont(Font.font("Roboto", FontWeight.BOLD, 20));


        Label log = new Label("Login:");
        TextField loginTextField = new TextField("");
        Label nom = new Label("Nom:");
        TextField nomTextField = new TextField("");
        Label prenom = new Label("Pr?nom :");
        TextField prenomTextField = new TextField("");
        Label pswd = new Label("Mot de passe :");
        PasswordField pswdPasswordField = new PasswordField();
        //////////////////////////////////////////////////////////////////////////////////////


        ///////////////////////////// REDIRECTIONS PAGES ////////////////////////////////////
        /*
        Pour faire appara?tre les diff?rentes interfaces, on utilise des boutons afin que
        l'administrateur puisse acc?der au fichier correspondant.
        Les boutons sont plac?s dans une HBox.
        Afin que l'administrateur puisse acc?der au fichier, il doit d'abord s'inscrire s'il n'a pas de compte
        ou se connecter si son compte est cr??.
        Si ce n'est pas un administrateur mais un utilisateur, il peut retourner sur la page d'accueil.
         */
        //Cr?ation du bouton de validation et du bouton de redirection vers la page d'accueil//
        Button btnValidate = new Button("Validez");
        btnValidate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                user = new User(nomTextField.getText(),
                        prenomTextField.getText(),
                        loginTextField.getText(),
                        pswdPasswordField.getText());
                inscriptionUser(user);
                try {
                    new ConnexionScene(primaryStage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        Button btnReturn = new Button("Retour");
        btnReturn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new ConnexionScene(primaryStage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////



        ///////////////////////////// AFFICHAGE DES ELEMENTS //////////////////////////////////
        /*
        On affiche tous les ?l?ments dans une GridPane, que l'on int?gre dans une sc?ne et ensuite un stage.
         */
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnValidate);

        HBox hbBtn1 = new HBox(5);
        hbBtn1.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn1.getChildren().add(btnReturn);

        GridPane grille = new GridPane();
        grille.setAlignment(CENTER);
        grille.setHgap(10);
        grille.setVgap(10);
        grille.setPadding(new Insets(20, 20, 20, 20));
        grille.add(titre, 1, 0, 2, 1);
        grille.add(log, 0, 1);
        grille.add(loginTextField, 1, 1);
        grille.add(nom, 0, 2);
        grille.add(nomTextField, 1, 2);
        grille.add(prenom, 0, 3);
        grille.add(prenomTextField, 1, 3);
        grille.add(pswd, 0, 4);
        grille.add(pswdPasswordField, 1, 4);
        grille.add(hbBtn, 1, 7);
        grille.add(hbBtn1, 3, 9);

        Scene inscription = new Scene(grille, 500, 450);
        inscription.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(inscription);
        primaryStage.setTitle("Inscription");
        primaryStage.show();
    }
        ////////////////////////////////////////////////////////////////////////////////////////


    private void idendifiantsDejaCrees() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Message d'erreur");
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Attention ces identifiants existent d?j?!");
        alert.showAndWait();
    }

    public String loginMemory (User user) {
        return user.getLogin();
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
                user.getPassword());
        if (!isCreated) {
            idendifiantsDejaCrees();
        }
    }

}
