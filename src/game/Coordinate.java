package game;

public class Coordinate {
    public int x=0;
    public int y=0;
    public Coordinate(){

    }
    public Coordinate(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public Coordinate copy(){
        return new Coordinate(this.x, this.y);

    }
    public boolean isInRange(Coordinate c2, int range) {
        return range >= Math.sqrt(Math.pow((this.x - c2.x), 2) + Math.pow((this.y - c2.y), 2));
    }

}