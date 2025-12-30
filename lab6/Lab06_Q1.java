/**
 * This program is a theater hall seater that prioritize the distance 
 * between each person for every line using an algorithm
 * 
 * @author Erkam Özdemir (22403374)
 * @version 23/10/2025 10:30
 */
package lab6;

import java.util.Scanner;

public class Lab06_Q1 {
    // To create the first stage of the theater hall
    public static void arrayFill(String[][] seats) {
        String empty = "-";
        // Fill all the space in the two-dimensional array with '-'
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                seats[i][j] = empty;
            }
        }
    }

    // To print the array
    public static void arrayPrint(String[][] seats) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                System.out.print(seats[i][j]);
            }
            System.out.println();
        }
    }

    // To check if any group size exceeds the column count
    public static boolean columnCheck(int column, int[] groupSizes) {
        boolean isOkey = true;
        for (int i = 0; i < groupSizes.length; i++) {
            if (groupSizes[i] > column) {
                isOkey = false;
                System.out.println("Error: group size " + groupSizes[i] + " exceeds columns " + column + " in row "
                        + (i + 1) + ".");
            }
        }
        return isOkey;
    }

    // To create the final theater hall after the seats have been placed
    public static void optimizedDistanceArray(String[][] seats, int[] groupSizes, int rows, int column) {
        String full = "x";
        for (int i = 0; i < seats.length; i++) {
            if (groupSizes[i] > 0) {
                if (groupSizes[i] == 1) {
                    seats[i][0] = full;
                } else {
                    // Calculate the gap and the remainder according to the algorithm
                    int gap = (column - 1) / (groupSizes[i] - 1);
                    int remainder = (column - 1) % (groupSizes[i] - 1);
                    int place = 0;
                    // First seat is always full if the person count is greater than 0
                    seats[i][0] = full;
                    // We already sat the first person
                    int seatedPeople = 1;
                    while (seatedPeople < groupSizes[i]) {
                        // Check if it is a big gap or a small one
                        if (seatedPeople <= remainder) {
                            place += (gap + 1);
                        } else {
                            place += gap;
                        }
                        seats[i][place] = full;
                        seatedPeople++;
                    }
                }
            }
        }
        arrayPrint(seats);
    }

    public static void main(String[] args) {
        // Set up the scanner
        Scanner in = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int row = in.nextInt();
        System.out.print("Enter number of columns: ");
        int column = in.nextInt();
        // To avoid bugs
        in.nextLine();
        // Create the two dimensional array
        String[][] seats = new String[row][column];
        // Fill the array using the method 'arrayFill'
        arrayFill(seats);
        System.out.print("Enter the groups for " + row + " rows and " + column + " columns: ");
        // Get the input for group sizes in a string type because it has whitespaces and
        // commas
        String groups = in.nextLine();
        // Split the text and put them in an array
        String[] parts = groups.split(", ");
        int[] groupSizes = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            // Get the values of the numbers using 'parseInt' and then put them in the
            // integer array
            groupSizes[i] = Integer.parseInt(parts[i]);
        }
        // Check for errors and give the output
        if (groupSizes.length < row) {
            System.out.println("Error: expected " + row + " group sizes but got " + groupSizes.length + ".");
        } else if (groupSizes.length == row) {
            boolean isFitting = columnCheck(column, groupSizes);
            if (isFitting) {
                optimizedDistanceArray(seats, groupSizes, row, column);
            }
        }
        in.close();

    }

}
