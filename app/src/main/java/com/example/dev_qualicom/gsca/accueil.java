package com.example.dev_qualicom.gsca;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class accueil extends AppCompatActivity {

    ImageButton btn_membre;
    ImageButton btn_event;
    ImageButton btn_document;
    ImageButton btn_stat;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    FloatingActionButton disconnect;

    MembreSingleton membreSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        btn_membre = (ImageButton) findViewById(R.id.members);
        btn_event = (ImageButton) findViewById(R.id.events);
        btn_document = (ImageButton) findViewById(R.id.docs);
        btn_stat = (ImageButton) findViewById(R.id.stats);

        disconnect = findViewById(R.id.disconnect);

        btn_membre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(accueil.this, Listmembers.class);
                startActivity(intent);

            }
        });

        btn_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(accueil.this, list_events.class);
                startActivity(intent);

            }
        });

        btn_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(accueil.this, list_docs.class);
                startActivity(intent);

            }
        });

        btn_stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(accueil.this, stats.class);
                startActivity(intent);

            }
        });

        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSharedPreferences = getSharedPreferences("ID", MODE_PRIVATE);
                mEditor = mSharedPreferences.edit();

                mEditor.remove("ID");
                mEditor.remove("URL");
                mEditor.remove("NAME");

                mEditor.apply();


                Intent intent = new Intent(accueil.this, Club_Log.class);
                startActivity(intent);

                finish();

            }
        });

        membreSingleton = MembreSingleton.getInstance();

    }
}
