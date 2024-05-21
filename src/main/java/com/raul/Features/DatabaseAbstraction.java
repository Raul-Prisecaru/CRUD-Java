package com.raul.Features;

import java.util.List;

// Template
abstract class ClientDatabase {
    public abstract void Create(String clientName, String clientAddress, String clientPhoneNumber, String clientEmail);
    public abstract List<Clients> Retrieve();
    public abstract void Update(int clientID, String clientName, String clientAddress, String clientPhoneNumber, String clientEmail);
    public abstract void Delete(int clientID);
    public abstract List<Clients> retrieveByID(int clientID);
}

abstract class CaseDatabase {
    private int caseID;
    private String caseNumber;
    private String caseTitle;
    private String caseDescription;
    private String caseStatus;
    private int dateFiled;
    private int dateClosed;
    private int clientID;

    public abstract void Create(String caseNumber, String caseTitle, String caseDescription, String caseStatus, int dateFiled, int dateClosed, int clientID);
    public abstract List<Cases> Retrieve();
    public abstract void Update(int caseID, String caseNumber, String caseTitle, String caseDescription, String caseStatus, int dateFiled, int dateClosed, int clientID);
    public abstract void Delete(int caseID);
    public abstract List<Cases> RetrieveByID(int caseID);
}

abstract class DocumentDatabase {
    public abstract void Create(int caseID, String documentName, String documentType, String documentPath);
    public abstract List<Documents> Retrieve();
    public abstract void Update(int documentID, int caseID, String documentName, String documentType, String documentPath);
    public abstract void Delete(int documentID);
    public abstract List<Documents> RetrieveByID(int documentID);
}

abstract class DateDatabase {
    public abstract void Create(int caseID, int eventDate, String eventDescription);
    public abstract List<ImportantDates> Retrieve();
    public abstract void Update(int dateID, int caseID, int eventDate, String eventDescription);
    public abstract void Delete(int dateID);
    public abstract List<ImportantDates> RetrieveByID(int dateID);
}
