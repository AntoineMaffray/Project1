package fr.eql.ai111.groupe5.projet1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.JOptionPane;

public class Connexion {

    User user;
    String menuInput = "";
    String message ="";
    UserDAO dao = new UserDAO();

    public void execute() {
        JOptionPane.showMessageDialog(null, "Bienvenue dans l'annuaire!");
        // Je boucle sur les menus utilisateur tant que l'utilisateur ne souhaite pas quitter l'application
        do {
            registerUser();
            showMenu();

            switch (menuInput) {
                case "i":
                    displayUser();
                    break;
                case "q":
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Mauvaise option.");
            }
        } while (!menuInput.equals("q"));
        JOptionPane.showMessageDialog(null, "Au revoir et à bientôt.");
    }

    private void registerUser() {
        String login;
        String password;
        // On boucle jusqu'à ce qu'un reader enregistré se connecte.
        do {
            String input = JOptionPane.showInputDialog("S'inscrire (i) ou se connecter (c)?");
            switch (input) {
                case "i":
                    String name = JOptionPane.showInputDialog("Veuillez renseigner votre prénom");
                    String surname = JOptionPane.showInputDialog("Veuillez renseigner votre nom");
                    login = JOptionPane.showInputDialog("Veuillez renseigner votre identifiant");
                    password = JOptionPane.showInputDialog("Veuillez renseigner votre mot de passe");
                    String statut = JOptionPane.showInputDialog("Etes vous un utilisateur (u), administrateur (a), " +
                            "super-administrateur (s)?");
                    /*
                    Si la méthode 'createAccount' retourne false, alors le fichier utilisateur
                    n'as pas été créé car un autre portant le même nom (correspondant au login
                    entré) existe déjà.
                     */
                    boolean isCreated = dao.createAccount(name, surname, login, password, statut);
                    if (!isCreated) {
                        JOptionPane.showMessageDialog(null,
                                "Cet identifiant n'est pas disponible. " +
                                        "Veuillez en choisir un autre.");
                    }
                    break;
                case "c":
                    login = JOptionPane.showInputDialog("Veuillez renseigner votre identifiant");
                    password = JOptionPane.showInputDialog("Veuillez renseigner votre mot de passe");
                    user = dao.connect(login, password);
                    if (user == null) {
                        JOptionPane.showMessageDialog(null,
                                "Vos identifants et mot de passe sont incorrect(s)");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Bienvenue " + user.getName() + " " + user.getSurname());
                    }
                    break;
                default:
            }
        } while (user == null);
    }

    private void showMenu() {
        message = "Veuillez selectionner une option:" +
                "\r\nAfficher fiche utilisateur (i)" +
                "\r\nEnregistrer des livres (e)";
        message += "\r\nQuitter (q)";
        menuInput = JOptionPane.showInputDialog(message);

    }


    private void displayUser() {
        JOptionPane.showMessageDialog(null, "Fiche utilisateur :" +
                "\r\nPrénom : " + user.getName() +
                "\r\nNom : " + user.getSurname());
    }

}

