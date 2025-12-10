package com.codeWithArslan.Model;

public class Course {
    String course_name;
    int course_code;
    String prerequisite_name;
    int prerequisite_code;
    int Status;
    int available_seats;
    String Status_description;


    public String getStatus_description() {
        return Status_description;
    }

    public void setStatus_description(String status_description) {
        Status_description = status_description;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public int isStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getPrerequisite_code() {
        return prerequisite_code;
    }

    public void setPrerequisite_code(int prerequisite_code) {
        this.prerequisite_code = prerequisite_code;
    }

    public String getPrerequisite_name() {
        return prerequisite_name;
    }

    public void setPrerequisite_name(String prerequisite_name) {
        this.prerequisite_name = prerequisite_name;
    }

    public int getCourse_code() {
        return course_code;
    }

    public void setCourse_code(int course_code) {
        this.course_code = course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}
