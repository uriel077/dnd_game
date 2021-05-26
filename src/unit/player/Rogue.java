package unit.player;

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

        this.currentEnergy-=cost;
        return Arrays.asList(new String[]{this.name+" cast "+this.abilityName});
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
