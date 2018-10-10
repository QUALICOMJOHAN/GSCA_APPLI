package com.example.dev_qualicom.gsca;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Listmembers extends AppCompatActivity {

    private RecyclerView recyclerView;

    WebService exemple = new WebService();

    private List<Membre> listeMembres;

    LinearLayoutManager llm = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_members);

        OkHttpClient client = new OkHttpClient();
        ClubSingleton club = ClubSingleton.getInstance();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(club.getUrl()+"webService/getMembres.php").newBuilder();
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

                try {
                    listeMembres = exemple.getMembres();

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            recyclerView = (RecyclerView) findViewById(R.id.listmembers_list);

                            MembresAdapter membresAdapter = new MembresAdapter();
                            llm.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(llm);
                            recyclerView.setAdapter(membresAdapter);

                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class MembresAdapter extends RecyclerView.Adapter<MembresHolder> {

        @Override
        public MembresHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View vue = getLayoutInflater().inflate(R.layout.activity_list_members_item, null);
            return new MembresHolder(vue);
        }

        @Override
        public void onBindViewHolder(MembresHolder holder, int position) {

            Membre membre = listeMembres.get(position);
            holder.bind(membre);

        }

        @Override
        public int getItemCount() {
            return listeMembres.size();
        }
    }
    class MembresHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView champNomPrenom;
        private TextView champSociete;
        private ImageView champImage;

        public MembresHolder(View itemView) {
            super(itemView);

            champNomPrenom = itemView.findViewById(R.id.nom_prenom);
            champSociete = itemView.findViewById(R.id.societe);
            champImage = itemView.findViewById(R.id.img_profil);

        }

        public void bind(Membre member){

            ClubSingleton club = ClubSingleton.getInstance();

            champNomPrenom.setText(member.getNom_contact()+" "+member.getPrenom_contact());
            champSociete.setText(member.getNom_societe());

            Picasso.get().load(club.getUrl()+"../../img_membre"+member.getImg_profil()).into(champImage);

            this.itemView.setId(member.getId());
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            /*Artist a = WebService.getArtist(view.getId());
            int artistId = a.id;

            Intent intent = new Intent(HomeActivity.this, ArtisteActivity.class);

            intent.putExtra("id", artistId);

            startActivity(intent);*/

        }
    }
}
