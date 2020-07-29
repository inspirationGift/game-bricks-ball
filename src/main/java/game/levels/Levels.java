package game.levels;

import game.core.LevelSettings;

public class Levels {
    public int life;
    //    private Class<?> aClass;
    public int frameWidth;
    public int frameHeight;
    public LevelSettings levelSettings;

    public Levels() {
    }

    public Levels(int i) {
        this.levelSettings = new Level0(i);
        this.frameWidth = this.levelSettings.getFrameWidth();
        this.frameHeight = this.levelSettings.getFrameHeight();
    }

//    private LevelSettings getProfiledLevel(int i) {
//        int m = 0;
//        this.levelSettings = null;
//        this.aClass = null;
//        try {
//            this.aClass = Class.forName("game.levels.Level" + m);
//            Constructor constructor = aClass.getConstructor(Integer.class);
//            Object[] argList = new Object[1];
//            argList[0] = i;
//            this.levelSettings = (LevelSettings) constructor.newInstance(argList);
//        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
