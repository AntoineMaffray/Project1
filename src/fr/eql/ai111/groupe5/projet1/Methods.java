package fr.eql.ai111.groupe5.projet1;

import java.io.IOException;
import java.io.RandomAccessFile;

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

            if (newChar >parentChar){
                comparison = 1;
            } else if (newChar <parentChar){
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

    public Integer simpleComparison(String newAdd, String parent){
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
                case 'û' : hasher += hasher /173;break;
                case 'î' : hasher += hasher *577;break;
                case 'ê' : hasher += hasher /179;break;
                case 'ö' : hasher += hasher *587;break;
                case 'ë' : hasher += hasher /181;break;
                case 'ï' : hasher += hasher /191;break;
                case 'ä' : hasher += hasher *491;break;
                case 'ü' : hasher += hasher *499;break;
                case '_' : hasher += hasher *503;break;
                case 'â' : hasher += hasher /193;break;
                case 'ç' : hasher += hasher *509;break;
                case 'à' : hasher += hasher *521;break;
                case ')' : hasher += hasher /197;break;
                case '=' : hasher += hasher *523;break;
                case '$' : hasher += hasher *541;break;
                case '*' : hasher += hasher /199;break;
                case 'ù' : hasher += hasher *547;break;
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
                case '?' : hasher += hasher /257;break;
                case '.' : hasher += hasher *601;break;
                case '/' : hasher += hasher /263;break;
                case '§' : hasher += hasher *607;break;
                case 'µ' : hasher += hasher *613;break;
                case '£' : hasher += hasher /269;break;
                case '+' : hasher += hasher *617;break;
                case '°' : hasher += hasher /271;break;
                default : hasher += hasher +10;break;
            }
            hashed = "u_u" + String.valueOf(hasher) + "n_n" ;
        }
        return hashed;
    }

}
