package com.uslu.akif.deplikeintern.models;

import java.io.Serializable;

public class Actor implements Serializable {
    private String name;
    private double popularity;
    private String photoUrl;

    public Actor(String name, double popularity, String photoUrl){
        this.name = name;
        this.popularity = popularity;
        this.photoUrl = photoUrl;
    }

    public String getName(){
        return name;
    }

    public double getPopularity(){
        return popularity;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
