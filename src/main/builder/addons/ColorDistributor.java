package main.builder.addons;


import main.utils.Randomizer;

import java.util.HashMap;
import java.util.Map;

public class ColorDistributor {

    private int length;
    private int qUncoloredBlocks;
    private Map<BlockColor, Integer> mapColor;

    public Map<BlockColor, Integer> getMapColor() {
        return mapColor;
    }

    public void setMapColor(Map<BlockColor, Integer> mapColor) {
        this.mapColor = mapColor;
    }

    public Map<Integer, Integer> getRowSizer() {
        return rowSizer;
    }

    public void setRowSizer(Map<Integer, Integer> rowSizer) {
        this.rowSizer = rowSizer;
    }

    private Map<Integer, Integer> rowSizer;
    private int q;

    public ColorDistributor(int quantity, boolean isEqualDistribution) {
        this.rowSizer = new HashMap<>();
        this.mapColor = new HashMap<>();
        this.q = quantity;
        this.length = BlockColor.values().length;
        this.qUncoloredBlocks = quantity % length;
        if (isEqualDistribution && qUncoloredBlocks > 0) this.q = quantity - qUncoloredBlocks;

        setColorMap();

    }

    public void setRowMap(int spaceBetweenRows, int maxBlocksInRow) {
        int stepBetweenRows = 0;
        int leftBlocks = this.q % maxBlocksInRow;

        for (int i = 0; i < q / maxBlocksInRow; i++) {
            this.rowSizer.put(stepBetweenRows, maxBlocksInRow);
            stepBetweenRows += spaceBetweenRows;
        }

        if (leftBlocks > 0)
            rowSizer.put(stepBetweenRows, leftBlocks);
    }

    public void setColorMap() {

        for (BlockColor color : BlockColor.values())
            mapColor.put(color, q / BlockColor.values().length);

        for (int i = 0; i < qUncoloredBlocks; i++) {
            BlockColor value = BlockColor.values()[Randomizer.randomInRange(0, length - 1)];
            int newVal = mapColor.get(value) + 1;
            mapColor.put(value, newVal);
        }
    }

    public Integer pullRowFromRowMap() {
        return (Integer) Randomizer.pullElementFromMap(this.rowSizer, false);
    }

    public BlockColor pullRowFromColorMap() {
        return (BlockColor) Randomizer.pullElementFromMap(this.mapColor, true);
    }
}
