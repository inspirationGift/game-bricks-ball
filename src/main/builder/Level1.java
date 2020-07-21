package main.builder;

import main.builder.addons.LevelSettings;
import main.builder.addons.PowerUpType;

public class Level1 extends BlockBreakerPanel {

    public Level1() {
        super();
    }

    @Override
    public void levelSettings(LevelSettings levelSettings) {
        levelSettings.setPaddleSpeed(35);
        levelSettings.setQuantityOfPowerUps(30);
        levelSettings.setPowerUpType(PowerUpType.ADD_BALL);
        super.levelSettings(levelSettings);
    }
}
