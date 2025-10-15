/**
 * This program is a simple library management system where users can log in, 
 * manage their library records, and log out.
 * 
 * @author Erkam Özdemir (22403374)
 * @version 16/10/2025 10:00 
 */

package lab3;

import java.util.Random;
import java.util.Scanner;

public class Lab03_Q3 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = input.nextLine();
        String realUser = "librarian";
        String finalUsername = username.trim();

        if (finalUsername.equals(realUser)) {
            System.out.print("Enter your password: ");
        } else {
            System.out.print("Username not found! Goodbye!");
        }

        String password = input.nextLine();
        String realPass = "books2024";
        String finalPassword = password.trim();

        if (finalPassword.equals(realPass)) {
            System.out.println("1- Add member");
            System.out.println("2- Delete member");
            System.out.println("3- Add book");
            System.out.println("4- Delete book");
            System.out.println("5- Logout");
            System.out.print("Select an operation:");
        } else {
            System.out.println("Incorrect password! Goodbye!");
        }


        String memberList = "JohnSmith, EmilyDavis, ZeynepDemir, ";

        int firstComma = memberList.indexOf(',');
        int secondComma = memberList.indexOf(',', firstComma + 1);
        int thirdComma = memberList.indexOf(',', secondComma + 1);

        String names1 = memberList.substring(0, firstComma).trim();
        String names2 = memberList.substring(firstComma + 1, secondComma).trim();
        String names3 = memberList.substring(secondComma + 1, thirdComma).trim();

        String bookList = "ISBN9781:JavaProgramming ISBN9782:DataStructures";
        int space = bookList.indexOf(' ');

        String book1 = bookList.substring(0, space);
        String book2 = bookList.substring(space + 1);

        int column1 = book1.indexOf(":");
        String isbn1 = book1.substring(0, column1);
        String bookName1 = book1.substring(column1 + 1);
        isbn1 = isbn1.replace("ISBN", "");
        int column2 = book1.indexOf(":");
        String isbn2 = book2.substring(0, column2);
        String bookName2 = book2.substring(column1 + 1);
        isbn2 = isbn2.replace("ISBN", "");

        String order = input.nextLine();

        if (order.equals("1")) {
            System.out.println("-- Add Member --");
            System.out.print("Enter member name: ");
            String newName = input.nextLine().trim();
            if (newName.equalsIgnoreCase(names1) || newName.equalsIgnoreCase(names2)
                    || newName.equalsIgnoreCase(names3)) {
                System.out.println("This member is already in your list!");
                System.out.println("Your members: (" + memberList + ")");
            } else {
                System.out.println("New member " + newName + " is added!");
                memberList += (newName + ", ");
                System.out.println("Your members: (" + memberList + ")");

            }
        } else if (order.equals("2")) {
            System.out.println("-- Delete Member --");
            System.out.println("Enter member name which you want to delete:");
            String deleteName = input.nextLine().trim();
            if (deleteName.equalsIgnoreCase(names1) || deleteName.equalsIgnoreCase(names2)
                    || deleteName.equalsIgnoreCase(names3)) {
                System.out.println(deleteName + " is deleted successfully from members!");
                memberList = memberList.replace(deleteName + ", ", "");
                System.out.println("Your members: (" + memberList + ")");
            } else {
                System.out.println("You don't have any member whose name is " + deleteName + "!");
                System.out.println("Your members: (" + memberList + ")");
            }
        } else if (order.equals("3")) {
            System.out.println("-- Add Book --");
            System.out.print("Enter book name: ");
            String bookName = input.nextLine();
            String trimmedBook = bookName.replaceAll("\\s+", "");
            Random random = new Random();
            random.nextInt(10000);
            String id = String.format("%04d", random.nextInt(10000));
            if (id.equals(isbn1) || id.equals(isbn2)) {
                System.out.println(
                        "There is a book with the ISBN" + id + ", you cannot add a new book with the same ISBN!");
                System.out.println("Your books:");
                System.out.println(bookList);
            } else {
                bookList += " ISBN" + id + ":" + trimmedBook;
                System.out.println("New book with ISBN " + id + " is added!");
                System.out.println("Your books:");
                System.out.println(bookList);
            }

        } else if (order.equals("4")) {
            System.out.println("-- Delete Book --");
            System.out.print("Enter ISBN which you want to delete: ");
            String deleteIsbn = input.nextLine();
            if (deleteIsbn.equals(isbn1)) {
                System.out.println("The book with the ISBN " + deleteIsbn + " deleted successfully!");
                String finalBooklist = bookList.replace("ISBN" + isbn1 + ":" + bookName1, "");
                System.out.println("Your Books:" + finalBooklist);
            } else if (deleteIsbn.equals(isbn2)) {
                System.out.println("The book with the ISBN " + deleteIsbn + " deleted successfully!");
                String finalBooklist = bookList.replace("ISBN" + isbn2 + ":" + bookName2, "");
                System.out.println("Your Books:" + finalBooklist);
            } else {
                System.out.println("You don't have any book with the ISBN " + deleteIsbn + "!");
                System.out.println("Your books: ");
                System.out.println(bookList);
            }

        } else if (order.equals("5")) {
            System.out.println("Logged out successfully!");
        }
        input.close();

    }
}
