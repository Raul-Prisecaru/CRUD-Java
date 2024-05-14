package com.raul.Features;

import java.sql.*;
import com.raul.Database.LawDatabase;

public class Clients extends LawDatabase {
    //  Client
    int clientID;
    String clientName;
    String clientAddress;
    String clientPhoneNumber;
    String clientEmail;

//    // Case
//    int caseID;
//    int caseString;
//    String caseTitle;
//    String caseDescription;
//    String caseStatus;
//    int dateFiled;
//    int dateClosed;
//
//    // Document
//    int documentID;
//    String documentName;
//    String documentType;
//    String documentPath;
//
//    // Important Dates
//    int dateID;
//    int eventDate;
//    String eventDescription;

     // Getter and setter for clientID
    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    // Getter and setter for clientName
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    // Getter and setter for clientAddress
    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    // Getter and setter for clientPhoneNumber
    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String  clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    // Getter and setter for clientEmail
    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public void create(int clientID, String clientName, String clientAddress, String clientPhoneNumber, String clientEmail) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:LawDatabase.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite database established.");

            String sql = "INSERT INTO clients (client_id, client_name, client_address, client_phone, client_email) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, clientID);
            preparedStatement.setString(2, clientName);
            preparedStatement.setString(3, clientAddress);
            preparedStatement.setString(4, clientPhoneNumber);
            preparedStatement.setString(5, clientEmail);

            preparedStatement.executeUpdate();
            System.out.println("Record inserted successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
public void retrieve(int clientID) {

}

public void update(int clientID) {

}

public void delete(int clientID) {

}

}
