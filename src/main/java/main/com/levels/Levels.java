package main.com.levels;

import main.com.core.LevelSettings;

public class Levels {
    public int life;
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
}
