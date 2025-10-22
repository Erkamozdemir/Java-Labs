package lab4;

import java.util.Scanner;

public class Lab04_Q1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a valid height: ");
        int number = input.nextInt();
        int spaceCount = number - 1;
        int i = 1;
        boolean inRange = true;
        while (inRange) {
            if (number >= 30 || number <= 0) {
                System.out.println("Invalid input, try again.");
                System.out.print("Enter a valid height: ");
                number = input.nextInt();
                continue;
            } else {
                while (i <= number || spaceCount > 0) {
                    String space = " ";
                    String star = "*";
                    String spaces = space.repeat(spaceCount);
                    String stars = star.repeat(2 * i - 1);
                    System.out.println(spaces + stars);
                    i++;
                    spaceCount--;
                }
            }

            input.close();
        }
    }
}
