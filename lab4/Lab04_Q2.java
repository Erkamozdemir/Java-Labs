/**
 * This program checks if a positive integer is a palindrome.
 * 
 * @author Erkam Özdemir (22403374)
 * @version 23/10/2025 09:30
 */
package lab4;

import java.util.Scanner;

public class Lab04_Q2 {
    public static void main(String[] args) {
        // Set up a scanner
        Scanner input = new Scanner(System.in);
        // Ask for the input
        System.out.print("Enter a positive integer (0 to quit):");
        int number = input.nextInt();
        // Set up a boolean to check is the number is a positive integer
        boolean isPositive = true;
        // Ask for a new number using while loop if the integer is negative
        while (number < 0) {
            System.out.println("Only positive integers are allowed.");
            System.out.print("Enter a positive integer (0 to quit):");
            number = input.nextInt();
        }
        // Check if it is a palindrome using while loop
        while (isPositive) {
            // End the program for input '0'
            if (number == 0) {
                isPositive = false;
                System.out.println("Program ended.");
            } else {
                // Change our integer into a string
                String numberString = Integer.toString(number);
                int size = numberString.length();
                String reverse = "";
                // Reverse it using a for loop
                for (int i = (size - 1); i >= 0; i--) {
                    char letter = numberString.charAt(i);
                    reverse += letter;
                }
                // Print out the result depending on the conditions
                if (reverse.equals(numberString)) {
                    System.out.println(numberString + " is a palindrome.");
                } else {
                    System.out.println(numberString + " is not a palindrome.");
                }
                // Ask for an input again
                System.out.print("Enter a positive integer (0 to quit):");
                number = input.nextInt();
            }
            // Close the scanner
            input.close();
        }
    }
}
