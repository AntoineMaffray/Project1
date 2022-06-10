package fr.eql.ai111.groupe5.projet1.methodsback;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MethodsConnexion {

    private static final Logger logger  = LogManager.getLogger();

    public void removeAdminProfile (String loginToDelete){
        File oldLoginFile = new File("C://BillyBook/AdminInfo/"+loginToDelete+".txt");
        if (oldLoginFile.exists()){
            oldLoginFile.delete();
        } else {
            logger.warn("Le login entré n'existe pas.");
        }
    }

    public void editAdminProfile (String loginToEdit, String surnameToAdd, String nameToAdd,
                                  String loginToAdd, String passwordToAdd) throws IOException {
        File oldLoginFile = new File("C://BillyBook/AdminInfo/"+loginToEdit+".txt");

        File newLoginFile = new File("C://BillyBook/adminInfo/"+loginToAdd+".txt");
        newLoginFile.createNewFile();
        FileWriter fw = new FileWriter(newLoginFile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(passwordToAdd);
        bw.newLine();
        bw.write(surnameToAdd);
        bw.newLine();
        bw.write(nameToAdd);
        bw.newLine();
        bw.write("admin");

        bw.close();
        fw.close();

        if (oldLoginFile.exists()){
            oldLoginFile.delete();
        } else {
            logger.warn("Le login entré n'existe pas.");
        }
    }

    public void createAdminProfile (String surnameToAdd, String nameToAdd,
                                  String loginToAdd, String passwordToAdd) throws IOException {
        File newLoginFile = new File("C://BillyBook/adminInfo/"+loginToAdd+".txt");
        newLoginFile.createNewFile();
        FileWriter fw = new FileWriter(newLoginFile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(passwordToAdd);
        bw.newLine();
        bw.write(surnameToAdd);
        bw.newLine();
        bw.write(nameToAdd);
        bw.newLine();
        bw.write("admin");
        bw.close();
        fw.close();
    }

    public ObservableList<User> createUserList() throws IOException {
        ObservableList <User> listUser = FXCollections.observableArrayList();

        File fileAdmin = new File("C://theEQLBook/AdminInfo");
        File[] content = fileAdmin.listFiles();
        for (File f : content) {
            if (f.isFile()){
                File fileTemp = new File("C://theEQLBook/AdminInfo" + f.getName() + ".txt");
                FileReader fr = new FileReader(fileTemp);
                BufferedReader bf = new BufferedReader(fr);
                String login = f.getName();
                String password = bf.readLine();
                String surname = bf.readLine();
                String name = bf.readLine();
            }

        }
        return listUser;
    }

}