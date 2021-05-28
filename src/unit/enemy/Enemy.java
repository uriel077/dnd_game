package unit.enemy;

import game.Coordinate;
import game.Health;
import unit.Unit;

import java.util.List;

public class Enemy extends Unit {
    public int experienceValue=0;
    public Enemy(String name, char tile, int hp, int ap, int dp, Coordinate pos) {
        super(name, tile, hp, ap, dp);
    }
    public Enemy(String name, char tile, int hp, int ap, int dp) {
        super(name, tile, hp, ap, dp);
    }

    @Override
    public void move(Coordinate moveTo) {

    }

    @Override
    public List<String> turn(int tick) {
        return null;
    }
}
