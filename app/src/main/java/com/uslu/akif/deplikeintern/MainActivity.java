package com.uslu.akif.deplikeintern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.uslu.akif.deplikeintern.adapters.ActorListAdapter;
import com.uslu.akif.deplikeintern.models.Actor;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Actor> actors;
    private ListView listView;
    private ActorListAdapter actorListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        FetchActors fetch = new FetchActors(this);
        fetch.populateActors(actors, listView, 1);
    }

    private void initialize()
    {
        actors = new ArrayList<Actor>();
        listView = (ListView) findViewById(R.id.actor_list_view);
        actorListAdapter = new ActorListAdapter(MainActivity.this,actors);
        listView.setAdapter(actorListAdapter);
    }
}
