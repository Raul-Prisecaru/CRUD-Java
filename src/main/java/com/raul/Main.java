package com.raul;
import com.raul.Database.LawDatabase;
import com.raul.Features.Cases;
import com.raul.Features.Clients;
import com.raul.Features.Documents;
import com.raul.Features.ImportantDates;
import com.raul.GUI.MainMenu;

//import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner Input = new Scanner(System.in);
        LawDatabase database = new LawDatabase();
        Clients clients = new Clients();
        Cases cases = new Cases();
        Documents documents = new Documents();
        ImportantDates importantDates = new ImportantDates();
        JFrame frame = new JFrame("My First Swing Application");

        frame.setSize(400, 300);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainMenu mainMenu = new MainMenu();
        frame.getContentPane().add(mainMenu);

        frame.setVisible(true);

        System.out.println("Welcome to St Mary's Law Firm");
        System.out.println("What would you like to do?");
        System.out.println("[0] - Setup Database");
        System.out.println("[1] - Add Information");
        System.out.println("[2] - Retrieve Information");
        System.out.println("[3] - Update Information");
        System.out.println("[4] - Delete Information");
        System.out.println("What would you like to do?");
        int userOption = Input.nextInt();
        switch (userOption) {
            case 0:
                try {
                    database.createDatabase();
                    break;
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            case 1:
                int Tables = Input.nextInt();
                System.out.println("What Table do you want to add to?");
                System.out.println("[1] - Clients");
                System.out.println("[2] - Cases");
                System.out.println("[3] - Documents");
                System.out.println("[4] - Important Dates");

                switch (Tables) {
                    case 1:
                    System.out.println("You have selected: Clients");
                    System.out.println("Enter ClientID: ");
                    int clientIDInput = Input.nextInt();
                    clients.setClientID(clientIDInput);

                    System.out.println("Enter Client Name: ");
                    String clientNameInput = Input.next();
                    clients.setClientName(clientNameInput);

                    System.out.println("Enter Client Email: ");
                    String clientEmailInput = Input.next();
                    clients.setClientEmail(clientEmailInput);

                    System.out.println("Enter Client Address: ");
                    String clientAddressInput = Input.next();
                    clients.setClientAddress(clientAddressInput);

                    System.out.println("Enter Client PhoneNumber: ");
                    String clientPhoneInput = Input.next();
                    clients.setClientPhoneNumber(clientPhoneInput);
                    clients.create(clients.getClientID(), clients.getClientName(), clients.getClientAddress(), clients.getClientPhoneNumber(), clients.getClientEmail());
                    break;

                    case 2:
                        System.out.println("You have selected: Cases");
                        System.out.println("Enter CaseID: ");
                        int caseIDCreate = Input.nextInt();
                        cases.setCaseID(caseIDCreate);

                        System.out.println("Enter Case Number: ");
                        String caseNumberCreate = Input.next();
                        cases.setcaseNumber("CASE" + caseNumberCreate);

                        System.out.println("Enter Case Title: ");
                        String caseTitleCreate = Input.next();
                        cases.setCaseTitle(caseTitleCreate);

                        System.out.println("Enter Case Description: ");
                        String caseDescriptionCreate = Input.next();
                        cases.setCaseDescription(caseDescriptionCreate);

                        System.out.println("Enter Case Status: ");
                        String caseStatusCreate = Input.next();
                        cases.setCaseStatus(caseStatusCreate);

                        System.out.println("Enter Date Filed: ");
                        int dateFiledCreate = Input.nextInt();
                        cases.setDateFiled(dateFiledCreate);

                        System.out.println("Enter Date Closed: ");
                        int dateClosedCreate = Input.nextInt();
                        cases.setDateClosed(dateClosedCreate);

                        System.out.println("Enter Client ID: ");
                        int clientID = Input.nextInt();
                        cases.setClientID(clientID);

                        cases.create(cases.getCaseID(), cases.getcaseNumberr(), cases.getCaseTitle(), cases.getCaseDescription(), cases.getCaseStatus(), cases.getDateFiled(), cases.getDateClosed(), cases.getClientID());
                        break;

                    case 3:
                        System.out.println("You have selected: Documents");
                        System.out.println("Enter Document ID: ");
                        int documentIDCreate = Input.nextInt();
                        documents.setDocumentID(documentIDCreate);

                        System.out.println("Enter Case ID: ");
                        int caseIDCreateForeign = Input.nextInt();
                        documents.setCaseID(caseIDCreateForeign);

                        System.out.println("Enter Document Name: ");
                        String documentNameCreate = Input.next();
                        documents.setDocumentName(documentNameCreate);

                        System.out.println("Enter Document Type: ");
                        String documentTypeCreate = Input.next();
                        documents.setDocumentType(documentTypeCreate);

                        System.out.println("Enter Document Path: ");
                        String documentPathCreate = Input.next();
                        documents.setDocumentPath(documentPathCreate);

                        documents.create(documents.getDocumentID(), documents.getCaseID(), documents.getDocumentName(), documents.getDocumentType(), documents.getDocumentPath());

                        break;

                    case 4:
                        System.out.println("You have selected: Important Dates");
                        System.out.println("Enter Date ID: ");
                        int dateIDCreate = Input.nextInt();
                        importantDates.setDateID(dateIDCreate);

                        System.out.println("Enter Case ID: ");
                        int caseIDCreateForeignDate = Input.nextInt();
                        importantDates.setCaseID(caseIDCreateForeignDate);

                        System.out.println("Enter Event Date: ");
                        int eventDateCreate = Input.nextInt();
                        importantDates.setEventDate(eventDateCreate);

                        System.out.println("Enter Event Description: ");
                        String eventDescriptionCreate = Input.next();
                        importantDates.setEventDescription(eventDescriptionCreate);

                        importantDates.create(importantDates.getDateID(), importantDates.getCaseID(), importantDates.getEventDate(), importantDates.getEventDescription());

                        break;
                }

                break;
            case 2:
                int RetrieveTables = Input.nextInt();
                System.out.println("What Table do you want to view information?");
                System.out.println("[1] - Clients");
                System.out.println("[2] - Cases");
                System.out.println("[3] - Documents");
                System.out.println("[4] - Important Dates");

                switch (RetrieveTables) {
                    case 1:
                        clients.retrieve();
                        break;

                    case 2:
                        cases.retrieve();
                        break;

                    case 3:
                        documents.retrieve();
                        break;

                    case 4:
                        importantDates.retrieve();
                        break;
                }

                break;

            case 3:
                int UpdateTables = Input.nextInt();
                System.out.println("What Table do you want to Update to?");
                System.out.println("[1] - Clients");
                System.out.println("[2] - Cases");
                System.out.println("[3] - Documents");
                System.out.println("[4] - Important Dates");

                switch (UpdateTables) {
                    case 1:
                    System.out.println("You have selected: Clients");
                    System.out.println("Enter ClientID to Update: ");
                    int clientIDUpdate = Input.nextInt();
                    clients.setClientID(clientIDUpdate);

                    System.out.println("Enter New Client Name: ");
                    String clientNameUpdate = Input.next();
                    clients.setClientName(clientNameUpdate);

                    System.out.println("Enter New Client Email: ");
                    String clientEmailUpdate = Input.next();
                    clients.setClientEmail(clientEmailUpdate);

                    System.out.println("Enter New Client Address: ");
                    String clientAddressUpdate = Input.next();
                    clients.setClientAddress(clientAddressUpdate);

                    System.out.println("Enter New Client PhoneNumber: ");
                    String clientPhoneUpdate = Input.next();
                    clients.setClientPhoneNumber(clientPhoneUpdate);
                    clients.update(clients.getClientID(), clients.getClientName(), clients.getClientAddress(), clients.getClientPhoneNumber(), clients.getClientEmail());
                    break;

                    case 2:
                        System.out.println("You have selected: Cases");
                        System.out.println("Enter CaseID to Update: ");
                        int caseIDUpdate = Input.nextInt();
                        cases.setCaseID(caseIDUpdate);

                        System.out.println("Enter New Case Number: ");
                        String caseNumberUpdate = Input.next();
                        cases.setcaseNumber("CASE" + caseNumberUpdate);

                        System.out.println("Enter New Case Title: ");
                        String caseTitleUpdate = Input.next();
                        cases.setCaseTitle(caseTitleUpdate);

                        System.out.println("Enter New Case Description: ");
                        String caseDescriptionUpdate = Input.next();
                        cases.setCaseDescription(caseDescriptionUpdate);

                        System.out.println("Enter New Case Status: ");
                        String caseStatusUpdate = Input.next();
                        cases.setCaseStatus(caseStatusUpdate);

                        System.out.println("Enter New Date Filed: ");
                        int dateFiledUpdate = Input.nextInt();
                        cases.setDateFiled(dateFiledUpdate);

                        System.out.println("Enter New Date Closed: ");
                        int dateClosedUpdate = Input.nextInt();
                        cases.setDateClosed(dateClosedUpdate);

                        System.out.println("Enter New Client ID: ");
                        int clientIDUpdateForeign = Input.nextInt();
                        cases.setClientID(clientIDUpdateForeign);

                        cases.update(cases.getCaseID(), cases.getcaseNumberr(), cases.getCaseTitle(), cases.getCaseDescription(), cases.getCaseStatus(), cases.getDateFiled(), cases.getDateClosed(), cases.getClientID());
                        break;

                    case 3:
                        System.out.println("You have selected: Documents");
                        System.out.println("Enter Document ID to Update: ");
                        int documentIDUpdate = Input.nextInt();
                        documents.setDocumentID(documentIDUpdate);

                        System.out.println("Enter New Case ID: ");
                        int caseIDUpdateForeign = Input.nextInt();
                        documents.setCaseID(caseIDUpdateForeign);

                        System.out.println("Enter New Document Name: ");
                        String documentNameUpdate = Input.next();
                        documents.setDocumentName(documentNameUpdate);

                        System.out.println("Enter New Document Type: ");
                        String documentTypeUpdate = Input.next();
                        documents.setDocumentType(documentTypeUpdate);

                        System.out.println("Enter New Document Path: ");
                        String documentPathUpdate = Input.next();
                        documents.setDocumentPath(documentPathUpdate);

                        documents.update(documents.getDocumentID(), documents.getCaseID(), documents.getDocumentName(), documents.getDocumentType(), documents.getDocumentPath());

                        break;

                    case 4:
                        System.out.println("You have selected: Important Dates");
                        System.out.println("Enter Date ID to Update: ");
                        int dateIDUpdate = Input.nextInt();
                        importantDates.setDateID(dateIDUpdate);

                        System.out.println("Enter New Case ID: ");
                        int caseIDUpdateForeignDate = Input.nextInt();
                        importantDates.setCaseID(caseIDUpdateForeignDate);

                        System.out.println("Enter New Event Date: ");
                        int eventDateUpdate = Input.nextInt();
                        importantDates.setEventDate(eventDateUpdate);

                        System.out.println("Enter New Event Description: ");
                        String eventDescriptionUpdate = Input.next();
                        importantDates.setEventDescription(eventDescriptionUpdate);

                        importantDates.update(importantDates.getDateID(), importantDates.getCaseID(), importantDates.getEventDate(), importantDates.getEventDescription());

                        break;
                }

                break;
            case 4:
                int DeleteTables = Input.nextInt();
                System.out.println("What Table do you want to Delete from?");
                System.out.println("[1] - Clients");
                System.out.println("[2] - Cases");
                System.out.println("[3] - Documents");
                System.out.println("[4] - Important Dates");

                switch (DeleteTables) {
                    case 1:
                        System.out.println("Select ClientID to delete Record: ");
                        int clientsIDDelete = Input.nextInt();
                        clients.delete(clientsIDDelete);
                        break;

                    case 2:
                        System.out.println("Select Case ID to delete Record: ");
                        int casesIDDelete = Input.nextInt();
                        cases.delete(casesIDDelete);
                        break;

                    case 3:
                        System.out.println("Select Document ID to delete Record:");
                        int documentIDDelete = Input.nextInt();
                        documents.delete(documentIDDelete);

                    case 4:
                        System.out.println("Select Date ID to delete Record:");
                        int dateIDDelete = Input.nextInt();
                        importantDates.delete(dateIDDelete);
                }
        }

    }

}








