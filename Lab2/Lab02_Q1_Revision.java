
/**
 * This program calculates a cones radius, volume and surface area depending on height and area of the base circle
 * 
 * @author Erkam Özdemir (22403374)
 * @version 09/10/2025 10:00 
 */
import java.util.Scanner;

public class Lab02_Q1_Revision {

    public static void main(String[] args) {
        // Get inputs for volume and height from the user
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter the surface area of the base circle: ");
        double baseCircle = inputScanner.nextDouble();
        System.out.print("Enter the height of the cone: ");
        double height = inputScanner.nextDouble();
        inputScanner.close();
        // Define the variables using the inputs
        double radius = Math.sqrt(baseCircle / Math.PI);
        double volume = baseCircle * height * 1.0 / 3.0;
        double surfaceArea = Math.sqrt(Math.pow(radius, 2) + Math.pow(height, 2)) * Math.PI * radius + baseCircle;
        // Print out by using format to have results in same columns
        System.out.printf("The radius of the cone is: %25.1f\n", radius);
        System.out.printf("The surface area of the cone is: %19.1f\n", surfaceArea);
        System.out.printf("The volume of the cone is: %25.1f\n", volume);

    }
}