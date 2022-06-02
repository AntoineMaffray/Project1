package fr.eql.ai111.groupe5.projet1;

public class Stagiaire {

    private String surname;
    private String name;
    private String dept;
    private String promo;
    private String year;

    public Stagiaire(String surname, String name, String dept, String promo, String year) {
        this.surname = surname;
        this.name = name;
        this.dept = dept;
        this.promo = promo;
        this.year = year;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getPromo() {
        return promo;
    }

    public String getYear() {
        return year;
    }
}
