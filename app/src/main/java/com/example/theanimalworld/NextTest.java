package com.example.theanimalworld;

import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;

public abstract class NextTest extends SettingShow {
    int correct;
    int[] images = new int[3];
    int[] soundes1 = new int[3],soundes2=new int[3];
    String[] words = new String[3];
    int p = 0;

    public void next1Test(TextView text, ImageView m1, ImageView m2, ImageView m3, ArrayList<Item> imageList){

        //shuffle randomize the imageList
        Collections.shuffle(imageList);
        for(int i=0; i<3; i++){
            images[p] = imageList.get(i).getImage();
            words[p] = imageList.get(i).getName();
            soundes1[p] = imageList.get(i).getSound1();
            soundes2[p] = imageList.get(i).getSound2();
            p++;
        }

        m1.setImageResource(images[0]);
        m2.setImageResource(images[1]);
        m3.setImageResource(images[2]);

        Random random = new Random();
        correct=random.nextInt(3);
        text.setText(words[correct]);
        fillSounds();
    }

    protected abstract void fillSounds();
}
