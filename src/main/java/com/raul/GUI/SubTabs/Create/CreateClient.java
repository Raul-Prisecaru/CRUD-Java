package com.raul.GUI.SubTabs.Create;

import com.raul.CustomErrorHandling.IDNotFoundException;
import com.raul.Features.Clients;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateClient extends JPanel {
    public CreateClient() {
        Clients clients = new Clients();

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

        // Create and add Client PhoneNumber components
        JLabel clientPhoneLabel = new JLabel("Client Phone:");
        JTextField clientPhoneTextField = new JTextField(15);

        // Set Client Phone Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 3;
        textFieldPanel.add(clientPhoneLabel, gbc);

        // Set Client Phone Text Field Positioning
        gbc.gridx = 1;
        gbc.gridy = 3;
        textFieldPanel.add(clientPhoneTextField, gbc);

        // Create and add Client Email components
        JLabel clientEmailLabel = new JLabel("Client Email:");
        JTextField clientEmailTextField = new JTextField(15);

        // Set Client Email Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 4;
        textFieldPanel.add(clientEmailLabel, gbc);

        // Set Client Email Text Field Positioning
        gbc.gridx = 1;
        gbc.gridy = 4;
        textFieldPanel.add(clientEmailTextField, gbc);

        // Create Submit Button and Set Positioning
        JButton submitButton = new JButton("Submit");
        gbc.gridx = 1;
        gbc.gridy = 5;

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    // Check if Client Name is empty
                    String clientName = clientNameTextField.getText();
                    if (clientName.isEmpty()) {
                        throw new IllegalArgumentException("Client Name is Empty");
                    }
                    clients.setClientName(clientName);

                    // Check if Client Address is empty
                    String clientAddress = clientAddressTextField.getText();
                    if (clientAddress.isEmpty()) {
                        throw new IllegalArgumentException("Client Address is Empty");
                    }
                    clients.setClientAddress(clientAddress);

                    // Check if Client Phone Number is empty, meets the Length Requirement and has no Letters
                    String clientPhoneNumber = clientPhoneTextField.getText();
                    if (clientPhoneNumber.isEmpty()) {
                        throw new IllegalArgumentException("Phone Number is Empty");
                    }
                    if (!clientPhoneNumber.matches("\\d{3}-\\d{4}")) {
                        throw new IllegalArgumentException("Phone Number Doesn't Follow Correct Format: xxx-xxxx");
                    }
                    clients.setClientPhoneNumber(clientPhoneNumber);

                    // Check if Email Follows Format
                    String clientEmail = clientEmailTextField.getText();
                    if (!clientEmail.matches("[a-zA-Z0-9.%±]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                        throw new IllegalArgumentException("Invalid Email Format");
                    }
                    if (clientEmail.isEmpty()) {
                        throw new IllegalArgumentException("Client Email is Empty");
                    }
                    clients.setClientEmail(clientEmail);

                    clients.Create(clients.getClientName(), clients.getClientAddress(), clients.getClientPhoneNumber(), clients.getClientEmail());
                    JOptionPane.showMessageDialog(textFieldPanel, "Records Successfully Added");
                } catch (IllegalArgumentException iae) {
                    JOptionPane.showMessageDialog(textFieldPanel, iae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        textFieldPanel.add(submitButton, gbc);

        // Add Panel
        add(textFieldPanel);
    }
}
