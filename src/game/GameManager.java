package game;

import unit.Unit;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class GameManager {
    public GameBoard gameBoard= new GameBoard();
    public List<Unit> listTurn=new ArrayList<Unit>();
    private int tickCount=0;
    private  List<String> levelsName=new ArrayList<String>();
    public GameManager(){

    }
    public GameManager(String address){
        createListOfLevel(address);
    }
    public void createListOfLevel(String address){
        File f = new File("C:\\example");
        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return  name.endsWith("txt");
            }
        });

    }
    public void loadGame(String address){
        gameBoard.buildBoard(address);
    }
    public List<String> onTick(){
        List<String> message=new ArrayList<String>();
        ListIterator<Unit> iter = listTurn.listIterator();
        while(iter.hasNext()){
            //   if(!gameBoard.player.isDead())
            //message.add(unit.turn(tickCount));
            //else{
            //message.add("Game over");
            // break;
            // }
        }
        return message;
    }

    public int getTickCount() {
        return tickCount;
    }

    public void setTickCount(int tickCount) {
        this.tickCount = tickCount;
        onTick();
    }
}
