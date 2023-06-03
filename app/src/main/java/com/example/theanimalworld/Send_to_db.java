package com.example.theanimalworld;

import static android.os.SystemClock.sleep;

import android.content.Context;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Send_to_db extends AppCompatActivity {
   private  FirebaseDatabase db;
    private DatabaseReference reference=FirebaseDatabase.getInstance().getReference("users");


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

        usernameB=findViewById(R.id.usernameB);
        passwordB=findViewById(R.id.passwordB);

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
        USERNAME=user;
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
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Send_to_db.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", user);
                editor.putString("age", age);
                editor.apply();
                // User does not exist, do something else
            }


        }catch (Exception e){
            Toast.makeText(this,"empty fields",Toast.LENGTH_SHORT).show();
            p.setVisibility(View.GONE);
        }

    }
    public int NBR_STARS,RANKLEVEL,TOTAL_TIME,AGE;
    public String USERNAME;

    public void get_rank_data(String user) {
        reference.child(user).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        AGE = dataSnapshot.child("age").getValue(Integer.class);
                        NBR_STARS = dataSnapshot.child("rank").child("total_nbr_stars").getValue(Integer.class);
                        RANKLEVEL = dataSnapshot.child("rank").child("ranklevel").getValue(Integer.class);
                        TOTAL_TIME = dataSnapshot.child("rank").child("total_time").getValue(Integer.class);

                        Toast.makeText(getApplicationContext(), String.valueOf(NBR_STARS), Toast.LENGTH_SHORT).show();
                    } else {
                        // Does not exist
                        Toast.makeText(getApplicationContext(), "Data does not exist", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Failed to read
                    Toast.makeText(getApplicationContext(), "Failed to read data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public HashMap<String, Object> ACTIVITY_INFO;

    public void get_activity_data(String user, int activity_id) {
        String id_of_A = "activity" + String.valueOf(activity_id);
        reference.child(user).child("rank").child(id_of_A).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();

                        int activity_time = dataSnapshot.child("finished_time").getValue(Integer.class);
                        int activity_stars = dataSnapshot.child("nbr_stars").getValue(Integer.class);
                        boolean status = dataSnapshot.child("state").getValue(Boolean.class);

                        HashMap<String, Object> activity_info = new HashMap<>();
                        activity_info.put("finished_time", activity_time);
                        activity_info.put("nbr_stars", activity_stars);
                        activity_info.put("state", status);

                        ACTIVITY_INFO=activity_info;

                        Toast.makeText(getApplicationContext(), String.valueOf(ACTIVITY_INFO.get("state")), Toast.LENGTH_SHORT).show();
                    } else {
                        // Does not exist
                        Toast.makeText(getApplicationContext(), "Data does not exist", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Failed to read
                    Toast.makeText(getApplicationContext(), "Failed to read data", Toast.LENGTH_SHORT).show();
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
            //p.setVisibility(View.GONE);
            User user = new User(username, password,age);
            HashMap<String, Object> rankValues = new HashMap<>();

            rankValues.put("ranklevel", 1);
            rankValues.put("total_nbr_stars", 0);

            //rankValues.put("total_time", System.currentTimeMillis());
            rankValues.put("total_time", 0);

        //add a activity (level)
            HashMap<String, Object> activityValues = new HashMap<>();
            activityValues.put("finished_time", 0);
            activityValues.put("state", false);
            activityValues.put("nbr_stars", 0);
            activityValues.put("id", 0);
            rankValues.put("activity "+String.valueOf(0), activityValues);

            reference.child(username).setValue(user);
            reference.child(username).child("rank").setValue(rankValues);
        reference.child(username).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent=new Intent(Send_to_db.this,Welcome.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void createActivity(int id){
        HashMap<String, Object> activityValues = new HashMap<>();
        activityValues.put("finished_time", 0);
        activityValues.put("state", false);
        activityValues.put("nbr_stars", 0);
        activityValues.put("id", id);
        reference.child(USERNAME).child("rank").child("activity"+String.valueOf(id)).setValue(activityValues)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"creation done",Toast.LENGTH_SHORT).show();

                        } else {
                            // Activity creation failed
                            Exception e = task.getException();
                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
    public void updateUser(String userId,Rank rankId) {
       // HashMap<String, Object> rankValues = new HashMap<>();

       // rankValues.put("ranklevel", rankId.getRanklevel());
       // rankValues.put("total_nbr_stars", rankId.getTotal_nbr_stars());

        //rankValues.put("total_time", System.currentTimeMillis());
      //  rankValues.put("total_time", rankId.getTotal_time());
        String id_activity=String.valueOf(rankId.activity.get_Id());

        reference.child(userId).child("rank").child("total_time").setValue(rankId.getTotal_time());
        reference.child(userId).child("rank").child("total_nbr_stars").setValue(rankId.getTotal_nbr_stars());
        reference.child(userId).child("rank").child("ranklevel").setValue(rankId.getRanklevel());

        reference.child(userId).child("rank").child("activity" + id_activity).child("finished_time").setValue(rankId.activity.getFinished_time());
        reference.child(userId).child("rank").child("activity" + id_activity).child("nbr_stars").setValue(rankId.activity.getNbr_stars()); /*  //add a activity (level)
        HashMap<String, Object> activityValues = new HashMap<>();
        activityValues.put("finished_time", rankId.getActivity().getFinished_time());
        activityValues.put("state", true);
        activityValues.put("nbr_stars",rankId.getActivity().getNbr_stars());
        activityValues.put("id", rankId.getActivity().getId());
        rankValues.put("activity "+String.valueOf(rankId.getActivity().getId()), activityValues);


        reference=FirebaseDatabase.getInstance().getReference("users");
        reference.child(userId).child("rank").updateChildren(rankValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"sucsses update",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext(),"failed update",Toast.LENGTH_SHORT).show();

                }
            }
        });*/
    }

    public void login(View v){
        Toast.makeText(getApplicationContext(),"access",Toast.LENGTH_SHORT).show();

            String user_login=String.valueOf(usernameB.getText());
            String pass_login=hash(String.valueOf(passwordB.getText()));
            if(user_login.equals("") || pass_login.equals("")){
                Toast.makeText(getApplicationContext(),"empty fields",Toast.LENGTH_SHORT).show();
                return;
            }
        else{
                USERNAME=user_login;
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Send_to_db.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", user_login);
                editor.apply();

            reference = FirebaseDatabase.getInstance().getReference("users");
            reference.child(user_login).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            DataSnapshot dataSnapshot = task.getResult();
                            String user_get = dataSnapshot.child("username").getValue(String.class);
                            String pass_get = dataSnapshot.child("password").getValue(String.class);
                            if (user_get.equals(user_login) && pass_get.equals(pass_login)) {
                                Toast.makeText(getApplicationContext(), "correct information", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Send_to_db.this, Welcome.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            //does not exist
                            Toast.makeText(getApplicationContext(), "Does not exist", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //failed to read
                        Toast.makeText(getApplicationContext(), "Failed to read", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
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