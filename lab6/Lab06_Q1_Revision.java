/**
 * This program is a theater hall seater that prioritize the distance 
 * between each person for every line using an algorithm
 * 
 * @author Erkam Özdemir (22403374)
 * @version 23/10/2025 10:30
 */
package lab6;

import java.util.Scanner;

public class Lab06_Q1_Revision {
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

    public static int[] full(String[][] seats, String places) {
        int[] index;
        if (places == null || places.equals(" ")) {
            index = new int[0];
        } else {
            String[] parts = places.split(",");
            index = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                index[i] = Integer.parseInt(parts[i].trim());
            }
        }
        return index;
    }

    public static boolean[] canPlace(String[][] seats, int[] groupSizes) {
        boolean[] canPlace = new boolean[seats.length];
        int count = 0;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j].equals("-")) {
                    count++;
                }
            }
            if (count >= groupSizes[i]) {
                canPlace[i] = true;
            }
        }
        return canPlace;
    }

    // To print the array
    public static void arrayPrint(String[][] seats) {
        System.out.print("     ");
        for (int i = 0; i < seats[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < seats.length; i++) {
            System.out.print("Row " + (i + 1) + ": ");
            for (int j = 0; j < seats[0].length; j++) {
                System.out.print(seats[i][j] + "");
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
    public static void centeredHall(String[][] seats, int[] groupSizes, boolean canPlace, int i) {
        double bestDistance = 1000000;
        double center = (seats[0].length - 1) / 2.0;
        int bestStart = -1;

        if (!canPlace) {
            System.out
                    .println("Row " + (i + 1) + ": cannot place group of " + groupSizes[i] + " due to blocked seats.");
        } else {
            int start = 0;
            while (start <= seats[0].length - groupSizes[i]) {
                boolean ok = true;
                int j = start;
                while (j < start + groupSizes[i] && ok) {
                    if (seats[i][j].equals("#")) {
                        ok = false;
                    }
                    j = j + 1;
                }
                if (ok) {
                    double segmentCenter = start + ((groupSizes[i] - 1) / 2.0);
                    if (center - segmentCenter < 0) {
                        double distance = center - segmentCenter;
                        double distanceAbs = Math.abs(center - segmentCenter);
                        if (distanceAbs < bestDistance) {
                            bestDistance = distance;
                            bestStart = start;
                        }
                    } else {
                        double distance = center - segmentCenter;
                        if (distance < Math.abs(bestDistance)) {
                            bestDistance = distance;
                            bestStart = start;
                        }
                    }

                }
                start++;
            }
        }
        if (bestStart != -1) {
            for (int h = bestStart; h < bestStart + groupSizes[i]; h++) {
                seats[i][h] = "x";
            }
        } else {
            System.out.println("Row " + (i + 1) + ": cannot place group of " + groupSizes[i] + " due to blocked seats.");
        }

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
        String[] parts = groups.split(",");
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
                for (int i = 0; i < seats.length; i++) {
                    System.out.print(
                            "Enter blocked seat indices for row " + (i + 1) + " (comma-separated, or empty): ");
                    String places = in.nextLine();
                    int[] index = full(seats, places);
                    for (int j = 0; j < column; j++) {
                        for (int h = 0; h < index.length; h++) {
                            if (index[h] == j) {
                                seats[i][j] = "#";
                            }
                        }
                    }
                }
                boolean[] canPlace = canPlace(seats, groupSizes);
                for (int i = 0; i < seats.length; i++) {
                    centeredHall(seats, groupSizes, canPlace[i], i);
                }
                arrayPrint(seats);
            }
            in.close();

        }

    }
}
