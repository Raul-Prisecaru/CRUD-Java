package com.raul.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LawDatabase {
    Scanner userSelection = new Scanner(System.in);
    public void createDatabase() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite database established.");

            statement = connection.createStatement();
            String CreateClients = "-- Clients table to store information about clients\n" +
                    "CREATE TABLE clients (\n" +
                    " client_id INTEGER PRIMARY KEY,\n" +
                    " client_name TEXT NOT NULL,\n" +
                    " client_address TEXT,\n" +
                    " client_phone TEXT,\n" +
                    " client_email TEXT);";

            String CreateCases = "CREATE TABLE cases (\n" +
                    " case_id INTEGER PRIMARY KEY,\n" +
                    " case_number TEXT UNIQUE,\n" +
                    " case_title TEXT NOT NULL,\n" +
                    " case_description TEXT,\n" +
                    " case_status TEXT,\n" +
                    " date_filed DATE,\n" +
                    " date_closed DATE,\n" +
                    " client_id INTEGER,\n" +
                    " FOREIGN KEY (client_id) REFERENCES clients(client_id) ON DELETE\n" +
                    "SET NULL\n" +
                    ");";
            String CreateDocuments = "-- Documents table to store information about legal documents\n" +
                    "CREATE TABLE documents (\n" +
                    " document_id INTEGER PRIMARY KEY,\n" +
                    " case_id INTEGER,\n" +
                    " document_name TEXT NOT NULL,\n" +
                    " document_type TEXT,\n" +
                    " document_path TEXT,\n" +
                    " FOREIGN KEY (case_id) REFERENCES cases(case_id) ON DELETE\n" +
                    "CASCADE);";

            String CreateImportantDates = "-- Important Dates table to store dates related to significant events in\n" +
                    "CREATE TABLE important_dates (\n" +
                    " date_id INTEGER PRIMARY KEY,\n" +
                    " case_id INTEGER,\n" +
                    " event_date DATE,\n" +
                    " event_description TEXT,\n" +
                    " FOREIGN KEY (case_id) REFERENCES cases(case_id) ON DELETE\n" +
                    "CASCADE\n" +
                    ");\n";

            String InsertClients = "-- Insert sample clients\n" +
                    "INSERT INTO clients (client_name, client_address, client_phone,\n" +
                    "client_email) \n" +
                    "VALUES \n" +
                    "('John Doe', '123 Main St, Anytown', '555-1234',\n" +
                    "'john.doe@example.com'),\n" +
                    "('Jane Smith', '456 Oak St, Anycity', '555-5678',\n" +
                    "'jane.smith@example.com');\n";

            String InsertCases = "-- Insert sample cases\n" +
                    "INSERT INTO cases (case_number, case_title, case_description,\n" +
                    "case_status, date_filed, date_closed, client_id) \n" +
                    "VALUES \n" +
                    "('CASE001', 'Trademark Infringement', 'Trademark infringement against\n" +
                    "XYZ Corp.', 'Open', '2023-01-15', NULL, 1),\n" +
                    "('CASE002', 'Patent Dispute', 'Patent dispute regarding new technology',\n" +
                    "'Closed', '2023-03-20', '2023-08-10', 2);\n";

            String InsertDocument = "-- Insert sample documents\n" +
                    "INSERT INTO documents (case_id, document_name, document_type,\n" +
                    "document_path) \n" +
                    "VALUES \n" +
                    "(1, 'Complaint', 'Legal', 'complaint.txt'),\n" +
                    "(1, 'Evidence', 'Legal', 'evidence.txt'),\n" +
                    "(2, 'Settlement Agreement', 'Legal', 'settlement_agreement.txt');\n";

            String InsertImportantDate = "-- Insert sample important dates\n" +
                    "INSERT INTO important_dates (case_id, event_date, event_description)\n" +
                    "VALUES \n" +
                    "(1, '2023-01-15', 'Date filed'),\n" +
                    "(1, '2023-02-10', 'Pre-trial conference'),\n" +
                    "(1, '2023-05-05', 'Court hearing'),\n" +
                    "(2, '2023-03-20', 'Date filed'),\n" +
                    "(2, '2023-06-15', 'Mediation session');";

            statement.execute(CreateClients);
            statement.execute(CreateCases);
            statement.execute(CreateDocuments);
            statement.execute(CreateImportantDates);
            statement.execute(InsertClients);
            statement.execute(InsertCases);
            statement.execute(InsertDocument);
            statement.execute(InsertImportantDate);

            System.out.println("Table created successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

}
















