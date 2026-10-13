package com.hcl.library.model;

import java.util.HashSet;
import java.util.Set;

public class Book extends LibraryItem {

    private static final double LATE_FEE_PER_DAY = 2.0;


    private static final Set<String> usedIsbns = new HashSet<>();

    private final String author;
    private final String isbn;

    public Book(String title, int year, String author, String isbn) {
        super(title, year);
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author must not be blank");
        }
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN must not be blank");
        }
        String cleanIsbn = isbn.trim();
        if (!usedIsbns.add(cleanIsbn)) {
            throw new IllegalArgumentException("A book with ISBN " + cleanIsbn + " already exists");
        }
        this.author = author.trim();
        this.isbn = cleanIsbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public double lateFee(int daysLate) {
        return chargeableDays(daysLate) * LATE_FEE_PER_DAY;
    }

    @Override
    public String toString() {
        return "Book[" + super.toString() + ", " + author + "]";
    }
}
