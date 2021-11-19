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


        System.out.println("Welcome, expenditure app open.");
        System.out.println("******************************\n");
        System.out.println("Enter username: \n");
        String userName = scan.nextLine();
        System.out.println("Now enter password");
        String pword = scan.nextLine();
        UserProfile user = new UserProfile(userName, pword);
        user.createUser(userName, pword);
        System.out.println("Please enter total income this month: ");
        double totalIncome = scan.nextDouble();
        SpendCounter SpendCounter = new SpendCounter(totalIncome);
        System.out.println("Total income this month is: £" + totalIncome);
        System.out.println("");
        System.out.println("");

        boolean quit = false;
        while (!quit) {
            System.out.println(" ");
            System.out.println("----MAIN MENU----");
            System.out.println("To add a single spend, press (1)");
            System.out.println("To show individual SpendCounter this month, press (2)");
            System.out.println("To delete an entry, press (3)");
            System.out.println("To show total spent this month, press (4)");
            System.out.println("To search for a specific amount, press (5)");
            System.out.println("To add an item note, press (6)");
            System.out.println("To quit the app, press (7)");
            System.out.println(" ");
            double input;
            int indexInput;
            int selection = scan.nextInt();
            if (selection > 6 || selection <= 0) {
                System.out.println("Invalid input, try again");
                System.out.println(" ");
            }
            switch (selection) {
                case 1: // enter a spend
                    System.out.println("Enter amount: ");
                    input = scan.nextDouble();
                    SpendCounter.addItem(input);
                    break;
                case 2: // show individual entries
                    //input=scan.nextLine();
                    SpendCounter.showSavings(); // show all entries
                    break;
                case 3: // delete entry
                    System.out.println("Show all entries? Press (Y) for Yes, (N) for No");
                    boolean escape = false;
                    while (!escape) {
                        String option = scan.nextLine().toUpperCase();
                        switch (option) {
                            case "Y":
                                System.out.println("Entries: ");
                                SpendCounter.showSavings();
                                System.out.println("Enter item number to remove:");
                                indexInput = scan.nextInt();
                                SpendCounter.removeItem(indexInput);
                                escape = true;
                                break;
                            case "N":
                                System.out.println("Enter item number to remove:");
                                indexInput = scan.nextInt();
                                SpendCounter.removeItem(indexInput);
                                escape = true;
                                break;
                        }
                    }
                case 4: // show total savings/SpendCounter
                    SpendCounter.showTotal();
                    break;
                case 5: // find a specific entry
                    System.out.println("Enter amount to search: ");
                    double doubleInput = scan.nextDouble();
                    SpendCounter.findEntry(doubleInput);
                    System.out.println("£"+doubleInput+" found.");
                    break;
                case 6: // add item note
                    SpendCounter.addNote();
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
//    class MySQLCon{
//        Class.[INSERT HERE]("com.mysql.jdbc.Driver");
//        Connection con = DriverManager.getConnection("database-1.ccphuhpftsbi.us-east-2.rds.amazonaws.com",
//                "admin","PToWnHaNd1959");
//        Statement stmnt = con.createStatement();
//        ResultSet rs = stmnt.executeQuery("SELECT * FROM ")
//    }
//}
