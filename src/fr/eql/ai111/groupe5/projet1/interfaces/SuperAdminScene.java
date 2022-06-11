package fr.eql.ai111.groupe5.projet1.interfaces;

import fr.eql.ai111.groupe5.projet1.methodsback.Arbre;
import fr.eql.ai111.groupe5.projet1.methodsback.Methods;
import fr.eql.ai111.groupe5.projet1.methodsback.Stagiaire;
import fr.eql.ai111.groupe5.projet1.methodsback.User;
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
        Label label= new Label("the   EQL   BOOK");
        label.setFont(new Font("Impact", 50));
        label.setOpacity(0.9);
        label.setStyle("-fx-text-fill: black");
        label.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label, 0.0);
        AnchorPane.setRightAnchor(label, 0.0);
        label.setAlignment(Pos.CENTER);

        // Cr?ation de MenuBar
        MenuBar menuBar = new MenuBar();

        // Creation des menus
        Menu fichierMenu = new Menu("Fichier");
        Menu compteMenu = new Menu("Mon Compte");
        Menu compteAdminMenu = new Menu("Compte admin");
        Menu aideMenu = new Menu("Aide");

        // Creation des MenuItems du menu Fichier
        MenuItem exportItem = new MenuItem("Export");
        MenuItem exportPDFItem = new MenuItem("Exporter au format PDF");
        exportPDFItem.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
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
                    System.out.println("Le fichier a bien été supprimé");
                } else {
                    System.out.println("Le fichier a bien été créé");
                }
            }
        });

        // Création du MenuItem du menu Mon compte
        MenuItem modifierItem = new MenuItem("Modifier mes identifiants");

        // Création du MenuItem du menu Compte Admin
        MenuItem gestionAdminMenu = new MenuItem("Gestion de l'administrateur");

        gestionAdminMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new AdminTableviewOfLogins(primaryStage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Creation des MenuItems du menu Aide
        MenuItem documentationItem = new MenuItem("Documentation");

        // Ajouter les menuItems aux Menus
        fichierMenu.getItems().addAll(exportPDFItem, separator, quitterItem);
        compteMenu.getItems().add(modifierItem);
        compteAdminMenu.getItems().add(gestionAdminMenu);
        aideMenu.getItems().addAll(documentationItem);

        // Ajouter les menus à la barre de menus
        menuBar.getMenus().addAll(fichierMenu, compteMenu, compteAdminMenu, aideMenu);

        BorderPane bp = new BorderPane();
        bp.setTop(menuBar);

        exportPDFItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(primaryStage);
                VBox dialogVbox = new VBox();
                HBox hBox = new HBox();
                dialogVbox.getChildren().add(new Text("Veuillez entrer un nom de fichier"));
                TextField tf = new TextField();
                hBox.getChildren().add(tf);
                Button btn = new Button("Valider");
                hBox.getChildren().add(btn);
                dialogVbox.getChildren().add(hBox);
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String namePDF = null;
                        namePDF = tf.getText();
                        try {
                            methods.ExportPDF(data, namePDF);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();
            }
        });


        //Cr?ation de la table
        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Cr?ation des cinq colonnes
        TableColumn<Stagiaire, String> surnameCol =
                new TableColumn<Stagiaire, String>("Nom");
        surnameCol.setMinWidth(250);
        //Sp?cifier comment remplir la donn?e pour chaque cellule de cette colonne
        //Ceci se fait en specifiant un "cell value factory" pour cette colonne.
        surnameCol.setCellValueFactory(
                new PropertyValueFactory<Stagiaire, String>("surname"));

        TableColumn<Stagiaire, String> nameCol = new TableColumn<Stagiaire, String>("Pr?nom");
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

        TableColumn<Stagiaire, Integer> yearCol = new TableColumn<Stagiaire, Integer>("Ann?e");
        yearCol.setMinWidth(200);
        //specifier un "cell factory" pour cette colonne.
        yearCol.setCellValueFactory(
                new PropertyValueFactory<Stagiaire,Integer>("year"));


        //On ajoute les trois colonnes ? la table
        table.getColumns().addAll(surnameCol, nameCol, deptCol, promoCol, yearCol);

        //On remplit la table avec la liste observable
        table.setItems(data);

        ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Modifier");
        MenuItem item2 = new MenuItem("Supprimer");
        contextMenu.getItems().addAll(item1, item2);

        // Instanciation du menu clic-droit
        table.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(table, event.getScreenX(), event.getScreenY());
            }
        });
        // Item 1, "Modifier", du menu clic-droit
        item1.setOnAction(new EventHandler<ActionEvent>() {

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
        // Item 2, "supprimer", du menu clic-droit
        item2.setOnAction(new EventHandler<ActionEvent>() {
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
                table.setItems(data);
            }
        });

        modifierItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });


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

        Scene supAdmin = new Scene(vbox);
        supAdmin.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(supAdmin);
        primaryStage.setTitle("SuperAdminScene");
        primaryStage.show();

    }

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
    private Label label;

    private boolean confirmationSuppression() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer l'utilisateur");
        alert.setHeaderText("Etes-vous s?r de vouloir supprimer l'utilisateur?");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            this.label.setText("Aucun utilisateur n'a ?t? s?lectionn?");
        } else if (option.get() == ButtonType.OK) {
            this.label.setText("Utilisateur supprim?!");
            return true;
        } else if (option.get() == ButtonType.CANCEL) {
            this.label.setText("Annul?");
            alert.close();
        } else {
            this.label.setText("-");
        }

        return false;
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
