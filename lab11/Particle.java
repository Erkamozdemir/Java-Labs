import java.awt.Color;

public abstract class Particle {

    private Color color;
    private boolean updated;

    public Particle(Color color) {
        this.color = color;
        this.updated = false;
    }

    public abstract void update(World world, int x, int y);

    public Color getColor() {
        return color;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}