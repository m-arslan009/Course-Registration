package com.codeWithArslan.UI;

import com.codeWithArslan.Service.UserServices;
import javax.swing.*;
import java.awt.*; // Import for Layouts

public class SignUpWindow extends JFrame {
    // 1. Use JButton (Swing)
    Button signUp;
    Button cancel;

    // Global fields so the listener can access them
    JTextField username;
    JPasswordField password;
    JPasswordField confirmPassword;
    JTextField name;
    JComboBox<String> role;
    JProgressBar progressBar;

    public SignUpWindow() {

        this.setTitle("Sign Up");
        this.setSize(350, 400); // Made slightly taller

        // Initialize Global Variables
        this.username = new JTextField(30);
        this.password = new JPasswordField(30);
        this.confirmPassword = new JPasswordField(30);
        this.name = new JTextField(30);

        // Initialize Progress Bar
        this.progressBar = new JProgressBar();
        this.progressBar.setIndeterminate(true);
        this.progressBar.setString("Signing Up...");
        this.progressBar.setStringPainted(true);
        this.progressBar.setVisible(false);


        JPanel User_NamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        User_NamePanel.add(new JLabel("Full Name: "));
        User_NamePanel.add(name);

        JPanel Username_Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Username_Panel.add(new JLabel("Username:  "));
        Username_Panel.add(username);

        JPanel Password_Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Password_Panel.add(new JLabel("Password:  "));
        Password_Panel.add(password);

        JPanel Confirm_Password_Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Confirm_Password_Panel.add(new JLabel("Confirm Password:   "));
        Confirm_Password_Panel.add(confirmPassword);

        String[] options = new String[]{"Regular Student", "Foreign Student", "Special Recommendation Student", "Cota Student"};
        JPanel User_Role_Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        User_Role_Panel.add(new JLabel("Role: "));
        role = new JComboBox<>(options);
        User_Role_Panel.add(role);

        JPanel button_Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        signUp = new Button("Primary", "Sign Up");
        cancel = new Button("Secondary","Cancel");
        button_Panel.add(signUp);
        button_Panel.add(cancel);

        // --- Add Panels to Frame ---
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(User_NamePanel);
        this.add(Username_Panel);
        this.add(Password_Panel);
        this.add(Confirm_Password_Panel);
        this.add(User_Role_Panel);
        this.add(button_Panel);
        this.add(progressBar);

        // 2. Call Event Function (NO ARGUMENTS PASSED)
        SignUpEvent();
        cancelEvent();

    }

    private void SignUpEvent() {
        this.signUp.addActionListener(e -> {

            String txtName = this.name.getText();
            String txtUsername = this.username.getText();
            String txtPassword = new String(this.password.getPassword());
            String txtConfirm = new String(this.confirmPassword.getPassword());
            String txtRole = (String) this.role.getSelectedItem();


            if(txtName.isEmpty() || txtUsername.isEmpty() || txtPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill complete form", "Form Incomplete", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            this.signUp.setText("Creating Account...");
            this.signUp.setEnabled(false);
            this.cancel.setEnabled(false);
            this.progressBar.setVisible(true);

            Thread worker = new Thread(() -> {

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                UserServices userServices = new UserServices();

                boolean validateStatus = userServices.validatePassword(txtPassword.trim(), txtConfirm.trim());
                boolean isExist = userServices.get(txtUsername.trim()) != null;
                int status = userServices.insert(txtUsername.trim(), txtPassword.trim(), txtName.trim(), txtRole.trim());


                SwingUtilities.invokeLater(() -> {

                    this.signUp.setText("Sign Up");
                    this.signUp.setEnabled(true);
                    this.cancel.setEnabled(true);
                    this.progressBar.setVisible(false);

                    if(isExist) {
                        JOptionPane.showMessageDialog(this, "User already exists", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if(!validateStatus) {
                        JOptionPane.showMessageDialog(this, "Passwords do not match", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // 6. INSERT
                    if( status == 1) {
                        JOptionPane.showMessageDialog(this, "User Successfully Registered", "Success", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Registration Failed", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
            });

            worker.start();
        });
    }

    private void cancelEvent() {
        this.cancel.addActionListener(e -> {
            this.dispose(); // Use dispose to actually close the window resources
        });
    }
}