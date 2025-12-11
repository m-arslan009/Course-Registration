package com.codeWithArslan.DAO;

import com.codeWithArslan.Model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CourseDAO implements Database<Course, String, ResultSet>{
    BuildConnection buildConnection = new BuildConnection();
    @Override
    public int insert(Course obj) {
        String courseName = obj.getCourse_name();
        int courseCode = obj.getCourse_code();
        String prerequisiteName = obj.getPrerequisite_name();
        int prerequisiteCode = obj.getPrerequisite_code();
        int seats = obj.getAvailable_seats();
        int status = obj.isStatus();
        String statusDescription = obj.getStatus_description();
        Connection connection = null;
        try {
            connection = buildConnection.establishConnection();

            String QUERY = "INSERT INTO USER (COURSE_NAME, COURSE_CODE, PREREQUISITE_NAME, PREREQUISITE_CODE, STATUS, STATUS_DESCRIPTION, SEATS) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(QUERY);

            ps.setString(1, courseName);
            ps.setInt(2, courseCode);
            ps.setString(3, prerequisiteName);
            ps.setInt(4, prerequisiteCode);
            ps.setInt(5, status);
            ps.setString(6, statusDescription);
            ps.setInt(7, seats);

            int rowsInserted = ps.executeUpdate();

            connection.close();
            return rowsInserted > 0 ? 1 : 0;   // return true if inserted

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(String courseName) {
        Connection connection = null;
        try {
            connection = buildConnection.establishConnection();
            String QUERY = "DELETE FROM USER WHERE COURSE_NAME = ?";
            PreparedStatement ps =  connection.prepareStatement(QUERY);
            ps.setString(1, courseName);
            int rowsDeleted = ps.executeUpdate();
            connection.close();
            return rowsDeleted > 0 ? 1 : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(String updatedVal, String factor, String key) {
        Connection connection = null;
        try {
            connection = buildConnection.establishConnection();
            String QUERY = "UPDATE COURSE SET " + factor.toUpperCase() + " = ?" + " WHERE COURSE_NAME = ?";
            PreparedStatement ps =  connection.prepareStatement(QUERY);
            ps.setString(1, updatedVal);
            ps.setString(2, key);
            int rowsUpdated = ps.executeUpdate();
            connection.close();
            return rowsUpdated > 0 ? 1 : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ResultSet get(String key) {
        Connection connection = null;
        try {
            connection = buildConnection.establishConnection();
            String QUERY = "SELECT * FROM COURSE WHERE COURSE_NAME = ?";
            PreparedStatement ps = connection.prepareStatement(QUERY);
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            connection.close();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getAll() {
        Connection connection = null;
        try {
            connection = buildConnection.establishConnection();
            String QUERY = "SELECT * FROM COURSE ";
            Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery(QUERY);
            connection.close();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
