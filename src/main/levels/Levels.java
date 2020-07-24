package main.levels;

import main.core.GamePanel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Levels {

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    private GamePanel gamePanel;

    public Levels(int i) {
        try {
            Class<?> aClass = Class.forName("main.levels.Level" + i);
            Constructor constructor = aClass.getConstructor();
            Object[] argList = new Object[0];
            this.gamePanel = (GamePanel) constructor.newInstance(argList);

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public void destroy() {
        this.gamePanel = null;
    }


}
