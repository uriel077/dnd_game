
package game;

import unit.Unit;
import unit.enemy.Enemy;
import unit.player.Player;

import java.io.File;
import java.util.*;

public class GameBoard {
    public int height;
    public int width;
    public Player player;
    public List<Enemy> enemies= new ArrayList<Enemy>();
    public HashMap<Coordinate, Wall> walls = new HashMap<Coordinate, Wall>();
    private String[][]board;
    public void buildBoard(File mapFile,Player player){
        this.player=player;
        buildBoard(mapFile);
    }
    public void buildBoard(File mapFile) {
        walls.clear();
        try {
            Scanner mapReader = new Scanner(mapFile);
            int y = 0, x=0;
            Coordinate Pos = new Coordinate();
            Unit enemy;
            String data;
            while (mapReader.hasNextLine()){
                data = mapReader.nextLine();

                x = 0;
               for (char ch: data.toCharArray()){
                    if (ch == '@') {
                        player.setCoordinate(x, y);
                        player.setTile('@');
                    }
                    if (ch == '#') {
                        Pos = new Coordinate(x, y);
                        walls.put(Pos, new Wall(Pos));
                    }
                    if (DatabaseUnits.enemyPool.containsKey(ch+"")){
                        enemy = DatabaseUnits.enemyPool.get(ch+"").copy();
                        enemy.setCoordinate(x,y);
                        enemies.add((Enemy) enemy);
                    }
                    x++;
                }
                y++;
            }
            this.width = x;
            this.height = y;
            this.board = new String[y][x];
        }
        catch (Exception e){}
    }
    public void buildArray(){
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                board[y][x] = ".";
        board[player.getCoordinate().y][player.getCoordinate().x] =player.toString();
        for (Unit enemy : this.enemies){
            board[enemy.getCoordinate().y][enemy.getCoordinate().x] = enemy.toString();
        }
        for (Coordinate wallPos: this.walls.keySet()){
            board[wallPos.y][wallPos.x] = this.walls.get(wallPos).toString();
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
