import game.GameManager;
import game.UI;


public class main {
    public static void main(String[] args) {
       // String address="D:\\study\\data_structre\\dnd ex\\levels_dir";// "C:\\Users\\kirbi\\Downloads\\D&D Test\\levels";
        //"D:\\study\\data_structre\\dnd ex\\levels_dir";
        GameManager gameManager=new GameManager();
        gameManager.start(args[0]);

    }
}

