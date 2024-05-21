package com.raul.Features;

import com.raul.Features.DocumentDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Documents extends DocumentDatabase {

    // Document
    private int documentID;
    private int caseID;
    private String documentName;
    private String documentType;
    private String documentPath;

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

    @Override
    public void Create(int caseID, String documentName, String documentType, String documentPath) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite database established.");

            String sql = "INSERT INTO documents (case_id, document_name, document_type, document_path) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, caseID);
            preparedStatement.setString(2, documentName);
            preparedStatement.setString(3, documentType);
            preparedStatement.setString(4, documentPath);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
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

    @Override
    public List<Documents> Retrieve() {
        List<Documents> documentsList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
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

    @Override
    public void Update(int documentID, int caseID, String documentName, String documentType, String documentPath) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
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

@Override
public void Delete(int documentID) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
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
}

@Override
public List<Documents> Retrieve(int documentID) {
    List<Documents> documentsList = new ArrayList<>();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite database established.");

        String sql = "SELECT * FROM documents WHERE document_id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, documentID);
        resultSet = preparedStatement.executeQuery();

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
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    return documentsList;
}


}



