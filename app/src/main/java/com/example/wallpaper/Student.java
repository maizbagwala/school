package com.example.wallpaper;

public class Student {
    private String fname;
    private String lname;
    private String rollno;
    private String address;
    private String email;
    private String phone;
    private String dob;

    public Student(String fname,String rollno){
        this.fname=fname;
        this.rollno=rollno;
    }

    public Student(String fname, String lname, String rollno, String address, String email, String phone, String dob) {
        this.fname = fname;
        this.lname = lname;
        this.rollno = rollno;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
