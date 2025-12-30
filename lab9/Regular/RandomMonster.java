import java.util.Random;

/**
 * Implements a monster that moves randomly on the grid
 */
public class RandomMonster extends Monster {
    public RandomMonster(GameGrid grid, int row, int col) {
        super(grid, row, col, 'R', 10);
    }

    public void move() {
        Random random = new Random();
        int randomNumber = random.nextInt(0, 4);
        switch (randomNumber) {
            case 0:// w
            if (this.row > 0) {
                this.row--;
            }
            break;
            case 1:// a
            if (this.col > 0) {
                this.col--;
            }
            break;
            case 2:// s
            if (this.row < 5) {
                this.row++;
            }
            break;
            case 3:// d
            if (this.col < 9) {
                this.col++;
            }
            break;
            default:
                break;
        }
    }

    public int getPrecedence() {
        return 2;
    }
}