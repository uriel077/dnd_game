package unit;
import game.Coordinate;
import handlers.MoveHandler;
import game.Health;
import unit.enemy.Enemy;

import java.util.Random;
public abstract class Unit {
    private String name;
    private Health health = new Health(0,0);
    private int attackPoints;
    private int defencePoints;
    private Coordinate coordinate = new Coordinate();
    private String tile;
    private Random rnd = new Random();

    public Unit(String name, Health hp, int ap, int dp, Coordinate pos, String tile){
        this.name = name;
        this.health = hp;
        this.attackPoints = ap;
        this.defencePoints = dp;
        this.coordinate = pos;
        this.tile = tile;

    }

    public String[] attack(Unit defender){
        int ar = rnd.nextInt(this.getAttackPoints() + 1);
        System.out.println(name + " rolled " + ar + "attack points.");
        int damage = defender.defence(ar);
        System.out.println(name + " dealt " + damage + " damage to" + defender + ".");
    }

    public int defence(int ar){
        int dr = rnd.nextInt(getDefencePoints());
        System.out.println(name + " rolled " + dr + " defence points.");
        int damage = Math.max(ar - dr, 0);
        this.health.healthAmount -= damage;
        return dr;
    }

    public abstract void move(Coordinate moveTo);


    public void turn(int tick){

    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getDefencePoints() {
        return defencePoints;
    }

    public void setAttackPoints(int ap) {
       this.attackPoints = ap;
    }

    public int setDefencePoints(int dp) {
        this.defencePoints = dp;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getName() {
        return name;
    }
    public Health getHealth() {
        return health;
    }

    public int getCurrentHealth() {
        return health.healthAmount;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
