package fr.eql.ai111.groupe5.projet1;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RafExecute {

    private static final Logger logger  = LogManager.getLogger();

    public List<String> createListStagiaires() throws IOException {

        FileReader reader = null;
        try {
            reader = new FileReader("C:/FolderProjet/Stagiaires.txt");
        } catch (FileNotFoundException e) {
            logger.warn("Le fichier à lire n'existe pas.");
        }
        BufferedReader bfReader = new BufferedReader(reader);
        List<String> listStringStagiaires = new ArrayList<>();
        String stagiaireX = "";
        String espace = " ";
        int index = 0;

        String surname = "";
        String name = "";
        String dept = "";
        String promo = "";
        String year = "";
        String temp = "";

        while (bfReader.ready()){
            index++;
            temp = "";
            temp = bfReader.readLine();
            switch(index){
                case 1 :
                    surname = temp;
                    int nbrEspace = 50 - surname.length();
                    for (int i = 0; i < nbrEspace; i++){
                        surname+= espace;
                    }
                    break;
                case 2 :
                    name = temp;
                    nbrEspace = 50 - name.length();
                    for (int i = 0; i < nbrEspace; i++){
                        name+= espace;
                    }
                    break;
                case 3 :
                    dept = temp;
                    nbrEspace = 2 - dept.length();
                    for (int i = 0; i < nbrEspace; i++){
                        dept+= espace;
                    }
                    break;
                case 4 :
                    promo = temp;
                    nbrEspace = 19 - promo.length();
                    for (int i = 0; i < nbrEspace; i++){
                        promo+= espace;
                    }
                    break;
                case 5:
                    year = temp;
                    break;
                case 6:
                    stagiaireX = surname + "*" + name + "*" + dept + "*" + promo + "*" + year ;
                    listStringStagiaires.add(stagiaireX);
                    index = 0;
//                    System.out.println(stagiaireX);
                    surname = "";
                    name = "";
                    dept = "";
                    promo = "";
                    year = "";
                    break;
            }
        }
        return listStringStagiaires;
    }

    public static void main(String[] args) throws IOException {
        RafExecute rafExecute = new RafExecute();
        Arbre arbre = new Arbre();
        File file = new File("C:/FolderProjet/Raf.bin");

        if(!file.exists()){
            arbre.arbreInsertion(rafExecute.createListStagiaires());
        }

        arbre.arbreSearch(JOptionPane.showInputDialog("Recherche par nom"));
    }
}