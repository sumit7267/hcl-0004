package com.hcl.library;

import java.util.ArrayList;
import java.util.Scanner;

class Book {
    int id;
    String title;
    boolean isAvailable = true;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Title: " + title + " | Available: " + (isAvailable ? "Yes" : "No");
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- LIBRARY MENU ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    books.add(new Book(id, title));
                    System.out.println("Book added successfully!");
                    break;

                case 2:
                    System.out.println("\n--- BOOK LIST ---");
                    if (books.isEmpty()) {
                        System.out.println("No books in library.");
                    } else {
                        for (Book b : books) {
                            System.out.println(b);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Book ID to borrow: ");
                    int borrowId = scanner.nextInt();
                    boolean foundBorrow = false;
                    for (Book b : books) {
                        if (b.id == borrowId) {
                            foundBorrow = true;
                            if (b.isAvailable) {
                                b.isAvailable = false;
                                System.out.println("Book borrowed successfully!");
                            } else {
                                System.out.println("Sorry, book is already issued!");
                            }
                        }
                    }
                    if (!foundBorrow) {
                        System.out.println("Book not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = scanner.nextInt();
                    boolean foundReturn = false;
                    for (Book b : books) {
                        if (b.id == returnId) {
                            foundReturn = true;
                            if (!b.isAvailable) {
                                b.isAvailable = true;
                                System.out.println("Book returned successfully!");
                            } else {
                                System.out.println("This book was not issued!");
                            }
                        }
                    }
                    if (!foundReturn) {
                        System.out.println("Book not found!");
                    }
                    break;

                case 0:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 0);

        scanner.close();
    }
}