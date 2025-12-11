package com.codeWithArslan.Service;

import com.codeWithArslan.DAO.UserDAO;
import com.codeWithArslan.Model.User;

import java.sql.*;
import java.util.*;

public class UserServices {

    private UserDAO userDAO;
    private static User currentUser = null;

    public UserServices() {
        this.userDAO = new UserDAO();
    }

    /**
     * Attempts to log in a user.
     * If successful, sets the static 'currentUser' session.
     */
    public int login(String username, String password) {

        User dbUser = this.get(username);

        if (dbUser != null && dbUser.getPassword().equals(password.trim())) {
            currentUser = dbUser;
            return 1;
        }

        // FAILED
        return -1;
    }

    public void lgout() {
        currentUser = null;
    }

    /**
     * Logs the user out by clearing the session.
     */
    public void logout() {
        currentUser = null;
    }

    /**
     * Checks if a user is currently logged in.
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    /**
     * Returns the currently logged-in user object (e.g., to display name on Dashboard).
     */
    public User getCurrentUser() {
        return currentUser;
    }

    public boolean validatePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public User get(String username) {
        ResultSet resultSet = userDAO.get(username);
        try {
            if(resultSet.next()) {
                User user = new User();

                user.setUsername(resultSet.getString("USERNAME").trim()); // The Login ID
                user.setPassword(resultSet.getString("PASSWORD").trim());
                user.setName(resultSet.getString("USER_NAME").trim());    // The Full Name
                user.setUserRole(resultSet.getString("ROLL").trim());

                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = userDAO.getAll();
        try {
            while(resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("USERNAME").trim());
                user.setPassword(resultSet.getString("PASSWORD").trim());
                user.setName(resultSet.getString("USER_NAME").trim());
                user.setUserRole(resultSet.getString("ROLL").trim());
                users.add(user);
            }

            return users.size() > 0 ? users : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int insert(String username, String password, String name, String role) {
        User userToInsert = new User();
        userToInsert.setName(name);
        userToInsert.setPassword(password);
        userToInsert.setUserRole(role);
        userToInsert.setUsername(username);

        return userDAO.insert(userToInsert);
    }

    public int delete(String username) {
        return userDAO.delete(username);
    }

    public int update(String updatedVal, String factor, String key) {
        return userDAO.update(updatedVal, factor, key);
    }
}