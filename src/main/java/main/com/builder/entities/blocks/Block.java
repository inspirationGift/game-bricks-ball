package main.com.builder.entities.blocks;

import main.com.builder.addons.color.BlockColor;
import main.com.builder.addons.bonus.BonusType;
import main.com.utils.Randomizer;

import java.awt.*;

public class Block extends Rectangle {
    private Image pic;
    public int dx;
    public int dy;
    public Rectangle left, right;
    public boolean destroyed = false;
    private BonusType bonusType = BonusType.DO_NOTHING;
    private boolean hasBlockBonus = false;
    private int qHits;
    private String str;
    public BlockColor color;

    public Block(int x, int y, int width, int height, Object path) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        if (path instanceof BlockColor) {
            this.color = (BlockColor) path;
            this.str = ((BlockColor) path).getPic();
            this.qHits = ((BlockColor) path).getqHits();
        } else if ((path instanceof String)) {
            this.str = (String) path;
        }

        this.pic = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(str));
        this.left = new Rectangle(x - 1, y, 1, height);
        this.right = new Rectangle(x + width + 1, y, 1, height);
        this.dx = Randomizer.randomInRangeNotZero(-3, 3);
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

    public boolean hasBlockBonus() {
        return hasBlockBonus;
    }

    public void setBlockHasBonus(boolean blockPoweredUp) {
        hasBlockBonus = blockPoweredUp;
    }

    public BonusType getBonusType() {
        return bonusType;
    }

    public void setBonusType(BonusType bonusType) {
        this.bonusType = bonusType;
        if (!bonusType.equals(BonusType.DO_NOTHING)) this.hasBlockBonus = true;
    }

    public void setPic(BonusType pic) {
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

    @Override
    public String toString() {
        return "Block{" +
                "destroyed=" + destroyed +
                ", hasBlockBonus=" + hasBlockBonus +
                ", bonusType=" + bonusType +
                '}';
    }
}
