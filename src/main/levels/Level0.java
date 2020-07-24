package main.levels;

import main.builder.addons.PowerUpType;
import main.core.GamePanel;
import main.core.LevelSettings;

public class Level0 extends GamePanel {

    public Level0() {
        super();
    }

    @Override
    public void levelSettings(LevelSettings levelSettings) {
        levelSettings.setPaddleSpeed(45);
        levelSettings.setPowerUps(PowerUpType.ADD_BALL, 10);
        levelSettings.setPowerUps(PowerUpType.DOUBLE_BALL, 10);
        levelSettings.setPowerUps(PowerUpType.BIG_BALL, 10);
        levelSettings.setPowerUps(PowerUpType.ENLARGE_PADDLE, 2);
    }
}
