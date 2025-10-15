/**
 * This program calculates the ualification for a new credit card 
 * depending on the inputs.
 * 
 * @author Erkam Özdemir (22403374)
 * @version 16/10/2025 10:00 
 */
package lab3;

import java.util.Scanner;

public class Lab03_Q2 {
    public static void main(String[] args) {
        // Define our constant which is base eligibility score
        int baseEligibility = 100;
        // Set up the scanner
        Scanner input = new Scanner(System.in);
        // Ask for users age by using the scanner
        System.out.print("Enter the applicant's age: ");
        int age = input.nextInt();
        // Ask for users annual income in dollars by using the scanner
        System.out.print("Enter the applicant's annual income: ");
        double annualIncome = input.nextDouble();
        // Calculate the points that will be added to the base eligibility score
        int incomePoints = (int) (annualIncome / 10000 * 5);
        // Ask for users credit score by using the scanner
        System.out.print("Enter the applicant's credit score: ");
        int creditScore = input.nextInt();
        // Ask for users number of cards by using the scanner
        System.out.print("Enter the number of existing credit cards: ");
        int numOfCards = input.nextInt();
        // Ask for users monthly rent/mortgage payment by using the scanner
        System.out.print("Enter the monthly rent/mortgage payment: ");
        double monthlyHousePayment = input.nextDouble();
        // Close the scanner
        input.close();
        // Calculate the cut we will get from house payment to our base eligibility
        int houseCut = (int) (monthlyHousePayment / 500 * 5);
        // Set a prompt to print out if the person isn't eligible
        String prompt = "The applicant is not approved for the credit card. Reason: \n";
        // Use if conditional commands to decide how many points will be added to the
        // base eligibility depending on age
        // Add a sentence to the prompt depending on the age because the limit is 18
        if (age < 18) {
            prompt += "Age is below 18.\n";
        } else if (age >= 18 && age <= 25) {
            baseEligibility += 10;
        } else if (age >= 26 && age <= 35) {
            baseEligibility += 20;
        } else if (age >= 36 && age <= 50) {
            baseEligibility += 25;
        } else if (age >= 51 && age <= 65) {
            baseEligibility += 15;
        }
        // Use if conditional commands to decide how many points will be added to the
        // base eligibility depending on annual income
        if (incomePoints <= 200) {
            baseEligibility += incomePoints;
        } else {
            baseEligibility += 200;
        }
        // Add another sentence to the prompt depending on the annual income
        if (annualIncome < 15000) {
            prompt += "Annual income is below $15000.\n";
        }
        // Use if conditional commands to decide how many points will be added to the
        // base eligibility depending on credit score
        // Add another sentence to the prompt depending on the credit score
        if (creditScore <= 579) {
            baseEligibility += 0;
            prompt += "Credit score is below 580.\n";
        } else if (creditScore >= 580 && creditScore <= 669) {
            baseEligibility += 50;
        } else if (creditScore >= 670 && creditScore <= 739) {
            baseEligibility += 100;
        } else if (creditScore >= 740 && creditScore <= 799) {
            baseEligibility += 150;
        } else if (creditScore >= 800 && creditScore <= 850) {
            baseEligibility += 200;
        }
        // Using if to give a limit to the number of cards
        if (numOfCards > 5) {
            prompt += "Number of existing credit cards exceeds 5.\n";
        }
        // Calculatee the total eligibility score
        int totalEligibility = baseEligibility - numOfCards * 10 - houseCut;
        // Print the result
        System.out.println("Total eligibility score: " + totalEligibility);
        // Add another sentence to the prompt depending on the total eligibility score
        if (totalEligibility < 250) {
            prompt += "Total eligibility score is below 250 points.";
        }
        // Trim the unnecessary spaces
        String trimmed = prompt.trim();
        // Print out depending on the eligibility using an if statement
        if (numOfCards > 5 || totalEligibility < 250 || creditScore <= 579 || age < 18 || annualIncome < 15000) {
            System.out.println(trimmed);
        } else {
            System.out.println("The applicant is approved for the credit card.");
        }

    }
}
