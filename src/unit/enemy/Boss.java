package unit.enemy;

import enums.UserInput;
import game.HeroicUnit;
import game.UI;
import handlers.TargetHandler;
import unit.Unit;
import unit.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boss extends Enemy implements HeroicUnit {
    private final int visionRange;
    private final int abilityFrequency;
    private int combatTicks = 0;
    private final UserInput[] rndArrs ={UserInput.Wait,UserInput.Right,UserInput.Left,UserInput.Up,UserInput.Down};

    public Boss(String name, char tile, int hp, int ap, int dp, int xp, int visionRange , int abilityFrequency) {
        super(name, tile, hp, ap, dp, xp);
        this.visionRange = visionRange;
        this.abilityFrequency = abilityFrequency;
    }

    @Override
    public Unit copy(){
       return new Boss(this.getName(), this.toString().charAt(0), this.getCurrentHealth(), this.getAttackPoints(), this.getDefencePoints()
               ,this.experienceValue,this.visionRange, this.abilityFrequency);
    }

    public void castAbility(){

    }

    public void castAbility(Player target) {
        UI.print(this.getName() + " cast special ability");
        int[] combatInfo = target.defence(this.getAttackPoints());
        UI.print(target.getName() + " rolled " + combatInfo[0] + " defence points.");
        UI.print(getName() + " hit " + target.getName() + " for " + combatInfo[1] + " ability damage.");
        if(target.isDead()){
            UI.print(target.getName()+" was killed by "+this.getName()+".");
        }
    }

    @Override
    public void turn(int turnCount) {
        super.turn(turnCount);
        List<Player> closePlayer=new ArrayList<>();
        closePlayer= TargetHandler.candidateTarget(this,visionRange);
        if(closePlayer.size()>0) {
            if (this.combatTicks == abilityFrequency) {
                this.combatTicks = 0;
                this.castAbility(closePlayer.get(0));
            } else {
                this.combatTicks += 1;
                int dx = this.getCoordinate().x - closePlayer.get(0).getCoordinate().x;
                int dy = this.getCoordinate().y - closePlayer.get(0).getCoordinate().y;
                if (Math.abs(dx) > Math.abs(dy)) {
                    if (dx > 0)
                        this.move(UserInput.Left);
                    else
                        this.move(UserInput.Right);
                } else {
                    if (dy > 0)
                        this.move(UserInput.Up);
                    else
                        this.move(UserInput.Down);
                }
            }
        }
        else
        {
            this.combatTicks = 0;
            this.move(rndArrs[(new Random().nextInt(4))]) ;
        }
    }

    @Override
    public String description(){return super.description()+"\t\tVision Range: "+this.visionRange +
            "\t\tAbility load :"+ this.combatTicks +"/"+this.abilityFrequency;}

}
