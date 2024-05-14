package com.raul;
import com.raul.Database.LawDatabase;
import com.raul.Features.Clients;

//import java.sql.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner Input = new Scanner(System.in);
        LawDatabase database = new LawDatabase();
        Clients createValues = new Clients();
        System.out.println("Welcome to St Mary's Law Firm");
        System.out.println("What would you like to do?");
        System.out.println("[0] - Create Database");
        System.out.println("[1] - CREATE");
        System.out.println("[2] - RECEIVE");
        System.out.println("[3] - UPDATE");
        System.out.println("[4] - DELETE");
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
                System.out.println("You have selected: CREATE");
                System.out.println("Enter ClientID: ");
                int clientIDInput = Input.nextInt();
                createValues.setClientID(clientIDInput);

                System.out.println("Enter Client Name: ");
                String clientNameInput = Input.next();
                createValues.setClientName(clientNameInput);

                System.out.println("Enter Client Email: ");
                String clientEmailInput = Input.next();
                createValues.setClientEmail(clientEmailInput);

                System.out.println("Enter Client Address: ");
                String clientAddressInput = Input.next();
                createValues.setClientAddress(clientAddressInput);

                System.out.println("Enter Client PhoneNumber: ");
                String clientPhoneInput = Input.next();
                createValues.setClientPhoneNumber(clientPhoneInput);
                createValues.create(createValues.getClientID(), createValues.getClientName(), createValues.getClientAddress(), createValues.getClientPhoneNumber(), createValues.getClientEmail());
                break;


            case 2:
                System.out.println("You have selected: RECEIVE");
                break;

            case 3:
                System.out.println("You have selected: UPDATE");
                break;

            case 4:
                System.out.println("You have selected: DELETE");
                break;
        }

    }

}








