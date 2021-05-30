package handlers;

import enums.UserInput;

import java.util.Scanner;

public class InputHandler {

    private static String userInputRegex=UserInput.CastAbility.getRegex();
    private static String menuRegex="[1-7]";
    public static char inputPlayerGame(){
        return inputCach(userInputRegex);

    }
    public static  char inputMenu(){
    return inputCach(menuRegex);

    }
    private static char inputCach(String regex){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String input="";
        do{
            input=myObj.nextLine();
        }while (!validateCharWithRegex(input,regex));
        return input.charAt(0);
    }


    private static Boolean validateCharWithRegex(String s,String regex){
        if(s.length()==1)
            if(s.matches(regex))
                return true;
        return false;
    }
}
