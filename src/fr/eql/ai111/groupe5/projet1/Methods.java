package fr.eql.ai111.groupe5.projet1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

public class Methods {

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

    public Integer searchCharToChar(String newAdd, String parent,
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
        } while (newChar == parentChar & charIndex < newAdd.length()-1);

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
                                      char removeDollars, String child) throws IOException {
        for (int i = 0; i < 7; i++) {
            removeDollars = rafDataBase.readChar();
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

    public Character incrementAlphabet (Integer indexChar){

        char lettre = ' ';

        switch (indexChar){
            case 0:
                lettre = ' ';
                break;
            case 1:
                lettre = 'A';
                break;
            case 2:
                lettre = 'B';
                break;
            case 3:
                lettre = 'C';
                break;
            case 4:
                lettre = 'D';
                break;
            case 5:
                lettre = 'E';
                break;
            case 6:
                lettre = 'F';
                break;
            case 7:
                lettre = 'G';
                break;
            case 8:
                lettre = 'H';
                break;
            case 9:
                lettre = 'I';
                break;
            case 10:
                lettre = 'J';
                break;
            case 11:
                lettre = 'K';
                break;
            case 12:
                lettre = 'L';
                break;
            case 13:
                lettre = 'M';
                break;
            case 14:
                lettre = 'N';
                break;
            case 15:
                lettre = 'O';
                break;
            case 16:
                lettre = 'P';
                break;
            case 17:
                lettre = 'Q';
                break;
            case 18:
                lettre = 'R';
                break;
            case 19:
                lettre = 'S';
                break;
            case 20:
                lettre = 'T';
                break;
            case 21:
                lettre = 'U';
                break;
            case 22:
                lettre = 'V';
                break;
            case 23:
                lettre = 'W';
                break;
            case 24:
                lettre = 'X';
                break;
            case 25:
                lettre = 'Y';
                break;
            case 26:
                lettre = 'Z';
                break;
        }
        return lettre;
    }

    public String testAlphabet (Character lettre){
        int indexChar = 0;
        String test = "";

        for (indexChar = 0; indexChar < 27; indexChar++){
            test += incrementAlphabet(indexChar);
            for (indexChar = 0; indexChar < 27; indexChar++){

            }
        }



        return test;
    }
}
