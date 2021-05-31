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
    public static List<Enemy> candidateTarget(Player player, Coordinate cord, int range){
        List<Enemy> closeEnemy=new ArrayList<>();
        for (Enemy enemy:gameBoard.enemies)
        { if( cord.isInRange(enemy.getCoordinate(),range))

             closeEnemy.add(enemy);
        }
       return closeEnemy;
    }

    public static List<Player> candidateTarget(Enemy enemy, Coordinate cord, int range){
        List<Player> closePlayer=new ArrayList<>();
         if( cord.isInRange(gameBoard.player.getCoordinate(),range))
             closePlayer.add(gameBoard.player);
        return closePlayer;
    }
    public static List<Player> candidateTarget(Enemy enemy, int range){

        return candidateTarget(enemy,enemy.getCoordinate(),range);
    }
    public static List<Enemy> candidateTarget(Player player, int range){

        return candidateTarget(player,player.getCoordinate(),range);
    }


}
