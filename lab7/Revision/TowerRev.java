public class TowerRev {

    private int health;
    private int score;
    private String symbol;

    public TowerRev(int health, int score, String symbol) {
        setHealth(health);
        this.score = score;
        this.symbol = symbol;
    }

    public boolean isStanding() {
        return (health > 0);
    }

    public void takeDamage(int damage) {
        int newHealth = this.health - damage;
        if (newHealth > 0) {
            this.health = newHealth;
        } else {
            this.health = 0;
        }
    }

    public void incrementScore(int score) {
        this.score = this.score + score;
    }

    @Override
    public String toString() {
        String tower = "Tower: symbol (" + symbol + "), health (" + health + "), score (" + score + ")";
        return tower;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        if (health > 0) {
            this.health = health;
        } else {
            this.health = 0;
        }
    }

}
