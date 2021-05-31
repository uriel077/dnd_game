package handlers;

import enums.UserInput;
import game.Coordinate;
import game.GameBoard;
import game.UI;
import unit.Unit;
import unit.enemy.Enemy;
import unit.player.Player;

public class MoveHandler {
    public static GameBoard gameBoard;

    public void move(UserInput input, Player player){
        Coordinate moveTo = joyStick(input, player.getCoordinate());
        Enemy target = isAttack(moveTo, player);
        if (target == null){
            player.setCoordinate(moveTo);
        }
        else {
            UI.print(player.getName() + " engaged in combat with " + target.getName());
            UI.print(player.description());
            UI.print(target.description());
            player.attack(target);
        }

    }

    public void move(UserInput input, Enemy enemy){
        Coordinate moveTo = joyStick(input, enemy.getCoordinate());
        Player target = isAttack(moveTo, enemy);
        if (target == null){
            enemy.setCoordinate(moveTo);
        }
        else {
            UI.print(enemy.getName() + " engaged in combat with " + target.getName());
            UI.print(enemy.description());
            UI.print(target.description());
            enemy.attack(target);
        }

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

    private Enemy isAttack(Coordinate newPos, Player player){
        return TargetHandler.candidateTarget(player, 0).get(0);
    }

    private Player isAttack(Coordinate newPos, Enemy player){
        return TargetHandler.candidateTarget(player, 0).get(0);
    }
}