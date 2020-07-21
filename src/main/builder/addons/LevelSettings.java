package main.builder.addons;

public class LevelSettings {

    private int quantityOfPowerUps;
    private PowerUpType powerUpType;
    private int paddleSpeed;

    public LevelSettings() {
    }

    public int getQuantityOfPowerUps() {
        return quantityOfPowerUps;
    }

    public void setQuantityOfPowerUps(int quantityOfPowerUps) {
        this.quantityOfPowerUps = quantityOfPowerUps;
    }

    public PowerUpType getPowerUpType() {
        return powerUpType;
    }

    public void setPowerUpType(PowerUpType powerUpType) {
        this.powerUpType = powerUpType;
    }

    public int getPaddleSpeed() {
        return paddleSpeed;
    }

    public void setPaddleSpeed(int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
    }
}
