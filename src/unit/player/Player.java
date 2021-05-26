package unit.player;

import enums.UserInput;
import unit.Unit;

import java.util.Arrays;
import java.util.List;

public class Player extends Unit {

    private int experience=0;
    private int playerLevel =1;
    public String abilityName="";
    public int abilityRange=0;
    public Player(String name, char tile, int health, int attack, int defence) {
        //super( name, tile,  health, attack, defence);
    }

//    @Override
//    public String description() {
//        return super.description()+"\tLevel: "+playerLevel+"\tExperience:"+getExperience()+"/"+getExperience()*playerLevel;
//    }

    public int getExperience() {
        return experience;
    }
    private void levelUp(){
        this.experience -= 50*this.playerLevel;
        this.playerLevel+=1;
        //this.getHealth().healthPool+=10*this.playerLevel;
        //this.setCurrentHealth(this.getHealth().healthPool);
        //this.setAttack(getAttack()+4*this.playerLevel);
        //this.setDefence(getAttack()+1*this.playerLevel);
    }
    public void setExperience(int experience) {
        this.experience = experience;
        while (experience>=50*getExperience())
            levelUp();
    }

    public List<String> tryCastAbility(int resource,int cost){
        if(resource-cost>=0)
            return castAbility();
        else
            return Arrays.asList(new String[]{this.name+" tried to cast "+this.abilityName+", but there was not enough energy: "+resource+"/"+cost});
    }
    public List<String> turn(int turnCount){
        UserInput input=UserInput.Wait;
        //input=InputHandler.getInput
        if(input==UserInput.CastAbility)
           return this.castAbility();
       // else
            //return MoveHandler(this,input);
        return null;
    }
    public  List<String> castAbility(){
        return null;
    }
}
