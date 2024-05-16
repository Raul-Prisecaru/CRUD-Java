package com.raul.GUI.SubTabs.Retrieve;

import com.raul.Features.Clients;

import javax.swing.*;
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

        // Set Spacing
//        add(Box.createVerticalStrut(10));
    }
}
