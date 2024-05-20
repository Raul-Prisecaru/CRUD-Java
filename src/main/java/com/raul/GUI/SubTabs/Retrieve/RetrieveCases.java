package com.raul.GUI.SubTabs.Retrieve;

import com.raul.Features.Cases;
import com.raul.Features.Clients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RetrieveCases extends JPanel {
    private JTextField CaseIDTextField;
    private JScrollPane jScrollPane;
    private DefaultTableModel tableModel;
    Cases cases = new Cases();
    public RetrieveCases() {
        // Layout for this Page
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Create Labels
        JLabel Title = new JLabel("Retrieve Cases from System");

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

        CaseIDTextField = new JTextField(20);
        JLabel CaseIDLabel = new JLabel("Client ID");

        gbc.gridx = 0;
        gbc.gridy = 1;
        textFieldPanel.add(CaseIDLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        textFieldPanel.add(CaseIDTextField, gbc);

        JButton getRecord = new JButton("Get Record");


        gbc.gridx = 1;
        gbc.gridy = 2;
        textFieldPanel.add(getRecord, gbc);

        List<Cases> caseList = cases.Retrieve();
        String[] columnNames = {"CaseID", "CaseNumber", "CaseTitle", "CaseDescription", "CaseStatus", "DateFiled", "DateClosed", "ClientID"};

        tableModel = new DefaultTableModel(columnNames, 0);

        for (Cases retrieveCases : caseList) {
            Object[] rowData = {
                    retrieveCases.getCaseID(),
                    retrieveCases.getcaseNumberr(),
                    retrieveCases.getCaseTitle(),
                    retrieveCases.getCaseDescription(),
                    retrieveCases.getCaseStatus(),
                    retrieveCases.getDateFiled(),
                    retrieveCases.getDateClosed(),
                    retrieveCases.getClientID(),
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
            String caseID;
            try {
                caseID = String.valueOf(CaseIDTextField.getText());
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("Client ID Must be an Integer");
            }

            if (!caseID.isEmpty()) {
            // Clear the existing rows
            tableModel.setRowCount(0);

            // Retrieve the New records from DB after Action
            List<Cases> updatedCasesList = cases.RetrieveByID(Integer.parseInt(caseID));
        for (Cases retrieveCases : updatedCasesList) {
            Object[] rowData = {
                    retrieveCases.getCaseID(),
                    retrieveCases.getcaseNumberr(),
                    retrieveCases.getCaseTitle(),
                    retrieveCases.getCaseDescription(),
                    retrieveCases.getCaseStatus(),
                    retrieveCases.getDateFiled(),
                    retrieveCases.getDateClosed(),
                    retrieveCases.getClientID(),
            };
            tableModel.addRow(rowData);
            }
            } else {
                // Clear the existing rows
                tableModel.setRowCount(0);

                // Retrieve the New records from DB after Action
                List<Cases> updatedClientsList = cases.Retrieve();
                for (Cases retrieveCases : updatedClientsList) {
                    Object[] rowData = {
                            retrieveCases.getCaseID(),
                            retrieveCases.getcaseNumberr(),
                            retrieveCases.getCaseTitle(),
                            retrieveCases.getCaseDescription(),
                            retrieveCases.getCaseStatus(),
                            retrieveCases.getDateFiled(),
                            retrieveCases.getDateClosed(),
                            retrieveCases.getClientID(),
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
