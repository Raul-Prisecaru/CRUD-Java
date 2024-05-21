package com.raul.GUI.SubTabs.Update;

import com.raul.CustomErrorHandling.IDNotFoundException;
import com.raul.Features.Cases;
import com.raul.Features.ImportantDates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateDates extends JPanel {
    public UpdateDates() {
        ImportantDates dates = new ImportantDates();
        Cases cases = new Cases();
        // Layout for this Page using BoxLayout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create Title Label
        JLabel titleLabel = new JLabel("Update Dates from System");

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

        // Create DateID Label and TextField
        JLabel dateIDLabel = new JLabel("Date ID");
        JTextField dateIDTextField = new JTextField(15);

        // Set CaseID Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 0;
        textFieldPanel.add(dateIDLabel, gbc);

        // Set CaseID Text Field Positioning
        gbc.gridx = 1;
        gbc.gridy = 0;
        textFieldPanel.add(dateIDTextField, gbc);


        // Create CaseID Label and TextField
        JLabel caseIDLabel = new JLabel("Case ID");
        JTextField CaseIDTextField = new JTextField(15);

        // Set CaseID Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 1;
        textFieldPanel.add(caseIDLabel, gbc);

        // Set CaseID Text Field Positioning
        gbc.gridx = 1;
        gbc.gridy = 1;
        textFieldPanel.add(CaseIDTextField, gbc);

        // Create eventDates Label and TextField
        JLabel eventDatesLabel = new JLabel("event Dates:");
        JTextField eventDatesTextField = new JTextField(15);

        // Set eventDates Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 2;
        textFieldPanel.add(eventDatesLabel, gbc);

        // Set eventDates Text Field Positioning
        gbc.gridx = 1;
        gbc.gridy = 2;
        textFieldPanel.add(eventDatesTextField, gbc);

        // Create and add eventDescription components
        JLabel eventDescriptionLabel = new JLabel("Event Description");
        JTextField eventDescriptionTextField = new JTextField(15);

        // Set eventDescription Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 3;
        textFieldPanel.add(eventDescriptionLabel, gbc);

        // Set eventDescription TextField Positioning
        gbc.gridx = 1;
        gbc.gridy = 3;
        textFieldPanel.add(eventDescriptionTextField, gbc);


        // Create Submit Button and Set Positioning
        JButton SubmitButton = new JButton("Submit");
        gbc.gridx = 1;
        gbc.gridy = 8;

        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    // Check if the answer is an Integer
                    int dateID;
                    try {
                        dateID = Integer.parseInt((dateIDTextField.getText()));
                    } catch (NumberFormatException nfe) {
                        throw new IllegalArgumentException("dateID must be a Value");
                    }

                    // Check if Date ID exists
                    if (!dates.dateIDExists(dateID)) {
                        throw new IDNotFoundException("DateID Doesn't Exists");
                    }

                    dates.setDateID(dateID);

                    // Check if the answer is an Integer
                    int caseID;
                    try {
                        caseID = Integer.parseInt((CaseIDTextField.getText()));
                    } catch (NumberFormatException nfe) {
                        throw new IllegalArgumentException("CaseID must be a Value");
                    }

                    // Check if Case ID exists
                    if (!cases.caseIDExists(caseID)) {
                        throw new IDNotFoundException("CaseID Doesn't Exists");
                    }

                    dates.setCaseID(caseID);

                    String eventDate = (eventDatesTextField.getText());
                    if (!eventDate.matches("\\d{4}\\d{2}\\d{2}")) {
                        throw new IllegalArgumentException("Ensure that Date follows: YYYYMMDD");
                    }
                    dates.setEventDate(eventDate);

                    String eventDescription = eventDescriptionTextField.getText();
                    if (eventDescription.isEmpty()) {
                        throw new IllegalArgumentException("Event Description cannot be empty");
                    }
                    dates.setEventDescription(eventDescription);
                    dates.Update(dates.getDateID(), dates.getCaseID(), dates.getEventDate(), dates.getEventDescription());

                    JOptionPane.showMessageDialog(textFieldPanel,"Records Successfully Added");
                } catch (IllegalArgumentException iae) {
                    JOptionPane.showMessageDialog(textFieldPanel, iae.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                } catch (IDNotFoundException ife) {
                    JOptionPane.showMessageDialog(textFieldPanel, ife.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        textFieldPanel.add(SubmitButton, gbc);

//         Add Panel
        add(textFieldPanel);

    }
}

