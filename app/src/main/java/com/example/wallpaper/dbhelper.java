package com.example.wallpaper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class dbhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myData.db";
    private static final String TABLE_NAME = "reg";
    private static final String TABLE_STUDENT = "student";
    private static final String cl_name = "name";
    private static final String cl_phone = "phone";
    private static final String cl_email = "email";
    private static final String cl_pswd = "password";
    private static final String cl_gender = "gender";
    private static final String cl_dob = "dob";


    private static final String fname = "fname";
    private static final String lname = "lname";
    private static final String rollno = "rollno";
    private static final String address = "address";
    private static final String email = "email";
    private static final String phone = "phone";
    private static final String dob = "dob";


    public dbhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_STUDENT + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , " + fname + " TEXT," + lname + " TEXT," + rollno + " TEXT," + address + " TEXT," + email + " TEXT," + phone + " TEXT," + dob + " TEXT)");
        Log.d("mmm", "onCreate: q pass kiya");
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT ," + cl_name + " TEXT," + cl_phone + " TEXT," + cl_email + " TEXT," + cl_pswd + " TEXT," + cl_gender + " INTEGER," + cl_dob + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }


    public boolean insertData(String name, String phone, String email, String pass, Integer gender, String dob) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(cl_name, name);
        values.put(cl_phone, phone);
        values.put(cl_email, email);
        values.put(cl_pswd, pass);
        values.put(cl_gender, gender);
        values.put(cl_dob, dob);

        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean addStudent(String fname, String lname, String rollno, String address, String email, String phone, String dob) {
        SQLiteDatabase db1 = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fname", fname);
        values.put("lname", lname);
        values.put("rollno", rollno);
        values.put("address", address);
        values.put("email", email);
        values.put("phone", phone);
        values.put("dob", dob);
        Log.d("mmm", "addStudent: call thay che");

        long result = db1.insert(TABLE_STUDENT, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

//    public Cursor selectData(){
//        SQLiteDatabase db=getWritableDatabase();
//        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);
////        +" where "+cl_email+" ="+us+" and "+cl_pswd+" ="+ps);
//        return cursor;
//    }

    public User checkUser(String user, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        User u = null;

        Log.d("mmm", "database: pohoche");

        Cursor cu = db.rawQuery("select * from " + TABLE_NAME + " where " + cl_email + " =? and " + cl_pswd + " =?", new String[]{user, pass});
        if (cu != null) {
            cu.moveToFirst();
        }
        if (cu != null && cu.getCount() > 0) {
            u = new User(cu.getString(1), cu.getString(2), cu.getString(3), cu.getString(4), Integer.parseInt(cu.getString(5)), cu.getString(6));
        }
//        Log.d("mmm", " column 0 " +cu.getString(0));

        return u;
    }


    public void deleteStudent(){
        SQLiteDatabase db=this.getWritableDatabase();
//        db.delete(TABLE_STUDENT,);
    }

    public Student[] getAllStudent() {
        SQLiteDatabase db = this.getReadableDatabase();
        Student[] s;

        Cursor cu = db.rawQuery("select * from " + TABLE_STUDENT, null);
        s = new Student[cu.getCount()];
        if (cu == null) {
            Log.d("mmm", "getAllUsers: khali");
        }
        if (cu != null) {
            cu.moveToFirst();
//            Log.d("mmm", "getAllUsers: " + cu.getCount());
//            Log.d("mmm", "getAllUsers: " + cu.getString(1));
//            Log.d("mmm", "getAllUsers: " + cu.getString(2));
        }
//        if (cu != null && cu.getCount() > 0) {
//            for (int i = 0; i < cu.getCount(); i++) {
//                try {
//                    s[i] = new Student(cu.getString(1), cu.getString(2),cu.getString(3),cu.getString(4),cu.getString(5),cu.getString(6),cu.getString(7));
//                    cu.moveToPosition(i + 1);
////                    Log.d("mmm", "getAllUsers: " + s[0].getName());
////                    Log.d("mmm", "getAllUsers: " + s[0].getPhone());
////                    Log.d("mmm", "getAllUsers:2 " + s[1].getName());
////                    Log.d("mmm", "getAllUsers:2 " + s[1].getPhone());
//                } catch (NullPointerException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
        db.close();
        return s;
    }
}
