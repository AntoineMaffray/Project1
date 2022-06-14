package fr.eql.ai111.groupe5.projet1.interfaces;

import fr.eql.ai111.groupe5.projet1.methodsback.Arbre;
import fr.eql.ai111.groupe5.projet1.methodsback.Methods;
import fr.eql.ai111.groupe5.projet1.methodsback.PDFReader;
import fr.eql.ai111.groupe5.projet1.methodsback.RAFException;
import fr.eql.ai111.groupe5.projet1.methodsback.Stagiaire;
import fr.eql.ai111.groupe5.projet1.methodsback.TriSimple;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SearchMenuBarAdmin {
    Arbre arbre = new Arbre();
    TriSimple triSimple = new TriSimple();
    ObservableList<Stagiaire> data;
    Methods methods = new Methods();
    {
        try {
            data = arbre.arbreParcours();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    int count = 1;

    public SearchMenuBarAdmin(Stage primaryStage) {


        //////////////////// LABEL - TITRE DE LA SCENE SEARCHSCENE //////////////////////////////
        /*
        Cr?ation du titre du fichier en label avec son style.
        Pour l'affichage, on utilise un AnchorPane.
         */
        Label label= new Label("RECHERCHE PAR CRITERES");
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
        Documentation => consigne pour l'utilisation de l'application
        Quitter => quitter l'application.
        Apr?s avoir cr?? le menuBar et les menuItems, on ajoute les menuItems au menu,
        et le menu au menuBar.
        Pour l'affichage du menu, on l'inclut dans une BorderPane.
         */
        //MenuBar et Menus//
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
        Menu compteMenu = new Menu("Compte administrateur");
        Menu aideMenu = new Menu("Aide");

        //MenuItems du fichier//
        MenuItem rechercherItem = new MenuItem("Recherche par critères");
        rechercherItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //new SearchMenuBarAdmin(primaryStage);
            }
        });
        MenuItem retourPagePrincipaleItem = new MenuItem("Retour page principale");
        retourPagePrincipaleItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new AdminScene(primaryStage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        MenuItem deconnexionItem = new MenuItem("Déconnexion");
        deconnexionItem.setOnAction(new EventHandler<ActionEvent>() {
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
                Label label = new Label("Nom du fichier à exporter :");
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
                dialogScene.getStylesheets().add(getClass().getResource("styleAdmin.css").toExternalForm());
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
            }
        });

        // MenuItem du menu Mon compte //
        MenuItem modifierItem = new MenuItem("Modifier mes identifiants");

        modifierItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String oldLogin = "";
                try {
                    File file = new File("C://theEqlbook/AdminInfo/Persistance/Login.txt");
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    oldLogin = br.readLine() + ".txt";
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                File fileLogin = new File("C://theEqlbook/AdminInfo/"+ oldLogin);
                FileReader frl = null;
                try {
                    frl = new FileReader(fileLogin);
                    BufferedReader brl = new BufferedReader(frl);
                    String oldPassword = brl.readLine();
                    String oldSurname = brl.readLine();
                    String oldName = brl.readLine();
                    brl.close();
                    frl.close();
                    modifFormAdmin(new Stage(), oldSurname, oldName, oldLogin, oldPassword);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // MenuItem du menu Aide //
        MenuItem documentationItem = new MenuItem("Documentation");
        documentationItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PDFReader pdfReader = new PDFReader();
                try {
                    pdfReader.openPdfAdmin();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Ajout des menusItems au menu, et du menu au menuBar, affichage en BorderPane//
        fichierMenu.getItems().addAll(rechercherItem, retourPagePrincipaleItem, deconnexionItem, exportPDFItem, separator, quitterItem);
        compteMenu.getItems().add(modifierItem);
        aideMenu.getItems().addAll(documentationItem);
        menuBar.getMenus().addAll(fichierMenu, compteMenu, aideMenu);
        BorderPane bp = new BorderPane();
        bp.setTop(menuBar);
        ////////////////////////////////////////////////////////////////////////////////



        ///////////////////////////// TABLE STAGIAIRE /////////////////////////////////
        /*
        Pour faire appara?tre la liste des stagiaires, on inclut les donn?es dans une table.
        Pour se faire, on cr?? 5 colonnes avec les informations requises
        (nom, pr?nom, d?partement,formation et ann?e), en divisant par cellule,
        et on r?cup?re les donn?es du fichier via la m?thode observable liste.
         */
        TableView<Stagiaire> table = new TableView<Stagiaire>();
        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Cr?ation des cinq colonnes de la table //
        TableColumn<Stagiaire, String> surnameCol = new TableColumn<Stagiaire, String>("Nom");
        surnameCol.setMinWidth(250);
        //Sp?cifier comment remplir la donn?e pour chaque cellule de cette colonne avec un "cell valu factory//
        surnameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("surname"));

        TableColumn<Stagiaire, String> nameCol = new TableColumn<Stagiaire, String>("Prénom");
        nameCol.setMinWidth(250);
        nameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("name"));

        TableColumn<Stagiaire, Integer> deptCol = new TableColumn<Stagiaire, Integer>("Département");
        deptCol.setMinWidth(200);
        deptCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, Integer>("dept"));

        TableColumn<Stagiaire, String> promoCol = new TableColumn<Stagiaire, String>("Formation");
        promoCol.setMinWidth(250);
        promoCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promo"));

        TableColumn<Stagiaire, Integer> yearCol = new TableColumn<Stagiaire, Integer>("Année");
        yearCol.setMinWidth(200);
        yearCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,Integer>("year"));

        //On ajoute les cinq colonnes ? la table//
        table.getColumns().addAll(surnameCol, nameCol, deptCol, promoCol, yearCol);

        //On remplit la table avec la liste observable//
        table.setItems(data);
        /////////////////////////////////////////////////////////////////////////////////


        ///////////////////////////// RECHERCHE PAR CRITERES /////////////////////////////////
        /*
        Pour la recherche par crit?res, des champs de textes avec des listes ont ?t? cr??s,
        avec des boutons plus et moins, et le bouton rechercher qui permet d'effectuer le
        tri simple.
        Ces ?l?ments sont plac?s dans une Hbox
         */
        //Cr?ation des champs de recherches, de leur apparition/disparition//
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(0, 0, 0, 20));
        ObservableList<String> values = FXCollections.observableArrayList
                ("Nom", "Prénom", "Département", "Formation", "Année");
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
                        hbox.getChildren().addAll(combo1, criterionField1, buttonPlus);
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

        Button btnRechercher = new Button("Rechercher");
        HBox hboxBtn = new HBox();
        hboxBtn.setPadding(new Insets(0, 0, 0, 20));
        hboxBtn.getChildren().add(btnRechercher);
        /////////////////////////////////////////////////////////////////////////////////


        ///////////////////////////// AFFICHAGE DES ELEMENTS //////////////////////////////////
        /*
        On affiche tous les ?l?ments dans une VBox, que l'on int?gre dans une sc?ne et ensuite un stage.
         */
        VBox search = new VBox();
        search.setSpacing(5);
        search.setPadding(new Insets(0, 0, 20, 0));
        search.getChildren().addAll(menuBar, label, hbox, hboxBtn, table);
        Scene searchScene = new Scene(search, 1200,700);
        search.getStylesheets().add(getClass().getResource("styleAdmin.css").toExternalForm());
        primaryStage.setScene(searchScene);
        primaryStage.setTitle("The EQL Book - Mode Administrateur");
        primaryStage.show();
        ////////////////////////////////////////////////////////////////////////////////////////


        ///////////////////////////// METHODE DE TRI SIMPLE //////////////////////////////////
        /*
        L'évènement est placée à la fin afin qu'il puisse prendre en compte tous les ?l?ments pr?c?dents.
         */
        EventHandler cs = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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

                try {
                    data = triSimple.searchByCriterion(criterion1, search1, criterion2, search2, criterion3, search3, criterion4,
                            search4, criterion5, search5);
                } catch (StringIndexOutOfBoundsException | RAFException | IOException e){
                    try {
                        data = arbre.arbreParcours();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                table.setItems(data);
            }};
        btnRechercher.setOnAction(cs);
        criterionField1.setOnAction(cs);
        criterionField2.setOnAction(cs);
        criterionField3.setOnAction(cs);
        criterionField4.setOnAction(cs);
        criterionField5.setOnAction(cs);

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
    public void modifFormAdmin (Stage stage, String oldSurname, String oldName, String oldLogin, String oldPassword)
            throws IOException {
        try {
            Label titleLabel = new Label("Modification du compte");
            titleLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 20));
            Label surnameLabel = new Label("Nom");
            TextField newSurname = new TextField(oldSurname);
            Label nameLabel = new Label("Prénom");
            TextField newName = new TextField(oldName);
            Label loginLabel = new Label("Identifiant");
            TextField newLogin = new TextField(oldLogin.substring(0, oldLogin.length() - 4));
            Label newPwLabel = new Label("Nouveau mot de passe");
            TextField newPw = new TextField();
            Label verifPwLabel = new Label("Vérification du mot de passe");
            TextField verifPw = new TextField();

            Button btnValidate = new Button("Valider");
            Button btnCancel = new Button("Annuler");
            HBox hboxBtn = new HBox();
            hboxBtn.getChildren().addAll(btnValidate, btnCancel);
            hboxBtn.setSpacing(50);
            hboxBtn.setAlignment(Pos.CENTER_RIGHT);

            GridPane gridModif2 = new GridPane();
            gridModif2.setAlignment(Pos.TOP_CENTER);
            gridModif2.setVgap(15);
            gridModif2.setHgap(20);
            gridModif2.setPadding(new Insets(10, 10, 10, 10));

            gridModif2.add(titleLabel, 0, 0, 2, 1);
            gridModif2.add(surnameLabel, 0, 1);
            gridModif2.add(newSurname, 1, 1);
            gridModif2.add(nameLabel, 0, 2);
            gridModif2.add(newName, 1, 2);
            gridModif2.add(loginLabel, 0, 3);
            gridModif2.add(newLogin, 1, 3);
            gridModif2.add(newPwLabel, 0, 4);
            gridModif2.add(newPw, 1, 4);
            gridModif2.add(verifPwLabel, 0, 5);
            gridModif2.add(verifPw, 1, 5);
            gridModif2.add(hboxBtn, 1, 6);

            Scene subModifScene = new Scene(gridModif2);
            subModifScene.getStylesheets().add(getClass().getResource("styleAdmin.css").toExternalForm());
            stage.setScene(subModifScene);
            stage.setTitle("Modification des identifiants");
            stage.show();


            btnCancel.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                }
            });
            btnValidate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (oldSurname != newSurname.getText() && oldName != newName.getText() && oldLogin != newLogin.getText()
                            && oldPassword != newPw.getText()) {

                        if (newPw.getText().equals(verifPw.getText())) {
                            File loginFileX = new File("C://theEQLBook/AdminInfo/" + newLogin.getText() + ".txt");
                            try {
                                loginFileX.createNewFile();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                FileWriter frX = new FileWriter(loginFileX);
                                BufferedWriter bwX = new BufferedWriter(frX);
                                bwX.write(methods.hashage(newPw.getText()));
                                bwX.newLine();
                                bwX.write(newSurname.getText());
                                bwX.newLine();
                                bwX.write(newName.getText());
                                bwX.newLine();
                                bwX.write("Admin");
                                bwX.close();
                                frX.close();
                                File toRemove = new File("C://theEQLBook/AdminInfo/" + oldLogin + ".txt");
                                toRemove.delete();
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            System.out.println("Passwords non identiques.");
                        }
                    } else if (oldSurname == newSurname.getText() && oldName == newName.getText() && oldLogin == newLogin.getText()
                            && oldPassword == newPw.getText()) {
                        System.out.println("Informations identiques, compte non modifi?.");
                    }
                    File filetoDelete = new File("C://theEQLBook/AdminInfo/" + oldLogin + ".txt");
                    filetoDelete.delete();
                    stage.close();
                }
            });

        } catch (StringIndexOutOfBoundsException e) {
            // popup ? faire identifiant pas encore activ?
        }
    }
}
