import java.util.*;

/**
 * Implements a grid for the maze in our game.
 */
public class GameGrid {
    // constants
    public static String HERO_SYMBOL = "😍";
    public static String RANDOM_MONSTER_SYMBOL = "👿";
    public static String CHASING_MONSTER_SYMBOL = "👹";
    public static String HEALTH_BONUS_SYMBOL = "💛";
    public static String SCORE_BONUS_SYMBOL = "💰";
    public static String FINISH_SYMBOL = "🏁";
    public static String WINNER_SYMBOL = "🥳";
    public static String LOSER_SYMBOL = "🥴️";

    // instance variables
    private int width, height; // dimensions of grid: # of columns & rows, resp.
    private ArrayList<GameObject> objects; // first one is always the hero
    private ArrayList<GameObject> queuedObjectsForRemoval;

    public GameGrid(int width, int height) {
        this.width = width;
        this.height = height;
        this.objects = new ArrayList<>();
        this.objects.add(new Hero(this, 0, 0));
        this.queuedObjectsForRemoval = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Hero getHero() {
        return (Hero) this.objects.get(0);
    }

    public boolean isAtTarget(int row, int col) {
        if (row == height - 1 && col == width - 1) {
            return true;
        }
        return false;
    }

    public void removeGameObject(GameObject obj) {
        this.queuedObjectsForRemoval.add(obj);
    }

    private void addGameObject(GameObject obj) {
        objects.add(obj);
    }

    public void spawnMonster(int row, int col) {
        Monster monster;
        if (Math.random() < 0.5) {
            monster = new RandomMonster(this, row, col);
        } else {
            monster = new ChasingMonster(this, row, col);
        }
        addGameObject(monster);
    }

    public void spawnBonus(int row, int col) {
        Bonus bonus;
        if (Math.random() < 0.5) {
            bonus = new HealthBonus(this, row, col);
        } else {
            bonus = new ScoreBonus(this, row, col);
        }
        addGameObject(bonus);
    }

    public void spawnGameObject() {
        int row, col;

        do {
            row = (int) (Math.random() * height);
            col = (int) (Math.random() * width);
        } while (isOccupied(row, col));

        if (Math.random() < 0.5) {
            spawnMonster(row, col);
        } else {
            spawnBonus(row, col);
        }
    }

    public boolean isOccupied(int row, int col) {
        for (GameObject obj : objects) {
            if (obj.getRow() == row && obj.getCol() == col) {
                return true;
            }
        }
        return false;
    }

    public void draw() {
    System.out.println("---------------------------------------------------");
    for (int r = 0; r < height; r++) {
        System.out.print("|");
        for (int c = 0; c < width; c++) {
            ArrayList<GameObject> foundObjects = new ArrayList<>();
            for (GameObject obj : objects) {
                if (obj.getRow() == r && obj.getCol() == c) {
                    foundObjects.add(obj);
                }
            }
            if (foundObjects.isEmpty()) {
                System.out.print("    ");
            } else if (foundObjects.size() == 1) {
                System.out.print(" " + turnPretty(foundObjects.get(0).getSymbol()) + " ");
            } else if (foundObjects.size() == 2) {
                System.out.print(turnPretty(foundObjects.get(0).getSymbol()) + 
                               turnPretty(foundObjects.get(1).getSymbol()));
            } else { 
                System.out.print(turnPretty(foundObjects.get(0).getSymbol()) + 
                               turnPretty(foundObjects.get(1).getSymbol()) + 
                               turnPretty(foundObjects.get(2).getSymbol()));
            }
            System.out.print("|");
        }
        System.out.println();
        System.out.println("---------------------------------------------------");
    }
}

    public static String turnPretty(char c) {
        if (c == 'P')
            return HERO_SYMBOL;
        else if (c == 'R')
            return RANDOM_MONSTER_SYMBOL;
        else if (c == 'C')
            return CHASING_MONSTER_SYMBOL;
        else if (c == 'H')
            return HEALTH_BONUS_SYMBOL;
        else if (c == 'S')
            return SCORE_BONUS_SYMBOL;
        return Character.toString(c);
    }

    public void update() {
        for (GameObject obj : objects) {
            obj.move();
        }
        checkAndHandleCollisions();        
        removeQueuedObjects();
    }

    public void checkAndHandleCollisions() {
        for (int i = 0; i < objects.size(); i++) {
                GameObject obj1 = objects.get(i);          
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject obj2 = objects.get(j);
                if (obj1.row == obj2.row && obj1.col == obj2.col) {
                    if (obj1 instanceof Hero) {
                        obj1.handleCollision(obj2);
                    } else if (obj2 instanceof Hero) {
                        obj2.handleCollision(obj1);
                    } else {
                        obj1.handleCollision(obj2);
                    }
                }
            }
        }
    }

    public void removeQueuedObjects() {
        for (GameObject obj : queuedObjectsForRemoval) {
            this.objects.remove(obj);
        }
        queuedObjectsForRemoval.clear();
    }
}