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
        System.out.println("£" + amount + " spent, new savings total: "+(this.initialIncome));
        System.out.println(" ");
    }

    public void removeItem(int position) {
        MonthlySaving findItem = spendList.get(position - 1);
        boolean exists = spendList.contains(findItem);
        if (exists) {
            spendList.remove(findItem);
            setInitialIncome(initialIncome+findItem.getSavings());
            System.out.println("£" + findItem.getSavings() + " removed, list adjusted");
        } else {
            System.out.println("Item at " + position + "does not exist, try again");
            // throws an error, not print this message??
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
        // calculate and show both running total of remaining savings pot
        // and how much spent overall
        for (int i = 0; i < spendList.size(); i++) {
            MonthlySaving savingsTotalObj = spendList.get(i);
            System.out.println("Entry " + (i + 1) + ": " + savingsTotalObj.getSavings());
        }
        System.out.println("Total savings: £" + initialIncome);
        System.out.println("Total amount spent: £" + runningTotalSpend());
    }

    public boolean addNote() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entries: ");
        showSavings();
        System.out.println("Enter item number to add a note to: ");
        int scInput = sc.nextInt();
        sc.nextLine();
        MonthlySaving addToSaving = spendList.get(scInput - 1);
        MonthlySaving emptySaving = new MonthlySaving(0);
        emptySaving.setItemInfo("");
        if (addToSaving.getItemInfo().equals(emptySaving.getItemInfo())) {
            System.out.println("Enter note to add to item: ");
            String itemNote = sc.nextLine();
            addToSaving.setItemInfo(itemNote);
            System.out.println("New saving info for item number " + spendList.get(scInput - 1) + ": "
                    + addToSaving.getSavings());
            return false; // item doesn't exist
        } else {
            System.out.println("Item info already exists. Rewrite? (Y)/(N)");
            boolean exitChoice = true;
            while(exitChoice){
                String choice = sc.nextLine().toUpperCase();
                if (choice.equals("Y")){
                    String itemNote = sc.nextLine();
                    addToSaving.setItemInfo(itemNote);
                    System.out.println("'"+itemNote + "' added to item "+scInput+".");
                    exitChoice = false;
                } else if (choice.equals("N")){
                    System.out.println("Item number "+scInput+" info: "+addToSaving.getItemInfo());
                    exitChoice = false;
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
}