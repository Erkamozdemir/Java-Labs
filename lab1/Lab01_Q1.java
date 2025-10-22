/*
* Fixed all errors with some changes
* 
* @author Erkam Özdemir 22403374
* 
* @version 02/10/2025
*/
public class Lab01_Q1 {

   /*
    * A simple program to fix syntax errors.
    * This program prints information about the lab schedule and grading policy.
    *
    */

   public static void main(String[] args) {
      // Constants
      final double GRADE_PERCENT = 0.15; // grading policy
      final int LAB_COUNT = 9;
      final double GRADE_PER_LAB = (GRADE_PERCENT / LAB_COUNT * 100);
      double rounded = Math.round(GRADE_PER_LAB * 100.0) / 100.0;
      // Variables
      String courseSemester;

      /* This program prints information about the lab schedule and grading policy */

      // First initialize the lab information
      courseSemester = "CS101 FALL 2025";

      // Print out this lab section info
      System.out.println("Hello everyone, below are some course details");
      System.out.println("Welcome to " + courseSemester + " Lab 01");

      // Print out the grading policy
      System.out.println("There are: " + LAB_COUNT + " lab sessions in this course.");
      System.out.println("Labs contribute to " + GRADE_PERCENT * 100 + "% of your total grade.");
      System.out.println("This lab :\t" + rounded + "\tpoints");
      System.out.println("All labs :\t" + GRADE_PERCENT * 100 + "\tpoints");
      System.out.println("Please come prepared...");

      // Wish them luck and finish
      System.out.println("Good luck!");
   }

}