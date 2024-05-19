package com.raul.GUI.SubTabs.Delete;

import com.raul.Features.Cases;
import com.raul.Features.Clients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

public class DeleteCases extends JPanel {
    public DeleteCases() {
        Cases cases = new Cases();
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

        // Create a nested panel with GridLayout for the JTextField
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Using FlowLayout to align components to the left
        JTextField textField = new JTextField(20); // Specify the columns for the text field
        JLabel label2 = new JLabel("Case ID");
        textFieldPanel.add(label2);
        textFieldPanel.add(textField);

        // Add the nested panel to the main panel
        add(textFieldPanel);

        List<Cases> caseList = cases.retrieve();

        // Column names for the table
        String[] columnNames = {"CaseID", "CaseNumber", "CaseTitle", "CaseDescription", "CaseStatus", "DateFiled", "DateClosed", "ClientID"};

        // Create a table model with empty data and column names
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Cases retrieveCases : caseList) {
            Object[] rowData = {
                    retrieveCases.getCaseID(),
                    retrieveCases.getcaseNumberr(),
                    retrieveCases.getCaseTitle(),
                    retrieveCases.getCaseDescription(),
                    retrieveCases.getCaseStatus(),
                    retrieveCases.getDateFiled(),
                    retrieveCases.getDateClosed(),
                    retrieveCases.getClientID()
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
                    int CaseID = Integer.parseInt(textField.getText());
                    System.out.println(CaseID);
                    cases.delete(CaseID);

                } catch (NumberFormatException NFE) {
                    System.out.println("Input Must be a Number!");
                }

            }
        });

        add(SubmitButton);

    }
}
