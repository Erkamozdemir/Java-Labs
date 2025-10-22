/*
* This program calculates a person with a 500kcal caloric deficits rate of
* losing weight in kilograms
* 
* @author Erkam Özdemir 22403374
* 
* @version 02/10/2025
*/
public class Lab01_Q3_Revision {

    public static void main(String[] args) {
        // First we defıne our constants like deficit per days, calories in proteins
        // etc.
        final int KILOGRAMS_TO_LOSE = 1;
        final int DEFICIT_PER_DAYS = 500;
        final int MAINTENANCE_CALORIES = 2000;
        final int TOTAL_CALORIES_FOR_ONE_KG = 7500;

        final int CALORIES_IN_CARBOHYDRATES = 4;
        final int CALORIES_IN_FATS = 9;
        final int CALORIES_IN_PROTEINS = 4;

        final double CARB_PERCENT = 0.5;
        final double FAT_PERCENT = 0.3;
        final double PROTEIN_PERCENT = 0.2;
        // Then we calculate the varıables by using our constants
        int daysToLose = (KILOGRAMS_TO_LOSE * TOTAL_CALORIES_FOR_ONE_KG) / DEFICIT_PER_DAYS;
        int dailyCaloricIntake = MAINTENANCE_CALORIES - DEFICIT_PER_DAYS;

        double carbGrams = (dailyCaloricIntake * CARB_PERCENT) / CALORIES_IN_CARBOHYDRATES;
        double fatGrams = (dailyCaloricIntake * FAT_PERCENT) / CALORIES_IN_FATS;
        double proteinGrams = (dailyCaloricIntake * PROTEIN_PERCENT) / CALORIES_IN_PROTEINS;

        int recommendedCarbPercent = (int) (CARB_PERCENT * 100);
        int recommendedFatPercent = (int) (FAT_PERCENT * 100);
        int recommendedProteinPercent = (int) (PROTEIN_PERCENT * 100);

        int carbCalories = (int) (dailyCaloricIntake * CARB_PERCENT);
        int fatCalories = (int) (dailyCaloricIntake * FAT_PERCENT);
        int proteinCalories = (int) (dailyCaloricIntake * PROTEIN_PERCENT);
        // Then we print it out
        System.out.println("Kilograms to lose: " + KILOGRAMS_TO_LOSE);
        System.out.println("Days to lose " + KILOGRAMS_TO_LOSE + "kg assuming daily deficit of " + DEFICIT_PER_DAYS
                + "kcals" + ": " + daysToLose);
        System.out.println("DAILY CALORIES TO LOSE " + KILOGRAMS_TO_LOSE + " KILOS IN " + daysToLose + " DAYS: "
                + dailyCaloricIntake);
        System.out.println("RECOMMENDED DAILY CALORIES TO LOSE " + KILOGRAMS_TO_LOSE + " KILOS IN " + daysToLose
                + " DAYS: " + dailyCaloricIntake);
        // Here I needed to use printf for building columns to make it look like a table
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
