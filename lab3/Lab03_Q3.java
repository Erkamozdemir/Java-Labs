/**
 * This program is a simple library management system where users can log in, 
 * manage their library records, and log out.
 * 
 * @author Erkam Özdemir (22403374)
 * @version 16/10/2025 11:20 
 */

package lab3;

import java.util.Random;
import java.util.Scanner;

public class Lab03_Q3 {
    public static void main(String[] args) {
        // Set up a scanner

        Scanner input = new Scanner(System.in);

        // Get username
        System.out.print("Enter your username: ");
        String username = input.nextLine();
        String realUser = "librarian";
        String finalUsername = username.trim();
        // Define booklist and memberlist
        String memberList = "JohnSmith, EmilyDavis, ZeynepDemir, ";

        String bookList = "ISBN9781:JavaProgramming ISBN9782:DataStructures ";
        // Check username
        if (!finalUsername.equals(realUser)) {
            System.out.print("Username not found! Goodbye!");
        } else {
            System.out.print("Enter your password: ");
            // Get password
            String password = input.nextLine();
            String realPass = "books2024";
            String finalPassword = password.trim();
            // Check password
            if (finalPassword.equals(realPass)) {
                System.out.println("1- Add member");
                System.out.println("2- Delete member");
                System.out.println("3- Add book");
                System.out.println("4- Delete book");
                System.out.println("5- Logout");
                System.out.print("Select an operation:");
                // Get order
                String order = input.nextLine();
                if (order.equals("1")) {
                    System.out.println("-- Add Member --");
                    System.out.print("Enter member name: ");
                    String newName = input.nextLine().trim();
                    // Check if there is this member already in the list
                    if (memberList.contains(newName + ", ")) {
                        System.out.println("This member is already in your list!");
                    } else {
                        System.out.println("New member " + newName + " is added!");
                        // Add the new member
                        memberList += (newName + ", ");
                    }
                    System.out.println("Your members: (" + memberList + ")");
                } else if (order.equals("2")) {
                    System.out.println("-- Delete Member --");
                    System.out.println("Enter member name which you want to delete:");
                    String deleteName = input.nextLine().trim();
                    // Check the list for the name
                    if (memberList.contains(deleteName + ", ")) {
                        System.out.println(deleteName + " is deleted successfully from members!");
                        // Delete the name using 'replace'
                        memberList = memberList.replace(deleteName + ", ", "");
                    } else {
                        System.out.println("You don't have any member whose name is " + deleteName + "!");
                    }
                    System.out.println("Your members: (" + memberList + ")");
                } else if (order.equals("3")) {
                    System.out.println("-- Add Book --");
                    System.out.print("Enter book name: ");
                    String bookName = input.nextLine();
                    // Generate random isbn
                    Random random = new Random();
                    // Format the isbn so it is always 4 digits
                    String randomIsbn = String.format("%04d", random.nextInt(10000));
                    // Check if there is a same isbn
                    if (bookList.contains("ISBN" + randomIsbn + ":")) {
                        System.out.println("There is a book with the ISBN" + randomIsbn
                                + ", you cannot add a new book with the same ISBN!");

                    } else {
                        bookList += " ISBN" + randomIsbn + ":" + bookName;
                        System.out.println("New book with ISBN " + randomIsbn + " is added!");

                    }
                    System.out.println("Your books:");
                    System.out.println(bookList);

                } else if (order.equals("4")) {
                    System.out.println("-- Delete Book --");
                    System.out.print("Enter ISBN which you want to delete: ");
                    String deleteIsbn = input.nextLine();
                    // Define a start index
                    int start = bookList.indexOf("ISBN" + deleteIsbn + ":");
                    // Check for the isbn to delete
                    if (start != -1) {
                        // Define an end index
                        int end = bookList.indexOf(" ", start);
                        // Compile the substrings
                        bookList = bookList.substring(0, start) + bookList.substring(end);
                        System.out.println("The book with the ISBN " + deleteIsbn + " deleted successfully!");
                    } else {
                        System.out.println("You don't have any book with the ISBN " + deleteIsbn + "!");
                    }
                    System.out.println("Your Books: " + bookList);
                    input.close();

                } else if (order.equals("5")) {
                    System.out.println("Logged out successfully!");
                }
            } else {
                System.out.println("Incorrect password! Goodbye!");
            }
        }

    }
}
