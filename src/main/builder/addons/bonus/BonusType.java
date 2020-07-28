package main.builder.addons.bonus;

import main.utils.Randomizer;

import java.util.Arrays;

public enum BonusType {

    ADD_BALL("resources/extras/extra_balloon.png", "resources/balls/ball.png", 0),
    BIG_BALL("resources/extras/extra_fire.png", "resources/balls/tennis_ball.png", 0),
    DOUBLE_BALL("resources/extras/extra.png", "resources/balls/drawn_ball.png", 0),
    ENLARGE_PADDLE("resources/extras/extra_carrot.png", "", 0),
    DO_NOTHING("", "", 0);

    private String extra;
    private String animate;
    private int q;

    BonusType(String extra, String animate, int q) {
        this.extra = extra;
        this.animate = animate;
        this.q = q;
    }

    public String getValue() {
        return extra;
    }

    public String getAnimate() {
        return animate;
    }

    public void setBonusQ(int q) {
        this.q = q;
    }

    public BonusType getAnyAndSetBonus() {

        int length = BonusType.values().length;
        int random = Randomizer.randomInRange(0, length - 2);

        BonusType value = (BonusType) Arrays.stream(BonusType.values())
                .filter(v -> v != BonusType.DO_NOTHING)
                .toArray()[random];
        value.setBonusQ(1);
        return value;
    }

    @Override
    public String toString() {
        return "BonusType{" +
                "extra='" + extra + '\'' +
                ", animate='" + animate + '\'' +
                ", q=" + q +
                '}';
    }
}
