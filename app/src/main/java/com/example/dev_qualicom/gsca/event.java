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

public class event extends AppCompatActivity {

    TextView event_name;
    TextView desc_event;
    TextView lieu_event;
    TextView add_event;
    TextView debut_event;
    TextView prix_event;
    TextView fin_event;
    TextView nb_places;

    Button oui;
    Button non;
    Button avec_invite;

    ClubSingleton club;
    MembreSingleton membre;
    int idevent;
    Event_Class event;

    OkHttpClient client;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent intent = new Intent(event.this, list_events.class);
        startActivity(intent);

        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        club = ClubSingleton.getInstance();
        membre = MembreSingleton.getInstance();
        client = new OkHttpClient();

        idevent = getIntent().getIntExtra("id", 0);

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

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        event_name = findViewById(R.id.event_name);
                        desc_event = findViewById(R.id.desc_event);
                        lieu_event = findViewById(R.id.lieu_event);
                        add_event = findViewById(R.id.add_event);
                        debut_event = findViewById(R.id.debut_event);
                        prix_event = findViewById(R.id.prix_event);
                        fin_event = findViewById(R.id.fin_event);
                        nb_places = findViewById(R.id.nb_places);

                        oui = findViewById(R.id.oui);
                        non = findViewById(R.id.non);
                        avec_invite = findViewById(R.id.avec_invite);

                        event_name.setText(event.getTitle());
                        desc_event.setText(event.getDesc_event());
                        lieu_event.setText(event.getLieu_event());
                        add_event.setText(event.getAdresse_event());
                        debut_event.setText(event.getStart());
                        prix_event.setText(event.getPrix_event());
                        fin_event.setText(event.getEnd());
                        nb_places.setText(event.getNb_event());

                        oui.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                HttpUrl.Builder urlBuilder = HttpUrl.parse(club.getUrl() + "webService/setReponseEvent.php").newBuilder();
                                urlBuilder.addQueryParameter("id", Integer.toString(idevent));
                                urlBuilder.addQueryParameter("id_membre", Integer.toString(membre.getMembre().getId()));
                                urlBuilder.addQueryParameter("rep", "oui");

                                String url = urlBuilder.build().toString();
                                Request request = new Request.Builder()
                                        .url(url)
                                        .build();

                                client.newCall(request).enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {}
                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {

                                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                                        try {
                                            Date datestart = df.parse(debut_event.getText().toString());
                                            Date dateend = df.parse(fin_event.getText().toString());

                                            Calendar cal = Calendar.getInstance();
                                            Intent intent1 = new Intent(Intent.ACTION_EDIT);
                                            intent1.setType("vnd.android.cursor.item/event");
                                            intent1.putExtra("beginTime", datestart.getTime());
                                            intent1.putExtra("allDay", false);
                                            intent1.putExtra("rrule", "FREQ=YEARLY");
                                            intent1.putExtra("endTime", dateend.getTime());
                                            intent1.putExtra("title", event_name.getText().toString());
                                            startActivityForResult(intent1, 1);

                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                            }
                        });

                        non.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                HttpUrl.Builder urlBuilder = HttpUrl.parse(club.getUrl() + "webService/setReponseEvent.php").newBuilder();
                                urlBuilder.addQueryParameter("id", Integer.toString(idevent));
                                urlBuilder.addQueryParameter("id_membre", Integer.toString(membre.getMembre().getId()));
                                urlBuilder.addQueryParameter("rep", "non");

                                String url = urlBuilder.build().toString();
                                Request request = new Request.Builder()
                                        .url(url)
                                        .build();

                                client.newCall(request).enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {}
                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {

                                        Intent intent = new Intent(event.this, list_events.class);
                                        startActivity(intent);

                                        finish();

                                    }
                                });
                            }
                        });

                        avec_invite.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                                try {
                                    Date datestart = df.parse(debut_event.getText().toString());
                                    Date dateend = df.parse(fin_event.getText().toString());

                                    Calendar cal = Calendar.getInstance();
                                    Intent intent1 = new Intent(Intent.ACTION_EDIT);
                                    intent1.setType("vnd.android.cursor.item/event");
                                    intent1.putExtra("beginTime", datestart.getTime());
                                    intent1.putExtra("allDay", false);
                                    intent1.putExtra("rrule", "FREQ=YEARLY");
                                    intent1.putExtra("endTime", dateend.getTime());
                                    intent1.putExtra("title", event_name.getText().toString());
                                    startActivityForResult(intent1, 1);

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                Intent intent = new Intent(event.this, invites.class);
                                startActivity(intent);

                                intent.putExtra("id", idevent);

                                finish();

                            }
                        });

                    }
                });

            }
        });

    }
}
