package com.example.wallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class homeActivity extends AppCompatActivity {
    ImageView login_b;
    ProgressBar pb_home;
    DatabaseReference reference, referenceCU;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        login_b = findViewById(R.id.login);
        pb_home=findViewById(R.id.pb_home);
        reference = FirebaseDatabase.getInstance().getReference().child("teacher");
        referenceCU = FirebaseDatabase.getInstance().getReference("currentUser");

        pb_home.setVisibility(View.VISIBLE);
        referenceCU.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String currentuser = dataSnapshot.getValue(String.class);
                getrole(currentuser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        login_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homeActivity.this, login.class);
                startActivity(intent);
            }
        });


    }

    private void getrole(final String currentuser) {
        if (!currentuser.equals("null")) {
            reference.child(currentuser).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String currentrole = dataSnapshot.child("role").getValue(String.class);
                    senduser(currentrole, currentuser);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void senduser(String currentrole, String currentuser) {
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
        intent.putExtra("currentUserExtra", currentuser);
        pb_home.setVisibility(View.GONE);
        startActivity(intent);

    }


}

