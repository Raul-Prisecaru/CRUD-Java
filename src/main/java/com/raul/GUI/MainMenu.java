package com.raul.GUI;

import javax.swing.*;

import java.awt.*;

public class MainMenu extends JPanel {
    public MainMenu() {
        // Layout for this Page
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Create Labels
        JLabel label1 = new JLabel("Welcome To St Mary's Law Firm System");
        JLabel label2 = new JLabel("What would you like to do?");

        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the labels to this panel
        add(label1);
        add(label2);


        JButton CreateButton = new JButton("Add Information");
        JButton RetrieveButton = new JButton("Retrieve Information");
        JButton UpdateButton = new JButton("Update Information");
        JButton DeleteButton = new JButton("Delete Information");

        CreateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        RetrieveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        UpdateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        DeleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);



        add(CreateButton);
        add(RetrieveButton);
        add(UpdateButton);
        add(DeleteButton);
    }
}
