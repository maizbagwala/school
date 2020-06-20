package com.example.wallpaper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class StudyMaterialActivity extends AppCompatActivity {
    private int PICK_IMAGE_REQUEST = 1;
    TextView filename;
    Button btnpick, btnupload;
    ProgressBar progressBar;
    Uri uri;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_material);
        btnpick = findViewById(R.id.btnpick);
        btnupload = findViewById(R.id.btnupload);
        filename = findViewById(R.id.filename);
        progressBar=findViewById(R.id.pb_study);
        mStorageRef = FirebaseStorage.getInstance().getReference().child("studyMaterial");
        btnpick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(StudyMaterialActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    selectPdf();
                } else {
                    ActivityCompat.requestPermissions(StudyMaterialActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                }
            }
        });

        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if(uri!=null){

                    FileUpload();
                }else{
                    Toast.makeText(StudyMaterialActivity.this, "please select file first", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void FileUpload() {
        Uri file = uri;
        String filename = System.currentTimeMillis()+"_"+uri.getLastPathSegment();

        mStorageRef.child(filename).putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
//                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        progressBar.setVisibility(View.GONE);
                        showdialog();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectPdf();
        } else {
            Toast.makeText(this, "please provide permission", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectPdf() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 86 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();

            filename.setText(uri.getLastPathSegment());
        } else {
            Toast.makeText(this, "please select file ", Toast.LENGTH_SHORT).show();
        }
    }

    public void showdialog() {
        final Dialog dialog = new Dialog(StudyMaterialActivity.this);
        dialog.setContentView(R.layout.mydialog);
        dialog.show();
        Button db = dialog.findViewById(R.id.button);
        db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }
}