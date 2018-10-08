package com.example.dev_qualicom.gsca;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebService {

    OkHttpClient client = new OkHttpClient();
    ClubSingleton club = ClubSingleton.getInstance();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string().toString();
        }
    }

    Membre getMembre(String id) throws IOException, JSONException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(club.getUrl()+"webService/getMembre.php").newBuilder();
        urlBuilder.addQueryParameter("id", id);
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            Gson gson = new Gson();
            String json = response.body().string().toString();
            Membre membre = gson.fromJson(json, Membre.class);
            return membre;
        }
    }

    void setToken(String token, Membre membre) throws IOException, JSONException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(club.getUrl()+"webService/setToken.php").newBuilder();
        urlBuilder.addQueryParameter("id", String.valueOf(membre.getId()));
        urlBuilder.addQueryParameter("token", token);

        Log.e("SAVE", String.valueOf(membre.getId()));
        Log.e("SAVE", token);

        String url = urlBuilder.build().toString();
        Log.e("SAVE", url);
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {

        }
    }
}
