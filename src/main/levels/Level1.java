package main.levels;

import main.core.LevelSettings;
import main.builder.addons.PowerUpType;
import main.core.GamePanel;

public class Level1 extends GamePanel {

    public Level1() {
        super();
    }

    @Override
    public void levelSettings(LevelSettings levelSettings) {
        levelSettings.setPaddleSpeed(35);
        levelSettings.setPowerUps(PowerUpType.ADD_BALL, 5);
        levelSettings.setPowerUps(PowerUpType.DOUBLE_BALL, 5);
        levelSettings.setPowerUps(PowerUpType.BIG_BALL, 5);
        levelSettings.setPowerUps(PowerUpType.ENLARGE_PADDLE, 5);
    }
}
