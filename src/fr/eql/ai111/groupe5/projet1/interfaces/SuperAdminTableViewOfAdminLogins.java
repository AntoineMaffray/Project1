package fr.eql.ai111.groupe5.projet1.interfaces;

import fr.eql.ai111.groupe5.projet1.methodsback.Arbre;
import fr.eql.ai111.groupe5.projet1.methodsback.Methods;
import fr.eql.ai111.groupe5.projet1.methodsback.MethodsConnexion;
import fr.eql.ai111.groupe5.projet1.methodsback.Stagiaire;
import fr.eql.ai111.groupe5.projet1.methodsback.User;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class SuperAdminTableViewOfAdminLogins {
    Arbre arbre = new Arbre();
    Methods methods = new Methods();
    MethodsConnexion methodsConnexion = new MethodsConnexion();
    ObservableList<Stagiaire> data = arbre.arbreParcours();
    ObservableList <User> dataLogin = methodsConnexion.createUserList();
    TableView<User> table = new TableView<>();


    public SuperAdminTableViewOfAdminLogins (Stage primaryStage) throws IOException {

        /////////////////LABEL - TITRE DE LA SCENE SUPER_ADMIN_TABLE_ADMIN_SCENE //////////////////
            /*
             Cr?ation du titre du fichier en label avec son style.
             Pour l'affichage, on utilise un AnchorPane.
            */
            Label label= new Label("ANNUAIRE ADMINISTRATEURS");
            label.setFont(new Font("Montserrat", 35));
            label.setMaxWidth(Double.MAX_VALUE);
            AnchorPane.setLeftAnchor(label, 0.0);
            AnchorPane.setRightAnchor(label, 0.0);
            label.setAlignment(Pos.TOP_CENTER);
        //////////////////////////////////////////////////////////////////////////////////////



        ///////////////////////////// MENU DU FICHIER //////////////////////////////////////
            /*
            Cr?ation du menuBar avec son menu et ses menusItems avec les ?v?nements li?s :
            Rechercher => redirection vers la page de recherche de crit?res.
            ExportPDF => export du fichier en PDF.
            Retour => redirection vers la page d'accueil.
            Compte administrateur => permet de modifier ses propres identifiants.
            Documentation => consigne pour l'utilisation de l'application
            Quitter => quitter l'application.
            Apr?s avoir cr?? le menuBar et les menuItems, on ajoute les menuItems au menu,
            et le menu au menuBar.
            Pour l'affichage du menu, on l'inclut dans une BorderPane.
            */
            //MenuBar et Menus//
            MenuBar menuBar = new MenuBar();
            Menu fichierMenu = new Menu("Fichier");
            Menu compteAdminMenu = new Menu("Gestion des comptes administrateurs");
            Menu aideMenu = new Menu("Aide");

        //MenuItems du fichier//
        MenuItem rechercherItem = new MenuItem("Rechercher");
        rechercherItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new SearchMenuBarSuperAdmin(primaryStage);
            }
        });
        MenuItem retourAccueilItem = new MenuItem("Retour accueil");
        retourAccueilItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new AccueilScene(primaryStage);
            }
        });
        MenuItem exportPDFItem = new MenuItem("Exporter au format PDF");
        exportPDFItem.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
        exportPDFItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Stage dialog = new Stage();
                Label label = new Label("Fichier ? exporter");
                label.setFont(new Font("Montserrat", 20));
                label.setOpacity(0.9);
                label.setStyle("-fx-text-fill: black");
                label.setMaxWidth(Double.MAX_VALUE);
                AnchorPane.setLeftAnchor(label, 0.0);
                AnchorPane.setRightAnchor(label, 0.0);
                label.setAlignment(Pos.CENTER);

                TextField tf = new TextField("");
                tf.setPromptText("Veuillez entrer un nom de fichier");
                Button btn = new Button("Valider");
                EventHandler epdf = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String namePDF = null;
                        namePDF = tf.getText();
                        tf.clear();
                        try {
                            methods.ExportPDF(data, namePDF);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        dialog.close();
                    }
                };
                btn.setOnAction(epdf);
                tf.setOnAction(epdf);

                Button btnFermer = new Button("Fermer");
                btnFermer.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        dialog.close();
                    }
                });

                HBox hbBtnFermer = new HBox(10);
                hbBtnFermer.setAlignment(Pos.BOTTOM_RIGHT);
                hbBtnFermer.getChildren().add(btnFermer);

                GridPane grille = new GridPane();
                grille.setPadding(new Insets(10, 10, 10, 10));
                grille.setAlignment(Pos.CENTER);
                grille.setHgap(10);
                grille.setVgap(10);
                grille.getChildren().add(label);
                grille.add(tf, 0, 1);
                grille.add(btn, 1, 6);
                grille.add(hbBtnFermer, 1, 8);

                Scene dialogScene = new Scene(grille, 500, 400);
                dialog.setScene(dialogScene);
                dialogScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                dialog.setTitle("Export fichier PDF");
                dialog.show();
            }
        });
        SeparatorMenuItem separator= new SeparatorMenuItem();
        MenuItem quitterItem = new MenuItem("Quitter");
        // Sp?cifier un raccourci clavier au menuItem Quitter.
        quitterItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        // Gestion du click sur le menuItem Quitter.
        quitterItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
//                File delete = new File ("Identifiants/Persistance/Login.txt");
//                boolean isDeleted = delete.delete();
//                if (isDeleted) {
//                    System.out.println("Le fichier a bien ?t? supprim?");
//                } else {
//                    System.out.println("Le fichier a bien ?t? cr??");
//                }
            }
        });


        // Cr?ation du MenuItem du menu Compte Admin
        MenuItem gestionAdminMenu = new MenuItem("Gestion de l'administrateur");
        gestionAdminMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new SuperAdminTableViewOfAdminLogins(primaryStage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        MenuItem deleteStagiairesViewMenu = new MenuItem("Liste des stagiares supprim�s");
        deleteStagiairesViewMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new SuperAdminStagiairesDesactives(primaryStage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        MenuItem deleteAdminViewMenu = new MenuItem("Liste des administrateurs supprim?s");

        // MenuItems du menu Aide //
        MenuItem documentationItem = new MenuItem("Documentation");

        // Ajouter les menuItems aux Menus
        fichierMenu.getItems().addAll(rechercherItem, retourAccueilItem, exportPDFItem, separator, quitterItem);
        compteAdminMenu.getItems().addAll(gestionAdminMenu, deleteStagiairesViewMenu);
        aideMenu.getItems().addAll(documentationItem);
        menuBar.getMenus().addAll(fichierMenu, compteAdminMenu, aideMenu);
        BorderPane bp = new BorderPane();
        bp.setTop(menuBar);
        ////////////////////////////////////////////////////////////////////////////////





        ///////////////////////////// TABLE ADMINISTRATEUR /////////////////////////////////
            /*
            Pour faire appara?tre la liste des admin, on inclut les donn?es dans une table.
            Pour se faire, on cr?? 3 colonnes avec les informations requises
            (nom, pr?nom, login), en divisant par cellule,
            et on r?cup?re les donn?es du fichier via la m?thode observable liste.
            */
            table.setEditable(true);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Cr?ation des trois colonnes de la table //
        TableColumn<User, String> surnameCol = new TableColumn<>("Nom");
        surnameCol.setMinWidth(250);
        ////Sp?cifier comment remplir la donn?e pour chaque cellule de cette colonne avec un "cell valu factory//
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<User, String> nameCol = new TableColumn<>("Pr?nom");
        nameCol.setMinWidth(250);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> loginCol = new TableColumn<>("Login");
        loginCol.setMinWidth(250);
        loginCol.setCellValueFactory(new PropertyValueFactory<>("login"));

        //On ajoute les trois colonnes ? la table//
        table.getColumns().addAll(loginCol, surnameCol, nameCol);

        //On remplit la table avec la liste observable//
        table.setItems(dataLogin);
        /////////////////////////////////////////////////////////////////////////////////


        ///////////////////////////// AJOUT ADMINISTRATEUR //////////////////////////////////
        /*
        Cr?ation de des champs et du bouton d'ajout pour ajouter un stagiaire ? la liste.
        On les inclut dans une HBox.
         */
        //Creation champs de rajout//
        TextField login = new TextField();
        login.setPromptText("Login");

        //Creation du bouton avec l'�v�nement et sa m�thode de confirmation via une alerte. //
        Button btnNewLogin = new Button("Nouveau login");
                EventHandler eventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    createLoginAdmin(login.getText());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } confirmationInscriptionAdmin();
                try {
                    dataLogin = methodsConnexion.createUserList();
                    table.setItems(dataLogin);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        btnNewLogin.setOnAction(eventHandler);
        login.setOnAction(eventHandler);

        HBox hbox = new HBox();
        hbox.setSpacing(5);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.getChildren().addAll(login, btnNewLogin);


        ///////////////////// MODIFICATION ET/OU SUPPRESSION DE L'ADMINISTRATEUR ////////////////
            /* Pour faciliter la gestion du stagiaire, un context menu a ?t? cr?? permettant
            en faisant un clic-droit sur la liste des stagiaires,  de modifier ou supprimer
            le stagiaire s?lectionn?.
            */
        // ContextMenu et ses MenuItems //
        ContextMenu contextMenu = new ContextMenu();
        MenuItem modifierAdmin = new MenuItem("Modifier");
        MenuItem supprimerAdmin = new MenuItem("Supprimer");

        contextMenu.getItems().addAll(modifierAdmin, supprimerAdmin);
        contextMenu.setAutoHide(true);

        // Instanciation du menu clic-droit avec ses items //
        table.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(table, event.getScreenX(), event.getScreenY());
            }
        });
        modifierAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (table.getSelectionModel().getSelectedItem().getSurname() != null
                        && table.getSelectionModel().getSelectedItem().getName() != null){
                    try {
                        modifFormAdmin(new Stage(), table.getSelectionModel().getSelectedItem().getSurname(),
                                table.getSelectionModel().getSelectedItem().getName(),
                                table.getSelectionModel().getSelectedItem().getLogin(),
                                table.getSelectionModel().getSelectedItem().getPassword());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    comptePasEncoreCree();
                }
            }
        });

        supprimerAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toDelete = table.getSelectionModel().getSelectedItem().getLogin();
                File filetoDelete = new File("C://theEqlbook/AdminInfo/" + toDelete + ".txt");
                System.out.println(toDelete+".txt");
                filetoDelete.delete();
                if (filetoDelete.exists() == false){
                    System.out.println("file deleted");
                }else{
                }
                try {
                    dataLogin = methodsConnexion.createUserList();
                    table.setItems(dataLogin);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////


        ///////////////////////////// AFFICHAGE DES ELEMENTS //////////////////////////////////
            /*
            On affiche tous les ?l?ments dans une VBox, que
            l'on int?gre dans une sc?ne et ensuite un stage.
            */
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(0, 0, 20, 0));
        vbox.getChildren().addAll(menuBar, label, table, hbox);

        Scene scene = new Scene(vbox);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Gestion des Administrateurs");
        primaryStage.setWidth(1250);
        primaryStage.setHeight(800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //////////////////////////////////////////////////////////////////////////////////////

    public void modifFormAdmin (Stage stage, String oldSurname, String oldName, String oldLogin, String oldPassword)
            throws IOException {
        try {
            Label titleLabel = new Label("Modification du compte");
            Label surnameLabel = new Label("Nom");
            TextField newSurname = new TextField(oldSurname);
            Label nameLabel = new Label("Pr�nom");
            TextField newName = new TextField(oldName);
            Label loginLabel = new Label("Identifiant");
            TextField newLogin = new TextField(oldLogin.substring(0, oldLogin.length() - 4));
            Label newPwLabel = new Label("Nouveau mot de passe");
            TextField newPw = new TextField();
            Label verifPwLabel = new Label("V�rification du mot de passe");
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
            gridModif2.setPadding(new Insets(10, 10, 10, 10));

            gridModif2.add(titleLabel, 0, 0, 2, 1);
            gridModif2.add(surnameLabel, 0, 1);
            gridModif2.add(newSurname, 1, 1);
            gridModif2.add(nameLabel, 0, 2);
            gridModif2.add(newName, 1, 2);
            gridModif2.add(loginLabel, 0, 3);
            gridModif2.add(newLogin, 1, 3);
            gridModif2.add(newPwLabel, 0, 4);
            gridModif2.add(newPw, 1, 4);
            gridModif2.add(verifPwLabel, 0, 5);
            gridModif2.add(verifPw, 1, 5);
            gridModif2.add(hboxBtn, 1, 6);

            Scene subModifScene = new Scene(gridModif2);
            stage.setScene(subModifScene);
            stage.show();

            btnValidate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (oldSurname != newSurname.getText() && oldName != newName.getText() && oldLogin != newLogin.getText()
                            && oldPassword != newPw.getText()) {

                        if (newPw.getText().equals(verifPw.getText())) {
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
                        System.out.println("Informations identiques, compte non modifi?.");
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

        } catch (StringIndexOutOfBoundsException e) {
                // popup ? faire identifiant pas encore activ?
        }
    }

    private void createLoginAdmin (String login) throws IOException {

        File newLogin = new File ("C://theEQLBook/AdminInfo/"+login+".txt");

        if (newLogin.exists()){
            // rajouter une POP UP identifiants d?j? cr??s
        } else {
            newLogin.createNewFile();
        }
    }

    private void confirmationInscriptionAdmin() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation Inscription");

        // Texte sans en-t?te
        alert.setHeaderText(null);
        alert.setContentText("Votre administrateur a bien �t� enregistr�!");
        alert.showAndWait();
    }

    private void comptePasEncoreCree() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Message d'erreur");
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("L'administrateur ne s'est pas encore inscrit! ");
        alert.showAndWait();
    }
}
