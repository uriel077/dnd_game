import game.Coordinate;
import game.DatabaseUnits;
import game.Health;
import handlers.TargetHandler;
import unit.player.Rogue;

public class main {
    public static void main(String[] args) {
        DatabaseUnits db=new DatabaseUnits();
        Health h =new Health(1,5);
        Rogue rog=new Rogue("",'w',1,5,5,5);
        TargetHandler.candidateTarget(rog,new Coordinate(),8);

    }
}
