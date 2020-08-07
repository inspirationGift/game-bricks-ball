package main.com.levels;


import main.com.builder.addons.color.BlockColor;
import main.com.builder.addons.figures.Figure;
import main.com.utils.Randomizer;
import main.com.core.LevelSettings;

import java.util.Arrays;

public class Level0 extends LevelSettings {

    Figure figure;

    public Level0(Integer i) {

        setPaddleSpeed(45);
        BlockColor.DEFAULT.resetBlockColor();

        int count = Randomizer.random((int) Arrays.stream(Figure.values()).count());
        this.figure = Figure.values()[count];
        setUp(i);
    }


    void setUp(int i) {
        if (i < 5) {
            figure.set(500, 600, 60, 25, 25);
            setFigure(figure);
            BlockColor.DEFAULT.setRandomBlockHits(1);
            setBlockColor(true, false);
            setRandomBonuses(15);
        }
        if (i >= 5 && i < 10) {
            figure.set(550, 600, 60, 25, 42);
            setFigure(figure);
            BlockColor.DEFAULT.setRandomBlockHits(4);

            setBlockColor(Randomizer.randomBool(), false);
            setRandomBonuses(10);
        }

        if (i >= 10 && i < 15) {
            figure.set(550, 600, 60, 25, 0);
            setFigure(figure);
            BlockColor.DEFAULT.setRandomBlockHits(6);

            setBlockColor(Randomizer.randomBool(), false);
            setRandomBonuses(8);
        }

        if (i >= 15) {
            figure.set(600, 700, 60, 25, 0);
            setFigure(figure);
            BlockColor.DEFAULT.setRandomBlockHits(7);

            setBlockColor(Randomizer.randomBool(), Randomizer.randomBool());
            setRandomBonuses(10);
        }

    }

}
