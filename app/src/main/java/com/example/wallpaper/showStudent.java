package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class showStudent extends AppCompatActivity {
    private RecyclerView recyclerView_student;
    private RelativeLayout relativeLayout;
    private myStudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);
        recyclerView_student = findViewById(R.id.rv_showStudent);
        relativeLayout = findViewById(R.id.rl_nothing);
        registerForContextMenu(recyclerView_student);
//        recyclerView_student.setVisibility(View.GONE);
//        relativeLayout.setVisibility(View.GONE);
        FirebaseRecyclerOptions<Student> options =
                new FirebaseRecyclerOptions.Builder<Student>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("student"), Student.class)
                        .build();


//        if(options.getSnapshots().isEmpty()){
//            relativeLayout.setVisibility(View.VISIBLE);
//        }else{
//            recyclerView_student.setVisibility(View.VISIBLE);
//        }
        adapter = new myStudentAdapter(options, getApplicationContext());
//        dbhelper db = new dbhelper(this);
//        Student[] student = db.getAllStudent();
//        if (options != null) {
//            relativeLayout_teacher.setVisibility(View.GONE);
//            recyclerView_teacher.setVisibility(View.VISIBLE);
//        }


        recyclerView_student.setAdapter(adapter);
        recyclerView_student.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}