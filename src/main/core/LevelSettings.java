package main.core;

import main.builder.addons.PowerUpType;

import java.util.HashMap;
import java.util.Map;

public class LevelSettings {

    private Map<PowerUpType, Integer> powerUps;
    private int paddleSpeed;

    public LevelSettings() {
        this.powerUps = new HashMap();
    }

    public Map<PowerUpType, Integer> getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(PowerUpType type, Integer powerUps) {
        this.powerUps.put(type, powerUps);
    }

    public int getPaddleSpeed() {
        return paddleSpeed;
    }

    public void setPaddleSpeed(int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
    }

    public int getQuantityOfPowerUps() {
        return this.powerUps.values().stream().reduce(0, Integer::sum);
    }
}
