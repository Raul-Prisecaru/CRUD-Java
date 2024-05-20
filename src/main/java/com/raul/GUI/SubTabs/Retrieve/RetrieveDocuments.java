package com.raul.GUI.SubTabs.Retrieve;

import com.raul.Features.Clients;
import com.raul.Features.Documents;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RetrieveDocuments extends JPanel {
    private JTextField DocumentIDTextField;
    private JScrollPane jScrollPane;
    private DefaultTableModel tableModel;
    Documents documents = new Documents();
    public RetrieveDocuments() {
        // Layout for this Page
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Create Labels
        JLabel Title = new JLabel("Retrieve Documents from System");

        // Center the Title
        Title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set the Font
        Title.setFont(new Font("serif", Font.PLAIN, 20));

        // Add the labels to this panel
        add(Title);

        // Add Vertical Glue to push the components below
        add(Box.createVerticalGlue());

        // Create a nested panel with GridBagLayout for the JTextField
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        DocumentIDTextField = new JTextField(20);
        JLabel ClientIDLabel = new JLabel("Document ID");

        gbc.gridx = 0;
        gbc.gridy = 1;
        textFieldPanel.add(ClientIDLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        textFieldPanel.add(DocumentIDTextField, gbc);

        JButton getRecord = new JButton("Get Record");


        gbc.gridx = 1;
        gbc.gridy = 2;
        textFieldPanel.add(getRecord, gbc);

        List<Documents> documentList = documents.Retrieve();
        String[] columnNames = {"documentID", "caseID", "DocumentName", "DocumentType", "DocumentPath"};

        tableModel = new DefaultTableModel(columnNames, 0);

        for (Documents retrieveDocuments : documentList) {
            Object[] rowData = {
                    retrieveDocuments.getDocumentID(),
                    retrieveDocuments.getCaseID(),
                    retrieveDocuments.getDocumentName(),
                    retrieveDocuments.getDocumentType(),
                    retrieveDocuments.getDocumentPath()
            };
            tableModel.addRow(rowData);
        }

    JTable table = new JTable(tableModel);
    jScrollPane = new JScrollPane(table);
    add(jScrollPane);

    getRecord.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateTable();
        }
    });

    add(textFieldPanel);
    }

    private void updateTable() {
        try {
            String DocumentID;
            try {
                DocumentID = String.valueOf(DocumentIDTextField.getText());
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("Document ID Must be an Integer");
            }

            if (!DocumentID.isEmpty()) {
            // Clear the existing rows
            tableModel.setRowCount(0);

            // Retrieve the New records from DB after Action
            List<Documents> documentList = documents.RetrieveByID(Integer.parseInt(DocumentID));
            for (Documents retrieveDocuments : documentList) {
                Object[] rowData = {
                        retrieveDocuments.getDocumentID(),
                        retrieveDocuments.getCaseID(),
                        retrieveDocuments.getDocumentName(),
                        retrieveDocuments.getDocumentType(),
                        retrieveDocuments.getDocumentPath()
                };
                tableModel.addRow(rowData);
            }
            } else {
                // Clear the existing rows
                tableModel.setRowCount(0);

                // Retrieve the New records from DB after Action
            List<Documents> documentList = documents.Retrieve();
            for (Documents retrieveDocuments : documentList) {
                Object[] rowData = {
                        retrieveDocuments.getDocumentID(),
                        retrieveDocuments.getCaseID(),
                        retrieveDocuments.getDocumentName(),
                        retrieveDocuments.getDocumentType(),
                        retrieveDocuments.getDocumentPath()
                };
                tableModel.addRow(rowData);
            }
            }
        } catch (IllegalArgumentException IAE) {
            JOptionPane.showMessageDialog(this, IAE.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
}


}
