package com.raul.GUI.SubTabs.Delete;

import com.raul.Features.Clients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

public class DeleteClient extends JPanel {
    public DeleteClient() {
        Clients clients = new Clients();
        // Layout for this Page
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Create Labels
        JLabel Title = new JLabel("Delete Clients from System");


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
        JLabel label2 = new JLabel("Client ID");
        textFieldPanel.add(label2);
        textFieldPanel.add(textField);

        // Add the nested panel to the main panel
        add(textFieldPanel);

        List<Clients> clientsList = clients.retrieve();

        // Column names for the table
        String[] columnNames = {"Client ID", "Client Name", "Client Address", "Client PhoneNumber", "Client Email"};

        // Create a table model with empty data and column names
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Clients retrievedClients : clientsList) {
            Object[] rowData = {
                    retrievedClients.getClientID(),
                    retrievedClients.getClientName(),
                    retrievedClients.getClientAddress(),
                    retrievedClients.getClientPhoneNumber(),
                    retrievedClients.getClientEmail()
            };
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);
        table.setEnabled(false);

        final JScrollPane[] scrollPane = {new JScrollPane(table)};
        add(scrollPane[0]);

        JButton SubmitButton = new JButton("Delete");
        JButton UpdateButton = new JButton("Update Table");


        UpdateButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Create a new table model with updated data
            DefaultTableModel newTableModel = new DefaultTableModel(columnNames, 0);
            List<Clients> updatedClientsList = clients.retrieve(); // Retrieve updated clients list

            for (Clients retrievedClients : updatedClientsList) {
                Object[] rowData = {
                        retrievedClients.getClientID(),
                        retrievedClients.getClientName(),
                        retrievedClients.getClientAddress(),
                        retrievedClients.getClientPhoneNumber(),
                        retrievedClients.getClientEmail()
                };
                newTableModel.addRow(rowData);
            }

            JTable newTable = new JTable(newTableModel);
            newTable.setEnabled(false);
            JScrollPane newScrollPane = new JScrollPane(newTable);

            // Remove the old table and add the new one
            remove(scrollPane[0]);
            scrollPane[0] = newScrollPane;
            add(scrollPane[0], BorderLayout.CENTER);

            // Refresh the panel
            revalidate();
            repaint();

            JOptionPane.showMessageDialog(textFieldPanel, "Table Updated");

        } catch (IllegalArgumentException IAE) {
            JOptionPane.showMessageDialog(textFieldPanel, IAE.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});





        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int clientID;
                    try {
                        clientID = Integer.parseInt(textField.getText());
                    } catch (NumberFormatException nfe) {
                        throw new IllegalArgumentException("Client ID Must be an Integer");
                    }

                    clients.setClientID(clientID);
                    clients.delete(clients.getClientID());


                    JOptionPane.showMessageDialog(textFieldPanel,"Records Successfully Deleted");


                } catch (IllegalArgumentException IAE) {
                    JOptionPane.showMessageDialog(textFieldPanel, IAE.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        add(SubmitButton);
        add(UpdateButton);

    }
}
