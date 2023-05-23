package com.example.theanimalworld;

import static android.os.SystemClock.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class TestActivity extends Send_to_db {
    TextView te;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        te=findViewById(R.id.testtext);


        Rank rank=new Rank();
        rank.setTotal_time(55);
        rank.setTotal_nbr_stars(33);
        rank.setRanklevel();

        rank.activity.setNbr_stars(3);
        rank.activity.setId(0);
        rank.activity.setFinished_time(222);

        updateUser("cherif123123",rank);

        get_activity_data("cherif123123",0);
        get_rank_data("cherif123123");

    }
    public void testbutton(View v){
        te.setText(String.valueOf(NBR_STARS)+" "+String.valueOf(TOTAL_TIME)+" "+String.valueOf(RANKLEVEL));
    }
}