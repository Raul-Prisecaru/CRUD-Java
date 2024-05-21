package com.raul.GUI.SubTabs.Create;

import com.raul.Features.Cases;
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



        // Create and add Client Email components
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

        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Check if Client Name is empty
                    String ClientName = clientNameTextField.getText();
                    if (ClientName.isEmpty()) {
                        throw new IllegalArgumentException("Client Name is Empty");
                    }
                    clients.setClientName(ClientName);

                    // Check if Client Address is empty
                    String ClientAddress = clientAddressTextField.getText();
                    if (ClientAddress.isEmpty()) {
                        throw new IllegalArgumentException("Client Address is Empty");
                    }
                    clients.setClientAddress(ClientAddress);

                    // Check if Client Phone Number is empty, meets the Length Requirement
                    // and has no Letters
                    String ClientPhoneNumber = ClientPhoneTextField.getText();
                    if (ClientPhoneNumber.isEmpty()) {
                        throw new IllegalArgumentException("Phone Number is Empty");
                    }
                    if (!ClientPhoneNumber.matches("\\d{3}-\\d{4}")) {
                        throw new IllegalArgumentException("Phone Number Doesn't Follow Correct Format: xxx-xxxx");
                    }
                    clients.setClientPhoneNumber(ClientPhoneNumber);

                    // Check if Email Follows Format
                    String ClientEmail = ClientEmailTextField.getText();
                    if (!ClientEmail.matches("[a-zA-Z0-9.%±]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}")) {
                        throw new IllegalArgumentException("Invalid Email Format");
                    }

                    if (ClientEmail.isEmpty()) {
                        throw new IllegalArgumentException("Client Email is empty");
                    }
                    clients.setClientEmail(ClientEmail);
                    clients.Create(clients.getClientName(), clients.getClientAddress(), clients.getClientPhoneNumber(), clients.getClientEmail());
                    JOptionPane.showMessageDialog(textFieldPanel,"Records Successfully Added");
                } catch (IllegalArgumentException iae) {
                    JOptionPane.showMessageDialog(textFieldPanel, iae.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        textFieldPanel.add(SubmitButton, gbc);

        // Add Panel
        add(textFieldPanel);

    }
}
