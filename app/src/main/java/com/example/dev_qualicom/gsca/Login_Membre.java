package com.example.dev_qualicom.gsca;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.HttpUrl;

public class Login_Membre extends AppCompatActivity implements View.OnClickListener{

    Button login;
    TextView email;
    TextView pass;
    ClubSingleton club;
    SharedPreferences sharedPreferences;
    private static final String PREFS = "ID";
    WebService example = new WebService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__membre);

        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);

        login = (Button) findViewById(R.id.connexion);
        email = (TextView) findViewById(R.id.membre_mail);
        pass = (TextView) findViewById(R.id.membre_pass);

        club = ClubSingleton.getInstance();

        login.setOnClickListener(Login_Membre.this);

    }

    @Override
    public void onClick(View v) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                String response = null;

                HttpUrl.Builder urlBuilder = HttpUrl.parse(club.getUrl()+"loginMobil.php").newBuilder();
                urlBuilder.addQueryParameter("login", email.getText().toString());
                urlBuilder.addQueryParameter("pass", pass.getText().toString());
                String url = urlBuilder.build().toString();
                try {

                    response = example.run(url);

                    JSONObject json = new JSONObject(response);

                    if(json.getString("id") != ""){

                        Membre membre = example.getMembre(json.getString("id"));
                        MembreSingleton membresingleton = MembreSingleton.getInstance();
                        membresingleton.init(membre);

                        sharedPreferences
                                .edit()
                                .putString(PREFS, json.getString("id"))
                                .apply();

                        example.setToken(FirebaseInstanceId.getInstance().getToken(), membre);

                        Intent intent = new Intent(Login_Membre.this, accueil.class);
                        startActivity(intent);
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
