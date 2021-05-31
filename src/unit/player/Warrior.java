package unit.player;

import game.GameManager;
import game.Coordinate;
import game.UI;
import handlers.TargetHandler;
import unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class Warrior extends Player {

    private int abilitycooldown;
    private int remainingCooldown = 0;


    public Warrior(String name, char tile, int health, int attack, int defence, int cooldown) {
        super(name, tile, health, attack, defence);
        this.remainingCooldown = remainingCooldown;
    }

    @Override
    public List<String> castAbility() {
        super.castAbility();
        List<Unit> potenTarget = TargetHandler.candidateTarget(this, this.getCoordinate(), this.abilityRange);
        List<String> message = new ArrayList<String>();
        message.add(this.getName() + " cast " + this.abilityName);
        for (Unit target : potenTarget) {
            //     message.add(getName() + " cast Heal, healing for " + (currentHealth - tmp));
        }
        this.setCurrentHealth(Math.min(getCurrentHealth() + (2 * getDefencePoints()), getCurrentHealth()));
        return message;
    }

    @Override
    public void levelUp(int health, int attack, int defence) {
        super.levelup();
        remainingCooldown = 0;
        this.getHealth().healthPool+=5*this.playerLevel;
        this.setCurrentHealth(this.getHealth().healthPool);
        this.defence += playerLevel;
        this.getDefencePoints(getAttackPoints()+1*this.playerLevel);
    }

    public String toString() {
        return super.toString();
    }

    public void turn() {
        if (remainingCooldown > 0)
            remainingCooldown = remainingCooldown - 1;
    }
}
