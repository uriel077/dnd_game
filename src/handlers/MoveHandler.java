package handlers;

import enums.UserInput;
import game.Coordinate;
import game.GameBoard;
import game.UI;
import unit.Unit;
import unit.enemy.Enemy;
import unit.player.Player;

import java.util.List;

public class MoveHandler {
    public static GameBoard gameBoard;

    public static void move(UserInput input, Player player){
        Coordinate moveTo = joyStick(input, player.getCoordinate());
        Enemy target = targetCondidate(moveTo, player);
        if (target == null){
            player.setCoordinate(moveTo);
        }
        else {
            combat(player, target);
            player.attack(target);
        }

    }

    public static void move(UserInput input, Enemy enemy){
        Coordinate moveTo = joyStick(input, enemy.getCoordinate());
        if(isOnEnemy(moveTo))
            return;
        Player target = targetCondidate(moveTo, enemy);
        if (target == null){
            enemy.setCoordinate(moveTo);
        }
        else {
            combat(enemy, target);
            enemy.attack(target);
        }

    }

    public static void combat(Unit unit1, Unit unit2){
        UI.print(unit1.getName() + " engaged in combat with " + unit2.getName());
        UI.print(unit1.description());
        UI.print(unit2.description());

    }

    private static Coordinate joyStick(UserInput input, Coordinate pos) {
        Coordinate newPos = pos.copy();
        switch (input) {
            case Right -> newPos.x++;
            case Left -> newPos.x--;
            case Up -> newPos.y--;
            case Down -> newPos.y++;
        }
        if (!isValidMove(newPos))
             newPos = pos.copy();
        return newPos;
    }
    private static boolean isValidMove(Coordinate newPos){
        return (newPos.x>= 0 && newPos.x<= gameBoard.width && newPos.y>= 0 && newPos.y<= gameBoard.height &&
                !gameBoard.walls.containsKey(newPos));
    }

    private static boolean isOnEnemy(Coordinate newPos){
        boolean isSameCor = false;
        int i = 0;
        List<Enemy> enemies = gameBoard.enemies;
        while (!isSameCor && i < enemies.size() ){
            if (enemies.get(i).getCoordinate().equals(newPos))
                isSameCor = true;
            i++;
        }
        return isSameCor;
    }

    private static Enemy targetCondidate(Coordinate newPos, Player player){
        List<Enemy> targets = TargetHandler.candidateTarget(player,newPos, 0);
        return targets.size() == 0 ? null: targets.get(0);
    }

    private static Player targetCondidate(Coordinate newPos, Enemy enemy){
        List<Player> targets = TargetHandler.candidateTarget(enemy,newPos, 0);
        return targets.size() == 0 ? null: targets.get(0);
    }
}
