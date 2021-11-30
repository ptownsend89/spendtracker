package com.company;
import java.sql.*;
import java.sql.JDBCType;
import java.util.Scanner;
//------------------------------------------------------------------
// 1. sort runningTotal methods - necessary? currently returning incorrect values
// 2. add item info when calling addItem
// 3. link to MySQL database ==> sort both sides of connection
//------------------------------------------------------------------
public class Main {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

//
//        System.out.println("Welcome, expenditure app open.");
//        System.out.println("******************************\n");
//        System.out.println("Enter username: \n");
//        String userName = scan.nextLine();
//        System.out.println("Now enter password");
//        String pword = scan.nextLine();
//        UserProfile user = new UserProfile(userName, pword);
//        user.createUser(userName, pword);


        System.out.println("Please enter total income this month: ");
        double totalIncome = scan.nextDouble();
        SpendCounter spendCounter = new SpendCounter(totalIncome);
        System.out.println("Total income this month is: £" + totalIncome);
        System.out.println("");
        System.out.println("");
        boolean quit = false;
        while (!quit) {
            spendCounter.mainMenu();
            double input;
            int indexInput;
            int selection = scan.nextInt();
            if (selection > 6 || selection <= 0) {
                System.out.println("Invalid input, try again");
                System.out.println(" ");
            }
            switch (selection) {
                case 0: // show menu
                    spendCounter.mainMenu();
                case 1: // enter a spend
                    System.out.println("Enter amount: ");
                    input = scan.nextDouble();
                    spendCounter.addItem(input);
                    break;
                case 2: // show individual entries
                    //input=scan.nextLine();
                    spendCounter.showSavings(); // show all entries
                    break;
                case 3: // delete entry
                    System.out.println("Show all entries? Press (Y) for Yes, (N) for No");
                    boolean escape = false;
                    while (!escape) {
                        String option = scan.nextLine().toUpperCase();
                        switch (option) {
                            case "Y":
                                System.out.println("Entries: ");
                                spendCounter.showSavings();
                                System.out.println("Enter item number to remove:");
                                indexInput = scan.nextInt();
                                spendCounter.removeItem(indexInput);
                                escape = true;
                                break;
                            case "N":
                                System.out.println("Enter item number to remove:");
                                indexInput = scan.nextInt();
                                spendCounter.removeItem(indexInput);
                                escape = true;
                                break;
                        }
                    }
                case 4: // show total savings/SpendCounter
                    spendCounter.showTotal();
                    break;
                case 5: // find a specific entry
                    System.out.println("Enter amount to search: ");
                    double doubleInput = scan.nextDouble();
                    spendCounter.findEntry(doubleInput);
                    System.out.println("£"+doubleInput+" found.");
                    break;
                case 6: // add item note
                    spendCounter.addNote();
                    break;
                case 7: // exit
                    quit = true;
                    System.out.println("Goodbye");
                    break;
            }
        }
        scan.close();
//        SpendCounter testSpendCounter = new SpendCounter(2180);
//        testSpendCounter.addItem(200);
//        testSpendCounter.addItem(300);
//        testSpendCounter.addItem(100);
//        testSpendCounter.runningTotalSaved();
//        testSpendCounter.runningTotalSpend();

    }
}
