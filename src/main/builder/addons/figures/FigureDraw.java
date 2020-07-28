package main.builder.addons.figures;

import java.util.ArrayList;
import java.util.List;

public class FigureDraw {


    private List<BlockPosition> positions;
    private final int frameWidth;
    private int blockWidth;
    private final int blockHeight;
    private final int qBlocks;
    private final double maxInRow;
    private final int frameHeight;
    private Figure figure;


    public FigureDraw(Figure figure) {
        this.positions = new ArrayList();
        this.figure = figure;
        this.frameWidth = figure.getFrameWidth();
        this.frameHeight = figure.getFrameHeight();
        this.blockWidth = figure.getBlockWidth();
        this.blockHeight = figure.getBlockHeight();
        this.qBlocks = figure.getqBlocks();
        this.maxInRow = Math.round((((double) frameWidth) / (blockWidth + 2)));
        draw();
    }

    public List<BlockPosition> getFigure() {
        return this.positions;
    }

    public int getqBlocks() {
        return this.qBlocks;
    }

    void draw() {
        switch (this.figure) {
            case RECTANGLE -> {
                rectangle();
            }
            case TRIANGLE_TOP -> {
                triangle_top();
            }
            case TRIANGLE_BOTTOM -> {
                triangle_bottom();
            }
            case RHOMBUS -> {
                rhombus();
            }
        }
    }

    private void rectangle() {
        int inRow = 0;
        int inCol = 0;
        for (int i = 0; i < qBlocks; i++) {
            positions.add(
                    new BlockPosition(inRow * blockWidth + 2,
                            inCol * blockHeight + 1, blockWidth, blockHeight));
            inRow++;
            if (inRow == maxInRow) {
                inRow = 0;
                inCol++;
            }
        }
    }

    private void triangle_top() {
        int rowNum = 0;
        int middle = frameWidth / 2;
        do {
            for (int j = 0; j < rowNum; j++) {
                this.positions.add(new BlockPosition(middle + (blockWidth + 2) * j,
                        rowNum * blockHeight + 1, blockWidth, blockHeight));
            }
            middle -= ((blockWidth + 2) / 2);
            rowNum++;
        } while (rowNum <= maxInRow);
        this.blockWidth = positions.size();
    }

    private void triangle_bottom() {
        int rowNum = 0;
        int middle = 1;
        double maxInRow = this.maxInRow;
        do {
            for (int j = 0; j < maxInRow; j++) {
                positions.add(new BlockPosition(middle + (blockWidth + 2) * j,
                        rowNum * blockHeight + 1, blockWidth, blockHeight));
            }
            middle += (blockWidth / 2);
            maxInRow--;
            rowNum++;
        } while (maxInRow != 0);
        this.blockWidth = positions.size();
    }

    private void rhombus() {
        int rowNum = 0;
        double maxInRow = this.maxInRow;
        int middle = 1;
        int yLine = frameHeight / 2;
        int yVerse = yLine;

        do {
            for (int j = 0; j < maxInRow; j++) {
                if (maxInRow != this.maxInRow)
                    positions.add(new BlockPosition(middle + (blockWidth + 2) * j,
                            yVerse, blockWidth, blockHeight));
                positions.add(new BlockPosition(middle + (blockWidth + 2) * j,
                        yLine, blockWidth, blockHeight));
            }
            middle += (blockWidth / 2);
            maxInRow--;
            rowNum++;
            yLine += (blockHeight + 1) * rowNum;
            yVerse -= (blockHeight + 1) * rowNum;
        } while (maxInRow != 0);
        this.blockWidth = positions.size();

    }

}
