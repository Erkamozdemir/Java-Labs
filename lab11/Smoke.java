import java.awt.Color;

public class Smoke extends Particle {

    private int direction;
    private int age;
    private static final int MAX_AGE = 100;

    public Smoke() {
        super(Color.DARK_GRAY);
        this.direction = Math.random() < 0.5 ? 1 : -1;
        this.age = 0;
    }

    private void tryMove(World world, int oldRow, int oldCol, int newRow, int newCol) {
        if (world.isEmpty(newRow, newCol)) {
            world.setParticle(newRow, newCol, this);
            world.setParticle(oldRow, oldCol, null);
            setUpdated(true);
        }
    }

    public void update(World world, int rows, int cols) {
        age++;
        if (age == MAX_AGE) {
            world.setParticle(rows, cols, null);
            return;
        }
        int value = 50 + (int) ((255 - 50) * ((double) age / MAX_AGE));
        setColor(new Color(value, value, value));
        double riseProb = 1.0 - (0.8 * ((double) age / MAX_AGE));
        if (Math.random() < riseProb) {
            tryMove(world, rows, cols, rows - 1, cols);
            if (!isUpdated()) {
                if (Math.random() < 0.5) {
                    tryMove(world, rows, cols, rows - 1, cols - 1);
                    if (!isUpdated()) {
                        tryMove(world, rows, cols, rows - 1, cols + 1);
                    }
                } else {
                    tryMove(world, rows, cols, rows - 1, cols + 1);
                    if (!isUpdated()) {
                        tryMove(world, rows, cols, rows - 1, cols - 1);
                    }
                }
            }
        }
        double spreadProb = 0.2 + (0.5 * ((double) age / MAX_AGE));
        if (!isUpdated() && Math.random() < spreadProb) {
            tryMove(world, rows, cols, rows, cols + direction);
            if (!isUpdated()) {
                direction *= -1;
            }
        }
    }
}
