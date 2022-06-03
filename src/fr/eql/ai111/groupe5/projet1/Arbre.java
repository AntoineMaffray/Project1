package fr.eql.ai111.groupe5.projet1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class Arbre {


//    CONSTANTES
    long ref6 = 260; // en bytes
    long ref7 = 278; // en bytes
    long elt = 300; // en bytes
    int eltrough = 130; // en caractères

    private static final Logger logger  = LogManager.getLogger();


    public void arbreInsertion (List<String> listStringStagiaires) throws IOException {

        // Ouverture des différentes instances de lecture et éciture
        RandomAccessFile rafDataBase = new RandomAccessFile("C:/FolderProjet/Raf.bin", "rw");

        // Variables de l'Arbre
        rafDataBase.seek(0);
        String root;
        String newAdd = "";
        String parent = "";
        String child = "0";
        char removeDollars;
        long parentPlace = 0;
        String wrote = "";

        root = listStringStagiaires.get(0);
        rafDataBase.seek(0);
        rafDataBase.writeChars(root + "*" + "$$$$$$$$" + "*" + "$$$$$$$$" + "*" + "V" + "*");

        // Premiere boucle d'itération de la liste
        for (int indexList = 1; indexList < listStringStagiaires.size(); indexList++) {
        String lss = listStringStagiaires.get(indexList);
        newAdd = lss;
        int charIndex = 0;

            do {
                wrote = "";
                parent = "";
                rafDataBase.seek(parentPlace);
                for (int i = 0; i < eltrough; i++) {
                    parent += rafDataBase.readChar();
                }
                Character newChar = newAdd.charAt(charIndex);
                Character knotChar = parent.charAt(charIndex);

                // Boucle de vérification du mot lettre à lettre pour comparaison au noeud
                // Sortir une méthode qui prend en argument deux char et qui renvoie une comparaison entre ces deux char
                if (newChar == knotChar) {
                    charIndex = 1;
                    do {
                        newChar = newAdd.charAt(charIndex);
                        knotChar = parent.charAt(charIndex);
                        if (newChar == 'é' && knotChar == 'é' || newChar == 'è' && knotChar == 'è'
                        || newChar == 'ï' && knotChar == 'ï') {
                            charIndex++;
                            newChar = newAdd.charAt(charIndex);
                            knotChar = parent.charAt(charIndex);
                        }
                        charIndex ++;
                    } while (newChar == knotChar);
                }

                // Comparaison inférieure -> Entrée dans la branche de gauche
                if (newChar < knotChar) {
                    rafDataBase.seek(parentPlace + ref6);
                    child = String.valueOf(rafDataBase.readChar());

                    if (!child.equals("$")) {
                        for (int i = 0; i < 7; i++) {
                            removeDollars = rafDataBase.readChar();
                            if (removeDollars != '$') {
                                child += removeDollars;
                            }
                        }
                        parentPlace = Long.parseLong(child);


                    } else if (child.equals("$")) {
                        rafDataBase.seek(parentPlace + ref6);
                        rafDataBase.writeChars(String.valueOf(rafDataBase.length()));
                        rafDataBase.seek(rafDataBase.length());
                        rafDataBase.writeChars(newAdd + "*" + "$$$$$$$$" + "*" + "$$$$$$$$" + "*" + "V" + "*");
                        parentPlace = 0;
                        wrote = "done";
                    }


                    // Comparaison supérieure -> Entrée dans la branche de droite
                } else if (newChar > knotChar) {
                    rafDataBase.seek(parentPlace + ref7);
                    child = String.valueOf(rafDataBase.readChar());

                    if (!child.equals("$")) {
                        for (int i = 0; i < 7; i++) {
                            removeDollars = rafDataBase.readChar();
                            if (removeDollars != '$') {
                                child += removeDollars;
                            }
                        }
                        parentPlace = Long.parseLong(child);

                    } else if (child.equals("$")) {
                        rafDataBase.seek(parentPlace + ref7);
                        rafDataBase.writeChars(String.valueOf(rafDataBase.length()));
                        rafDataBase.seek(rafDataBase.length());
                        rafDataBase.writeChars(newAdd + "*" + "$$$$$$$$" + "*" + "$$$$$$$$" + "*" + "V" + "*");
                        parentPlace = 0;
                        wrote = "done";
                    }
                }


        } while (!wrote.equals("done")) ;

    }
    }
}