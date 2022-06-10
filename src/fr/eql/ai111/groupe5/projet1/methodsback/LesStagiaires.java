package fr.eql.ai111.groupe5.projet1.methodsback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//TO DO
/**
 *
 * @author admtmp
 */
//Cette classe r�cup�re un fichier texte dans un vecteur
public class LesStagiaires {
    private BufferedReader bfr;

    //Constructeurs
    public LesStagiaires() {
        bfr = null;
    }

    public LesStagiaires(String nomFichier) {
        try {
            //Cr�ation d'un flux pour le fichier texte
            //le nom du fichier est pass� en argument
            FileReader in = new FileReader
                    ("C:\\Users\\sabri\\Workspace\\PROJET1GROUPE5\\src\\fr\\eql\\ai111\\groupe5\\projet1\\stagiaires.txt");
            bfr = new BufferedReader(in);
        } catch (IOException e) {
            System.out.println("Pb entr�e sortie :" + e.getMessage());
        }
    }

    // Transforme une chaine en un objet de type Stagiaire
    //format de la chaine : LEPANTE
    //                      Willy
    //                      95
    //                      AI 78
    //                      2010
    public Stagiaire fabriqueStagiaire(String chaine) {
        Stagiaire stg = null;
        StringTokenizer st = new StringTokenizer(chaine, "_");
        if (st.countTokens() == 5) {
            String surname = st.nextToken();
            String name = st.nextToken();
            String dept = st.nextToken();
            String promo = st.nextToken();
            String year = st.nextToken();
            stg = new Stagiaire(surname, name, dept, promo, year);
        }
        return stg;
    }

    //Transformer le fichier en une collection de stagiaires
    public List<Stagiaire> fabriqueList() {
        String chaine;
        Stagiaire stg;
        List<Stagiaire> stagiaires = new ArrayList<>();
        String stagiaireInfo = "";
        try {
            do {
                chaine = bfr.readLine();
                if (chaine != null) { //chaine non vide
                    //si chaine = * alors je recr�e une nouvelle chaine
                    //sinon
                    if(chaine.equals("*")){
                        stg = fabriqueStagiaire(stagiaireInfo);
                        stagiaires.add(stg);
                        stagiaireInfo="";
                    } else {
                        stagiaireInfo = stagiaireInfo.concat(chaine + "_");
                    }

                }
            } while (chaine != null);
        } catch (IOException e) {
            System.out.println("Probl�me de lecture : " + e.getMessage());
        }
        return stagiaires;
    }

    //Transforme le fichier en une chaine de caract�res form�e de plusieurs lignes
    public String fabriqueChaine() {
        StringBuffer chainebf = new StringBuffer();
        String chaine;
        try {
            do {
                chaine = bfr.readLine();
                if (chaine != null) {
                    chainebf.append(chaine + "*\n");
                }
            } while (chaine != null);
        } catch (IOException e) {
            System.out.println("Probl�me de lecture : " + e.getMessage());

        }
        return chainebf.toString();
    }
}
