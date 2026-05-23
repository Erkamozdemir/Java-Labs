import java.awt.Color;

public class Water extends Particle {

    private int direction;

    public Water() {
        super(Color.BLUE);
        this.direction = Math.random() < 0.5 ? 1 : -1;
    }

    private void tryMove(World world, int oldRow, int oldCol, int newRow, int newCol) {
        if (world.isEmpty(newRow, newCol)) {
            world.setParticle(newRow, newCol, this);
            world.setParticle(oldRow, oldCol, null);
            setUpdated(true);
        }
    }

    public void update(World world, int rows, int cols) {
        tryMove(world, rows, cols, rows + 1, cols);
        if (!isUpdated()) {
            if (Math.random() < 0.5) {
                tryMove(world, rows, cols, rows + 1, cols + 1);
                if (!isUpdated()) {
                    tryMove(world, rows, cols, rows + 1, cols - 1);
                }
            } else {
                tryMove(world, rows, cols, rows + 1, cols - 1);
                if (!isUpdated()) {
                    tryMove(world, rows, cols, rows + 1, cols + 1);
                }
            }
        }
        if (!isUpdated()) {
            tryMove(world, rows, cols, rows, cols + direction);
        }
        if (!isUpdated()) {
            direction *= -1;
        }
    }
}
