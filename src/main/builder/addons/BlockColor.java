package main.builder.addons;

public enum BlockColor {
    RED("resources/blocks/red.png"),
    GREEN("resources/blocks/green.png"),
    YELLOW("resources/blocks/yellow.png"),
    BLUE("resources/blocks/blue.png");

    private String pic;

    BlockColor(String s) {
        this.pic = s;
    }

    public String getPic() {
        return this.pic;
    }


}

