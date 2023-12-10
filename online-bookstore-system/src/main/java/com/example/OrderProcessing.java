package com.example;

import java.util.HashMap;
import java.util.Scanner;

import com.exceptions.*;
import com.validator.*;

public class OrderProcessing {
    public void processOrder(Scanner inputReader, HashMap<String, Integer> stock) {
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

        System.out.print("Enter number of books to purchase: ");
        int change;
        while (true) {
            change = inputReader.nextInt();
            inputReader.nextLine();
            try {
                if (change <= 0) {
                    throw new InvalidStockException("Number of books cannot be negative.");
                } else if (change > num_of_books) {
                    throw new InvalidStockException("Purchase count exceeds book stock");
                } else {
                    num_of_books -= change;
                    break;
                }
            } catch (InvalidStockException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter number of books to purchase: ");
            }
        }

        stock.put(ISBN, num_of_books);
        System.out.println("Book with ISBN " + ISBN + " purchased. Current stock is: " + num_of_books);
    }
}
