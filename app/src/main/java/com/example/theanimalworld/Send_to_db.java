package com.example.theanimalworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Send_to_db extends AppCompatActivity {
    private DatabaseReference mDatabase;
    Button btnA,btnB;
    TextView txtA,txtB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_to_db);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        btnA=findViewById(R.id.A);
        btnB=findViewById(R.id.B);
        LinearLayout lnA=findViewById(R.id.registerLayout);
        LinearLayout lnB=findViewById(R.id.loginLayout);
        txtA=findViewById(R.id.txtA);
        txtB=findViewById(R.id.txtB);
        txtA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnA.setVisibility(View.GONE);
                lnB.setVisibility(View.VISIBLE);
            }
        });
        txtB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnB.setVisibility(View.GONE);
                lnA.setVisibility(View.VISIBLE);
            }
        });
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    btnA.setVisibility(View.GONE);
                    btnB.setVisibility(View.GONE);
                    lnA.setVisibility(View.VISIBLE);
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnA.setVisibility(View.GONE);
                btnB.setVisibility(View.GONE);
                lnB.setVisibility(View.VISIBLE);
            }
        });
    }




    public void writeNewUser(String userId, String username, String password, int age) {
        User user = new User(username, password,age);

        mDatabase.child("animalworld").child(userId).setValue(user);

    }


    public void updateUser(String userId,String username) {
        mDatabase.child("animalworld").child(userId).child("username").setValue(username);

    }
}