package handlers;

import enums.UserInput;
import game.Coordinate;
import game.GameBoard;

public class MoveHandler {
    public static GameBoard gameBoard;

    public void move(UserInput input){
        int playerPosX = gameBoard.player.getCoordinate().x;
        int playerPosY = gameBoard.player.getCoordinate().y;
        switch (input){
            case Up -> playerPosY++;//gameBoard.player.setCoordinate(playerPos[0], playerPos[1]++);
            case Down -> playerPosY--;//gameBoard.player.setCoordinate(playerPos[0], playerPos[1]--);
            case Left -> playerPosX--;//gameBoard.player.setCoordinate(playerPos[0]--, playerPos[1]);
            case Right -> playerPosX++;//gameBoard.player.setCoordinate(playerPos[0]++, playerPos[1]);
            default ->
        }
    }

    public boolean isValidMove(int x, int y){
        return (x>= 0 && x<= gameBoard.width && y>= 0 && y<= gameBoard.length);
    }
}
