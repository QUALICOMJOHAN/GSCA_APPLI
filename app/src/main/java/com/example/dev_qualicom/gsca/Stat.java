package com.example.dev_qualicom.gsca;

public class Stat {

    String participation;
    String absence;
    String participation_pourcent;
    String parole;
    String nbtran;
    String ca;

    public Stat(String participation, String absence, String participation_pourcent, String parole, String nbtran, String ca) {
        this.participation = participation;
        this.absence = absence;
        this.participation_pourcent = participation_pourcent;
        this.parole = parole;
        this.nbtran = nbtran;
        this.ca = ca;
    }

    public String getParticipation() {
        return participation;
    }

    public String getAbsence() {
        return absence;
    }

    public String getParticipation_pourcent() {
        return participation_pourcent;
    }

    public String getParole() {
        return parole;
    }

    public String getNbtran() {
        return nbtran;
    }

    public String getCa() {
        return ca;
    }
}
