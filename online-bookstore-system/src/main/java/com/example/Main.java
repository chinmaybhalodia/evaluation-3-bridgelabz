package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookStore bookStore = new BookStore();
        Scanner inputReader = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Book Store. What action do you want to perfrom?");
            System.out.println("[1] Add User");
            System.out.println("[2] Add new Book");
            System.out.println("[3] Add books to stock");
            System.out.println("[4] Purchase a book");
            System.out.println("[5] Print books stock");
            System.out.print("Enter your choice (enter 0 to exit): ");
            int choice = inputReader.nextInt();
            inputReader.nextLine();

            switch (choice) {
                case 0:
                    inputReader.close();
                    return;

                case 1:
                    bookStore.addUser(inputReader);
                    break;

                case 2:
                    bookStore.addBook(inputReader);
                    break;

                case 3:
                    bookStore.addToStock(inputReader);
                    break;

                case 4:
                    bookStore.purchaseBook(inputReader);
                    break;

                case 5:
                    bookStore.printStock();
                    break;

                default:
                    break;
            }
        }
    }
}