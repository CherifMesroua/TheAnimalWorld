package com.example.theanimalworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Send_to_db extends AppCompatActivity {
   private  FirebaseDatabase db;
    private DatabaseReference reference;
    Button btnA,btnB;
    TextView txtA,txtB;
    private TextInputEditText usernameA,passwordA,usernameB,passwordB,ageA;
    ProgressBar p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_to_db);
        btnA=findViewById(R.id.A);
        btnB=findViewById(R.id.B);
        LinearLayout lnA=findViewById(R.id.registerLayout);
        LinearLayout lnB=findViewById(R.id.loginLayout);
        txtA=findViewById(R.id.txtA);
        txtB=findViewById(R.id.txtB);
        usernameA=findViewById(R.id.usernameA);
        passwordA=findViewById(R.id.passwordA);
        ageA=findViewById(R.id.ageA);
        p=findViewById(R.id.p);
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
    public void signup(View view){
        p.setVisibility(View.VISIBLE);
        String user,pass,age;
        int Nage;
        user=String.valueOf(usernameA.getText());
        pass=String.valueOf(passwordA.getText());
        age=String.valueOf(ageA.getText());
        Nage=Integer.parseInt(age);
        if(TextUtils.isEmpty(user) || user.length()<8){
            Toast.makeText(this,"username not valid",Toast.LENGTH_SHORT).show();
            p.setVisibility(View.GONE);
            return;
        }if(TextUtils.isEmpty(pass) || pass.length()<8){
            Toast.makeText(this,"Password not valid, must be more than 8 characters",Toast.LENGTH_SHORT).show();
            p.setVisibility(View.GONE);
            return;
        }
        if(TextUtils.isEmpty(age) || age.length()>2){
            Toast.makeText(this,"age not valid, <10",Toast.LENGTH_SHORT).show();
            p.setVisibility(View.GONE);
            return;
        }
        writeNewUser(user,hash(pass),Nage);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Send_to_db.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", user);
        editor.putString("age", age);
        editor.apply();
    }



    public void writeNewUser(String username, String password, int age) {
        p.setVisibility(View.GONE);
        User user = new User(username, password,age);
        db=FirebaseDatabase.getInstance();
        reference=db.getReference("users");
        reference.child(username).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent=new Intent(Send_to_db.this,Welcome.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void updateUser(String userId,String username) {

    }

    public static String hash(String input) {
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add input bytes to digest
            md.update(input.getBytes());

            // Get the hash's bytes
            byte[] digest = md.digest();

            // Convert the bytes to a hex string
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception here
            return null;
        }
    }
}