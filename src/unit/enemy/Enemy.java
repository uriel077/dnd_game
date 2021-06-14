package unit.enemy;

import enums.UserInput;
import game.Coordinate;
import game.UI;
import handlers.MoveHandler;
import unit.Unit;
import unit.player.Player;

public class Enemy extends Unit {
    private int experienceValue=0;
    public Enemy(){}
    public Enemy(String name, char tile, int hp, int ap, int dp, Coordinate pos) {
        super(name, tile, hp, ap, dp,pos);
    }
    public Enemy(String name, char tile, int hp, int ap, int dp,int xp) {
        this(name, tile, hp, ap, dp,xp,new Coordinate());

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
    public void move(UserInput moveDir) {
        MoveHandler.move(moveDir, this);
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
    @Override
    public void turn(int turnCount){

    }
    public void attack(Player defender){
        super.attack(defender);
        if(defender.isDead()){
            UI.print(defender.getName()+" was killed by "+this.getName()+".");
        }

    }
    public int getExperienceValue(){
        return experienceValue;
    }
}
