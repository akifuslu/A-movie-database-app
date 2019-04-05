package com.uslu.akif.deplikeintern;


import android.content.Context;
import android.os.Debug;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.uslu.akif.deplikeintern.models.Actor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FetchActors{

    private static final String TAG = FetchActors.class.getSimpleName();
    private RequestQueue requestQueue;
    private String url = "https://api.themoviedb.org/3/person/popular?api_key=9635bc7db895ff8e4f593763493bbd1e&page=";
    private Context context;

    public FetchActors(Context context){
        this.context = context;
    }

    public void populateActors(final ArrayList<Actor> actors, final ListView listView, final int page) {
        requestQueue = Volley.newRequestQueue(context);
        String pgUrl = url + page;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, pgUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray arr = response.getJSONArray("results");
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject act = arr.getJSONObject(i);
                            String name = act.getString("name");
                            double popularity = act.getDouble("popularity");
                            Actor tmp = new Actor(name, popularity, R.drawable.dummy);
                            actors.add(tmp);
                        }
                        listView.requestLayout();
                        if(page < 10)//load first 10 pages just for now
                            populateActors(actors, listView, page+1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("ERROR", "Json error", error);
                }
            });
        requestQueue.add(jsonObjectRequest);
    }
}
