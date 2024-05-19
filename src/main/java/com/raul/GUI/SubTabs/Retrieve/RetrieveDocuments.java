package com.raul.GUI.SubTabs.Retrieve;

import com.raul.Features.Documents;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.jar.JarEntry;

public class RetrieveDocuments extends JPanel {
    public RetrieveDocuments() {
        Documents documents = new Documents();
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

        List<Documents> documentsList = documents.retrieve();

        String[] columnNames = {"documentID", "CaseID", "Document Name", "Document Type", "Document Path"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Documents retrieveDocument : documentsList) {
            Object[] rowData = {
                    retrieveDocument.getDocumentID(),
                    retrieveDocument.getCaseID(),
                    retrieveDocument.getDocumentName(),
                    retrieveDocument.getDocumentType(),
                    retrieveDocument.getDocumentPath()
            };
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);


    }
}
