package unit.player;

import game.UI;
import handlers.TargetHandler;
import unit.enemy.Enemy;

import java.util.List;
import java.util.Random;

public class Mage extends Player{
    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int spellPower;
    private int hitCount;
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
        return super.description() + "Mana: " + this.currentMana + "/" + this.manaPool + "spellPower: " + this.spellPower;}

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
        if(potenTargets.size() > 0) {
            UI.print(this.getName() + "" + this.abilityName + );
            String rndTargetsBin = Integer.toBinaryString(new Random().nextInt(2 ^ potenTargets.size()));
            for(int i = potenTargets.size() - rndTargetsBin.length(); i < potenTargets.size(); i++){
                if (rndTargetsBin.charAt(i) == '1') {
                    this.attack(potenTargets.get(i));

                }
            }
        }
    }
    @Override
    public void tryCastAbility(){
        boolean cast = super.tryCastAbility(currentMana , manaCost);
        if (!cast){
            UI.print(this.getName()+" tried to cast "+this.abilityName+", but there was not enough mana: "+currentMana+"/"+manaCost);
        }
    }
}
