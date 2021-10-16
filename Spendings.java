package com.company;
import java.util.ArrayList;

public class Spendings {
    private ArrayList<Double> spendList = new ArrayList<Double>();

    public void addItem(double amount){
        spendList.add(amount);
        System.out.println("£" + amount + " spent, removed from total savings.");
        System.out.println(" ");
    }
    public void removeItem(int position){
        spendList.remove(position-1);
    }
    public void showSavings(){ // show all entries
        System.out.println(spendList.size() + " entries in the list.");
        System.out.println("Displaying entries: ");
        for (int i = 0; i<spendList.size(); i++){
            System.out.println("Item number " + (i+1) + ": £" + spendList.get(i));
        }
        System.out.println(" ");
    }
    public Double findEntry(double searchAmount){
        //boolean exists = spendList.contains(searchItem);
        int position = spendList.indexOf(searchAmount); // searches list for the index of an item matching
                                                        // searchItem and returns. if -1, doesn't exist in list
        if (position >= 0){
            System.out.println("£"+searchAmount+" spend found at position: "+(position+1));
            return spendList.get(position);
        }
        System.out.println("Unable to find £"+ searchAmount);
        return null; // null returned only if unable to find position/any item from indexOf() call
    }
    public void showTotal(){
        int total = 0;
        for (int i=0; i< spendList.size(); i++){  // loop through and add up all entries in the list
            total += spendList.get(i);
        }
        System.out.println("Total amount spent: £" + total);
    }
}
