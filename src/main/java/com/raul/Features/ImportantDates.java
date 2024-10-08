package com.raul.Features;

import com.raul.Features.DateDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImportantDates extends DateDatabase {

    // Important Dates
    private int dateID;
    private int caseID;
    private String eventDate;
    private String eventDescription;

    // Getter and Setter for dateID
    public int getDateID() {
        return dateID;
    }

    public void setDateID(int dateID) {
        this.dateID = dateID;
    }

    // Getter and Setter for caseID
    public int getCaseID() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }

    // Getter and Setter for eventDate
    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    // Getter and Setter for eventDescription
    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Override
    public void Create(int caseID, String eventDate, String eventDescription) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite database established.");

            String sql = "INSERT INTO important_dates (case_id, event_date, event_description) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, caseID);
            preparedStatement.setString(2, eventDate);
            preparedStatement.setString(3, eventDescription);
            preparedStatement.executeUpdate();
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
    public List<ImportantDates> Retrieve() {
        List<ImportantDates> dateList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite database established.");

            statement = connection.createStatement();
            String sql = "SELECT * FROM important_dates";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                ImportantDates datesObject = new ImportantDates();

                datesObject.setDateID(resultSet.getInt("date_id"));
                datesObject.setCaseID(resultSet.getInt("case_id"));
                datesObject.setEventDate(resultSet.getString("event_date"));
                datesObject.setEventDescription(resultSet.getString("event_description"));

                dateList.add(datesObject);
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
        return dateList;
    }

    @Override
    public void Update(int dateID, int caseID, String eventDate, String eventDescription) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite database established.");

        String sql = "UPDATE important_dates " +
                     "SET case_id = ?, event_date = ?, event_description = ?" +
                     "WHERE date_id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, caseID);
        preparedStatement.setString(2, eventDate);
        preparedStatement.setString(3, eventDescription);
        preparedStatement.setInt(4, dateID);
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
public void Delete(int dateID) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite database established.");

        String sql = "DELETE FROM important_dates WHERE date_id = ?" ;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, dateID);
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
public List<ImportantDates> Retrieve(int dateID) {
    List<ImportantDates> dateList = new ArrayList<>();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite database established.");

        String sql = "SELECT * FROM important_dates WHERE date_id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, dateID);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
                ImportantDates datesObject = new ImportantDates();

                datesObject.setDateID(resultSet.getInt("date_id"));
                datesObject.setCaseID(resultSet.getInt("case_id"));
                datesObject.setEventDate(resultSet.getString("event_date"));
                datesObject.setEventDescription(resultSet.getString("event_description"));

                dateList.add(datesObject);
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
    return dateList;
}

public boolean dateIDExists(int dateID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean exists = false;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/java/com/raul/Database/LawDatabase.db";
            connection = DriverManager.getConnection(url);
            String sql = "SELECT 1 FROM important_dates WHERE date_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, dateID);
            resultSet = preparedStatement.executeQuery();
            exists = resultSet.next();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exists;
    }


}