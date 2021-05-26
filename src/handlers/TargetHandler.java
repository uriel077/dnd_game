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
      //  for (Enemy enemy:gameBoard.enemy)
        { //if( cord.range(enemy)<=range)

            // closeEnemy.add(enemy);
        }
       return closeEnemy;
    }

    public static List<Unit> candidateTarget(Enemy player, Coordinate cord, int range){
        List<Unit> closePlayer=new ArrayList<>();
        //  for (Player player:gameBoard.player)
        { //if( cord.range(player)<=range)

            // closePlayer.add(player);
        }
        return closePlayer;
    }

}
