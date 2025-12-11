package com.codeWithArslan.UI;

import javax.swing.*;
import java.awt.*;

public class AddCourse extends JFrame {
    Button addCourse;
    Button cancel;
    TextField courseName;
    TextField courseCode;
    TextField prerequisite_name;
    TextField prerequisite_code;
    JComboBox<String> Status;
    TextField description;
    TextField seats;

    public AddCourse() {
        /**
         * Initializing the defined Components
         * */

        addCourse = new Button("Add Course");
        cancel = new Button("Cancel");

        courseName = new TextField();
        courseCode = new TextField();
        prerequisite_name = new TextField();
        prerequisite_code = new TextField();
        description = new TextField();
        seats = new TextField();

        String[] column = {"Progress","Open", "Closed"};
        Status = new JComboBox<>(column);

        /**
        * Create Container to keep things combine and controlled
        * */

        JPanel Course_Name_panel = new JPanel();
        Course_Name_panel.setLayout(new BoxLayout(Course_Name_panel, BoxLayout.Y_AXIS));
        Course_Name_panel.add(new JLabel("Course Name:"));
        Course_Name_panel.add(courseName);

        JPanel Course_Code_panel = new JPanel();
        Course_Code_panel.setLayout(new BoxLayout(Course_Code_panel, BoxLayout.Y_AXIS));
        Course_Code_panel.add(new JLabel("Course Code:"));
        Course_Code_panel.add(courseCode);

    }

}
