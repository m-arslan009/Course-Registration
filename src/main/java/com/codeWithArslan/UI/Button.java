package com.codeWithArslan.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// It is safe to name it Button as long as you DO NOT import java.awt.Button
public class Button extends JButton {

    // Constructor 1: Full customization (Type + Text)
    public Button(String type, String text) {
        super(text); // Pass text to parent JButton
        setupStyle(type);
        this.setPreferredSize(new Dimension(100,30));
    }

    // Constructor 2: Simple version (Just Text) - Defaults to "Secondary"
    // This prevents your SignUpWindow from crashing
    public Button(String text) {
        super(text);
        setupStyle("Secondary");
    }

    private void setupStyle(String type) {
        // Fix: Use .equals() for string comparison
        if ("Primary".equals(type)) {
            this.setBackground(Color.BLUE);
            this.setForeground(Color.WHITE); // Make text readable on blue
        } else {
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }

        // Fix: Ensure color shows up on all OS (Mac/Windows)
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setFocusPainted(false); // Removes the annoying line around text when clicked
    }

    // Your custom wrapper
    public void addActionEvent(ActionListener e) {
        this.addActionListener(e);
    }
}