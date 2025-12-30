/**
 * The SpaceShip class represents the player-controlled ship in the game.
 * It extends the Entity superclass, inheriting position (x, y) and symbol,
 * while adding new properties and behaviors unique to the player:
 * - Health (for tracking damage)
 * - Shooting (creates Bullet objects)
 * - Movement (left and right within screen boundaries)
 */
public class SpaceShip extends Entity {
    /** The number of health points the spaceship currently has. */
    private int health;

    /**
     * Constructs a SpaceShip at the given position with a specified health value.
     * Uses the GameEngine constant SPACE_SHIP_SYMBOL for its appearance.
     *
     * @param x       horizontal position on the grid
     * @param y       vertical position on the grid
     * @param health  initial health value of the spaceship
     */
    public SpaceShip(int x, int y, int health) {
        super(x, y, GameEngine.SPACE_SHIP_SYMBOL);
        this.health = health;
    }

    /**
     * Fires a bullet from the spaceship's current position.
     * Returns a new Bullet object that will move upward on the grid.
     *
     * @return a new Bullet fired by the spaceship
     */
    public Bullet shoot() {
        return new Bullet(x, y);
    }

    /**
     * Moves the spaceship one cell to the left,
     * ensuring it stays within the left boundary of the game area.
     */
    public void moveLeft() {
        if(x > 0){
            x--;
        }
    }

    /**
     * Moves the spaceship one cell to the right,
     * ensuring it stays within the right boundary of the game area.
     */
    public void moveRight() {
        if (x < GameEngine.WIDTH - 1) {
            x++;
        }
    }

    /** @return the current health of the spaceship */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the spaceship’s health to a new value.
     * Used when the player takes damage or is healed.
     *
     * @param health the new health value to assign
     */
    public void setHealth(int health) {
        this.health = health;
    }
}
