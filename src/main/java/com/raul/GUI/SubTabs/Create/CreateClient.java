package com.raul.GUI.SubTabs.Create;

import javax.swing.*;
import java.awt.*;

public class CreateClient extends JPanel {
    public CreateClient() {
        // Layout for this Page using BoxLayout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create Title Label
        JLabel titleLabel = new JLabel("Add Clients to System");

        // Set Font Name and Font Size for Title
        titleLabel.setFont(new Font("serif", Font.PLAIN, 20));

        // Auto align to Center
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        // Add some vertical space
        add(Box.createVerticalStrut(20));

        // Create a nested panel that uses GridBagLayout
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridBagLayout());
        // Use GridBagConstraints to gain Access to Settings such as gridx and gridy
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add some padding
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create Client ID Label and TextField
        JLabel clientIDLabel = new JLabel("Client ID:");
        JTextField clientIDTextField = new JTextField(15);

        // Set Client ID Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 0;
        textFieldPanel.add(clientIDLabel, gbc);

        // Set Client ID Text Field Positioning
        gbc.gridx = 1;
        gbc.gridy = 0;
        textFieldPanel.add(clientIDTextField, gbc);

        // Create Client Name Label and TextField
        JLabel clientNameLabel = new JLabel("Client Name:");
        JTextField clientNameTextField = new JTextField(15);

        // Set Client Name Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 1;
        textFieldPanel.add(clientNameLabel, gbc);

        // Set Client Name Text Field Positioning
        gbc.gridx = 1;
        gbc.gridy = 1;
        textFieldPanel.add(clientNameTextField, gbc);

        // Create Client Address Label and TextField
        JLabel clientAddressLabel = new JLabel("Client Address:");
        JTextField clientAddressTextField = new JTextField(15);

        // Set Client Address Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 2;
        textFieldPanel.add(clientAddressLabel, gbc);

        // Set Client Address Text Field Positioning
        gbc.gridx = 1;
        gbc.gridy = 2;
        textFieldPanel.add(clientAddressTextField, gbc);

        // Create and add Client Address components
        JLabel ClientPhoneLabel = new JLabel("Client Phone");
        JTextField ClientPhoneTextField = new JTextField(15);

        // Set Client PhoneNumber Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 3;
        textFieldPanel.add(ClientPhoneLabel, gbc);

        // Set Client PhoneNumber TextField Positioning
        gbc.gridx = 1;
        gbc.gridy = 3;
        textFieldPanel.add(ClientPhoneTextField, gbc);



        // Create and add Client Address components
        JLabel ClientEmailLabel = new JLabel("Client Email");
        JTextField ClientEmailTextField = new JTextField(15);
        // Set Client Email Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 4;
        textFieldPanel.add(ClientEmailLabel, gbc);

        // Set Client Email TextField Positioning
        gbc.gridx = 1;
        gbc.gridy = 4;
        textFieldPanel.add(ClientEmailTextField, gbc);

        // Create Submit Button and Set Positioning
        JButton SubmitButton = new JButton("Submit");
        gbc.gridx = 1;
        gbc.gridy = 5;

        textFieldPanel.add(SubmitButton, gbc);

        // Add Panel
        add(textFieldPanel);

    }
}
