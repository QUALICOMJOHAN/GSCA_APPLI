package com.example.dev_qualicom.gsca;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class accueil extends AppCompatActivity {
    
    MembreSingleton membreSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        membreSingleton = MembreSingleton.getInstance();

    }
}
