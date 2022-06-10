package fr.eql.ai111.groupe5.projet1.methodsback;

import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class Methods {

    private static final Logger logger  = LogManager.getLogger();
    public List<String> createListStagiaires() throws IOException {

        FileReader reader = null;
        try {
            reader = new FileReader("C:/FolderProjet/Stagiaires.txt");
        } catch (FileNotFoundException e) {
            logger.warn("Le fichier ? lire n'existe pas.");
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
                    name = temp.substring(0,1).toUpperCase() + temp.substring(1).toLowerCase();
                    nbrEspace = 50 - name.length();
                    for (int i = 0; i < nbrEspace; i++){
                        name+= espace;
                    }
                    break;
                case 3 :
                    dept = temp;
                    nbrEspace = 3 - dept.length();
                    for (int i = 0; i < nbrEspace; i++){
                        dept+= espace;
                    }
                    break;
                case 4 :
                    promo = temp.toUpperCase();
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
                if (newChar == '?' && parentChar == '?' || newChar == '?' && parentChar == '?'
                        || newChar == '?' && parentChar == '?') {
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
            if (newChar == '?' && parentChar == '?' || newChar == '?' && parentChar == '?'
                    || newChar == '?' && parentChar == '?') {
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
        } else if (charIndex == newAdd.length()){
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
        surname = surname.toUpperCase();
        for (int i = 0; i < nbrEspace; i++){
            surname+= espace;
        }
        nbrEspace = 50 - name.length();
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        for (int i = 0; i < nbrEspace; i++){
            name+= espace;
        }
        nbrEspace = 3 - dept.length();
        for (int i = 0; i < nbrEspace; i++){
            dept+= espace;
        }
        nbrEspace = 19 - promo.length();
        promo = promo.toUpperCase();
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

    public Integer simpleComparison(String newAdd, String parent){
        int charIndex = 0;
        int comparison = 0;
        char newChar;
        char parentChar;
        do {
            newChar = newAdd.charAt(charIndex);
            parentChar = parent.charAt(charIndex);
            if (newChar == '?' && parentChar == '?' || newChar == '?' && parentChar == '?'
                    || newChar == '?' && parentChar == '?') {
                charIndex++;
                newChar = newAdd.charAt(charIndex);
                parentChar = parent.charAt(charIndex);
            }
            charIndex++;
        } while (newChar == parentChar);

        if (newChar > parentChar){
            comparison = 1;
        } else if (newChar < parentChar){
            comparison = -1;
        }
        return comparison;
    }

    public String hashage (String mdp) {
        String hashed = "";
        long hasher = 99991;
        mdp += mdp + "KeY";
        for (int i = 0; i < mdp.length(); i++) {
            switch (mdp.charAt(i)) {
                case 'a' : hasher += hasher *307;break;
                case 'A' : hasher += hasher /19;break;
                case 'b' : hasher += hasher /29;break;
                case 'B' : hasher += hasher *311;break;
                case 'c' : hasher += hasher /41;break;
                case 'C' : hasher += hasher *313;break;
                case 'd' : hasher += hasher /43;break;
                case 'D' : hasher += hasher *317;break;
                case 'e' : hasher += hasher *331;break;
                case 'E' : hasher += hasher /89;break;
                case 'f' : hasher += hasher /2;break;
                case 'F' : hasher += hasher *337;break;
                case 'g' : hasher += hasher *347;break;
                case 'G' : hasher += hasher *349;break;
                case 'h' : hasher += hasher /3;break;
                case 'H' : hasher += hasher *353;break;
                case 'i' : hasher += hasher /11;break;
                case 'I' : hasher += hasher *359;break;
                case 'j' : hasher += hasher *367;break;
                case 'J' : hasher += hasher /31;break;
                case 'k' : hasher += hasher /37;break;
                case 'K' : hasher += hasher *373;break;
                case 'l' : hasher += hasher *379;break;
                case 'L' : hasher += hasher /47;break;
                case 'm' : hasher += hasher *383;break;
                case 'M' : hasher += hasher /53;break;
                case 'n' : hasher += hasher /59;break;
                case 'N' : hasher += hasher /61;break;
                case 'o' : hasher += hasher *389;break;
                case 'O' : hasher += hasher *397;break;
                case 'p' : hasher += hasher *401;break;
                case 'P' : hasher += hasher /67;break;
                case 'q' : hasher += hasher *409;break;
                case 'Q' : hasher += hasher /71;break;
                case 'r' : hasher += hasher /73;break;
                case 'R' : hasher += hasher *419;break;
                case 's' : hasher += hasher /79;break;
                case 'S' : hasher += hasher *421;break;
                case 't' : hasher += hasher /83;break;
                case 'T' : hasher += hasher *431;break;
                case 'u' : hasher += hasher *433;break;
                case 'U' : hasher += hasher /89;break;
                case 'v' : hasher += hasher /97;break;
                case 'V' : hasher += hasher /101;break;
                case 'w' : hasher += hasher *439;break;
                case 'W' : hasher += hasher /103;break;
                case 'x' : hasher += hasher *443;break;
                case 'X' : hasher += hasher /107;break;
                case 'y' : hasher += hasher /109;break;
                case 'Y' : hasher += hasher *449;break;
                case 'z' : hasher += hasher *503;break;
                case 'Z' : hasher += hasher *509;break;
                case '0' : hasher += hasher /113;break;
                case '1' : hasher += hasher /127;break;
                case '2' : hasher += hasher /131;break;
                case '3' : hasher += hasher *521;break;
                case '4' : hasher += hasher *523;break;
                case '5' : hasher += hasher /137;break;
                case '6' : hasher += hasher *541;break;
                case '7' : hasher += hasher /139;break;
                case '8' : hasher += hasher /149;break;
                case '9' : hasher += hasher *547;break;
                case '&' : hasher += hasher *557;break;
                case 'é' : hasher += hasher *563;break;
                case '\"' : hasher += hasher /151;break;
                case '\'' : hasher += hasher /157;break;
                case '(' : hasher += hasher /163;break;
                case '-' : hasher += hasher *569;break;
                case 'è' : hasher += hasher /167;break;
                case 'ô' : hasher += hasher *571;break;
                case 'î' : hasher += hasher /173;break;
                case 'ê' : hasher += hasher *577;break;
                case 'â' : hasher += hasher /179;break;
                case 'ö' : hasher += hasher *587;break;
                case 'ï' : hasher += hasher /181;break;
                case 'ë' : hasher += hasher /191;break;
                case 'ä' : hasher += hasher *491;break;
                case 'à' : hasher += hasher *499;break;
                case '_' : hasher += hasher *503;break;
                case 'ù' : hasher += hasher /193;break;
                case '§' : hasher += hasher *509;break;
                case '?' : hasher += hasher *521;break;
                case ')' : hasher += hasher /197;break;
                case '=' : hasher += hasher *523;break;
                case '$' : hasher += hasher *541;break;
                case '*' : hasher += hasher /199;break;
                case '%' : hasher += hasher *547;break;
                case '#' : hasher += hasher /211;break;
                case '!' : hasher += hasher *557;break;
                case ':' : hasher += hasher /223;break;
                case ';' : hasher += hasher *563;break;
                case ',' : hasher += hasher /227;break;
                case '²' : hasher += hasher *569;break;
                case '~' : hasher += hasher /229;break;
                case '{' : hasher += hasher /233;break;
                case '[' : hasher += hasher /239;break;
                case '|' : hasher += hasher *571;break;
                case '`' : hasher += hasher *577;break;
                case '^' : hasher += hasher *587;break;
                case '@' : hasher += hasher /241;break;
                case ']' : hasher += hasher /251;break;
                case '}' : hasher += hasher *593;break;
                case '¤' : hasher += hasher *599;break;
                case 'µ' : hasher += hasher /257;break;
                case '.' : hasher += hasher *601;break;
                case '/' : hasher += hasher /263;break;
                case '£' : hasher += hasher *607;break;
                case '¨' : hasher += hasher *613;break;
                case '°' : hasher += hasher /269;break;
                case '+' : hasher += hasher *617;break;
                case '\\' : hasher += hasher /271;break;
                default : hasher += hasher +10;break;
            }
            hashed = "u_u" + String.valueOf(hasher) + "n_n" ;
        }
        return hashed;
    }

    public void ExportPDF (ObservableList<Stagiaire> stagiaires, String fileName) throws IOException {

        Arbre arbre = new Arbre();
        int compteur = 10;
        PDDocument doc = null;
        try
        {
            doc = new PDDocument();
            PDPage page = new PDPage();
            doc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            PDImageXObject image = PDImageXObject.createFromFile("C:\\Users\\Formation\\Documents\\Projects\\PROJET1GROUPE5\\src\\fr\\eql\\ai111\\groupe5\\projet1\\interfaces\\images\\eql.png", new PDDocument());
            PDImageXObject image2 = PDImageXObject.createFromFile("C:\\Users\\Formation\\Documents\\Projects\\PROJET1GROUPE5\\src\\fr\\eql\\ai111\\groupe5\\projet1\\interfaces\\images\\eqlfil.png", new PDDocument());
            contentStream.drawImage(image, 30, 580);
            PDFont pdfFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
            // taille des caractères
            float fontSize = 12;
            // interligne
            float leading = 1.5f * fontSize;
            PDRectangle mediabox = page.getMediaBox();
            // marge gauche
            float margin = 20;
            String text = "";
            StringBuffer stringBuffer = new StringBuffer();
            contentStream.beginText();
            contentStream.setFont(pdfFont, fontSize);
            contentStream.newLineAtOffset(30, 550);
            System.out.println(stagiaires.size());
            for (Stagiaire s : stagiaires) {
                int nspace1 = 20 - (s.getSurname().trim().length());
                String space1 = "";
                text = "";
                stringBuffer.append(s.getSurname().trim()+s.getName().trim()+ s.getDept().trim()+ s.getPromo().trim()+ s.getYear().trim());
                text = "Nom: " + s.getSurname().trim()+ "  " +
                        "Prénom: " + s.getName().trim()+ "  " +
                        "Département: " + s.getDept().trim()+ "  " +
                        "Promotion: " + s.getPromo().trim()+ "  " +
                        "Année: " + s.getYear().trim();
                compteur = compteur+1;
                if (compteur > 40) {
                    page = new PDPage();
                    doc.addPage(page);
                    contentStream.endText();
                    contentStream.close();
                    contentStream = new PDPageContentStream(doc, page);
                    contentStream.drawImage(image2, 150, 150);
                    contentStream.beginText();
                    contentStream.setFont(pdfFont, fontSize);
                    contentStream.newLineAtOffset(20, 750);
                    compteur = 0;
                }
                contentStream.showText(text);
                contentStream.newLineAtOffset(0, -leading);
            }
            contentStream.endText();
            contentStream.close();
            File file = new File("C:/theEQLBook/"+fileName+".pdf");
            if (!file.exists()) {
                doc.save(file);
            }
        }
        finally
        {
            if (doc != null)
            {
                doc.close();
            }
        }
    }
}