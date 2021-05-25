package game;


public class Health {
    public int healthAmount;
    public int healthPool;

    public boolean isDead() {
       return healthPool<=0;
    }
}
