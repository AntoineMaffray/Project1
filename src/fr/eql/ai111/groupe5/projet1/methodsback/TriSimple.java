package fr.eql.ai111.groupe5.projet1.methodsback;

import fr.eql.ai111.groupe5.projet1.methodsback.Arbre;
import fr.eql.ai111.groupe5.projet1.methodsback.Methods;
import fr.eql.ai111.groupe5.projet1.methodsback.RAFException;
import fr.eql.ai111.groupe5.projet1.methodsback.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class TriSimple {

    //    CONSTANTES
    long REF1 = 0; // en bytes - emplacement de la référence du nom
    long REF2 = 102; // en bytes - emplacement de la référence du prénom
    long REF3 = 204; // en bytes - emplacement de la référence du département
    long REF4 = 212; // en bytes - emplacement de la référence de la promo
    long REF5 = 252; // en bytes - emplacement de la référence de l'année
    long REF6 = 262; // en bytes - emplacement de la référence de l'enfant gauche
    long REF7 = 280; // en bytes - emplacement de la référence de l'enfant droit
    long REF8 = 298; // en bytes - emplacement de la référence du parent
    long REF9 = 316; // en bytes - emplacement de la référence d'activation/désactivation
    long ELT = 320; // en bytes - taille intégrale d'un objet
    int ELTROUGH = 130; // en caractères - taille des informations (sans réf de structure)
    int ELTREF1 = 50; // en caractères - taille du nom
    int ELTREF2 = 50; // en caractères - taille du prénom
    int ELTREF3 = 3; // en caractères - taille du département
    int ELTREF4 = 19; // en caractères - taille de la promo
    int ELTREF5 = 4; // en caractères - taille de l'année

    public ObservableList <Stagiaire> searchByCriterion (int criterion1, String search1,
                                                         int criterion2, String search2,
                                                         int criterion3, String search3,
                                                         int criterion4, String search4,
                                                         int criterion5, String search5) throws RAFException, IOException {

        Methods methods = new Methods();
        Arbre arbre = new Arbre();
        RandomAccessFile rafDataBase = null;
        try {
            rafDataBase = new RandomAccessFile("C:/theEQLBook/Raf.bin", "rw");
        } catch (FileNotFoundException e) {
            throw new RAFException("Le fichier - C:/theEQLBook/Raf.bin/ - n'existe pas.", e);
        }
        ArrayList <Stagiaire> stList1 = new ArrayList<>(); ArrayList <Stagiaire> stList2 = new ArrayList<>();
        ArrayList <Stagiaire> stList3 = new ArrayList<>(); ArrayList <Stagiaire> stList4 = new ArrayList<>();
        ObservableList <Stagiaire> stListResult = FXCollections.observableArrayList();

        long ref2 = 0; int ref2T = 0; long ref3 = 0; int ref3T = 0;
        long ref4 = 0; int ref4T = 0; long ref5 = 0; int ref5T = 0;

        try {
            stList1 = (ArrayList<Stagiaire>) arbre.arbreParcoursSearch(criterion1, search1);
        } catch (IOException e) {
            throw new RAFException("Le fichier - C:/theEQLBook/Raf.bin/ - n'existe pas ou ne contient rien.", e);
        }

        long nbrObjets = 0;
        try {
            nbrObjets = rafDataBase.length()/ELT;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String objetTemp;
        int index;

        if (criterion2 > 0 && !search2.isEmpty()){
            switch (criterion2){
                case 1:
                    ref2 = REF1;
                    ref2T = ELTREF1;
                    break;
                case 2:
                    ref2 = REF2;
                    ref2T = ELTREF2;
                    break;
                case 3:
                    ref2 = REF3;
                    ref2T = ELTREF3;
                    break;
                case 4:
                    ref2 = REF4;
                    ref2T = ELTREF4;
                    break;
                case 5:
                    ref2 = REF5;
                    ref2T = ELTREF5;
                    break;
                case 0:
                    break;
            }

            for (index = 0; index < nbrObjets; index++){
                rafDataBase.seek(ref2 + (index*ELT));
                objetTemp = "";

                for (int j = 0; j <ref2T; j++){
                    objetTemp += rafDataBase.readChar();
                }

                if (methods.searchCharToChar(search2, objetTemp) == 0){
                    rafDataBase.seek(index*ELT+REF9);
                    char ref8 = rafDataBase.readChar();
                    if (ref8 == 'V'){
                        rafDataBase.seek(index*ELT);
                        objetTemp = "";
                        for (int j = 0; j <ELTROUGH; j++){
                            objetTemp+= rafDataBase.readChar();
                        }
                        Stagiaire stagiaireX = methods.createObjectStagiaire(objetTemp);
                        if (methods.isAlreadyInList(stList1, stagiaireX)){
                            stList2.add(stagiaireX);
                        }
                    }
                }
            }
        }
        else if (criterion2 == 0){
            for (Stagiaire st : stList1) {
                stListResult.add(st);
            }
        }

        if (criterion3 > 0 && !search3.isEmpty()){
            switch (criterion3){
                case 1:
                    ref3 = REF1;
                    ref3T = ELTREF1;
                    break;
                case 2:
                    ref3 = REF2;
                    ref3T = ELTREF2;
                    break;
                case 3:
                    ref3 = REF3;
                    ref3T = ELTREF3;
                    break;
                case 4:
                    ref3 = REF4;
                    ref3T = ELTREF4;
                    break;
                case 5:
                    ref3 = REF5;
                    ref3T = ELTREF5;
                    break;
                case 0:
                    break;
            }

            for (index = 0; index < nbrObjets; index++){
                rafDataBase.seek(ref3 + (index*ELT));
                objetTemp = "";

                for (int j = 0; j <ref3T; j++){
                    objetTemp += rafDataBase.readChar();
                }

                if (methods.searchCharToChar(search3, objetTemp) == 0){
                    rafDataBase.seek(index*ELT+REF9);
                    char ref8 = rafDataBase.readChar();
                    if (ref8 == 'V'){
                        rafDataBase.seek(index*ELT);
                        objetTemp = "";
                        for (int j = 0; j <ELTROUGH; j++){
                            objetTemp+= rafDataBase.readChar();
                        }
                        Stagiaire stagiaireX = methods.createObjectStagiaire(objetTemp);
                        if (methods.isAlreadyInList(stList2, stagiaireX)){
                            stList3.add(stagiaireX);
                        }
                    }
                }
            }
        }
        else if (criterion3 == 0){
            for (Stagiaire st : stList2) {
                stListResult.add(st);
            }
        }

        if (criterion4 > 0 && !search4.isEmpty()){
            switch (criterion4){
                case 1:
                    ref4 = REF1;
                    ref4T = ELTREF1;
                    break;
                case 2:
                    ref4 = REF2;
                    ref4T = ELTREF2;
                    break;
                case 3:
                    ref4 = REF3;
                    ref4T = ELTREF3;
                    break;
                case 4:
                    ref4 = REF4;
                    ref4T = ELTREF4;
                    break;
                case 5:
                    ref4 = REF5;
                    ref4T = ELTREF5;
                    break;
                case 0:
                    break;
            }

            for (index = 0; index < nbrObjets; index++){
                rafDataBase.seek(ref4 + (index*ELT));
                objetTemp = "";

                for (int j = 0; j <ref4T; j++){
                    objetTemp += rafDataBase.readChar();
                }

                if (methods.searchCharToChar(search4, objetTemp) == 0){
                    rafDataBase.seek(index*ELT+REF9);
                    char ref8 = rafDataBase.readChar();
                    if (ref8 == 'V'){
                        rafDataBase.seek(index*ELT);
                        objetTemp = "";
                        for (int j = 0; j <ELTROUGH; j++){
                            objetTemp+= rafDataBase.readChar();
                        }
                        Stagiaire stagiaireX = methods.createObjectStagiaire(objetTemp);
                        if (methods.isAlreadyInList(stList3, stagiaireX)){
                            stList4.add(stagiaireX);
                        }
                    }
                }
            }
        }
        else if (criterion4 == 0){
            for (Stagiaire st : stList3) {
                stListResult.add(st);
            }
        }

        if (criterion5 > 0 && !search5.isEmpty()){
            switch (criterion5){
                case 1:
                    ref5 = REF1;
                    ref5T = ELTREF1;
                    break;
                case 2:
                    ref5 = REF2;
                    ref5T = ELTREF2;
                    break;
                case 3:
                    ref5 = REF3;
                    ref5T = ELTREF3;
                    break;
                case 4:
                    ref5 = REF4;
                    ref5T = ELTREF4;
                    break;
                case 5:
                    ref5 = REF5;
                    ref5T = ELTREF5;
                    break;
                case 0:
                    break;
            }

            for (index = 0; index < nbrObjets; index++){
                rafDataBase.seek(ref5 + (index*ELT));
                objetTemp = "";

                for (int j = 0; j <ref5T; j++){
                    objetTemp += rafDataBase.readChar();
                }

                if (methods.searchCharToChar(search5, objetTemp) == 0){
                    rafDataBase.seek(index*ELT+REF9);
                    char ref8 = rafDataBase.readChar();
                    if (ref8 == 'V'){
                        rafDataBase.seek(index*ELT);
                        objetTemp = "";
                        for (int j = 0; j <ELTROUGH; j++){
                            objetTemp+= rafDataBase.readChar();
                        }
                        Stagiaire stagiaireX = methods.createObjectStagiaire(objetTemp);
                        if (methods.isAlreadyInList(stList4, stagiaireX)){
                            stListResult.add(stagiaireX);
                        }
                    }
                }
            }
        }
        else if (criterion5 == 0){
            for (Stagiaire st : stList4) {
                stListResult.add(st);
            }
        }

        rafDataBase.close();
        System.out.println(stListResult);
        stListResult.sorted();
        return stListResult;
    }
}

