/**
 * This program determines the student's letter grade
 * and whether they passed or failed the course.
 * 
 * @author Erkam Özdemir (22403374)
 * @version 16/10/2025 10:00 
 */

package lab3;

import java.util.Scanner;

public class Lab03_Q1 {
    public static void main(String[] args) {
        // Set up the scanner
        Scanner notes = new Scanner(System.in);
        // Ask the user for midterm and final exam grades
        System.out.print("Enter your midterm score: ");
        int midterm = notes.nextInt();
        System.out.print("Enter your final exam score: ");
        int finalExam = notes.nextInt();
        // Close the Scanner
        notes.close();
        // Define the variable depending on the input
        double average = (midterm * 0.4) + (finalExam * 0.6);
        // Calculate and round up
        double averageFinal = Math.round(average * 100.0) / 100.0;
        // Define a string that changes in conditions
        String letterGrade;
        // Write the condition with if command
        if (finalExam < 50) {
            letterGrade = "FF";
        } else if (average >= 90) {
            letterGrade = "AA";
        } else if (average < 90 && average >= 85) {
            letterGrade = "BA";
        } else if (average < 85 && average >= 80) {
            letterGrade = "BB";
        } else if (average < 80 && average >= 75) {
            letterGrade = "CB";
        } else if (average < 75 && average >= 70) {
            letterGrade = "CC";
        } else if (average < 70 && average >= 60) {
            letterGrade = "DC";
        } else if (average < 60 && average >= 50) {
            letterGrade = "DD";
        } else {
            letterGrade = "FF";
        }
        // Define a string that will be printed out depending on the letter grade
        String prompt;
        // Use if to regulate the terms
        if (finalExam < 50) {
            prompt = "You failed the course due to insufficient final exam score.";
        } else if (letterGrade == "FF") {
            prompt = "You failed the course.";
        } else {
            prompt = "You passed the course successfully. Congratulations!";
        }
        // Print out the results
        System.out.println("Your average is: " + averageFinal);
        System.out.println("Your letter grade is: " + letterGrade);
        System.out.println(prompt);

    }
}
