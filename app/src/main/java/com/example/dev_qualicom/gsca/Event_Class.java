package com.example.dev_qualicom.gsca;

public class Event_Class {

        private int id;
        private String title;
        private String start;
        private String end;
        private String backgroundColor;
        private String desc_event;
        private String lieu_event;
        private String adresse_event;
        private String nb_event;
        private String prix_event;
        private String real_end;
        private String id_membre;
        private String statut;
        private String id_invitation;
        private String token;


    public Event_Class(int id, String title, String start, String end, String backgroundColor, String desc_event, String lieu_event, String adresse_event, String nb_event, String prix_event, String real_end, String id_membre, String statut, String id_invitation, String token) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.backgroundColor = backgroundColor;
        this.desc_event = desc_event;
        this.lieu_event = lieu_event;
        this.adresse_event = adresse_event;
        this.nb_event = nb_event;
        this.prix_event = prix_event;
        this.real_end = real_end;
        this.id_membre = id_membre;
        this.statut = statut;
        this.id_invitation = id_invitation;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getDesc_event() {
        return desc_event;
    }

    public String getLieu_event() {
        return lieu_event;
    }

    public String getAdresse_event() {
        return adresse_event;
    }

    public String getNb_event() {
        return nb_event;
    }

    public String getPrix_event() {
        return prix_event;
    }

    public String getReal_end() {
        return real_end;
    }

    public String getId_membre() {
        return id_membre;
    }

    public String getStatut() {
        return statut;
    }

    public String getId_invitation() {
        return id_invitation;
    }

    public String getToken() {
        return token;
    }
}
