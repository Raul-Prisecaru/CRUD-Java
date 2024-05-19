package com.raul.GUI.SubTabs.Create;

import com.raul.Features.Cases;
import com.raul.Features.Clients;
import com.raul.Features.Documents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class CreateDocuments extends JPanel {
    public CreateDocuments() {
        Documents documents = new Documents();
        // Layout for this Page using BoxLayout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create Title Label
        JLabel titleLabel = new JLabel("Add Documents to System");

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
        JLabel caseIDLabel = new JLabel("Case ID:");
        JTextField CaseIDTextField = new JTextField(15);

        // Set CaseID Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 1;
        textFieldPanel.add(caseIDLabel, gbc);

        // Set CaseID Text Field Positioning
        gbc.gridx = 1;
        gbc.gridy = 1;
        textFieldPanel.add(CaseIDTextField, gbc);

        // Create DocumentName Label and TextField
        JLabel documentNameLabel = new JLabel("Document Name:");
        JTextField documentNameTextField = new JTextField(15);

        // Set documentName Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 2;
        textFieldPanel.add(documentNameLabel, gbc);

        // Set documentName Text Field Positioning
        gbc.gridx = 1;
        gbc.gridy = 2;
        textFieldPanel.add(documentNameTextField, gbc);

        // Create and add DocumentType components
        JLabel documentTypeLabel = new JLabel("Document Type");
        JTextField documentTypeTextField = new JTextField(15);

        // Set DocumentType Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 3;
        textFieldPanel.add(documentTypeLabel, gbc);

        // Set DocumentType TextField Positioning
        gbc.gridx = 1;
        gbc.gridy = 3;
        textFieldPanel.add(documentTypeTextField, gbc);


        // Create and add documentPath components
        JLabel documentPathLabel = new JLabel("Document Path");
        JTextField documentPathTextField = new JTextField(15);


        // Set Case Status Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 4;
        textFieldPanel.add(documentPathLabel, gbc);

        // Set Client Case Status Positioning
        gbc.gridx = 1;
        gbc.gridy = 4;
        textFieldPanel.add(documentPathTextField, gbc);

        // Create Submit Button and Set Positioning
        JButton SubmitButton = new JButton("Submit");
        gbc.gridx = 1;
        gbc.gridy = 8;

        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Check if the answer is an Integer
                    int caseID;
                    try {
                        caseID = Integer.parseInt((CaseIDTextField.getText()));
                    } catch (NumberFormatException nfe) {
                        throw new IllegalArgumentException("CaseID must be a Value");
                    }
                    documents.setCaseID(caseID);

                    // Check if Document Name is empty
                    String documentName = documentNameTextField.getText();
                    if (documentName.isEmpty()) {
                        throw new IllegalArgumentException("DocumentName cannot be empty");
                    }
                    documents.setDocumentName(documentName);

                    // Check if DocumentType is empty
                    String documentType = documentTypeTextField.getText();
                    if (documentType.isEmpty()) {
                        throw new IllegalArgumentException("DocumentType cannot be empty");
                    }
                    documents.setDocumentType(documentType);


                    // Check if Date Filed is empty
                    String documentPath = documentPathTextField.getText();
                    if (documentPath.isEmpty()) {
                        throw new IllegalArgumentException("documentPath cannot be empty");
                    }


                    documents.create(documents.getCaseID(), documents.getDocumentName(), documents.getDocumentType(), documents.getDocumentPath());
                    JOptionPane.showMessageDialog(textFieldPanel,"Records Successfully Added");
                } catch (IllegalArgumentException iae) {
                    JOptionPane.showMessageDialog(textFieldPanel, iae.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        textFieldPanel.add(SubmitButton, gbc);

//         Add Panel
        add(textFieldPanel);

    }
}

