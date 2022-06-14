package fr.eql.ai111.groupe5.projet1.interfaces;

import fr.eql.ai111.groupe5.projet1.methodsback.Execute;
import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class AnnuaireApplication extends Application {

    // La méthode start permet l'ouverture de l'application //
    @Override
    public void start(Stage primaryStage) throws Exception {
        new Execute().executeAtStart();
        new AccueilScene(primaryStage);
    }
}
