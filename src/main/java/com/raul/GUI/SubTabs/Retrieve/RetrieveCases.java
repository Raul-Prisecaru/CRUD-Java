package com.raul.GUI.SubTabs.Retrieve;

import com.raul.Features.Cases;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RetrieveCases extends JPanel {
    public RetrieveCases() {
        Cases cases = new Cases();

        // Layout for this Page
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create Labels
        JLabel title = new JLabel("Retrieve Cases from System");

        // Center the Title
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set the Font
        title.setFont(new Font("serif", Font.PLAIN, 20));

        // Add the title to this panel
        add(title);

        // Create JTextField with preferred size
        JTextField textField = new JTextField();
        // Set preferred size (Needs Fixing)
        textField.setPreferredSize(new Dimension(200, 30));

        // Add the text field to this panel
        add(textField);

        // Retrieve cases
        List<Cases> caseList = cases.retrieve();

        // Column names for the table
        String[] columnNames = {"Case ID", "Case Number", "Case Title", "Case Description", "Case Status", "Date Filed", "Date Closed", "Client ID"};

        // Create a table model with empty data and column names
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);



        // Enhanced Loop to do something per Case in caseList
        for (Cases retrievedCase : caseList) {
            Object[] rowData = {
                    retrievedCase.getCaseID(),
                    retrievedCase.getcaseNumberr(),
                    retrievedCase.getCaseTitle(),
                    retrievedCase.getCaseDescription(),
                    retrievedCase.getCaseStatus(),
                    retrievedCase.getDateFiled(),
                    retrievedCase.getDateClosed(),
                    retrievedCase.getClientID()
            };
            tableModel.addRow(rowData);
        }



        // Create a JTable with the table model
        JTable table = new JTable(tableModel);

        // Prevent Table from being edited
        table.setEnabled(false);

        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to this panel
        add(scrollPane);
    }
}
