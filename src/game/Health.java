package game;


public class Health {
    public int healthAmount=0;
    public int healthPool=0;
    public Health(){

    }
    public Health(int healthAmount,int healthPool){
        this.healthAmount=healthAmount;
        this.healthPool=healthPool;
    }
    public boolean isDead() {
       return healthAmount<=0;
    }
    @Override
    public String toString() {
        return "health amount:"+healthAmount+", health pool:"+healthPool;
    }
}
