package com.raul.Features;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.raul.Database.LawDatabase;

public class Cases extends LawDatabase {

    // Case
    int caseID;
    String caseNumber;
    String caseTitle;
    String caseDescription;
    String caseStatus;
    int dateFiled;
    int dateClosed;
    int clientID;

        // Getter and Setter for caseID
    public int getCaseID() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }

    // Getter and Setter for caseString
    public String getcaseNumberr() {
        return caseNumber;
    }

    public void setcaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    // Getter and Setter for caseTitle
    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    // Getter and Setter for caseDescription
    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    // Getter and Setter for caseStatus
    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    // Getter and Setter for dateFiled
    public int getDateFiled() {
        return dateFiled;
    }

    public void setDateFiled(int dateFiled) {
        this.dateFiled = dateFiled;
    }

    // Getter and Setter for dateClosed
    public int getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(int dateClosed) {
        this.dateClosed = dateClosed;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }




    public void create(String caseNumber, String caseTitle, String caseDescription, String caseStatus, int dateFiled, int dateClosed, int clientID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite database established.");

            String sql = "INSERT INTO cases (case_number, case_title, case_description, case_status, date_filed, date_closed, client_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, caseNumber);
            preparedStatement.setString(2, caseTitle);
            preparedStatement.setString(3, caseDescription);
            preparedStatement.setString(4, caseStatus);
            preparedStatement.setInt(5, dateFiled);
            preparedStatement.setInt(6, dateClosed);
            preparedStatement.setInt(7, clientID);

            int afftectedRows = preparedStatement.executeUpdate();
            if (afftectedRows > 0) {
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        int lastInsertedId = rs.getInt(1);
                    }
                }
            }
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

    public List<Cases> retrieve() {
        List<Cases> caseList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite database established.");

            statement = connection.createStatement();
            String sql = "SELECT * FROM cases";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Cases caseObject = new Cases();

                // Set values to caseObject
                caseObject.setCaseID(resultSet.getInt("case_id"));
                caseObject.setcaseNumber(resultSet.getString("case_number"));
                caseObject.setCaseTitle(resultSet.getString("case_title"));
                caseObject.setCaseDescription(resultSet.getString("case_description"));
                caseObject.setCaseStatus(resultSet.getString("case_status"));
                caseObject.setDateFiled(resultSet.getInt("date_filed"));
                caseObject.setDateClosed(resultSet.getInt("date_closed"));
                caseObject.setClientID(resultSet.getInt("client_id"));

                // Add caseObject to the list
                caseList.add(caseObject);

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
        return caseList;
    }

public void update(int caseID, String caseNumber, String caseTitle, String caseDescription, String caseStatus, int dateFiled, int dateClosed, int clientID) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite database established.");

        String sql = "UPDATE cases " +
                     "SET case_number = ?, case_title = ?, case_description = ?, case_status = ?, date_filed = ?, date_closed = ?, client_id = ? " +
                     "WHERE case_id = ?";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, caseNumber);
        preparedStatement.setString(2, caseTitle);
        preparedStatement.setString(3, caseDescription);
        preparedStatement.setString(4, caseStatus);
        preparedStatement.setInt(5, dateFiled);
        preparedStatement.setInt(6, dateClosed);
        preparedStatement.setInt(7, clientID);
        preparedStatement.setInt(8, caseID);

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

public void delete(int caseID) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite database established.");

        String sql = "DELETE FROM cases WHERE case_id = ?" ;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, caseID);
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





}



