package com.example.wallpaper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity {
    dbhelper db;
    TextView name,phone,email,dob,gender,header_name,header_class,header_rollno;
    CircleImageView profile_image;
    private int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db=new dbhelper(this);
//        name=findViewById(R.id.textName);
        header_name=findViewById(R.id.name_profile);
//        header_class=findViewById(R.id.header_class);
//        header_rollno=findViewById(R.id.header_rollno);
//        phone=findViewById(R.id.textPhone);
//        email=findViewById(R.id.textEmail);
//        dob=findViewById(R.id.textDob);
//        gender=findViewById(R.id.textGender);
        profile_image=findViewById(R.id.image_profile);

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent,"select picture"),PICK_IMAGE_REQUEST);
            }
        });

//        Cursor cursor=db.selectData();
//        cursor.moveToFirst();
//        name.setText(cursor.getString(1));
//        email.setText(cursor.getString(3));
//        phone.setText(cursor.getString(2));
//        dob.setText(cursor.getString(6));

        Bundle data=getIntent().getExtras();
        if (data!=null){
            String na=data.getString("name");
            String ph=data.getString("phone");
            String em=data.getString("email");
            String ddd=data.getString("dob");
            int g=data.getInt("gender");
//            name.setText(na);
            header_name.setText(na);
//            email.setText(em);
//            phone.setText(ph);
//            dob.setText(ddd);
//
//            if (g==1){
//                gender.setText("Male");
//            }else if (g==2){
//              gender.setText("female");
//            }else if(g==0){
//                gender.setHint("edit profile");
//            }
        }else{
            Toast.makeText(this, "data is empty", Toast.LENGTH_LONG).show();
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            Uri uri=data.getData();

            try{
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);

                profile_image.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
