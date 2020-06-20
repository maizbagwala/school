package com.example.wallpaper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddFaculty extends AppCompatActivity {
    ProgressBar progressBar;
    FirebaseDatabase database;
    DatabaseReference reference;
    EditText Etfname_f, Etlname_f, Etpassword_f, Etaddress_f, Etemail_f, Etphone_f, Etdob_f;
    Button addbtnfaculty;
    CircleImageView imageProfile;
    String pw;
    Uri uri;
    private StorageReference mStorageRef;

    private int PICK_IMAGE_REQUEST = 1;

    final String role = "t";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("teacher");

        Etfname_f = findViewById(R.id.Fname_addFaculty);
        Etlname_f = findViewById(R.id.Lname_addFaculty);
        Etpassword_f = findViewById(R.id.password_addFaculty);
        Etaddress_f = findViewById(R.id.address_addFaculty);
        Etemail_f = findViewById(R.id.email_addFaculty);
        Etphone_f = findViewById(R.id.phone_addFaculty);
        Etdob_f = findViewById(R.id.dob_addFaculty);
        imageProfile = findViewById(R.id.image_profile_add_f);
        addbtnfaculty = findViewById(R.id.add_addFaculty);
        progressBar = findViewById(R.id.pb_addfaculty);

        mStorageRef = FirebaseStorage.getInstance().getReference().child("profileImage");


        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "select picture"), PICK_IMAGE_REQUEST);
            }
        });

        addbtnfaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    pw = Etpassword_f.getText().toString();
                    String id = reference.push().getKey();
                    Uri file = uri;
                    String filename = Etemail_f.getText().toString() + "_" + id;
                    progressBar.setVisibility(View.VISIBLE);
                    mStorageRef.child(filename).putFile(file)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    // Get a URL to the uploaded content
//                        Uri downloadUrl = taskSnapshot.getDownloadUrl();

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

                    Teacher teacher = new Teacher(id, Etfname_f.getText().toString(), Etlname_f.getText().toString(), Etaddress_f.getText().toString(), Etemail_f.getText().toString(), Etpassword_f.getText().toString(), Etphone_f.getText().toString(), Etdob_f.getText().toString(), role,filename);
                    reference.child(id).setValue(teacher);
                    Toast.makeText(AddFaculty.this, "data inserted ", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    showdialog();
                }
            }
        });
    }

    public void showdialog() {
        final Dialog dialog = new Dialog(AddFaculty.this);
        dialog.setContentView(R.layout.mydialog);
        dialog.show();
        Button db = dialog.findViewById(R.id.button);
        db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Etfname_f.setText("");
                Etlname_f.setText("");
                Etaddress_f.setText("");
                Etemail_f.setText("");
                Etpassword_f.setText("");
                Etphone_f.setText("");
                Etdob_f.setText("");
                imageProfile.setImageDrawable(null);
                imageProfile.setBackgroundResource(R.drawable.imgback);
                dialog.dismiss();
            }
        });


    }

    public boolean checkValidation() {
        if (imageProfile.getDrawable() == null) {
            Toast.makeText(this, "select image", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Etfname_f.getText().toString().length() == 0) {
            Etfname_f.setError("enter First name");
            return false;
        }
        if (Etlname_f.getText().toString().length() == 0) {
            Etlname_f.setError("enter Last name");
            return false;
        }
        if (Etpassword_f.getText().toString().length() == 0) {
            Etpassword_f.setError("enter Password");
            return false;
        }
        if (Etaddress_f.getText().toString().length() == 0) {
            Etaddress_f.setError("enter Address");
            return false;
        }

        if (Etemail_f.getText().toString().length() == 0) {
            Etemail_f.setError("enter Email");
            return false;
        }
        if (Etphone_f.getText().toString().length() == 0) {
            Etphone_f.setError("enter Phone");
            return false;
        }
        if (Etdob_f.getText().toString().length() == 0) {
            Etdob_f.setError("enter DOB");
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                imageProfile.setImageBitmap(bitmap);
                imageProfile.setBackgroundResource(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

