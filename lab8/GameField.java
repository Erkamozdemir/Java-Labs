import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The GameField class manages the core gameplay logic and keeps track of all
 * active entities in the game (SpaceShip, Enemies, and Bullets).
 * It acts as a container for the game's state, handling spawning, movement,
 * and collisions.
 *
 * Responsibilities:
 *  - Spawning new enemies
 *  - Detecting and handling collisions
 *  - Tracking the player’s score
 */
public class GameField {
    /** Reference to the player’s spaceship (controlled by the user). */
    private final SpaceShip spaceShip;

    /** List of all active enemy objects currently on the field. */
    private final List<Enemy> enemies;

    /** List of all bullets currently fired by the spaceship. */
    private final List<Bullet> bullets;

    /** Player’s score, increased whenever an enemy is destroyed. */
    private int score;

    /**
     * Constructs a new GameField with the player’s ship and shared entity lists.
     *
     * @param spaceShip  the player’s spaceship
     * @param enemies    list of all enemy objects
     * @param bullets    list of all bullet objects
     */
    public GameField(SpaceShip spaceShip, List<Enemy> enemies, List<Bullet> bullets) {
        this.spaceShip = spaceShip;
        this.enemies = enemies;
        this.bullets = bullets;
        this.score = 0;
    }

    /**
     * Spawns a fixed number of new enemies at the top of the grid each turn.
     * Enemies are placed in random horizontal positions, but never stacked
     * directly on top of each other in the same column.
     */
    public void spawnEnemies() {
        ArrayList<Integer> enemyXCoordinates = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < GameEngine.ENEMY_PER_ROW; i++){
            int enemyLocation = random.nextInt(0, GameEngine.WIDTH + 1);
            if (!enemyXCoordinates.contains(enemyLocation)) {
                Enemy enemy = new Enemy(enemyLocation, 0);
                enemies.add(enemy);
                enemyXCoordinates.add(enemyLocation);
            }
        }
    }

    /**
     * Checks whether any enemy has collided with the player's spaceship.
     * If a collision is detected:
     *  - The enemy is removed.
     *  - The spaceship loses one health point.
     *
     * @return true if a collision occurred; false otherwise
     */
    public boolean checkSpaceShipCollusion() {
        for(int i = enemies.size() - 1; i >= 0; i--){
            Enemy enemy = enemies.get(i);
            if(enemy.attack(spaceShip)){
                enemies.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for bullet–enemy collisions.
     * If a bullet hits an enemy:
     *  - Both are removed from the field.
     *  - The player’s score increases by one.
     *
     * @return number of enemies destroyed this turn
     */
    public int checkBulletCollusion() {
        int destroyedEnemies = 0;
        for(int i = bullets.size() - 1; i >= 0; i--){
            Bullet bullet = bullets.get(i);
            for(int j = enemies.size() - 1; j >= 0; j--){
                Enemy enemy = enemies.get(j);
                if (bullet.collidesWith(enemy)) {
                    bullets.remove(i);
                    enemies.remove(j);
                    score++;
                    destroyedEnemies++;
                    break;
                }
            }
        }
        return destroyedEnemies;
    }

    /** @return the current player score. */
    public int getScore() {
        return score;
    }
}
