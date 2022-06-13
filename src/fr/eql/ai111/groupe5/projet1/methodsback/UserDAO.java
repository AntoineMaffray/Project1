package fr.eql.ai111.groupe5.projet1.methodsback;

import fr.eql.ai111.groupe5.projet1.interfaces.InscriptionScene;
import javafx.scene.control.Alert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserDAO {

    Methods methods = new Methods();
    private static final Logger logger = LogManager.getLogger();
    private static final String IDENTIFIANTS = "Identifiants";

    // Méthode permettant de créer le dossier identifiants incluant les fichiers utilisateurs //
    public boolean createAccount(
            String surname,
            String name,
            String login,
            String password) {
        boolean isCreated = false;
        // Je déclare le fichier utilisateur
        File userFile = new File("C://theEqlbook/AdminInfo/" +login + ".txt");
        try {
            if(userFile.length() < 1 ) {
                if (userFile.exists()) {
                    FileWriter fw = new FileWriter(userFile, false);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(methods.hashage(password));
                    bw.newLine();
                    bw.write(surname);
                    bw.newLine();
                    bw.write(name);
                    bw.close();
                    fw.close();
                    isCreated = true;
                }
            } else if (userFile.length() >= 1) {
                idendifiantsDejaCrees();
            }

        } catch (IOException e) {
            logger.warn("Le fichier administrateur n'a pas été créé.");
        }
        return isCreated;
    }

    // Méthode permettant de récupérer les informations utilisateurs afin de pourvoir se connecter//
    public User connect(String login, String password) {
        File userFile = new File(IDENTIFIANTS + "/" + login + ".txt");
        User user = null;
        if (!userFile.exists()) {
            return null;
        }
        try {
            FileReader fr = new FileReader(userFile);
            BufferedReader br = new BufferedReader(fr);
            String surnameInFile = br.readLine();
            String nameInFile = br.readLine();
            String loginInFile = br.readLine();
            String passwordInFile = br.readLine();
            passwordInFile = methods.hashage(passwordInFile);
            String roleInFile = br.readLine();
            user = new User(surnameInFile, nameInFile, loginInFile, passwordInFile, roleInFile);
            br.close();
            fr.close();
        } catch (IOException e) {
            logger.warn("Un problème s'est produit lors de la lecture du fichier utilisateur.");
        }
        // Si le password est correct, on retourne l'instance du reader.
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    private void idendifiantsDejaCrees() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Message d'erreur");
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Attention ces identifiants existent d?j?!");
        alert.showAndWait();
    }
}