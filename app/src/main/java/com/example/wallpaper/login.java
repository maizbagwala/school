package com.example.wallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class login extends AppCompatActivity {
    TextView tv, tvf;
    Button log_check;
    EditText us, ps;
    dbhelper db;
    Map<String, Object> data;
    FirebaseDatabase database;
    DatabaseReference reference,referenceCU;
    public static String current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv = findViewById(R.id.textView2);
        tvf = findViewById(R.id.textView3);
        us = findViewById(R.id.email);
        ps = findViewById(R.id.password);
        log_check = findViewById(R.id.login_btn);
        db = new dbhelper(this);
        database = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("teacher");
        referenceCU = FirebaseDatabase.getInstance().getReference();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, reg.class);
                startActivity(intent);
            }
        });

        tvf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, forgotPassword.class);
                startActivity(intent);
            }
        });
        log_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean check = checkValidation();
                if (check) {
                    try {

                        //firebase vala
                        if ((us.getText().toString()).equals("admin") && (ps.getText().toString()).equals("admin")) {
                            Intent intent = new Intent(login.this, AdminDashboard.class);
                            startActivity(intent);
                        }

                        if ((us.getText().toString()).equals("student") && (ps.getText().toString()).equals("student")) {
                            Intent intent = new Intent(login.this, StudentDashboardActivity.class);
                            startActivity(intent);
                        }
//                        Query query = reference.orderByChild("id").equalTo(us.getText().toString());
                        reference.orderByChild("email").equalTo(us.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        data = (Map<String, Object>) snapshot.getValue();
//                                        Log.i("loginm", "onDataChange: "+dataSnapshot);
//                                        Log.i("loginm", "onDataChange: "+data.get("pwd"));
//                                        Log.i("loginm", "onDataChange: "+dataSnapshot.child("teacher").child("-M9YWi99oNMZHhiespYJ"));

                                    }

//                                    String passwordfromDb = dataSnapshot.child(us.getText().toString()).child("password").getValue(String.class);
                                    String passwordfromDb = data.get("pwd").toString();
                                    if (passwordfromDb.equals(ps.getText().toString())) {
                                        Toast.makeText(login.this, "login successful", Toast.LENGTH_LONG).show();
                                        Intent intent;
                                        switch (data.get("role").toString()) {
                                            case "t":
                                                intent = new Intent(getApplicationContext(), TeacherDashboardActivity.class);
                                                break;
                                            case "s":
                                                intent = new Intent(getApplicationContext(), StudentDashboardActivity.class);
                                                break;
                                            default:
                                                intent = new Intent(getApplicationContext(), AdminDashboard.class);
                                                break;
                                        }

                                        SessionManager sessionManager =new SessionManager(login.this);
                                        sessionManager.createLoginSession(data.get("id").toString());

                                        current_user = data.get("id").toString();
                                        referenceCU = FirebaseDatabase.getInstance().getReference();
                                        referenceCU.child("currentUser").setValue(current_user);
                                        Toast.makeText(login.this, current_user, Toast.LENGTH_SHORT).show();
                                        intent.putExtra("id", data.get("id").toString());
                                        Log.i("loginm", "onDataChange: " + data.get("id").toString());
                                        startActivity(intent);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }

                        });


                        //yaha tak


                        //old vala
//                        Log.d("mmm", "onClick: pohoche");

//                        User u1 = db.checkUser(us.getText().toString(), ps.getText().toString());
////                        Log.d("mmm", "onClick: curser check kiya");
//
//                        if (u1 != null) {
//////                    Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_LONG).show();
////                            Bundle bundle = new Bundle();
////                            bundle.putString("name", u1.getName());
////                            bundle.putString("phone", u1.getPhone());
////                            bundle.putString("email", u1.getEmail());
////                            bundle.putString("pass", u1.getPassword());
////                            bundle.putInt("gender", u1.getGender());
////                            bundle.putString("dob", u1.getDob());
////                            Intent intent = new Intent(login.this, profile.class);
////                            intent.putExtras(bundle);
////                            startActivity(intent);
//
//                        //yaha tak
//                        Toast.makeText(login.this, "Login successful", Toast.LENGTH_LONG).show();
//                        finish();
//
//
//                    }else if ((us.getText().toString()).equals("admin") && (ps.getText().toString()).equals("admin")) {
//                        Intent intent = new Intent(login.this, AdminDashboard.class);
//                        startActivity(intent);

//                    } else {
//                        Toast.makeText(login.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
////                    cur.moveToFirst();
////                    String na=cur.getString(1);
////                    Log.d("mmm", "onClick: "+na);
////                    String ph=cur.getString(2);
////                    String em=cur.getString(3);
////                    String date=cur.getString(6);
////                    Intent intent=new Intent(login.this,profile.class);
////                    intent.putExtra("name",na);
////                    intent.putExtra("phone",ph);
////                    intent.putExtra("email",em);
////                    intent.putExtra("dob",date);
////                    startActivity(intent);
//                    }
                    } catch (RuntimeException e) {
                        Toast.makeText(login.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(login.this, "enter valid details.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public boolean checkValidation() {
        if (us.getText().toString().length() == 0) {
            us.setError("enter username");
            return false;
        }
        if (ps.getText().toString().length() == 0) {
            ps.setError("enter password");
            return false;
        }
        return true;
    }
}
// btnLogin.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        Query query=reference.orderByChild("username").equalTo(l_us.getText().toString());
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        if (dataSnapshot.exists()){
//        String passwordfromDb=dataSnapshot.child(l_us.getText().toString()).child("password").getValue(String.class);
//
//        if (passwordfromDb.equals(l_pw.getText().toString())){
//        Intent intent=new Intent(getApplicationContext(),home.class);
//        intent.putExtra("phone",dataSnapshot.child(l_us.getText().toString()).child("phone").getValue(String.class));
//        intent.putExtra("username",dataSnapshot.child(l_us.getText().toString()).child("username").getValue(String.class));
//        intent.putExtra("password",dataSnapshot.child(l_us.getText().toString()).child("password").getValue(String.class));
//        startActivity(intent);
//        }
//        }
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError databaseError) {
//
//        }
//        });
//        }
//        });