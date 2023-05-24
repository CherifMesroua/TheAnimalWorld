package com.example.theanimalworld;

import static android.os.SystemClock.sleep;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Landlevel1 extends SettingShow {

    LinearLayout sl;
    ImageView wsetting;
    ImageView img1,img2,img3;
    TextView txt,time,txt1;
    Timer timer;
    int id=0, n=0,correct,stars_nbr=1,correct_nbr=0;

    int[] images = new int[3],soundes1 = new int[3],soundes2=new int[3];
    String[] words = new String[3];

    ArrayList<Item> imageList;
    MediaPlayer sound11,sound12,sound13,sound21,sound22,sound23;

    int minutes=0,seconds=59;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlevel1);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
        txt=findViewById(R.id.txt);
        txt1=findViewById(R.id.txt1);
        time=findViewById(R.id.timer);
        wsetting=findViewById(R.id.Wsetting);
        sl=findViewById(R.id.settingLayout);
        Game_Sounds game_sounds=new Game_Sounds(this);

        wsetting.setOnClickListener(View->{
            show_setting(wsetting,sl);
        });
        //ooooooooooooooooooooooooooooooo
        // Create a list of Image objects
        Level_s_Item_Bank farmList=new Level_s_Item_Bank();
        imageList = farmList.farm_imageList();

        startTimer(time);

        //randomise the items
        nextTest();
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                block_clicking();
                if(words[0].equals(words[correct])){
                    img1.setBackgroundResource(R.drawable.correct_answer_bg);
                    sound11.start();
                    correct_nbr++;
                }
                else{
                    if(words[1].equals(words[correct])){
                        img2.setBackgroundResource(R.drawable.correct_answer_bg);
                    }
                    else if(words[2].equals(words[correct])){
                        img3.setBackgroundResource(R.drawable.correct_answer_bg);
                    }
                    img1.setBackgroundResource(R.drawable.wrong_answer_bg);
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(words[0].equals(words[correct])){
                            game_sounds.play_correct();
                        }else{
                            game_sounds.play_wrong();
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                nextTest();
                                unblock_clicking();
                            }
                        }, 2000);
                    }
                }, 1000);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                block_clicking();
                if(words[1].equals(words[correct])){
                    img2.setBackgroundResource(R.drawable.correct_answer_bg);
                    sound12.start();
                    correct_nbr++;
                }
                else{
                    if(words[0].equals(words[correct])){
                        img1.setBackgroundResource(R.drawable.correct_answer_bg);
                    }
                    else if(words[2].equals(words[correct])){
                        img3.setBackgroundResource(R.drawable.correct_answer_bg);
                    }
                    img2.setBackgroundResource(R.drawable.wrong_answer_bg);
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(words[1].equals(words[correct])){
                            game_sounds.play_correct();
                        }else{
                            game_sounds.play_wrong();
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                nextTest();
                                unblock_clicking();
                            }
                        }, 2000);
                    }
                }, 1000);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                block_clicking();
                if(words[2].equals(words[correct])){
                    img3.setBackgroundResource(R.drawable.correct_answer_bg);
                    sound13.start();
                    correct_nbr++;
                }
                else{
                    if(words[1].equals(words[correct])){
                        img2.setBackgroundResource(R.drawable.correct_answer_bg);
                    }
                    else if(words[0].equals(words[correct])){
                        img1.setBackgroundResource(R.drawable.correct_answer_bg);
                    }
                    img3.setBackgroundResource(R.drawable.wrong_answer_bg);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(words[2].equals(words[correct])){
                            game_sounds.play_correct();
                        }else{
                            game_sounds.play_wrong();
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                nextTest();
                                unblock_clicking();
                            }
                        }, 2000);
                    }
                }, 1000);
            }
        });


        if(n > 3 || (seconds==0&&minutes==0)){
            timer.purge();
            timer.cancel();
            if(seconds>30){
                stars_nbr++;
            }
            if(correct_nbr==3){
                stars_nbr++;
            }
            Activity activity=new Activity(id,stars_nbr,seconds,true);
            HashMap<String, Object> activityValues = new HashMap<>();
            activityValues.put("finished_time", seconds);
            activityValues.put("state", true);
            activityValues.put("nbr_stars", stars_nbr);
            activityValues.put("id", id);
            Rank rank=new Rank();
            rank.setActivity(activity);
            rank.setTotal_nbr_stars(stars_nbr);
            rank.setTotal_time(seconds);
            rank.setRanklevel();

            createActivity(id);

            updateUser(USERNAME,rank);
        }
        //ooooooooooooooooooooooooooooooo

    }


    private void startTimer(TextView timertxt){
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if(seconds<10){
                    timertxt.setTextColor(getResources().getColor(R.color.red));
                }
                else if(seconds<30){
                    timertxt.setTextColor(getResources().getColor(R.color.fireorange));
                }
                else{
                    seconds--;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(seconds==0 && minutes==0){
                            timer.purge();
                            timer.cancel();
                        }
                        String finalMin=String.valueOf(minutes),finalSec=String.valueOf(seconds);
                        if(finalSec.length()==1){
                            finalSec="0"+finalSec;
                        }
                        timertxt.setText("0"+finalMin+":"+finalSec+" ");
                    }
                });
            }
        },1000,1000);
    }

    public void nextTest(){
        n=n+1;
        txt1.setText(String.valueOf(n)+"/3");
        img1.setBackgroundResource(R.drawable.transparent_bg);
        img2.setBackgroundResource(R.drawable.transparent_bg);
        img3.setBackgroundResource(R.drawable.transparent_bg);
        int p=0;
        //shuffle randomize the imageList
        Collections.shuffle(imageList);
        for(int i=0; i<3; i++){
            images[p] = imageList.get(i).getImage();
            words[p] = imageList.get(i).getName();
            soundes1[p] = imageList.get(i).getSound1();
            soundes2[p] = imageList.get(i).getSound2();
            p++;
        }

        img1.setImageResource(images[0]);
        img2.setImageResource(images[1]);
        img3.setImageResource(images[2]);

        Random random = new Random();
        correct=random.nextInt(3);
        txt.setText(words[correct]);

        sound11 = MediaPlayer.create(this,soundes1[0]);
        sound12 = MediaPlayer.create(this,soundes1[1]);
        sound13 = MediaPlayer.create(this,soundes1[2]);

        sound21 = MediaPlayer.create(this,soundes2[0]);
        sound22 = MediaPlayer.create(this,soundes2[1]);
        sound23 = MediaPlayer.create(this,soundes2[2]);
    }

    public void block_clicking(){
        img1.setClickable(false);
        img2.setClickable(false);
        img3.setClickable(false);
    }
    public void unblock_clicking(){
        img1.setClickable(true);
        img2.setClickable(true);
        img3.setClickable(true);
    }



}