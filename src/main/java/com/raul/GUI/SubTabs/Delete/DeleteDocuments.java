package com.raul.GUI.SubTabs.Delete;

import com.raul.CustomErrorHandling.IDNotFoundException;
import com.raul.Features.Documents;
import com.raul.Features.ImportantDates;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteDocuments extends JPanel {
    private JTextField DocumentIDTextField;
    private JScrollPane jScrollPane;
    private DefaultTableModel tableModel;
    Documents documents = new Documents();

    public DeleteDocuments() {
        // Layout for this Page
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Create Labels
        JLabel Title = new JLabel("Delete Documents from System");

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
        JLabel DocumentIDLabel = new JLabel("Document ID");

        gbc.gridx = 0;
        gbc.gridy = 1;
        textFieldPanel.add(DocumentIDLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        textFieldPanel.add(DocumentIDTextField, gbc);

        JButton DeleteButton = new JButton("Update Table / Delete Record");


        gbc.gridx = 1;
        gbc.gridy = 2;
        textFieldPanel.add(DeleteButton, gbc);


        List<Documents> DocumentList = documents.Retrieve();
        String[] columnNames = {"DocumentID", "CaseID", "DocumentName", "DocumentType", "DocumentPath"};

        tableModel = new DefaultTableModel(columnNames, 0);

        for (Documents retrieveDocuments : DocumentList) {
            Object[] rowData = {
                    retrieveDocuments.getDocumentID(),
                    retrieveDocuments.getCaseID(),
                    retrieveDocuments.getDocumentName(),
                    retrieveDocuments.getDocumentType(),
                    retrieveDocuments.getDocumentPath(),

            };
            tableModel.addRow(rowData);
        }

    JTable table = new JTable(tableModel);
    jScrollPane = new JScrollPane(table);
    add(jScrollPane);

    DeleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteCases();
            updateTable();
        }
    });

    add(textFieldPanel);
    }

    private void updateTable() {
        try {
            // Clear the existing rows
            tableModel.setRowCount(0);

       List<Documents> DocumentList = documents.Retrieve();
        for (Documents retrieveDocuments : DocumentList) {
            Object[] rowData = {
                    retrieveDocuments.getDocumentID(),
                    retrieveDocuments.getCaseID(),
                    retrieveDocuments.getDocumentName(),
                    retrieveDocuments.getDocumentType(),
                    retrieveDocuments.getDocumentPath(),

            };
            tableModel.addRow(rowData);
        }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }

public void deleteCases() {
try {
    int documentID = Integer.parseInt(DocumentIDTextField.getText());
    if (!documents.documentIDExists(documentID)) {
            throw new IDNotFoundException("CaseID Doesn't Exists");
            }
        // Set the ClientID of user Response from TextField
        documents.setDocumentID(documentID);
        // Delete the relevant Record through delete method
        documents.Delete(documents.getDocumentID());

        // Pop-up Window with Message notifying User that Record is successfully deleted
        JOptionPane.showMessageDialog(this, "Records Successfully Deleted");

        // Pop-up Window with Message Notifying User that Input is Invalid
    } catch (IDNotFoundException ife) {
            JOptionPane.showMessageDialog(this, ife.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

}
}


}
