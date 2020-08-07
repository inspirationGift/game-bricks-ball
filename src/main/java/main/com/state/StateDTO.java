package main.com.state;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import main.com.core.GamePanel;
import main.com.core.LevelSettings;
import main.com.core.ScorePanel;

import java.util.ArrayList;
import java.util.List;


@JsonSerialize
public class StateDTO {

    @JsonProperty("balls")
    public ArrayList balls;
    @JsonProperty("blocks")
    public List<BlockProxy> blocks;
    @JsonProperty("bonuses")
    public List<BlockProxy> bonuses;
    @JsonProperty("frameWidth")
    public int frameWidth;
    @JsonProperty("frameHeight")
    public int frameHeight;
    @JsonProperty("level")
    public int level;
    @JsonProperty("scoreTotal")
    public int scoreTotal;
    @JsonProperty("life")
    public int life;
    @JsonProperty("paddleSpeed")
    public int paddleSpeed;

    public StateDTO() {
        this.blocks = new ArrayList();
        this.balls = new ArrayList();
        this.bonuses = new ArrayList();
    }


    public StateDTO(GamePanel game, ScorePanel scorePanel, LevelSettings settings) {
        this();
        this.frameWidth = settings.getFrameWidth();
        this.frameHeight = settings.getFrameHeight();
        this.level = scorePanel.levelText;
        this.life = scorePanel.lifeText;
        this.scoreTotal = scorePanel.gameScoreText;
        this.paddleSpeed = game.getPaddleSpeed();


        game.getBallList().stream()
                .filter(b -> !b.destroyed)
                .map(bl -> new BlockProxy(bl.color, bl.getqHits(), bl.getBonusType(),
                        bl.x, bl.y, bl.width, bl.height, bl.dx, bl.dy, bl.hasBlockBonus()))
                .forEach(v -> this.balls.add(v));

        game.getBlocks().stream()
                .filter(b -> !b.destroyed)
                .map(bl -> new BlockProxy(bl.color, bl.getqHits(), bl.getBonusType(),
                        bl.x, bl.y, bl.width, bl.height, bl.dx, bl.dy, bl.hasBlockBonus()))
                .forEach(v -> this.blocks.add(v));

        game.getBonusList().stream()
                .filter(b -> !b.destroyed)
                .map(bl -> new BlockProxy(bl.color, bl.getqHits(), bl.getBonusType(),
                        bl.x, bl.y, bl.width, bl.height, bl.dx, bl.dy, bl.hasBlockBonus()))
                .forEach(v -> this.bonuses.add(v));
    }

    @Override
    public String toString() {
        return "StateDTO{" +
                "balls=" + balls +
                ", blocks=" + blocks +
                ", bonuses=" + bonuses +
                ", frameWidth=" + frameWidth +
                ", frameHeight=" + frameHeight +
                ", level=" + level +
                ", scoreTotal=" + scoreTotal +
                ", life=" + life +
                '}';
    }
}



