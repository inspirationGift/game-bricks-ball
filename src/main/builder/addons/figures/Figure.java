package main.builder.addons.figures;


public enum Figure {

    RECTANGLE(32, 490, 600, 60, 25),
    SQUARE(32, 490, 600, 60, 25),
    TRIANGLE_TOP(32, 490, 600, 60, 25),
    TRIANGLE_BOTTOM(32, 490, 600, 60, 25),
    RHOMBUS(32, 490, 600, 60, 25);

    private int qBlocks;
    private int qRows;
    private double qInRow;
    private int frameWidth;
    private int frameHeight;
    private int blockHeight;
    private int blockWidth;


    Figure(int qBlocks, int frameWidth, int frameHeight, int blockWidth, int blockHeight) {
    }

    public void set(int qBlocks, int frameWidth, int frameHeight, int blockWidth, int blockHeight) {
        this.qBlocks = qBlocks;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
//        this.qInRow = Math.round((((double) frameWidth) / (blockWidth + 2)));
    }

    public int getqBlocks() {
        return qBlocks;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public void setqBlocks(int qBlocks) {
        this.qBlocks = qBlocks;
    }

    public int getqRows() {
        return qRows;
    }

    public void setqRows(int qRows) {
        this.qRows = qRows;
    }

    public double getqInRow() {
        return qInRow;
    }

    public void setqInRow(double qInRow) {
        this.qInRow = qInRow;
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(int blockHeight) {
        this.blockHeight = blockHeight;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getBlockWidth() {
        return blockWidth;
    }

    public void setBlockWidth(int blockWidth) {
        this.blockWidth = blockWidth;
    }
}
