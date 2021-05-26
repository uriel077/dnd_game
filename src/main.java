import game.Coordinate;
import game.DatabaseUnits;
import game.Health;
import handlers.TargetHandler;
import unit.player.Rogue;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class main {
    public static void main(String[] args) {
        DatabaseUnits db=new DatabaseUnits();
        Health h =new Health(1,5);
        Rogue rog=new Rogue("",'w',1,5,5,5);
        TargetHandler.candidateTarget(rog,new Coordinate(),8);
        List<String> m =new ArrayList<String>();
        m.add("damn");
        m.add("boy");
        m.add("king");
        m.add("d");
        ListIterator<String> iter = m.listIterator();
        while(iter.hasNext()){
            if(iter.next().equals("boy")){
                iter.remove();
            }
        }

    }
}
