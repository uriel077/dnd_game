package unit.player;

import game.UI;
import handlers.TargetHandler;
import unit.enemy.Enemy;

import java.util.List;
import java.util.Random;

public class Mage extends Player{
    private int manaPool;
    private int currentMana;
    private final int manaCost;
    private int spellPower;
    private final int hitCount;

    public Mage(String name,char tile,int health,int attack,int defence,int manaPool,int manaCost,int spellPower,int hitCount,int range){
        super(name,tile,health,attack,defence);
        this.abilityName = "Blizzard";
        this.abilityRange = range;
        this.manaPool = manaPool;
        this.currentMana = manaPool/4;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitCount = hitCount;
    }
    @Override
    public String description(){
        return super.description() + "\t\tMana: " + this.currentMana + "/" + this.manaPool + "\t\tspellPower: " + this.spellPower;}

    @Override
    public void levelUp(){
        super.levelUp();
        this.manaPool += 25*this.playerLevel;
        this.currentMana = Math.min(this.currentMana + this.manaPool/4, this.manaPool);
        this.spellPower += 10*playerLevel;
    }

    @Override
    public void turn(int tick){
        super.turn(tick);
        this.currentMana = Math.min(currentMana + playerLevel, manaPool);
    }

    @Override
    public void castAbility(){
        List<Enemy> potenTargets = TargetHandler.candidateTarget(this,this.getCoordinate(), abilityRange);
        UI.print(this.getName() + " cast " + this.abilityName);
            int hits = 0;
            Random rnd = new Random();
            this.currentMana -= manaCost;
            while(hits < hitCount && 0 < potenTargets.size()){
                Enemy target = potenTargets.get(rnd.nextInt(potenTargets.size()));
                this.castAbility(target, this.spellPower);
                if(target.isDead())
                    potenTargets.remove(target);
                hits++;

            }
    }
    @Override
    public void tryCastAbility(){
        boolean cast = super.tryCastAbility(currentMana , manaCost);
        if (!cast){
            UI.print(this.getName()+" tried to cast "+this.abilityName+", but there was not enough mana: "+currentMana+"/"+manaCost);
        }
    }

    @Override
    public Mage copy(){
        return new Mage(this.getName(), this.toString().charAt(0),this.getCurrentHealth(), this.getAttackPoints()
                ,this.getDefencePoints(),this.manaPool, this.manaCost, this.spellPower,this.hitCount,this.abilityRange);
    }
}
