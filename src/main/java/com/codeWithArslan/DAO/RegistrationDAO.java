package com.codeWithArslan.DAO;

import com.codeWithArslan.Model.Registration;

import javax.xml.transform.Result;
import java.sql.*;

public class RegistrationDAO implements Database<Registration, String, ResultSet> {
    BuildConnection buildConnection;
    public RegistrationDAO() {
        this.buildConnection = new BuildConnection();
    }

    @Override
    public int insert(Registration registration) {
        String QUERY = "INSERT INTO REGISTRATION(COURSE_NAME, COURSE_CODE, USER_NAME, STATUS, STATUS_DESCRIPTION) VALUES(?,?,?,?,?,?)";

        String Course_Name = registration.getCourse_Name();
        int  Course_Code = registration.getCourse_Code();
        String User_Name = registration.getUser_Name();
        String Status = registration.getStatus();
        String Status_Description = registration.getStatus_Description();

        try {
            Connection connection = buildConnection.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, Course_Name);
            preparedStatement.setInt(2, Course_Code);
            preparedStatement.setString(3, User_Name);
            preparedStatement.setString(4, Status);
            preparedStatement.setString(5, Status_Description);

            int result = preparedStatement.executeUpdate();
            connection.close();
            return result > 0 ? 1 : 0;

        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int update(String updatedVal, String factor, String key){
        String QUERY = "UPDATE REGISTRATION SET " + factor + " = ? WHERE USER_NAME = ?";
        try {
            Connection connection = buildConnection.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, updatedVal);
            preparedStatement.setString(1, key);
            int result = preparedStatement.executeUpdate();
            connection.close();
            return result > 0 ? 1 : 0;

        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int delete(String key) {
        String QUERY = "DELETE FROM REGISTRATION WHERE USER_NAME = ?";
        try {
            Connection connection = buildConnection.establishConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, key);

            int result = preparedStatement.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean deleteCourse(String key) {
        String QUERY = "DELETE FROM REGISTRATION WHERE COURSE_NAME = ?";
        try {
            Connection connection = buildConnection.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, key);
            int result = preparedStatement.executeUpdate();
            connection.close();
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public ResultSet get(String key){
        String QUERY = "SELECT * FROM REGISTRATION WHERE USER_NAME = ?";
        try {
            Connection connection = buildConnection.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.close();
            return resultSet;
        } catch (Exception e) {
            return null;
        }
    }

    public ResultSet getAll(){
        String QUERY = "SELECT * FROM REGISTRATION";
        try {
            Connection connection = buildConnection.establishConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            connection.close();
            return resultSet;
        } catch (Exception e) {
            return null;
        }
    }
}
