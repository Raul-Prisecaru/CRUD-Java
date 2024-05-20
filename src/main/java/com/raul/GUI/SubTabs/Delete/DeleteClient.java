package com.raul.GUI.SubTabs.Delete;

import com.raul.Features.Clients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteClient extends JPanel {
    private JTextField ClientIDTextField;
    Clients clients = new Clients();
    public DeleteClient() {
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

        JButton UpdateButton = new JButton("Update Table");
        JButton DeleteButton = new JButton("Delete Record");


        gbc.gridx = 1;
        gbc.gridy = 2;
        textFieldPanel.add(DeleteButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        textFieldPanel.add(UpdateButton, gbc);


        List<Clients> clientsList = clients.retrieve();
        String[] columnNames = {"Client ID", "Client Name", "Client Address", "Client PhoneNumber", "Client Email"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

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
    JScrollPane jScrollPane = new JScrollPane(table);
    add(jScrollPane);

    UpdateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateTable();
        }
    });

    DeleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteClient();
        }
    });




    add(textFieldPanel);
    }

public void updateTable() {
    try {
        System.out.println("Test");
    } catch (Exception e) {
        System.out.println(e);
    }
}

public void deleteClient() {
try {
    System.out.println("Button Clicked");
          int clientID;
            try {
                clientID = Integer.parseInt(ClientIDTextField.getText());
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("Client ID Must be an Integer");
            }

            clients.setClientID(clientID);
            clients.delete(clients.getClientID());

//             Refresh the table after deletion
//            updateTable();

            JOptionPane.showMessageDialog(this, "Records Successfully Deleted");

        } catch (IllegalArgumentException IAE) {
            JOptionPane.showMessageDialog(this, IAE.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
}


}
