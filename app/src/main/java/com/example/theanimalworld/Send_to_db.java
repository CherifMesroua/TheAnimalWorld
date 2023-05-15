package com.example.theanimalworld;

import static android.os.SystemClock.sleep;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


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

        reference=FirebaseDatabase.getInstance().getReference("users");

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
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfUserExists(String.valueOf(usernameA.getText()));
            }
        });
    }
    public void signup(){
        p.setVisibility(View.VISIBLE);
        sleep(5);
        String user,pass,age;
        int Nage;
        user=String.valueOf(usernameA.getText());
        pass=String.valueOf(passwordA.getText());
        age=String.valueOf(ageA.getText());
        try{
            Nage = Integer.parseInt(age);
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
           else {
                writeNewUser(user, hash(pass), Nage);
                /*SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Send_to_db.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", user);
                editor.putString("age", age);
                editor.apply();*/
                // User does not exist, do something else
            }


        }catch (Exception e){
            Toast.makeText(this,"empty fields",Toast.LENGTH_SHORT).show();
            p.setVisibility(View.GONE);
        }

    }

    private void getdata(String user) {

        reference.child(user).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot dataSnapshot= task.getResult();
                        String username=String.valueOf(dataSnapshot.child("username").getValue());
                        String age=String.valueOf(dataSnapshot.child("age").getValue());
                    }else{
                        //does not exist
                    }
                }else{
                    //failed to read
                }
            }
        });
    }
    private void checkIfUserExists(String user) {
        reference.child(user).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                boolean userExists = false;
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        userExists = true;
                        Toast.makeText(getApplicationContext(),"user already exist",Toast.LENGTH_SHORT).show();
                        p.setVisibility(View.GONE);
                    }
                }
                if(!userExists){
                    signup();
                }
                }
        });
    }


    public void writeNewUser(String username, String password, int age) {
            p.setVisibility(View.GONE);
            User user = new User(username, password,age);
            HashMap<String, Object> rankValues = new HashMap<>();
            long initial_time= 0;

            rankValues.put("ranklevel", 1);
            rankValues.put("total_nbr_stars", 0);

            //rankValues.put("total_time", System.currentTimeMillis());
            rankValues.put("total_time", initial_time);

        //add a activity (level)
            HashMap<String, Object> activityValues = new HashMap<>();
            activityValues.put("finished_time", initial_time);
            activityValues.put("state", false);
            activityValues.put("nbr_stars", 0);
            activityValues.put("id", 0);
            rankValues.put("activity "+String.valueOf(0), activityValues);

            reference.child(username).setValue(user);
            reference.child(username).child("rank").setValue(rankValues);
       /* reference.child(username).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent=new Intent(Send_to_db.this,Welcome.class);
                startActivity(intent);
                finish();
            }
        });*/
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