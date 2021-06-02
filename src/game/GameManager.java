package game;

import handlers.InputHandler;
import handlers.MoveHandler;
import handlers.TargetHandler;
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
    List<String> messages=new ArrayList<String>();
    public GameBoard gameBoard= new GameBoard();
    public List<Unit> listTurn=new ArrayList<Unit>();
    private int tickCount=0;
    private  List<File> levelsFiles=new ArrayList<File>();
    public GameManager(){
        DatabaseUnits.buildDictionary();
        UI.gameBoard=this.gameBoard;
        TargetHandler.gameBoard=this.gameBoard;
        MoveHandler.gameBoard=this.gameBoard;
        Unit.gameManager=this;
    }

    public void start(String address){
        getPlayerMenu();
        createListOfLevel(address);
        for (File level:levelsFiles) {
             if(gameBoard.player.isDead())
              break;
            loadGame(level);
            startLevel();
        }
        if(gameBoard.player.isDead())
          UI.printLevel("Game Over.");
        else
         UI.printLevel("You Won.");
    }

    private void startLevel() {
        List<String> message=new ArrayList<String>();
        List<String> msg=new ArrayList<String>();
        tickCount=0;
        while(!gameBoard.player.isDead()&&gameBoard.enemies.size()!=0)
        {
            UI.printLevel();
            message.clear();
            tickCount+=1;
          onTick();
        }if(gameBoard.player.isDead())
            message.add("You Lost");
        UI.printLevel();
    }

    /**
     * get the player choose
     * @return the choosen player
     */
    private void getPlayerMenu(){
        UI.printMenu();
        char choose= InputHandler.inputMenu();
        gameBoard.player=(Player) DatabaseUnits.playerPool.get(choose+"").copy();
        UI.printChoosenPlayer();

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
        gameBoard.buildBoard(file);
        listTurn.clear();
        listTurn.add(gameBoard.player);

        for(Unit enemy:gameBoard.enemies){
            listTurn.add(enemy);
        }
    }
    public void onTick(){
        ListIterator<Unit> iter = listTurn.listIterator();
        while(iter.hasNext()&&!gameBoard.player.isDead()){
                   Unit unit= iter.next();
                   if(unit.isDead()){
                       iter.remove();
                    continue;
                   }
                  unit.turn(tickCount);
        }
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
//        listTurn.remove(e);
       gameBoard.enemies.remove(e);

    }
}
