package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class showStudent extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);
        recyclerView = findViewById(R.id.rv_showStudent);
        relativeLayout = findViewById(R.id.rl);
        registerForContextMenu(recyclerView);
//        relativeLayout.setVisibility(View.GONE);

        dbhelper db = new dbhelper(this);
        Student[] student = db.getAllStudent();
        if (student != null) {
            relativeLayout.setVisibility(View.GONE);
//            recyclerView.setVisibility(View.GONE);
        }


        recyclerView.setAdapter(new myadapter(this,student));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}