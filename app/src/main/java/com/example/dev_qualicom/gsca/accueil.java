package com.example.dev_qualicom.gsca;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class accueil extends AppCompatActivity {

    ImageButton btn_membre;
    ImageButton btn_event;
    ImageButton btn_document;
    ImageButton btn_stat;
    ClubSingleton club;
    MembreSingleton membre;
    ImageView pub;

    JSONObject jsonObj;

    TextView nomclub;

    TextView bvn;

    float scale;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    FloatingActionButton disconnect;

    MembreSingleton membreSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        club = ClubSingleton.getInstance();
        membre = MembreSingleton.getInstance();

        scale = getResources().getDisplayMetrics().density;

        btn_membre = (ImageButton) findViewById(R.id.members);
        btn_event = (ImageButton) findViewById(R.id.events);
        btn_document = (ImageButton) findViewById(R.id.docs);
        btn_stat = (ImageButton) findViewById(R.id.stats);
        bvn = (TextView) findViewById(R.id.bienvenue);
        pub = (ImageView) findViewById(R.id.pub);

        nomclub = findViewById(R.id.nomduclub);

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

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(club.getUrl()+"webService/getPub.php").newBuilder();
        String url = urlBuilder.build().toString();
        final Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string().toString();

                try {
                    jsonObj = new JSONObject(json);
                    Log.e("TEST",jsonObj.get("pub").toString());

                    if(jsonObj.get("pub").toString() != "") {
                        Log.e("TEST",club.getUrl() + "../../img/" + jsonObj.get("pub"));

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                try {


                                    int pixels = (int) (50 * scale + 0.5f);

                                    Picasso.get().load(club.getUrl() + "../../img/" + jsonObj.get("pub")).into(pub);
                                    pub.setVisibility(View.VISIBLE);
                                    ViewGroup.LayoutParams layoutParams = pub.getLayoutParams();
                                    layoutParams.height = pixels;
                                    pub.setLayoutParams(layoutParams);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

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


                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        WebService example = new WebService();
                        String response = null;

                        HttpUrl.Builder urlBuilder = HttpUrl.parse(club.getUrl() + "webService/logout.php").newBuilder();
                        urlBuilder.addQueryParameter("id", Integer.toString(membre.getMembre().getId()));
                        String url = urlBuilder.build().toString();
                        try {
                            response = example.run(url);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });

                thread.start();

                Intent intent = new Intent(accueil.this, Club_Log.class);
                startActivity(intent);

                finish();

            }
        });

        membreSingleton = MembreSingleton.getInstance();

        bvn.setText("Bienvenue " + membreSingleton.getMembre().getPrenom_contact() + " " + membreSingleton.getMembre().getNom_contact());
        nomclub.setText(club.getNom());


    }
}
