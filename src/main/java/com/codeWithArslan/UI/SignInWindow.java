package com.codeWithArslan.UI;


import com.codeWithArslan.Service.UserServices;

import javax.swing.*;
import java.awt.*;

public class SignInWindow extends JFrame {
    Button btnSignIn;
    Button btnCancel;
    JTextField username;
    JPasswordField password;

    JProgressBar progressBar;

    private Runnable onLoginSuccess;

    public SignInWindow() {
        this.setTitle("Sign In");
        this.setSize(350,300);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        btnSignIn = new Button("Primary","Sign In");
        btnCancel = new Button("Secondary","Cancel");

        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false);
        progressBar.setStringPainted(true);
        progressBar.setString("Authenticating...");


        JPanel username_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        username_panel.add(new JLabel("Username:"));
        this.username = new JTextField(30);
        username_panel.add(username);

        JPanel password_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        password_panel.add(new JLabel("Password:"));
        this.password = new JPasswordField(30);
        password_panel.add(password);

        JPanel btn_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btn_panel.add(btnSignIn);
        btn_panel.add(btnCancel);

        handleSignIn();
        handleCancel();


        this.add(username_panel);
        this.add(password_panel);
        this.add(btn_panel);

        this.add(progressBar);
        this.setResizable(false);
    }

    public void setOnLoginSuccess(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }

    private void handleSignIn() {
        this.btnSignIn.addActionEvent(e -> {
            String user = new  String(username.getText());
            String pass = new  String(password.getPassword());

            if(user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields", "Empty Filed", JOptionPane.WARNING_MESSAGE);
                return;
            }

            this.btnSignIn.setText("Loading...");
            this.btnSignIn.setEnabled(false);
            this.btnCancel.setEnabled(false);
            this.progressBar.setVisible(true);

            Thread worker = new Thread(() -> {

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                UserServices obj = new UserServices();

                int isSuccess = obj.login(user, pass);

                SwingUtilities.invokeLater(() -> {

                    this.btnSignIn.setText("Sign In");
                    this.btnSignIn.setEnabled(true);
                    this.btnCancel.setEnabled(true);
                    this.progressBar.setVisible(false);

                    if(isSuccess == -1) {
                        JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Invalid User", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if(onLoginSuccess != null) {
                        onLoginSuccess.run();
                    }

                    JOptionPane.showMessageDialog(null, "Welcome to DashBoard", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                });
            });

            worker.start();

        });
    }

    private void handleCancel() {
        this.btnCancel.addActionEvent(e -> {
            this.dispose();
        });
    }
}
