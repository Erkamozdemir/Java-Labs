package lab7;

import java.util.Random;

public class EnemyWave {

    private String[] enemyWave;

    public EnemyWave(int numberOfEnemies) {
        enemyWave = new String[TowerDefenseGame.ROWS];
        initializeWave(numberOfEnemies);
        if (numberOfEnemies > 0) {
            shuffleArray();
        }
    }

    private void initializeWave(int numberOfEnemies) {
        int smaller = Math.min(numberOfEnemies, TowerDefenseGame.ROWS);
        for (int i = 0; i < TowerDefenseGame.ROWS; i++) {
            if (i < smaller) {
                enemyWave[i] = TowerDefenseGame.ENEMY_SYMBOL;
            } else {
                enemyWave[i] = TowerDefenseGame.EMPTY_SYMBOL;
            }
        }
    }

    private void shuffleArray() {
        Random random = TowerDefenseGame.random;
        for (int i = (enemyWave.length - 1); i > 0; i--) {
            int randomInd = random.nextInt(i + 1);
            String wait = enemyWave[i];
            enemyWave[i] = enemyWave[randomInd];
            enemyWave[randomInd] = wait;
        }
    }

    public int hitEnemy(int index) {
        if (!(index < 0 || index >= enemyWave.length)) {
            boolean isEnemy = (enemyWave[index].equals(TowerDefenseGame.ENEMY_SYMBOL));
            if (isEnemy) {
                enemyWave[index] = TowerDefenseGame.EMPTY_SYMBOL;
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    public int getNumberOfEnemies() {
        int count = 0;
        for (int i = 0; i < TowerDefenseGame.ROWS; i++) {
            if (enemyWave[i].equals(TowerDefenseGame.ENEMY_SYMBOL)) {
                count++;
            }
        }
        return count;
    }

    public String[] getEnemyWave() {
        return enemyWave;
    }

}
