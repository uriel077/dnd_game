package unit.player;

import handlers.TargetHandler;
import unit.Unit;

import java.util.Arrays;
import java.util.List;

public class Rogue extends Player{
    private int currentEnergy;
    private int cost;


    public Rogue(String name,char tile,int health,int attack,int defence,int cost){
        super(name,tile,health,attack,defence);
            this.abilityName="Fan of Knives";
        this.abilityRange=2;
        this.cost=cost;
    }
    @Override
    public List<String> castAbility(){
        List<Unit> potenTarget= TargetHandler.candidateTarget(this,this.cordinate,this.abilityRange);
        List<String> message=Arrays.asList(new String[]{this.name+" cast "+this.abilityName});
        for(Unit target:potenTarget){
       //     message.add(this.attack(target));
        }
        this.currentEnergy-=cost;
        return message;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
