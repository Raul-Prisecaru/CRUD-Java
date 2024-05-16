package com.raul.GUI.SubTabs.Create;

import javax.swing.*;
import java.awt.*;

public class CreateClient extends JPanel {
    public CreateClient() {
        // Layout for this Page using BoxLayout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create Title Label
        JLabel title = new JLabel("Add Clients to System");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("serif", Font.PLAIN, 20));
        add(title);

//        // Add Vertical Glue to push the components below
        add(Box.createVerticalGlue());

        // Create a nested panel with GridLayout for the JTextField
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Using FlowLayout to align components to the left
        JTextField textField = new JTextField(20); // Specify the columns for the text field
        JLabel label2 = new JLabel("Client ID");
        textFieldPanel.add(label2);
        textFieldPanel.add(textField);

        // Add the nested panel to the main panel
        add(textFieldPanel);
    }


}

