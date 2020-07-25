package main.builder.addons;

public enum BlockColor {
    RED("resources/blocks/red.png", 1, 4),
    GREEN("resources/blocks/green.png", 1, 1),
    YELLOW("resources/blocks/yellow.png", 1, 2),
    BLUE("resources/blocks/blue.png", 1, 3);

    private String pic;
    private int qHits;
    private int rateTransition;

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
}

