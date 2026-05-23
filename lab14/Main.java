import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("How many numbers to generate? ");
        int n = in.nextInt();
        System.out.println("Ordering:");
        System.out.println("  1. Ascending");
        System.out.println("  2. Descending");
        System.out.println("  3. Alternating");
        System.out.println("  4. Random");
        System.out.print("Your choice (1-4): ");
        int choice = in.nextInt();
        int minVal = 0;
        int maxVal = 10000;
        if (choice == 4) {
            System.out.print("Enter minimum value for Random LinkedList: ");
            minVal = in.nextInt();
            System.out.print("Enter maximum value for Random LinkedList: ");
            maxVal = in.nextInt();
        }

        System.out.println("\nWhich sorting algorithm?");
        System.out.println("  1. Insertion Sort");
        System.out.println("  2. Merge Sort");
        System.out.println("  3. Quick Sort");
        System.out.println("  4. 3-Way Quick Sort");
        System.out.print("Your choice (1-4): ");
        int sortType = in.nextInt();

        System.out.print("\nDo you want to print the lists? (1 for Yes, 0 for No): ");
        int printingChoice = in.nextInt();
        LinkedList list = generateNewLinkedList(n, choice, minVal, maxVal);
        System.out.println("\nList Size: " + list.size());

        if (printingChoice == 1) {
            System.out.println("\n--- Original List ---");
            printLinkedList(list);
        }
        double timeTaken = runSorting(list, sortType);

        if (printingChoice == 1) {
            System.out.println("\n--- Sorted List ---");
            printLinkedList(list);
        }
        System.out.printf("\nTime taken: %.10f seconds\n", timeTaken);

        in.close();
    }

    private static void printLinkedList(LinkedList list) {
        System.out.println(list.toString());
    }

    private static LinkedList generateNewLinkedList(int n, int choice, int min, int max) {
        LinkedList list = new LinkedList();
        switch (choice) {
            case 1:
                for (int i = 1; i <= n; i++)
                    list.add(i);
                break;
            case 2:
                for (int i = n; i >= 1; i--)
                    list.add(i);
                break;
            case 3:
                int low = 1;
                int high = n;
                while (low <= high) {
                    list.add(low);
                    if (low != high) {
                        list.add(high);
                    }
                    low++;
                    high--;
                }
                break;
            case 4:
                Random randomNumber = new Random();
                int range = max - min + 1;
                for (int i = 0; i < n; i++) {
                    list.add(min + randomNumber.nextInt(range));
                }
                break;
            default:
                for (int i = 1; i <= n; i++)
                    list.add(i);
        }
        return list;
    }

    private static double runSorting(LinkedList list, int sortingType) {
        long startTime = System.currentTimeMillis();

        switch (sortingType) {
            case 1:
                list.insertionSort();
                break;
            case 2:
                list.mergeSort();
                break;
            case 3:
                list.quickSort();
                break;
            case 4:
                list.threeWayQuickSort();
                break;
        }

        long endTime = System.currentTimeMillis();
        return (endTime - startTime) / 1000.0;
    }
}