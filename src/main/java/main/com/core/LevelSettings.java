package main.com.core;


import main.com.builder.addons.bonus.BonusType;
import main.com.builder.addons.color.BlockColor;
import main.com.builder.addons.figures.BlockPosition;
import main.com.builder.addons.figures.Figure;
import main.com.builder.addons.figures.FigureDraw;
import main.com.utils.Randomizer;

import java.util.List;

public class LevelSettings {

    private FigureDraw draw;
    private int paddleSpeed;
    private List<BlockPosition> positions;
    private int frameHeight;
    private int frameWidth;
    private int qBlocks;

    public LevelSettings() {
    }

    public void setFigure(Figure figure) {
        this.draw = new FigureDraw(figure);
        this.frameHeight = this.draw.getFrameHeight();
        this.frameWidth = this.draw.getFrameWidth();
        this.positions = this.draw.getBlockPositionsList();
        this.qBlocks = this.positions.size();
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public List<BlockPosition> getFigure() {
        return this.positions;
    }

    public void setRandomBonuses(int maxBonuses) {
        while (maxBonuses != 0) {
            int random = Randomizer.randomInRange(1, positions.size() - 1);
            if (positions.get(random).getBonus() == BonusType.DO_NOTHING) {
                positions.get(random).setBonus(BonusType.DO_NOTHING.getAnyBonusAndSetIncrementalQ());
                maxBonuses--;
            }
        }
    }

    public void setBlockColor(boolean onlyWithHits, boolean isRandom) {
        Object[] list;
        if (!onlyWithHits)
            BlockColor.DEFAULT.setRandomHits();
        list = BlockColor.DEFAULT.getNotZeroHits();
        if (isRandom) {
            positions.forEach(v -> {
                int random = Randomizer.randomInRange(0, list.length - 1);
                v.setColor((BlockColor) list[random]);
            });
        } else {
            int m = 0;
            int qColors = positions.size() / list.length;
            int c = 0;
            for (BlockPosition position : positions) {
                if (c == list.length) c = 0;
                position.setColor((BlockColor) list[c]);
                m++;
                if (m == qColors) {
                    m = 0;
                    c++;
                }
            }
        }
    }

    public int getPaddleSpeed() {
        return paddleSpeed;
    }

    public void setPaddleSpeed(int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
    }

    @Override
    public String toString() {
        return "LevelSettings{" +
                ", cells=" + positions +
                ", paddleSpeed=" + paddleSpeed +
                '}';
    }
}
