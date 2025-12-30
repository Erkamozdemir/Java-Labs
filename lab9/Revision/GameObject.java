/**
 * Base class for all game objects including the hero
 */
public abstract class GameObject {
    protected GameGrid grid; // grid on which this game object is on
    protected int row, col; // coordinates on grid
    protected char symbol; // symbol with which we display this game object

    public GameObject(GameGrid grid, int row, int col, char symbol) {
        this.grid = grid;
        this.row = row;
        this.col = col;
        this.symbol = symbol;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public char getSymbol() { return symbol; }
    
    // returns the distinct precendence for a specific game object type
    public abstract int getPrecedence(); 

    // some game objects move in each round
    public abstract void move();

    // by default, on every collision one object gets destroyed, other (one with higher
    // precedence) survives
    public void handleCollision(GameObject obj) {
        if (doesSurviveOnCollision(this, obj)) {
            this.grid.removeGameObject(obj);
        }
        else {
            this.grid.removeGameObject(this);
        }
    }

    // checks which of two non-hero game objects survives on collision; returns true
    // if obj survives and obj2 gets destroyed, false otherwise. 
    public static boolean doesSurviveOnCollision(GameObject obj, GameObject obj2) {
        return obj.getPrecedence() > obj2.getPrecedence();
    }
}
