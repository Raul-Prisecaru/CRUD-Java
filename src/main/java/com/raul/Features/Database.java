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
    public abstract void Create(String caseNumber, String caseTitle, String caseDescription, String caseStatus, int dateFiled, int dateClosed, int clientID);
    public abstract List<Cases> Retrieve();
    public abstract void Update(int caseID, String caseNumber, String caseTitle, String caseDescription, String caseStatus, int dateFiled, int dateClosed, int clientID);
    public abstract void Delete(int caseID);
    public abstract List<Cases> RetrieveByID(int caseID);
}

abstract class DocumentDatabase {
    public abstract void Create();
    public abstract void Retrieve();
    public abstract void Update();
    public abstract void Delete();
}

abstract class DateDatabase {
    public abstract void Create();
    public abstract void Retrieve();
    public abstract void Update();
    public abstract void Delete();
}
