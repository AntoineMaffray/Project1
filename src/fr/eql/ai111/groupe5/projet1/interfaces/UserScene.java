package fr.eql.ai111.groupe5.projet1.interfaces;

import fr.eql.ai111.groupe5.projet1.methodsback.Stagiaire;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
import javafx.stage.Stage;

public class UserScene {

    public UserScene(Stage primaryStage) {
        Label label= new Label("ANNUAIRE STAGIAIRES");
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
                search(primaryStage);
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
                new PropertyValueFactory<Stagiaire,Integer>("year"));


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


        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(surname, name, dept, promo, year, btnAjouter);

        //On place le label et la table dans une VBox
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(75, 20, 20, 20));
        vbox.getChildren().addAll(label, menuBar, table, hbox);


        Scene user = new Scene(vbox);
        user.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(user);
        primaryStage.setTitle("UserScene");
        primaryStage.show();
    }

    private void search(Stage primaryStage){
        //Label for name
        Label label1 = new Label("Champs 1");
        TextField criteria1 = new TextField();

        Label label2 = new Label("Champs 2");
        TextField criteria2 = new TextField();

        Label label3 = new Label("Champs 3 ");
        TextField criteria3 = new TextField();

        Label label4 = new Label("Champs 4");
        TextField criteria4 = new TextField();

        Label label5 = new Label("Champs 5");
        TextField criteria5 = new TextField();

        //Label for location
        Label allElements = new Label("Elements");

        //Choice box for location
        ChoiceBox locationchoice1Box = new ChoiceBox();
        locationchoice1Box.getItems().addAll
                ("Nom", "Pr�nom", "D�partement", "Promotion", "Ann�e");

        ChoiceBox locationchoice2Box = new ChoiceBox();
        locationchoice2Box.getItems().addAll
                ("Nom", "Pr�nom", "D�partement", "Promotion", "Ann�e");

        ChoiceBox locationchoice3Box = new ChoiceBox();
        locationchoice3Box.getItems().addAll
                ("Nom", "Pr�nom", "D�partement", "Promotion", "Ann�e");

        ChoiceBox locationchoice4Box = new ChoiceBox();
        locationchoice4Box.getItems().addAll
                ("Nom", "Pr�nom", "D�partement", "Promotion", "Ann�e");

        ChoiceBox locationchoice5Box = new ChoiceBox();
        locationchoice5Box.getItems().addAll
                ("Nom", "Pr�nom", "D�partement", "Promotion", "Ann�e");

        //Button for register
        Button buttonSearch = new Button("Rechercher");

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(500, 500);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(label1, 0, 0);
        gridPane.add(label2, 0, 1);
        gridPane.add(label3, 0, 2);
        gridPane.add(label4, 0, 3);
        gridPane.add(label5, 0, 4);


        gridPane.add(locationchoice1Box, 1, 0);
        gridPane.add(locationchoice2Box, 1, 1);
        gridPane.add(locationchoice3Box, 1, 2);
        gridPane.add(locationchoice4Box, 1, 3);
        gridPane.add(locationchoice5Box, 1, 4);

        gridPane.add(buttonSearch, 2, 8);

        //Styling nodes
        allElements.setStyle("-fx-font: normal bold 15px 'serif' ");

        //Setting the back ground color
        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("UserScene");
        primaryStage.show();
    }
}
