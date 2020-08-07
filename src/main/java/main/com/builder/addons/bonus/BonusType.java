package main.com.builder.addons.bonus;

import main.com.utils.Randomizer;

import java.util.Arrays;

public enum BonusType {
    ADD_BALL("extras/extra_balloon.png", "balls/ball.png", 0),
    BIG_BALL("extras/extra_fire.png", "balls/tennis_ball.png", 0),
    DOUBLE_BALL("extras/extra.png", "balls/drawn_ball.png", 0),
    ENLARGE_PADDLE("extras/extra_carrot.png", "", 0),
    DO_NOTHING("", "", 0);

    private String extra;
    private String animate;
    private int bonusQ;

    BonusType(String extra, String animate, int bonusQ) {
        this.extra = extra;
        this.animate = animate;
        this.bonusQ = bonusQ;
    }

    public String getValue() {
        return extra;
    }

    public String getAnimate() {
        return animate;
    }

    public void setBonusQ(int q) {
        this.bonusQ += q;
    }

    public int getBonusQ() {
        return bonusQ;
    }

    public BonusType getAnyBonusAndSetIncrementalQ() {
        int length = BonusType.values().length;
        int random = Randomizer.randomInRange(0, length - 2);
        BonusType value = (BonusType) Arrays.stream(BonusType.values())
                .filter(v -> !v.equals(BonusType.DO_NOTHING))
                .toArray()[random];
        value.setBonusQ(1);
        return value;
    }

    public void resetBonus() {
        Arrays.stream(BonusType.values()).forEach(v -> v.bonusQ = 0);
    }


    @Override
    public String toString() {
        return "BonusType{" +
                "extra='" + extra + '\'' +
                ", animate='" + animate + '\'' +
                ", q=" + bonusQ +
                '}';
    }
}
