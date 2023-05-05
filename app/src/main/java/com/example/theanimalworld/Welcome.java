package com.example.theanimalworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {
    ImageView wplay,wsetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Welcome.this);
        String user = preferences.getString("username", "");
        if (user.equals("")) {
            Intent intent = new Intent(Welcome.this, Send_to_db.class);
            startActivity(intent);
            finish();
        }
        Toast.makeText(this,user,Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_welcome);
        wplay=findViewById(R.id.Wplay);
        wsetting=findViewById(R.id.Wsetting);



    }
}