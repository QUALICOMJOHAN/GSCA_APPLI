package com.example.dev_qualicom.gsca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class invites extends AppCompatActivity {

    TextView nom_invite1;
    TextView prenom_invite1;
    TextView societe_invite1;
    TextView email_invite1;

    TextView nom_invite2;
    TextView prenom_invite2;
    TextView societe_invite2;
    TextView email_invite2;

    TextView nom_invite3;
    TextView prenom_invite3;
    TextView societe_invite3;
    TextView email_invite3;

    Button bnt_valide;

    OkHttpClient client;
    ClubSingleton club;
    MembreSingleton membre;

    int idevent;
    Event_Class event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invites);

        client = new OkHttpClient();
        club = ClubSingleton.getInstance();
        membre = MembreSingleton.getInstance();

        nom_invite1 = findViewById(R.id.nom_invite1);
        prenom_invite1 = findViewById(R.id.prenom_invite1);
        societe_invite1 = findViewById(R.id.societe_invite1);
        email_invite1 = findViewById(R.id.email_invite1);

        nom_invite2 = findViewById(R.id.nom_invite2);
        prenom_invite2 = findViewById(R.id.prenom_invite2);
        societe_invite2 = findViewById(R.id.societe_invite2);
        email_invite2 = findViewById(R.id.email_invite2);

        nom_invite3 = findViewById(R.id.nom_invite3);
        prenom_invite3 = findViewById(R.id.prenom_invite3);
        societe_invite3 = findViewById(R.id.societe_invite3);
        email_invite3 = findViewById(R.id.email_invite3);

        bnt_valide = findViewById(R.id.button);

        idevent = getIntent().getIntExtra("id", 0);

        bnt_valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HttpUrl.Builder urlBuilder = HttpUrl.parse(club.getUrl() + "webService/setReponseEventInvite.php").newBuilder();
                urlBuilder.addQueryParameter("id", Integer.toString(idevent));
                urlBuilder.addQueryParameter("id_membre", Integer.toString(membre.getMembre().getId()));
                urlBuilder.addQueryParameter("rep", "oui");
                urlBuilder.addQueryParameter("nom_invite1", nom_invite1.getText().toString());
                urlBuilder.addQueryParameter("prenom_invite1", prenom_invite1.getText().toString());
                urlBuilder.addQueryParameter("email_invite1", email_invite1.getText().toString());
                urlBuilder.addQueryParameter("societe_invite1", societe_invite1.getText().toString());
                urlBuilder.addQueryParameter("nom_invite2", nom_invite2.getText().toString());
                urlBuilder.addQueryParameter("prenom_invite2", prenom_invite2.getText().toString());
                urlBuilder.addQueryParameter("email_invite2", email_invite2.getText().toString());
                urlBuilder.addQueryParameter("societe_invite2", societe_invite2.getText().toString());
                urlBuilder.addQueryParameter("nom_invite3", nom_invite3.getText().toString());
                urlBuilder.addQueryParameter("prenom_invite3", prenom_invite3.getText().toString());
                urlBuilder.addQueryParameter("email_invite3", email_invite3.getText().toString());
                urlBuilder.addQueryParameter("societe_invite3", societe_invite3.getText().toString());

                String url = urlBuilder.build().toString();
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {


                        HttpUrl.Builder urlBuilder = HttpUrl.parse(club.getUrl() + "webService/getEvent.php").newBuilder();
                        urlBuilder.addQueryParameter("id", Integer.toString(idevent));
                        urlBuilder.addQueryParameter("id_membre", Integer.toString(membre.getMembre().getId()));

                        String url = urlBuilder.build().toString();
                        Request request = new Request.Builder()
                                .url(url)
                                .build();

                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                Gson gson = new Gson();
                                String json = response.body().string().toString();
                                event = gson.fromJson(json, Event_Class.class);

                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                                try {
                                    Date datestart = df.parse(event.getStart().toString());
                                    Date dateend = df.parse(event.getEnd().toString());

                                    Calendar cal = Calendar.getInstance();
                                    Intent intent1 = new Intent(Intent.ACTION_EDIT);
                                    intent1.setType("vnd.android.cursor.item/event");
                                    intent1.putExtra("beginTime", datestart.getTime());
                                    intent1.putExtra("allDay", false);
                                    intent1.putExtra("rrule", "FREQ=YEARLY");
                                    intent1.putExtra("endTime", dateend.getTime());
                                    intent1.putExtra("title", event.getTitle().toString());
                                    startActivityForResult(intent1, 1);

                                    finish();

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }



                            }
                        });

                    }
                });

            }
        });
    }
}
