package com.example.theanimalworld;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Learning_level1 extends SettingShow {
    private Handler handler;
    ImageView hand_l;
    Button button;
    TextView text1,text2,text3,text4,text5;
    ImageView img1, img2, img3, img4, img5;
    MediaPlayer sound11,sound12,sound13,sound14,sound15,sound21,sound22,sound23,sound24,sound25;
    ArrayList<Item> imageList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_level1);

        Level_s_Item_Bank farmList=new Level_s_Item_Bank();
        imageList = farmList.farm_imageList();

        img1 = findViewById(R.id.djaj);
        img2 = findViewById(R.id.hisan);
        img3 = findViewById(R.id.dikromi);
        img4 = findViewById(R.id.kalb);
        img5 = findViewById(R.id.himar);

        text1 = findViewById(R.id.chi_word);
        text2 = findViewById(R.id.hor_word);
        text3 = findViewById(R.id.tur_word);
        text4 = findViewById(R.id.dog_word);
        text5 = findViewById(R.id.don_word);

        button = findViewById(R.id.gototest);

        fillsounds();
        hand_l =findViewById(R.id.hand_l);
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f,  // Start scale X
                1.2f,  // End scale X
                1f,  // Start scale Y
                1.2f,  // End scale Y
                Animation.RELATIVE_TO_SELF, 0.2f, // Pivot X
                Animation.RELATIVE_TO_SELF, 0.2f // Pivot Y
        );
        scaleAnimation.setDuration(1000); // Animation duration in milliseconds
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.REVERSE);


// Start the animation on a View
        hand_l.startAnimation(scaleAnimation);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound23.start();
                block_img();
                showTextViewForDuration(text1);

            }

        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound22.start();
                block_img();
                showTextViewForDuration(text2);

            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound24.start();
                block_img();
                showTextViewForDuration(text3);

            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound21.start();
                block_img();
                showTextViewForDuration(text4);

            }
        });
        img5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sound25.start();
                block_img();
                showTextViewForDuration(text5);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Learning_level1.this,Landlevel1.class);
                startActivity(intent);
            }
        });
    }


    public void fillsounds(){
        sound11=MediaPlayer.create(this,imageList.get(0).getSound1());//dog
        sound12=MediaPlayer.create(this,imageList.get(1).getSound1());//horse
        sound13=MediaPlayer.create(this,imageList.get(2).getSound1());//chicken
        sound14=MediaPlayer.create(this,imageList.get(3).getSound1());//turkey
        sound15=MediaPlayer.create(this,imageList.get(4).getSound1());//donkey

        sound21=MediaPlayer.create(this,imageList.get(0).getSound2());
        sound22=MediaPlayer.create(this,imageList.get(1).getSound2());
        sound23=MediaPlayer.create(this,imageList.get(2).getSound2());
        sound24=MediaPlayer.create(this,imageList.get(3).getSound2());
        sound25=MediaPlayer.create(this,imageList.get(4).getSound2());

    }
    public void block_img(){
        img1.setClickable(false);
        img2.setClickable(false);
        img3.setClickable(false);
        img4.setClickable(false);
        img5.setClickable(false);

    }
    public void unblock_img(){
        img1.setClickable(true);
        img2.setClickable(true);
        img3.setClickable(true);
        img4.setClickable(true);
        img5.setClickable(true);
    }
    private void showTextViewForDuration(TextView textview) {
        handler = new Handler();
        textview.setVisibility(View.VISIBLE);
        hand_l.clearAnimation();
        hand_l.setVisibility(View.GONE);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textview.setVisibility(View.GONE);
                unblock_img();
            }
        }, 2000);

    }
}