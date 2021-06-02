package unit.enemy;

import enums.UserInput;
import game.Coordinate;
import unit.Unit;

import java.util.List;

public class Enemy extends Unit {
    public int experienceValue=0;
    public Enemy(String name, char tile, int hp, int ap, int dp, Coordinate pos) {
        super(name, tile, hp, ap, dp,pos);
    }
    public Enemy(String name, char tile, int hp, int ap, int dp,int xp) {
        this(name, tile, hp, ap, dp,new Coordinate());

    }
    public Enemy(String name, char tile, int hp, int ap, int dp,int xp,Coordinate pos) {
        super(name, tile, hp, ap, dp);
        this.experienceValue=xp;
    }

    @Override
    public Unit copy() {
        return new Enemy(this.getName(), this.toString().charAt(0), this.getCurrentHealth(), this.getAttackPoints(), this.getDefencePoints()
                ,this.experienceValue);
    }

    @Override
    public void move(UserInput moveTo) {

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
    @Override
    public String description() {
        return super.description()+"\t\tExperience Value: "+experienceValue;
    }
}
