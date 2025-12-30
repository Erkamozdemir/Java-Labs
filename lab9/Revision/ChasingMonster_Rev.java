/**
 * Implements a monster following the hero with a certain probability on each round
 */
public class ChasingMonster_Rev extends Monster {
    protected int barrier = 67;
    public ChasingMonster_Rev(GameGrid grid, int row, int col) {
        super(grid, row, col, 'C', 10);
    }
    public void move() {
        int random = (int)(100 * Math.random());
        if (random < barrier) {
            int heroRow = this.grid.getHero().row;
            int heroCol = this.grid.getHero().col;
            if (heroRow > this.row) {
                this.row++;
            } else if (heroRow < this.row) {
                this.row--;
            }
            if (heroCol > this.col) {
                this.col++;
            } else if (heroCol < this.col) {
                this.col--;
            }
        } else {
            barrier += 2;
        }
    }

    public int getPrecedence() {
        return 4;
    }
}