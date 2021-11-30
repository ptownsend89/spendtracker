package com.company;
import java.util.ArrayList;
import java.util.Scanner;

public class SpendCounter {
    private double initialIncome;
    private ArrayList<MonthlySaving> spendList = new ArrayList<MonthlySaving>();

    public SpendCounter(double initialIncome) {
        this.initialIncome = initialIncome;
    }

    public void setInitialIncome(double initialIncome) {
        this.initialIncome = initialIncome;
    }

    public void addItem(double amount) {
        MonthlySaving newSaving = new MonthlySaving(amount);
        spendList.add(newSaving);
        setInitialIncome(initialIncome-amount);
        System.out.println("£" + amount + " spent, new savings total: £"+(this.initialIncome));
        System.out.println(" ");
    }

    public void removeItem(int position) {
        try {
            MonthlySaving findItem = spendList.get(position - 1);
            boolean exists = spendList.contains(findItem);
            if (exists) {
                spendList.remove(findItem);
                setInitialIncome(initialIncome + findItem.getSavings());
                System.out.println("£" + findItem.getSavings() + " removed, list adjusted");
            } else {
                System.out.println("Item at " + position + "does not exist, try again");
                // throws an error, not print this message??
            }
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Incorrect list number, try deleting again");
        }
    }

    public void showSavings() { // show all entries
        System.out.println(spendList.size() + " entries in the list.");
        System.out.println("Displaying entries: ");
        for (int i = 0; i < spendList.size(); i++) {
            MonthlySaving savingsDisplay = spendList.get(i);
            System.out.println("Item number " + (i + 1) + ": £" + savingsDisplay.getSavings());
        }
        System.out.println(" ");
    }

    public MonthlySaving findEntry(double savings) {
        // searches arrayList for a monthlySaving matching parameter
        // and returns it
        // ========================================================
        // WHAT ABOUT DUPLICATE SPENDS?? ==========================
        // ========================================================
        MonthlySaving findSaving = new MonthlySaving(savings);
        for (int i = 0; i < spendList.size(); i++) {
            if (spendList.get(i).equals(findSaving)) {
                return findSaving;
            }
        } return null;
    }

    public void showTotal() {
        // calculate and show running total of remaining savings pot
        // and how much spent overall
        for (int i = 0; i < spendList.size(); i++) {
            MonthlySaving savingsTotalObj = spendList.get(i);
            System.out.println("Entry " + (i + 1) + ": " + savingsTotalObj.toString());
        }
        System.out.println("Total savings: £" + initialIncome);
        System.out.println("Total amount spent: £" + runningTotalSpend());
    }

    public boolean addNote() {
        Scanner sc = new Scanner(System.in);
        String itemNote;
        System.out.println("Entries: ");
        showSavings();
        System.out.println("Enter item number to add a note to: ");
        int scInput = sc.nextInt();
        sc.nextLine();
        MonthlySaving itemAdd = spendList.get(scInput - 1);
        MonthlySaving emptySaving = new MonthlySaving(0);
        emptySaving.setItemInfo("");
        if (itemAdd.getItemInfo().equals(emptySaving.getItemInfo())) {
            System.out.println("Enter note to add to item: ");
            itemNote = sc.nextLine();
            itemAdd.setItemInfo(itemNote);
            System.out.println("New saving info for item number " + scInput + ": "
                    + itemAdd.getItemInfo());
            return false; // item doesn't exist - proceed with adding
        } else { // item info exists - give option to proceed with adding
            System.out.println("Item info "+itemAdd.getItemInfo()+" already exists. Rewrite? (Y)/(N)");
            boolean exit = true;
            while(exit){
                String choice = sc.nextLine().toUpperCase();
                if (choice.equals("Y")){
                    System.out.println("Add item note: ");
                    itemNote = sc.nextLine();
                    itemAdd.setItemInfo(itemNote);
                    System.out.println("'"+itemNote + "' added to item "+scInput+".");
                    exit = false;
                } else if (choice.equals("N")){
                    System.out.println("Item number "+scInput+" item info: "+itemAdd.getItemInfo());
                    mainMenu();
                    exit = false;
                }
            }
        }
        sc.close();
        return true;
    }

    public double runningTotalSpend(){
        double totalSpend = 0;
        for (int i = 0; i < spendList.size(); i++) {
            MonthlySaving savingsTotal = spendList.get(i);
            totalSpend += savingsTotal.getSavings(); //total SpendCounter this month
        } return totalSpend;
    }

    public void mainMenu(){
        System.out.println("Show the main menu? (Y/N)");
        Scanner sc = new Scanner(System.in);
        String input;
        boolean exit = true;
        while (exit){
            input = sc.nextLine().toUpperCase();
            if (input.equals("Y")){
                System.out.println(" \n =======================================================");
                System.out.println("----MAIN MENU----");
                System.out.println("To see the main menu, press (0)");
                System.out.println("To add a single spend, press (1)");
                System.out.println("To show individual spends this month, press (2)");
                System.out.println("To delete an entry, press (3)");
                System.out.println("To show total spent this month, press (4)");
                System.out.println("To search for a specific amount, press (5)");
                System.out.println("To add an item note, press (6)");
                System.out.println("To quit the app, press (7)");
                System.out.println("=======================================================  \n ");
                exit = false;
            } else if (input.equals("N")){
                exit = false;
            } else {
                System.out.println("Invalid input, try again");
            }
        }
    }
}