package com.antonsskafferi.projekt_dt142g;

public class Month {

    private final int monthNumber;
    private final int numberOfDays;

    public Month(int monthNumber, int numberOfDays) {
        this.monthNumber = monthNumber;
        this.numberOfDays = numberOfDays;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }
}
