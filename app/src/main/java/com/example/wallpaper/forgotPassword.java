package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forgotPassword extends AppCompatActivity {
Button sendOtp,verifyOtp;
EditText email,otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email=findViewById(R.id.editem);
        otp=findViewById(R.id.editTextOTP);
        verifyOtp=findViewById(R.id.btn_verify);
        sendOtp=findViewById(R.id.btn_sendotp);


        verifyOtp.setVisibility(View.GONE);
        otp.setVisibility(View.GONE);

        verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(forgotPassword.this, "verified", Toast.LENGTH_SHORT).show();
            }
        });

        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOtp.setVisibility(View.GONE);
                email.setVisibility(View.GONE);
                verifyOtp.setVisibility(View.VISIBLE);
                otp.setVisibility(View.VISIBLE);
            }
        });
    }
}
