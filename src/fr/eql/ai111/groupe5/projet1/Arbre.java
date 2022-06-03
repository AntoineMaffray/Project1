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

    private static final Logger logger  = LogManager.getLogger();


    public void arbreInsertion (List<String> listStringStagiaires) throws IOException {

        // Ouverture des différentes instances de lecture et éciture
        FileReader reader = new FileReader("C:/Users/Formation/Desktop/PROJET1/Stagiaires.txt");
        BufferedReader bfReader = new BufferedReader(reader);
        RandomAccessFile rafDataBase = new RandomAccessFile("C:/Users/Formation/Desktop/PROJET1/Raf.bin", "rw");

        // Variables de l'Arbre
        rafDataBase.seek(0);
        String newAdd = "";
        String knot = "";
        String knotAncestor = "";
        long knotPlace = 0;
        long knotAncestorPlace = 0;

        // Premiere boucle d'itération de la liste

        for (int indexList = 0; indexList < listStringStagiaires.size(); indexList++) {
            String lss = listStringStagiaires.get(indexList);
            newAdd = lss;
            int charIndex = 0;

            // Création-écriture du premier nom qui devient premier noeud
            if (indexList == 0){
                rafDataBase.seek(0);
                rafDataBase.writeChars(newAdd+"*"+"00000000"+"*"+"00000000"+"*"+"V"+"*");
                System.out.println(("nouveau = " + newAdd));
                knot = newAdd;


            // Itération de l'objet 2 et du reste de la liste, début de l'arbre
            } else if (indexList > 0){
                Character newChar = newAdd.charAt(charIndex);
                Character knotChar = knot.charAt(charIndex);

                // Boucle de vérification du mot lettre à lettre pour comparaison au noeud
                if (newChar == knotChar){
                    charIndex = 1;
                    do{
                        newChar = newAdd.charAt(charIndex);
                        knotChar = knot.charAt(charIndex);
                        charIndex++;
                    }while (newChar == knotChar);
                }

                // Comparaison inférieure -> Entrée dans la branche de gauche
                if (newChar < knotChar){
                    // Vérification s'il y a déjà un "enfant inférieur"

                    System.out.println("gauche");
                    rafDataBase.seek(knotPlace+ref6);
                    rafDataBase.writeChars(String.valueOf(knotPlace));
                    rafDataBase.seek(rafDataBase.length());
                    rafDataBase.writeChars(newAdd);

                // Comparaison inférieure -> Entrée dans la branche de gauche
                } else if (newChar > knotChar){
                    System.out.println("droite");
                    rafDataBase.seek(knotPlace+ref7);
                    rafDataBase.writeChars(String.valueOf(knotPlace));
                    rafDataBase.seek(rafDataBase.length());
                    rafDataBase.writeChars(newAdd);
                }
            }
        }
    }
}
