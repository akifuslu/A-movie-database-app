package com.uslu.akif.deplikeintern.models;

import java.io.Serializable;

public class Actor implements Serializable {
    private String name;
    private double popularity;
    private int photoId;

    public Actor(String name, double popularity, int photoId){
        this.name = name;
        this.popularity = popularity;
        this.photoId = photoId;
    }

    public String getName(){
        return name;
    }

    public double getPopularity(){
        return popularity;
    }

    public int getPhotoId() {
        return photoId;
    }
}
