package com.example.dev_qualicom.gsca;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class stats extends AppCompatActivity {

    private ClubSingleton club;
    private MembreSingleton membre;
    Stat stat;

    TextView reunionsPIvalue;
    TextView reunionsABSvalue;
    TextView reunionsABSpercent;
    TextView reunionsTDPvalue;
    TextView transacsvalue;
    TextView transacsvalue2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        OkHttpClient client = new OkHttpClient();
        club = ClubSingleton.getInstance();
        membre = MembreSingleton.getInstance();

        int id = getIntent().getIntExtra("id", 0);

        HttpUrl.Builder urlBuilder = HttpUrl.parse(club.getUrl()+"webService/getStat.php").newBuilder();
        urlBuilder.addQueryParameter("id", Integer.toString(membre.getMembre().getId()));
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
                stat = gson.fromJson(json, Stat.class);

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        reunionsPIvalue = findViewById(R.id.reunionsPIvalue);
                        reunionsABSvalue = findViewById(R.id.reunionsABSvalue);
                        reunionsABSpercent = findViewById(R.id.reunionsABSpercent);
                        reunionsTDPvalue = findViewById(R.id.reunionsTDPvalue);
                        transacsvalue = findViewById(R.id.transacsvalue);
                        transacsvalue2 = findViewById(R.id.transacsvalue2);

                        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

                        int millis = (int) (Float.parseFloat(stat.getParole().toString()) * 1000);

                        TimeZone tz = TimeZone.getTimeZone("UTC");
                        df.setTimeZone(tz);
                        String time = df.format(new Date(millis));

                        reunionsPIvalue.setText(stat.getParticipation());
                        reunionsABSvalue.setText(stat.getAbsence());
                        reunionsABSpercent.setText(stat.getParticipation_pourcent());
                        reunionsTDPvalue.setText(df.format(millis));
                        transacsvalue.setText(stat.getNbtran());
                        transacsvalue2.setText(stat.getCa());

                    }
                });

            }
        });

    }
}
