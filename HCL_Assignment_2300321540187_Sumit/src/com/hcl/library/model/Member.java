package com.hcl.library.model;

import java.util.ArrayList;
import java.util.List;

public class Member {

    public static final int MAX_BORROW_LIMIT = 3;
    private static final String DEFAULT_EMAIL = "not-provided@library.local";

    private final int id;
    private String name;
    private String email;
    private final List<LibraryItem> borrowedItems = new ArrayList<>();

    public Member(int id, String name, String email) {
        this.id = id;
        this.name = validateName(name);
        this.email = validateEmail(email);
    }


    public Member(int id, String name) {
        this(id, name, DEFAULT_EMAIL);
    }

    // validation //

    private static String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        return name.trim();
    }

    private static String validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email must contain '@'");
        }
        return email.trim();
    }

    // borrowing //

    public boolean canBorrowMore() {
        return borrowedItems.size() < MAX_BORROW_LIMIT;
    }

    public void addBorrowedItem(LibraryItem item) {
        borrowedItems.add(item);
    }

    public boolean removeBorrowedItem(LibraryItem item) {
        return borrowedItems.remove(item);
    }

    public boolean hasBorrowed(LibraryItem item) {
        return borrowedItems.contains(item);
    }

    // here I use getter and setter validation which is encapsulation concept //

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = validateName(name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = validateEmail(email);
    }


    public List<LibraryItem> getBorrowedItems() {
        return List.copyOf(borrowedItems);
    }

    // equals and hashcode code here sir //

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Member)) {
            return false;
        }
        Member other = (Member) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("Member[").append(id).append(", ").append(name)
                .append(", ").append(email).append(", borrowed=");
        if (borrowedItems.isEmpty()) {
            text.append("none");
        } else {
            for (LibraryItem item : borrowedItems) {
                text.append(item.getId()).append(' ');
            }
        }
        return text.toString().trim() + "]";
    }
}