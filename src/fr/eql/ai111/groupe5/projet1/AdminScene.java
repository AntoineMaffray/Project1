package fr.eql.ai111.groupe5.projet1;

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
            Menu aideMenu = new Menu("Aide");

            // Creation des MenuItems du menu Fichier
            MenuItem rechercherItem = new MenuItem("Rechercher");
            rechercherItem.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
            rechercherItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

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

            // Creation des MenuItems du menu Aide
            MenuItem documentationItem = new MenuItem("Documentation");

            // Ajouter les menuItems aux Menus
            fichierMenu.getItems().addAll(rechercherItem, separator, quitterItem);
            aideMenu.getItems().addAll(documentationItem);

            // Ajouter les menus � la barre de menus
            menuBar.getMenus().addAll(fichierMenu, aideMenu);

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

            TableColumn<Stagiaire, String> nameCol = new TableColumn<Stagiaire, String>("Pr�nom");
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

            TableColumn<Stagiaire, Integer> yearCol = new TableColumn<Stagiaire, Integer>("Ann�e");
            yearCol.setMinWidth(200);
            //specifier un "cell factory" pour cette colonne.
            yearCol.setCellValueFactory(
                    new PropertyValueFactory<Stagiaire, Integer>("year"));


            //On ajoute les trois colonnes � la table
            table.getColumns().addAll(surnameCol, nameCol, deptCol, promoCol, yearCol);

            //On remplit la table avec la liste observable


            //Creation champs de rajout
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

            HBox hbox = new HBox();
            hbox.setSpacing(10);
            hbox.getChildren().addAll(surname, name, dept, promo, year, btnAjouter, btnSupprimer);

            //On place le label et la table dans une VBox
            VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(75, 20, 20, 20));
            vbox.getChildren().addAll(label, menuBar, table, hbox);


            Scene admin = new Scene(vbox);
            admin.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            primaryStage.setScene(admin);
            primaryStage.setTitle("AdminScene");
            primaryStage.show();

        }

}