public class Lab01_Q3{
    public static void main(String[] args) {
        int kilogramsToLose = 1;
        int daysToLose = 7;
        int maintenanceCalories = 2000;
        int totalCaloriesForOneKG = 7700;

        int caloriesInCarbohydrates = 4;
        int caloriesInFats = 9;
        int caloriesInProteins = 4;

        double carbPercent = 0.5;
        double fatPercent = 0.3;
        double proteinPercent = 0.2;

        int deficitPerDays = (kilogramsToLose * totalCaloriesForOneKG) / daysToLose;
        int dailyCaloricIntake = maintenanceCalories - deficitPerDays;

        double carbGrams = (dailyCaloricIntake * carbPercent) / caloriesInCarbohydrates;
        double fatGrams = (dailyCaloricIntake * fatPercent) / caloriesInFats;
        double proteinGrams = (dailyCaloricIntake * proteinPercent) / caloriesInProteins;

        int recommendedCarbPercent = (int)(carbPercent * 100);
        int recommendedFatPercent = (int)(fatPercent * 100);
        int recommendedProteinPercent = (int)(proteinPercent * 100);

        int carbCalories = (int)(dailyCaloricIntake * carbPercent);
        int fatCalories = (int)(dailyCaloricIntake * fatPercent);
        int proteinCalories = (int)(dailyCaloricIntake * proteinPercent);

        System.out.println("Kilograms to lose: " + kilogramsToLose);
        System.out.println("Days to lose: " + daysToLose);
        System.out.println("To lose " + kilogramsToLose + " kilograms in " + daysToLose + " days you will need a daily deficit of " + deficitPerDays + " calories");
        System.out.println("RECOMMENDED DAILY CALORIES TO LOSE " + kilogramsToLose + " KILOS IN " + daysToLose + " DAYS: " + dailyCaloricIntake);
        System.out.printf("%-20s %20s %20s %25s %20s%n" , "MACRO" , "RECOMMENDED PERCENT", "CALORIES PER GRAM", "RECOMMENDED CALORIES", "GRAMS");
        System.out.printf("%-20s %20s %20d %25d %20.1f%n", "CARBOHYDRATE", recommendedCarbPercent + "%", caloriesInCarbohydrates, carbCalories, carbGrams);
        System.out.printf("%-20s %20s %20d %25d %20.1f%n", "FAT", recommendedFatPercent + "%", caloriesInFats, fatCalories, fatGrams);
        System.out.printf("%-20s %20s %20d %25d %20.1f%n", "PROTEIN", recommendedProteinPercent + "%", caloriesInProteins, proteinCalories, proteinGrams);








    }
}