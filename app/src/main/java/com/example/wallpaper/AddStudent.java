package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {
    String[] schoolStd_item = {"--select class--", "1st", "2nd", "3rd"};
    Spinner std_spinner;
    Button addBtn;
    EditText Etfname, Etlname, Etrollno, Etaddress, Etemail, Etphone, Etdob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        std_spinner = findViewById(R.id.std_spinner);
        addBtn = findViewById(R.id.add_addStudent);
        Etfname = findViewById(R.id.Fname_addStudent);
        Etlname = findViewById(R.id.Lname_addStudent);
        Etrollno = findViewById(R.id.rollNO_addStudent);
        Etaddress = findViewById(R.id.address_addStudent);
        Etemail = findViewById(R.id.email_addStudent);
        Etphone = findViewById(R.id.phone_addStudent);
        Etdob = findViewById(R.id.dob_addStudent);

        final dbhelper db=new dbhelper(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, schoolStd_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//        std_spinner.setPopupBackgroundResource(R.drawable.board);
        std_spinner.setAdapter(arrayAdapter);

        std_spinner.setSelection(0, true);
//        View v = std_spinner.getSelectedView();
//        ((TextView)v).setTextColor(Color.BLACK);
//        View view=std_spinner.getSelectedView();
//        ((TextView)view).setTextColor(Color.BLACK);
        std_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.BLACK);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Etfname.getText().length() == 0 || Etlname.getText().length() == 0 || Etrollno.getText().length() == 0 || Etaddress.getText().length() == 0 || Etemail.getText().length() == 0 || Etphone.getText().length() == 0 || Etdob.getText().length() == 0) {
                    Toast.makeText(AddStudent.this, "please enter detail.", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = db.addStudent(Etfname.getText().toString(), Etlname.getText().toString(), Etrollno.getText().toString(), Etaddress.getText().toString(), Etemail.getText().toString(), Etphone.getText().toString(),Etdob.getText().toString());
                    if (isInserted) {
                        Toast.makeText(AddStudent.this, "Data Saved", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(AddStudent.this, .class);
//                        startActivity(intent);
                    } else {
                        Toast.makeText(AddStudent.this, "Data not Saved", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
