package main.core;

import main.builder.addons.bonus.BonusType;
import main.builder.addons.color.BlockColor;
import main.builder.addons.figures.BlockPosition;
import main.builder.addons.figures.Figure;
import main.builder.addons.figures.FigureDraw;
import main.utils.Randomizer;

import java.util.Arrays;
import java.util.List;


public class LevelSettings {

    private FigureDraw draw;
    private int paddleSpeed;
    private List<BlockPosition> positions;

    public LevelSettings() {
    }

    public void setFigure(Figure figure) {
        this.draw = new FigureDraw(figure);
        this.positions = this.draw.getFigure();
//        System.out.println(this.cells);
    }

    public List<BlockPosition> getFigure() {
        this.positions.stream().forEach(v -> {
            System.out.println(v);
        });
        return this.positions;
    }

    public void setBonuses(int maxBonuses) {
        while (maxBonuses != 0) {
            int random = Randomizer.randomInRange(0, positions.size() - 1);

            if (positions.get(random).getBonus() == BonusType.DO_NOTHING) {
                positions.get(random).setBonus(BonusType.DO_NOTHING.getAnyAndSetBonus());
                maxBonuses--;
            }
        }
    }

    public void setBlockColor(boolean onlyWithHits, boolean isRandom) {
        Object[] list;
        if (!onlyWithHits)
            BlockColor.DEFAULT.setDefaultHits();
        list = BlockColor.DEFAULT.getNotZeroHits();

        Arrays.stream(list).forEach(System.out::println);

        if (isRandom) {
            positions.stream().forEach(v -> {
                int random = Randomizer.randomInRange(0, list.length - 1);
                v.setColor((BlockColor) list[random]);
            });
        } else {
            int m = 0;
            int qColors = positions.size() / list.length;
            int c = 0;
            for (BlockPosition position : positions) {
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
