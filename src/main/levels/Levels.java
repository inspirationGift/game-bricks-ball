package main.levels;

import main.builder.addons.BlockColor;
import main.builder.addons.PowerUpType;
import main.builder.entities.block.BlockSettings;
import main.core.GamePanel;
import main.core.LevelSettings;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Levels extends GamePanel {
    public int life;
    private int score = 0;
    private int gameLevel;

    public Levels(int i) {
        this.gameLevel = i;
    }

    @Override
    public LevelSettings levelSetUp() {
        BlockColor.GREEN.setqHits(1);
        BlockColor.YELLOW.setqHits(2);
        BlockColor.BLUE.setqHits(3);
        BlockColor.RED.setqHits(4);
        LevelSettings ls = new LevelSettings();
        ls.setPaddleSpeed(50);
        ls.setBlockSettings(new BlockSettings());
        ls.getBlockSettings().setqBlocks(32);
        ls.getBlockSettings().setRowStep(25);
        ls.getBlockSettings().setqBlocksInRow(8);
        ls.getBlockSettings().setEquallyDistribute(true);
        ls.setPowerUps(PowerUpType.DOUBLE_BALL, 3);
        ls.setPowerUps(PowerUpType.BIG_BALL, 3);
        ls.setPowerUps(PowerUpType.ADD_BALL, 3);
        ls.setPowerUps(PowerUpType.ENLARGE_PADDLE, 3);
        return ls;
    }


    private void getProfiledLevel(int i) {
        try {
            Class<?> aClass = Class.forName("main.levels.Level" + i);
            Constructor constructor = aClass.getConstructor();
            Object[] argList = new Object[0];
            constructor.newInstance(argList);

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
