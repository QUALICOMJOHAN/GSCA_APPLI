package com.example.dev_qualicom.gsca;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class list_docs extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<Doc> listeDoc;

    MembreSingleton membreSingleton = MembreSingleton.getInstance();

    ClubSingleton club;

    LinearLayoutManager llm = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_docs);

        OkHttpClient client = new OkHttpClient();
        club = ClubSingleton.getInstance();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(club.getUrl()+"webService/getFiles.php").newBuilder();
        urlBuilder.addQueryParameter("id", Integer.toString(membreSingleton.getMembre().getId()));
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
                listeDoc = gson.fromJson(json, new TypeToken<List<Doc>>(){}.getType());

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        recyclerView = (RecyclerView) findViewById(R.id.list_docs_list);

                        DocsAdapter docsAdapter = new DocsAdapter();
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(llm);
                        recyclerView.setAdapter(docsAdapter);

                    }
                });

            }
        });
    }

    class DocsAdapter extends RecyclerView.Adapter<DocsHolder> {

        @Override
        public DocsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View vue = getLayoutInflater().inflate(R.layout.activity_list_docs_item, null);
            return new DocsHolder(vue);
        }

        @Override
        public void onBindViewHolder(DocsHolder holder, int position) {

            Doc doc = listeDoc.get(position);
            holder.bind(doc);

        }

        @Override
        public int getItemCount() {
            return listeDoc.size();
        }
    }
    class DocsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nom_doc;
        private TextView date_doc;
        private TextView url;

        public DocsHolder(View itemView) {
            super(itemView);

            nom_doc = itemView.findViewById(R.id.nom_doc);
            date_doc = itemView.findViewById(R.id.date_doc);
            url = itemView.findViewById(R.id.url);

        }

        public void bind(Doc doc){

            nom_doc.setText(doc.getNom());
            date_doc.setText(doc.getDate());
            url.setText(doc.getChemin());

            this.itemView.setId(listeDoc.indexOf(doc));
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            TextView url = view.findViewById(R.id.url);
            TextView nom = view.findViewById(R.id.nom_doc);

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(club.getUrl()+url.getText().toString().substring(1)+nom.getText().toString()));
            startActivity(browserIntent);

        }
    }
}
