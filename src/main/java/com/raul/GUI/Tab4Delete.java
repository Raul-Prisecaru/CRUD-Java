package com.raul.GUI;

import javax.swing.*;
import java.awt.*;

public class Tab4Delete extends JPanel {
    public Tab4Delete() {
        // Layout for this Page
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Create Labels
        JLabel Title = new JLabel("Welcome To St Mary's Law Firm System");
        JLabel SubTitle = new JLabel("What would you like to do?");

        // Center the Labels
        Title.setAlignmentX(Component.CENTER_ALIGNMENT);
        SubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set the Labels Font

        Title.setFont(new Font("serif", Font.PLAIN, 20));
        SubTitle.setFont(new Font("serif", Font.PLAIN, 15));


        // Add the labels to this panel
        add(Title);

        // Set Spacing
        add(Box.createVerticalStrut(10));

        add(SubTitle);

        // Create Buttons
        JButton CreateButton = new JButton("Add Information");
        JButton RetrieveButton = new JButton("Retrieve Information");
        JButton UpdateButton = new JButton("Update Information");
        JButton DeleteButton = new JButton("Delete Information");

        // Center Buttons
        CreateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        RetrieveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        UpdateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        DeleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        add(Box.createVerticalStrut(20));
        add(CreateButton);
        add(Box.createVerticalStrut(5));
        add(RetrieveButton);
        add(Box.createVerticalStrut(5));
        add(UpdateButton);
        add(Box.createVerticalStrut(5));
        add(DeleteButton);
    }
}
