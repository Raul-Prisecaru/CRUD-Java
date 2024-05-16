package com.raul.GUI.SubTabs.Retrieve;

import com.raul.Features.Clients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RetrieveClients extends JPanel {
    public RetrieveClients() {
        Clients clients = new Clients();
        // Layout for this Page
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Create Labels
        JLabel Title = new JLabel("Retrieve Clients from System");


        // Center the Title
        Title.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Set the Font
        Title.setFont(new Font("serif", Font.PLAIN, 20));


        // Add the labels to this panel
        add(Title);

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

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);


    }
}
