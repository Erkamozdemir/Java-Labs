import java.awt.Color;

public class Sand extends Particle {

    public Sand() {
        super(Color.YELLOW);
    }

    private void tryMove(World world, int oldRow, int oldCol, int newRow, int newCol) {
        if (world.isEmpty(newRow, newCol)) {
            world.setParticle(newRow, newCol, this);
            world.setParticle(oldRow, oldCol, null);
            setUpdated(true);
        }
        if (world.getParticle(newRow, newCol) instanceof Water || world.getParticle(newRow, newCol) instanceof Smoke) {
            Particle temp = world.getParticle(newRow, newCol);
            world.setParticle(newRow, newCol, this);
            world.setParticle(oldRow, oldCol, temp);
            setUpdated(true);
        }
    }

    public void update(World world, int rows, int cols) {
        tryMove(world, rows, cols, rows + 1, cols);
        if (!isUpdated()) {
            int random = (int) (Math.random() * 2);
            if (random == 0) {
                tryMove(world, rows, cols, rows + 1, cols - 1);
                if (!isUpdated()) {
                    tryMove(world, rows, cols, rows + 1, cols + 1);
                }
            } else {
                tryMove(world, rows, cols, rows + 1, cols + 1);
                if (!isUpdated()) {
                    tryMove(world, rows, cols, rows + 1, cols - 1);
                }
            }
        }
    }
}
