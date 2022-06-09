package fr.eql.ai111.groupe5.projet1.interfaces;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import fr.eql.ai111.groupe5.projet1.methodsback.Arbre;
import fr.eql.ai111.groupe5.projet1.methodsback.Methods;
import fr.eql.ai111.groupe5.projet1.methodsback.Stagiaire;
import fr.eql.ai111.groupe5.projet1.methodsback.TriSimple;
import fr.eql.ai111.groupe5.projet1.methodsback.RAFException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Search {
    Arbre arbre = new Arbre();
    Methods methods = new Methods();
    TriSimple triSimple = new TriSimple();
    ObservableList<Stagiaire> data;

    {
        try {
            data = arbre.arbreParcours();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    int count = 1;
    String criterion = "";

    public Search(Stage primaryStage) {

        Label label= new Label("RECHERCHE PAR CRITERES");
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
        MenuItem creerItem = new MenuItem("Cr?er");
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

        // Ajouter les menus ? la barre de menus
        menuBar.getMenus().addAll(fichierMenu, identifiantMenu, aideMenu);

        BorderPane bp = new BorderPane();
        bp.setTop(menuBar);

        //Création de la table
        TableView<Stagiaire> table = new TableView<Stagiaire>();
        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Création des cinq colonnes
        TableColumn<Stagiaire, String> surnameCol =
                new TableColumn<Stagiaire, String>("Nom");
        surnameCol.setMinWidth(250);
        //Spécifier comment remplir la donn?e pour chaque cellule de cette colonne
        //Ceci se fait en specifiant un "cell value factory" pour cette colonne.
        surnameCol.setCellValueFactory(
                new PropertyValueFactory<Stagiaire, String>("surname"));

        TableColumn<Stagiaire, String> nameCol = new TableColumn<Stagiaire, String>("Prénom");
        nameCol.setMinWidth(250);
        //specifier un "cell factory" pour cette colonne.
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Stagiaire, String>("name"));

        TableColumn<Stagiaire, Integer> deptCol =
                new TableColumn<Stagiaire, Integer>("Département");
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
                new PropertyValueFactory<Stagiaire,Integer>("year"));


        //On ajoute les trois colonnes ? la table
        table.getColumns().addAll(surnameCol, nameCol, deptCol, promoCol, yearCol);

        //On remplit la table avec la liste observable
        table.setItems(data);

        //Création des champs de recherches, de leur apparition/disparition
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        ObservableList<String> values = FXCollections.observableArrayList("Nom", "Prénom", "Département", "Formation", "Année");
        TextField criterionField1 = new TextField();
        criterionField1.setPrefWidth(120);
        ChoiceBox<String> combo1 = new ChoiceBox<>();
        combo1.setPrefWidth(80);
        TextField criterionField2 = new TextField();
        criterionField2.setPrefWidth(120);
        ChoiceBox<String> combo2 = new ChoiceBox<>();
        combo2.setPrefWidth(80);
        TextField criterionField3 = new TextField();
        criterionField3.setPrefWidth(120);
        ChoiceBox<String> combo3 = new ChoiceBox<>();
        combo3.setPrefWidth(80);
        TextField criterionField4 = new TextField();
        criterionField4.setPrefWidth(120);
        ChoiceBox<String> combo4 = new ChoiceBox<>();
        combo4.setPrefWidth(80);
        TextField criterionField5 = new TextField();
        criterionField5.setPrefWidth(120);
        ChoiceBox<String> combo5 = new ChoiceBox<>();
        combo5.setPrefWidth(80);

        Button buttonPlus = new Button("+");
        Button buttonLess = new Button("-");

        hbox.getChildren().addAll(combo1, criterionField1, buttonPlus);

        ConnectedComboBox<String> connectedComboBox = new ConnectedComboBox();

        buttonPlus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                count++;
                switch (count){
                    case 2:
                        hbox.getChildren().remove(buttonPlus);
                        hbox.getChildren().remove(buttonLess);
                        hbox.getChildren().addAll(combo2, criterionField2, buttonPlus, buttonLess);
                        break;
                    case 3:
                        hbox.getChildren().remove(buttonPlus);
                        hbox.getChildren().remove(buttonLess);
                        hbox.getChildren().addAll(combo3, criterionField3, buttonPlus, buttonLess);
                        break;
                    case 4:
                        hbox.getChildren().remove(buttonPlus);
                        hbox.getChildren().remove(buttonLess);
                        hbox.getChildren().addAll(combo4, criterionField4, buttonPlus, buttonLess);
                        break;
                    case 5:
                        hbox.getChildren().remove(buttonPlus);
                        hbox.getChildren().remove(buttonLess);
                        hbox.getChildren().addAll(combo5, criterionField5, buttonLess);
                        break;
                }
            }
        });

        buttonLess.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                count--;
                switch (count){
                    case 1:
                        hbox.getChildren().clear();
                        hbox.getChildren().addAll(combo1, criterionField1, buttonPlus, buttonLess);
                        combo2.setValue(null);
                        criterionField2.clear();
                        break;
                    case 2:
                        hbox.getChildren().clear();
                        hbox.getChildren().addAll(combo1, criterionField1, combo2, criterionField2,
                                buttonPlus, buttonLess);
                        combo3.setValue(null);
                        criterionField3.clear();
                        break;
                    case 3:
                        hbox.getChildren().clear();
                        hbox.getChildren().addAll(combo1, criterionField1, combo2, criterionField2,
                                combo3, criterionField3, buttonPlus, buttonLess);
                        combo4.setValue(null);
                        criterionField4.clear();
                        break;
                    case 4:
                        hbox.getChildren().clear();
                        hbox.getChildren().addAll(combo1, criterionField1, combo2, criterionField2,
                                combo3, criterionField3, combo4, criterionField4, buttonPlus, buttonLess);
                        combo5.setValue(null);
                        criterionField5.clear();
                        break;
                }
            }
        });

        connectedComboBox.ConnectedComboBox(values);
        connectedComboBox.addComboBox(combo1);
        connectedComboBox.addComboBox(combo2);
        connectedComboBox.addComboBox(combo3);
        connectedComboBox.addComboBox(combo4);
        connectedComboBox.addComboBox(combo5);

        //Creation boutons + Actions
        Button btnRechercher = new Button("Rechercher");

        //On place le label et la table dans une VBox
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(0, 0, 20, 0));
        vbox.getChildren().addAll(menuBar, label, hbox, btnRechercher, table);

        btnRechercher.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(combo2.getValue()+"/" + combo3.getValue()+"/" +combo4.getValue()+"/" +combo5.getValue());
                int criterion1 = conversionCriterion(combo1.getValue().toString());
                String search1 = criterionField1.getText();
                int criterion2;
                if (combo2.getValue() == null){
                    criterion2 = 0;
                } else{
                    criterion2 = conversionCriterion(combo2.getValue().toString());
                }
                String search2 = criterionField2.getText();
                int criterion3;
                if(combo3.getValue() == null){
                    criterion3 = 0;
                } else {
                    criterion3 = conversionCriterion(combo3.getValue().toString());
                }
                String search3 = criterionField3.getText();

                int criterion4;
                if(combo4.getValue() == null){
                    criterion4 = 0;
                } else {
                    criterion4 = conversionCriterion(combo4.getValue().toString());
                }
                String search4 = criterionField4.getText();
                int criterion5;
                if(combo5.getValue() == null){
                    criterion5 = 0;
                } else {
                    criterion5 = conversionCriterion(combo5.getValue().toString());
                }
                String search5 = criterionField5.getText();

                System.out.println(criterionField2.getText()+" "+criterionField3.getText()+" "+criterionField4.getText()+" "+criterionField5.getText());

                try {
                    data = triSimple.searchByCriterion(criterion1, search1, criterion2, search2, criterion3, search3, criterion4,
                            search4, criterion5, search5);
                } catch (RAFException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                table.setItems(data);
            }});

            Scene scene = new Scene(vbox);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.setTitle("Annuaire");
            primaryStage.setWidth(1250);
            primaryStage.setHeight(800);
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        private void createContact(String surname, String name, String dept, String promo, String year,
                ObservableList<Stagiaire> data){
            Stagiaire stagiaireX = new Stagiaire(surname, name, dept, promo, year);
            data.add(stagiaireX);
        }


        // Méthode pour créer une fenêtre d'information
        private void confirmationInscription() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Inscription");

            // Texte sans en-tête
            alert.setHeaderText(null);
            alert.setContentText("Votre stagiaire a bien été enregistré!");
            alert.showAndWait();
        }

        // Méthode pour afficher une confirmation de suppresion via une fen?tre pop-up
        private Label label;

        private boolean confirmationSuppression() {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer l'utilisateur");
            alert.setHeaderText("Etes-vous sûr de vouloir supprimer l'utilisateur?");

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null) {
                this.label.setText("Aucun utilisateur n'a été sélectionné.");
            } else if (option.get() == ButtonType.OK) {
                this.label.setText("Utilisateur supprimé!");
                return true;
            } else if (option.get() == ButtonType.CANCEL) {
                this.label.setText("Annulé");
                alert.close();
            } else {
                this.label.setText("-");
            }

            return false;
        }

        private int conversionCriterion (String criterion){
            int criterionConvert = 0;
            switch (criterion){
                case "Nom":
                    criterionConvert = 1;
                    break;
                case "Prénom":
                    criterionConvert = 2;
                    break;
                case "Département":
                    criterionConvert = 3;
                    break;
                case "Formation":
                    criterionConvert = 4;
                    break;
                case "Année":
                    criterionConvert = 5;
                    break;
                default:
                    criterionConvert = 0;
                    break;
            }
            return criterionConvert;
        }
}

