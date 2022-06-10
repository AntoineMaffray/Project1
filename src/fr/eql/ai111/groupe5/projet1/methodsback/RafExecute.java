package fr.eql.ai111.groupe5.projet1.methodsback;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RafExecute {

    LogManager logManager;
    Logger logger;

    public void createStagiairesObjects() throws IOException {

        FileReader reader = new FileReader("C:/Users/Formation/Desktop/PROJET1/Stagiaires.txt");
        BufferedReader bfReader = new BufferedReader(reader);
        RandomAccessFile raf = new RandomAccessFile("C:/Users/Formation/Desktop/PROJET1/Raf.bin", "rw");
        List<Stagiaire> listStagiaires = new ArrayList<>();

        while (bfReader.ready()){
            bfReader.readLine();
            String chain = bfReader.readLine();
            Stagiaire stagiaire = null;
            StringTokenizer st = new StringTokenizer(chain, "\r\n");
            if(st.countTokens()==5){
                String surname = st.nextToken();
                String name = st.nextToken();
                String promo = st.nextToken();
                String dept = st.nextToken();
                String year = st.nextToken();
                stagiaire = new Stagiaire(surname, name, dept, promo, year);
                listStagiaires.add(stagiaire);
                System.out.println(stagiaire);

        }


    }


}
}