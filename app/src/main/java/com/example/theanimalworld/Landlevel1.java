package com.example.theanimalworld;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Landlevel1 extends NextTest {

    ImageView img1,img2,img3;
    TextView txt,time,txt1;
    Timer timer;
    int n=1,correct;

    int[] images = new int[3],soundes1 = new int[3],soundes2=new int[3];
    String[] words = new String[3];
    int p = 0;
    ArrayList<Item> imageList;
    MediaPlayer sound11,sound12,sound13,sound21,sound22,sound23;

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

        //ooooooooooooooooooooooooooooooo
        // Create a list of Image objects
        imageList = new ArrayList<>();
        imageList.add(new Item("Dog", R.drawable.dog,R.raw.dog,R.raw.dog_name));
        imageList.add(new Item("Horse", R.drawable.horse,R.raw.horse,R.raw.horse_name));
        imageList.add(new Item("Chicken", R.drawable.chicken,R.raw.chicken,R.raw.chicken_name));
        imageList.add(new Item("Turky", R.drawable.turky,R.raw.turkey,R.raw.turkey_name));
        imageList.add(new Item("danky", R.drawable.danky,R.raw.donkey,R.raw.donkey_name));

        startTimer(time);

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


       // nextTest(txt,img1,img2,img3,imageList);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(words[0].equals(words[correct])){
                    img1.setBackgroundResource(R.drawable.correct_answer_bg);
                    sound11.start();
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
                txt1.setText(String.valueOf(n++)+"/3");
               // nextTest(txt,img1,img2,img3,imageList);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(words[1].equals(words[correct])){
                    img2.setBackgroundResource(R.drawable.correct_answer_bg);
                    sound12.start();
                }
                else{
                    if(words[0].equals(words[correct])){
                        img2.setBackgroundResource(R.drawable.correct_answer_bg);
                    }
                    else if(words[2].equals(words[correct])){
                        img3.setBackgroundResource(R.drawable.correct_answer_bg);
                    }
                    img2.setBackgroundResource(R.drawable.wrong_answer_bg);
                }
                txt1.setText(String.valueOf(n++)+"/3");
               // nextTest(txt,img1,img2,img3,imageList);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(words[2].equals(words[correct])){
                    img3.setBackgroundResource(R.drawable.correct_answer_bg);
                    sound13.start();
                    timer.purge();
                    timer.cancel();
                }
                else{
                    if(words[1].equals(words[correct])){
                        img2.setBackgroundResource(R.drawable.correct_answer_bg);
                    }
                    else if(words[0].equals(words[correct])){
                        img3.setBackgroundResource(R.drawable.correct_answer_bg);
                    }
                    img3.setBackgroundResource(R.drawable.wrong_answer_bg);
                }
                txt1.setText(String.valueOf(n++)+"/3");
                //nextTest(txt,img1,img2,img3,imageList);
            }
        });

        //ooooooooooooooooooooooooooooooo

    }



    private int minutes=1,seconds=0,totalTimeInMin=1;
    private void startTimer(TextView timertxt){
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() { 
                if(seconds<=10){
                    timertxt.setTextColor(getResources().getColor(R.color.red));
                }
                else if(seconds<=30){
                    timertxt.setTextColor(getResources().getColor(R.color.fireorange));
                }
                if(seconds==0){
                    totalTimeInMin--;
                    seconds=59;
                }
                else{

                    seconds--;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(seconds==0 && totalTimeInMin==0){
                            timer.purge();
                            timer.cancel();

                        }
                        String finalMin=String.valueOf(totalTimeInMin),finalSec=String.valueOf(seconds);
                        if(finalSec.length()==1){
                            finalSec="0"+finalSec;
                        }
                        timertxt.setText("0"+finalMin+":"+finalSec+" ");
                    }
                });
            }
        },1000,1000);
    }

    @Override
    protected void fillSounds() {

    }
}