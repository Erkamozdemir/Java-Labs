/**
 * Base class for monster game objects
 */
public abstract class Monster extends GameObject {
    protected int damage; // amount by which health is decreased on hitting hero

    public Monster(GameGrid grid, int row, int col, char symbol, int damage) {
        super(grid, row, col, symbol);
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }
}