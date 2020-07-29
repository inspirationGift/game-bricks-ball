package game.builder.addons.figures;


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

    public void setRequiredBlocks(int requiredBlocks) {
        this.requiredBlocks = requiredBlocks;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
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
