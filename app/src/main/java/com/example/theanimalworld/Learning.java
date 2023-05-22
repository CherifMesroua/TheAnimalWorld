package com.example.theanimalworld;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Learning extends AppCompatActivity {
    private Handler handler;

    Button button;
    TextView text1;
    ImageView img1, img2, img3, img4, img5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        img1 = findViewById(R.id.djaj);
        img2 = findViewById(R.id.hisan);
        img3 = findViewById(R.id.dikromi);
        img4 = findViewById(R.id.kalb);
        img5 = findViewById(R.id.himar);
        text1 = findViewById(R.id.text1);
        button = findViewById(R.id.gototest);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                block_img();
                handler = new Handler();
                showTextViewForDuration(2000, "هذه دجاجة");
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler = new Handler();
                block_img();
                showTextViewForDuration(2000, "هذا حصان");
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler = new Handler();
                block_img();
                showTextViewForDuration(2000, "هذاديك رومي");
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler = new Handler();
                block_img();
                showTextViewForDuration(2000, "هذا كلب");
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                block_img();
                handler = new Handler();
                showTextViewForDuration(2000, "هذا حمار");
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Learning.this,Landlevel1.class);
                startActivity(intent);
            }
        });
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
    private void showTextViewForDuration(long duration, String textView) {
        text1.setText(textView);
        text1.setVisibility(View.VISIBLE);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                text1.setVisibility(View.INVISIBLE);
                unblock_img();
            }
        }, duration);

    }
}