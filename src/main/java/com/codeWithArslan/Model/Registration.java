package com.codeWithArslan.Model;

public class Registration {
    String Course_Name;
    int Course_Code;
    String User_Name;
    String Status;
    String Status_Description;

    public String getStatus_Description() {
        return Status_Description;
    }

    public void setStatus_Description(String status_Description) {
        Status_Description = status_Description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public int getCourse_Code() {
        return Course_Code;
    }

    public void setCourse_Code(int course_Code) {
        Course_Code = course_Code;
    }

    public String getCourse_Name() {

        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }
}
