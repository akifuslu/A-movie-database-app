package com.uslu.akif.deplikeintern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.uslu.akif.deplikeintern.adapters.ActorListAdapter;
import com.uslu.akif.deplikeintern.models.Actor;
import com.uslu.akif.deplikeintern.R;
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
        fillActorList(actors);
    }

    private void initialize()
    {
        actors = new ArrayList<Actor>();
        listView = (ListView) findViewById(R.id.actor_list_view);
        actorListAdapter = new ActorListAdapter(MainActivity.this,actors);
        listView.setAdapter(actorListAdapter);
    }

    private void fillActorList(ArrayList<Actor> actorArrayList)
    {
        for (int i = 0; i < 15; i++)
        {
            Actor tmp = new Actor("actor:" + i, 0, R.drawable.dummy);
            actorArrayList.add(tmp);
        }
    }
}
