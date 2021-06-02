package unit.enemy;

import game.Coordinate;

public class Trap extends Enemy {
    public int visibilityTime=0;
    public int invisibilityTime=0;
    public boolean visible=true;
    public int tickCount=0;

    public Trap(String name, char tile, int hp, int ap, int dp, int xp, int visibilityTime, int invisibilityTime, Coordinate pos){
        super(name, tile, hp, ap, dp,xp,pos);
        this.visibilityTime=visibilityTime;
        this.invisibilityTime=invisibilityTime;
    }
    public Trap(String name, char tile, int hp, int ap, int dp, int xp, int visibilityTime, int invisibilityTime){
        this(name, tile, hp, ap, dp,xp,visibilityTime,invisibilityTime, new Coordinate());

    }
}
