package lab5;

import java.util.Scanner;

public class Lab05_Q1 {
    public static char toLower(char ch) {
        int ascii = ch;
        if (ascii >= 65 && ascii <= 90) {
            ascii += 32;
            ch = (char) (ascii);
            return ch;
        } else {
            return ch;
        }
    }

    public static boolean isLetter(char ch) {
        int ascii = ch;
        return (ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122);
    }

    public static boolean isDigit(char ch) {
        int ascii = ch;
        return (ascii >= 48 && ascii <= 57);
    }

    public static boolean isWhiteSpace(char ch) {
        return (ch == ' ' || ch == '\n' || ch == '\t');
    }

    public static boolean isPalindrome(String str) {
        String first = "";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!isWhiteSpace(ch)) {
                first += ch;
            }
        }
        String second = "";
        for (int i = 0; i < first.length(); i++) {
            char ch = first.charAt(i);
            char newCh = toLower(ch);
            second += newCh;
        }
        String reverse = "";
        int stringSize = second.length();
        for (int i = (stringSize - 1); i >= 0; i--) {
            char ch = second.charAt(i);
            reverse += ch;
        }
        return (reverse.equals(second));
    }

    public static boolean areAnagrams(String str1, String str2) {
        StringBuilder newStr = new StringBuilder();
        String cleaned1 = "";
        for (int i = 0; i < str1.length(); i++) {
            char ch = str1.charAt(i);
            char newCh = toLower(ch);
            if (isLetter(newCh)) {
                cleaned1 += newCh;
            }
        }
        String cleaned2 = "";
        for (int i = 0; i < str2.length(); i++) {
            char ch = str2.charAt(i);
            char newCh = toLower(ch);
            if (isLetter(newCh)) {
                cleaned2 += newCh;
            }
        }

        for (int i = 0; i < cleaned1.length(); i++) {
            char ch = cleaned1.charAt(i);
            int index = cleaned2.indexOf(ch);
            if (index != -1) {
                String sub1 = cleaned2.substring(0, index);
                String sub2 = cleaned2.substring(index + 1);
                cleaned2 = sub1 += sub2;
            } else {
                newStr.append(ch);
            }
        }
        newStr.append(cleaned2);
        String strNew = newStr.toString();
        int ascii = 150;
        for (int i = 0; i < strNew.length(); i++) {
            char ch = strNew.charAt(i);
            int min = ch;
            if (min < ascii) {
                ascii = ch;
            }
        }
        char ch = (char) (ascii);
        if (!strNew.equals("")) {
            System.out.println("Character count mismatch for: " + ch);
            return false;
        } else {
            return true;
        }

    }

    public static int wordCount(String str) {
        String trimmedStr = str.trim();
        if (trimmedStr.equals("")) {
            return 0;
        }
        int spaceCount = 0;
        for (int i = 0; i < trimmedStr.length(); i++) {
            char ch = trimmedStr.charAt(i);
            char previousCh = 0;
            if (i > 0) {
                previousCh = trimmedStr.charAt(i - 1);
            }
            if (isWhiteSpace(ch) && !isWhiteSpace(previousCh)) {
                spaceCount++;
            }
        }

        return spaceCount + 1;
    }

    public static String slugify(String str) {
        String trimmed = str.trim();
        String lowered = "";
        for (int i = 0; i < trimmed.length(); i++) {
            char ch = trimmed.charAt(i);
            char newCh = toLower(ch);
            lowered += newCh;
        }
        StringBuilder strNew = new StringBuilder("");
        for (int i = 0; i < lowered.length(); i++) {
            char ch = lowered.charAt(i);
            if (isWhiteSpace(ch)) {
                strNew.append("-");
            } else if (isLetter(ch) || isDigit(ch)) {
                strNew.append(ch);
            } else {
                strNew.append("");
            }
        }
        String returned = strNew.toString();
        return returned;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isQuit = false;
        while (!isQuit) {
            System.out.println("=== Text Toolkit ===\n" + "1) Check Palindrome\n" + "2) Check Anagrams\n"
                    + "3) Word Count\n" + "4) Slugify\n" + "5) Exit");
            System.out.print("Select an option (1-5):");
            int order = input.nextInt();
            input.nextLine();
            if (order == 1) {
                System.out.print("Enter text to check for palindrome: ");
                String palindromeOrNo = input.nextLine();
                if (isPalindrome(palindromeOrNo)) {
                    System.out.println("\"" + palindromeOrNo + "\" is a palindrome.");
                } else {
                    System.out.println("\"" + palindromeOrNo + "\" is not a palindrome.");
                }
            } else if (order == 2) {
                System.out.print("Enter first string: ");
                String str1 = input.nextLine();
                System.out.print("Enter second string: ");
                String str2 = input.nextLine();
                if (!areAnagrams(str1, str2)) {
                    System.out.println("\"" + str1 + "\" and \"" + str2 + "\" are not anagrams.");
                } else {
                    System.out.println("\"" + str1 + "\" and \"" + str2 + "\" are anagrams.");
                }
            } else if (order == 3) {
                System.out.print("Enter text to count words: ");
                String str = input.nextLine();
                int numOfWords = wordCount(str);
                System.out.println("Word count: " + numOfWords);
            } else if (order == 4) {
                System.out.print("Enter text to slugify: ");
                String str = input.nextLine();
                String newStr = slugify(str);
                System.out.println("Slug: " + newStr);
            } else if (order == 5) {
                System.out.println("Goodbye!");
                isQuit = true;
            }
        }
        input.close();
    }
}
