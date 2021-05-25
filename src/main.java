import game.DatabaseUnits;
import game.Health;
import unit.player.Player;

public class main {
    public static void main(String[] args) {
        DatabaseUnits m=new DatabaseUnits();
        Health h =new Health(1,5);

        m.jsonWrite(h);
    }
}
