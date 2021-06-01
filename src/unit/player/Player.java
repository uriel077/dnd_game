package unit.player;

import enums.UserInput;
import game.Health;
import handlers.InputHandler;
import handlers.MoveHandler;
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
    private int LEVEL_SIZE=50;
    public List<String> messageContainer=new ArrayList<>();
    public Player(String name, char tile, int health, int attack, int defence) {
        super( name, tile, health, attack, defence);

    }
    public Player(String name, char tile, Health health, int attack, int defence) {
        super( name, tile, health.healthAmount, attack, defence);//TODO: check again the constructor

    }

    @Override
    public String description() {
        return super.description()+"\t\tLevel: "+playerLevel+"\t\tExperience:"+getExperience()+"/"+LEVEL_SIZE*playerLevel;
    }

    public int getExperience() {
        return experience;
    }

    public void levelUp(){
        this.experience -=LEVEL_SIZE*this.playerLevel;
        this.playerLevel+=1;
        this.getHealth().healthPool+=10*this.playerLevel;
        this.setCurrentHealth(this.getHealth().healthPool);
        this.setAttackPoints(this.getAttackPoints()+4*this.playerLevel);
        this.setDefencePoints(getDefencePoints()+ this.playerLevel);
         }

    public void setExperience(int experience) {
        this.experience = experience;
        while (experience>=LEVEL_SIZE*playerLevel) {
            int[] saveState=new int[]{this.getHealth().healthPool,this.getAttackPoints(),this.getDefencePoints()};
            levelUp();
            this.messageContainer.add(this.getName() + " reached level " + this.playerLevel + ": +"+(this.getHealth().healthPool-saveState[0])+" Health, +"+(this.getAttackPoints()-saveState[1])+" Attack, +"+(this.getDefencePoints()-saveState[2])+" Defense");
        }
    }
    private void gainXp(int xp){

        setExperience(getExperience()+xp);
    }


    public List<String> attack(Enemy defender){
       messageContainer.addAll(super.attack(defender));
        if(defender.isDead()){
            messageContainer.add(defender.getName()+" died. "+this.getName()+" gained "+defender.experienceValue+" experience");
            gainXp(defender.experienceValue);
        }
        return messageContainer;
    }
    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public List<String> tryCastAbility(int resource, int cost){
        if(resource-cost>=0)
             castAbility();
        return messageContainer;
    }
    public List<String> tryCastAbility(){
        return null;
        }

    @Override

    public void move(UserInput moveDir) {
        MoveHandler.move(moveDir, this);

    }
@Override
    public List<String> turn(int turnCount){
        messageContainer=new ArrayList<String>();
        UserInput input=InputHandler.inputPlayerGame();
        if(input==UserInput.CastAbility)
            this.tryCastAbility();
        else
            move(input);
        return messageContainer;
    }
    public  List<String> castAbility(){
        return null;
    }

    public Player copy()
    {
       return new Player(this.getName(),this.toString().charAt(0),this.getCurrentHealth(),this.getAttackPoints(),this.getDefencePoints());
    }
    @Override
    public void setHealth(int ha, int hp){
      super.setHealth(ha,hp);
        if (this.isDead()){
            this.setTile('X');
            gameManager.removeTurn(this);
        }
    }
}
