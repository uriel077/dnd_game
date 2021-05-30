package game;

public class Wall {
    public Coordinate position;
    public char tile='#';
    public Wall(Coordinate pos){
        this.position = pos;
    }

    @Override
    public String toString() {
        return this.tile+"";
    }
}
