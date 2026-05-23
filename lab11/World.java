public class World {

    private Particle[][] particles;
    private int cols;
    private int rows;

    public World(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        particles = new Particle[cols][rows];
    }

    public boolean isRight(int rows, int cols) {
        return rows <= this.rows - 1 && cols <= this.cols - 1 && cols >= 0 && rows >= 0;
    }

    public Particle getParticle(int rows, int cols) {
        if (isRight(rows, cols)) {
            return particles[cols][rows];
        }
        return null;
    }

    public void setParticle(int rows, int cols, Particle particle) {
        if (isRight(rows, cols)) {
            particles[cols][rows] = particle;
        }
    }

    public boolean isEmpty(int rows, int cols) {
        if (isRight(rows, cols)) {
            return particles[cols][rows] == null;
        }
        return false;
    }
}
