package fr.eql.ai111.groupe5.projet1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Methods {

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
                    surname = temp.toUpperCase();
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
    public Integer compareCharToChar(String newAdd, String parent,
                                     char newChar, char parentChar, int charIndex){
            charIndex = 0;
            int comparison = 0;
            do {
                newChar = newAdd.charAt(charIndex);
                parentChar = parent.charAt(charIndex);
                if (newChar == 'é' && parentChar == 'é' || newChar == 'è' && parentChar == 'è'
                        || newChar == 'ï' && parentChar == 'ï') {
                    charIndex++;
                    newChar = newAdd.charAt(charIndex);
                    parentChar = parent.charAt(charIndex);
                }
                charIndex ++;
            } while (newChar == parentChar);

            if (newChar>parentChar){
                comparison = 1;
            } else if (newChar<parentChar){
                comparison = -1;
            }
            return comparison;
    }
    public Integer searchCharToChar(String newAdd, String parent){
        int charIndex = 0;
        int comparison = 0;
        char newChar;
        char parentChar;
        do {
            newChar = newAdd.charAt(charIndex);
            parentChar = parent.charAt(charIndex);
            if (newChar == 'é' && parentChar == 'é' || newChar == 'è' && parentChar == 'è'
                    || newChar == 'ï' && parentChar == 'ï') {
                charIndex++;
                newChar = newAdd.charAt(charIndex);
                parentChar = parent.charAt(charIndex);
            }
            charIndex ++;
        } while (newChar == parentChar & charIndex < newAdd.length());

        if (newChar>parentChar){
            comparison = 1;
        } else if (newChar<parentChar){
            comparison = -1;
        } else if (charIndex == newAdd.length()-1){
            comparison = 0;
        }
        return comparison;
    }
    public String removeDollarFromRef(RandomAccessFile rafDataBase,
                                       String child) throws IOException {
        for (int i = 0; i < 7; i++) {
            char removeDollars = rafDataBase.readChar();
            if (removeDollars != '$') {
                child += removeDollars;
            }
        }
        return child;
    }
    public void writeLeftChildInRaf(RandomAccessFile rafDataBase, long parentPlace,
                                    long REF6, long REF8, long ELT, String newAdd) throws IOException {
        rafDataBase.seek(parentPlace + REF6);
        rafDataBase.writeChars(String.valueOf(rafDataBase.length()));
        rafDataBase.seek(rafDataBase.length());
        rafDataBase.writeChars(newAdd + "*" + "$$$$$$$$" + "*" +
                "$$$$$$$$" + "*" + "$$$$$$$$" + "*" + "V" + "*");
        rafDataBase.seek(rafDataBase.length() - ELT + REF8);
        rafDataBase.writeChars(String.valueOf(parentPlace));
    }
    public void writeRightChildInRaf(RandomAccessFile rafDataBase, long parentPlace,
                                     long REF7, long REF8, long ELT, String newAdd) throws IOException {
        rafDataBase.seek(parentPlace + REF7);
        rafDataBase.writeChars(String.valueOf(rafDataBase.length()));
        rafDataBase.seek(rafDataBase.length());
        rafDataBase.writeChars(newAdd + "*" + "$$$$$$$$" + "*" +
                "$$$$$$$$" + "*" + "$$$$$$$$" + "*" + "V" + "*");
        rafDataBase.seek(rafDataBase.length() - ELT + REF8);
        rafDataBase.writeChars(String.valueOf(parentPlace));
    }
    public Stagiaire createObjectStagiaire (String parent){
        String name = "";
        String surname = "";
        String dept = "";
        String promo = "";
        String year = "";
        String [] parentParts = parent.split("\\*");
        for (int i = 0; i < parentParts.length; i++){
            parentParts[i].trim();
            switch (i){
                case 0 :
                    surname = parentParts[i];
                    break;
                case 1 :
                    name = parentParts[i];
                    break;
                case 2 :
                    dept = parentParts[i];
                    break;
                case 3 :
                    promo = parentParts[i];
                    break;
                case 4 :
                    year = parentParts[i];
                    break;
            }
        }
        Stagiaire stagiaireX = new Stagiaire(surname, name, dept, promo, year);
        return stagiaireX;
    }
    public List<String> createListOneStagiaire(String surname, String name, String dept, String promo, String year){

        List <String> listOneStagiaire = new ArrayList<>();
        String temp = "";
        String espace = " ";

        int nbrEspace = 50 - surname.length();
        for (int i = 0; i < nbrEspace; i++){
            surname+= espace;
        }
        nbrEspace = 50 - name.length();
        for (int i = 0; i < nbrEspace; i++){
            name+= espace;
        }
        nbrEspace = 2 - dept.length();
        for (int i = 0; i < nbrEspace; i++){
            dept+= espace;
        }
        nbrEspace = 19 - promo.length();
        for (int i = 0; i < nbrEspace; i++){
            promo+= espace;
        }

        String stagiaireXString = surname + "*" + name + "*" + dept + "*" + promo + "*" + year;
        listOneStagiaire.add(stagiaireXString);

        return listOneStagiaire;
    }
    public String createStringOneStagiaire(String surname, String name, String dept, String promo, String year){

        List <String> listOneStagiaire = new ArrayList<>();
        String temp = "";
        String espace = " ";

        int nbrEspace = 50 - surname.length();
        for (int i = 0; i < nbrEspace; i++){
            surname+= espace;
        }
        nbrEspace = 50 - name.length();
        for (int i = 0; i < nbrEspace; i++){
            name+= espace;
        }
        nbrEspace = 2 - dept.length();
        for (int i = 0; i < nbrEspace; i++){
            dept+= espace;
        }
        nbrEspace = 19 - promo.length();
        for (int i = 0; i < nbrEspace; i++){
            promo+= espace;
        }

        String stagiaireXString = surname + "*" + name + "*" + dept + "*" + promo + "*" + year;
        return stagiaireXString;
    }
    public boolean isAlreadyInList (List<Stagiaire> stList, Stagiaire stagiaireX){
        boolean isAlreadyInList = false;

        for (Stagiaire st : stList) {
            if (st.getName().equals(stagiaireX.getName()) &&
                    st.getSurname().equals(stagiaireX.getSurname()) &&
                    st.getDept().equals(stagiaireX.getDept()) &&
                    st.getPromo().equals(stagiaireX.getPromo()) &&
                    st.getYear().equals(stagiaireX.getYear())){
                isAlreadyInList = true;
            }
        }

        return isAlreadyInList;
    }
    public boolean searchAlreadyInList (List<Stagiaire> stList, String search, int criterion){
        boolean searchAlreadyInList = false;
        String temp = "";

        for (Stagiaire st : stList) {
            switch (criterion){
                case 1:
                    temp = st.getSurname();
                    break;
                case 2:
                    temp = st.getName();
                    break;
                case 3:
                    temp = st.getDept();
                    break;
                case 4:
                    temp = st.getPromo();
                    break;
                case 5:
                    temp = st.getYear();
                    break;
            }
            if (searchCharToChar(search, temp) == 0){
                searchAlreadyInList = true;
            }
        }
        return searchAlreadyInList;
    }


}
