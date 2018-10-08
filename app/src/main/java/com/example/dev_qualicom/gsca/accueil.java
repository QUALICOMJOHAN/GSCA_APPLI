package com.example.dev_qualicom.gsca;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class accueil extends AppCompatActivity {

    TextView nom_contact;
    TextView prenom_contact;
    MembreSingleton membreSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);


        nom_contact = (TextView) findViewById(R.id.nom_contact);
        prenom_contact = (TextView) findViewById(R.id.prenom_contact);

        membreSingleton = MembreSingleton.getInstance();

        nom_contact.setText(membreSingleton.getMembre().getNom_contact());
        prenom_contact.setText(membreSingleton.getMembre().getPrenom_contact());
    }
}
