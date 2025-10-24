/**
 * This program print out a triangle out of stars (*) in a specific range from users input
 * 
 * @author Erkam Özdemir (22403374)
 * @version 23/10/2025 10:30
 */
package lab4;

import java.util.Scanner;

public class Lab04_Q1 {
    public static void main(String[] args) {
        // Set up a scanner
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a valid height: ");
        // Get the input
        int number = input.nextInt();
        // Definitions
        int i = 1;
        // Set up a boolean to check if the number is out of range
        boolean isEnded = true;
        // Make a loop to check if the number is in the range
        while (isEnded) {
            if (number >= 30 || number <= 0) {
                System.out.println("Invalid input, try again.");
                System.out.print("Enter a valid height: ");
                // Ask for a new input until its in the range
                number = input.nextInt();
            } else {
                // Set up a loop to print out the triangle shape
                while (isEnded) {
                    while (i <= number) {
                        String space = " ";
                        String star = "*";
                        String spaces = space.repeat(number - i);
                        String stars = star.repeat(2 * i - 1);
                        System.out.println(spaces + stars);
                        // Calculate the new variables
                        i++;
                        if (i == number) {
                            isEnded = false;
                        }
                    }
                }
            }
        }
        // Close the scanner
        input.close();
    }
}
