package game;

import handlers.InputHandler;
import unit.Unit;
import unit.enemy.Enemy;
import unit.player.Player;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class GameManager {
    public GameBoard gameBoard= new GameBoard();
    public List<Unit> listTurn=new ArrayList<Unit>();
    private int tickCount=0;
    private  List<File> levelsFiles=new ArrayList<File>();
    public GameManager(){
        DatabaseUnits.buildDictionary();
    }

    public void start(String address){
        Player p = getPlayerMenu();
        createListOfLevel(address);
        for (File level:levelsFiles) {
            // if(gameBoard.player.isDead())
            //  break;
            loadGame(level);
            startLevel();
        }
        // if(gameBoard.player.isDead())
        //  UI.printLevel("Game Over.");
        //else
        //  UI.printLevel("You Won.");
    }

    private void startLevel() {
        List<String> msg=new ArrayList<String>();
        tickCount=0;
      //  while(!gameBoard.player.isDead()&&gameBoard.size()!=0)
        {
            //UI.printLevel(player,msg)
            tickCount+=1;
            msg=onTick();
        }
      //  if(gameBoard.player.isDead()){
        //msg.add("You Lost");

        //UI.printLevel(msg);
    }

    /**
     * get the player choose
     * @return the choosen player
     */
    private Player getPlayerMenu(){
        //UI.printMenu(gameBoard.players);
        char choose= InputHandler.inputMenu();
        return (Player) DatabaseUnits.playerPool.get(choose);//.copy();

    }

    /**
     * create list of files
     * @param address the dir they are locate
     */
    public void createListOfLevel(String address){
        File f = new File(address);
        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return  name.endsWith("txt");
            }
        });
        levelsFiles= Arrays.asList(matchingFiles);
        levelsFiles.sort((File f1,File f2)->f1.getName().compareTo(f2.getName()));
    }

    public void loadGame(File file){
      //  gameBoard.buildBoard(file);
    }
    public List<String> onTick(){
        List<String> message=new ArrayList<String>();
        ListIterator<Unit> iter = listTurn.listIterator();
        while(iter.hasNext()){
            //   if(!gameBoard.player.isDead())
            //message.add(unit.turn(tickCount));
            //else{
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

    }
    public void removeTurn(Player p){

    }
    public void removeTurn(Enemy e){
        listTurn.remove(e);

    }
}
