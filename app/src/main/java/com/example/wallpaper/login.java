package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    TextView tv,tvf;
    Button log_check;
    EditText us,ps;
    dbhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv=findViewById(R.id.textView2);
        tvf=findViewById(R.id.textView3);
        us=findViewById(R.id.email);
        ps=findViewById(R.id.password);
        log_check=findViewById(R.id.login_btn);
        db=new dbhelper(this);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,reg.class);
                startActivity(intent);
            }
        });

        tvf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,forgotPassword.class);
                startActivity(intent);
            }
        });
        log_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean check=checkValidation();

                if(check){
                    try {
//                        Log.d("mmm", "onClick: pohoche");

                        User u1 = db.checkUser(us.getText().toString(), ps.getText().toString());
//                        Log.d("mmm", "onClick: curser check kiya");

                        if (u1 != null) {
//                    Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                            Bundle bundle = new Bundle();
                            bundle.putString("name", u1.getName());
                            bundle.putString("phone", u1.getPhone());
                            bundle.putString("email", u1.getEmail());
                            bundle.putString("pass", u1.getPassword());
                            bundle.putInt("gender", u1.getGender());
                            bundle.putString("dob", u1.getDob());
                            Intent intent = new Intent(login.this, profile.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            Toast.makeText(login.this, "Login successful", Toast.LENGTH_LONG).show();
                            finish();


                        }else if (  (us.getText().toString()).equals("admin")  &&  (ps.getText().toString()).equals("admin") ) {
                            Intent intent=new Intent(login.this,AdminDashboard.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(login.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
//                    cur.moveToFirst();
//                    String na=cur.getString(1);
//                    Log.d("mmm", "onClick: "+na);
//                    String ph=cur.getString(2);
//                    String em=cur.getString(3);
//                    String date=cur.getString(6);
//                    Intent intent=new Intent(login.this,profile.class);
//                    intent.putExtra("name",na);
//                    intent.putExtra("phone",ph);
//                    intent.putExtra("email",em);
//                    intent.putExtra("dob",date);
//                    startActivity(intent);
                        }
                    }catch (RuntimeException e){
                        Toast.makeText(login.this, ""+e, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(login.this, "enter valid details.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public boolean checkValidation(){
        if (us.getText().toString().length()==0){
            us.setError("enter username");
            return false;
        }
        if (ps.getText().toString().length()==0){
            ps.setError("enter password");
            return false;
        }
        return true;
    }
}
