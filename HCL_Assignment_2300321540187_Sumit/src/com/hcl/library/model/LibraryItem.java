package com.hcl.library.model;

import java.time.Year;
import java.util.Comparator;


public abstract class LibraryItem implements Borrowable, Comparable<LibraryItem> {

    public static final int MIN_YEAR = 1900;
    private static final int FIRST_ID = 1001;


    private static int nextId = FIRST_ID;


    public static final Comparator<LibraryItem> BY_YEAR_NEWEST_FIRST =
            Comparator.comparing(LibraryItem::getYear, Comparator.reverseOrder())
                    .thenComparing(LibraryItem::getTitle, String.CASE_INSENSITIVE_ORDER);

    private final int id;
    private String title;
    private int year;
    private boolean available = true;

    protected LibraryItem(String title, int year) {
        // validate first, so a bad item never uses up an id
        this.title = validateTitle(title);
        this.year = validateYear(year);
        this.id = nextId++;
    }



    private static String validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be blank");
        }
        return title.trim();
    }

    private static int validateYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < MIN_YEAR || year > currentYear) {
            throw new IllegalArgumentException(
                    "Year must be between " + MIN_YEAR + " and " + currentYear);
        }
        return year;
    }



    public abstract double lateFee(int daysLate);


    protected static int chargeableDays(int daysLate) {
        return Math.max(daysLate, 0);
    }



    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void borrow() {
        available = false;
    }

    @Override
    public void giveBack() {
        available = true;
    }



    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = validateTitle(title);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = validateYear(year);
    }



    @Override
    public int compareTo(LibraryItem other) {
        return title.compareToIgnoreCase(other.title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LibraryItem)) {
            return false;
        }
        LibraryItem other = (LibraryItem) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return id + ", " + title + ", " + year;
    }
}
