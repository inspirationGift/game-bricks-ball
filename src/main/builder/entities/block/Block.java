package main.builder.entities.block;

import main.builder.addons.BlockColor;
import main.builder.addons.PowerUpType;
import main.utils.Randomizer;

import java.awt.*;

public class Block extends Rectangle {
    private Image pic;
    public int dx;
    public int dy;
    public Rectangle left, right;
    public boolean destroyed = false;
    private boolean isBlockPoweredUp = false;
    private PowerUpType powerUpType = PowerUpType.DO_NOTHING;
    private int qHits;
    private String str;


    public Block(int x, int y, int width, int height, Object path) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        if (path instanceof BlockColor) {
            this.str = ((BlockColor) path).getPic();
            this.qHits = ((BlockColor) path).getqHits();
        } else if ((path instanceof String)) {
            this.str = (String) path;
        }

        this.pic = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(str));
        this.left = new Rectangle(x - 1, y, 1, height);
        this.right = new Rectangle(x + width + 1, y, 1, height);
        this.dx = Randomizer.randomInRangeNotZero(-5, 5);
        this.dy = Randomizer.randomInRangeNotZero(-5, 0);
    }

    public void draw(Graphics graphics, Component component) {
        if (!destroyed)
            graphics.drawImage(this.pic, this.x, this.y, this.width, this.height, component);
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

    public void setPic(BlockColor color) {
        this.pic = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(color.getPic()));
    }

    public int getqHits() {
        return qHits;
    }

    public void setqHits(int qHits) {
        this.qHits = qHits;
    }

}
