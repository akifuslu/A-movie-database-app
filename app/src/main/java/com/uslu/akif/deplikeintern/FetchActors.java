package com.uslu.akif.deplikeintern;


import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.uslu.akif.deplikeintern.adapters.ActorListAdapter;
import com.uslu.akif.deplikeintern.models.Actor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

import static java.sql.Types.NULL;

public class FetchActors{

    private static FetchActors instance = null;
    private static final String TAG = FetchActors.class.getSimpleName();
    private RequestQueue requestQueue;
    private String popularListUrl;
    private String searchUrl;
    private int currentPage;
    private Context context;

    public static synchronized FetchActors getInstance(Context context)
    {
        if(instance == null)
            instance = new FetchActors(context);
        return instance;
    }

    private FetchActors(Context context){
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
        currentPage = 1;//start from page 1
        popularListUrl = context.getResources().getString(R.string.popular_list_base_url) + context.getResources().getString(R.string.api_key);
        searchUrl = context.getResources().getString(R.string.search_base_url) + context.getResources().getString(R.string.api_key);
    }

    public void populateActors(final ArrayList<Actor> actors, final ActorListAdapter actorListAdapter) {
        String pgUrl = popularListUrl + "&page=" + currentPage;//add page to popular base url
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
                            String photoUrl = context.getResources().getString(R.string.image_base_url);
                            photoUrl += act.getString("profile_path");
                            Actor tmp = new Actor(name, popularity, photoUrl);
                            actors.add(tmp);
                        }
                        actorListAdapter.notifyDataSetChanged();
                        currentPage++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
        requestQueue.add(jsonObjectRequest);
    }

    public void searchActors(final String actorName, final TextView resultText)
    {
        String sUrl = searchUrl + "&query=" + actorName;//add query to search base url
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, sUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int result = response.getInt("total_results");
                            resultText.setText(String.format(context.getResources().getString(R.string.search_results), result));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}
