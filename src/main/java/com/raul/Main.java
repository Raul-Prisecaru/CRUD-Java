package com.raul;
import com.raul.Database.LawDatabase;

import java.sql.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner Input = new Scanner(System.in);
        LawDatabase database = new LawDatabase();
        System.out.println("Welcome to St Mary's Law Firm");
        System.out.println("What would you like to do?");
        System.out.println("[1] - CREATE");
        System.out.println("[2] - RECEIVE");
        System.out.println("[3] - UPDATE");

        System.out.println("[4] - DELETE");
        System.out.println("What would you like to do?");
        int userOption = Input.nextInt();
        switch (userOption) {
            case 1:
                System.out.println("You have selected: CREATE");
                database.createEntries(1);

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


