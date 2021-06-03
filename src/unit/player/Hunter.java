package unit.player;

import game.UI;
import handlers.TargetHandler;
import unit.Unit;
import unit.enemy.Enemy;

import java.util.List;

public class Hunter extends Player{

    private int arrowCount=0;
    private int tickCount=0;
    private final int ARROW_COST=1;

    public Hunter(String name,char tile,int health,int attack,int defence,int range){
        super(name,tile,health,attack,defence);
        this.abilityRange=range;
        this.arrowCount=10*playerLevel;
    }
    @Override
    public void castAbility(){
        List<Enemy> potenTarget= TargetHandler.candidateTarget(this,this.getCoordinate(),this.abilityRange);
        if(potenTarget.size()>0) {

            Enemy closetEnemy=potenTarget.get(0);
            for (Enemy target : potenTarget) {
                if(this.distance(target)<this.distance(closetEnemy))
                    closetEnemy=target;
            }
            UI.print(this.getName() + "  fired an arrow at " + closetEnemy.getName()+".");
            this.castAbility(closetEnemy, this.getAttackPoints());
            this.arrowCount -= ARROW_COST;
        }
        else
        UI.print(this.getName() + "tried to shoot an arrow but there were no enemies in range.");
    }

    public double distance(Unit unit){
        return this.getCoordinate().distance(unit.getCoordinate());
    }

    @Override
    public void tryCastAbility(){
        boolean cast=super.tryCastAbility(this.arrowCount,ARROW_COST);
        if(!cast)
            UI.print(this.getName() + "tried to shoot an arrow but there were no enemies in range.");
    }
    @Override
    public void levelUp(){
        super.levelUp();
        this.arrowCount+=10*this.playerLevel;
        this.setAttackPoints(this.getAttackPoints()+this.playerLevel*2);
        this.setDefencePoints(this.getDefencePoints()+playerLevel);
    }
    @Override
    public void turn(int turnCount){
        super.turn(turnCount);
        if(tickCount==10){
            arrowCount+=playerLevel;
            tickCount=0;
        }
        else
            tickCount+=1;

    }

    @Override
    public String description() {
        return super.description()+"\t\tArrows: "+this.arrowCount;
    }

    @Override
    public Hunter copy()
    {
        return new Hunter(this.getName(),this.toString().charAt(0),this.getCurrentHealth(),this.getAttackPoints(),this.getDefencePoints(),this.abilityRange);
    }
}
