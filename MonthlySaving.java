package com.company;

public class MonthlySaving {
    private double savings;
    private String itemInfo;
    //savings class to access overall savings amount and detract
    //from whenever entry added

    public MonthlySaving(double savings) {
        this.savings = savings;
        this.itemInfo = ""; // initialise to "" so can compare empty fields
    }

    public String toString(){
        return this.savings + " - " + this.itemInfo;
    }

    public String getItemInfo() {
        return itemInfo;
    }
    public void setItemInfo(String itemInfo){
        this.itemInfo = itemInfo;
    }

    public double getSavings() {
        return savings;
    }
    public void setSavings(double savings) {
        this.savings = savings;
    }
}
