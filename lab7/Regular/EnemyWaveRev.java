
import java.util.Random;

public class EnemyWaveRev {

    private final String[] enemyWave;

    public EnemyWaveRev(int numberOfEnemies) {
        enemyWave = new String[TowerDefenseGameRev.ROWS];
        initializeWave(numberOfEnemies);
        if (numberOfEnemies > 0) {
            shuffleArray();
            spawnShockwave();
        }
    }

    private void initializeWave(int numberOfEnemies) {
        int smaller = Math.min(numberOfEnemies, TowerDefenseGameRev.ROWS);
        for (int i = 0; i < TowerDefenseGameRev.ROWS; i++) {
            if (i < smaller) {
                enemyWave[i] = TowerDefenseGameRev.ENEMY_SYMBOL;
            } else {
                enemyWave[i] = TowerDefenseGameRev.EMPTY_SYMBOL;
            }
        }
    }

    private void shuffleArray() {
        Random random = TowerDefenseGameRev.random;
        for (int i = (enemyWave.length - 1); i > 0; i--) {
            int randomInd = random.nextInt(i + 1);
            String wait = enemyWave[i];
            enemyWave[i] = enemyWave[randomInd];
            enemyWave[randomInd] = wait;
        }
    }

    private void spawnShockwave() {
        Random random = TowerDefenseGameRev.random;
        if (random.nextDouble() < TowerDefenseGameRev.SHOCKWAVE_SPAWN_RATE) {
            int index = random.nextInt(0, enemyWave.length);
            enemyWave[index] = TowerDefenseGameRev.SHOCKWAVE_SYMBOL;
        }
    }

    public int hitEnemy(int index) {
        if (!(index < 0 || index >= enemyWave.length)) {
            boolean isEnemy = (enemyWave[index].equals(TowerDefenseGameRev.ENEMY_SYMBOL)
                    || enemyWave[index].equals(TowerDefenseGameRev.SHOCKWAVE_SYMBOL));
            if (isEnemy) {
                if (enemyWave[index].equals(TowerDefenseGameRev.ENEMY_SYMBOL)) {
                    enemyWave[index] = TowerDefenseGameRev.EMPTY_SYMBOL;
                    return 1;
                } else {
                    enemyWave[index] = TowerDefenseGameRev.EMPTY_SYMBOL;
                    return -1;
                }

            } else {
                return 0;
            }
        }
        return 0;
    }

    public int getNumberOfEnemies() {
        int count = 0;
        for (int i = 0; i < TowerDefenseGameRev.ROWS; i++) {
            if (enemyWave[i].equals(TowerDefenseGameRev.ENEMY_SYMBOL)) {
                count++;
            }
        }
        return count;
    }

    public String[] getEnemyWave() {
        return enemyWave;
    }

}
