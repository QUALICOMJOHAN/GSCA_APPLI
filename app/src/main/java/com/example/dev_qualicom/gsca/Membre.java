package com.example.dev_qualicom.gsca;

public class
Membre {

    private int id;
    private String img_logo;
    private String nom_societe;
    private String prenom_contact;
    private String des_societe;
    private String adresse_societe;
    private String tel_societe;
    private String mail_societe;
    private String img_profil;
    private String nom_contact;
    private String statut_contact;
    private String tel_contact;
    private String mail_contact;
    private String secteur_activite;
    private String activite_principal;
    private String site_societe;

    public Membre(int id, String img_logo, String nom_societe, String prenom_contact, String des_societe, String adresse_societe, String tel_societe, String mail_societe, String img_profil, String nom_contact, String statut_contact, String tel_contact, String mail_contact, String secteur_activite, String activite_principal, String site_societe) {
        this.id = id;
        this.img_logo = img_logo;
        this.nom_societe = nom_societe;
        this.prenom_contact = prenom_contact;
        this.des_societe = des_societe;
        this.adresse_societe = adresse_societe;
        this.tel_societe = tel_societe;
        this.mail_societe = mail_societe;
        this.img_profil = img_profil;
        this.nom_contact = nom_contact;
        this.statut_contact = statut_contact;
        this.tel_contact = tel_contact;
        this.mail_contact = mail_contact;
        this.secteur_activite = secteur_activite;
        this.activite_principal = activite_principal;
        this.site_societe = site_societe;
    }

    public int getId() {
        return id;
    }

    public String getImg_logo() {
        return img_logo;
    }

    public String getNom_societe() {
        return nom_societe;
    }

    public String getPrenom_contact() {
        return prenom_contact;
    }

    public String getDes_societe() {
        return des_societe;
    }

    public String getAdresse_societe() {
        return adresse_societe;
    }

    public String getTel_societe() {
        return tel_societe;
    }

    public String getMail_societe() {
        return mail_societe;
    }

    public String getImg_profil() {
        return img_profil;
    }

    public String getNom_contact() {
        return nom_contact;
    }

    public String getStatut_contact() {
        return statut_contact;
    }

    public String getTel_contact() {
        return tel_contact;
    }

    public String getMail_contact() {
        return mail_contact;
    }

    public String getSecteur_activite() {
        return secteur_activite;
    }

    public String getActivite_principal() {
        return activite_principal;
    }

    public String getSite_societe() {
        return site_societe;
    }

}
