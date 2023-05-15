package com.example.theanimalworld;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class SettingShow extends AppCompatActivity{
    protected void show_setting(ImageView wsetting, LinearLayout sl){

        if (sl.getVisibility() == View.VISIBLE) {
            RotateAnimation rotateAnimation = new RotateAnimation(
                    0f, // starting angle
                    180f, // ending angle
                    Animation.RELATIVE_TO_SELF, 0.5f, // pivot x
                    Animation.RELATIVE_TO_SELF, 0.5f // pivot y
            );

// Set the animation's properties
            rotateAnimation.setDuration(500); // duration in milliseconds
            //rotateAnimation.setRepeatCount(Animation.INFINITE); // repeat indefinitely
// Start the animation
            wsetting.startAnimation(rotateAnimation);
            sl.setVisibility(View.GONE);
        } else {
            RotateAnimation rotateAnimation = new RotateAnimation(
                    180f, // starting angle
                    0f, // ending angle
                    Animation.RELATIVE_TO_SELF, 0.5f, // pivot x
                    Animation.RELATIVE_TO_SELF, 0.5f // pivot y
            );

// Set the animation's properties
            rotateAnimation.setDuration(500); // duration in milliseconds
            //rotateAnimation.setRepeatCount(Animation.INFINITE); // repeat indefinitely
// Start the animation
            wsetting.startAnimation(rotateAnimation);
            sl.setVisibility(View.VISIBLE);
        }
    }
}
