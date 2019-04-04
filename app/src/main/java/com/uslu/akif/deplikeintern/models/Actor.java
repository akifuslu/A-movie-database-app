package com.uslu.akif.deplikeintern.models;

public class Actor {
    private String name;
    private int popularity;
    private int photoId;

    public Actor(String name, int popularity, int photoId){
        this.name = name;
        this.popularity = popularity;
        this.photoId = photoId;
    }

    public String getName(){
        return name;
    }

    public int getPopularity(){
        return popularity;
    }

    public int getPhotoId() {
        return photoId;
    }
}
