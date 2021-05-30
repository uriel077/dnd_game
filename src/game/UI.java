package game;

import unit.Unit;

import java.util.List;
import java.util.Map;

public class UI {
    public static GameBoard gameBoard;

    public static void printLevel(List<String> msgs){
        for(String msg : msgs)
            print(msg);
     //   if(gameBoard.enemies.size()>=0) {
        print(gameBoard.toString());
            //   print(gameBoard.player.description());
        //}
    }
    public static void printLevel(String msg){
        print(msg);
    }
    public static void print(String msg){
        System.out.println(msg);
    }
    public static void printMenu(){
        print("Select player:");
        for(Map.Entry<String,Unit> player : DatabaseUnits.playerPool.entrySet())
           print(player.getValue().toString()+"."+player.getValue().description());
    }
        public static void printChoosenPlayer(){
        print("You have selected:");
            //print(gameBoard.players.description());
    }

}
