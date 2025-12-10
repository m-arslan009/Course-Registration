package com.codeWithArslan.Service;

import com.codeWithArslan.DAO.CourseDAO;
import com.codeWithArslan.Model.Course;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseServices {
    CourseDAO courseDAO;
    public CourseServices() {
        courseDAO = new CourseDAO();
    }

    public Course getCourse(String courseName) {
        ResultSet rs = courseDAO.get(courseName);
        Course course = new Course();
        try {
            if(rs.next()) {
                course.setCourse_name(rs.getString("COURSE_NAME"));
                course.setCourse_code(rs.getInt("COURSE_CODE"));
                course.setPrerequisite_name(rs.getString("PREREQUISITE_NAME"));
                course.setPrerequisite_code(rs.getInt("PREREQUISITE_CODE"));
                course.setStatus(rs.getInt("STATUS"));
                course.setAvailable_seats(rs.getInt("AVAILABLE_SEATS"));
                course.setStatus_description(rs.getString("STATUS_DESCRIPTION"));
            }
            return course;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Course> getAllCourses() {
        ResultSet rs = courseDAO.getAll();
        List<Course> courses = new ArrayList<Course>();
        try {
            while(rs.next()) {
                Course obj = new Course();
                obj.setCourse_name(rs.getString("COURSE_NAME"));
                obj.setCourse_code(rs.getInt("COURSE_CODE"));
                obj.setPrerequisite_name(rs.getString("PREREQUISITE_NAME"));
                obj.setPrerequisite_code(rs.getInt("PREREQUISITE_CODE"));
                obj.setStatus(rs.getInt("STATUS"));
                obj.setAvailable_seats(rs.getInt("AVAILABLE_SEATS"));
                obj.setStatus_description(rs.getString("STATUS_DESCRIPTION"));
                courses.add(obj);
            }

            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insert (String cn, int cc, String pn, int pc, int s, String sd, int seats) {
        Course course = new Course();
        course.setCourse_name(cn);
        course.setCourse_code(cc);
        course.setPrerequisite_name(pn);
        course.setPrerequisite_code(pc);
        course.setStatus(s);
        course.setAvailable_seats(seats);
        course.setStatus_description(sd);
        return courseDAO.insert(course);
    }

    public boolean delete(String cn) {
        return courseDAO.delete(cn);
    }
}
