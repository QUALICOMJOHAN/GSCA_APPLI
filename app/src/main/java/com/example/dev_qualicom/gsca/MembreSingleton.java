package com.example.dev_qualicom.gsca;

public class MembreSingleton {

    private static MembreSingleton singleton = null;
    private Membre membre;
    private MembreSingleton() {

    }

    public synchronized static MembreSingleton getInstance() {
        if(singleton == null) singleton = new MembreSingleton();
        return singleton;
    }

    public void init(Membre membre){

        this.membre = membre;

    }

    public Membre getMembre() {
        return membre;
    }
}
