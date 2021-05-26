package game;


public class Health {
    public int healthAmount;
    public int healthPool;
    public Health(int healthAmount,int healthPool){
        this.healthAmount=healthAmount;
        this.healthPool=healthPool;
    }
    public boolean isDead() {
       return healthPool<=0;
    }
}
