/**
 * Increments the score on collision with the hero
 */
public class ScoreBonus extends Bonus {
    public int getPrecedence(){
        return 1;
    }
    public ScoreBonus(GameGrid grid, int row, int col){
        super(grid, row, col, 'S', 1);
    }

    public void move(){
    }
}