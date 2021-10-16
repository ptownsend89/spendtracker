package com.company;

public class MonthlySavings {
    private double savings;
    //savings class to access overall savings amount and detract
    //from whenever entry added

    public MonthlySavings(double savings) {
        this.savings = savings;
    }
    public double getSavings() {
        return savings;
    }
    public void setSavings(double savings) {
        this.savings = savings;
    }
    public void minusSavings(double minusSavings){
        this.savings -= minusSavings;
    }
}
