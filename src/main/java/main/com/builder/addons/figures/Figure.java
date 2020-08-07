package main.com.builder.addons.figures;


public enum Figure {

    RECTANGLE(0, 0, 0, 0, 0),
    SQUARE(0, 0, 0, 0, 0),
    TRIANGLE_TOP(0, 0, 0, 0, 0),
    TRIANGLE_BOTTOM(0, 0, 0, 0, 0),
    RHOMBUS(0, 0, 0, 0, 0);

    private int qRows;
    private double qInRow;
    private int frameWidth;
    private int frameHeight;
    private int blockHeight;
    private int blockWidth;
    private int requiredBlocks;


    Figure(int frameWidth, int frameHeight, int blockWidth, int blockHeight, int requiredBlocks) {
    }

    public void set(int frameWidth, int frameHeight, int blockWidth, int blockHeight, int requiredBlocks) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
        this.requiredBlocks = requiredBlocks;
    }

    public int getRequiredBlocks() {
        return requiredBlocks;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getBlockWidth() {
        return blockWidth;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "qRows=" + qRows +
                ", qInRow=" + qInRow +
                ", frameWidth=" + frameWidth +
                ", frameHeight=" + frameHeight +
                ", blockHeight=" + blockHeight +
                ", blockWidth=" + blockWidth +
                ", requiredBlocks=" + requiredBlocks +
                '}';
    }
}
