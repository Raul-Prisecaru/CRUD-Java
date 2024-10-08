package com.raul.GUI.SubTabs.Delete;

import com.raul.CustomErrorHandling.IDNotFoundException;
import com.raul.Features.ImportantDates;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteDates extends JPanel {
    private JTextField DatesIDTextField;
    private JScrollPane jScrollPane;
    private DefaultTableModel tableModel;
    ImportantDates dates = new ImportantDates();

    public DeleteDates() {
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

        // Create a nested panel with GridBagLayout for the JTextField
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        DatesIDTextField = new JTextField(20);
        JLabel DateIDLabel = new JLabel("Date ID");

        gbc.gridx = 0;
        gbc.gridy = 1;
        textFieldPanel.add(DateIDLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        textFieldPanel.add(DatesIDTextField, gbc);

        JButton DeleteButton = new JButton("Delete Record");
        JButton UpdateButton = new JButton("Update Table");


        gbc.gridx = 1;
        gbc.gridy = 2;
        textFieldPanel.add(DeleteButton, gbc);


        gbc.gridx = 1;
        gbc.gridy = 3;
        textFieldPanel.add(UpdateButton, gbc);



        List<ImportantDates> dateList = dates.Retrieve();
        String[] columnNames = {"DateID", "CaseID", "EventDate", "EventDescription"};

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

    DeleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteDate();
            updateTable();
        }
    });

    UpdateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateTable();
            System.out.println("Table Is Updated");
        }
    });



    add(textFieldPanel);
    }

    private void updateTable() {
        try {
            // Clear the existing rows
            tableModel.setRowCount(0);

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
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }

public void deleteDate() {
try {
            int DateID = Integer.parseInt(DatesIDTextField.getText());
            if (!dates.dateIDExists(DateID)) {
                throw new IDNotFoundException("DateID Doesn't Exists");
            }
        // Set the ClientID of user Response from TextField
        dates.setDateID(DateID);
        // Delete the relevant Record through delete method
        dates.Delete(dates.getDateID());

        // Pop-up Window with Message notifying User that Record is successfully deleted
        JOptionPane.showMessageDialog(this, "Records Successfully Deleted");

        // Pop-up Window with Message Notifying User that Input is Invalid
    } catch (IDNotFoundException ife) {
        JOptionPane.showMessageDialog(this, ife.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

}
}


}
