/**
 * Base class for bonus game objects
 */
public abstract class Bonus extends GameObject {
    protected int power; // health or score amount

    public Bonus(GameGrid grid, int row, int col, char symbol, int power) {
        super(grid, row, col, symbol);
        this.power = power;
    }

    public int getPower() {
        return this.power;
    }
}