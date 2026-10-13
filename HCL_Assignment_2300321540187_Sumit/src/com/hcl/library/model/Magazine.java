package com.hcl.library.model;

public class Magazine extends LibraryItem {

    private static final double LATE_FEE_PER_DAY = 1.0;

    private final int issueNumber;

    public Magazine(String title, int year, int issueNumber) {
        super(title, year);
        if (issueNumber <= 0) {
            throw new IllegalArgumentException("Issue number must be greater than 0");
        }
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    @Override
    public double lateFee(int daysLate) {
        return chargeableDays(daysLate) * LATE_FEE_PER_DAY;
    }

    @Override
    public String toString() {
        return "Magazine[" + super.toString() + ", issue " + issueNumber + "]";
    }
}