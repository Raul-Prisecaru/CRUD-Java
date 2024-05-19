package com.raul.GUI.SubTabs.FileReader;

import com.raul.Features.Documents;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReader extends JPanel {
    private JTextArea textArea;

    public FileReader() {
        Documents documents = new Documents();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        // Create JTextArea
        textArea = new JTextArea();
        textArea.setEditable(false);

        // Retrieve documents and store paths in an ArrayList
        List<Documents> documentsList = documents.retrieve();
        ArrayList<String> documentTypeList = new ArrayList<>();

        for (Documents retrieveDocuments : documentsList) {
            documentTypeList.add(retrieveDocuments.getDocumentPath());
        }

        // Convert the ArrayList to an array
        String[] documentArray = documentTypeList.toArray(new String[0]);
        System.out.println(Arrays.toString(documentArray));

        // Create a List using the Array
        JList<String> list = new JList<>(documentArray);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add ListSelectionListener
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {  // This is to prevent the event from firing twice
                    String selectedValue = list.getSelectedValue();
                    System.out.println("Selected Value: " + selectedValue);

                    // Clear the text when different option is selected
                    textArea.setText("");

                    try {
                        // Read the content of the File located in Documents Folder
                        java.io.FileReader fileReader = new java.io.FileReader("src/main/java/com/raul/Documents/" + selectedValue);

                        BufferedReader reader = new BufferedReader(fileReader);

                        String line;

                        // Check that if the Line isn't empty and display to textArea
                        while ((line = reader.readLine()) != null) {
                            textArea.append(line + "\n");
                        }

                        // Close the BufferedReader
                        reader.close();
                    } catch (IOException ie) {
                        // Print the stack trace for the exception
                        ie.printStackTrace();
                    }
                }
            }
        });


        JScrollPane textAreaScrollPane = new JScrollPane(textArea);
        add(new JScrollPane(list));
        add(textAreaScrollPane);
    }
}
