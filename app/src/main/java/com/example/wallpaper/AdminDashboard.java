package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class AdminDashboard extends AppCompatActivity {
    CardView addTeacher,showTeacher,card_add_event_admin;
    ImageView navbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        addTeacher=findViewById(R.id.card_add_teacher_admin);
        showTeacher=findViewById(R.id.card_show_teacher_admin);
        card_add_event_admin=findViewById(R.id.card_add_event_admin);
        navbtn=findViewById(R.id.navbtn_admin);

        addTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminDashboard.this,AddFaculty.class);
                startActivity(intent);
            }
        });

        showTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminDashboard.this,ShowTeacherActivity.class);
                startActivity(intent);
            }
        });

        card_add_event_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminDashboard.this,StudyMaterialActivity.class);
                startActivity(intent);
            }
        });

        navbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openbtmnav();
            }
        });
    }

    private void openbtmnav() {
        final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(AdminDashboard.this,R.style.BottomSheet);
        View bottomSheet = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_layout_admin,(LinearLayout)findViewById(R.id.BottomSheetCont));
        bottomSheet.findViewById(R.id.ll_profile_sheet_admin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "Profile clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheet.findViewById(R.id.ll_notice_sheet_admin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "Notice clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheet.findViewById(R.id.ll_studymaterial_sheet_admin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "Study Material clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheet.findViewById(R.id.ll_teacher_admin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "Teacher clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheet.findViewById(R.id.ll_student_admin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "student clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheet.findViewById(R.id.ll_logout_sheet_admin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "Logout clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheet.findViewById(R.id.ll_done_admin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "delete clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(bottomSheet);
        bottomSheetDialog.show();
    }
}
