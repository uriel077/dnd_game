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
    private GameBoard gameBoard= new GameBoard();
    private List<Unit> listTurn=new ArrayList<Unit>();
    private int tickCount=0;
    private  List<File> levelsFiles=new ArrayList<File>();

    public GameManager(){
        DatabaseUnits.buildDictionary();
        UI.gameBoard=this.gameBoard;
        TargetHandler.gameBoard=this.gameBoard;
        MoveHandler.gameBoard=this.gameBoard;
        Unit.gameManager=this;
    }
    public void instruction(){
        UI.print("*!*!*!*!*!*!*!*!*! D&D-Roguelike !*!*!*!*!*!*!*!*!*");
        UI.print("*** Game instructions:\n");
        UI.print("* Game Controls:\n");
        UI.print(
                "-Move up:\tW\n" +
                "-Move down:\tS\n" +
                "-Move right:\tD\n" +
                "-Move left:\tA\n" +
                "-Wait:\tQ\n" +
                "-Attack: Steping on an enemy\n" +
                "-Cast special Attack:\tE\n");
        UI.print("* Map description:\n");
        UI.print("-(.):\t Free space\n" +
                "-(#):\t Wall\n" +
                "-(@):\t Your player\n");
        UI.print("* Enemies list:\n");
        UI.print("-(s):\t Lannister Solider\n" +
                "-(k):\t Lannister Knight\n" +
                "-(q):\t Queen’s Guard\n" +
                "-(z):\t Wright\n" +
                "-(b):\t Bear-Wright\n" +
                "-(g):\t Giant-Wright\n" +
                "-(w):\t White Walker\n" +
                "^ Traps:\n" +
                "-(B):\t Bonus Trap\n" +
                "-(Q):\t Queen’s Trap\n" +
                "-(D):\t Death Trap\n" +
                "! Bosses:\n" +
                "-(M):\t The Mountain\n" +
                "-(C):\t Queen Cersei\n" +
                "-(N):\t Night’s King\n");
    }
    public void start(String address){
        instruction();
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

        tickCount=0;
        while(!gameBoard.player.isDead()&&gameBoard.enemies.size()!=0)
        {
            UI.printLevel();
            tickCount+=1;
          onTick();
        }if(gameBoard.player.isDead())
            UI.print("You Lost");
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
