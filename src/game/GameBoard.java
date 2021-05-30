
package game;

import unit.Unit;
import unit.enemy.Enemy;
import unit.player.Player;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GameBoard {
    private int length;
    private int width;
    public Player player;
    private List<Unit> enemies;
    private HashMap<Coordinate, Wall> walls = new HashMap<Coordinate, Wall>();
    private char[][]board;
    public void buildBoard(File mapFile) {
        try {
            Scanner mapReader = new Scanner(mapFile);
            int i = 0, j=0;
            Coordinate Pos = new Coordinate();
            Unit enemy;
            String data;
            while (mapReader.hasNextLine()){
                data = mapReader.nextLine();
                j = 0;
               for (char ch: data.toCharArray()){
                    if (ch == '@')
                        player.setCoordinate(i,j);
                    if (ch == '#')
                        //Pos = new Coordinate(i,j); //TODO: delete comment
                        //walls.put(Pos, new Wall(Pos));
                    if (DatabaseUnits.enemyPool.containsKey(data)){
                        enemy = DatabaseUnits.enemyPool.get(data).copy();
                        enemy.setCoordinate(i,j);
                        enemies.add(enemy);
                    }
                    j++;
                }
                i++;
            }
            this.width = j;
            this.length = i;
            this.board = new char[i][j];
        }
        catch (Exception e){}
    }
    public void buildArray(){
        for (char[] line : board) {
            for (char block : line) {
                block = '.';
            }
        }
        //board[player.getCoordinate().x][player.getCoordinate().y] = '@'; TODO: delete comment
        for (Unit enemy : this.enemies){
            //board[enemy.getCoordinate().x][enemy.getCoordinate().y] = enemy.toString();
        }
        for (Coordinate wallPos: this.walls.keySet()){
            // board[wallPos.x][wallPos.y] = '#';
        }
    }

    public String arrayToString(){
        String currBoard = "";
        for (char[] line : board) {
            for (char block : line) {
                currBoard += block;
            }
            currBoard += "\n";
        }
        return currBoard;
    }
    public String toString(){
        buildArray();
        return arrayToString();
    }
}
