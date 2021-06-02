import game.GameManager;
import game.UI;
import unit.Unit;
import unit.player.Rogue;

public class main {
    public static void main(String[] args) {
        String address= "C:\\Users\\kirbi\\Downloads\\D&D Test\\levels"; //"D:\\study\\data_structre\\dnd ex\\levels_dir";
        GameManager gameManager=new GameManager();
        gameManager.start(address);
//        Unit m =new Rogue("null",' ',1,1,1,1);
//        UI.print(m.getClass().getSuperclass().getName());
    }
}

