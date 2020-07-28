package main.builder.addons.color;


import main.builder.addons.figures.Figure;
import main.utils.Randomizer;

import java.util.HashMap;
import java.util.Map;

public class ColorDistributor {

    private Map<BlockColor, Integer> mapColor;
    private int qUncoloredBlocks;
    private int qBlocks;
    private Object[] blockColors;
    private int blockColorsLength;


    public ColorDistributor(Figure figure) {
        this.mapColor = new HashMap<>();
        this.blockColors = BlockColor.DEFAULT.getNotZeroHits();
        this.blockColorsLength = BlockColor.DEFAULT.notZeroHitsLength;
        this.qBlocks = figure.getqBlocks();
        this.qUncoloredBlocks = qBlocks % blockColorsLength;
        if (qUncoloredBlocks > 0) this.qBlocks = qBlocks - qUncoloredBlocks;
        setColorMap();
    }

    public void setColorMap() {
        for (Object color : this.blockColors)
            mapColor.put((BlockColor) color, this.qBlocks / blockColorsLength);

        for (int i = 0; i < qUncoloredBlocks; i++) {
            BlockColor value = (BlockColor) this.blockColors[Randomizer.randomInRange(0, blockColorsLength - 1)];
            int newVal = mapColor.get(value) + 1;
            mapColor.put(value, newVal);
        }
    }

    public BlockColor pullRowFromColorMap(boolean isRandom) {
        return (BlockColor) Randomizer.pullElementFromMap(this.mapColor, isRandom);
    }

    public Map<BlockColor, Integer> getMapColor() {
        return mapColor;
    }

    public void setMapColor(Map<BlockColor, Integer> mapColor) {
        this.mapColor = mapColor;
    }

}
