package com.example.theanimalworld;

public class Rank extends Send_to_db{

    Activity activity;
    int ranklevel,total_nbr_stars,total_time;

    public Rank(){

    }
    public Rank(Activity activity, int ranklevel, int total_nbr_stars, int total_time) {
        this.activity = activity;
        this.ranklevel = ranklevel;
        this.total_nbr_stars = total_nbr_stars;
        this.total_time = total_time;
    }

    public Activity getActivity() {
        return activity;
    }

    public int getRanklevel() {
        return ranklevel;
    }
    public int getTotal_nbr_stars() {
        return total_nbr_stars;
    }

    public int getTotal_time() {
        return total_time;
    }


    public void setActivity(Activity activity) {
        this.activity=activity;
    }
    public void setRanklevel() {

        this.ranklevel = RANKLEVEL+getTotal_nbr_stars()/3;
    }

    public void setTotal_nbr_stars(int total_nbr_stars) {
        //getdata(USERNAME);
        this.total_nbr_stars = total_nbr_stars+NBR_STARS;
    }

    public void setTotal_time(int total_time) {
        //getdata(USERNAME);
        this.total_time = total_time+TOTLA_TIME;
    }
}
