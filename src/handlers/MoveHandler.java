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

    public void move(UserInput input, Player player){
        Coordinate moveTo = joyStick(input, player.getCoordinate());
        Enemy target = targetCondidate(moveTo, player);
        if (target == null){
            player.setCoordinate(moveTo);
        }
        else {
//            UI.print(player.getName() + " engaged in combat with " + target.getName());
//            UI.print(player.description());
//            UI.print(target.description());
            combat(player, target);
            player.attack(target);
        }

    }

    public void move(UserInput input, Enemy enemy){
        Coordinate moveTo = joyStick(input, enemy.getCoordinate());
        Player target = targetCondidate(moveTo, enemy);
        if (target == null){
            enemy.setCoordinate(moveTo);
        }
        else {
//            UI.print(enemy.getName() + " engaged in combat with " + target.getName());
//            UI.print(enemy.description());
//            UI.print(target.description());
            combat(enemy, target);
            enemy.attack(target);
        }

    }

    public void combat(Unit unit1, Unit unit2){
        UI.print(unit1.getName() + " engaged in combat with " + unit2.getName());
        UI.print(unit1.description());
        UI.print(unit2.description());

    }

    private Coordinate joyStick(UserInput input, Coordinate pos) {
        Coordinate newPos = pos.copy();
        switch (input) {
            case Right -> newPos.x++;
            case Left -> newPos.x--;
            case Up -> newPos.y++;
            case Down -> newPos.y--;
        }
        if (isValidMove(newPos))
             newPos = pos.copy();
        return newPos;
    }
    private boolean isValidMove(Coordinate newPos){
        return (newPos.x>= 0 && newPos.x<= gameBoard.width && newPos.y>= 0 && newPos.y<= gameBoard.length &&
                !gameBoard.walls.containsKey(newPos) );
    }

    private Enemy targetCondidate(Coordinate newPos, Player player){
        List<Enemy> targets = TargetHandler.candidateTarget(player, 0);
        return targets.size() == 0 ? null: targets.get(0);
    }

    private Player targetCondidate(Coordinate newPos, Enemy enemy){
        List<Player> targets = TargetHandler.candidateTarget(enemy, 0);
        return targets.size() == 0 ? null: targets.get(0);
    }
}
