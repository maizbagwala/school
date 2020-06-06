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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class reg extends AppCompatActivity {
Button reg;
dbhelper db;
Spinner role;

EditText name,phone,email,pass,dob;
RadioGroup radioGroup;
Boolean check=true;
//RadioButton selected_gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        reg=findViewById(R.id.button5);
        db=new dbhelper(this);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        dob=findViewById(R.id.dob);
        radioGroup=findViewById(R.id.rg);
        role=findViewById(R.id.role);

        String[] role_item={"--select--","Admin","Teacher","Student"};
        ArrayAdapter<String> role_list=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,role_item);
        role_list.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        role.setAdapter(role_list);


        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView)view).setTextColor(getResources().getColor(R.color.colorPrimary));


                switch (position){
                    case 1:
                        dob.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                check=false;

            }
        });


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer gender=0;
                int selectedId=radioGroup.getCheckedRadioButtonId();

                switch (selectedId){
                    case R.id.male:
                        gender=1;
                        break;
                    case R.id.female:
                        gender=2;
                        break;
                }
                if(name.getText().length()==0||phone.getText().length()==0||email.getText().length()==0||pass.getText().length()==0||dob.getText().length()==0){
                    Toast.makeText(reg.this, "please enter detail.", Toast.LENGTH_SHORT).show();
                }else {
                    boolean isInserted=db.insertData(name.getText().toString(),phone.getText().toString(),email.getText().toString(),pass.getText().toString(),gender,dob.getText().toString());
                    if(isInserted){
                        Toast.makeText(reg.this, "Data Saved", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(reg.this,login.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(reg.this, "Data not Saved", Toast.LENGTH_LONG).show();
                    }

                }


//                Intent intent=new Intent(reg.this,profile.class);
//                startActivity(intent);
            }
        });
    }
}
