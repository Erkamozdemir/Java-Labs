/*
* This program calculates a cones radius and surface area depending on height and volume
* 
* @author Erkam Özdemir 22403374
* 
* @version 09/10/2025
*/
import java.util.Scanner;

public class Lab02_Q1 {

    public static void main(String[] args) {
        // Get inputs for volume and height from the user
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter the volume of the cone: ");
        double volume = inputScanner.nextDouble();
        System.out.print("Enter the height of the cone: ");
        double height = inputScanner.nextDouble();
        // Define the variables using the inputs
        double radius = Math.sqrt((3 * volume) / (Math.PI * height));
        double surfaceArea = Math.PI * radius * (radius + Math.sqrt(Math.pow(height, 2.0) + Math.pow(radius, 2.0)));
        // Print out by using format to have results in same columns
        System.out.printf("The radius of the cone is: %17.1f\n", radius);
        System.out.printf("The surface area of the cone is: %11.1f\n", surfaceArea);

    }
}