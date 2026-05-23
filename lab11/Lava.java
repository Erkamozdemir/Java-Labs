import java.awt.Color;

public class Lava extends Particle {

    private int step;
    private int direction;

    public Lava() {
        super(Color.RED);
        this.step = 1;
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
        if (step == 3) {
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
            if (world.getParticle(rows + 1, cols) instanceof Water){
                world.setParticle(rows, cols, new Stone());
                world.setParticle(rows + 1, cols, new Stone());
            }
            if (world.getParticle(rows - 1, cols) instanceof Water){
                world.setParticle(rows, cols, new Stone());
                world.setParticle(rows - 1, cols, new Stone());
            }
            if (world.getParticle(rows, cols + 1) instanceof Water){
                world.setParticle(rows, cols, new Stone());
                world.setParticle(rows, cols + 1, new Stone());
            }
            if (world.getParticle(rows, cols - 1) instanceof Water){
                world.setParticle(rows, cols, new Stone());   
                world.setParticle(rows, cols - 1, new Stone());
            }
                step -= 2;
        } else {
            step++;
        }
    }

}
