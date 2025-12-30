/**
 * Implements the hero (the player) of the game. Can move in one of four
 * directions in each round. Aims to reach the target location on the grid.
 */
public class Hero extends GameObject {
    private int health = 100;
    private int score = 0;
    private char direction;

    public Hero(GameGrid grid, int row, int col) {
        super(grid, row, col, 'P');
    }

    public boolean isAlive() {
        return (health > 0);
    }

    public boolean isTargetReached() {
        return grid.isAtTarget(row, col);
    }

    public void takeDamage(int damage) {
        this.health = this.health - damage;
    }

    public void addHealth(int healthAdd) {
        if (this.health + healthAdd <= 90) {
            this.health = this.health + healthAdd;
        }
    }

    public void increaseScore(int scoreAdd) {
        this.score = this.score + scoreAdd;
    }

    @Override
    public void move() {
        switch (direction) {
            case 'w':
                if (row > 0) {
                    row--;
                }
                break;
            case 'a':
                if (col > 0) {
                    col--;
                }
                break;
            case 's':
                if (row < 5) {
                    row++;
                }
                break;
            case 'd':
                if (col < 9) {
                    col++;
                }
                break;

            default:
                break;
        }
    }

    @Override
    public int getPrecedence() {
        return -1;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    @Override
    public void handleCollision(GameObject obj) {
        if (obj instanceof HealthBonus) {
            this.grid.removeGameObject(obj);
            this.addHealth(((HealthBonus) obj).getPower());
        } else if (obj instanceof ScoreBonus) {
            this.grid.removeGameObject(obj);
            this.increaseScore(((ScoreBonus) obj).getPower());
        } else if (obj instanceof Monster) {
            this.takeDamage(((Monster) obj).getDamage());
            if (obj instanceof RandomMonster) {
                this.grid.removeGameObject(obj);
            }
        }
    }

    public int getHealth() {
        return this.health;
    }

    public int getScore() {
        return this.score;
    }
}