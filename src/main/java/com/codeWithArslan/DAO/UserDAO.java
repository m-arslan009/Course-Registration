package com.codeWithArslan.DAO;

import com.codeWithArslan.Model.User;

import java.sql.*;

// Note: I recommend changing the interface to return 'User' instead of 'ResultSet'
// to prevent connection leaks, but for now I will fix the logic within your current structure.
public class UserDAO implements Database<User, String, ResultSet> {

    private BuildConnection bc;

    public UserDAO() {
        bc = new BuildConnection();
    }

    @Override
    public boolean insert(User obj) {
        // 1. Get data from object
        String username = obj.getUsername();
        String password = obj.getPassword();
        String name = obj.getName();
        String role = obj.getUserRole(); // Changed 'roll' to 'role' for clarity

        Connection connection = null;
        try {
            connection = bc.establishConnection();
            if (connection == null) return false; // Safety check

            // FIX 1: Table name changed to USERS (USER is reserved keyword)
            // FIX 2: Check your DB columns. Is it 'ROLL' or 'ROLE'? I assumed 'Role' based on your UI.
            String QUERY = "INSERT INTO USERS (USERNAME, PASSWORD, USER_NAME, ROLL) VALUES (?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(QUERY);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, role);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // Best Practice: Close connection in a finally block
            try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    @Override
    public boolean update(String updatedVal, String factor, String key) {
        Connection connection = null;
        try {
            connection = bc.establishConnection();

            // FIX 3: SQL Injection prevention & Execution
            // Note: Column names (factor) cannot be set with ?, so string concat is necessary there,
            // but be careful.
            String QUERY = "UPDATE USERS SET " + factor + " = ? WHERE USERNAME = ?";

            PreparedStatement ps = connection.prepareStatement(QUERY);
            ps.setString(1, updatedVal); // The new value
            ps.setString(2, key);        // The username (WHERE clause)

            // FIX 4: Actually Execute the update!
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    @Override
    public boolean delete(String key) {
        Connection connection = null;
        try {
            connection = bc.establishConnection();
            String QUERY = "DELETE FROM USERS WHERE USERNAME = ?";
            PreparedStatement ps = connection.prepareStatement(QUERY);
            ps.setString(1, key);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    @Override
    public ResultSet get(String username) {
        ResultSet resultSet = null;

        try {
            Connection connection = bc.establishConnection();
            String QUERY = "SELECT * FROM USERS WHERE USERNAME = ?";
            PreparedStatement ps = connection.prepareStatement(QUERY);
            ps.setString(1, username);

            resultSet = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            resultSet = null;
        }
        return resultSet;
    }

    @Override
    public ResultSet getAll() {
        ResultSet resultSet = null;
        try {
            Connection connection = bc.establishConnection();
            String QUERY = "SELECT * FROM USERS";
            Statement ps = connection.createStatement();
            resultSet = ps.executeQuery(QUERY);

            // CRITICAL FIX 6: DO NOT CLOSE CONNECTION HERE either.
            // connection.close(); <--- REMOVED

        } catch (Exception e) {
            e.printStackTrace();
            resultSet = null;
        }
        return resultSet;
    }
}