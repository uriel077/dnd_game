package handlers;

import game.Coordinate;
import game.GameBoard;
import unit.Unit;
import unit.enemy.Enemy;
import unit.player.Player;

import java.util.ArrayList;
import java.util.List;

public class TargetHandler {
    public static GameBoard gameBoard;
    public static List<Unit> candidateTarget(Player player, Coordinate cord, int range){
        List<Unit> closeEnemy=new ArrayList<>();
        for (Unit enemy:gameBoard.enemies)
        { if( cord.isInRange(enemy.getCoordinate(),range))

             closeEnemy.add(enemy);
        }
       return closeEnemy;
    }

    public static List<Unit> candidateTarget(Enemy enemy, Coordinate cord, int range){
        List<Unit> closePlayer=new ArrayList<>();
         if( cord.isInRange(gameBoard.player.getCoordinate(),range))
             closePlayer.add(gameBoard.player);
        return closePlayer;
    }
    public static List<Unit> candidateTarget(Enemy enemy, int range){

        return candidateTarget(enemy,enemy.getCoordinate(),range);
    }
    public static List<Unit> candidateTarget(Player player, int range){

        return candidateTarget(player,player.getCoordinate(),range);
    }


}
