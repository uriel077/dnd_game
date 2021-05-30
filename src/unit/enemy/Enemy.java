package unit.enemy;

import game.Coordinate;
import unit.Unit;

import java.util.List;

public class Enemy extends Unit {
    public int experienceValue=0;
    public Enemy(String name, char tile, int hp, int ap, int dp, Coordinate pos) {
        super(name, tile, hp, ap, dp);
    }
    public Enemy(String name, char tile, int hp, int ap, int dp,int xp) {
        super(name, tile, hp, ap, dp);
        this.experienceValue=xp;
    }

    @Override
    public Unit copy() {
        return null;
    }

    @Override
    public void move(Coordinate moveTo) {

    }

    @Override
    public List<String> turn(int tick) {
    return null;
    }
}
