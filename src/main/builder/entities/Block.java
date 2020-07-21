package main.builder.entities;

import main.builder.addons.PowerUpType;

import java.awt.*;
import java.util.Random;

public class Block extends Rectangle {
    private Image pic;
    public int dx;
    public int dy;
    public Rectangle left, right;
    String path;

    public boolean destroyed = false;
    private boolean isBlockPoweredUp = false;
    private PowerUpType powerUpType = PowerUpType.DO_NOTHING;

    public Block(int x, int y, int width, int height, String path) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.path = path;
        this.pic = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(this.path));

        left = new Rectangle(x - 1, y, 1, height);
        right = new Rectangle(x + width + 1, y, 1, height);
        this.dx = randomInRange(-3, 3);
        this.dy = randomInRange(-5, 0);
    }

    public void draw(Graphics graphics, Component component) {
        if (!destroyed)
            graphics.drawImage(this.pic, this.x, this.y, this.width, this.height, component);
    }

    private int randomInRange(int min, int max) {
        Random r = new Random();
        int res = 0;
        do {
            res = r.nextInt((max - min) + 1) + min;
        } while (res == 0);
        return res;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isBlockPoweredUp() {
        return isBlockPoweredUp;
    }

    public void setBlockPoweredUp(boolean blockPoweredUp) {
        isBlockPoweredUp = blockPoweredUp;
    }

    public PowerUpType getPowerUpType() {
        return powerUpType;
    }

    public void setPowerUpType(PowerUpType powerUpType) {
        this.powerUpType = powerUpType;
    }

    public void setPic(PowerUpType pic) {
        this.pic = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(pic.getAnimate()));
    }
}
