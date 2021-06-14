import game.GameManager;



public class main {
    public static void main(String[] args) {
        GameManager gameManager=new GameManager();
        gameManager.start(combiner(args));

    }

    private static String combiner(String[] args)
    {
        String combineString ="";
        for(String str: args)
            combineString+=str+" ";
        return combineString.trim();
    }
}

