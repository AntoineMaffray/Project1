package fr.eql.ai111.groupe5.projet1.methodsback;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestDeTrucs {
    public static void main(String[] args) throws IOException {

        FileReader rdr = new FileReader ("Identifiants/Persistance/Login.txt");
        int getLogin = 0;
        String login = "";
        while ((getLogin = rdr.read()) != -1) {
            login += (char) getLogin;
        }
        rdr.close();
    }
}
