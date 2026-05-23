package CS102_Sec3_Asgn6_Ozdemir_Erkam;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the .sq file: ");
        String path = scanner.nextLine().trim();
        if ((path.startsWith("'") && path.endsWith("'")) ||
                (path.startsWith("\"") && path.endsWith("\""))) {
            path = path.substring(1, path.length() - 1);
        }
        try {
            Interpreter interpreter = new Interpreter(path);
            interpreter.run();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found → " + path);
        } finally {
            System.out.println("\nThanks for using the interpreter!");
            scanner.close();
        }
    }
}
