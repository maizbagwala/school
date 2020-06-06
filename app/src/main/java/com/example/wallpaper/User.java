package com.example.wallpaper;

public class User {
    private String name;
    private String phone;
    private String email;
    private String password;
    private int gender;
    private String dob;

    public User(String name,String phone){
        this.name=name;
        this.phone=phone;
    }

    public User(String name, String phone, String email, String password, int gender, String dob){
        this.name=name;
        this.phone=phone;
        this.email=email;
        this.password=password;
        this.gender=gender;
        this.dob=dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
