package main.levels;


import main.builder.addons.color.BlockColor;
import main.builder.addons.figures.Figure;
import main.core.LevelSettings;

public class Level0 extends LevelSettings {

    public Level0(Integer i) {
        setPaddleSpeed(45);
        BlockColor.DEFAULT.resetBlockColor();

        Figure figure = Figure.RHOMBUS;
        figure.set(32, 490, 600, 60, 25);
        setFigure(figure);
        BlockColor.DEFAULT.setBlockHits(3);

        setBlockColor(true, true);
        setBonuses(4);


    }

}
