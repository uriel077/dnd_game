package unit.enemy;

import enums.UserInput;
import handlers.TargetHandler;
import unit.Unit;
import unit.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Monster extends Enemy {
    private int visionRange=0;
    private UserInput rndArrs[]={UserInput.Wait,UserInput.Right,UserInput.Left,UserInput.Up,UserInput.Down};
    public Monster(String name,char tile,int hp,int ap,int dp,int xp,int visionRange){
        super(name, tile, hp, ap, dp,xp);
        this.visionRange=visionRange;
    }
    @Override
    public List<String> turn(int tick) {
        List<Player> closePlayer=new ArrayList<>();
        closePlayer= TargetHandler.candidateTarget(this,visionRange);
        if(closePlayer.size()>0){
        int dx=this.getCoordinate().x-closePlayer.get(0).getCoordinate().x;
        int dy=this.getCoordinate().y-closePlayer.get(0).getCoordinate().y;
//        if(Math.abs(dx)>Math.abs(dy)) {
//            if (dx > 0)
//                return this.move(UserInput.Left);
//            else
//                return this.move(UserInput.Right);
//        }else{
//            if (dy > 0)
//                return this.move(UserInput.Up);
//            else
//                return this.move(UserInput.Down);
//        }
        }
        else
        {

           //return this.move(rndArrs[(new Random().nextInt(6))]) ;
        }
        return null;
    }
    @Override
    public Unit copy() {
        return new Monster(this.getName(), this.toString().charAt(0), this.getCurrentHealth(), this.getAttackPoints(), this.getDefencePoints()
                ,this.experienceValue,this.visionRange);
    }
}
