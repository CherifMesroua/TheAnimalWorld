package com.example.theanimalworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class LandAnimalLevels extends SettingShow {
    ImageView land1,wsetting,level1,stars_level1;
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
        level1=findViewById(R.id.level1);
        level1.setOnClickListener(View ->{
            Intent intent = new Intent(LandAnimalLevels.this, Learning_level1.class);
            startActivity(intent);
        });

        get_activity_data("cherif123123", 0);

        stars_level1 = findViewById(R.id.stars_level1);
        stars_level1.setOnClickListener(View->{
            try {
                String nbrStars = String.valueOf(ACTIVITY_INFO.get("nbr_stars"));
                if (nbrStars.equals("3")) {
                    stars_level1.setImageResource(R.drawable.full_stars);
                } else if (nbrStars.equals("2")) {
                    stars_level1.setImageResource(R.drawable.stars_70);
                } else {
                    stars_level1.setImageResource(R.drawable.stars_30);
                }
            }catch (Exception e){

            }


        });

    }

}