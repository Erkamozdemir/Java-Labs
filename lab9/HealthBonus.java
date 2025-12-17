/**
 * Implements a health bonus which increases hero's health on collision
 */
public class HealthBonus extends Bonus {
    public int getPrecedence(){
        return 3;
    }
    public HealthBonus(GameGrid grid, int row, int col){
        super(grid, row, col, 'H', 10);
    }

    public void move(){
    }
}