package com.example.dev_qualicom.gsca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class accueil extends AppCompatActivity {

    ImageButton btn_membre;

    MembreSingleton membreSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        btn_membre = (ImageButton) findViewById(R.id.members);

        btn_membre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(accueil.this, Listmembers.class);
                startActivity(intent);

            }
        });

        membreSingleton = MembreSingleton.getInstance();

    }
}
