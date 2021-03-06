package unit;

import enums.UserInput;
import game.Coordinate;
import game.GameManager;
import game.Health;
import game.UI;

import java.util.Random;
public abstract class Unit {

    protected String name;
    protected Health health = new Health(0,0);
    protected int attackPoints;
    protected int defencePoints;
    protected Coordinate coordinate = new Coordinate();
    protected char tile;
    protected Random rnd = new Random();
    public static GameManager gameManager;

    public Unit(){}
    public Unit(String name, char tile, Health hp, int ap, int dp, Coordinate pos){
        this.name = name;
        this.health = hp;
        this.attackPoints = ap;
        this.defencePoints = dp;
        this.coordinate = pos;
        this.tile = tile;

    }

    public Unit(String name, char tile, int hp, int ap, int dp){
        this( name,  tile,  new Health(hp,hp),  ap,  dp, new Coordinate());
    }

    public Unit(String name, char tile, int hp, int ap, int dp,Coordinate pos){
        this( name,  tile,  new Health(hp,hp),  ap,  dp, pos);
    }


    public abstract Unit copy();

    public void attack(Unit defender){
        this.attack(defender,this.getAttackPoints());
    }

    public void  attack(Unit defender,int ap){
        int ar = rnd.nextInt(ap + 1);
        UI.print(getName() + " rolled " + ar + " attack points.");
        int [] combatInfo = defender.defence(ar);
        UI.print(defender.getName() + " rolled " + combatInfo[0] + " defence points.");
        UI.print(getName() + " dealt " + combatInfo[1] + " damage to " + defender.getName() + ".");
    }

    public int[] defence(int ar){
        int [] combatInfo = new int[2];
        combatInfo[0] = rnd.nextInt(getDefencePoints());
        combatInfo[1] = Math.max(ar - combatInfo[0], 0);
        this.setCurrentHealth(getCurrentHealth() - combatInfo[1]);
        return combatInfo;
    }


    public abstract void move(UserInput moveDir);

    public abstract void turn(int tick);


    @Override
    public String toString() {
        return tile+"";
    }

    public String getName() {
        return name;
    }

    public String description(){
        return getName() + "\t\t" + "Health: " + getCurrentHealth() + "/" + getHealth().healthPool + "\t\t" +
                "Attack: " + getAttackPoints() + "\t\t" + "Defence: " + getDefencePoints();
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

    public void setDefencePoints(int dp) {
        this.defencePoints = dp;
    }

    public void setTile(char tile) {
        this.tile = tile;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(int ha, int hp){
        ha = Math.max(ha, 0);
        ha = Math.min(ha,hp);
        health = new Health(ha, hp);
    }

    public int getCurrentHealth() {
        return health.healthAmount;
    }

    public void setCurrentHealth(int currentHealth){
        this.setHealth(currentHealth, this.health.healthPool);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate cor){
        this.coordinate = cor;
    }
    public void setCoordinate(int i, int j) {
        this.coordinate.x = i;
        this.coordinate.y = j;
    }

    public boolean isDead(){
        return health.isDead();
    }
}
