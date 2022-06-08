package fr.eql.ai111.groupe5.projet1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

import java.util.List;
import java.util.Optional;

public class AnnuaireViewUser extends Application{
    LesStagiaires lesStagiaires = new LesStagiaires
            ("C:\\Users\\sabri\\Workspace\\PROJET1GROUPE5\\src\\fr\\eql\\ai111\\groupe5\\projet1\\stagiaires.txt");
    ObservableList<Stagiaire> data = createListStagiaire();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        new Search(stage);

    }

        private void superAdmin(Stage stage) {
            Label label= new Label("ANNUAIRE STAGIAIRES");
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

            // Ajouter les menus à la barre de menus
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
            //Spécifier comment remplir la donnée pour chaque cellule de cette colonne
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
                    new PropertyValueFactory<Stagiaire,Integer>("year"));


            //On ajoute les trois colonnes à la table
            table.getColumns().addAll(surnameCol, nameCol, deptCol, promoCol, yearCol);

            //On remplit la table avec la liste observable
            table.setItems(data);

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
                    createContact(surname.getText(), name.getText(), dept.getText(), promo.getText(), year.getText(), data);
                    surname.clear();
                    name.clear();
                    dept.clear();
                    promo.clear();
                    year.clear();
                    confirmationInscription();
                }
            });

            this.label = new Label();
            Button btnSupprimer = new Button("Supprimer");
            btnSupprimer.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    boolean suppression = confirmationSuppression();
                    if(suppression){
                        data.remove(table.getSelectionModel().getSelectedItem());
                    }
                }
            });

            HBox hbox = new HBox();
            hbox.setSpacing(10);
            hbox.getChildren().addAll(surname, name, dept, promo, year, btnAjouter, btnSupprimer);

            //On place le label et la table dans une VBox
            VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(0, 0, 20, 0));
            vbox.getChildren().addAll(menuBar, label, table, hbox);


            Scene scene = new Scene(vbox);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setTitle("Annuaire");
            stage.setWidth(1250);
            stage.setHeight(800);
            stage.setScene(scene);
            stage.show();
        }


        private ObservableList<Stagiaire> createListStagiaire(){
            ObservableList<Stagiaire> list = FXCollections.observableArrayList();
            List<Stagiaire> listM = lesStagiaires.fabriqueList();
            for (Stagiaire stagiaire : listM) {
                list.add(stagiaire);
            }
            return list;
        }

        private void createContact(String surname, String name, String dept, String promo, String year,
                ObservableList<Stagiaire> data){
            Stagiaire stagiaireX = new Stagiaire(surname, name, dept, promo, year);
            data.add(stagiaireX);
        }


    // Méthode pour créer une fenêtre d'information
    private void confirmationInscription() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmation Inscription");

        // Texte sans en-tête
        alert.setHeaderText(null);
        alert.setContentText("Votre stagiaire a bien été enregistré!");
        alert.showAndWait();
    }

    // Méthode pour afficher une confirmation de suppresion via une fenêtre pop-up
    private Label label;

    private boolean confirmationSuppression() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Supprimer l'utilisateur");
        alert.setHeaderText("Etes-vous sûr de vouloir supprimer l'utilisateur?");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            this.label.setText("Aucun utilisateur n'a été sélectionné");
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

}

