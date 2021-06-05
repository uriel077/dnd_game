package unit.player;

import game.HeroicUnit;
import game.UI;
import handlers.TargetHandler;
import unit.Unit;
import unit.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Rogue extends Player {
    private int currentEnergy;
    private int cost;
    private final int MAX_ENERGY=100;
    private final String ABILITY_NAME="Fan of Knives";
    private final int ABILITY_RANGE=2;

    public Rogue(String name,char tile,int health,int attack,int defence,int cost){
        super(name,tile,health,attack,defence);
        this.abilityName=ABILITY_NAME;
        this.abilityRange=ABILITY_RANGE;
        this.cost=cost;
        currentEnergy=MAX_ENERGY;
    }

    @Override
    public void castAbility(){
        List<Enemy> potenTarget= TargetHandler.candidateTarget(this,this.getCoordinate(),this.abilityRange);
        UI.print(this.getName()+" cast "+this.abilityName);
        for(Enemy target:potenTarget){
            this.castAbility(target,this.getAttackPoints());
        }
        this.currentEnergy-=cost;

    }

    @Override
    public void tryCastAbility(){
        boolean cast=super.tryCastAbility(currentEnergy,cost);
        if(!cast)
            UI.print(this.getName()+" tried to cast "+this.abilityName+", but there was not enough energy: "+currentEnergy+"/"+cost);

    }
    @Override
    public void levelUp(){
        super.levelUp();
        this.currentEnergy=MAX_ENERGY;
        this.setAttackPoints(this.getAttackPoints()+this.playerLevel*3);
    }
    @Override
    public void turn(int turnCount){
        super.turn(turnCount);
        currentEnergy=Math.min(MAX_ENERGY,currentEnergy+10);

    }
    @Override
    public String description() {
        return super.description()+"\t\tEnergy: "+currentEnergy+"/"+MAX_ENERGY;
    }

    @Override
    public Rogue copy()
    {
        return new Rogue(this.getName(),this.toString().charAt(0),this.getCurrentHealth(),this.getAttackPoints(),this.getDefencePoints(),this.cost);
    }

}
