package com.example.theanimalworld;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    ImageView hand;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        ImageView cloud = findViewById(R.id.sky);
        TranslateAnimation animation = new TranslateAnimation(0, 500, 0, 0);
        animation.setDuration(8000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        cloud.startAnimation(animation);

        ImageView cloud1 = findViewById(R.id.sky1);
        TranslateAnimation animation1 = new TranslateAnimation(0, 500, 0, 0);
        animation1.setDuration(6000);
        animation1.setRepeatCount(Animation.INFINITE);
        animation1.setRepeatMode(Animation.REVERSE);
        cloud1.startAnimation(animation1);

        ImageView cloud2 = findViewById(R.id.sky2);
        TranslateAnimation animation2 = new TranslateAnimation(0, 500, 0, 0);
        animation2.setDuration(7000);
        animation2.setRepeatCount(Animation.INFINITE);
        animation2.setRepeatMode(Animation.REVERSE);
        cloud2.startAnimation(animation2);

        ImageView whale=findViewById(R.id.whale);
        ImageView sea = findViewById(R.id.sea);
        TranslateAnimation animation3 = new TranslateAnimation(0, 0, 0, -30);
        animation3.setDuration(2500);
        animation3.setRepeatCount(Animation.INFINITE);
        animation3.setRepeatMode(Animation.REVERSE);
        sea.startAnimation(animation3);
        whale.startAnimation(animation3);

        ImageView sea1 = findViewById(R.id.sea1);
        TranslateAnimation animation4 = new TranslateAnimation(0, 100, 0, 0);
        animation4.setDuration(2500);
        animation4.setRepeatCount(Animation.INFINITE);
        animation4.setRepeatMode(Animation.REVERSE);
        sea1.startAnimation(animation4);

        hand =findViewById(R.id.hand);
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f,  // Start scale X
                1.5f,  // End scale X
                1f,  // Start scale Y
                1.5f,  // End scale Y
                Animation.RELATIVE_TO_SELF, 0.2f, // Pivot X
                Animation.RELATIVE_TO_SELF, 0.2f // Pivot Y
        );
        scaleAnimation.setDuration(1000); // Animation duration in milliseconds
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
// Start the animation on a View
        hand.startAnimation(scaleAnimation);

    }
    public void land(View view){
        Intent intent=new Intent(Home.this, LandAnimalLevels.class);
        startActivity(intent);
        hand.setVisibility(View.GONE);
    }
    public void sea(View view){

    }
    public void sky(View view){

    }
}