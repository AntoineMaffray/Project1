package fr.eql.ai111.groupe5.projet1.interfaces;
import fr.eql.ai111.groupe5.projet1.methodsback.Arbre;
import fr.eql.ai111.groupe5.projet1.methodsback.Stagiaire;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SuperAdminStagiairesDesactives {

    Arbre arbre = new Arbre();
    ObservableList <Stagiaire> listDeactivated = arbre.arbreParcoursInv();
    TableView<Stagiaire> tableDeactivated = new TableView<Stagiaire>();


    private SuperAdminStagiairesDesactives (Stage primaryStage) throws IOException {

        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Réactiver");
        contextMenu.getItems().add(item1);


        tableDeactivated.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(tableDeactivated, event.getScreenX(), event.getScreenY());
            }
        });
        // Item 1, "Réactivers", du menu clic-droit
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                tableDeactivated.getSelectionModel();
                String newAdd = "";
                try {
                    arbre.arbreDReactivation(newAdd, "V");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }
}
