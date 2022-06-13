package fr.eql.ai111.groupe5.projet1.interfaces;

import fr.eql.ai111.groupe5.projet1.methodsback.Arbre;
import fr.eql.ai111.groupe5.projet1.methodsback.Methods;
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
import javafx.scene.control.ButtonType;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class SuperAdminScene {

    Arbre arbre = new Arbre();
    Methods methods = new Methods();
    ObservableList<Stagiaire> data = arbre.arbreParcours();
    TableView<Stagiaire> table = new TableView<Stagiaire>();

    public SuperAdminScene(Stage primaryStage) throws IOException {


            //////////////////// LABEL - TITRE DE LA SCENE SUPERADMINSCENE //////////////////////////////
            /*
             Création du titre du fichier en label avec son style.
             Pour l'affichage, on utilise un AnchorPane.
            */
            Label label= new Label("ANNUAIRE STAGIAIRES");
            label.setFont(new Font("Montserrat", 35));
            label.setMaxWidth(Double.MAX_VALUE);
            AnchorPane.setLeftAnchor(label, 0.0);
            AnchorPane.setRightAnchor(label, 0.0);
            label.setAlignment(Pos.TOP_CENTER);
            //////////////////////////////////////////////////////////////////////////////////////



            ///////////////////////////// MENU DU FICHIER //////////////////////////////////////
            /*
            Création du menuBar avec son menu et ses menusItems avec les événements liés :
            Rechercher => redirection vers la page de recherche de critères.
            ExportPDF => export du fichier en PDF.
            Retour => redirection vers la page d'accueil.
            Compte administrateur => permet de modifier ses propres identifiants.
            Documentation => consigne pour l'utilisation de l'application
            Quitter => quitter l'application.
            Après avoir créé le menuBar et les menuItems, on ajoute les menuItems au menu,
            et le menu au menuBar.
            Pour l'affichage du menu, on l'inclut dans une BorderPane.
            */
            //MenuBar et Menus//
            MenuBar menuBar = new MenuBar();
            Menu fichierMenu = new Menu("Fichier");
            Menu compteMenu = new Menu("Compte administrateur");
            Menu compteAdminMenu = new Menu("Gestion des comptes administrateurs");
            Menu aideMenu = new Menu("Aide");

            //MenuItems du fichier//
            MenuItem rechercherItem = new MenuItem("Rechercher");
            rechercherItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Search(primaryStage);
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
                Label label = new Label("Fichier à exporter");
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
                btn.setOnAction(new EventHandler<ActionEvent>() {
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
                });

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
            // Spécifier un raccourci clavier au menuItem Quitter.
            quitterItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
            // Gestion du click sur le menuItem Quitter.
            quitterItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
//                File delete = new File ("Identifiants/Persistance/Login.txt");
//                boolean isDeleted = delete.delete();
//                if (isDeleted) {
//                    System.out.println("Le fichier a bien été supprimé");
//                } else {
//                    System.out.println("Le fichier a bien été créé");
//                }
                }
            });

            // MenuItem du menu Mon compte //
            MenuItem modifierItem = new MenuItem("Modifier mes identifiants");
            modifierItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }
            });

            // Création du MenuItem du menu Compte Admin
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
            MenuItem deleteStagiairesViewMenu = new MenuItem("Liste des stagiairess supprimés");
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

            // MenuItems du menu Aide //
            MenuItem documentationItem = new MenuItem("Documentation");

            // Ajouter les menuItems aux Menus
            fichierMenu.getItems().addAll(rechercherItem, retourAccueilItem, exportPDFItem, separator, quitterItem);
            compteMenu.getItems().add(modifierItem);
            compteAdminMenu.getItems().addAll(gestionAdminMenu, deleteStagiairesViewMenu);
            aideMenu.getItems().addAll(documentationItem);
            menuBar.getMenus().addAll(fichierMenu, compteMenu, compteAdminMenu, aideMenu);
            BorderPane bp = new BorderPane();
            bp.setTop(menuBar);
            ////////////////////////////////////////////////////////////////////////////////



            ///////////////////////////// TABLE STAGIAIRE /////////////////////////////////
            /*
            Pour faire apparaître la liste des stagiaires, on inclut les données dans une table.
            Pour se faire, on créé 5 colonnes avec les informations requises
            (nom, prénom, département,formation et année), en divisant par cellule,
            et on récupère les données du fichier via la méthode observable liste.
            */
            TableView<Stagiaire> table = new TableView<Stagiaire>();
            table.setEditable(true);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            //Création des cinq colonnes de la table //
            TableColumn<Stagiaire, String> surnameCol = new TableColumn<Stagiaire, String>("Nom");
            surnameCol.setMinWidth(250);
            //Spécifier comment remplir la donnée pour chaque cellule de cette colonne avec un "cell valu factory//
            surnameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("surname"));

            TableColumn<Stagiaire, String> nameCol = new TableColumn<Stagiaire, String>("Prénom");
            nameCol.setMinWidth(250);
            nameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("name"));

            TableColumn<Stagiaire, Integer> deptCol = new TableColumn<Stagiaire, Integer>("Departement");
            deptCol.setMinWidth(200);
            deptCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, Integer>("dept"));

            TableColumn<Stagiaire, String> promoCol = new TableColumn<Stagiaire, String>("Formation");
            promoCol.setMinWidth(250);
            promoCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promo"));

            TableColumn<Stagiaire, Integer> yearCol = new TableColumn<Stagiaire, Integer>("Année");
            yearCol.setMinWidth(200);
            yearCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,Integer>("year"));

            //On ajoute les cinq colonnes à la table//
            table.getColumns().addAll(surnameCol, nameCol, deptCol, promoCol, yearCol);

            //On remplit la table avec la liste observable//
            table.setItems(data);
            /////////////////////////////////////////////////////////////////////////////////



            ///////////////////// MODIFICATION ET/OU SUPPRESSION DU STAGIAIRE ////////////////
            /* Pour faciliter la gestion du stagiaire, un context menu a été créé permettant
            en faisant un clic-droit sur la liste des stagiaires,  de modifier ou supprimer
            le stagiaire sélectionné.
            */
            // ContextMenu et ses MenuItems //
            ContextMenu contextMenu = new ContextMenu();
            MenuItem modifierStagiaire = new MenuItem("Modifier");
            MenuItem supprimerStagiaire = new MenuItem("Supprimer");
            contextMenu.getItems().addAll(modifierStagiaire, supprimerStagiaire);

            // Instanciation du menu clic-droit avec ses items //
            table.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
                @Override
                public void handle(ContextMenuEvent event) {
                        contextMenu.show(table, event.getScreenX(), event.getScreenY());
                    }
                });
            modifierStagiaire.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String oldSurname = table.getSelectionModel().getSelectedItem().getSurname().trim();
                    String oldName = table.getSelectionModel().getSelectedItem().getName().trim();
                    String oldDept = table.getSelectionModel().getSelectedItem().getDept().trim();
                    String oldPromo = table.getSelectionModel().getSelectedItem().getPromo().trim();
                    String oldYear = table.getSelectionModel().getSelectedItem().getYear().trim();

                    String newAdd = methods.createStringOneStagiaire(oldSurname, oldName, oldDept, oldPromo, oldYear);
                    modifFormStagiaire(new Stage(), oldSurname, oldName, oldDept, oldPromo, oldYear, newAdd);
                    try {
                        data = arbre.arbreParcours();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    table.setItems(data);
                }
            });
            supprimerStagiaire.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        arbre.arbreDReactivation(methods.createStringOneStagiaire(table.getSelectionModel().getSelectedItem().getSurname(),
                                table.getSelectionModel().getSelectedItem().getName(),
                                table.getSelectionModel().getSelectedItem().getDept(),
                                table.getSelectionModel().getSelectedItem().getPromo(),
                                table.getSelectionModel().getSelectedItem().getYear()), "I");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    boolean suppression = confirmationSuppression();
                    if (suppression){
                        try {
                            data = arbre.arbreParcours();
                            table.setItems(data);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            /////////////////////////////////////////////////////////////////////////////////


            ///////////////////////////// AJOUT DU STAGIAIRE //////////////////////////////////
                /*
                Création de des champs et du bouton d'ajout pour ajouter un stagiaire à la liste.
                On les inclut dans une HBox.
                */
            TextField surname = new TextField();
            surname.setPromptText("Nom");
            TextField name = new TextField();
            name.setPromptText("Prénom");
            TextField dept = new TextField();
            dept.setPromptText("Département");
            TextField promo = new TextField();
            promo.setPromptText("Promotion");
            TextField year = new TextField();
            year.setPromptText("Année");

            //Creation du bouton avec l'événement et sa méthode de confirmation via une alerte. //
            Button btnAjouter = new Button("Ajouter");
            btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        arbre.arbreInsertionNew(methods.createStringOneStagiaire(surname.getText(),
                                name.getText(),
                                dept.getText(),
                                promo.getText(),
                                year.getText()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        data = arbre.arbreParcours();
                        table.setItems(data);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    surname.clear();
                    name.clear();
                    dept.clear();
                    promo.clear();
                    year.clear();
                    confirmationInscription();
                }
            });
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            hbox.getChildren().addAll(surname, name, dept, promo, year, btnAjouter);
        ////////////////////////////////////////////////////////////////////////////////////////





        ///////////////////////////// AFFICHAGE DES ELEMENTS //////////////////////////////////
            /*
            On affiche tous les éléments dans une VBox, que l'on intègre dans une scène et ensuite un stage.
            */
            VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(0, 0, 20, 0));
            vbox.getChildren().addAll(menuBar, label, table, hbox);
            Scene supAdmin = new Scene(vbox);
            supAdmin.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.setScene(supAdmin);
            primaryStage.setTitle("SuperAdminScene");
            primaryStage.show();
        }
        ////////////////////////////////////////////////////////////////////////////////////////

        private void createContact(String surname, String name, String login, String password){
            User userX = new User(surname, name, login, password);
        }


    // M?thode pour cr?er une fen?tre d'information
        private void confirmationInscription() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Inscription");

        // Texte sans en-t?te
            alert.setHeaderText(null);
            alert.setContentText("Votre stagiaire a bien ?t? enregistr?!");
            alert.showAndWait();
        }

    // M?thode pour afficher une confirmation de suppresion via une fen?tre pop-up

        private boolean confirmationSuppression() {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer l'utilisateur");
            alert.setHeaderText("Etes-vous s?r de vouloir supprimer l'utilisateur?");

        // option != null.
            Optional<ButtonType> option = alert.showAndWait();
            Label label = new Label();

            boolean status = false;

            if (option.get() == null) {
                label.setText("Aucun utilisateur n'a ?t? s?lectionn?");
                status = false;
            } else if (option.get() == ButtonType.OK) {
                label.setText("Utilisateur supprimé!");
                status = true;
            } else if (option.get() == ButtonType.CANCEL) {
                label.setText("Annul?");
                alert.close();
                status = false;
            } else {
                label.setText("-");
                status = false;
            }
            return status;
        }


    private void modifFormStagiaire (Stage stage, String oldSurname, String oldName, String oldDept,
                                     String oldPromo, String oldYear, String newAdd){

        Label surnameLabel = new Label();
        TextField newSurname = new TextField(oldSurname);
        Label nameLabel = new Label();
        TextField newName = new TextField(oldName);
        Label deptLabel = new Label();
        TextField newDept = new TextField(oldDept);
        Label promoLabel = new Label();
        TextField newPromo = new TextField(oldPromo);
        Label yearLabel = new Label();
        TextField newYear = new TextField(oldYear);
        Button btnValidate = new Button("Valider");
        Button btnCancel = new Button("Annuler");

        GridPane gridModif = new GridPane();
        gridModif.setVgap(5);
        gridModif.setHgap(5);
        gridModif.setPadding(new Insets(5,5,5,5));

        gridModif.add(surnameLabel, 0, 0);
        gridModif.add(newSurname, 1, 0);
        gridModif.add(nameLabel, 0, 1);
        gridModif.add(newName, 1, 1);
        gridModif.add(deptLabel, 0, 2);
        gridModif.add(newDept, 1, 2);
        gridModif.add(promoLabel, 0, 3);
        gridModif.add(newPromo, 1, 3);
        gridModif.add(yearLabel, 0, 4);
        gridModif.add(newYear, 1, 4);
        gridModif.add(btnValidate, 1, 5);
        gridModif.add(btnCancel, 2, 5);

        Scene subScene = new Scene(gridModif);
        stage.setScene(subScene);
        stage.show();

        btnValidate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String modifiedString = methods.createStringOneStagiaire(newSurname.getText(), newName.getText(), newDept.getText(),
                        newPromo.getText(), newYear.getText());
                if (methods.searchCharToChar(newAdd, modifiedString) != 0){
                    try {
                        arbre.arbreModification(newAdd,modifiedString);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.close();
                } else {
                    System.out.println("Le stagiaire n'a pas été modifié.");
                }
            }
        });

        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
    }
}
