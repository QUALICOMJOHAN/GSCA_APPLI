package com.example.dev_qualicom.gsca;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.HttpUrl;

public class Club_Log extends AppCompatActivity implements View.OnClickListener {

    Button log_club_btn;
    TextView login_club;

    SharedPreferences sharedPreferences;
    private static final String PREFS = "ID";
    private static final String PREFS_URL = "URL";
    private static final String PREFS_NAME_CLUB = "NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);

        if (sharedPreferences.contains(PREFS) && sharedPreferences.contains(PREFS_URL) && sharedPreferences.contains(PREFS_NAME_CLUB)) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String id = sharedPreferences.getString(PREFS, null);
                        String url = sharedPreferences.getString(PREFS_URL, null);
                        String name = sharedPreferences.getString(PREFS_NAME_CLUB, null);

                        ClubSingleton club = ClubSingleton.getInstance();

                        club.init(name, url);

                        WebService example = new WebService();

                        Membre membre = example.getMembre(id);

                        MembreSingleton membresingleton = MembreSingleton.getInstance();
                        membresingleton.init(membre);

                        Intent intent = new Intent(Club_Log.this, accueil.class);
                        startActivity(intent);

                        finish();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

            thread.start();

        }
        setContentView(R.layout.activity_club__log);

        log_club_btn = (Button) findViewById(R.id.log_club_btn);
        login_club = (TextView) findViewById(R.id.login_club);

        log_club_btn.setOnClickListener(Club_Log.this);

    }

    @Override
    public void onClick(View v) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                WebService example = new WebService();
                String response = null;

                HttpUrl.Builder urlBuilder = HttpUrl.parse("https://qualicom-conseil.com/GSCA_Teleconseil/webService/base_club.php").newBuilder();
                urlBuilder.addQueryParameter("login", login_club.getText().toString());
                String url = urlBuilder.build().toString();
                try {
                    response = example.run(url);

                    JSONObject json = new JSONObject(response);

                    ClubSingleton club = ClubSingleton.getInstance();

                    club.init(json.getString("login_club"), json.getString("nom_base"));

                    if (club.getUrl() != "") {

                        sharedPreferences
                                .edit()
                                .putString(PREFS_URL, json.getString("nom_base"))
                                .putString(PREFS_NAME_CLUB, json.getString("login_club"))
                                .apply();

                        Intent intent = new Intent(Club_Log.this, Login_Membre.class);
                        startActivity(intent);

                        finish();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();

    }

}

