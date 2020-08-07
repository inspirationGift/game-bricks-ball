package main.com.builder.addons.figures;

import main.com.builder.addons.bonus.BonusType;
import main.com.builder.addons.color.BlockColor;

public class BlockPosition {
    public int x;
    public int y;
    public int width;
    public int height;
    public BonusType bonus;
    public BlockColor color;
    public boolean isBonus;

    public BlockPosition(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bonus = BonusType.DO_NOTHING;
        this.color = BlockColor.DEFAULT;
    }

    public BonusType getBonus() {
        return bonus;
    }

    public void setBonus(BonusType bonus) {
        this.isBonus = true;
        this.bonus = bonus;
    }

    public void setColor(BlockColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "BlockPosition{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", bonus=" + bonus +
                '}';
    }
}
