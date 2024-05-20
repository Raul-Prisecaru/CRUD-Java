package com.raul.Features;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.raul.Abstraction.ClientDatabase;

public class Clients extends ClientDatabase {
    //  Client
    int clientID;
    private String clientName;
    private String clientAddress;
    private String clientPhoneNumber;
    private String clientEmail;


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
    @Override
    public void Create(String clientName, String clientAddress, String clientPhoneNumber, String clientEmail) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite database established.");

            String sql = "INSERT INTO clients (client_name, client_address, client_phone, client_email) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, clientName);
            preparedStatement.setString(2, clientAddress);
            preparedStatement.setString(3, clientPhoneNumber);
            preparedStatement.setString(4, clientEmail);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        int lastInsertedId = rs.getInt(1);
                    }
                }
            }
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
    @Override
    public List<Clients> Retrieve() {
        List<Clients> clientList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite database established.");

            statement = connection.createStatement();
            String sql = "SELECT * FROM clients";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Clients clientObjects = new Clients();

                clientObjects.setClientID(resultSet.getInt("client_id"));
                clientObjects.setClientName(resultSet.getString("client_name"));
                clientObjects.setClientAddress(resultSet.getString("client_address"));
                clientObjects.setClientPhoneNumber(resultSet.getString("client_phone"));
                clientObjects.setClientEmail(resultSet.getString("client_email"));

                clientList.add(clientObjects);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return clientList;
    }
@Override
public void Update(int clientID, String clientName, String clientAddress, String clientPhoneNumber, String clientEmail) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite database established.");

        String sql = "UPDATE clients " +
                     "SET client_name = ?, client_address = ?, client_phone = ?, client_email = ? " +
                     "WHERE client_id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, clientName);
        preparedStatement.setString(2, clientAddress);
        preparedStatement.setString(3, clientPhoneNumber);
        preparedStatement.setString(4, clientEmail);
        preparedStatement.setInt(5, clientID);

        preparedStatement.executeUpdate();
        System.out.println("Record updated successfully.");
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


@Override
public void Delete(int clientID) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite database established.");

        String sql = "DELETE FROM clients WHERE client_id = ?" ;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, clientID);
        preparedStatement.executeUpdate();
        System.out.println("Record Deleted Successfully.");
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


public List<Clients> retrieveByID(int clientID) {
    List<Clients> clientList = new ArrayList<>();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite database established.");

        String sql = "SELECT * FROM clients WHERE client_id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, clientID);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Clients clientObjects = new Clients();

            clientObjects.setClientID(resultSet.getInt("client_id"));
            clientObjects.setClientName(resultSet.getString("client_name"));
            clientObjects.setClientAddress(resultSet.getString("client_address"));
            clientObjects.setClientPhoneNumber(resultSet.getString("client_phone"));
            clientObjects.setClientEmail(resultSet.getString("client_email"));

            clientList.add(clientObjects);
        }
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println(e);
    } finally {
        try {
            if (resultSet != null)
                resultSet.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    return clientList;
}






}




