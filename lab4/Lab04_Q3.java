/**
 * This program is the game battleship with a 5x5 grid and 3 players 5 random ships 
 * are located the player who found more ships win
 * 
 * @author Erkam Özdemir (22403374)
 * @version 23/10/2025 09:30
 */
package lab4;

import java.util.Random;
import java.util.Scanner;

public class Lab04_Q3 {
    public static void main(String[] args) {
        // Set up a scanner
        Scanner input = new Scanner(System.in);
        // Get the random generater for ship location
        Random random = new Random();
        // Print the start of the game
        System.out.println("Welcome to Battleship!");
        System.out.println("The grid is 5×5. There are 5 hidden ships.");
        System.out.println();
        System.out.println("Initial Grid:");
        // Make a 5x5 grid out of '~'
        String line = "~ ~ ~ ~ ~ \n";
        String grid = line.repeat(5);
        System.out.println(grid);
        // Make another 5x5 grid for hidden map
        String hiddenLine = "0 0 0 0 0 \n";
        String hiddenGrid = hiddenLine.repeat(5);

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
        int currentPlayer = 1;
        int guesses = 0;
        // Set a boolean to detect if game ends
        boolean gameEnds = false;
        // Set up a great while loop which contains most of the game
        // and make it end if all 5 ships are hit
        while (!gameEnds) {
            // Set a nestep while loop for each players turn
            while (currentPlayer == 1) {
                System.out.println("###### Round #" + round);
                System.out.println("## Player" + currentPlayer + " ##");
                System.out.println("Previous Grid:");
                System.out.println(grid);
                // Get the location from the user
                System.out.print("Guess row: ");
                int guessedRow = input.nextInt();
                System.out.print("Guess column: ");
                int guessedColumn = input.nextInt();
                // Add to guess count
                guesses++;
                // Check if its in the valid range, if not ask for a new guess
                while (guessedRow > 4 || guessedColumn > 4 || guessedRow < 0 || guessedColumn < 0) {
                    System.out.println("Invalid guess. Try again.");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    System.out.print("Guess row: ");
                    guessedRow = input.nextInt();
                    System.out.print("Guess column: ");
                    guessedColumn = input.nextInt();
                    // Add to guess count
                    guesses++;
                }
                // Find the location of the selected character using math
                int locationIndex = guessedRow * 11 + guessedColumn * 2;
                // Define a boolean to check if a ship is hit
                boolean isShip = false;
                // Check the hidden map
                if (hiddenGrid.charAt(locationIndex) == '1') {
                    isShip = true;
                }
                // Check if the location got hit before and respond depending on the locations
                // status of being a ship
                if (grid.charAt(locationIndex) == 'X') {
                    System.out.println("Already hit this ship!");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    // Pass to the other player
                    currentPlayer++;
                } else if (grid.charAt(locationIndex) == 'O') {
                    System.out.println("Already guessed here!");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    // Pass to the other player
                    currentPlayer++;
                } else {
                    // Change the grid that is shown to the user depending if a ship is hit or not
                    if (isShip) {
                        System.out.println("Hit!");
                        // Add to players score
                        player1Score++;
                        numberOfShips--;
                        // Change the hidden grid if the ship is hit
                        hiddenGrid = hiddenGrid.substring(0, locationIndex) + "0"
                                + hiddenGrid.substring(locationIndex + 1);
                        // Define the new grid and print out
                        grid = grid.substring(0, locationIndex) + "X" + grid.substring(locationIndex + 1);
                        System.out.println("Current Grid:");
                        System.out.println(grid);
                        // Pass to the other player
                        currentPlayer++;
                    } else {
                        System.out.println("Miss!");
                        // Define the new grid and print out
                        grid = grid.substring(0, locationIndex) + "O" + grid.substring(locationIndex + 1);
                        System.out.println("Current Grid:");
                        System.out.println(grid);
                        // Pass to the other player
                        currentPlayer++;
                    }
                    // Check if the game has ended
                    if (numberOfShips == 0) {
                        gameEnds = true;
                    }
                }
            }
            if (gameEnds) {
                break;
            }
            while (currentPlayer == 2) {
                System.out.println("###### Round #" + round);
                System.out.println("## Player" + currentPlayer + " ##");
                System.out.println("Previous Grid:");
                System.out.println(grid);
                // Get the location from the user
                System.out.print("Guess row: ");
                int guessedRow = input.nextInt();
                System.out.print("Guess column: ");
                int guessedColumn = input.nextInt();
                // Add to guess count
                guesses++;
                // Check if its in the valid range, if not ask for a new guess
                while (guessedRow > 4 || guessedColumn > 4 || guessedRow < 0 || guessedColumn < 0) {
                    System.out.println("Invalid guess. Try again.");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    System.out.print("Guess row: ");
                    guessedRow = input.nextInt();
                    System.out.print("Guess column: ");
                    guessedColumn = input.nextInt();
                    // Add to guess count
                    guesses++;
                }
                // Find the location of the selected character using math
                int locationIndex = guessedRow * 11 + guessedColumn * 2;
                // Define a boolean to check if a ship is hit
                boolean isShip = false;
                // Check the hidden map
                if (hiddenGrid.charAt(locationIndex) == '1') {
                    isShip = true;
                }
                // Check if the location got hit before and respond depending on the locations
                // status of being a ship
                if (grid.charAt(locationIndex) == 'X') {
                    System.out.println("Already hit this ship!");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    // Pass to the other player
                    currentPlayer++;
                } else if (grid.charAt(locationIndex) == 'O') {
                    System.out.println("Already guessed here!");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    // Pass to the other player
                    currentPlayer++;
                } else {
                    // Change the grid that is shown to the user depending if a ship is hit or not
                    if (isShip) {
                        System.out.println("Hit!");
                        // Add to players score
                        player2Score++;
                        numberOfShips--;
                        // Change the hidden grid if the ship is hit
                        hiddenGrid = hiddenGrid.substring(0, locationIndex) + "0"
                                + hiddenGrid.substring(locationIndex + 1);
                        // Define the new grid and print out
                        grid = grid.substring(0, locationIndex) + "X" + grid.substring(locationIndex + 1);
                        System.out.println("Current Grid:");
                        System.out.println(grid);
                        // Pass to the other player
                        currentPlayer++;
                    } else {
                        System.out.println("Miss!");
                        // Define the new grid and print out
                        grid = grid.substring(0, locationIndex) + "O" + grid.substring(locationIndex + 1);
                        System.out.println("Current Grid:");
                        System.out.println(grid);
                        // Pass to the other player
                        currentPlayer++;
                    }
                    // Check if the game has ended
                    if (numberOfShips == 0) {
                        gameEnds = true;
                    }
                }
            }
            // Leave the loop if the game has ended
            if (gameEnds) {
                break;
            }
            while (currentPlayer == 3) {
                System.out.println("###### Round #" + round);
                System.out.println("## Player" + currentPlayer + " ##");
                System.out.println("Previous Grid:");
                System.out.println(grid);
                // Get the location from the user
                System.out.print("Guess row: ");
                int guessedRow = input.nextInt();
                System.out.print("Guess column: ");
                int guessedColumn = input.nextInt();
                // Add to guess count
                guesses++;
                // Check if its in the valid range, if not ask for a new guess
                while (guessedRow > 4 || guessedColumn > 4 || guessedRow < 0 || guessedColumn < 0) {
                    System.out.println("Invalid guess. Try again.");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    System.out.print("Guess row: ");
                    guessedRow = input.nextInt();
                    System.out.print("Guess column: ");
                    guessedColumn = input.nextInt();
                    // Add to guess count
                    guesses++;
                }
                // Find the location of the selected character using math
                int locationIndex = guessedRow * 11 + guessedColumn * 2;
                // Define a boolean to check if a ship is hit
                boolean isShip = false;
                // Check the hidden map
                if (hiddenGrid.charAt(locationIndex) == '1') {
                    isShip = true;
                }
                // Check if the location got hit before and respond depending on the locations
                // status of being a ship
                if (grid.charAt(locationIndex) == 'X') {
                    System.out.println("Already hit this ship!");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    // Pass to the first player
                    currentPlayer -= 2;
                } else if (grid.charAt(locationIndex) == 'O') {
                    System.out.println("Already guessed here!");
                    System.out.println("Current Grid: ");
                    System.out.println(grid);
                    // Pass to the first player
                    currentPlayer -= 2;
                } else {
                    // Change the grid that is shown to the user depending if a ship is hit or not
                    if (isShip) {
                        System.out.println("Hit!");
                        // Add to players score
                        player3Score++;
                        numberOfShips--;
                        // Change the hidden grid if the ship is hit
                        hiddenGrid = hiddenGrid.substring(0, locationIndex) + "0"
                                + hiddenGrid.substring(locationIndex + 1);
                        // Define the new grid and print out
                        grid = grid.substring(0, locationIndex) + "X" + grid.substring(locationIndex + 1);
                        System.out.println("Current Grid:");
                        System.out.println(grid);
                        // Pass to the first player
                        currentPlayer -= 2;
                    } else {
                        System.out.println("Miss!");
                        // Define the new grid and print out
                        grid = grid.substring(0, locationIndex) + "O" + grid.substring(locationIndex + 1);
                        System.out.println("Current Grid:");
                        System.out.println(grid);
                        // Pass to the first player
                        currentPlayer -= 2;
                    }
                    // Check if the game has ended
                    if (numberOfShips == 0) {
                        gameEnds = true;
                    }
                }
            }
            // We are already out of loop if number of ships is 0 so it is not necessary to
            // command it again

            // Increase the rounds at the end of every big while loop
            round++;

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

        if (player1Score == maxScore) {
            winners++;
        }
        if (player2Score == maxScore) {
            winners++;
        }
        if (player3Score == maxScore) {
            winners++;
        }

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
