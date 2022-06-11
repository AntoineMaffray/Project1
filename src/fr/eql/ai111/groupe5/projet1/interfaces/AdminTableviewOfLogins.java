package fr.eql.ai111.groupe5.projet1.interfaces;

import fr.eql.ai111.groupe5.projet1.methodsback.Methods;
import fr.eql.ai111.groupe5.projet1.methodsback.MethodsConnexion;
import fr.eql.ai111.groupe5.projet1.methodsback.User;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AdminTableviewOfLogins {

    Methods methods = new Methods();
    MethodsConnexion methodsConnexion = new MethodsConnexion();
    ObservableList <User> dataLogin = methodsConnexion.createUserList();
    TableView<User> table = new TableView<>();

    public AdminTableviewOfLogins (Stage primaryStage) throws IOException {

        Label label= new Label("Gestion des comptes Administrateur");
        label.setFont(new Font("Arial", 35));
        label.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label, 0.0);
        AnchorPane.setRightAnchor(label, 0.0);
        label.setAlignment(Pos.TOP_CENTER);

        // Création de MenuBar
        MenuBar menuBar = new MenuBar();

        // Creation des menus
        Menu fichierMenu = new Menu("Fichier");
        Menu identifiantMenu = new Menu("Identifiants");
        Menu aideMenu = new Menu("Aide");

        // Creation des MenuItems du menu Fichier
        MenuItem nouveauItem = new MenuItem("Nouveau");
        MenuItem ouvrirItem = new MenuItem("Ouvrir");
        SeparatorMenuItem separator= new SeparatorMenuItem();
        MenuItem quitterItem = new MenuItem("Quitter");
        // Spécifier un raccourci clavier au menuItem Quitter.
        quitterItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        // Gestion du click sur le menuItem Quitter.
        quitterItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        // Creation des MenuItems du menu Identifiants
        MenuItem creerItem = new MenuItem("Créer");
        MenuItem modifierItem = new MenuItem("Modifier");
        MenuItem supprimerItem = new MenuItem("Supprimer");

        // Creation des MenuItems du menu Aide
        MenuItem documentationItem = new MenuItem("Documentation");
        SeparatorMenuItem separator1= new SeparatorMenuItem();
        MenuItem rechercherItem = new MenuItem("Rechercher");
        rechercherItem.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
        rechercherItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        // Ajouter les menuItems aux Menus
        fichierMenu.getItems().addAll(nouveauItem, ouvrirItem, separator, quitterItem);
        identifiantMenu.getItems().addAll(creerItem, modifierItem, supprimerItem);
        aideMenu.getItems().addAll(documentationItem, separator1, rechercherItem);

        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Création des quatre colonnes
        TableColumn<User, String> surnameCol =
                new TableColumn<>("Nom");
        surnameCol.setMinWidth(250);
        //Spécifier comment remplir la donnée pour chaque cellule de cette colonne
        //Ceci se fait en specifiant un "cell value factory" pour cette colonne.
        surnameCol.setCellValueFactory(
                new PropertyValueFactory<>("surname"));

        TableColumn<User, String> nameCol = new TableColumn<>("Prénom");
        nameCol.setMinWidth(250);
        //specifier un "cell factory" pour cette colonne.
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn<User, String> loginCol = new TableColumn<>("Login");
        loginCol.setMinWidth(250);
        //specifier un "cell factory" pour cette colonne.
        loginCol.setCellValueFactory(new PropertyValueFactory<>("login"));

        //On ajoute les trois colonnes à la table
        table.getColumns().addAll(loginCol, surnameCol, nameCol);

        //On remplit la table avec la liste observable
        table.setItems(dataLogin);

        //On crée le menu du clic droit qui va permettre de modifier et supprimer un compte Amdin
        ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Modifier");
        MenuItem item2 = new MenuItem("Supprimer");
        contextMenu.getItems().addAll(item1, item2);
        contextMenu.setAutoHide(true);

        table.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(table, event.getScreenX(), event.getScreenY());
            }
        });
        // Action de modifier
        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    modifFormAdmin(new Stage(), table.getSelectionModel().getSelectedItem().getSurname(),
                            table.getSelectionModel().getSelectedItem().getName(),
                            table.getSelectionModel().getSelectedItem().getLogin(),
                            table.getSelectionModel().getSelectedItem().getPassword());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        item2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toDelete = table.getSelectionModel().getSelectedItem().getLogin();
                File filetoDelete = new File("C://theEqlbook/AdminInfo/" + toDelete + ".txt");
                System.out.println(toDelete+".txt");
                filetoDelete.delete();
                if (filetoDelete.delete()){
                    System.out.println("file deleted");
                }else{
                    System.out.println("wtf?");
                }
                try {
                    dataLogin = methodsConnexion.createUserList();
                    table.setItems(dataLogin);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(0, 0, 20, 0));
        vbox.getChildren().addAll(menuBar, label, table);

        Scene scene = new Scene(vbox);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Gestion des Administrateurs");
        primaryStage.setWidth(1250);
        primaryStage.setHeight(800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void modifFormAdmin (Stage stage, String oldSurname, String oldName, String oldLogin, String oldPassword) throws IOException {
        Label titleLabel = new Label("Modification du compte");
        Label surnameLabel = new Label("Nom");
        TextField newSurname = new TextField(oldSurname);
        Label nameLabel = new Label("Prénom");
        TextField newName = new TextField(oldName);
        Label loginLabel = new Label("Identifiant");
        TextField newLogin = new TextField(oldLogin.substring(0, oldLogin.length()-4));
        Label newPwLabel = new Label("Nouveau mot de passe");
        TextField newPw = new TextField();
        Label verifPwLabel = new Label("Vérification du mot de passe");
        TextField verifPw = new TextField();
        Button btnValidate = new Button("Valider");
        Button btnCancel = new Button("Annuler");

        HBox hboxBtn = new HBox();
        hboxBtn.getChildren().addAll(btnValidate, btnCancel);
        hboxBtn.setSpacing(50);
        hboxBtn.setAlignment(Pos.CENTER_RIGHT);

        GridPane gridModif2 = new GridPane();
        gridModif2.setAlignment(Pos.TOP_CENTER);
        gridModif2.setVgap(15);
        gridModif2.setHgap(20);
        gridModif2.setPadding(new Insets(10,10,10,10));

        gridModif2.add(titleLabel, 0,0, 2, 1);
        gridModif2.add(surnameLabel, 0,1);
        gridModif2.add(newSurname, 1,1);
        gridModif2.add(nameLabel, 0,2);
        gridModif2.add(newName, 1,2);
        gridModif2.add(loginLabel, 0,3);
        gridModif2.add(newLogin, 1,3);
        gridModif2.add(newPwLabel, 0,4);
        gridModif2.add(newPw, 1,4);
        gridModif2.add(verifPwLabel, 0,5);
        gridModif2.add(verifPw, 1,5);
        gridModif2.add(hboxBtn, 1,6);

        Scene subModifScene = new Scene(gridModif2);
        stage.setScene(subModifScene);
        stage.show();

        btnValidate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (oldSurname != newSurname.getText() && oldName != newName.getText() && oldLogin != newLogin.getText()
                        && oldPassword != newPw.getText()){

                    if (newPw.getText().equals(verifPw.getText())){
                        File loginFileX = new File("C://theEQLBook/AdminInfo/" + newLogin.getText() + ".txt");
                        try {
                            loginFileX.createNewFile();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            FileWriter frX = new FileWriter(loginFileX);
                            BufferedWriter bwX = new BufferedWriter(frX);
                            bwX.write(methods.hashage(newPw.getText()));
                            bwX.newLine();
                            bwX.write(newSurname.getText());
                            bwX.newLine();
                            bwX.write(newName.getText());
                            bwX.newLine();
                            bwX.write("Admin");
                            bwX.close();
                            frX.close();
                            File toRemove = new File("C://theEQLBook/AdminInfo/" + oldLogin + ".txt");
                            toRemove.delete();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("Passwords non identiques.");
                    }
                } else if (oldSurname == newSurname.getText() && oldName == newName.getText() && oldLogin == newLogin.getText()
                        && oldPassword == newPw.getText()) {
                    System.out.println("Informations identiques, compte non modifié.");
                }
                try {
                    dataLogin = methodsConnexion.createUserList();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                File filetoDelete = new File("C://theEQLBook/AdminInfo/" + oldLogin + ".txt");
                filetoDelete.delete();
                table.setItems(dataLogin);
                stage.close();
            }
        });
    }
}
