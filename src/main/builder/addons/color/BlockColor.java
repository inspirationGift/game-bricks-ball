package main.builder.addons.color;

import main.utils.Randomizer;

import java.util.Arrays;

public enum BlockColor {
    GREEN("resources/blocks/green.png", 0, 1),
    YELLOW("resources/blocks/yellow.png", 0, 2),
    BLUE("resources/blocks/blue.png", 0, 3),
    RED("resources/blocks/red.png", 0, 4),
    DEFAULT("", 0, 0);

    private String pic;
    private int qHits;
    private int rateTransition;
    public int zeroHitsLength;
    public int notZeroHitsLength;

    BlockColor(String s, int qHits, int rateTransition) {
        this.pic = s;
        this.qHits = qHits;
        this.rateTransition = rateTransition;
    }

    public int getRateTransition() {
        return rateTransition;
    }

    public void setRateTransition(int rateTransition) {
        this.rateTransition = rateTransition;
    }

    public String getPic() {
        return this.pic;
    }

    public int getqHits() {
        return qHits;
    }

    public void setqHits(int qHits) {
        this.qHits = qHits;
    }

    public Object[] getZeroHits() {
        Object[] zeroHits = Arrays.stream(BlockColor.values())
                .filter(val -> (!val.equals(BlockColor.DEFAULT)) && (val.getqHits() == 0)).toArray();
        this.zeroHitsLength = zeroHits.length;
        return zeroHits;
    }

    public Object[] getNotZeroHits() {
        Object[] zeroHits = Arrays.stream(BlockColor.values())
                .filter(val -> (!val.equals(BlockColor.DEFAULT)) && (val.getqHits() > 0)).toArray();
        this.notZeroHitsLength = zeroHits.length;
        return zeroHits;
    }

    public Object[] getAllColors() {
        Object[] all = Arrays.stream(BlockColor.values())
                .filter(val -> (!val.equals(BlockColor.DEFAULT))).toArray();
        return all;
    }

    public void resetBlockColor() {
        Arrays.stream(BlockColor.values()).forEach(v -> v.setqHits(0));
    }

    public void setDefaultHits() {
        Arrays.stream(BlockColor.values()).forEach(v -> v.setqHits(2));
    }


    public void setBlockHits(int maxHits) {
        Object[] zeroHits = getZeroHits();
        int id = Randomizer.random(zeroHitsLength - 1);
        int m = Randomizer.randomInRange(1, maxHits);
        ((BlockColor) zeroHits[id]).setqHits(m);
    }

    @Override
    public String toString() {
        return "BlockColor{" +
                "pic='" + pic + '\'' +
                ", qHits=" + qHits +
                ", rateTransition=" + rateTransition +
                '}';
    }
}

