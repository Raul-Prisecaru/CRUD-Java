package com.raul.Features;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Documents {

    // Document
    int documentID;
    int caseID;
    String documentName;
    String documentType;
    String documentPath;

     // Getter and setter for documentID
    public int getDocumentID() {
        return documentID;
    }

    public void setDocumentID(int documentID) {
        this.documentID = documentID;
    }

         // Getter and setter for documentID
    public int getCaseID() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }


    // Getter and setter for documentName
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    // Getter and setter for documentType
    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    // Getter and setter for documentPath
    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public void create(int documentID, int caseID, String documentName, String documentType, String documentPath) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:LawDatabase.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite database established.");

            String sql = "INSERT INTO documents (document_id, case_id, document_name, document_type, document_path) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, documentID);
            preparedStatement.setInt(2, caseID);
            preparedStatement.setString(3, documentName);
            preparedStatement.setString(4, documentType);
            preparedStatement.setString(5, documentPath);

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
public List<Documents> retrieve() {
        List<Documents> documentsList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:LawDatabase.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite database established.");

            statement = connection.createStatement();
            String sql = "SELECT * FROM documents";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Documents documentsObject = new Documents();

                documentsObject.setDocumentID(resultSet.getInt("document_id"));
                documentsObject.setCaseID(resultSet.getInt("case_id"));
                documentsObject.setDocumentName(resultSet.getString("document_name"));
                documentsObject.setDocumentType(resultSet.getString("document_type"));
                documentsObject.setDocumentPath(resultSet.getString("document_path"));

                documentsList.add(documentsObject);
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
        return documentsList;
    }

public void update(int documentID, int caseID, String documentName, String documentType, String documentPath) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:LawDatabase.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite database established.");

        String sql = "UPDATE documents " +
                     "SET case_id = ?, document_name = ?, document_type = ?, document_path = ?" +
                     "WHERE document_id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, caseID);
        preparedStatement.setString(2, documentName);
        preparedStatement.setString(3, documentType);
        preparedStatement.setString(4, documentPath);
        preparedStatement.setInt(5, documentID);
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

public void delete(int documentID) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:LawDatabase.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite database established.");

        String sql = "DELETE FROM documents WHERE document_id = ?" ;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, documentID);
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
}}
