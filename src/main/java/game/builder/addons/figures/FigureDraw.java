package game.builder.addons.figures;

import java.util.ArrayList;
import java.util.List;

public class FigureDraw {


    private final int requiredBlocks;
    private final List<BlockPosition> positions;
    private final int frameWidth;
    private final int blockHeight;
    private final int blockWidth;
    private int maxInRow;
    private final int frameHeight;
    private final Figure figure;
    private int spaceBetween;


    public FigureDraw(Figure figure) {
        this.positions = new ArrayList();
        this.figure = figure;
        this.frameWidth = figure.getFrameWidth();
        this.frameHeight = figure.getFrameHeight();
        this.blockWidth = figure.getBlockWidth();
        this.blockHeight = figure.getBlockHeight();
        this.requiredBlocks = figure.getRequiredBlocks();
        setParams();
        draw();
    }

    private void setParams() {

        this.maxInRow = frameWidth / (blockWidth + 2);
        this.spaceBetween = (frameWidth % (blockWidth + 2)) / 2;
    }

    public List<BlockPosition> getFigure() {
        return this.positions;
    }


    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    void draw() {
        switch (this.figure) {
            case RECTANGLE, SQUARE -> rectangle(this.figure);
            case TRIANGLE_TOP -> triangle_top();
            case TRIANGLE_BOTTOM -> triangle_bottom();
            case RHOMBUS -> rhombus();
        }
    }

    private void rectangle(Figure figure) {
        int inRow = 0;
        int inCol = 0;

        int row = maxInRow;
        int qCol = row - 1;

        if (figure.equals(Figure.SQUARE)) {
            if (requiredBlocks > 0) {
                row = (int) Math.sqrt(requiredBlocks);
            }
            qCol = row * blockWidth / blockHeight;
        }

        for (int i = 0; i < row * qCol; i++) {
            positions.add(
                    new BlockPosition(inRow * (blockWidth + 2) + spaceBetween,
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
        int middle = (frameWidth / 2);
        do {
            for (int j = 0; j < rowNum; j++) {
                this.positions.add(new BlockPosition(middle + (blockWidth + 2) * j,
                        rowNum * blockHeight + 1, blockWidth, blockHeight));
            }
            middle -= ((blockWidth + 2) / 2);
            rowNum++;
        } while (rowNum <= maxInRow);
    }

    private void triangle_bottom() {
        int rowNum = 0;
        int middle = spaceBetween;
        double maxInRow = this.maxInRow;
        do {
            for (int j = 0; j < maxInRow; j++) {
                positions.add(new BlockPosition(middle + (blockWidth + 2) * j,
                        rowNum * blockHeight + 1, blockWidth, blockHeight));
            }
            middle += ((blockWidth + 2) / 2);
            maxInRow--;
            rowNum++;
        } while (maxInRow != 0);
    }

    private void rhombus() {
        int rowNum = 0;
        double maxInRow = this.maxInRow;
        int middle = spaceBetween;
        int yLine = (int) (frameHeight * 0.3);
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
            yLine += (blockHeight + 1);
            yVerse -= (blockHeight + 1);
        } while (maxInRow != 0);

    }

}
