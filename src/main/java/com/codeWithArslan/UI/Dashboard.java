package com.codeWithArslan.UI;
import javax.swing.*;
import java.awt.*;

import com.codeWithArslan.Service.UserServices;
import com.codeWithArslan.UI.SignUpWindow;

public class Dashboard extends JFrame {
    SignUpWindow signUpWindow;
    SignInWindow signInWindow;
    UserServices userServices;
    Button btnSignUp;
    Button btnSignIn;

    SideBar sideBar;

    JLabel welcomeLabel;

    public Dashboard() {
        sideBar = new SideBar();
        userServices = new UserServices();
        this.setSize(600,600);
        this.setTitle("Dashboard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcomeLabel = new JLabel("Welcome to Dashboard");

        btnSignUp = new Button("Sign Up", "Sign Up");
        btnSignIn = new Button("Primary", "Sign In");

        btnSignUp.addActionEvent(e->{
            if(signUpWindow == null) {
                signUpWindow = new SignUpWindow();
                signUpWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
            signUpWindow.setVisible(true);
        });

        btnSignIn.addActionEvent(e->{
            signInWindow = new SignInWindow();
            signInWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            signInWindow.setOnLoginSuccess(() -> {
                refreshDashBoardState();
            });
            signInWindow.setVisible(true);
        });

        sideBar.setOnLogoutSuccess(() -> {
            refreshDashBoardState();
        });

        JPanel action_btn_panel = new JPanel();
        action_btn_panel.add(btnSignUp);
        action_btn_panel.add(btnSignIn);

        this.add(action_btn_panel, BorderLayout.CENTER);
        this.add(sideBar, BorderLayout.WEST);

        refreshDashBoardState();
        this.setVisible(true);
    }

    private void refreshDashBoardState() {
        boolean isLogIn = userServices.isLoggedIn();
        if(isLogIn) {
           sideBar.setVisible(true);
           btnSignIn.setVisible(false);
           btnSignUp.setVisible(false);
        } else {
            sideBar.setVisible(false);
            btnSignIn.setVisible(true);
            btnSignUp.setVisible(true);
        }

        this.revalidate();
        this.repaint();
    }
}
