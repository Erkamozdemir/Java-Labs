
/*
* This program calculates and displays two objects corresponding weights at 4 altitudes.
* 
* @author Erkam Özdemir 22403374
* 
* @version 09/10/2025
*/
import java.util.Scanner;

public class Lab02_Q2 {
    public static void main(String[] args) {
        // We define our constants
        final double G = 9.80665;
        final double G_10 = 9.77594;
        final double G_100 = 9.5059;
        final double G_1000 = 7.32628;
        // Get inputs for object1 and object2 in kilograms from the user and print them
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter the mass of the first object(kg): ");
        double firstObject = inputScanner.nextInt();
        System.out.print("Enter the mass of the second object(kg): ");
        double secondObject = inputScanner.nextInt();
        // Define the variables using the inputs and constants
        double firstWeight0 = firstObject * G;
        double firstWeight10 = firstObject * G_10;
        double firstWeight100 = firstObject * G_100;
        double firstWeight1000 = firstObject * G_1000;

        double secondWeight0 = secondObject * G;
        double secondWeight10 = secondObject * G_10; 
        double secondWeight100 = secondObject * G_100;
        double secondWeight1000 = secondObject * G_1000;
        // Using string methods to make formatting easier
        String space = "           ";
        String header = "%-20s%12s%12s%12s%12s%n";
        String rowFormat = "%-20s%12.1f%12.1f%12.1f%12.1f%n";
        // Using string format to regulate the space in paranthesis
        String object1 = String.format("OBJECT ONE(%5.1fkg)", firstObject);
        String object2 = String.format("OBJECT TWO(%5.1fkg)", secondObject);
        // Print out the results with printf to make it like a table
        System.out.printf(space + header, "", "0km", "10km", "100km", "1000km");
        System.out.printf(space + rowFormat, object1, firstWeight0, firstWeight10, firstWeight100, firstWeight1000);
        System.out.printf(space + rowFormat, object2, secondWeight0, secondWeight10, secondWeight100, secondWeight1000);

    }
}
