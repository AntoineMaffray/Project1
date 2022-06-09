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

//        Création du répertoire BillyBook + Dossier AdminInfo
        File billyBook = new File("C:/BillyBook/AdminInfo");
        if (!billyBook.exists()){
            billyBook.mkdirs();
        }

//        Création du fichier d'identifiant SuperAdmin
        File billyBookAdmin = new File("C:/BillyBook/AdminInfo/SuperAdmin.txt");
        if(!billyBookAdmin.exists()){
            billyBookAdmin.createNewFile();
            FileWriter fw = new FileWriter("C:/BillyBook/AdminInfo/SuperAdmin.txt", false);
            BufferedWriter bw  = new BufferedWriter(fw);
            bw.write("1234");
            bw.newLine();
            bw.write("superAdmin");
            bw.close();
            fw.close();
        }
//        Vérification de l'existence du RAF et création si non existant
        File file = new File("C:/BillyBook/Raf.bin");
        if(!file.exists()){
            try {
                arbre.arbreCreation(methods.createListStagiaires());
            } catch (IOException e) {
                logger.warn("Le fichier n'existe pas" + e);
            }
        }
    }
}