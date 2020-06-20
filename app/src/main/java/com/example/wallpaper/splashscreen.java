package com.example.wallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import static com.example.wallpaper.SessionManager.KEY_ID;

public class splashscreen extends AppCompatActivity {
    DatabaseReference reference, referenceCU;
    SessionManager sessionManager;

    private static int timeOut = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        reference = FirebaseDatabase.getInstance().getReference().child("teacher");
//        referenceCU = FirebaseDatabase.getInstance().getReference("currentUser");
        sessionManager = new SessionManager(this);

//        referenceCU.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String currentuser = dataSnapshot.getValue(String.class);
//                getrole(currentuser);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        String sessionCurrentUser = sessionManager.getIdFromSession();
//        HashMap<String, String> user = sessionManager.getUserDetailFromSession();
        getrole(sessionCurrentUser);


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent=new Intent(splashscreen.this, homeActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },timeOut);
    }

    private void getrole(final String sessionCurrentUser) {


        if (sessionCurrentUser != null) {
            reference.child(sessionCurrentUser).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String currentrole = dataSnapshot.child("role").getValue(String.class);
                    senduser(currentrole, sessionCurrentUser);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
        }

    }

    private void senduser(String currentrole, String sessionCurrentUser) {
        Intent intent;
        switch (currentrole) {
            case "t":
                intent = new Intent(this, TeacherDashboardActivity.class);
                break;
            case "s":
                intent = new Intent(this, StudentDashboardActivity.class);
                break;
            default:
                intent = new Intent(this, AdminDashboard.class);
        }
        intent.putExtra("currentUserExtra", sessionCurrentUser);
        startActivity(intent);
        finish();

    }
}
