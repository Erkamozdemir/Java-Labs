package lab7;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {

    private Tower tower;
    private ArrayList<EnemyWave> enemyWaves;
    private int enemyWavesLeft;

    public GameManager() {
        tower = new Tower(TowerDefenseGame.INITIAL_HEALTH, 0, TowerDefenseGame.TOWER_SYMBOL);
        enemyWaves = new ArrayList<>();
        enemyWavesLeft = TowerDefenseGame.ENEMY_WAVES;
        generateEmptyMap();
        addNextEnemyWave();
    }

    private void generateEmptyMap() {
        for (int i = 0; i < TowerDefenseGame.COLUMNS; i++) {
            enemyWaves.add(new EnemyWave(0));
        }
    }

    private EnemyWave generateNextEnemyWave(boolean hasEnemies) {
        if (!hasEnemies) {
            return new EnemyWave(0);
        }
        Random random = TowerDefenseGame.random;
        int enemyNumber = random.nextInt(1, TowerDefenseGame.MAX_ENEMY_SPAWN_RATE + 1);
        return new EnemyWave(enemyNumber);
    }

    public int getNumberOfEnemies(int index) {
        if (index < 0 || index >= enemyWaves.size()) {
            return 0;
        }
        return enemyWaves.get(index).getNumberOfEnemies();
    }

    public void addNextEnemyWave() {
        enemyWaves.remove(0);
        boolean wavesRemaining = (enemyWavesLeft > 0);
        EnemyWave newEnemies = generateNextEnemyWave(wavesRemaining);
        enemyWaves.add(newEnemies);
        if (wavesRemaining) {
            enemyWavesLeft--;
        }
    }

    public int hitEnemy(int columnIndex, int rowIndex) {
        if (columnIndex < 0 || columnIndex >= enemyWaves.size()) {
            return 0;
        }
        EnemyWave selectedWave = enemyWaves.get(columnIndex);
        int hit = selectedWave.hitEnemy(rowIndex);
        if (hit == 1) {
            tower.incrementScore(1);
        }
        return hit;
    }

    public Tower getTower() {
        return tower;
    }

    public boolean hasEnemyWavesLeft() {
        return (enemyWavesLeft > 0);
    }

    public boolean isGameOver() {
        return !tower.isStanding() || (!hasEnemyWavesLeft());
    }

    public void handleTowerDamage() {
        EnemyWave front = enemyWaves.get(0);
        int damage = front.getNumberOfEnemies();
        tower.takeDamage(damage);
    }

    public ArrayList<EnemyWave> getEnemyWaves() {
        return enemyWaves;
    }

    public int getEnemyWavesLeft() {
        return enemyWavesLeft;
    }

}
