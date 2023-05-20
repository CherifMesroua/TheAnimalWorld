package com.example.theanimalworld;

import android.content.Context;
import android.media.MediaPlayer;

public class Game_Sounds {
    MediaPlayer correct_answer,wrong_answer,hurry_up;

    public Game_Sounds(Context context) {
        correct_answer=MediaPlayer.create(context,R.raw.correct_answer);
        wrong_answer=MediaPlayer.create(context,R.raw.wrong_answer);
        hurry_up=MediaPlayer.create(context,R.raw.hurry_up);
    }
    public void play_hurry_up(){
        hurry_up.start();
    }
    public void play_correct(){
        correct_answer.start();
    }
    public void play_wrong(){
        wrong_answer.start();
    }

}
