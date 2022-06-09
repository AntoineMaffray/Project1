package fr.eql.ai111.groupe5.projet1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Arbre {

//    CONSTANTES
long REF1 = 0; // en bytes - emplacement de la référence du nom
    long REF2 = 102; // en bytes - emplacement de la référence du prénom
    long REF3 = 204; // en bytes - emplacement de la référence du département
    long REF4 = 210; // en bytes - emplacement de la référence de la promo
    long REF5 = 250; // en bytes - emplacement de la référence de l'année
    long REF6 = 260; // en bytes - emplacement de la référence de l'enfant gauche
    long REF7 = 278; // en bytes - emplacement de la référence de l'enfant droit
    long REF8 = 296; // en bytes - emplacement de la référence du parent
    long REF9 = 314; // en bytes - emplacement de la référence d'activation/désactivation
    long ELT = 318; // en bytes - taille intégrale d'un objet
    int ELTROUGH = 130; // en caractères - taille des informations (sans réf de structure)
    int ELTREF1 = 50; // en caractères - taille du nom
    int ELTREF2 = 50; // en caractères - taille du prénom
    int ELTREF3 = 2; // en caractères - taille du département
    int ELTREF4 = 19; // en caractères - taille de la promo
    int ELTREF5 = 4; // en caractères - taille de l'année

    private static final Logger logger  = LogManager.getLogger();

    public void arbreCreation(List<String> listStringStagiaires) throws IOException {

        Methods methods = new Methods();

        // Ouverture des différentes instances de lecture et éciture
        RandomAccessFile rafDataBase = new RandomAccessFile("C:/BillyBook/Raf.bin", "rw");

        // Variables de l'Arbre
        rafDataBase.seek(0);
        String root;
        String newAdd = "";
        String parent = "";
        String child = "";
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
                        parentPlace = Long.parseLong(methods.removeDollarFromRef(rafDataBase, child));

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
                    parentPlace = Long.parseLong(methods.removeDollarFromRef(rafDataBase, child));

                    // S'il n'y a pas d'enfant à cet emplacement, on écrit le nouvel enfant
                    } else if (child.equals("$")) {
                        methods.writeRightChildInRaf(rafDataBase, parentPlace, REF7, REF8, ELT, newAdd);
                        parentPlace = 0;
                        wrote = "done";
                    }
                }
            } while (!wrote.equals("done")) ;
            System.out.println(indexList + " / " +listStringStagiaires.size());
        }
        rafDataBase.close();
    }
    public void arbreInsertionNew (String newAdd) throws IOException {

        Methods methods = new Methods();

        // Ouverture des différentes instances de lecture et éciture
        RandomAccessFile rafDataBase = new RandomAccessFile("C:/BillyBook/Raf.bin", "rw");

        // Variables de l'Arbre
        rafDataBase.seek(0);
        String parent = "";
        String child = "";
        char removeDollars = 0;
        long parentPlace = 0;
        String wrote = "";
        int charIndex = 0;

            // Boucle de définition de la place du nouvel enfant (newAdd) et sa référence
            do {
                wrote = "";
                parent = "";
                rafDataBase.seek(parentPlace);
                for (int i = 0; i < ELTROUGH; i++) {
                    parent += rafDataBase.readChar();
                }
                char newChar = newAdd.charAt(charIndex);
                char parentChar = parent.charAt(charIndex);

                // Comparaison inférieure -> Entrée dans la branche de gauche
                if (newChar < parentChar || methods.searchCharToChar(newAdd, parent) == -1) {
                    rafDataBase.seek(parentPlace + REF6);
                    child = String.valueOf(rafDataBase.readChar());

                    // Si un enfant existe à cet emplacement, on descend au niveau inférieur
                    if (!child.equals("$")) {
                        parentPlace = Long.parseLong(methods.removeDollarFromRef(rafDataBase, child));

                        // S'il n'y a pas d'enfant à cet emplacement, on écrit le nouvel enfant
                    } else if (child.equals("$")) {
                        methods.writeLeftChildInRaf(rafDataBase, parentPlace, REF6, REF8, ELT, newAdd);
                        parentPlace = 0;
                        wrote = "done";
                    }

                    // Comparaison supérieure -> Entrée dans la branche de droite
                } else if (newChar > parentChar || methods.searchCharToChar(newAdd, parent) == 1) {
                    rafDataBase.seek(parentPlace + REF7);
                    child = String.valueOf(rafDataBase.readChar());

                    // Si un enfant existe à cet emplacement, on descend au niveau inférieur
                    if (!child.equals("$")) {
                        parentPlace = Long.parseLong(methods.removeDollarFromRef(rafDataBase, child));

                        // S'il n'y a pas d'enfant à cet emplacement, on écrit le nouvel enfant
                    } else if (child.equals("$")) {
                        methods.writeRightChildInRaf(rafDataBase, parentPlace, REF7, REF8, ELT, newAdd);
                        parentPlace = 0;
                        wrote = "done";
                    }
                }
            } while (!wrote.equals("done")) ;
        rafDataBase.close();
    }
    public ObservableList<Stagiaire> arbreSearch (String newAdd) throws IOException{

        Methods methods = new Methods();

        // Ouverture des différentes instances de lecture et éciture
        RandomAccessFile rafDataBase = new RandomAccessFile("C:/BillyBook/Raf.bin", "rw");

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
            if (newChar < parentChar || methods.searchCharToChar(newAdd, parent) == -1) {
                rafDataBase.seek(parentPlace + REF6);
                temp = rafDataBase.readChar();
                child = String.valueOf(temp);
                parentPlace = Long.parseLong(methods.removeDollarFromRef(rafDataBase, child));

                // Comparaison supérieure -> Entrée dans la branche de droite
            } else if (newChar > parentChar || methods.searchCharToChar(newAdd, parent) == 1) {
                rafDataBase.seek(parentPlace + REF7);
                temp = rafDataBase.readChar();
                child = String.valueOf(temp);
                parentPlace = Long.parseLong(methods.removeDollarFromRef(rafDataBase, child));

                // Comparaison égale -> remontée du résultat
            } else if (newChar == parentChar || methods.searchCharToChar(newAdd, parent) == 0) {
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

    public List <Stagiaire> arbreParcours () throws IOException{

        Methods methods = new Methods();
        List <Stagiaire> listSearch = new ArrayList<>();

        // Ouverture des différentes instances de lecture et écriture
        RandomAccessFile rafDataBase = new RandomAccessFile("C:/BillyBook/Raf.bin", "rw");

        long knot = 0;

        arbreRecursif(rafDataBase, methods, listSearch);
        return listSearch;

//        /**
//         * Affiche l'arbre selon un parcours prefixe
//         */
//        public void ParcoursPrefixe() {
//            System.out.println(getValeur());
//            if (getSousArbreGauche() != null)
//                getSousArbreGauche().ParcoursPrefixe();
//            if (getSousArbreDroit() != null)
//                getSousArbreDroit().ParcoursPrefixe();
    }
    private List <Stagiaire> arbreRecursif (RandomAccessFile rafDataBase, Methods methods,
                                            List listSearch) throws IOException {

        long knot = rafDataBase.getFilePointer();
        String temp = "";

        // Variables de l'Arbre
        rafDataBase.seek(knot+REF6);
        if (rafDataBase.readChar() != '$'){
            rafDataBase.seek(knot+REF6);
            String child = String.valueOf(rafDataBase.readChar());
            rafDataBase.seek(Long.parseLong(methods.removeDollarFromRef(rafDataBase, child)));
            arbreRecursif(rafDataBase, methods, listSearch);
        }

        temp = "";
        rafDataBase.seek(knot);
        for (int i = 0; i < ELTROUGH; i++){
            temp+=rafDataBase.readChar();
        }
        listSearch.add(methods.createObjectStagiaire(temp));

        rafDataBase.seek(knot+REF7);
        if (rafDataBase.readChar() != '$'){
            rafDataBase.seek(knot+REF7);
            String child = String.valueOf(rafDataBase.readChar());
            rafDataBase.seek(Long.parseLong(methods.removeDollarFromRef(rafDataBase, child)));
            arbreRecursif(rafDataBase, methods, listSearch);
        }

        return listSearch;
    }
    public ObservableList<Stagiaire> arbreModification (String newAdd, String modifiedString) throws IOException{

        Methods methods = new Methods();

        // Ouverture des différentes instances de lecture et écriture
        RandomAccessFile rafDataBase = new RandomAccessFile("C:/BillyBook/Raf.bin", "rw");

        // Variables de l'Arbre
        rafDataBase.seek(0);
        String parent = "";
        String child = "";
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
            char newChar = newAdd.charAt(charIndex);
            char parentChar = parent.charAt(charIndex);

            // Comparaison inférieure -> Entrée dans la branche de gauche
            if (newChar < parentChar || methods.searchCharToChar(newAdd, parent) == -1) {
                rafDataBase.seek(parentPlace + REF6);
                temp = rafDataBase.readChar();
                child = String.valueOf(temp);
                parentPlace = Long.parseLong(methods.removeDollarFromRef(rafDataBase, child));

                // Comparaison supérieure -> Entrée dans la branche de droite
            } else if (newChar > parentChar || methods.searchCharToChar(newAdd, parent) == 1) {
                rafDataBase.seek(parentPlace + REF7);
                temp = rafDataBase.readChar();
                child = String.valueOf(temp);
                parentPlace = Long.parseLong(methods.removeDollarFromRef(rafDataBase, child));

                // Comparaison égale -> modification de la valeur
            } else if (newChar == parentChar || methods.searchCharToChar(newAdd, parent) == 0) {
                rafDataBase.seek(parentPlace+REF9);
                rafDataBase.writeChars("X");
                arbreInsertionNew(modifiedString);
                wrote = "done";
            }
        } while (!wrote.equals("done")) ;
        rafDataBase.close();
        return listSearch;
    }
    public ObservableList<Stagiaire> arbreDReactivation (String newAdd, String showStatus) throws IOException{

        Methods methods = new Methods();

        // Ouverture des différentes instances de lecture et écriture
        RandomAccessFile rafDataBase = new RandomAccessFile("C:/BillyBook/Raf.bin", "rw");

        // Variables de l'Arbre
        rafDataBase.seek(0);
        String parent = "";
        String child = "";
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
            if (newChar < parentChar || methods.searchCharToChar(newAdd, parent) == -1) {
                rafDataBase.seek(parentPlace + REF6);
                temp = rafDataBase.readChar();
                child = String.valueOf(temp);
                parentPlace = Long.parseLong(methods.removeDollarFromRef(rafDataBase, child));

                // Comparaison supérieure -> Entrée dans la branche de droite
            } else if (newChar > parentChar || methods.searchCharToChar(newAdd, parent) == 1) {
                rafDataBase.seek(parentPlace + REF7);
                temp = rafDataBase.readChar();
                child = String.valueOf(temp);
                parentPlace = Long.parseLong(methods.removeDollarFromRef(rafDataBase, child));

                // Comparaison égale -> modification de la valeur
            } else if (newChar == parentChar || methods.searchCharToChar(newAdd, parent) == 0) {
                rafDataBase.seek(parentPlace+REF9);
                rafDataBase.writeChars(showStatus);
                wrote = "done";
            }
        } while (!wrote.equals("done")) ;
        rafDataBase.close();

        for (Stagiaire search : listSearch) {
            System.out.println(search);
        }
        return listSearch;
    }
    public List <Stagiaire> arbreParcoursSearch (int criterion, String search) throws IOException{

        Methods methods = new Methods();
        List <Stagiaire> listSearch = new ArrayList<>();

        // Ouverture des différentes instances de lecture et éciture
        RandomAccessFile rafDataBase = new RandomAccessFile("C:/BillyBook/Raf.bin", "rw");

        long knot = 0;

        arbreRecursifSearch(rafDataBase, methods, listSearch, criterion, search);
        return listSearch;

//        /**
//         * Affiche l'arbre selon un parcours prefixe
//         */
//        public void ParcoursPrefixe() {
//            System.out.println(getValeur());
//            if (getSousArbreGauche() != null)
//                getSousArbreGauche().ParcoursPrefixe();
//            if (getSousArbreDroit() != null)
//                getSousArbreDroit().ParcoursPrefixe();
        }
    private List <Stagiaire> arbreRecursifSearch (RandomAccessFile rafDataBase, Methods methods, List listSearch,
                                                  int criterion, String search) throws IOException {

        long knot = rafDataBase.getFilePointer();
        String temp = "";

        // Variables de l'Arbre
        rafDataBase.seek(knot+REF6);
        if (rafDataBase.readChar() != '$'){
            rafDataBase.seek(knot+REF6);
            String child = String.valueOf(rafDataBase.readChar());
            rafDataBase.seek(Long.parseLong(methods.removeDollarFromRef(rafDataBase, child)));
            arbreRecursifSearch(rafDataBase, methods, listSearch, criterion, search);
        }

        temp = "";
        rafDataBase.seek(knot);
        for (int i = 0; i < ELTROUGH; i++){
            temp+=rafDataBase.readChar();
        }

        Stagiaire stagiaireTemp = methods.createObjectStagiaire(temp);
        switch (criterion){
            case 1:
                String tempSurname = stagiaireTemp.getSurname();
                rafDataBase.seek(knot+REF9);
                char status = rafDataBase.readChar();
                if (methods.searchCharToChar(search, tempSurname) == 0 && status == 'V'){
                    listSearch.add(methods.createObjectStagiaire(temp));
                }
                break;
            case 2:
                String tempName = methods.createObjectStagiaire(temp).getName();
                rafDataBase.seek(knot+REF9);
                status = rafDataBase.readChar();
                if (methods.searchCharToChar(search, tempName) == 0 && status == 'V'){
                    listSearch.add(methods.createObjectStagiaire(temp));
                }
                break;
            case 3:
                String tempDept = methods.createObjectStagiaire(temp).getDept();
                rafDataBase.seek(knot+REF9);
                status = rafDataBase.readChar();
                if (methods.searchCharToChar(search, tempDept) == 0 && status == 'V'){
                    listSearch.add(methods.createObjectStagiaire(temp));
                }
                break;
            case 4:
                String tempPromo = methods.createObjectStagiaire(temp).getPromo();
                rafDataBase.seek(knot+REF9);
                status = rafDataBase.readChar();
                if (methods.searchCharToChar(search, tempPromo) == 0 && status == 'V'){
                    listSearch.add(methods.createObjectStagiaire(temp));
                }
                break;
            case 5:
                String tempYear = methods.createObjectStagiaire(temp).getYear();
                rafDataBase.seek(knot+REF9);
                status = rafDataBase.readChar();
                if (methods.searchCharToChar(search, tempYear) == 0 && status == 'V'){
                    listSearch.add(methods.createObjectStagiaire(temp));
                }
                break;
        }

        rafDataBase.seek(knot+REF7);
        if (rafDataBase.readChar() != '$'){
            rafDataBase.seek(knot+REF7);
            String child = String.valueOf(rafDataBase.readChar());
            rafDataBase.seek(Long.parseLong(methods.removeDollarFromRef(rafDataBase, child)));
            arbreRecursifSearch(rafDataBase, methods, listSearch, criterion, search);
        }
        return listSearch;
    }

}