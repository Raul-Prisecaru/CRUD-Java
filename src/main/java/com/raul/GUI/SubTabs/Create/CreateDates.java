package com.raul.GUI.SubTabs.Create;

import javax.swing.*;
import java.awt.*;

public class CreateDates extends JPanel {
    public CreateDates() {
        // Layout for this Page
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Create Labels
        JLabel Title = new JLabel("Add Dates to System");


        // Center the Title
        Title.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Set the Font
        Title.setFont(new Font("serif", Font.PLAIN, 20));


        // Add the labels to this panel
        add(Title);

        // Set Spacing
//        add(Box.createVerticalStrut(10));
    }
}
