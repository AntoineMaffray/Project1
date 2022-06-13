package fr.eql.ai111.groupe5.projet1.methodsback;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Execute {

    private static final Logger logger  = LogManager.getLogger();
    public void executeAtStart() throws IOException {
        Arbre arbre = new Arbre();
        Methods methods = new Methods();

//        Création du répertoire theEqlBook + Dossier AdminInfo
        File eqlBook = new File("C:/theEqlbook/AdminInfo/SuperAdmin");
        if (!eqlBook.exists()){
            eqlBook.mkdirs();
        }

//        Création du fichier d'identifiant SuperAdmin
        File eqlBookAdmin = new File("C:/theEQLBook/AdminInfo/SuperAdmin/SUPERADMIN.txt");
        if(!eqlBookAdmin.exists()){
            eqlBookAdmin.createNewFile();
            FileWriter fw = new FileWriter("C:/theEQLBook/AdminInfo/SuperAdmin/SUPERADMIN.txt", false);
            BufferedWriter bw  = new BufferedWriter(fw);
            bw.write(methods.hashage("12345EQL"));
            bw.newLine();
            bw.write("SUPERADMIN");
            bw.close();
            fw.close();
        }
//        Vérification de l'existence du RAF et création si non existant
        File file = new File("C:/theEQLBook/Raf.bin");
        if(!file.exists()){
            try {
                arbre.arbreCreation(methods.createListStagiaires());
            } catch (IOException e) {
                logger.warn("Le fichier n'existe pas" + e);
            }
        }
    }
}