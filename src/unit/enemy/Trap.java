package unit.enemy;

import game.Coordinate;
import handlers.TargetHandler;
import unit.Unit;
import unit.player.Player;

import java.util.List;

public class Trap extends Enemy {
    private int visibilityTime=0;
    private int invisibilityTime=0;
    private boolean visible=true;
    private int tickCount=0;
    private final int VISION_RANGE=2;
    private  char VISIBLE_TILE;
    private  char INVISIBLE_TILE='.';

    public Trap(String name, char tile, int hp, int ap, int dp, int xp, int visibilityTime, int invisibilityTime, Coordinate pos){
        super(name, tile, hp, ap, dp,xp,pos);
        this.visibilityTime=visibilityTime;
        this.invisibilityTime=invisibilityTime;
        this.VISIBLE_TILE=tile;
    }
    public Trap(String name, char tile, int hp, int ap, int dp, int xp, int visibilityTime, int invisibilityTime){
        this(name, tile, hp, ap, dp,xp,visibilityTime,invisibilityTime, new Coordinate());
    }

    public void turn(int turnCount){
        setVisible(tickCount<visibilityTime);
        if(tickCount==(visibilityTime+invisibilityTime))
            tickCount=0;
        else
            tickCount+=1;
        List<Player> closePlayer= TargetHandler.candidateTarget(this,VISION_RANGE);
        if(closePlayer.size()>0)
            this.attack(closePlayer.get(0));
    }
    @Override
    public Unit copy() {
        return new Trap(this.getName(), this.toString().charAt(0), this.getCurrentHealth(), this.getAttackPoints(), this.getDefencePoints()
                ,this.getExperienceValue() ,this.visibilityTime,invisibilityTime);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        setTile(visible ? VISIBLE_TILE : INVISIBLE_TILE);
    }
}
