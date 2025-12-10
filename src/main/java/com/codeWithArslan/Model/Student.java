package com.codeWithArslan.Model;

public class Student {
    String name;
    String address;
    String email;
    int rollnumber;

    public  Student(String name, String address, String email, int rollnumber) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.rollnumber = rollnumber;
    }

    public int getRollnumber() {
        return rollnumber;
    }

    public void setRollnumber(int rollnumber) {
        this.rollnumber = rollnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
