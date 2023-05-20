package com.example.theanimalworld;

public class Activity {
    int id,nbr_stars;
    long finished_time;
    boolean state;

    public Activity(){

    }
    public Activity(int id, int nbr_stars, long finished_time, boolean state) {
        this.id = id;
        this.nbr_stars = nbr_stars;
        this.finished_time = finished_time;
        this.state = state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNbr_stars(int nbr_stars) {
        this.nbr_stars = nbr_stars;
    }

    public void setFinished_time(long finished_time) {
        this.finished_time = finished_time;
    }
    public void setState(boolean state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public int getNbr_stars() {
        return nbr_stars;
    }

    public long getFinished_time() {
        return finished_time;
    }

    public boolean getState() {
        return state;
    }
}
