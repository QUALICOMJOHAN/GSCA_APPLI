package com.example.dev_qualicom.gsca;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

                    if(club.getUrl() != ""){
                        Intent intent = new Intent(Club_Log.this, Login_Membre.class);
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

