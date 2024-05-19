package com.raul.GUI.SubTabs.Delete;

import com.raul.Features.Documents;
import com.raul.Features.ImportantDates;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

public class DeleteDates extends JPanel {
    public DeleteDates() {
        ImportantDates dates = new ImportantDates();
        // Layout for this Page
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Create Labels
        JLabel Title = new JLabel("Delete Dates from System");


        // Center the Title
        Title.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Set the Font
        Title.setFont(new Font("serif", Font.PLAIN, 20));


        // Add the labels to this panel
        add(Title);


        // Add Vertical Glue to push the components below
        add(Box.createVerticalGlue());

        // Create a nested panel with GridLayout for the JTextField
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Using FlowLayout to align components to the left
        JTextField textField = new JTextField(20); // Specify the columns for the text field
        JLabel label2 = new JLabel("Date ID");
        textFieldPanel.add(label2);
        textFieldPanel.add(textField);

        // Add the nested panel to the main panel
        add(textFieldPanel);

        List<ImportantDates> dateList = dates.retrieve();

        // Column names for the table
        String[] columnNames = {"DateID", "CaseID", "EventDate", "EventDescription"};

        // Create a table model with empty data and column names
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (ImportantDates retrieveDates : dateList) {
            Object[] rowData = {
                    retrieveDates.getDateID(),
                    retrieveDates.getCaseID(),
                    retrieveDates.getEventDate(),
                    retrieveDates.getEventDescription(),
            };
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        JButton SubmitButton = new JButton("Delete");

        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dateID;
                    try {
                        dateID = Integer.parseInt(textField.getText());
                    } catch (NumberFormatException nfe) {
                        throw new IllegalArgumentException("Date ID Must be an Integer");
                    }

                    dates.setDateID(dateID);
                    dates.delete(dates.getDateID());

                    JOptionPane.showMessageDialog(textFieldPanel,"Records Successfully Deleted");

                } catch (IllegalArgumentException IAE) {
                    JOptionPane.showMessageDialog(textFieldPanel, IAE.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        add(SubmitButton);

    }
}
