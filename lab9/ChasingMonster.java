/**
 * Implements a monster following the hero with a certain probability on each round
 */
public class ChasingMonster extends Monster {
    public ChasingMonster(GameGrid grid, int row, int col) {
        super(grid, row, col, 'C', 10);
    }

    public void move() {
        int random = (int)(3 * Math.random());
        if (random != 0) {
            int random2 = (int)(2 * Math.random());
            if (random2 == 0) {
                int heroCol = this.grid.getHero().col;
                if (this.col > heroCol) {
                    if (this.col > 0) {
                    this.col--;                        
                    }
                } else if (this.col < heroCol) {
                    if (this.col < 9) {
                        this.col++;
                    }
                }
            } else {
                int heroRow = this.grid.getHero().row;
                if (this.row > heroRow) {
                    if (this.row > 0) {
                    this.row--;                        
                    }
                } else if(this.row < heroRow) {
                    if (this.row < 5) {
                        this.row++;
                    }
                }
            }
        }
    }

    public int getPrecedence() {
        return 4;
    }
}