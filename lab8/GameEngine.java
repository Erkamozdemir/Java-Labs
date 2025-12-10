import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The GameEngine class controls the main flow of the game.
 * It manages user input, updates the game state, and triggers rendering each turn.
 *
 * The class also defines global constants such as grid size, entity symbols,
 * and initial spaceship health.
 */
public class GameEngine {
    /** Symbol used to display an enemy. */
    public static final char ENEMY_SYMBOL = 'M';
    /** Symbol used to display the player’s spaceship. */
    public static final char SPACE_SHIP_SYMBOL = '^';
    /** Symbol used to display bullets. */
    public static final char BULLET_SYMBOL = '|';
    /** Number of enemies spawned per turn. */
    public static final int ENEMY_PER_ROW = 3;
    /** Initial health value of the spaceship. */
    public static final int SPACE_SHIP_HEALTH = 3;
    /** Width of the game grid. */
    public static final int WIDTH = 24;
    /** Height of the game grid. */
    public static final int HEIGHT = 12;

    private final GameField field;
    private final Scanner in = new Scanner(System.in);

    private final SpaceShip spaceShip;
    private final List<Enemy> enemies;
    private final List<Bullet> bullets;

    /**
     * Initializes the game engine by creating the spaceship,
     * and empty lists for enemies and bullets.
     * Also sets up the game field that manages these entities.
     */
    public GameEngine() {
        this.enemies = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.spaceShip = new SpaceShip(WIDTH / 2, HEIGHT - 1, SPACE_SHIP_HEALTH);
        this.field = new GameField(spaceShip, enemies, bullets);
    }

    /**
     * Starts the main game loop.
     * Handles user input, updates the game state, and redraws the screen each turn.
     * The loop terminates when the spaceship’s health reaches zero or the player quits.
     */
    public void run() {
        Drawer.printControls();

        boolean running = true;
        while (running) {
            Drawer.render(spaceShip, enemies, bullets, field.getScore());

            if (spaceShip.getHealth() <= 0) {
                System.out.println("\nGAME OVER!");
                running = false;
                continue;
            }

            System.out.print("Move (A/D), Shoot (S), Wait (Enter), Quit (Q): ");
            String input = in.nextLine().trim().toUpperCase();
            if (input.isEmpty()) {
                input = " ";
            }

            char command = input.charAt(0);
            if (command == 'Q') {
                running = false;
                continue;
            }

            update(command);
        }

        System.out.println("Final Score: " + field.getScore());
    }

    /**
     * Processes a single turn of the game based on the player’s command.
     * Updates all entities, checks collisions, and spawns new enemies.
     *
     * @param command  the player’s input command (A, D, S, or Enter)
     */
    private void update(char command) {
        switch (command) {
            case 'A':
                spaceShip.moveLeft();
                break;
            case 'D':
                spaceShip.moveRight();
                break;
            case 'S':
                bullets.add(spaceShip.shoot());
                break;
            default:
                break;
        }

        for (Bullet b : bullets) {
            b.update();
        }

        int collusionCount = field.checkBulletCollusion();
        if (collusionCount > 0) {
            System.out.println("You killed " + collusionCount + " monsters!");
        }

        for (Enemy e : enemies) {
            e.update();
        }

        if (field.checkSpaceShipCollusion()) {
            System.out.println("You were hit by a monster!");
        }

        field.spawnEnemies();
        enemies.removeIf(e -> e.getY() >= HEIGHT - 1);
        bullets.removeIf(b -> b.getY() < 0);
    }
}