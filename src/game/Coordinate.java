package game;

public class Coordinate {
    private int x=0;
    private int y=0;
    public Coordinate(){

    }
    public Coordinate(int x, int y) {
        this.x=x;
        this.y=y;
    }
    public boolean isInRange(Coordinate c1, Coordinate c2, int range) {
        return range > Math.sqrt(Math.pow((c1.x - c2.x), 2) + Math.pow((c1.y - c2.y), 2));
    }
}