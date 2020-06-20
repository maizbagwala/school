package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ShowTeacherActivity extends AppCompatActivity {
    private RecyclerView recyclerView_teacher;
    private RelativeLayout relativeLayout_teacher;
    private myTeacheradapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_teacher);

        recyclerView_teacher = findViewById(R.id.rv_showTeacher);
        relativeLayout_teacher = findViewById(R.id.rl_teacher);
        registerForContextMenu(recyclerView_teacher);
//        recyclerView_teacher.setVisibility(View.GONE);
//        relativeLayout_teacher.setVisibility(View.GONE);
        FirebaseRecyclerOptions<Teacher> options =
                new FirebaseRecyclerOptions.Builder<Teacher>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("teacher"), Teacher.class)
                        .build();

        adapter = new myTeacheradapter(options, getApplicationContext());
//        dbhelper db = new dbhelper(this);
//        Student[] student = db.getAllStudent();
//        if (options != null) {
//            relativeLayout_teacher.setVisibility(View.GONE);
//            recyclerView_teacher.setVisibility(View.VISIBLE);
//        }


        recyclerView_teacher.setAdapter(adapter);
        recyclerView_teacher.setLayoutManager(new LinearLayoutManager(this));


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