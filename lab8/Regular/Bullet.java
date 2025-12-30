/**
 * The Bullet class represents projectiles fired by the player's spaceship.
 * It extends the Entity class, meaning it inherits the position (x, y) and
 * symbol properties common to all game objects. Bullets move upward each turn
 * and can collide with enemies to remove them from the field.
 */
public class Bullet extends Entity {
    /**
     * Constructs a Bullet object at the given (x, y) position.
     * Calls the superclass (Entity) constructor to set position and symbol.
     *
     * @param x  the horizontal position of the bullet
     * @param y  the vertical position of the bullet
     */
    public Bullet(int x, int y) {
        super(x, y, GameEngine.BULLET_SYMBOL);
    }

    /**
     * Moves the bullet one cell upward each turn.
     * In this coordinate system, moving up means decreasing the y-value.
     * */
    public void update() {
        y--;
    }

    /**
     * Determines whether this bullet collides with another Entity.
     * A collision occurs if both objects share the same x-coordinate,
     * and their y-coordinates are close (difference ≤ 1). This allows
     * the bullet to "hit" a moving enemy even if they pass by quickly.
     *
     * @param other  another Entity (such as an Enemy)
     * @return true if a collision is detected; false otherwise
     */
    @Override
    public boolean collidesWith(Entity other) {
        return (other.x == this.x && (this.y - other.y) < 1);
    }
}
