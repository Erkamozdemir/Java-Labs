package lab4;

import java.util.Random;
import java.util.Scanner;

public class Lab04_Q3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Welcome to Battleship!");
        System.out.println("The grid is 5×5. There are 5 hidden ships.");
        System.out.println();
        System.out.println("Initial Grid:");
        String line = "~ ~ ~ ~ ~ \n";
        String grid = line.repeat(5);
        System.out.println(grid);
        String x = "X";
        String o = "O";
        String one = "1";
        String hiddenLine = "0 0 0 0 0 \n";
        String hiddenGrid = hiddenLine.repeat(5);

        int player1Score = 0;
        int player2Score = 0;
        int player3Score = 0;

        int shipsLocated = 0;
        int numberOfShips = 5;

        while (shipsLocated < 5) {
            int hiddenRow = random.nextInt(5);
            int hiddenColumn = random.nextInt(5);

            // find the character index of this position
            int hiddenIndex = hiddenRow * 11 + hiddenColumn * 2; // each line has 5 chars + spaces + '\n'

            // check if there's already a ship there
            if (hiddenGrid.charAt(hiddenIndex) != one.charAt(0)) {
                hiddenGrid = hiddenGrid.substring(0, hiddenIndex) + "1" + hiddenGrid.substring(hiddenIndex + 1);
                shipsLocated++;
            }
        }
        int round = 1;
        int currentPlayer = 1;
        int guesses = 0;
        boolean gameEnds = false;
        while (!gameEnds) {
            while (currentPlayer == 1) {
                System.out.println("###### Round #" + round);
                System.out.println("## Player" + currentPlayer + " ##");
                System.out.println("Previous Grid:");
                System.out.println(grid);
                System.out.print("Guess row: ");
                int guessedRow = input.nextInt();
                System.out.print("Guess column: ");
                int guessedColumn = input.nextInt();
                guesses++;
                while (guessedRow > 4 || guessedColumn > 4 || guessedRow < 0 || guessedColumn < 0) {
                    System.out.println("Invalid guess. Try again.");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    System.out.print("Guess row: ");
                    guessedRow = input.nextInt();
                    System.out.print("Guess column: ");
                    guessedColumn = input.nextInt();
                    guesses++;
                }
                int locationIndex = guessedRow * 11 + guessedColumn * 2;
                boolean isShip = false;
                if (hiddenGrid.charAt(locationIndex) == one.charAt(0)) {
                    isShip = true;
                }
                if (grid.charAt(locationIndex) == x.charAt(0)) {
                    System.out.println("Already hit this ship!");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    currentPlayer++;
                } else if (grid.charAt(locationIndex) == o.charAt(0)) {
                    System.out.println("Already guessed here!");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    currentPlayer++;
                } else {
                    if (isShip) {
                        System.out.println("Hit!");
                        player1Score++;
                        numberOfShips--;
                        hiddenGrid = hiddenGrid.substring(0, locationIndex) + "0"
                                + hiddenGrid.substring(locationIndex + 1);
                        grid = grid.substring(0, locationIndex) + "X" + grid.substring(locationIndex + 1);
                        System.out.println("Current Grid:");
                        System.out.println(grid);
                        currentPlayer++;
                    } else {
                        System.out.println("Miss!");
                        grid = grid.substring(0, locationIndex) + "O" + grid.substring(locationIndex + 1);
                        System.out.println("Current Grid:");
                        System.out.println(grid);
                        currentPlayer++;
                    }
                    if (numberOfShips == 0) {
                        gameEnds = true;
                    }
                }
                if (gameEnds) {
                    break;
                }
            }
            while (currentPlayer == 2) {
                System.out.println("## Player" + currentPlayer + " ##");
                System.out.println("Previous Grid:");
                System.out.println(grid);
                System.out.print("Guess row: ");
                int guessedRow = input.nextInt();
                System.out.print("Guess column: ");
                int guessedColumn = input.nextInt();
                guesses++;
                while (guessedRow > 4 || guessedColumn > 4 || guessedRow < 0 || guessedColumn < 0) {
                    System.out.println("Invalid guess. Try again.");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    System.out.print("Guess row: ");
                    guessedRow = input.nextInt();
                    System.out.print("Guess column: ");
                    guessedColumn = input.nextInt();
                    guesses++;
                }
                int locationIndex = guessedRow * 11 + guessedColumn * 2;
                boolean isShip = false;
                if (hiddenGrid.charAt(locationIndex) == one.charAt(0)) {
                    isShip = true;
                }
                if (grid.charAt(locationIndex) == x.charAt(0)) {
                    System.out.println("Already hit this ship!");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    currentPlayer++;
                } else if (grid.charAt(locationIndex) == o.charAt(0)) {
                    System.out.println("Already guessed here!");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    currentPlayer++;
                } else {
                    if (isShip) {
                        System.out.println("Hit!");
                        player2Score++;
                        numberOfShips--;
                        hiddenGrid = hiddenGrid.substring(0, locationIndex) + "0"
                                + hiddenGrid.substring(locationIndex + 1);
                        grid = grid.substring(0, locationIndex) + "X" + grid.substring(locationIndex + 1);
                        System.out.println("Current Grid:");
                        System.out.println(grid);
                        currentPlayer++;
                    } else {
                        System.out.println("Miss!");
                        grid = grid.substring(0, locationIndex) + "O" + grid.substring(locationIndex + 1);
                        System.out.println("Current Grid:");
                        System.out.println(grid);
                        currentPlayer++;
                    }
                    if (numberOfShips == 0) {
                        gameEnds = true;
                    }
                }
                if (gameEnds) {
                    break;
                }
            }
            while (currentPlayer == 3) {
                System.out.println("## Player" + currentPlayer + " ##");
                System.out.println("Previous Grid:");
                System.out.println(grid);
                System.out.print("Guess row: ");
                int guessedRow = input.nextInt();
                System.out.print("Guess column: ");
                int guessedColumn = input.nextInt();
                guesses++;
                while (guessedRow > 4 || guessedColumn > 4 || guessedRow < 0 || guessedColumn < 0) {
                    System.out.println("Invalid guess. Try again.");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    System.out.print("Guess row: ");
                    guessedRow = input.nextInt();
                    System.out.print("Guess column: ");
                    guessedColumn = input.nextInt();
                    guesses++;
                }
                int locationIndex = guessedRow * 11 + guessedColumn * 2;
                boolean isShip = false;
                if (hiddenGrid.charAt(locationIndex) == one.charAt(0)) {
                    isShip = true;
                }
                if (grid.charAt(locationIndex) == x.charAt(0)) {
                    System.out.println("Already hit this ship!");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    currentPlayer++;
                } else if (grid.charAt(locationIndex) == o.charAt(0)) {
                    System.out.println("Already guessed here!");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    currentPlayer++;
                } else {
                    if (isShip) {
                        System.out.println("Hit!");
                        player3Score++;
                        numberOfShips--;
                        grid = grid.substring(0, locationIndex) + "X" + grid.substring(locationIndex + 1);
                        System.out.println("Current Grid:");
                        System.out.println(grid);
                        currentPlayer -= 2;
                    } else {
                        System.out.println("Miss!");
                        grid = grid.substring(0, locationIndex) + "O" + grid.substring(locationIndex + 1);
                        System.out.println("Current Grid:");
                        System.out.println(grid);
                        currentPlayer -= 2;
                    }
                    if (numberOfShips == 0) {
                        gameEnds = true;
                    }
                }
                if (gameEnds) {
                    break;
                }
                round++;
            }

        }
        input.close();
        System.out.println("*********************");
        System.out.println("Game finished!");
        System.out.println("Total rounds played: " + round);
        System.out.println("Total guesses: " + guesses);
        System.out.println("Player1 score: " + player1Score + " ships found");
        System.out.println("Player2 score: " + player2Score + " ships found");
        System.out.println("Player3 score: " + player3Score + " ships found");
        int maxScore = Math.max(player1Score, Math.max(player2Score, player3Score));
        int winners = 0;
        if (player1Score == maxScore)
            winners++;
        if (player2Score == maxScore)
            winners++;
        if (player3Score == maxScore)
            winners++;
        if (winners > 1) {
            System.out.println("It's a tie!");
        } else if (player1Score == maxScore) {
            System.out.println("Winner: Player1");
        } else if (player2Score == maxScore) {
            System.out.println("Winner: Player2");
        } else {
            System.out.println("Winner: Player3");
        }

    }
}
