package com.raul.GUI.SubTabs.Retrieve;

import com.raul.Features.Clients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RetrieveClients extends JPanel {
    private JTextField ClientIDTextField;
    private JScrollPane jScrollPane;
    private DefaultTableModel tableModel;
    Clients clients = new Clients();
    public RetrieveClients() {
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

        // Add Vertical Glue to push the components below
        add(Box.createVerticalGlue());

        // Create a nested panel with GridBagLayout for the JTextField
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        ClientIDTextField = new JTextField(20);
        JLabel ClientIDLabel = new JLabel("Client ID");

        gbc.gridx = 0;
        gbc.gridy = 1;
        textFieldPanel.add(ClientIDLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        textFieldPanel.add(ClientIDTextField, gbc);

        JButton getRecord = new JButton("Get Record");


        gbc.gridx = 1;
        gbc.gridy = 2;
        textFieldPanel.add(getRecord, gbc);

        List<Clients> clientsList = clients.Retrieve();
        String[] columnNames = {"Client ID", "Client Name", "Client Address", "Client PhoneNumber", "Client Email"};

        tableModel = new DefaultTableModel(columnNames, 0);

        for (Clients retrieveClients : clientsList) {
            Object[] rowData = {
                    retrieveClients.getClientID(),
                    retrieveClients.getClientName(),
                    retrieveClients.getClientAddress(),
                    retrieveClients.getClientPhoneNumber(),
                    retrieveClients.getClientEmail()
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
            String clientID;
            try {
                clientID = String.valueOf(ClientIDTextField.getText());
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("Client ID Must be an Integer");
            }

            if (!clientID.isEmpty()) {
            // Clear the existing rows
            tableModel.setRowCount(0);

            // Retrieve the New records from DB after Action
            List<Clients> updatedClientsList = clients.retrieveByID(Integer.parseInt(clientID));
            for (Clients retrievedClients : updatedClientsList) {
                Object[] rowData = {
                        retrievedClients.getClientID(),
                        retrievedClients.getClientName(),
                        retrievedClients.getClientAddress(),
                        retrievedClients.getClientPhoneNumber(),
                        retrievedClients.getClientEmail()
                };
                tableModel.addRow(rowData);
            }
            } else {
                // Clear the existing rows
                tableModel.setRowCount(0);

                // Retrieve the New records from DB after Action
                List<Clients> updatedClientsList = clients.Retrieve();
                for (Clients retrievedClients : updatedClientsList) {
                    Object[] rowData = {
                            retrievedClients.getClientID(),
                            retrievedClients.getClientName(),
                            retrievedClients.getClientAddress(),
                            retrievedClients.getClientPhoneNumber(),
                            retrievedClients.getClientEmail()
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
