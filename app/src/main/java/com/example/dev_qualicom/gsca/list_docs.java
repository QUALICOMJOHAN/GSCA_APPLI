package com.example.dev_qualicom.gsca;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class list_docs extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<Doc> listeDoc;

    MembreSingleton membreSingleton = MembreSingleton.getInstance();

    LinearLayoutManager llm = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_docs);

        /*OkHttpClient client = new OkHttpClient();
        ClubSingleton club = ClubSingleton.getInstance();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(club.getUrl()+"webService/getDoc.php").newBuilder();
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
                listeEvent = gson.fromJson(json, new TypeToken<List<Event_Class>>(){}.getType());

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        recyclerView = (RecyclerView) findViewById(R.id.list_event_ecran);

                        list_events.EventsAdapter eventsAdapter = new list_events.EventsAdapter();
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(llm);
                        recyclerView.setAdapter(eventsAdapter);

                    }
                });

            }
        });
    }

    class EventsAdapter extends RecyclerView.Adapter<list_events.EventsHolder> {

        @Override
        public list_events.EventsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View vue = getLayoutInflater().inflate(R.layout.activity_list_events_item, null);
            return new list_events.EventsHolder(vue);
        }

        @Override
        public void onBindViewHolder(list_events.EventsHolder holder, int position) {

            Event_Class event = listeEvent.get(position);
            holder.bind(event);

        }

        @Override
        public int getItemCount() {
            return listeEvent.size();
        }
    }
    class EventsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nom_event;
        private TextView date_event;
        private TextView desc_event;
        private View pastille;

        public EventsHolder(View itemView) {
            super(itemView);

            nom_event = itemView.findViewById(R.id.nom_event);
            date_event = itemView.findViewById(R.id.date_event);
            desc_event = itemView.findViewById(R.id.desc_event);
            pastille = itemView.findViewById(R.id.pastille);

        }

        public void bind(Event_Class event){

            nom_event.setText(event.getTitle());
            date_event.setText(event.getStart());
            desc_event.setText(event.getDesc_event());

            if(event.getStatut().equals("oui")){pastille.getBackground().mutate().setColorFilter(Color.parseColor("#99cc00"), PorterDuff.Mode.MULTIPLY);}
            if(event.getStatut().equals("non")){pastille.getBackground().mutate().setColorFilter(Color.parseColor("#cc0000"), PorterDuff.Mode.MULTIPLY);}

            this.itemView.setId(event.getId());
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int eventId = view.getId();

            Intent intent = new Intent(list_events.this, event.class);

            intent.putExtra("id", eventId);

            startActivity(intent);

            finish();

        }*/
    }
}
