package com.example.theanimalworld;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class LandAnimalLevels extends SettingShow {
    ImageView land1,wsetting;
    LinearLayout sl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_animal);
        land1=findViewById(R.id.land1);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 10);
        animation.setDuration(1500);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        land1.startAnimation(animation);
        wsetting=findViewById(R.id.Wsetting);
        sl=findViewById(R.id.settingLayout);
        wsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_setting(wsetting,sl);
            }
        });
    }

}