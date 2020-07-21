package main.builder.addons;

public enum PowerUpType {

    ADD_BALL("resources/extras/extra_balloon.png", "resources/balls/ball.png"),
    BIG_BALL("resources/extras/extra_fire.png", "resources/balls/tennis_ball.png"),
    DOUBLE_BALL("resources/extras/extra.png", "resources/balls/drawn_ball.png"),
    ENLARGE_PADDLE("resources/extras/extra_carrot.png", ""),
    DO_NOTHING("", "");

    private String extra;
    private String animate;

    PowerUpType(String extra, String animate) {
        this.extra = extra;
        this.animate = animate;
    }

    public String getValue() {
        return extra;
    }

    public String getAnimate() {
        return animate;
    }
}
