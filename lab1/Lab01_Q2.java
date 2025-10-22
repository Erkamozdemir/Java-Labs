/*
* This program calculates some numbers via using Java codes like Math.pow
* 
* @author Erkam Özdemir 22403374
* 
* @version 02/10/2025
*/
public class Lab01_Q2 {
    // This code calculates x, y, z
    public static void main(String[] args) {

        double x = (23.2 - (7.1 / 2.2)) / ((5.1 - 3.7) * (3.4 + 4.2));
        // using Math.pow to calculate
        double y = (35.7 * 64.1 - Math.pow(6, 3)) / (43 + Math.pow(5, 2));
        // using Math.pow again to calculate exponents
        double z = Math.pow(2.1 + 8.0, -1.0 / 3.0);
        //print out the results
        System.out.println("y = " + y);
        System.out.println("x = " + x);
        System.out.println("z = " + z);
    }
}
