/**
 * The Enemy class represents hostile objects that move downward toward the player's spaceship.
 * Each Enemy is a game entity that shares the same basic properties (x, y position and symbol)
 * through inheritance from the Entity superclass.
 *
 * Game behavior:
 *  - Every turn, enemies move one step downward (increase in y).
 *  - If they collide with the player's spaceship, the player loses 1 health point.
 */
public class Enemy_Rev extends Entity_Rev {

    /**
     * Constructs an Enemy object at the specified (x, y) location.
     * The enemy symbol is defined in the GameEngine class.
     *
     * @param x  the horizontal position of the enemy
     * @param y  the vertical position of the enemy
     */
    public Enemy_Rev(int x, int y) {
        super(x, y, GameEngine_Rev.ENEMY_SYMBOL);
    }

    /**
     * Moves the enemy one step downward on the grid.
     * Increasing the y-coordinate simulates falling toward the spaceship.
     */
    public void update() {
        y++;
    }

    /**
     * Checks whether this enemy has collided with the player's spaceship.
     * If a collision occurs, the spaceship’s health decreases by 1.
     *
     * @param ship  the player’s SpaceShip object
     * @return true if the enemy collides with the spaceship; false otherwise
     */
    public boolean attack(SpaceShip_Rev ship) {
        if (collidesWith(ship)) {
            ship.setHealth(ship.getHealth() - 1);
            return true;
        } else {
            return false;
        }
        
         
    }
}
