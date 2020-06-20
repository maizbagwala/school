package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class TeacherDashboardActivity extends AppCompatActivity {
    TextView tvname;
    ImageView navbtn;
    CardView add_student, show_student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");


        tvname = findViewById(R.id.tvname_teacher);
        navbtn = findViewById(R.id.navbtn_teacher);
        add_student = findViewById(R.id.card_add_student_teacher);
        show_student = findViewById(R.id.card_show_student_teacher);

        tvname.setText(id);

        navbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbtmNav();

            }
        });

        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentaddstudent = new Intent(TeacherDashboardActivity.this, AddStudent.class);
                startActivity(intentaddstudent);
            }
        });

        show_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentshowstudent = new Intent(TeacherDashboardActivity.this, showStudent.class);
                startActivity(intentshowstudent);
            }
        });
    }

    private void openbtmNav() {
        final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(TeacherDashboardActivity.this, R.style.BottomSheet);
        View bottomSheet = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_layout_teacher, (LinearLayout) findViewById(R.id.BottomSheetCont));

        bottomSheet.findViewById(R.id.ll_profile_sheet_teacher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TeacherDashboardActivity.this, "profile clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheet.findViewById(R.id.ll_mystudent_sheet_teacher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TeacherDashboardActivity.this, "My Student clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheet.findViewById(R.id.ll_notice_sheet_teacher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TeacherDashboardActivity.this, "Notice clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheet.findViewById(R.id.ll_studymaterial_sheet_teacher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TeacherDashboardActivity.this, "Study Material clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheet.findViewById(R.id.ll_Admindetail_sheet_teacher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TeacherDashboardActivity.this, "Admin detail clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheet.findViewById(R.id.ll_logout_sheet_teacher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(TeacherDashboardActivity.this, "Logout clicked", Toast.LENGTH_SHORT).show();
                SessionManager manager=new SessionManager(TeacherDashboardActivity.this);
                manager.logoutUserFromSession();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheet.findViewById(R.id.ll_done_teacher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(bottomSheet);
        bottomSheetDialog.show();
    }
}
