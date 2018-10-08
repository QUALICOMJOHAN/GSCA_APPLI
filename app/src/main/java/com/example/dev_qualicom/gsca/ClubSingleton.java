package com.example.dev_qualicom.gsca;

public class ClubSingleton {

    private static ClubSingleton singleton = null;
    private String nom = "";
    private String url = "";

    private ClubSingleton() {

    }

    public synchronized static ClubSingleton getInstance() {
        if(singleton == null) singleton = new ClubSingleton();
        return singleton;
    }

    public void init(String nom, String url){

        this.nom = nom;
        this.url = url;

    }

    public String getNom() {
        return nom;
    }

    public String getUrl() {
        return url;
    }
}
