package com.example.dev_qualicom.gsca;

public class Doc {

    String nom;
    String date;
    String chemin;

    public Doc(String nom, String date, String chemin) {

        this.nom = nom;
        this.date = date;
        this.chemin = chemin;
    }

    public String getChemin() {
        return chemin;
    }

    public String getNom() {
        return nom;
    }

    public String getDate() {
        return date;
    }

}
