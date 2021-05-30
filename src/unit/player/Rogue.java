package unit.player;

import handlers.TargetHandler;
import unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class Rogue extends Player{
    private int currentEnergy;
    private int cost;
    private int MAX_ENERGY=100;

    public Rogue(String name,char tile,int health,int attack,int defence,int cost){
        super(name,tile,health,attack,defence);
            this.abilityName="Fan of Knives";
        this.abilityRange=2;
        this.cost=cost;
    }

    @Override
    public List<String> castAbility(){
        List<Unit> potenTarget= TargetHandler.candidateTarget(this,this.getCoordinate(),this.abilityRange);
        List<String> message=new ArrayList<String>();
       message.add(this.getName()+" cast "+this.abilityName);
        for(Unit target:potenTarget){
            message.addAll(this.attack(target));
        }
        this.currentEnergy-=cost;
        return message;
    }
    @Override
    public List<String> tryCastAbility(int resource, int cost){
        return super.tryCastAbility(currentEnergy,cost);
    }
    @Override
    public void levelUp(){
        super.levelUp();
        this.currentEnergy=MAX_ENERGY;
        this.setAttackPoints(this.getAttackPoints()+this.playerLevel*3);
    }
    @Override
    public List<String> turn(int turnCount){
        List<String> messages=super.turn(turnCount);
        currentEnergy=Math.min(MAX_ENERGY,currentEnergy+10);
        return messages;
    }
    @Override
    public String description() {
        return super.description()+"\tEnergy: "+currentEnergy+"/"+MAX_ENERGY;
    }

    @Override
    public Rogue copy()
    {
        return new Rogue(this.getName(),this.toString().charAt(0),this.getCurrentHealth(),this.getAttackPoints(),this.getDefencePoints(),this.cost);
    }

}
