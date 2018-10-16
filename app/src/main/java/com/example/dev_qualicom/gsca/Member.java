package com.example.dev_qualicom.gsca;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.Manifest.permission.CALL_PHONE;

public class Member extends AppCompatActivity {

    Membre membre;
    ImageView logo_societe;
    TextView societe_name;
    TextView societe_desc;
    TextView societe_address;
    TextView societe_tel;
    TextView societe_sector;
    TextView societe_mainsector;
    TextView societe_mail;
    TextView societe_web;

    ImageView member_profil;
    TextView member_name;
    TextView member_firstname;
    TextView member_poste;
    TextView member_number;
    TextView member_mail;

    ClubSingleton club;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        OkHttpClient client = new OkHttpClient();
        club = ClubSingleton.getInstance();

        int id = getIntent().getIntExtra("id", 0);

        HttpUrl.Builder urlBuilder = HttpUrl.parse(club.getUrl()+"webService/getMembre.php").newBuilder();
        urlBuilder.addQueryParameter("id", Integer.toString(id));
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
                membre = gson.fromJson(json, Membre.class);

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        logo_societe = (ImageView) findViewById(R.id.societe_logo);
                        societe_name = (TextView) findViewById(R.id.societe_name);
                        societe_desc = (TextView) findViewById(R.id.societe_desc);
                        societe_address = (TextView) findViewById(R.id.societe_address);
                        societe_tel = (TextView) findViewById(R.id.societe_tel);
                        societe_sector = (TextView) findViewById(R.id.societe_sector);
                        societe_mainsector = (TextView) findViewById(R.id.societe_mainsector);
                        societe_mail = (TextView) findViewById(R.id.societe_mail);
                        societe_web = (TextView) findViewById(R.id.societe_web);
                        member_profil = (ImageView) findViewById(R.id.member_profil);
                        member_name = (TextView) findViewById(R.id.member_name);
                        member_firstname = (TextView) findViewById(R.id.membre_firstname);
                        member_poste = (TextView) findViewById(R.id.membre_poste);
                        member_number = (TextView) findViewById(R.id.membre_number);
                        member_mail = (TextView) findViewById(R.id.membre_mail);

                        Picasso.get().load(club.getUrl()+"../../img_membre"+membre.getImg_logo()).into(logo_societe);

                        societe_name.setText(membre.getNom_societe());
                        societe_desc.setText(membre.getDes_societe());
                        societe_address.setText(membre.getAdresse_societe());
                        societe_tel.setText(membre.getTel_societe());
                        societe_sector.setText(membre.getSecteur_activite());
                        societe_mainsector.setText(membre.getActivite_principal());
                        societe_mail.setText(membre.getMail_societe());
                        societe_web.setText(membre.getSite_societe());

                        Picasso.get().load(club.getUrl()+"../../img_membre"+membre.getImg_profil()).into(member_profil);

                        member_name.setText(membre.getNom_contact());
                        member_firstname.setText(membre.getPrenom_contact());
                        member_poste.setText(membre.getStatut_contact());
                        member_number.setText(membre.getTel_contact());
                        member_mail.setText(membre.getMail_contact());


                        societe_name.setTextIsSelectable(true);
                        societe_desc.setTextIsSelectable(true);
                        societe_address.setTextIsSelectable(true);
                        societe_tel.setTextIsSelectable(true);
                        societe_sector.setTextIsSelectable(true);
                        societe_mainsector.setTextIsSelectable(true);
                        societe_mail.setTextIsSelectable(true);
                        societe_web.setTextIsSelectable(true);
                        member_name.setTextIsSelectable(true);
                        member_firstname.setTextIsSelectable(true);
                        member_poste.setTextIsSelectable(true);
                        member_number.setTextIsSelectable(true);
                        member_mail.setTextIsSelectable(true);

                        societe_tel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + societe_tel.getText()));

                                if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                    startActivity(intent);
                                } else {
                                    requestPermissions(new String[]{CALL_PHONE}, 1);
                                }

                            }
                        });

                        member_number.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + member_number.getText()));

                                if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                    startActivity(intent);
                                } else {
                                    requestPermissions(new String[]{CALL_PHONE}, 1);
                                }

                            }
                        });
                    }
                });

            }
        });

    }

}
