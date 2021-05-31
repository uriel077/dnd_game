package unit.player;

import game.GameManager;
import game.Coordinate;
import game.UI;
import handlers.TargetHandler;
import unit.Unit;
import unit.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player {

    private int abilityCooldown;
    private int remainingCooldown = 0;
    private final String ABILITY_NAME="Avenger's Shield";
    private final int ABILITY_RANGE=3;


    public Warrior(String name, char tile, int health, int attack, int defence, int cooldown) {
        super(name, tile, health, attack, defence);
        this.abilityCooldown = cooldown;
    }

    @Override
    public List<String> castAbility() {
        List<Enemy> potenTarget = TargetHandler.candidateTarget(this, this.getCoordinate(), this.abilityRange);
        List<String> message = new ArrayList<String>();
        int healBuff=10* getDefencePoints();
        message.add(this.getName() + " cast " + this.abilityName+", healing for "+healBuff+".");
        this.setCurrentHealth(getCurrentHealth() + healBuff);

        if(potenTarget.size()>0) {
            int random = new Random().nextInt(potenTarget.size());
            message.addAll(this.attack(potenTarget.get(random)));
        }
          return message;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        remainingCooldown = 0;
        this.getHealth().healthPool+=5*this.playerLevel;
        this.setAttackPoints(this.getAttackPoints()+this.playerLevel*2);
        this.setDefencePoints(this.getDefencePoints()+playerLevel);

    }

    @Override
    public List<String> turn(int turnCount) {
        List<String> messages=super.turn(turnCount);
        remainingCooldown=Math.max(remainingCooldown-1,0);
        return messages;
    }
    @Override
    public String description() {
        return super.description()+"\tCooldown: "+remainingCooldown+"/"+abilityCooldown;
    }
    @Override
    public Warrior copy()
    {
        return new Warrior(this.getName(),this.toString().charAt(0),this.getCurrentHealth(),this.getAttackPoints(),this.getDefencePoints(),this.abilityCooldown);
    }
    @Override
    public List<String> tryCastAbility(){
        List<String> messages=super.tryCastAbility(abilityCooldown,remainingCooldown);
        if(messages.size()==0)
            messages.add(this.getName()+" tried to cast "+this.abilityName+", but there is a cooldown : "+remainingCooldown);
        return messages;

    }
}
