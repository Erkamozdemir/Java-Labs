package lab4;

import java.util.Scanner;

public class Lab04_Q2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a positive integer (0 to quit):");
        int number = input.nextInt();
        boolean isPositive = true;
        while (number < 0) {
            System.out.println("Only positive integers are allowed.");
            System.out.print("Enter a positive integer (0 to quit):");
            number = input.nextInt();
        }
        while (isPositive) {
            if (number == 0) {
                isPositive = false;
                System.out.println("Program ended.");
            } else {
                String numberString = Integer.toString(number);
                int size = numberString.length();
                String reverse = "";
                for (int i = (size - 1); i >= 0; i--) {
                    char letter = numberString.charAt(i);
                    reverse += letter;
                }
                if (reverse.equals(numberString)) {
                    System.out.println(numberString + " is a palindrome.");
                } else {
                    System.out.println(numberString + " is not a palindrome.");
                }
                System.out.print("Enter a positive integer (0 to quit):");
                number = input.nextInt();
            }
            input.close();
        }
    }
}
