package com.uslu.akif.deplikeintern;


import android.content.Context;
import android.os.Debug;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

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
    private String searchUrl = "https://api.themoviedb.org/3/search/person?api_key=9635bc7db895ff8e4f593763493bbd1e&query=";
    private Context context;

    public FetchActors(Context context){
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public void populateActors(final ArrayList<Actor> actors, final int page, final ListView listView) {
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
                            String photoUrl = "http://image.tmdb.org/t/p/w185";
                            photoUrl += act.getString("profile_path");
                            Actor tmp = new Actor(name, popularity, photoUrl);
                            actors.add(tmp);
                        }
                        if(page < 1001)//load all pages
                            populateActors(actors, page+1, listView);
                        listView.requestLayout();
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

    public void searchActors(final int page, final String[] actorName, final int counter, final TextView result)
    {
        String pgUrl = searchUrl;
        for(int i = 0; i < actorName.length; i++)
            pgUrl += actorName[i] + "+";
        pgUrl += "&page=" + page;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, pgUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int tmp = 0;
                            JSONArray arr = response.getJSONArray("results");
                            int totalPages = response.getInt("total_pages");
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject act = arr.getJSONObject(i);
                                String name = act.getString("name");
                                String[] tokens = name.split(" ");//parse name
                                if(tokens.length != actorName.length + 1)
                                    continue;
                                boolean match = true;
                                for(int j = 0; j < actorName.length; j++)
                                {
                                    if(!tokens[j].equals(actorName[j]))
                                        match = false;
                                }
                                if(match)
                                    tmp++;
                            }
                            if(page < totalPages)//load all pages
                                searchActors(page+1, actorName, counter + tmp, result);
                            else
                                result.setText(counter + tmp + " actors found!");
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
