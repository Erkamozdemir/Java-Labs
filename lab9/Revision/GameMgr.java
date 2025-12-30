import java.util.Scanner;

/**
 * Implements Escape the Maze as defined in lab document
 */
public class GameMgr {
    // some constants
    public static final int GRID_WIDTH = 10;
    public static final int GRID_HEIGHT = 6;
    public static final int INITIAL_ENEMY_COUNT = 5;
    public static void main(String[] args) {
        // create game grid
        GameGrid grid = new GameGrid(GRID_WIDTH, GRID_HEIGHT);

        // initial setup of monsters & enemies
        for (int i = 0; i < INITIAL_ENEMY_COUNT; i++) {
            grid.spawnGameObject();
        }

        Hero hero = grid.getHero();
        Scanner in = new Scanner(System.in);

        // draw initial state
        grid.draw();

        // main game loop
        while (hero.isAlive() && !hero.isTargetReached()) {
            System.out.println("Health: " + hero.getHealth() + " | Score: " + hero.getScore());
            System.out.print("Move (w/a/s/d/q): ");
            char dir = in.next().charAt(0);
            if (dir == 'q') {
                System.out.println("Quitting game...");
                break;
            }
            hero.setDirection(dir);
            grid.update();
            if (Math.random() < 0.25) {
                grid.spawnGameObject();
            }
            grid.draw();
        }

        // report ending
        if (hero.isTargetReached()) {
            System.out.println(GameGrid.WINNER_SYMBOL + "You win!");
        }
        else if (!hero.isAlive()) {
            System.out.println(GameGrid.LOSER_SYMBOL + "You lost!");
        }

        in.close();
    }
}
