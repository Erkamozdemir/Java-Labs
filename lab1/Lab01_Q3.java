/*
* This program calculates a person who wants to lose x amount of calories in y amount of days using info from the user by scanners
* 
* @author Erkam Özdemir 22403374
* 
* @version 02/10/2025
*/

import java.util.Scanner;

public class Lab01_Q3 {
    public static void main(String[] args) {
        // First we define our constants
        final int MAINTENANCE_CALORIES = 2000;
        final int TOTAL_CALORIES_OF_ONE_KG = 7700;

        final int CALORIES_IN_CARBOHYDRATES = 4;
        final int CALORIES_IN_FATS = 9;
        final int CALORIES_IN_PROTEINS = 4;

        final double CARB_PERCENT = 0.5;
        final double FAT_PERCENT = 0.3;
        final double PROTEIN_PERCENT = 0.2;
        // Set up our scanner
        Scanner Scanner = new Scanner(System.in);
        // Using scanner for inputs from the user
        System.out.print("How Many Kilograms Would You Like To Lose? ");
        int kilogramsToLose = Scanner.nextInt();
        System.out.print("How Many Days You Want It To Take? ");
        int daysToLose = Scanner.nextInt();
        // Calculate our variables by using our constants and inputs from the user
        int deficitPerDays = (kilogramsToLose * TOTAL_CALORIES_OF_ONE_KG) / daysToLose;
        int dailyCaloricIntake = MAINTENANCE_CALORIES - deficitPerDays;

        double carbGrams = (dailyCaloricIntake * CARB_PERCENT) / CALORIES_IN_CARBOHYDRATES;
        double fatGrams = (dailyCaloricIntake * FAT_PERCENT) / CALORIES_IN_FATS;
        double proteinGrams = (dailyCaloricIntake * PROTEIN_PERCENT) / CALORIES_IN_PROTEINS;

        int recommendedCarbPercent = (int) (CARB_PERCENT * 100);
        int recommendedFatPercent = (int) (FAT_PERCENT * 100);
        int recommendedProteinPercent = (int) (PROTEIN_PERCENT * 100);

        int carbCalories = (int) (dailyCaloricIntake * CARB_PERCENT);
        int fatCalories = (int) (dailyCaloricIntake * FAT_PERCENT);
        int proteinCalories = (int) (dailyCaloricIntake * PROTEIN_PERCENT);
        // Printing basic things each one in its own line
        System.out.println("Kilograms to lose: " + kilogramsToLose);
        System.out.println("Days to lose " + kilogramsToLose + ": " + daysToLose);
        System.out.println("To lose " + kilogramsToLose + " kilograms in " + daysToLose
                + " days you will need a daily deficit of " + deficitPerDays + " calories");
        System.out.println("RECOMMENDED DAILY CALORIES TO LOSE " + kilogramsToLose + " KILOS IN " + daysToLose
                + " DAYS: " + dailyCaloricIntake);
        // Using printf to make it look like a table
        System.out.printf("%-20s %20s %20s %25s %20s%n", "MACRO", "RECOMMENDED PERCENT", "CALORIES PER GRAM",
                "RECOMMENDED CALORIES", "GRAMS");
        System.out.printf("%-20s %20s %20d %25d %20.1f%n", "CARBOHYDRATE", recommendedCarbPercent + "%",
                CALORIES_IN_CARBOHYDRATES, carbCalories, carbGrams);
        System.out.printf("%-20s %20s %20d %25d %20.1f%n", "FAT", recommendedFatPercent + "%", CALORIES_IN_FATS,
                fatCalories, fatGrams);
        System.out.printf("%-20s %20s %20d %25d %20.1f%n", "PROTEIN", recommendedProteinPercent + "%",
                CALORIES_IN_PROTEINS, proteinCalories, proteinGrams);

    }
}