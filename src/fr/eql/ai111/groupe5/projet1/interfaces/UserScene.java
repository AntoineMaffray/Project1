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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

import java.io.IOException;

public class UserScene {

    Arbre arbre = new Arbre();
    Methods methods = new Methods();
    ObservableList<Stagiaire> data = arbre.arbreParcours();

    public UserScene(Stage primaryStage) throws IOException {

        //////////////////// LABEL - TITRE DE LA SCENE USERSCENE //////////////////////////////
        /*
        Cr�ation du titre du fichier en label avec son style.
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
        Cr�ation du menuBar avec son menu et ses menusItems avec les �v�nements li�s :
        Rechercher => redirection vers la page de recherche de crit�res.
        ExportPDF => export du fichier en PDF.
        Retour => redirection vers la page d'accueil.
        Documentation => consigne pour l'utilisation de l'application
        Quitter => quitter l'application.
        Apr�s avoir cr�� le menuBar et les menuItems, on ajoute les menuItems au menu,
        et le menu au menuBar.
        Pour l'affichage du menu, on l'inclut dans une BorderPane.
         */
        //MenuBar et Menus//
        MenuBar menuBar = new MenuBar();
        Menu fichierMenu = new Menu("Fichier");
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
        quitterItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        quitterItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {Platform.exit();}
        });
        //MenuItem du menu Aide//
        MenuItem documentationItem = new MenuItem("Documentation");

        //Ajout des menusItems au menu, et du menu au menuBar, affichage en BorderPane//
        fichierMenu.getItems().addAll(rechercherItem, retourAccueilItem, exportPDFItem, separator, quitterItem);
        aideMenu.getItems().addAll(documentationItem);
        menuBar.getMenus().addAll(fichierMenu, aideMenu);
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
        surnameCol.setMinWidth(100);
        //Sp�cifier comment remplir la donn�e pour chaque cellule de cette colonne avec un "cell valu factory//
        surnameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("surname"));

        TableColumn<Stagiaire, String> nameCol = new TableColumn<Stagiaire, String>("Pr�nom");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("name"));

        TableColumn<Stagiaire, Integer> deptCol = new TableColumn<Stagiaire, Integer>("Departement");
        deptCol.setMinWidth(100);
        deptCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, Integer>("dept"));

        TableColumn<Stagiaire, String> promoCol = new TableColumn<Stagiaire, String>("Formation");
        promoCol.setMinWidth(75);
        promoCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promo"));

        TableColumn<Stagiaire, Integer> yearCol = new TableColumn<Stagiaire, Integer>("Ann�e");
        yearCol.setMinWidth(50);
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
        hbox.setSpacing(5);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(surname, name, dept, promo, year, btnAjouter);
        ////////////////////////////////////////////////////////////////////////////////////////





        ///////////////////////////// AFFICHAGE DES ELEMENTS //////////////////////////////////
        /*
        On affiche tous les �l�ments dans une VBox, que l'on int�gre dans une sc�ne et ensuite un stage.
         */
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(0, 0, 20, 0));
        vbox.getChildren().addAll(menuBar, label, table, hbox);
        Scene user = new Scene(vbox);
        user.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(user);
        primaryStage.setTitle("UserScene");
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

}
