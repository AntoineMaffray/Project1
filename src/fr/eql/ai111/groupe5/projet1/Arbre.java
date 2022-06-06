package fr.eql.ai111.groupe5.projet1;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class Arbre {


//    CONSTANTES
    long REF6 = 260; // en bytes - emplacement de la référence de l'enfant gauche
    long REF7 = 278; // en bytes - emplacement de la référence de l'enfant droit
    long REF8 = 296; // en bytes - emplacement de la référence du parent
    long ELT = 318; // en bytes - taille intégral d'un objet
    int ELTROUGH = 130; // en caractères - taille des informations (sans réf de structure)

    private static final Logger logger  = LogManager.getLogger();

    public void arbreInsertion (List<String> listStringStagiaires) throws IOException {

        Methods methods = new Methods();

        // Ouverture des différentes instances de lecture et éciture
        RandomAccessFile rafDataBase = new RandomAccessFile("C:/FolderProjet/Raf.bin", "rw");

        // Variables de l'Arbre
        rafDataBase.seek(0);
        String root;
        String newAdd = "";
        String parent = "";
        String child = "0";
        char removeDollars = 0;
        long parentPlace = 0;
        String wrote = "";

        root = listStringStagiaires.get(0);
        rafDataBase.seek(0);
        rafDataBase.writeChars(root + "*" + "$$$$$$$$" + "*" + "$$$$$$$$" + "*" + "$$$$$$$$" + "*" + "V" + "*");

        // Premiere boucle d'itération de la liste
        for (int indexList = 1; indexList < listStringStagiaires.size(); indexList++) {
        String lss = listStringStagiaires.get(indexList);
        newAdd = lss;
        int charIndex = 0;

        // Boucle de définition de la place du nouvel enfant (newAdd) et sa référence
            do {
                wrote = "";
                parent = "";
                rafDataBase.seek(parentPlace);
                for (int i = 0; i < ELTROUGH; i++) {
                    parent += rafDataBase.readChar();
                }
                Character newChar = newAdd.charAt(charIndex);
                Character parentChar = parent.charAt(charIndex);

                // Comparaison inférieure -> Entrée dans la branche de gauche
                if (newChar < parentChar || methods.compareCharToChar(newAdd, parent, newChar, parentChar, charIndex) == -1) {
                    rafDataBase.seek(parentPlace + REF6);
                    child = String.valueOf(rafDataBase.readChar());

                    // Si un enfant existe à cet emplacement, on descend au niveau inférieur
                    if (!child.equals("$")) {
                        parentPlace = Long.parseLong(methods.removeDollarFromRef(rafDataBase, removeDollars, child));

                    // S'il n'y a pas d'enfant à cet emplacement, on écrit le nouvel enfant
                    } else if (child.equals("$")) {
                        methods.writeLeftChildInRaf(rafDataBase, parentPlace, REF6, REF8, ELT, newAdd);
                        parentPlace = 0;
                        wrote = "done";
                    }

                    // Comparaison supérieure -> Entrée dans la branche de droite
                } else if (newChar > parentChar || methods.compareCharToChar(newAdd, parent, newChar, parentChar, charIndex) == 1) {
                    rafDataBase.seek(parentPlace + REF7);
                    child = String.valueOf(rafDataBase.readChar());

                    // Si un enfant existe à cet emplacement, on descend au niveau inférieur
                    if (!child.equals("$")) {
                    parentPlace = Long.parseLong(methods.removeDollarFromRef(rafDataBase, removeDollars, child));

                    // S'il n'y a pas d'enfant à cet emplacement, on écrit le nouvel enfant
                    } else if (child.equals("$")) {
                        methods.writeRightChildInRaf(rafDataBase, parentPlace, REF7, REF8, ELT, newAdd);
                        parentPlace = 0;
                        wrote = "done";
                    }
                }
            } while (!wrote.equals("done")) ;
        }
        rafDataBase.close();
    }

    public ObservableList<Stagiaire> arbreSearch (String newAdd) throws IOException{

        Methods methods = new Methods();

        // Ouverture des différentes instances de lecture et éciture
        RandomAccessFile rafDataBase = new RandomAccessFile("C:/FolderProjet/Raf.bin", "rw");

        // Variables de l'Arbre
        rafDataBase.seek(0);
        String parent = "";
        String child = "0";
        char removeDollars = 0;
        long parentPlace = 0;
        char temp;
        String wrote = "";
        ObservableList <Stagiaire> listSearch = FXCollections.observableArrayList();

        rafDataBase.seek(0);
        for (int i = 0; i < ELTROUGH; i++) {
            parent += rafDataBase.readChar();
        }
            // Boucle de définition de la place du nouvel enfant (newAdd) et sa référence
        do {
            int charIndex = 0;
            wrote = "";
            parent = "";
            rafDataBase.seek(parentPlace);
            for (int i = 0; i < ELTROUGH; i++) {
                parent += rafDataBase.readChar();
            }
            Character newChar = newAdd.charAt(charIndex);
            Character parentChar = parent.charAt(charIndex);

            // Comparaison inférieure -> Entrée dans la branche de gauche
            if (newChar < parentChar || methods.searchCharToChar(newAdd, parent, newChar, parentChar, charIndex) == -1) {
                rafDataBase.seek(parentPlace + REF6);
                temp = rafDataBase.readChar();
                child = String.valueOf(temp);
                System.out.println(child);
                parentPlace = Long.parseLong(methods.removeDollarFromRef(rafDataBase, removeDollars, child));

                // Comparaison supérieure -> Entrée dans la branche de droite
            } else if (newChar > parentChar || methods.searchCharToChar(newAdd, parent, newChar, parentChar, charIndex) == 1) {
                rafDataBase.seek(parentPlace + REF7);
                temp = rafDataBase.readChar();
                child = String.valueOf(temp);
                System.out.println(child);
                parentPlace = Long.parseLong(methods.removeDollarFromRef(rafDataBase, removeDollars, child));

                // Comparaison égale -> remontée du résultat
            } else if (newChar == parentChar || methods.searchCharToChar(newAdd, parent, newChar, parentChar, charIndex) == 0) {
                rafDataBase.seek(parentPlace);
                listSearch.add(methods.createObjectStagiaire(parent));
                    wrote = "done";
            }
        } while (!wrote.equals("done")) ;
        rafDataBase.close();

        for (Stagiaire search : listSearch) {
            System.out.println(search);
        }
        return listSearch;
    }

    public ObservableList <Stagiaire> arbreParcours () throws IOException {

        Methods methods = new Methods();

        // Ouverture des différentes instances de lecture et éciture
        RandomAccessFile rafDataBase = new RandomAccessFile("C:/FolderProjet/Raf.bin", "rw");

        // Variables de l'Arbre
        rafDataBase.seek(0);
        String parent = "";
        String child = "0";
        long childPlace = 0;
        char removeDollars = 0;
        long parentPlace = 0;
        int indexPath = 0;
        char temp;
        String wrote = "";
        ObservableList <Stagiaire> listSearch = FXCollections.observableArrayList();

        // Descente dans la branche la plus à gauche

        do{
            rafDataBase.seek(childPlace+REF6);
            temp = rafDataBase.readChar();
            child = String.valueOf(temp);
            if (temp != '$'){
                childPlace = Long.parseLong(methods.removeDollarFromRef(rafDataBase, removeDollars, child));
                rafDataBase.seek(childPlace);
            }
        }while (temp != '$');
        indexPath = 1;
        rafDataBase.seek(childPlace);
        child = "";
        for (int i = 0; i < ELTROUGH; i++) {
            child += rafDataBase.readChar();
        }
        listSearch.add(methods.createObjectStagiaire(child));
        rafDataBase.seek(childPlace+REF8);
        parent ="";
        for (int i = 0; i < ELTROUGH; i++) {
            parent += rafDataBase.readChar();
        }
        listSearch.add(methods.createObjectStagiaire(parent));

        return listSearch;
    }

}