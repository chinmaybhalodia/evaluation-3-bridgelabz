package com.example;

import com.validator.*;
import com.exceptions.*;

import java.util.HashMap;
import java.util.Scanner;

public class BookListing {
    public void addBook(Scanner inputReader, HashMap<String, Integer> stock, HashMap<String, Book> books) {
        System.out.print("Enter book title: ");
        String title;
        while (true) {
            title = inputReader.nextLine();
            try {
                Validator.validateName(title);
                break;
            } catch (InvalidNameException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter book title: ");
            }
        }

        System.out.print("Enter author name: ");
        String author;
        while (true) {
            author = inputReader.nextLine();
            try {
                Validator.validateName(author);
                break;
            } catch (InvalidNameException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter author name: ");
            }
        }

        System.out.print("Enter ISBN number: ");
        String ISBN;
        while (true) {
            ISBN = inputReader.nextLine();
            try {
                if (books.containsKey(ISBN)) {
                    throw new InvalidISBNException(
                            "Book with same ISBN already exists. Try updating the stock instead.");
                } else {
                    Validator.validateISBN(ISBN);
                }
                break;
            } catch (InvalidISBNException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter ISBN number: ");
            }
        }

        System.out.print("Enter book price: ");
        int price = inputReader.nextInt();
        inputReader.nextLine();

        System.out.print("Enter number of books: ");
        int num_of_books = inputReader.nextInt();
        inputReader.nextLine();

        Book book = new Book(title, author, ISBN, price);
        books.put(ISBN, book);
        stock.put(ISBN, num_of_books);

        System.out.println(num_of_books + " books having ISBN " + ISBN + " added successfully!");
    }

    public void addToStock(Scanner inputReader, HashMap<String, Integer> stock) {
        System.out.print("Enter book ISBN: ");
        String ISBN;
        while (true) {
            ISBN = inputReader.nextLine();
            try {
                if (!stock.containsKey(ISBN)) {
                    throw new InvalidISBNException("No book found with entered ISBN");
                } else {
                    Validator.validateISBN(ISBN);
                }
                break;
            } catch (InvalidISBNException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter book ISBN: ");
            }
        }
        int num_of_books = stock.get(ISBN);

        System.out.println("Enter number of books to add: ");
        int change;
        while (true) {
            change = inputReader.nextInt();
            inputReader.nextLine();
            try {
                if (change < 0) {
                    throw new InvalidStockException("Adding stock cannot be negative");
                } else {
                    num_of_books += change;
                    break;
                }
            } catch (InvalidStockException exception) {
                System.out.println(exception.getMessage());
                System.out.println("Enter number of books to add: ");
            }
        }

        stock.put(ISBN, num_of_books);
        System.out
                .println(change + " books having ISBN " + ISBN + " added to stock. Current stock is: " + num_of_books);
    }
}
