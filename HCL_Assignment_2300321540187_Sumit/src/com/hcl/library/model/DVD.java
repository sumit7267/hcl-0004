package com.hcl.library.model;

public class DVD extends LibraryItem {

    private static final double LATE_FEE_PER_DAY = 5.0;

    private final int durationMinutes;

    public DVD(String title, int year, int durationMinutes) {
        super(title, year);
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0 minutes");
        }
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public double lateFee(int daysLate) {
        return chargeableDays(daysLate) * LATE_FEE_PER_DAY;
    }

    @Override
    public String toString() {
        return "DVD[" + super.toString() + ", " + durationMinutes + " min]";
    }
}