package main.levels;

import main.core.GamePanel;
import main.core.LevelSettings;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Levels {
    public int life;
    private int score;
    private int gameLevel;
    private LevelSettings ls;
    private GamePanel gamePanel;
    private Class<?> aClass;

    public Levels(int i) {
        this.gameLevel = i;
        getProfiledLevel(i);
        this.gamePanel = new GamePanel(this.ls);

    }

    public int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }


    private LevelSettings getProfiledLevel(Integer i) {
        Integer m = i < 16 ? 0 : 1;
        this.ls = null;
        this.aClass = null;
        try {
            this.aClass = Class.forName("main.levels.Level" + m);
            Constructor constructor = aClass.getConstructor(Integer.class);
            Object[] argList = new Object[1];
            argList[0] = i;
            this.ls = (LevelSettings) constructor.newInstance(argList);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LevelSettings getLs() {
        return ls;
    }

}
