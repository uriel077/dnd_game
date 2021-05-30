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
        return new Enemy(this.getName(), this.toString().charAt(0), this.getCurrentHealth(), this.getAttackPoints(), this.getDefencePoints()
                ,this.experienceValue);
    }

    @Override
    public void move(Coordinate moveTo) {

    }

    @Override
    public List<String> turn(int tick) {
    return null;
    }

    @Override
    public void setHealth(int ha, int hp){
        super.setHealth(ha,hp);
        if (this.isDead()){
            gameManager.removeTurn(this);
        }
    }
}
