package fr.eql.ai111.groupe5.projet1.interfaces;
import fr.eql.ai111.groupe5.projet1.methodsback.Arbre;
import fr.eql.ai111.groupe5.projet1.methodsback.Methods;
import fr.eql.ai111.groupe5.projet1.methodsback.Stagiaire;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SuperAdminStagiairesDesactives {

    Arbre arbre = new Arbre();
    Methods methods = new Methods();
    ObservableList <Stagiaire> listDeactivated = arbre.arbreParcoursInv();
    TableView<Stagiaire> tableDeactivated = new TableView<Stagiaire>();
    public SuperAdminStagiairesDesactives (Stage primaryStage) throws IOException {
        //////////////////// LABEL - TITRE DE LA SCENE SUPERADMINSCENE //////////////////////////////
            /*
             Cr?ation du titre du fichier en label avec son style.
             Pour l'affichage, on utilise un AnchorPane.
            */
        Label label= new Label("STAGIAIRES DESACTIVES");
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
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String namePDF = null;
                        namePDF = tf.getText();
                        tf.clear();
                        try {
                            methods.ExportPDF(listDeactivated, namePDF);
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
        // Sp?cifier un raccourci clavier au menuItem Quitter.
        quitterItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        // Gestion du click sur le menuItem Quitter.
        quitterItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                File delete = new File ("Identifiants/Persistance/Login.txt");
                boolean isDeleted = delete.delete();
                if (isDeleted) {
                    System.out.println("Le fichier a bien ?t? supprim?");
                } else {
                    System.out.println("Le fichier a bien ?t? cr??");
                }
            }
        });

        // MenuItem du menu Mon compte //
        MenuItem modifierItem = new MenuItem("Modifier mes identifiants");
        modifierItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

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
        MenuItem deleteAdminViewMenu = new MenuItem("Liste des administrateurs supprim?s");
        deleteAdminViewMenu.setOnAction(new EventHandler<ActionEvent>() {
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
        compteAdminMenu.getItems().addAll(gestionAdminMenu, deleteAdminViewMenu);
        aideMenu.getItems().addAll(documentationItem);
        menuBar.getMenus().addAll(fichierMenu, compteAdminMenu, aideMenu);
        BorderPane bp = new BorderPane();
        bp.setTop(menuBar);
        ////////////////////////////////////////////////////////////////////////////////



        ///////////////////////////// TABLE STAGIAIRE SUPPRIME /////////////////////////////////
            /*
            Pour faire appara?tre la liste des stagiaires supprim?s, on inclut les donn?es dans une table.
            Pour se faire, on cr?? 5 colonnes avec les informations requises
            (nom, pr?nom, d?partement,formation et ann?e), en divisant par cellule,
            et on r?cup?re les donn?es du fichier via la m?thode observable liste.
            */
        tableDeactivated.setEditable(true);
        tableDeactivated.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Cr?ation des cinq colonnes de la table //
        TableColumn<Stagiaire, String> surnameCol = new TableColumn<Stagiaire, String>("Nom");
        surnameCol.setMinWidth(250);
        //Sp?cifier comment remplir la donn?e pour chaque cellule de cette colonne avec un "cell valu factory//
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

        //On ajoute les cinq colonnes ? la table//
        table.getColumns().addAll(surnameCol, nameCol, deptCol, promoCol, yearCol);
        //On ajoute les cinq colonnes � la table//
        tableDeactivated.getColumns().addAll(surnameCol, nameCol, deptCol, promoCol, yearCol);

        //On remplit la table avec la liste observable//
        tableDeactivated.setItems(listDeactivated);
        /////////////////////////////////////////////////////////////////////////////////



        ///////////////////// REACTIVATION DU STAGIAIRE ////////////////
            /* Pour faciliter la gestion du stagiaire, un context menu a ?t? cr?? permettant
            en faisant un clic-droit sur la liste des stagiaires,  de r?activer le stagiaire
            s?lectionn?.
            */
        // ContextMenu et ses MenuItems //
        ContextMenu contextMenu = new ContextMenu();
        MenuItem reactivate = new MenuItem("R?activer");
        contextMenu.getItems().add(reactivate);
        tableDeactivated.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(tableDeactivated, event.getScreenX(), event.getScreenY());
            }
        });
        // Item 1, "R?activers", du menu clic-droit
        reactivate.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    arbre.arbreDReactivation(methods.createStringOneStagiaire(tableDeactivated.getSelectionModel().getSelectedItem().getSurname(),
                            tableDeactivated.getSelectionModel().getSelectedItem().getName(),
                            tableDeactivated.getSelectionModel().getSelectedItem().getDept(),
                            tableDeactivated.getSelectionModel().getSelectedItem().getPromo(),
                            tableDeactivated.getSelectionModel().getSelectedItem().getYear()), "V");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    listDeactivated = arbre.arbreParcoursInv();
                    tableDeactivated.setItems(listDeactivated);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });

            ///////////////////////////// AFFICHAGE DES ELEMENTS //////////////////////////////////
                /*
                On affiche tous les ?l?ments dans une VBox, que l'on int?gre dans une sc?ne et ensuite un stage.
                */
                VBox vbox = new VBox();
                vbox.setSpacing(5);
                vbox.setPadding(new Insets(0, 0, 20, 0));
                vbox.getChildren().addAll(menuBar, label, tableDeactivated);
                Scene supAdminStagiaireDesactive = new Scene(vbox);
                supAdminStagiaireDesactive.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                primaryStage.setScene(supAdminStagiaireDesactive);
                primaryStage.setTitle("SuperAdminScene");
                primaryStage.show();
            }
        ////////////////////////////////////////////////////////////////////////////////////////
}

