package unit.player;

import game.DatabaseUnits;
import game.GameManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unit.Unit;
import unit.enemy.Enemy;

public class PlayerTest {

    public  Player rogue;
    public  Player mage;
    public  Player hunter;
    public  Player warrior;
    public Enemy trap;
    public Enemy monster;
    public Enemy superDuperMonster;


    @BeforeEach
    public void setUp() throws Exception {
        Unit.gameManager=new GameManager();
        DatabaseUnits.buildDictionary();
        warrior=(Player) DatabaseUnits.playerPool.get("1").copy();
        mage=(Player) DatabaseUnits.playerPool.get("3").copy();
        rogue=(Player) DatabaseUnits.playerPool.get("5").copy();
        hunter=(Player) DatabaseUnits.playerPool.get("7").copy();
        trap=(Enemy) DatabaseUnits.enemyPool.get("B").copy();
        monster=(Enemy) DatabaseUnits.enemyPool.get("k").copy();
        superDuperMonster=new Enemy("Super",'D',2000000,0,200000,20);
    }

    @Test
    public void description() {
        Assert.assertEquals(warrior.description(),"Jon Snow\t\tHealth: 300/300\t\tAttack: 30\t\tDefence: 4\t\tLevel: 1\t\tExperience:0/50\t\tCooldown: 0/3");
        Assert.assertEquals(mage.description(),"Melisandre\t\tHealth: 100/100\t\tAttack: 5\t\tDefence: 1\t\tLevel: 1\t\tExperience:0/50\t\tMana: 75/300\t\tspellPower: 15");
        Assert.assertEquals(rogue.description(),"Arya Stark\t\tHealth: 150/150\t\tAttack: 40\t\tDefence: 2\t\tLevel: 1\t\tExperience:0/50\t\tEnergy: 100/100");
        Assert.assertEquals(hunter.description(),"Ygritte\t\tHealth: 220/220\t\tAttack: 30\t\tDefence: 2\t\tLevel: 1\t\tExperience:0/50\t\tArrows: 10");
    }

    @Test
    public void getExperience() {
        Assert.assertEquals(warrior.getExperience(),0);
        warrior.setExperience(25);
        Assert.assertEquals(warrior.getExperience(),25);
        warrior.setExperience(51);
        Assert.assertEquals(warrior.getExperience(),1);

    }

    @Test
    public void levelUp() {
        Assert.assertEquals(mage.playerLevel,1);
        mage.setExperience(50);
        Assert.assertEquals(mage.playerLevel,2);
        mage.setExperience(250);
        Assert.assertEquals(mage.playerLevel,4);

    }


    @Test
    public void attack() {
        Assert.assertEquals(rogue.playerLevel, 1);
        while (!monster.isDead()) {
            rogue.attack(monster);
        }
        Assert.assertEquals(rogue.playerLevel, 2);

    }
    @Test
    public void setHealth() {
        Assert.assertEquals(hunter.getCurrentHealth(),220);
        hunter.setHealth(hunter.getCurrentHealth()-5, hunter.getCurrentHealth());
        Assert.assertEquals(hunter.getCurrentHealth(),215);
    }
}