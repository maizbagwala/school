package com.example.wallpaper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class StudentDashboardActivity extends AppCompatActivity {
ImageView navbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        navbtn=findViewById(R.id.navbtn);

        navbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(StudentDashboardActivity.this,R.style.BottomSheet);
                View bottomSheet = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_layout_student,(LinearLayout)findViewById(R.id.BottomSheetCont));

                bottomSheet.findViewById(R.id.ll_profile_sheet_student).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(StudentDashboardActivity.this, "Profile clicked", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheet.findViewById(R.id.ll_notice_sheet_student).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(StudentDashboardActivity.this, "Notice clicked", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheet.findViewById(R.id.ll_studymaterial_sheet_student).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(StudentDashboardActivity.this, "Study Material clicked", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheet.findViewById(R.id.ll_teacherdetail_sheet_student).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(StudentDashboardActivity.this, "Teacher Detail clicked", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheet.findViewById(R.id.ll_Admindetail_sheet_student).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(StudentDashboardActivity.this, "Admin Detail clicked", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheet.findViewById(R.id.ll_logout_sheet_student).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(StudentDashboardActivity.this, "Logout clicked", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheet.findViewById(R.id.ll_done_student).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheet);
                bottomSheetDialog.show();
            }
        });

    }
}
