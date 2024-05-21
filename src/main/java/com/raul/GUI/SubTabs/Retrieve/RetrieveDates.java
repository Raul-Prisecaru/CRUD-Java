package com.raul.GUI.SubTabs.Retrieve;

import com.raul.Features.Clients;
import com.raul.Features.ImportantDates;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RetrieveDates extends JPanel {
    private JTextField DateIDTextField;
    private JScrollPane jScrollPane;
    private DefaultTableModel tableModel;
    ImportantDates dates = new ImportantDates();
    public RetrieveDates() {
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

        // Add Vertical Glue to push the components below
        add(Box.createVerticalGlue());

        // Create a nested panel with GridBagLayout for the JTextField
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        DateIDTextField = new JTextField(20);
        JLabel DateIDLabel = new JLabel("Date ID");

        gbc.gridx = 0;
        gbc.gridy = 1;
        textFieldPanel.add(DateIDLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        textFieldPanel.add(DateIDTextField, gbc);

        JButton getRecord = new JButton("Update Table / Get Record");


        gbc.gridx = 1;
        gbc.gridy = 2;
        textFieldPanel.add(getRecord, gbc);

        List<ImportantDates> dateList = dates.Retrieve();
        String[] columnNames = {"DateID", "CaseID", "eventDate", "eventDescription"};

        tableModel = new DefaultTableModel(columnNames, 0);

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
            String dateID;
            try {
                dateID = String.valueOf(DateIDTextField.getText());
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("Date ID Must be an Integer");
            }

            if (!dateID.isEmpty()) {
            // Clear the existing rows
            tableModel.setRowCount(0);

            // Retrieve the New records from DB after Action
            List<ImportantDates> dateList = dates.Retrieve(Integer.parseInt(dateID));
            for (ImportantDates retrieveDates : dateList) {
                Object[] rowData = {
                    retrieveDates.getDateID(),
                    retrieveDates.getCaseID(),
                    retrieveDates.getEventDate(),
                    retrieveDates.getEventDescription(),
            };
            tableModel.addRow(rowData);
            }
            } else {
                // Clear the existing rows
                tableModel.setRowCount(0);

                // Retrieve the New records from DB after Action
                List<ImportantDates> dateList = dates.Retrieve();
                for (ImportantDates retrieveDates : dateList) {
                    Object[] rowData = {
                            retrieveDates.getDateID(),
                            retrieveDates.getCaseID(),
                            retrieveDates.getEventDate(),
                            retrieveDates.getEventDescription(),
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
