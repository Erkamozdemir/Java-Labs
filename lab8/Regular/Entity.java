/**
 * The Entity class serves as the base (superclass) for all objects that appear in the game world.
 * It defines the shared properties and behavior that all entities (Enemy, Bullet, SpaceShip) have in common
 */
public class Entity {
    /** The horizontal position of the entity on the game grid. */
    protected int x;

    /** The vertical position of the entity on the game grid. */
    protected int y;

    /** The character symbol used to represent this entity on screen. */
    protected char symbol;

    /**
     * Constructs an Entity with the given position and symbol.
     * All subclasses call this constructor to initialize shared attributes.
     *
     * @param x       horizontal position on the grid
     * @param y       vertical position on the grid
     * @param symbol  the visual character that represents the entity
     */
    public Entity(int x, int y, char symbol) {
        this.symbol = symbol;
        this.x = x;
        this.y = y;
    }

    /**
     * Checks whether this entity occupies the same position as another entity.
     * This basic collision check is inherited and reused by all subclasses.
     *
     * @param other another Entity (could be an Enemy, Bullet, or SpaceShip)
     * @return true if both entities share the same (x, y) position
     */
    public boolean collidesWith(Entity other) {
        return (other.x == this.x && other.y == this.y);
    }

    /** @return the current x-coordinate of this entity */
    public int getX() {
        return x;
    }

    /** @return the current y-coordinate of this entity */
    public int getY() {
        return y;
    }

    /** @return the character symbol representing this entity */
    public char getSymbol() {
        return symbol;
    }
}
