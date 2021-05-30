package unit.player;

import enums.UserInput;
import game.Coordinate;
import game.Health;
import unit.Unit;
import unit.enemy.Enemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player extends Unit {

    private int experience=0;
    public int playerLevel =1;
    public String abilityName="";
    public int abilityRange=0;
    private List<String> messageContainer=new ArrayList<>();
    public Player(String name, char tile, int health, int attack, int defence) {
        super( name, tile, health, attack, defence);

    }
    public Player(String name, char tile, Health health, int attack, int defence) {
        super( name, tile, health, attack, defence);

    }

    @Override
    public String description() {
        return super.description()+"\tLevel: "+playerLevel+"\tExperience:"+getExperience()+"/"+getExperience()*playerLevel;
    }

    public int getExperience() {
        return experience;
    }

    public void levelUp(){
        this.experience -= 50*this.playerLevel;
        this.playerLevel+=1;
        this.getHealth().healthPool+=10*this.playerLevel;
        this.setCurrentHealth(this.getHealth().healthPool);
        this.setAttackPoints(this.getAttackPoints()+4*this.playerLevel);
        this.setDefencePoints(getDefencePoints()+ this.playerLevel);
         }

    public void setExperience(int experience) {
        this.experience = experience;
        while (experience>=50*playerLevel) {
            int[] saveState=new int[]{this.getHealth().healthPool,this.getAttackPoints(),this.getDefencePoints()};
            levelUp();
            this.messageContainer.add(this.getName() + " reached level " + this.playerLevel + ": +"+(this.getHealth().healthPool-saveState[0])+" Health, +"+(this.getAttackPoints()-saveState[1])+" Attack, +"+(this.getDefencePoints()-saveState[2])+" Defense");
        }
    }
    private void gainXp(int xp){
        setExperience(getExperience()+xp);
    }

    public List<String> attack(Enemy defender){
        List<String> message=super.attack(defender);
        if(defender.isDead())
            gainXp(defender.experienceValue);
        return message;
    }
    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public List<String> tryCastAbility(int resource, int cost){
        if(resource-cost>=0)
            return castAbility();
        else
            return Arrays.asList(new String[]{this.getName()+" tried to cast "+this.abilityName+", but there was not enough energy: "+resource+"/"+cost});
    }

    @Override
    public void move(Coordinate moveTo) {

    }

    public List<String> turn(int turnCount){
        messageContainer.clear();
        UserInput input=UserInput.Wait;
        //input=InputHandler.getInput;
        if(input==UserInput.CastAbility)
           messageContainer.addAll( this.castAbility());
       // else
            //return MoveHandler(this,input);
        return messageContainer;
    }
    public  List<String> castAbility(){
        return null;
    }

    public Player copy()
    {
       return new Player(this.getName(),this.toString().charAt(0),this.getCurrentHealth(),this.getAttackPoints(),this.getDefencePoints());
    }
}
