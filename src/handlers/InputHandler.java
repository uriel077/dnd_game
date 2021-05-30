package handlers;

import enums.UserInput;
import game.DatabaseUnits;

import java.util.Scanner;

public class InputHandler {

    private static String userInputRegex=UserInput.CastAbility.getRegex();
    private static String menuRegex="[1-"+ DatabaseUnits.playerPool.size() +"]";

    public static UserInput inputPlayerGame(){
        return UserInput.valueOf(inputCache(userInputRegex)+"");

    }
    public static  char inputMenu(){
    return inputCache(menuRegex);
    }

    private static char inputCache(String regex){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String input="";
        do{
            input=myObj.nextLine();
        }while (!validateWithRegex(input,regex));
        return input.charAt(0);
    }


    private static Boolean validateWithRegex(String s,String regex){
        if(s.length()==1)
            if(s.matches(regex))
                return true;
        return false;
    }
}
