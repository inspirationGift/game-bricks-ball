package main.com.state;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import main.com.builder.addons.bonus.BonusType;
import main.com.builder.addons.color.BlockColor;

@JsonSerialize
public class BlockProxy {

    @JsonProperty("color")
    public BlockColor color;
    @JsonProperty("bonus")
    public BonusType bonus;
    @JsonProperty("x")
    public int x;
    @JsonProperty("y")
    public int y;
    @JsonProperty("dx")
    public int dx;
    @JsonProperty("dy")
    public int dy;
    @JsonProperty("height")
    public int height;
    @JsonProperty("width")
    public int width;
    @JsonProperty("isBonuses")
    public boolean isBonuses;
    @JsonProperty("hits")
    public int hits;

    public BlockProxy() {
    }

    public BlockProxy(BlockColor color, int hits, BonusType bonus, int x, int y, int width, int height,
                      int dx, int dy, boolean isBonuses) {
        this.bonus = bonus;
        this.hits = hits;
        this.color = color;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.width = width;
        this.height = height;
        this.isBonuses = isBonuses;
    }
}
