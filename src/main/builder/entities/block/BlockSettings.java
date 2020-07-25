package main.builder.entities.block;


public class BlockSettings {

    private int qBlocks;
    private int qBlocksInRow;
    private int rowStep;
    private boolean isEquallyDistribute;

    public BlockSettings() {
    }

    public BlockSettings(int qBlocks, int qBlocksInRow, int rowStep, boolean isEquallyDistribute) {
        this.qBlocks = qBlocks;
        this.qBlocksInRow = qBlocksInRow;
        this.rowStep = rowStep;
        this.isEquallyDistribute = isEquallyDistribute;
    }

    public int getqBlocks() {
        return qBlocks;
    }

    public void setqBlocks(int qBlocks) {
        this.qBlocks = qBlocks;
    }

    public int getqBlocksInRow() {
        return qBlocksInRow;
    }

    public void setqBlocksInRow(int qBlocksInRow) {
        this.qBlocksInRow = qBlocksInRow;
    }

    public int getRowStep() {
        return rowStep;
    }

    public void setRowStep(int rowStep) {
        this.rowStep = rowStep;
    }

    public boolean isEquallyDistribute() {
        return isEquallyDistribute;
    }

    public void setEquallyDistribute(boolean equallyDistribute) {
        isEquallyDistribute = equallyDistribute;
    }

}
