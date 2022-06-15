package fr.eql.ai111.groupe5.projet1.methodsback;

public class User {

        private String surname;
        private String name;
        private String login;
        private String password;
        private String role;

        // Constructeur utilis� pour les m�thodes de bases //
        public User(String surname, String name, String login, String password, String role) {
            this.surname = surname;
            this.name = name;
            this.login = login;
            this.password = password;
            this.role = role;
        }

        // Constructeur pour l'affichage du login //
    public User(String surname, String name, String login, String password) {
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    // Constructeur pour la connexion //
    public User(String login) {
        this.login = login;
    }


    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getLogin() { return login; }

    public String getPassword() { return password; }

    public String getRole() { return role; }

    public void setLogin(String login) {
        this.login = login;
    }
}

