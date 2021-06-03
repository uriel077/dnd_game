package game;

import java.util.Objects;

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
        return range >= this.distance(c2);
    }
    public double distance(Coordinate c2){
        return Math.sqrt(Math.pow((this.x - c2.x), 2) + Math.pow((this.y - c2.y), 2));
    }

    @Override
    public String toString() {
        return "x:"+x+", y:"+y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}