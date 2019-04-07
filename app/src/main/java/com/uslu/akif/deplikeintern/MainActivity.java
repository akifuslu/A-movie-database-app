package com.uslu.akif.deplikeintern;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import com.uslu.akif.deplikeintern.adapters.ActorListAdapter;
import com.uslu.akif.deplikeintern.models.Actor;

import java.io.Serializable;
import java.util.ArrayList;
import android.net.Uri;

public class MainActivity extends AppCompatActivity implements ActorListFragment.OnFragmentInteractionListener,
                                                               SearchActorsFragment.OnFragmentInteractionListener {

    private ActionBar actionBar;
    private Fragment actorListFragment;
    private Fragment searchActorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeFragments();
    }

    private void initializeFragments()
    {
        actionBar = getSupportActionBar();
        actionBar.setTitle("Actor List");
        actorListFragment = new ActorListFragment();
        searchActorFragment = new SearchActorsFragment();
        loadFragment(actorListFragment);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_actor_list:
                    actionBar.setTitle("Actor List");
                    loadFragment(actorListFragment);
                    return true;
                case R.id.navigation_search_actors:
                    actionBar.setTitle("Search Actors");
                    loadFragment(searchActorFragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }
}
