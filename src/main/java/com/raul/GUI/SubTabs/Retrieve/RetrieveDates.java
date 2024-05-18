package com.raul.GUI.SubTabs.Retrieve;

import com.raul.Features.Cases;
import com.raul.Features.ImportantDates;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RetrieveDates extends JPanel {
    public RetrieveDates() {
        ImportantDates dates = new ImportantDates();
        // Layout for this Page
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Create Labels
        JLabel Title = new JLabel("Retrieve Dates from System");


        // Center the Title
        Title.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Set the Font
        Title.setFont(new Font("serif", Font.PLAIN, 20));


        // Add the labels to this panel
        add(Title);

        // Set Spacing
//        add(Box.createVerticalStrut(10));

        List<ImportantDates> datesList = dates.retrieve();

        // Column names for the table
        String[] columnNames = {"Date ID", "Case ID", "Event Date", "Event Description"};

        // Create a table model with empty data and column names
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);



        // Enhanced Loop to do something per Case in caseList
        for (ImportantDates retrievedDates : datesList) {
            Object[] rowData = {
                    retrievedDates.getDateID(),
                    retrievedDates.getCaseID(),
                    retrievedDates.getEventDate(),
                    retrievedDates.getEventDescription()
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
