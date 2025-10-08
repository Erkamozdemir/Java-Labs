
/*
* This program changes the inputs format using string methods.
* 
* @author Erkam Özdemir 22403374
* 
* @version 09/10/2025
*/
import java.util.Scanner;

public class Lab02_Q3 {
    public static void main(String[] args) {
        // Setting up our scanner for information input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter student information: ");
        String information = input.nextLine();
        // Trimming our information so there is no unnecessary spaces
        String trimmed = information.trim();

        int slash = trimmed.indexOf('/');
        String name = trimmed.substring(0, slash).trim();
        String rest = trimmed.substring(slash + 1).trim();

        int hashtag1 = name.indexOf('#');
        String firstName = name.substring(0, hashtag1);
        String secondName = name.substring(hashtag1 + 1);

        int hashtag2 = rest.indexOf('#');
        String universityInfo = rest.substring(0, hashtag2).trim();
        String time = rest.substring(hashtag2 + 1).trim();

        int dash = universityInfo.indexOf('-');
        String department = universityInfo.substring(0, dash).trim();
        String university = universityInfo.substring(dash + 1).trim();

        int colon = time.indexOf(':');
        String hour = time.substring(0, colon).trim();
        String minute = time.substring(colon + 1).trim();

        System.out.println(firstName + " " + secondName + " registered to " + university + " " + department + " at "
                + minute + " past " + hour + ".");

    }
}
