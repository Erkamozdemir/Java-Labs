import java.util.ArrayList;
import java.util.Random;

public class GameManagerRev {

    private TowerRev tower;
    private ArrayList<EnemyWaveRev> enemyWaves;
    private int enemyWavesLeft;

    public GameManagerRev() {
        tower = new TowerRev(TowerDefenseGameRev.INITIAL_HEALTH, 0, TowerDefenseGameRev.TOWER_SYMBOL);
        enemyWaves = new ArrayList<>();
        enemyWavesLeft = TowerDefenseGameRev.ENEMY_WAVES;
        generateEmptyMap();
        addNextEnemyWave();
    }

    private void generateEmptyMap() {
        for (int i = 0; i < TowerDefenseGameRev.COLUMNS; i++) {
            enemyWaves.add(new EnemyWaveRev(0));
        }
    }

    private EnemyWaveRev generateNextEnemyWave(boolean hasEnemies) {
        if (!hasEnemies) {
            return new EnemyWaveRev(0);
        }
        Random random = TowerDefenseGameRev.random;
        int enemyNumber = random.nextInt(1, TowerDefenseGameRev.MAX_ENEMY_SPAWN_RATE + 1);
        return new EnemyWaveRev(enemyNumber);
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
        EnemyWaveRev newEnemies = generateNextEnemyWave(wavesRemaining);
        enemyWaves.add(newEnemies);
        if (wavesRemaining) {
            enemyWavesLeft--;
        }
    }

    public int hitEnemy(int columnIndex, int rowIndex) {
        if (columnIndex < 0 || columnIndex >= enemyWaves.size()) {
            return 0;
        }
        EnemyWaveRev selectedWave = enemyWaves.get(columnIndex);
        int hit = selectedWave.hitEnemy(rowIndex);
        if (hit == 1) {
            tower.incrementScore(1);
        } else if (hit == -1) {
            int count = handleShockwave(columnIndex, rowIndex);
            tower.incrementScore(count);
        }
        return hit;
    }

    private int handleShockwave(int columnIndex, int rowIndex) {
        int count = 0;
        for (int c = columnIndex - 1; c <= columnIndex + 1; c++) {
            EnemyWaveRev selectedWave;
            if (c > enemyWaves.size() - 1) {
                selectedWave = enemyWaves.get(c - 1);
            }else {
                selectedWave = enemyWaves.get(c);
            }
            for (int r = rowIndex - 1; r <= rowIndex + 1; r++) {
                int hit = selectedWave.hitEnemy(r);
                if (hit == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public TowerRev getTower() {
        return tower;
    }

    public boolean hasEnemyWavesLeft() {
        return (enemyWavesLeft > 0);
    }

    public boolean isGameOver() {
        return !tower.isStanding() || (!hasEnemyWavesLeft());
    }

    public void handleTowerDamage() {
        EnemyWaveRev front = enemyWaves.get(0);
        int damage = front.getNumberOfEnemies();
        tower.takeDamage(damage);
    }

    public ArrayList<EnemyWaveRev> getEnemyWaves() {
        return enemyWaves;
    }

    public int getEnemyWavesLeft() {
        return enemyWavesLeft;
    }

}
