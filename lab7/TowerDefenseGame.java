package lab7;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TowerDefenseGame {

    public final static int ROWS = 6;
    public final static int COLUMNS = 10;
    public final static int INITIAL_HEALTH = 15;
    public final static int ENEMY_WAVES = 10;
    public final static int MAX_ENEMY_SPAWN_RATE = 4;
    public final static String VICTORY_MESSAGE = "Victory! Your tower stands strong";
    public final static String DEFEAT_MESSAGE = "Defeat! Your tower has fallen!";

    public final static String TOWER_SYMBOL = "🏰";
    public final static String ENEMY_SYMBOL = "👾";
    public final static String EMPTY_SYMBOL = "⬜";

    private static GameManager gameManager;
    private static Scanner scanner;
    public static Random random;

    public static void main(String[] args) {
        initializeVariables();
        playGame();
        handleGameEnding();
    }

    private static void initializeVariables() {
        random = new Random();
        scanner = new Scanner(System.in);
        gameManager = new GameManager();
    }

    private static void playGame() {
        while (!gameManager.isGameOver()) {
            renderGraphics();
            handleHit();
            gameManager.handleTowerDamage();

            if (gameManager.hasEnemyWavesLeft()) {
                gameManager.addNextEnemyWave();
            }
        }
    }

    private static void handleGameEnding() {
        renderGraphics();

        Tower tower = gameManager.getTower();
        System.out.println(tower.isStanding() ? VICTORY_MESSAGE : DEFEAT_MESSAGE);
        System.out.println("Final Score: " + tower.getScore());

        scanner.close();
    }

    private static void renderGraphics() {
        System.out.println("\n" + "=".repeat(2 * COLUMNS + 3));
        renderGameMap();
        renderGameInformation();
        System.out.println("=".repeat(2 * COLUMNS + 3));
    }

    private static void renderGameMap() {
        ArrayList<EnemyWave> enemyWaves = gameManager.getEnemyWaves();

        System.out.print("    ");
        for (int i = 0; i < COLUMNS; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int row = 0; row < ROWS; row++) {
            System.out.print(row + " ");
            System.out.print((row == ROWS / 2) ? TOWER_SYMBOL : EMPTY_SYMBOL);

            for (int col = 0; col < COLUMNS; col++) {
                String[] enemyWave = enemyWaves.get(col).getEnemyWave();
                System.out.print(enemyWave[row]);
            }
            System.out.println();
        }
    }

    private static void renderGameInformation() {
        Tower tower = gameManager.getTower();
        int wavesRemaining = gameManager.getEnemyWavesLeft();
        System.out.println(tower + " | Enemy Waves Left: " + wavesRemaining);
    }

    private static void handleHit() {
        int columnIndex = getValidInput("Enter column index (0 to " + (COLUMNS - 1) + "): ", 0, COLUMNS - 1);
        int rowIndex = getValidInput("Enter row index (0 to " + (ROWS - 1) + "): ", 0, ROWS - 1);

        gameManager.hitEnemy(columnIndex, rowIndex);
    }

    private static int getValidInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

}
