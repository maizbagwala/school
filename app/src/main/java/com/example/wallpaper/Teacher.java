package com.example.wallpaper;

public class Teacher {
    String id;
    String fname;
    String lname;
    String address;
    String email;
    String pwd;
    String phone;
    String dob;
    String role;
    String imageurl;

    public Teacher() {
    }

    public Teacher(String id, String fname, String lname, String address, String email, String pwd, String phone, String dob, String role,String imageurl) {

        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.email = email;
        this.pwd = pwd;
        this.phone = phone;
        this.dob = dob;
        this.role = role;
        this.imageurl=imageurl;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
