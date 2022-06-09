package fr.eql.ai111.groupe5.projet1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.JOptionPane;
import java.io.IOException;

public class LauncherTest {

    private static final Logger logger  = LogManager.getLogger();
    public static void main(String[] args) throws IOException {

        Execute execute = new Execute();
        MethodsConnexion methodsConnexion = new MethodsConnexion();

        execute.executeAtStart();

//        String loginToEdit = JOptionPane.showInputDialog("Quel compte voulez-vous modifier?");
//        FileReader fr = new FileReader("C://BillyBook/AdminInfo/"+loginToEdit+".txt");
//        BufferedReader bw = new BufferedReader(fr);
//
//        String message = "Login : " + loginToEdit + ", ";
//        bw.readLine();
//        message+= "Nom : " + bw.readLine() + ", ";
//        message+= "Prénom : " + bw.readLine();
//        bw.close();
//        fr.close();
//
//        String loginToAdd = JOptionPane.showInputDialog(message + "\r\n Entrez le nouveau Login.");
//        String surnameToAdd = JOptionPane.showInputDialog(message + "\r\n Entrez le nouveau Nom.");
//        String nameToAdd = JOptionPane.showInputDialog(message + "\r\n Entrez le nouveau Prénom.");
//        String passwordToAdd = JOptionPane.showInputDialog("Entrez votre nouveau mot de passe.");
//        String passwordToAdd2 = JOptionPane.showInputDialog("Vérifiez votre nouveau mot de passe.");
//
//        if (passwordToAdd.equals(passwordToAdd2)){
//            connection.editAdminProfile(loginToEdit, surnameToAdd, nameToAdd, loginToAdd, passwordToAdd);
//        } else {
//            do{
//                JOptionPane.showMessageDialog(null, "Les deux mots de passe entrés ne sont pas identiques.\r\n" +
//                        "Veuillez recommencer votre saisie.");
//                passwordToAdd = JOptionPane.showInputDialog("Entrez votre nouveau mot de passe.");
//                passwordToAdd2 = JOptionPane.showInputDialog("Vérifiez votre nouveau mot de passe.");
//            } while(!passwordToAdd.equals(passwordToAdd2));
//            connection.editAdminProfile(loginToEdit, surnameToAdd, nameToAdd, loginToAdd, passwordToAdd);
//        }

        String loginToDelete = JOptionPane.showInputDialog("Quel compte administrateur voulez-vous supprimer? Entrez le Login du compte.");
        methodsConnexion.removeAdminProfile(loginToDelete);


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
