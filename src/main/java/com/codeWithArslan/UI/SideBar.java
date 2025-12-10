package com.codeWithArslan.UI;

import com.codeWithArslan.Service.UserServices;

import javax.swing.*;
import java.awt.*;

public class SideBar extends JPanel {
    Button Registration;
    Button RegisterCourses;
    Button AllCourses;
    Button AddCourses;
    Button PersonalInformation;
    Button Logout;

    private Runnable onLogoutSuccess;

    UserServices userServices;
    public SideBar() {

        userServices = new  UserServices();

        this.setLayout(new BoxLayout(this,  BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        Registration = new Button("Registration");
        RegisterCourses = new Button("Register Courses");
        PersonalInformation = new Button("Personal Information");
        Logout = new Button("Logout");
        AllCourses = new Button("Offered Courses");
        AddCourses = new Button("Add Courses");

        this.add(Box.createVerticalStrut(20));
        this.add(AddCourses);
        this.add(Box.createVerticalStrut(20));
        this.add(AllCourses);
        this.add(Box.createVerticalStrut(20)); // add margin to top
        this.add(Registration);
        this.add(Box.createVerticalStrut(20));
        this.add(RegisterCourses);
        this.add(Box.createVerticalStrut(20));
        this.add(PersonalInformation);
        this.add(Box.createVerticalGlue()); // to push the logout button to the bottom
        this.add(Logout);
        this.add(Box.createVerticalStrut(20));

        handleLogout();

        this.setVisible(true);
    }

    public void setOnLogoutSuccess(Runnable onLogoutSuccess) {
        this.onLogoutSuccess = onLogoutSuccess;
    }

    private void handleLogout() {
        this.Logout.addActionListener(e-> {
            userServices.logout();
            JOptionPane.showMessageDialog(this, "Logged out successfully.");

            if(onLogoutSuccess != null)
                onLogoutSuccess.run();
        });
    }
}
