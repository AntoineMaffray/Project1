package fr.eql.ai111.groupe5.projet1.interfaces;

import fr.eql.ai111.groupe5.projet1.methodsback.Stagiaire;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class AdminScene {

    public AdminScene(Stage primaryStage) {

            Label label = new Label("ANNUAIRE STAGIAIRES");
            label.setFont(new Font("Arial", 35));
            label.setMaxWidth(Double.MAX_VALUE);
            AnchorPane.setLeftAnchor(label, 0.0);
            AnchorPane.setRightAnchor(label, 0.0);
            label.setAlignment(Pos.TOP_CENTER);

            // Cr�ation de MenuBar
            MenuBar menuBar = new MenuBar();

            // Creation des menus
            Menu fichierMenu = new Menu("Fichier");
            Menu compteMenu = new Menu("Mon Compte");
            Menu aideMenu = new Menu("Aide");

            // Creation des MenuItems du menu Fichier
            MenuItem exportItem = new MenuItem("Export");
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

            // Création du MenuItem du menu Mon compte
            MenuItem modifierItem = new MenuItem("Modifier mes identifiants");

            // Creation des MenuItems du menu Aide
            MenuItem documentationItem = new MenuItem("Documentation");

            // Ajouter les menuItems aux Menus
            fichierMenu.getItems().addAll(exportItem, separator, quitterItem);
            compteMenu.getItems().add(modifierItem);
            aideMenu.getItems().addAll(documentationItem);

            // Ajouter les menus à la barre de menus
            menuBar.getMenus().addAll(fichierMenu, compteMenu, aideMenu);

            BorderPane bp = new BorderPane();
            bp.setTop(menuBar);


            //Cr�ation de la table
            TableView<Stagiaire> table = new TableView<Stagiaire>();
            table.setEditable(true);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            //Cr�ation des cinq colonnes
            TableColumn<Stagiaire, String> surnameCol =
                    new TableColumn<Stagiaire, String>("Nom");
            surnameCol.setMinWidth(250);
            //Sp�cifier comment remplir la donn�e pour chaque cellule de cette colonne
            //Ceci se fait en specifiant un "cell value factory" pour cette colonne.
            surnameCol.setCellValueFactory(
                    new PropertyValueFactory<Stagiaire, String>("surname"));

            TableColumn<Stagiaire, String> nameCol = new TableColumn<Stagiaire, String>("Prénom");
            nameCol.setMinWidth(250);
            //specifier un "cell factory" pour cette colonne.
            nameCol.setCellValueFactory(
                    new PropertyValueFactory<Stagiaire, String>("name"));

            TableColumn<Stagiaire, Integer> deptCol =
                    new TableColumn<Stagiaire, Integer>("Departement");
            deptCol.setMinWidth(200);
            //specifier un "cell factory" pour cette colonne.
            deptCol.setCellValueFactory(
                    new PropertyValueFactory<Stagiaire, Integer>("dept"));

            TableColumn<Stagiaire, String> promoCol = new TableColumn<Stagiaire, String>("Formation");
            promoCol.setMinWidth(250);
            //specifier un "cell factory" pour cette colonne.
            promoCol.setCellValueFactory(
                    new PropertyValueFactory<Stagiaire, String>("promo"));

            TableColumn<Stagiaire, Integer> yearCol = new TableColumn<Stagiaire, Integer>("Année");
            yearCol.setMinWidth(200);
            //specifier un "cell factory" pour cette colonne.
            yearCol.setCellValueFactory(
                    new PropertyValueFactory<Stagiaire, Integer>("year"));


            //On ajoute les trois colonnes à la table
            table.getColumns().addAll(surnameCol, nameCol, deptCol, promoCol, yearCol);

            //On remplit la table avec la liste observable


            //Creation champs de rajout
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

            //Creation boutons + Actions
            Button btnAjouter = new Button("Ajouter");
            btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }
            });

            Button btnSupprimer = new Button("Supprimer");
            btnSupprimer.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }
            });

            Button btnRetourAccueil = new Button("Retour Accueil");
            btnRetourAccueil.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                            new AccueilScene(primaryStage);
                    }
            });

            HBox hbox = new HBox();
            hbox.setSpacing(10);
            hbox.getChildren().addAll(surname, name, dept, promo, year, btnAjouter, btnSupprimer);

            HBox hbox1 = new HBox();
            hbox1.getChildren().addAll(btnRetourAccueil);

            //On place le label et la table dans une VBox
            VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(0, 0, 20, 0));
            vbox.getChildren().addAll(menuBar, label, table, hbox, hbox1);


            Scene admin = new Scene(vbox);
            admin.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            primaryStage.setScene(admin);
            primaryStage.setTitle("AdminScene");
            primaryStage.show();

        }

}
