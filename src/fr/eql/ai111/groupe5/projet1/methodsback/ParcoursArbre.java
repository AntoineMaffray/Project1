//package fr.eql.ai111.groupe5.projet1;
//
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//
//import java.io.IOException;
//import java.io.RandomAccessFile;
//
//public class ParcoursArbre {
//
//    long ref6 = 260; // en bytes
//    long ref7 = 278; // en bytes
//    long ref8 = 296; // en bytes -
//    long elt = 300; // en bytes
//    int eltrough = 130; // en caract�res
//
//
//    public static void main(String[] args) throws IOException {
//
//        Methods methods = new Methods();
//
//        long ref6 = 260; // en bytes
//        long ref7 = 278; // en bytes
//        long ref8 = 296; // en bytes
//        long elt = 318; // en bytes
//        int eltrough = 130; // en caract�res
//        int nameSurname = 130; // en caract�res
//
//        RandomAccessFile rafDataBase = new RandomAccessFile("C:/FolderProjet/Raf.bin", "rw");
//
//
//        // variable de la comparaison lors de la remont�e
//        String origin = "";
//        // variable de comparaison de base
//        long baseNode = 0;
//        // Trucs d'avant
//        char removeDollars = 0;
//        String child = "0";
//        String wrote = "";
//        String refBaseNode = "";
//        String ajoutListe = "";
//        String nomEnfant = "";
//        String nomParent = "";
//        ObservableList<Stagiaire> listShow = FXCollections.observableArrayList();
//        String way = "down";
//
//
//        do {
//
//        if (way.equals("down")) {
//        rafDataBase.seek(baseNode + ref6);
//        child = String.valueOf(rafDataBase.readChar());
//
//        // Si un enfant existe � l'emplacement inf�rieur, on y va
//        if (!child.equals("$")) {
//            rafDataBase.seek(baseNode+ref6);
//            refBaseNode = "";
//            baseNode = Long.parseLong(methods.removeDollarFromRef(rafDataBase, removeDollars, refBaseNode));
//            // S'il n'y a pas d'enfant � cet emplacement
//        } else if (child.equals("$")) {
//            // On ajoute l'�l�ment � la liste � la liste
//            rafDataBase.seek(baseNode);
//            ajoutListe = "";
//            for (int i = 0; i < eltrough; i++) {
//                ajoutListe += rafDataBase.readChar();
//            }
//            listShow.add(methods.createObjectStagiaire(ajoutListe));
//            // Puis on v�rifie s'il a un enfant droit
//            rafDataBase.seek(baseNode + ref7);
//            child = String.valueOf(rafDataBase.readChar());
//
//
//            if (!child.equals("$")) {
//                // Si c'est le cas, on renvoie le nouveau n?ud dans la boucle
//                rafDataBase.seek(baseNode + ref7);
//                refBaseNode = "";
//                baseNode = Long.parseLong(methods.removeDollarFromRef(rafDataBase, removeDollars, refBaseNode));
//            } else if (child.equals("$")) {
//                // S'il n'a pas d'enfant sup�rieur, on active la remont�e EN STOCKANT LE NOM DUQUEL ON VIENT DANS UNE VARIABLE
//                way = "up";
//                rafDataBase.seek(baseNode);
//                nomEnfant = "";
//                for (int i = 0; i < nameSurname; i++) {
//                    nomEnfant += rafDataBase.readChar();
//                }
//                rafDataBase.seek(baseNode + ref8);
//                refBaseNode = "";
//                baseNode = Long.parseLong(methods.removeDollarFromRef(rafDataBase, removeDollars, refBaseNode));
//                rafDataBase.seek(baseNode);
//                nomParent = "";
//                for (int i = 0; i < nameSurname; i++) {
//                    nomParent += rafDataBase.readChar();
//                }
////                System.out.println(nomEnfant + nomParent );
//            }
//        }
//
//            }
//
//                if (way.equals("up")) {
//                    // Puis on fait la comparaison pour savoir si on vient de l'enfant inf�rieur ou sup�rieur
//                    if (methods.simpleComparison(nomEnfant, nomParent) > 0) {
//                        rafDataBase.seek(baseNode);
//                        nomEnfant = "";
//                        for (int i = 0; i < nameSurname; i++) {
//                            nomEnfant += rafDataBase.readChar();
//                        }
////                        System.out.println(nomEnfant);
//                        rafDataBase.seek(baseNode + ref8);
//                        refBaseNode = "";
//                            baseNode = Long.parseLong(methods.removeDollarFromRef(rafDataBase, removeDollars, refBaseNode));
//                        rafDataBase.seek(baseNode);
//                        nomParent = "";
//                        for (int i = 0; i < nameSurname; i++) {
//                            nomParent += rafDataBase.readChar();
//                        }
////                        System.out.println(nomParent);
//                } else if (methods.simpleComparison(nomEnfant, nomParent) < 0) {
//                        // On ajoute � la liste
//                        rafDataBase.seek(baseNode);
//                        ajoutListe = "";
//                        for (int i = 0; i < eltrough; i++) {
//                            ajoutListe += rafDataBase.readChar();
//                        }
//                        listShow.add(methods.createObjectStagiaire(ajoutListe));
//                    // On v�rifie s'il a un enfant droit
//                    rafDataBase.seek((baseNode + ref7));
//                    child = String.valueOf(rafDataBase.readChar());
//
//                    if (!child.equals("$")) {
//                        // Si c'est le cas, on renvoie le nouveau n?ud dans la boucle
//                        way = "down";
//                        rafDataBase.seek(baseNode+ref7);
//                        refBaseNode = "";
//                        baseNode = Long.parseLong(methods.removeDollarFromRef(rafDataBase, removeDollars, refBaseNode));
//                        // Sinon on remonte �galement
//                    } else if (child.equals("$")) {
//                        rafDataBase.seek(baseNode);
//                        nomEnfant = "";
//                        for (int i = 0; i < nameSurname; i++) {
//                            nomEnfant += rafDataBase.readChar();
//                        }
////                        System.out.println(nomEnfant);
//                        rafDataBase.seek(baseNode + ref8);
//                        refBaseNode = "";
//                        baseNode = Long.parseLong(methods.removeDollarFromRef(rafDataBase, removeDollars, refBaseNode));
//                        rafDataBase.seek(baseNode);
//                        nomParent = "";
//                        for (int i = 0; i < nameSurname; i++) {
//                            nomParent += rafDataBase.readChar();
//                        }
//                    }
//                } else if (methods.simpleComparison(nomEnfant, nomParent) == 0) {
//                        // On n'ajoute pas de doublons dans la liste
//                    }
//                }
//
//        } while (refBaseNode.equals(""));
//    }
//
//}
