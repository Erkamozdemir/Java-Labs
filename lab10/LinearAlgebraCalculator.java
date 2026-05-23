import java.util.Scanner;

public class LinearAlgebraCalculator {
    private static void printNegate(Algebraic original, Algebraic result) {
        String[] origLines = original.toString().split("\n");
        String[] resLines = result.toString().split("\n");
        int n = origLines.length;
        int origWidth = maxWidth(origLines);
        int mid = n / 2;

        for (int i = 0; i < n; i++) {
            String prefix;
            if (i == mid) {
                prefix = "- ";
            } else {
                prefix = "  ";
            }
            String stringEqual;
            if (i == mid) {
                stringEqual = " = ";
            } else {
                stringEqual = "   ";
            }
            System.out.println(prefix + pad(origLines[i], origWidth) + stringEqual + resLines[i]);
        }
    }

    private static void printOperation(Algebraic a, String op, Algebraic b, Algebraic result) {
        String[] aLines = a.toString().split("\n");
        String[] bLines = b.toString().split("\n");
        String[] resultLines = result.toString().split("\n");
        int n = Math.max(Math.max(aLines.length, bLines.length), resultLines.length);
        int aWidth = maxWidth(aLines);
        int bWidth = maxWidth(bLines);
        int mid = n / 2;

        for (int i = 0; i < n; i++) {
            String aPart;
            if (i < aLines.length) {
                aPart = aLines[i];
            } else {
                aPart = "";
            }
            String bPart;
            if (i < bLines.length) {
                bPart = bLines[i];
            } else {
                bPart = "";
            }
            String resultPieces;
            if (i < resultLines.length) {
                resultPieces = resultLines[i];
            } else {
                resultPieces = "";
            }
            String operationString;
            if (i == mid) {
                operationString = " " + op + " ";
            } else {
                operationString = "   ";
            }
            String stringEqual;
            if (i == mid) {
                stringEqual = " = ";
            } else {
                stringEqual = "   ";
            }
            System.out.println(pad(aPart, aWidth) + operationString + pad(bPart, bWidth) + stringEqual + resultPieces);
        }
    }

    private static void printCompare(Algebraic a, Algebraic b, boolean equal) {
        String[] aLines = a.toString().split("\n");
        String[] bLines = b.toString().split("\n");
        int n = Math.max(aLines.length, bLines.length);
        int aWidth = maxWidth(aLines);

        for (int i = 0; i < n; i++) {
            String aPart;
            if (i < aLines.length) {
                aPart = aLines[i];
            } else {
                aPart = "";
            }
            String bPart;
            if (i < bLines.length) {
                bPart = bLines[i];
            } else {
                bPart = "";
            }

            if (i == 0) {
                System.out.println(pad(aPart, aWidth) + " == " + bPart + " ==> " + equal);
            } else {
                String indent = " ".repeat(aWidth + 4);
                System.out.println(indent + bPart);
            }
        }
    }

    private static int maxWidth(String[] lines) {
        int max = 0;
        for (String s : lines) {
            if (s.length() > max)
                max = s.length();
        }
        return max;
    }

    private static String pad(String s, int width) {
        if (s.length() >= width)
            return s;
        return s + " ".repeat(width - s.length());
    }

    private static Algebraic readAlgebraic(Scanner scanner) {
        System.out.print("Enter number of rows and columns (n x m): ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        if (rows == 1) {
            System.out.print("Enter vector elements separated by spaces: ");
            float[] vec = new float[cols];
            for (int i = 0; i < cols; i++) {
                vec[i] = scanner.nextFloat();
            }
            return new Vector(vec);
        } else {
            System.out.println("Enter matrix elements separated by spaces:");
            float[][] mat = new float[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    mat[i][j] = scanner.nextFloat();
                }
            }

            if (rows == cols && isLowerTriangular(mat)) {
                return new LTMatrix(mat);
            }
            return new Matrix(mat);
        }
    }

    private static boolean isLowerTriangular(float[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = i + 1; j < mat[0].length; j++) {
                if (mat[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a vector or matrix:");
        Algebraic current = readAlgebraic(scanner);
        System.out.println(current);

        boolean running = true;
        while (running) {
            boolean isVector = current instanceof Vector && !(current instanceof Matrix);

            System.out.println("\nSelect an operation:");
            System.out.println("1: Negate");
            System.out.println("2: Add");
            System.out.println("3: Subtract");
            System.out.println("4: Multiply");
            if (isVector) {
                System.out.println("5: Cross Product");
            } else {
                System.out.println("5: Determinant");
            }
            System.out.println("6: Compare");
            System.out.println("7: Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Algebraic negated = current.negate();
                    printNegate(current, negated);
                    current = negated;
                    break;

                case 2:
                    System.out.println("Enter the second vector or matrix:");
                    Algebraic second = readAlgebraic(scanner);
                    Algebraic sum = current.add(second);
                    if (sum == null) {
                        System.out.println("Invalid operation");
                    } else {
                        printOperation(current, "+", second, sum);
                        current = sum;
                    }
                    break;

                case 3:
                    System.out.println("Enter the second vector or matrix:");
                    Algebraic sub = readAlgebraic(scanner);
                    Algebraic diff = current.subtract(sub);
                    if (diff == null) {
                        System.out.println("Invalid operation");
                    } else {
                        printOperation(current, "-", sub, diff);
                        current = diff;
                    }
                    break;

                case 4:
                    System.out.println("Enter the second vector or matrix:");
                    Algebraic mult = readAlgebraic(scanner);
                    Algebraic product = current.multiply(mult);
                    if (product == null) {
                        System.out.println("Invalid operation");
                    } else {
                        printOperation(current, "*", mult, product);
                        current = product;
                    }
                    break;

                case 5:
                    if (isVector) {
                        System.out.println("Enter the second vector");
                        Algebraic crossVec = readAlgebraic(scanner);
                        if (crossVec instanceof Vector) {
                            Vector cross = ((Vector) current).crossproduct((Vector) crossVec);
                            if (cross == null) {
                                System.out.println("Invalid operation");
                            } else {
                                printOperation(current, "x", crossVec, cross);
                                current = cross;
                            }
                        }
                    } else {
                        Algebraic det = ((Matrix) current).determinant();
                        if (det == null) {
                            System.out.println("null");
                        } else {
                            System.out.println(det);
                            current = det;
                        }
                    }
                    break;

                case 6:
                    System.out.println("Enter the second vector or matrix:");
                    Algebraic compare = readAlgebraic(scanner);
                    boolean equal = current.equals(compare);
                    printCompare(current, compare, equal);
                    break;

                case 7:
                    System.out.println("Exiting...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }

        scanner.close();
    }
}
