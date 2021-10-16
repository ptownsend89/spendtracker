package com.company;
import java.util.Scanner;
//------------------------------------------------------------------
// 1. fix option to display all entries before deleting a single one
//------------------------------------------------------------------
public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static Spendings spendList = new Spendings();

    public static void main(String[] args) {
        System.out.println("Welcome, expenditure app open.");
        System.out.println("******************************\n");
        System.out.println("Enter username: \n");
        String userName = scan.nextLine();
        System.out.println("Now enter password");
        String pword = scan.nextLine();
        UserInfo user = new UserInfo(userName, pword);
        user.createUser(userName, pword);
        System.out.println("Please enter total income this month: ");
        double totalIncome = scan.nextDouble();
        MonthlySavings savings = new MonthlySavings(totalIncome);
        System.out.println("Total income this month is: £" + totalIncome);
        System.out.println("");
        System.out.println("");

        boolean quit = false;
        while(!quit) {
            System.out.println(" ");
            System.out.println("----MAIN MENU----");
            System.out.println("To add a single spend, press (1)");
            System.out.println("To show individual spendings this month, press (2)");
            System.out.println("To delete an entry, press (3)");
            System.out.println("To show total spent this month, press (4)");
            System.out.println("To search for a specific amount, press (5)");
            System.out.println("To quit the app, press (6)");
            System.out.println(" ");
            double input;
            int indexInput;
            int selection = scan.nextInt();
            if (selection >6 || selection <=0){
                System.out.println("Invalid input, try again");
                System.out.println(" ");
            }
            switch (selection) {
                case 1: // enter a spend
                    System.out.println("Enter amount: ");
                    input = scan.nextDouble();
                    spendList.addItem(input);
                    savings.minusSavings(input);
                    break;
                case 2: // show individual entries
                    //input=scan.nextLine();
                    spendList.showSavings(); // show all entries
                    break;
                case 3: // deleted entries
                    System.out.println("Show all entries? Press (Y) for Yes, (N) for No");
                    boolean escape = false;
                    while (!escape) {
                        String option = scan.nextLine().toUpperCase();
                        switch (option) {
                            case "Y":
                            System.out.println("Entries: ");
                            spendList.showSavings();
                            System.out.println("Enter item number to remove:");
                            indexInput = scan.nextInt();
                            spendList.removeItem(indexInput);
                            System.out.println("Item number " + indexInput + " successfully deleted," +
                                    "savings increased by £" + spendList.findEntry(indexInput));
                            escape = true;
                            break;
                            case "N":
                            System.out.println("Enter item number to remove:");
                            indexInput = scan.nextInt();
                            spendList.removeItem(indexInput+1);
                            System.out.println("Item number " + indexInput+1 + " successfully deleted," +
                                    "savings increased by £" + spendList.findEntry(indexInput+1));
                            escape = true;
                            break;
                        }
            }
                case 4: // show total savings/spendings
                    spendList.showTotal();
                    System.out.println("Total savings: £" + savings.getSavings());
                    break;
                case 5: // find a specific entry
                    System.out.println("Enter amount to search: ");
                    double doubleInput = scan.nextDouble();
                    spendList.findEntry(doubleInput);
                    break;
                case 6: // exit
                    quit = true;
                    System.out.println("Goodbye");
                    break;
            }
        }
        scan.close();
    }
}
