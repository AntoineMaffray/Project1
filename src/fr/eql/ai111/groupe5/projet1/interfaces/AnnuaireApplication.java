package fr.eql.ai111.groupe5.projet1.interfaces;

import fr.eql.ai111.groupe5.projet1.interfaces.AccueilScene;
import fr.eql.ai111.groupe5.projet1.methodsback.Execute;
import javafx.application.Application;
import javafx.stage.Stage;

public class AnnuaireApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        new Execute().executeAtStart();
        new AccueilScene(primaryStage);
    }
}
