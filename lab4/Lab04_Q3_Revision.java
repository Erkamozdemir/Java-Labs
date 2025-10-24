/**
 * This program is the game battleship with a 5x5 grid and 3 players 5 random ships 
 * are located. First ship gives extra 3 points and last ship gives an extra 5. Misses cut 1 point.
 * Player who gets more points win.
 * 
 * @author Erkam Özdemir (22403374)
 * @version 23/10/2025 11:00
 */
package lab4;

import java.util.Random;
import java.util.Scanner;

public class Lab04_Q3_Revision {
    public static void main(String[] args) {
        // Set up a scanner
        Scanner input = new Scanner(System.in);
        // Get the random generater for ship location
        Random random = new Random();
        // Print the start of the game
        System.out.println("Welcome to Battleship!");
        System.out.println("The grid is 5x5. There are 5 hidden ships.");
        System.out.println("Misses cost 1 point. First ship gives bonus 3 points. Last ship gives bonus 5 points.");
        System.out.println();
        System.out.println("Initial Grid:");
        // Make a 5x5 grid out of '~'
        String line = "~ ~ ~ ~ ~ \n";
        String grid = line.repeat(5);
        System.out.println(grid);
        // Make another 5x5 grid for hidden map
        String hiddenLine = "0 0 0 0 0 \n";
        String hiddenGrid = hiddenLine.repeat(5);
        String prompt = "";
        int shipsLocated = 0;
        int numberOfShips = 5;
        // Set a while loop to locate unique ships 5 times in hidden map
        while (shipsLocated < 5) {
            int hiddenRow = random.nextInt(5);
            int hiddenColumn = random.nextInt(5);
            int hiddenIndex = hiddenRow * 11 + hiddenColumn * 2;
            // if its unique change the '0' with '1'
            if (hiddenGrid.charAt(hiddenIndex) != '1') {
                hiddenGrid = hiddenGrid.substring(0, hiddenIndex) + "1" + hiddenGrid.substring(hiddenIndex + 1);
                shipsLocated++;
            }
        }
        // Define some integers that will change over the game
        int player1Score = 0;
        int player2Score = 0;
        int player3Score = 0;

        int round = 1;
        int currentPlayer = 0;
        int guesses = 0;
        // Set up a great while loop which contains most of the game
        // and make it end if all 5 ships are hit
        while (numberOfShips > 0) {
            System.out.println("###### Round #" + round);
            System.out.println("## Player" + ((currentPlayer % 3) + 1) + " ##");
            System.out.println("Previous Grid:");
            System.out.println(grid);
            // Get the input
            System.out.print("Guess row: ");
            int guessedRow = input.nextInt();
            System.out.print("Guess column: ");
            int guessedColumn = input.nextInt();
            guesses++;
            // Check if it is valid
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
            // Calculate the location
            int locationIndex = guessedRow * 11 + guessedColumn * 2;
            boolean isShip = false;
            // Check if hidden map has a ship in the exact spot
            if (hiddenGrid.charAt(locationIndex) == '1') {
                isShip = true;
            }
            // Check if it is already guessed
            if (grid.charAt(locationIndex) == 'X') {
                System.out.println("Already hit this ship!");
                System.out.println("Current Grid: ");
                System.out.println(grid);
            } else if (grid.charAt(locationIndex) == 'O') {
                System.out.println("Already guessed here!");
                System.out.println("Current Grid: ");
                System.out.println(grid);
            } else {
                // Change the points depending on conditions
                int pointsToAdd;

                if (isShip) {
                    System.out.println("Hit!");
                    numberOfShips--;
                    // Add points to the current player give extra points depending on the ship
                    if (numberOfShips == 4) {
                        pointsToAdd = 4;
                        System.out.println("First ship bonus! +3 points");
                        prompt += "Player " + (currentPlayer % 3 + 1) + " hit the first ship. \n";
                    } else if (numberOfShips == 0) {
                        pointsToAdd = 6;
                        System.out.println("Last ship bonus! +5 points");
                        prompt += "Player " + (currentPlayer % 3 + 1) + " hit the last ship.";
                    } else {
                        pointsToAdd = 1;
                    }
                    if (currentPlayer % 3 == 0) {
                        player1Score += pointsToAdd;
                    } else if (currentPlayer % 3 == 1) {
                        player2Score += pointsToAdd;
                    } else {
                        player3Score += pointsToAdd;
                    }
                    hiddenGrid = hiddenGrid.substring(0, locationIndex) + "0"
                            + hiddenGrid.substring(locationIndex + 1);
                    grid = grid.substring(0, locationIndex) + "X" + grid.substring(locationIndex + 1);
                    System.out.println("Current Grid:");
                    System.out.println(grid);
                    // Determine which player gets the point

                } else {
                    System.out.println("Miss!");
                    grid = grid.substring(0, locationIndex) + "O" + grid.substring(locationIndex + 1);
                    System.out.println("Current Grid:");
                    System.out.println(grid);
                    // Cut 1 points from the current player if player has a positive point value
                    if (currentPlayer % 3 == 0 && player1Score > 0) {
                        player1Score--;
                    } else if (currentPlayer % 3 == 1 && player2Score > 0) {
                        player2Score--;
                    } else if (currentPlayer % 3 == 2 && player3Score > 0) {
                        player3Score--;
                    }
                }
                currentPlayer++;

            }
            // Add 1 to round every time all players play one time
            if (currentPlayer % 3 == 0 && currentPlayer > 1) {
                round++;
            }
        }
        input.close();
        System.out.println("*********************");
        System.out.println("Game finished!");
        System.out.println("Total rounds played: " + round);
        System.out.println("Total guesses: " + guesses);
        System.out.println("Player1 score: " + player1Score + " points");
        System.out.println("Player2 score: " + player2Score + " points");
        System.out.println("Player3 score: " + player3Score + " points");
        // Fınd the max score
        int maxScore = Math.max(player1Score, Math.max(player2Score, player3Score));
        int winners = 0;
        // if there is more than one max score its a tie
        if (player1Score == maxScore) {
            winners++;
        }
        if (player2Score == maxScore) {
            winners++;
        }
        if (player3Score == maxScore) {
            winners++;
        }
        // Print out the winner
        if (winners > 1) {
            System.out.println("It's a tie!");
        } else if (player1Score == maxScore) {
            System.out.println("Winner: Player1");
        } else if (player2Score == maxScore) {
            System.out.println("Winner: Player2");
        } else {
            System.out.println("Winner: Player3");
        }
        System.out.println(prompt);

    }
}
