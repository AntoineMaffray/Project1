package fr.eql.ai111.groupe5.projet1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

public class LauncherTest {

    private static final Logger logger  = LogManager.getLogger();

    public static void main(String[] args) throws IOException {

            Execute execute = new Execute();
            execute.executeAtStart();

//        arbre.arbreSearch(JOptionPane.showInputDialog("Recherche par nom : "));

//        String search1 = JOptionPane.showInputDialog("Nom").toUpperCase();
//        String temp = JOptionPane.showInputDialog("Prénom");
//        String search2 = temp.substring(0,1).toUpperCase()+temp.substring(1).toLowerCase();
//        String search3 = JOptionPane.showInputDialog("Département").toUpperCase();
//        String search4 = JOptionPane.showInputDialog("Promo").toUpperCase();
//        String search5 = JOptionPane.showInputDialog("Année").toUpperCase();
//        triSimple.searchByCriterion(2, search1, 1, search2, 3, search3, 0, "", 0, "");

//        String surname = JOptionPane.showInputDialog("Nom à ajouter");
//        String name = JOptionPane.showInputDialog("Prénom à ajouter");
//        String dept = JOptionPane.showInputDialog("Département à ajouter");
//        String promo = JOptionPane.showInputDialog("Promo à ajouter");
//        String year = JOptionPane.showInputDialog("Année à ajouter");
//        arbre.arbreInsertionNew(rafExecute.createListOneStagiaire(surname, name, dept, promo, year));
//
//        arbre.arbreSearch(JOptionPane.showInputDialog("Recherche par nom : "));

//        String search = JOptionPane.showInputDialog("Quelle personne voulez-vous modifier?");
//        String surname = JOptionPane.showInputDialog(arbre.arbreSearch(search)+"\r\nModification du nom");
//        String name = JOptionPane.showInputDialog(arbre.arbreSearch(search)+"\r\nModification du prénom");
//        String dept = JOptionPane.showInputDialog(arbre.arbreSearch(search)+"\r\nModification du département");
//        String promo = JOptionPane.showInputDialog(arbre.arbreSearch(search)+"\r\nModification de la promo");
//        String year = JOptionPane.showInputDialog(arbre.arbreSearch(search)+"\r\nModification de l'année");
//        arbre.arbreModification(search, (rafExecute.createStringOneStagiaire(surname, name, dept, promo, year)));
//        arbre.arbreSearch(JOptionPane.showInputDialog("Personne à rechercher"));
//
//        try {
//            arbre.arbreParcours();
//        } catch (IOException e) {
//            logger.warn("Le RAF ne fonctionne pas.");
//        }

//        String search1 = JOptionPane.showInputDialog("Nom").toUpperCase();
//        System.out.println(arbre.arbreParcoursSearch(1, search1));

    }
}
