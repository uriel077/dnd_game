
package game;

import unit.Unit;
import unit.player.Player;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GameBoard {
    private int length;
    private int width;
    public Player player;
    public List<Unit> enemies;
    private HashMap<Coordinate, Wall> walls = new HashMap<Coordinate, Wall>();
    private String[][]board;
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
                        Pos = new Coordinate(i,j); //TODO: delete comment
                        walls.put(Pos, new Wall(Pos));
                    if (DatabaseUnits.enemyPool.containsKey(ch)){
                        enemy = DatabaseUnits.enemyPool.get(ch).copy();
                        enemy.setCoordinate(i,j);
                        enemies.add(enemy);
                    }
                    j++;
                }
                i++;
            }
            this.width = j;
            this.length = i;
            this.board = new String[i][j];
        }
        catch (Exception e){}
    }
    public void buildArray(){
        for (String[] line : board) {
            for (String block : line) {
                block = ".";
            }
        }
        board[player.getCoordinate().x][player.getCoordinate().y] =player.toString();
        for (Unit enemy : this.enemies){
            board[enemy.getCoordinate().x][enemy.getCoordinate().y] = enemy.toString();
        }
        for (Coordinate wallPos: this.walls.keySet()){
            board[wallPos.x][wallPos.y] = this.walls.get(wallPos).toString();
        }
    }

    public String arrayToString(){
        String currBoard = "";
        for (String[] line : board) {
            for (String block : line) {
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
