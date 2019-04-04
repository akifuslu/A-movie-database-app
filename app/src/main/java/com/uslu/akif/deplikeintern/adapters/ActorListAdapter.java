package com.uslu.akif.deplikeintern.adapters;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.uslu.akif.deplikeintern.R;
import com.uslu.akif.deplikeintern.models.Actor;
import java.util.ArrayList;

public class ActorListAdapter extends ArrayAdapter<Actor> {
    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private final ArrayList<Actor> actors;

    public ActorListAdapter(Context context, ArrayList<Actor> actors) {
        super(context, 0, actors);
        this.context = context;
        this.actors = actors;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return actors.size();
    }

    @Override
    public Actor getItem(int position) {
        return actors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return actors.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.list_view_item, null);

            holder = new ViewHolder();
            holder.actorImage = (ImageView) convertView.findViewById(R.id.actor_image);
            holder.actorNameLabel = (TextView) convertView.findViewById(R.id.actor_name_label);
            holder.actorPopularityLabel = (TextView) convertView.findViewById(R.id.actor_popularity_label);
            convertView.setTag(holder);

        }
        else{
            //Get viewholder we already created
            holder = (ViewHolder)convertView.getTag();
        }

        Actor actor = actors.get(position);
        if(actor != null){
            holder.actorImage.setImageResource(actor.getPhotoId());
            holder.actorNameLabel.setText(actor.getName());
            holder.actorPopularityLabel.setText(Integer.toString(actor.getPopularity()));
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView actorNameLabel;
        TextView actorPopularityLabel;
        ImageView actorImage;
    }
}
