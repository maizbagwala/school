package com.example.wallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddStudent extends AppCompatActivity {
//    String[] schoolStd_item = {"--select class--", "1st", "2nd", "3rd"};
    //    Spinner std_spinner;
    Button addBtn;
    final String role="s";
    EditText Etfname_s, Etlname_s, Etrollno_s, Etaddress_s, Etemail_s, Etphone_s, Etdob_s;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("student");

//        std_spinner = findViewById(R.id.std_spinner);
        addBtn = findViewById(R.id.add_addStudent);
        Etfname_s = findViewById(R.id.Fname_addStudent);
        Etlname_s = findViewById(R.id.Lname_addStudent);
        Etrollno_s = findViewById(R.id.rollNO_addStudent);
        Etaddress_s = findViewById(R.id.address_addStudent);
        Etemail_s = findViewById(R.id.email_addStudent);
        Etphone_s = findViewById(R.id.phone_addStudent);
        Etdob_s = findViewById(R.id.dob_addStudent);

//        final dbhelper db=new dbhelper(this);

//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, schoolStd_item);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
////        std_spinner.setPopupBackgroundResource(R.drawable.board);
//        std_spinner.setAdapter(arrayAdapter);
//
//        std_spinner.setSelection(0, true);
////        View v = std_spinner.getSelectedView();
////        ((TextView)v).setTextColor(Color.BLACK);
////        View view=std_spinner.getSelectedView();
////        ((TextView)view).setTextColor(Color.BLACK);
//        std_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                ((TextView) view).setTextColor(Color.BLACK);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    String pw = Etfname_s.getText().toString()+Etrollno_s;
                    String id = reference.push().getKey();
                    Student student = new Student(id, Etfname_s.getText().toString(), Etlname_s.getText().toString(),Etrollno_s.getText().toString(), Etaddress_s.getText().toString(), Etemail_s.getText().toString(), pw,Etphone_s.getText().toString(), Etdob_s.getText().toString(), role);
                    reference.child(id).setValue(student);
                    Toast.makeText(AddStudent.this, "data inserted ", Toast.LENGTH_SHORT).show();
                    showdialog();
                }
            }
        });


//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Etfname.getText().length() == 0 || Etlname.getText().length() == 0 || Etrollno.getText().length() == 0 || Etaddress.getText().length() == 0 || Etemail.getText().length() == 0 || Etphone.getText().length() == 0 || Etdob.getText().length() == 0) {
//                    Toast.makeText(AddStudent.this, "please enter detail.", Toast.LENGTH_SHORT).show();
//                } else {
//                    boolean isInserted = db.addStudent(Etfname.getText().toString(), Etlname.getText().toString(), Etrollno.getText().toString(), Etaddress.getText().toString(), Etemail.getText().toString(), Etphone.getText().toString(),Etdob.getText().toString());
//                    if (isInserted) {
//                        Toast.makeText(AddStudent.this, "Data Saved", Toast.LENGTH_LONG).show();
////                        Intent intent = new Intent(AddStudent.this, .class);
////                        startActivity(intent);
//                    } else {
//                        Toast.makeText(AddStudent.this, "Data not Saved", Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//        });

//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                reference=database.getReference().child("teacher");
//                Teacher teacher=new Teacher(Etfname.getText().toString(), Etlname.getText().toString(), Etaddress.getText().toString(), Etemail.getText().toString(), Etphone.getText().toString(),Etdob.getText().toString());
//                reference.setValue(teacher);
//            }
//        });
    }

    public boolean checkValidation() {
        if (Etfname_s.getText().toString().length() == 0) {
            Etfname_s.setError("enter First name");
            return false;
        }
        if (Etlname_s.getText().toString().length() == 0) {
            Etlname_s.setError("enter Last name");
            return false;
        }
        if (Etrollno_s.getText().toString().length() == 0) {
            Etrollno_s.setError("enter roll number");
            return false;
        }
        if (Etaddress_s.getText().toString().length() == 0) {
            Etaddress_s.setError("enter Address");
            return false;
        }

        if (Etemail_s.getText().toString().length() == 0) {
            Etemail_s.setError("enter Email");
            return false;
        }
        if (Etphone_s.getText().toString().length() == 0) {
            Etphone_s.setError("enter Phone");
            return false;
        }
        if (Etdob_s.getText().toString().length() == 0) {
            Etdob_s.setError("enter DOB");
            return false;
        }

        return true;
    }



    public void showdialog() {
        final Dialog dialog = new Dialog(AddStudent.this);
        dialog.setContentView(R.layout.mydialog);
        dialog.show();
        Button db = dialog.findViewById(R.id.button);
        db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Etfname_s.setText("");
                Etlname_s.setText("");
                Etaddress_s.setText("");
                Etemail_s.setText("");
                Etrollno_s.setText("");
                Etphone_s.setText("");
                Etdob_s.setText("");
                dialog.dismiss();
            }
        });


    }

}
