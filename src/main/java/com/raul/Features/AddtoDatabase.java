package com.raul.Features;

import com.raul.Database.LawDatabase;

public class AddtoDatabase extends LawDatabase {
    //  Client
    int clientID;
    String clientName;
    String clientAddress;
    int clientPhoneNumber;
    String clientEmail;

    // Case
    int caseID;
    int caseString;
    String caseTitle;
    String caseDescription;
    String caseStatus;
    int dateFiled;
    int dateClosed;

    // Document
    int documentID;
    String documentName;
    String documentType;
    String documentPath;

    // Important Dates
    int dateID;
    int eventDate;
    String eventDescription;

    public void create() {

    }
}
