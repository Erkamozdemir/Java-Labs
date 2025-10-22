
/**
 * This program calculates and displays two objects corresponding weights at 4 altitudes and their differences to base weight.
 * 
 * @author Erkam Özdemir (22403374)
 * @version 09/10/2025 10:00 
 */
import java.util.Scanner;

public class Lab02_Q2_Revision {
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
        inputScanner.close();
        // Define the variables using the inputs and constants
        double firstWeight0 = firstObject * G;
        double firstWeight10 = firstObject * G_10;
        double firstWeight100 = firstObject * G_100;
        double firstWeight1000 = firstObject * G_1000;

        double secondWeight0 = secondObject * G;
        double secondWeight10 = secondObject * G_10;
        double secondWeight100 = secondObject * G_100;
        double secondWeight1000 = secondObject * G_1000;
        // Calculate the differences
        double difference1 = firstWeight0 - firstWeight10;
        double difference2 = firstWeight0 - firstWeight100;
        double difference3 = firstWeight0 - firstWeight1000;

        double difference4 = secondWeight0 - secondWeight10;
        double difference5 = secondWeight0 - secondWeight100;
        double difference6 = secondWeight0 - secondWeight1000;
        // Using String to make some of them into a specific format '.1'
        String formattedFirst10 = String.format("%.1f", firstWeight10) + String.format("(%6.1f)", difference1);
        String formattedFirst100 = String.format("%.1f", firstWeight100) + String.format("(%6.1f)", difference2);
        String formattedFirst1000 = String.format("%.1f", firstWeight1000) + String.format("(%6.1f)", difference3);

        String formattedSecond10 = String.format("%.1f", secondWeight10) + String.format("(%6.1f)", difference4);
        String formattedSecond100 = String.format("%.1f", secondWeight100) + String.format("(%6.1f)", difference5);
        String formattedSecond1000 = String.format("%.1f", secondWeight1000) + String.format("(%6.1f)", difference6);
        // Using string methods to make formatting easier
        String space = "           ";
        String header = "%-20s%8s%15s%15s%15s%n";
        String rowFormat = "%-20s%8.1f%15s%15s%15s%n";
        // Using string format to regulate the space in paranthesis
        String object1 = String.format("OBJECT ONE(%5.1fkg)", firstObject);
        String object2 = String.format("OBJECT TWO(%5.1fkg)", secondObject);
        // Print out the results with printf to make it like a table
        System.out.printf(space + header, "", "0km", "10km", "100km", "1000km");
        System.out.printf(space + rowFormat, object1, firstWeight0, formattedFirst10, formattedFirst100,
                formattedFirst1000);
        System.out.printf(space + rowFormat, object2, secondWeight0, formattedSecond10, formattedSecond100,
                formattedSecond1000);

    }
}
