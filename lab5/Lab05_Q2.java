package lab5;

import java.util.Scanner;

public class Lab05_Q2 {
    public static boolean isLowerCase(char ch) {
        int ascii = ch;
        return (ascii >= 97 && ascii <= 122);
    }

    public static boolean isLetter(char ch) {
        int ascii = ch;
        return (ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122);
    }

    public static String caesarCipher(String text, int shift, boolean encode) {
        StringBuilder result = new StringBuilder();
        if (encode) {
            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                if (isLetter(ch)) {
                    if (isLowerCase(ch)) {
                        int ascii = ((ch - 'a') + shift) % 26;
                        ch = (char) ('a' + ascii);
                        result.append(ch);
                    } else {
                        int ascii = ((ch - 'A') + shift) % 26;
                        ch = (char) ('A' + ascii);
                        result.append(ch);
                    }
                } else {
                    result.append(ch);
                }
            }
        } else {
            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                if (isLetter(ch)) {
                    if (isLowerCase(ch)) {
                        int ascii = ((ch - 'a') - shift) % 26;
                        if (ascii < 0) {
                            ascii += 26;
                        }
                        ch = (char) ('a' + ascii);
                        result.append(ch);
                    } else {
                        int ascii = ((ch - 'A') - shift) % 26;
                        if (ascii < 0) {
                            ascii += 26;
                        }
                        ch = (char) ('A' + ascii);
                        result.append(ch);
                    }
                } else {
                    result.append(ch);
                }
            }

        }
        return result.toString();
    }

    public static String atbashCipher(String text) {
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (isLetter(ch)) {
                if (isLowerCase(ch)) {
                    ch = (char) ('a' + ('z' - ch));
                    newStr.append(ch);
                } else {
                    ch = (char) ('A' + ('Z' - ch));
                    newStr.append(ch);
                }
            } else {
                newStr.append(ch);
            }
        }
        return newStr.toString();
    }

    public static String reverseText(String text) {
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(text.length() - 1 - i);
            newStr.append(ch);
        }
        return newStr.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isQuit = false;
        while (!isQuit) {
            System.out.print("=== Cipher Toolkit ===\n" + "1) Caesar Cipher\n" + "2) Atbash Cipher\n"
                    + "3) Reverse Text\n" + "4) Exit\n" + "Choose an option (1-4): ");
            int order = input.nextInt();
            if (order == 1) {
                input.nextLine();
                System.out.print("Enter text: ");
                String text1 = input.nextLine();
                System.out.print("Enter shift amount (1-25): ");
                int shift = input.nextInt();
                input.nextLine();
                System.out.print("Type 'e' to encode and 'd' to decode: ");
                String ceaserOrder = input.nextLine();
                String trimmed = ceaserOrder.trim();
                String result = caesarCipher(text1, shift, trimmed.equals("e"));
                System.out.println("Result: " + result);
            } else if (order == 2) {
                input.nextLine();
                System.out.print("Enter text: ");
                String text2 = input.nextLine();
                String result = atbashCipher(text2);
                System.out.println("Result: " + result);
            } else if (order == 3) {
                input.nextLine();
                System.out.print("Enter text: ");
                String text3 = input.nextLine();
                String result = reverseText(text3);
                System.out.println("Result: " + result);
            } else if (order == 4) {
                System.out.println("Exiting the program. Goodbye!");
                isQuit = true;
            }
        }
        input.close();

    }

}
