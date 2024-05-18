package com.raul.GUI.SubTabs.Create;

import com.raul.Features.Cases;
import com.raul.Features.Clients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCases extends JPanel {
    public CreateCases() {
        Cases cases = new Cases();

        // Layout for this Page using BoxLayout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create Title Label
        JLabel titleLabel = new JLabel("Add Cases to System");

        // Set Font Name and Font Size for Title
        titleLabel.setFont(new Font("serif", Font.PLAIN, 20));

        // Auto align to Center
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        // Add some vertical space
        add(Box.createVerticalStrut(5));

        // Create a nested panel that uses GridBagLayout
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridBagLayout());
        // Use GridBagConstraints to gain Access to Settings such as gridx and gridy
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add some padding
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create Case Number Label and TextField
        JLabel caseNumberLabel = new JLabel("Case Number:");
        JTextField caseNumberTextField = new JTextField(15);

        // Set Case Number Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 1;
        textFieldPanel.add(caseNumberLabel, gbc);

        // Set Case Number Text Field Positioning
        gbc.gridx = 1;
        gbc.gridy = 1;
        textFieldPanel.add(caseNumberTextField, gbc);

        // Create Case Title Label and TextField
        JLabel caseTitleLabel = new JLabel("Case Title:");
        JTextField caseTitleTextField = new JTextField(15);

        // Set Case Title Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 2;
        textFieldPanel.add(caseTitleLabel, gbc);

        // Set Case Title Text Field Positioning
        gbc.gridx = 1;
        gbc.gridy = 2;
        textFieldPanel.add(caseTitleTextField, gbc);

        // Create and add Case Description components
        JLabel caseDescriptionLabel = new JLabel("Case Description");
        JTextField caseDescriptionTextField = new JTextField(15);

        // Set Case Description Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 3;
        textFieldPanel.add(caseDescriptionLabel, gbc);

        // Set Case Description TextField Positioning
        gbc.gridx = 1;
        gbc.gridy = 3;
        textFieldPanel.add(caseDescriptionTextField, gbc);


        // Create and add Case Status components
        JLabel caseStatusLabel = new JLabel("Case Status");
        JTextField caseStatusTextField = new JTextField(15);
        // Set Case Status Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 4;
        textFieldPanel.add(caseStatusLabel, gbc);

        // Set Client Case Status Positioning
        gbc.gridx = 1;
        gbc.gridy = 4;
        textFieldPanel.add(caseStatusTextField, gbc);

        // Create and add Date Filed components
        JLabel dateFiledLabel = new JLabel("Date Filed: ");
        JTextField dateFiledTextField = new JTextField(15);
        // Set Date Filed Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 5;
        textFieldPanel.add(dateFiledLabel, gbc);

        // Set Date Filed Status Positioning
        gbc.gridx = 1;
        gbc.gridy = 5;
        textFieldPanel.add(dateFiledTextField, gbc);

        // Create and add Date Closed components
        JLabel dateClosedLabel = new JLabel("Date Closed: ");
        JTextField dateClosedTextField = new JTextField(15);
        // Set Date Closed Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 6;
        textFieldPanel.add(dateClosedLabel, gbc);

        // Set Date Closed TextField Positioning
        gbc.gridx = 1;
        gbc.gridy = 6;
        textFieldPanel.add(dateClosedTextField, gbc);



        // Create and add CaseID components
        JLabel ClientIDLabel = new JLabel("Client ID: ");
        JTextField ClientIDTextField = new JTextField(15);
        // Set ClientID Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 7;
        textFieldPanel.add(ClientIDLabel, gbc);

        // Set ClientID TextField Positioning
        gbc.gridx = 1;
        gbc.gridy = 7;
        textFieldPanel.add(ClientIDTextField, gbc);





        // Create Submit Button and Set Positioning
        JButton SubmitButton = new JButton("Submit");
        gbc.gridx = 1;
        gbc.gridy = 8;

        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Check if the answer is an Integer
                    int caseNumber;
                    try {
                        caseNumber = Integer.parseInt((caseNumberTextField.getText()));
                    } catch (NumberFormatException nfe) {
                        throw new IllegalArgumentException("Case Number must be a Value");
                    }
                    cases.setcaseNumber(String.valueOf(caseNumber));

                    // Check if CaseTitle is empty
                    String caseTitle = caseTitleTextField.getText();
                    if (caseTitle.isEmpty()) {
                        throw new IllegalArgumentException("Case Title cannot be empty");
                    }
                    cases.setCaseTitle(caseTitle);

                    // Check if case Description is empty
                    String caseDescription = caseDescriptionTextField.getText();
                    if (caseDescription.isEmpty()) {
                        throw new IllegalArgumentException("Case Description cannot be empty");
                    }
                    cases.setCaseDescription(caseDescription);

                    // Check if Case Status is empty
                    String caseStatus = caseStatusTextField.getText();
                    if (caseStatus.isEmpty()) {
                        throw new IllegalArgumentException("Case Status cannot be empty");
                    }
                    cases.setCaseStatus(caseStatus);

                    // Check if Date Filed is empty
                    String dateFiled = dateFiledTextField.getText();
                    if (dateFiled.isEmpty()) {
                        throw new IllegalArgumentException("Date Filed cannot be empty");
                    }
                    cases.setDateFiled(Integer.parseInt(dateFiled));

                    // Set Date Closed to Ongoing
                    String dateClosed = dateClosedTextField.getText();
                    cases.setDateClosed(Integer.parseInt(dateClosed));

                    int clientID;
                    try {
                        clientID = Integer.parseInt(ClientIDTextField.getText());
                    } catch (NumberFormatException nfe) {
                        throw new IllegalArgumentException("Client ID Must be an Integer");
                    }

                    cases.create(cases.getcaseNumberr(), cases.getCaseTitle(), cases.getCaseDescription(), cases.getCaseStatus(), cases.getDateFiled(), cases.getDateClosed(), cases.getClientID());
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
