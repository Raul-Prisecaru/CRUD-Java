package com.raul;

import com.raul.GUI.SubTabs.Create.CreateCases;
import com.raul.GUI.SubTabs.Create.CreateClient;
import com.raul.GUI.SubTabs.Create.CreateDates;
import com.raul.GUI.SubTabs.Create.CreateDocuments;
import com.raul.GUI.SubTabs.Delete.DeleteCases;
import com.raul.GUI.SubTabs.Delete.DeleteClient;
import com.raul.GUI.SubTabs.Delete.DeleteDates;
import com.raul.GUI.SubTabs.Delete.DeleteDocuments;
import com.raul.GUI.SubTabs.Retrieve.RetrieveCases;
import com.raul.GUI.SubTabs.Retrieve.RetrieveClients;
import com.raul.GUI.SubTabs.Retrieve.RetrieveDates;
import com.raul.GUI.SubTabs.Retrieve.RetrieveDocuments;
import com.raul.GUI.SubTabs.Update.UpdateCases;
import com.raul.GUI.SubTabs.Update.UpdateClients;
import com.raul.GUI.SubTabs.Update.UpdateDates;
import com.raul.GUI.SubTabs.Update.UpdateDocuments;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Law Firm System");
        frame.setSize(650, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Importing CREATE files
        CreateClient createClient = new CreateClient();
        CreateCases createCases = new CreateCases();
        CreateDocuments createDocuments = new CreateDocuments();
        CreateDates createDates = new CreateDates();


        // Importing RETRIEVE Files
        RetrieveClients retrieveClients = new RetrieveClients();
        RetrieveCases retrieveCases = new RetrieveCases();
        RetrieveDocuments retrieveDocuments = new RetrieveDocuments();
        RetrieveDates retrieveDates = new RetrieveDates();

        // Importing UPDATE Files
        UpdateClients updateClients = new UpdateClients();
        UpdateCases updateCases = new UpdateCases();
        UpdateDocuments updateDocuments = new UpdateDocuments();
        UpdateDates updateDates = new UpdateDates();

        // Importing DELETE Files
        DeleteClient deleteClient = new DeleteClient();
        DeleteCases deleteCases = new DeleteCases();
        DeleteDocuments deleteDocuments = new DeleteDocuments();
        DeleteDates deleteDates = new DeleteDates();


        // Create Main Tabbed Panel
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create second Tabs for CREATE Operations
        JPanel createPanel = new JPanel();
        // Use BoxLayout
        createPanel.setLayout(new BoxLayout(createPanel, BoxLayout.Y_AXIS));
        JTabbedPane createNestedTabbedPane = new JTabbedPane();
        createNestedTabbedPane.addTab("👨‍💼 Clients", createClient);
        createNestedTabbedPane.addTab("💼 Cases", createCases);
        createNestedTabbedPane.addTab("📃 Documents", createDocuments);
        createNestedTabbedPane.addTab("📅 Dates", createDates);
        createPanel.add(createNestedTabbedPane);
        tabbedPane.addTab("✍ CREATE", createPanel);


        JPanel retrievePanel = new JPanel();
        retrievePanel.setLayout(new BoxLayout(retrievePanel, BoxLayout.Y_AXIS));
        JTabbedPane retrieveNestedTabbedPane = new JTabbedPane();
        retrieveNestedTabbedPane.addTab("👨‍💼 Clients", retrieveClients);
        retrieveNestedTabbedPane.addTab("💼 Cases", retrieveCases);
        retrieveNestedTabbedPane.addTab("📃 Documents", retrieveDocuments);
        retrieveNestedTabbedPane.addTab("📅 Dates", retrieveDates);
        retrievePanel.add(retrieveNestedTabbedPane);
        tabbedPane.addTab("\uD83D\uDCDA RETRIEVE", retrievePanel);

        JPanel updatePanel = new JPanel();
        updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.Y_AXIS));
        JTabbedPane updateNestedTabbedPane = new JTabbedPane();
        updateNestedTabbedPane.addTab("👨‍💼 Clients", updateClients);
        updateNestedTabbedPane.addTab("💼 Cases", updateCases);
        updateNestedTabbedPane.addTab("📃 Documents", updateDocuments);
        updateNestedTabbedPane.addTab("📅 Dates", updateDates);
        updatePanel.add(updateNestedTabbedPane);
        tabbedPane.addTab("\uD83D\uDD03 UPDATE", updatePanel);

        JPanel deletePanel = new JPanel();
        deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.Y_AXIS));
        JTabbedPane deleteNestedTabbedPane = new JTabbedPane();
        deleteNestedTabbedPane.addTab("👨‍💼 Clients", deleteClient);
        deleteNestedTabbedPane.addTab("💼 Cases", deleteCases);
        deleteNestedTabbedPane.addTab("📃 Documents", deleteDocuments);
        deleteNestedTabbedPane.addTab("📅 Dates", deleteDates);
        deletePanel.add(deleteNestedTabbedPane);
        tabbedPane.addTab("❌ DELETE", deletePanel);

        // Make Window Visible
        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}
