package fr.eql.ai111.groupe5.projet1.methodsback;

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
    Methods methods = new Methods();

    public boolean createAccount(
            String surname,
            String name,
            String login,
            String password,
            String role) {
        File folder = new File(IDENTIFIANTS);
        // Si le dossier n'existe pas, je le crée.
        if (!folder.exists()) {
            folder.mkdir();
        }
        boolean isCreated = false;
        // Je d�clare le fichier utilisateur
        File userFile = new File(folder + "/" + login + ".txt");
        try {
            // Je tente de créer le fichier sur le disque, s'il n'existe pas déjà.
            isCreated = userFile.createNewFile();
            if (isCreated) {
                FileWriter fw = new FileWriter(userFile, false);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(surname);
                bw.newLine();
                bw.write(name);
                bw.newLine();
                bw.write(login);
                bw.newLine();
                bw.write(password);
                bw.newLine();
                bw.write(role);
                bw.close();
                fw.close();
            }
        } catch (IOException e) {
            logger.warn("Le fichier utilisateur n'a pas été créé.");
        }
        return isCreated;
    }

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
            logger.warn("Un problme s'est produit lors de la lecture du fichier utilisateur.");
        }
        // Si le password est correct, on retourne l'instance du reader.
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}