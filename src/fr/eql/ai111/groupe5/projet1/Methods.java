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
}
