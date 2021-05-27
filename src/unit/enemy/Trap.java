package unit.enemy;

import game.Coordinate;
import unit.Unit;

public class Trap extends Enemy {
    public Trap(String name, char tile, int hp, int ap, int dp, int expirenceValue, int visibilityTime, int invisibilityTime, Coordinate pos){
        super(name, tile, hp, ap, dp, pos);

    }
    public Trap(String name, char tile, int hp, int ap, int dp, int expirenceValue, int visibilityTime, int invisibilityTime){
        super(name, tile, hp, ap, dp);

    }
}
