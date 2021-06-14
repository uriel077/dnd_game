package unit.player;

import enums.UserInput;
import game.Health;
import game.HeroicUnit;
import game.UI;
import handlers.InputHandler;
import handlers.MoveHandler;
import unit.Unit;
import unit.enemy.Enemy;

public class Player extends Unit implements HeroicUnit {

    protected int experience=0;
    protected int playerLevel =1;
    protected String abilityName="";
    protected int abilityRange=0;
    private int LEVEL_SIZE=50;

    public Player(){}
    public Player(String name, char tile, int health, int attack, int defence) {
        super( name, tile, health, attack, defence);

    }
    public Player(String name, char tile, Health health, int attack, int defence) {
        super( name, tile, health.healthAmount, attack, defence);

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
        while (this.experience>=LEVEL_SIZE*playerLevel) {
            int[] saveState=new int[]{this.getHealth().healthPool,this.getAttackPoints(),this.getDefencePoints()};
            levelUp();
            UI.print(this.getName() + " reached level " + this.playerLevel + ": +"+(this.getHealth().healthPool-saveState[0])+" Health, +"+(this.getAttackPoints()-saveState[1])+" Attack, +"+(this.getDefencePoints()-saveState[2])+" Defense");
        }
    }

    private void gainXp(int xp){

        setExperience(getExperience()+xp);
    }
    public void castAbility(Enemy defender,int ap){
        int [] combatInfo = defender.defence(ap);
        UI.print(defender.getName() + " rolled " + combatInfo[0] + " defence points.");
        UI.print(getName() + " hit " + defender.getName() + " for " + combatInfo[1] + " ability damage.");
        if(defender.isDead()) {
            UI.print(defender.getName() + " died. " + this.getName() + " gained " + defender.getExperienceValue() + " experience");
            gainXp(defender.getExperienceValue() );
        }
    }


    public void attack(Enemy defender ){
       super.attack(defender);
        if(defender.isDead()){
            UI.print(defender.getName()+" died. "+this.getName()+" gained "+defender.getExperienceValue() +" experience");
            gainXp(defender.getExperienceValue() );
        }

    }
    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public Boolean tryCastAbility(int resource, int cost){
        if(resource-cost>=0){
             castAbility();
             return true;
        }
        return false;

    }
    public void tryCastAbility(){

        }

    @Override
    public void move(UserInput moveDir) {
        MoveHandler.move(moveDir, this);
    }
@Override
    public void turn(int turnCount){
        UserInput input=InputHandler.inputPlayerGame();
        if(input==UserInput.CastAbility)
            this.tryCastAbility();
        else
            move(input);
    }
    public  void castAbility(){

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
