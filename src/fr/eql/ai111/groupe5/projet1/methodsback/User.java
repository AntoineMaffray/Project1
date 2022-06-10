package fr.eql.ai111.groupe5.projet1.methodsback;

public class User {

        private String surname;
        private String name;
        private String login;
        private String password;
        private String role;


        public User(String surname, String name, String login, String password, String role) {
            this.surname = surname;
            this.name = name;
            this.login = login;
            this.password = password;
            this.role = role;
        }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
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
}

