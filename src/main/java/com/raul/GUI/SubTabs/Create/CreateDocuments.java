package com.raul.GUI.SubTabs.Create;
import com.raul.Features.Documents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class CreateDocuments extends JPanel {
    private JTextField documentPathTextField;
    private Path selectedFilePath;

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
        JTextField caseIDTextField = new JTextField(15);

        // Set CaseID Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 1;
        textFieldPanel.add(caseIDLabel, gbc);

        // Set CaseID Text Field Positioning
        gbc.gridx = 1;
        gbc.gridy = 1;
        textFieldPanel.add(caseIDTextField, gbc);

        // Create DocumentName Label and TextField
        JLabel documentNameLabel = new JLabel("Document Name:");
        JTextField documentNameTextField = new JTextField(15);
        documentNameTextField.setEditable(false);

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
        JButton documentButton = new JButton("Select File");
        documentPathTextField = new JTextField(15);
        documentPathTextField.setEditable(false);

        // Set documentPath Label Positioning
        gbc.gridx = 0;
        gbc.gridy = 4;
        textFieldPanel.add(documentPathLabel, gbc);

        // Set documentPath TextField Positioning
        gbc.gridx = 1;
        gbc.gridy = 4;
        textFieldPanel.add(documentPathTextField, gbc);


        gbc.gridx = 2;
        gbc.gridy = 4;
        textFieldPanel.add(documentButton, gbc);

        // Create Submit Button and Set Positioning
        JButton submitButton = new JButton("Submit");
        gbc.gridx = 1;
        gbc.gridy = 5;

        documentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                int result = jFileChooser.showOpenDialog(textFieldPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jFileChooser.getSelectedFile();
                    selectedFilePath = selectedFile.toPath();
                    documentPathTextField.setText(selectedFilePath.toString());

                    // Extract file name and set it as the document name
                    String fileName = selectedFile.getName();
                    documentNameTextField.setText(fileName);


                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Check if the answer is an Integer
                    int caseID;
                    try {
                        caseID = Integer.parseInt(caseIDTextField.getText());
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

                    // Check if Document Path is empty
                    String documentPath = documentPathTextField.getText();
                    if (documentPath.isEmpty()) {
                        throw new IllegalArgumentException("Document Path cannot be empty");
                    }

                    // Move the selected file to a specific directory
                    Path sourcePath = Paths.get(documentPath);
                    Path targetPath = Paths.get("src/main/java/com/raul/Documents/" + sourcePath.getFileName().toString());
                    Files.move(sourcePath, targetPath);

                    documents.setDocumentPath(documentName);

                    documents.create(documents.getCaseID(), documents.getDocumentName(), documents.getDocumentType(), documents.getDocumentPath());
                    JOptionPane.showMessageDialog(textFieldPanel, "Records Successfully Added");
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(textFieldPanel, "Failed to move the file: " + ioException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
