package fr.eql.ai111.groupe5.projet1.methodsback;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;

public class AddRaf {
    public static void main(String[] args) throws IOException {

                RandomAccessFile raf = new RandomAccessFile("c:/FolderProjet/raf.bin", "rw");

                String premier = "c ";
                String ajout = "b ";
                String ajout2 = "a ";
                String parent;
                int filsgauche;
                int filsdroit;
                int test;
                boolean wrote = false;

                raf.seek(0);
                int x = (int) raf.getFilePointer();


                raf.writeChars(premier);
                raf.seek(4);
                test = raf.read();




        do {
            // On commence au début du raf (la racine de l'arbre binaire)
            raf.seek(0);
            // On affecte à la variable String 'parent' ce qu'on lit dans le raf (n+p de la racine)
            parent = String.valueOf(raf.readChar());
            // On utilise la méthode compareTo pour voir si 'ajout' est plus petit ou plus grand que 'enfant'
            test = ajout.compareTo(parent);
            // Si 'enfant' est plus petit
            if (test < 0) {
                // On indique le chemin pris par 'ajout'
                System.out.println("gauche");
                raf.seek(parent.length());
                filsgauche = raf.read();
                if (filsgauche != 0) {
                    raf.seek(raf.read());
                    parent = String.valueOf(raf.readChar());
                } else {
                    raf.writeChars(String.valueOf(raf.length()));
                    raf.seek(raf.length());
                    raf.writeChars(ajout);
                    wrote = true;
                }
                // Si 'ajout' est plus grand
            } else if (test > 0) {
                System.out.println("droite");
                raf.seek(2);
                filsgauche = raf.read();
                if (filsgauche != 0) {
                    raf.seek(raf.readInt());
                    parent = String.valueOf(raf.readChar());
                } else {
                    raf.writeChars(String.valueOf(raf.length()));
                    System.out.println("on ajoute la ref de l'enfant");
                    System.out.println(raf.readChar());
                    raf.seek(raf.length());
                    raf.writeChars(ajout);
                    System.out.println("on écrit le prochain à la suite du raf");
                    wrote = true;
                }
            }

        } while (wrote=false);

        raf.seek(6);
        System.out.println(raf.readChar());

        Reader rdr = new FileReader("c:/FolderProjet/raf.bin");
        BufferedReader bf = new BufferedReader(rdr);

        String lecture = "";
        String lectureTotale = "";

        for (int i = 0; i < raf.length(); i+=2) {
            lecture = bf.readLine();
            lectureTotale += lecture;

        }

        System.out.println(lectureTotale);

    }
}
