package com.raul.GUI.SubTabs.Delete;

import com.raul.CustomErrorHandling.IDNotFoundException;
import com.raul.Features.Cases;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteCases extends JPanel {
    private JTextField CasesIDTextField;
    private JScrollPane jScrollPane;
    private DefaultTableModel tableModel;
    Cases cases = new Cases();
    public DeleteCases() {
        // Layout for this Page
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Create Labels
        JLabel Title = new JLabel("Delete Cases from System");

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

        CasesIDTextField = new JTextField(20);
        JLabel ClientIDLabel = new JLabel("Case ID");

        gbc.gridx = 0;
        gbc.gridy = 1;
        textFieldPanel.add(ClientIDLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        textFieldPanel.add(CasesIDTextField, gbc);

        JButton DeleteButton = new JButton("Delete Record");
        JButton UpdateButton = new JButton("Update Table");


        gbc.gridx = 1;
        gbc.gridy = 2;
        textFieldPanel.add(DeleteButton, gbc);


        gbc.gridx = 1;
        gbc.gridy = 3;
        textFieldPanel.add(UpdateButton, gbc);


        List<Cases> casesList = cases.Retrieve();
        String[] columnNames = {"CaseID", "CaseNumber", "CaseTitle", "CaseDescription", "CaseStatus", "DateFiled", "DateClosed", "ClientID"};

        tableModel = new DefaultTableModel(columnNames, 0);

        for (Cases retrieveCases : casesList) {
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

    DeleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteCases();

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

          List<Cases> casesList = cases.Retrieve();
          for (Cases retrieveCases : casesList) {
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
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }

public void deleteCases() {
    try {

            int CaseID = Integer.parseInt(CasesIDTextField.getText());
            if (!cases.caseIDExists(CaseID)) {
                throw new IDNotFoundException("CaseID Doesn't Exists");
            }



            // Set the ClientID of user Response from TextField
            cases.setCaseID(CaseID);
            // Delete the relevant Record through delete method
            cases.Delete(cases.getCaseID());

            // Pop-up Window with Message notifying User that Record is successfully deleted
            JOptionPane.showMessageDialog(this, "Records Successfully Deleted");




} catch (IDNotFoundException ife) {
        JOptionPane.showMessageDialog(this, ife.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }}}
