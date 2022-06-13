package fr.eql.ai111.groupe5.projet1.interfaces;

import fr.eql.ai111.groupe5.projet1.methodsback.Arbre;
import fr.eql.ai111.groupe5.projet1.methodsback.Methods;
import fr.eql.ai111.groupe5.projet1.methodsback.Stagiaire;
import fr.eql.ai111.groupe5.projet1.methodsback.User;
import fr.eql.ai111.groupe5.projet1.methodsback.UserPersistance;
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
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;


public class AdminScene {
        Arbre arbre = new Arbre();
        Methods methods = new Methods();
        ObservableList<Stagiaire> data = arbre.arbreParcours();
        TableView<Stagiaire> table = new TableView<Stagiaire>();
    public AdminScene(Stage primaryStage) throws IOException {

            //////////////////// LABEL - TITRE DE LA SCENE ADMINSCENE //////////////////////////////
            /*
             Cr�ation du titre du fichier en label avec son style.
             Pour l'affichage, on utilise un AnchorPane.
            */
            Label label= new Label("ANNUAIRE STAGIAIRES");
            label.setFont(new Font("Montserrat", 35));
            label.setMaxWidth(Double.MAX_VALUE);
            AnchorPane.setLeftAnchor(label, 0.0);
            AnchorPane.setRightAnchor(label, 0.0);
            label.setAlignment(Pos.CENTER);
            //////////////////////////////////////////////////////////////////////////////////////



            ///////////////////////////// MENU DU FICHIER //////////////////////////////////////
        /*
        Cr�ation du menuBar avec son menu et ses menusItems avec les �v�nements li�s :
        Rechercher => redirection vers la page de recherche de crit�res.
        ExportPDF => export du fichier en PDF.
        Retour => redirection vers la page d'accueil.
        Compte administrateur => permet de modifier ses propres identifiants.
        Documentation => consigne pour l'utilisation de l'application
        Quitter => quitter l'application.
        Apr�s avoir cr�� le menuBar et les menuItems, on ajoute les menuItems au menu,
        et le menu au menuBar.
        Pour l'affichage du menu, on l'inclut dans une BorderPane.
         */
            //MenuBar et Menus//
            MenuBar menuBar = new MenuBar();
            Menu fichierMenu = new Menu("Fichier");
            Menu compteMenu = new Menu("Compte administrateur");
            Menu aideMenu = new Menu("Aide");

            //MenuItems du fichier//
            MenuItem rechercherItem = new MenuItem("Rechercher");
            rechercherItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                            new SearchMenuBarAdmin(primaryStage);
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
                            Label label = new Label("Fichier � exporter");
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
            // Sp�cifier un raccourci clavier au menuItem Quitter.
            quitterItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
            // Gestion du click sur le menuItem Quitter.
            quitterItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                            Platform.exit();
                    }
            });

            // MenuItem du menu Mon compte //
            MenuItem modifierItem = new MenuItem("Modifier mes identifiants");
            modifierItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                            try {
                                    String oldLogin = "";
                                    SuperAdminTableViewOfAdminLogins superAdminTableViewOfAdminLogins =
                                            new SuperAdminTableViewOfAdminLogins(primaryStage);
                                    File file = new File("C://theEqlBook/AdminInfo/"+ ".txt");
                                    FileReader fr = new FileReader(file);
                                    BufferedReader br = new BufferedReader(fr);
                                    String oldPassword = br.readLine();
                                    String oldSurname = br.readLine();
                                    String oldName = br.readLine();
                                    br.close();
                                    fr.close();
                                    superAdminTableViewOfAdminLogins.modifFormAdmin(new Stage(), oldSurname, oldName, oldLogin, oldPassword);
                            } catch (IOException e) {
                                    throw new RuntimeException(e);
                            }
                    }
            });

            // MenuItem du menu Aide //
            MenuItem documentationItem = new MenuItem("Documentation");

            //Ajout des menusItems au menu, et du menu au menuBar, affichage en BorderPane//
            fichierMenu.getItems().addAll(rechercherItem, retourAccueilItem, exportPDFItem, separator, quitterItem);
            compteMenu.getItems().add(modifierItem);
            aideMenu.getItems().addAll(documentationItem);
            menuBar.getMenus().addAll(fichierMenu, compteMenu, aideMenu);
            BorderPane bp = new BorderPane();
            bp.setTop(menuBar);
            ////////////////////////////////////////////////////////////////////////////////



            ///////////////////////////// TABLE STAGIAIRE /////////////////////////////////
            /*
            Pour faire appara�tre la liste des stagiaires, on inclut les donn�es dans une table.
            Pour se faire, on cr�� 5 colonnes avec les informations requises
            (nom, pr�nom, d�partement,formation et ann�e), en divisant par cellule,
            et on r�cup�re les donn�es du fichier via la m�thode observable liste.
            */
            TableView<Stagiaire> table = new TableView<Stagiaire>();
            table.setEditable(true);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            //Cr�ation des cinq colonnes de la table //
            TableColumn<Stagiaire, String> surnameCol = new TableColumn<Stagiaire, String>("Nom");
            surnameCol.setMinWidth(250);
            //Sp�cifier comment remplir la donn�e pour chaque cellule de cette colonne avec un "cell valu factory//
            surnameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("surname"));

            TableColumn<Stagiaire, String> nameCol = new TableColumn<Stagiaire, String>("Pr�nom");
            nameCol.setMinWidth(250);
            nameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("name"));

            TableColumn<Stagiaire, Integer> deptCol = new TableColumn<Stagiaire, Integer>("Departement");
            deptCol.setMinWidth(200);
            deptCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, Integer>("dept"));

            TableColumn<Stagiaire, String> promoCol = new TableColumn<Stagiaire, String>("Formation");
            promoCol.setMinWidth(250);
            promoCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promo"));

            TableColumn<Stagiaire, Integer> yearCol = new TableColumn<Stagiaire, Integer>("Ann�e");
            yearCol.setMinWidth(200);
            yearCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,Integer>("year"));

            //On ajoute les cinq colonnes � la table//
            table.getColumns().addAll(surnameCol, nameCol, deptCol, promoCol, yearCol);

            //On remplit la table avec la liste observable//
            table.setItems(data);
            /////////////////////////////////////////////////////////////////////////////////



            ///////////////////////////// AJOUT DU STAGIAIRE //////////////////////////////////
                /*
                Cr�ation de des champs et du bouton d'ajout pour ajouter un stagiaire � la liste.
                On les inclut dans une HBox.
                */
            //Creation champs de rajout//
            TextField surname = new TextField();
            surname.setPromptText("Nom");
            TextField name = new TextField();
            name.setPromptText("Pr�nom");
            TextField dept = new TextField();
            dept.setPromptText("D�partement");
            TextField promo = new TextField();
            promo.setPromptText("Promotion");
            TextField year = new TextField();
            year.setPromptText("Ann�e");

            //Creation du bouton avec l'�v�nement et sa m�thode de confirmation via une alerte. //
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




            ///////////////////// MODIFICATION ET/OU SUPPRESSION DU STAGIAIRE ////////////////
            /* Pour faciliter la gestion du stagiaire, un context menu a �t� cr�� permettant
            en faisant un clic-droit sur la liste des stagiaires,  de modifier ou supprimer
            le stagiaire s�lectionn�.
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
                            try {
                                    data = arbre.arbreParcours();
                            } catch (IOException e) {
                                    throw new RuntimeException(e);
                            }
                            boolean suppression = confirmationSuppression();
                            if (suppression){
                                    table.setItems(data);
                            }
                    }
            });
            /////////////////////////////////////////////////////////////////////////////////



            ///////////////////////////// AFFICHAGE DES ELEMENTS //////////////////////////////////
                /*
                On affiche tous les �l�ments dans une VBox, que l'on int�gre dans une sc�ne et ensuite un stage.
                */
            VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(0, 0, 20, 0));
            vbox.getChildren().addAll(menuBar, label, table, hbox);
            Scene admin = new Scene(vbox);
            admin.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.setScene(admin);
            primaryStage.setTitle("AdminScene");
            primaryStage.show();
    }
        ////////////////////////////////////////////////////////////////////////////////////////

        private void confirmationInscription() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation Inscription");

                // Texte sans en-t?te
                alert.setHeaderText(null);
                alert.setContentText("Votre stagiaire a bien �t� enregistr�!");
                alert.showAndWait();
        }

        private Label label;

        private boolean confirmationSuppression() {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Supprimer l'utilisateur");
                alert.setHeaderText("Etes-vous s�r de vouloir supprimer l'utilisateur?");

                // option != null.
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == null) {
                        this.label.setText("Aucun utilisateur n'a �t� s�lectionn�");
                } else if (option.get() == ButtonType.OK) {
                        this.label.setText("Utilisateur supprim�!");
                        return true;
                } else if (option.get() == ButtonType.CANCEL) {
                        this.label.setText("Annul�");
                        alert.close();
                } else {
                        this.label.setText("-");
                }
                return false;
        }

        public void modifFormStagiaire (Stage stage, String oldSurname, String oldName, String oldDept,
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

//                public void modifFormAdmin (Stage stage, String login){
//                        Label surnameLabel = new Label();
//                        TextField newSurname = new TextField(oldSurname);
//                        Label nameLabel = new Label();
//                        TextField newName = new TextField(oldName);
//                        Label deptLabel = new Label();
//                        TextField newDept = new TextField(oldDept);
//                        Label promoLabel = new Label();
//                        TextField newPromo = new TextField(oldPromo);
//                        Label yearLabel = new Label();
//                        TextField newYear = new TextField(oldYear);
//                        Button btnValidate = new Button("Valider");
//                        Button btnCancel = new Button("Annuler");
//
//                        GridPane gridModif = new GridPane();
//                        gridModif.setVgap(5);
//                        gridModif.setHgap(5);
//                        gridModif.setPadding(new Insets(5,5,5,5));
//
//                        gridModif.add(surnameLabel, 0, 0);
//                        gridModif.add(newSurname, 1, 0);
//                        gridModif.add(nameLabel, 0, 1);
//                        gridModif.add(newName, 1, 1);
//                        gridModif.add(deptLabel, 0, 2);
//                        gridModif.add(newDept, 1, 2);
//                        gridModif.add(promoLabel, 0, 3);
//                        gridModif.add(newPromo, 1, 3);
//                        gridModif.add(yearLabel, 0, 4);
//                        gridModif.add(newYear, 1, 4);
//                        gridModif.add(btnValidate, 1, 5);
//                        gridModif.add(btnCancel, 2, 5);
//
//                        Scene subScene = new Scene(gridModif);
//                        stage.setScene(subScene);
//                        stage.show();

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
                                        try {
                                                data = arbre.arbreParcours();
                                        } catch (IOException e) {
                                                throw new RuntimeException(e);
                                        }
                                        table.setItems(data);
                                        stage.close();
                                } else {
                                        System.out.println("Le stagiaire n'a pas ?t? modifi?.");
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


