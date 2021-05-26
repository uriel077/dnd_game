package game;


import unit.Unit;
import unit.enemy.Monster;
import unit.enemy.Trap;
import unit.player.Mage;
import unit.player.Rogue;
import unit.player.Warrior;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



public class DatabaseUnits {


   public Map<String, Unit > playerPool= new HashMap<String, Unit>();
   public Map<String, Unit > enemyPool= new HashMap<String, Unit>();
   private final String dirAddons="src/addons";

   public DatabaseUnits(){
      buildDictionary();
   }
   private void buildDictionary(){
      buildUnit(dirAddons+"/dbPlayer",playerPool);
      buildUnit(dirAddons+"/dbEnemy",enemyPool);

   }
   private String getFile(String address)  {
      String fileText="";
      try {
         Path fileName = Path.of(address);
         fileText=Files.readString(fileName);
      }
      catch (IOException e){}
      return fileText;
   }
   private void buildUnit(String address,Map<String, Unit > map){
   String txtToSplit=getFile(address);
   ArrayList<String> enemyUnit=  new ArrayList<String>(Arrays.asList(txtToSplit.split("\r\n")));
      for (String unitStr:enemyUnit) {
         ArrayList<String> argumentUnit= new ArrayList<String>(Arrays.asList(unitStr.split("\\|")));
         Unit unitObj=facrotyUnit(argumentUnit);
         map.put(unitObj.tile,unitObj);
      }

   }

   private Unit facrotyUnit( ArrayList<String> typeUnit){
      String type=typeUnit.get(0);
      String name=typeUnit.get(1);
      char tile;
      int health,attack,defence;
      if(type.equals("Monster")){
         tile=typeUnit.get(2).charAt(0);
         health=Integer.parseInt(typeUnit.get(3));
         attack=Integer.parseInt(typeUnit.get(4));
         defence=Integer.parseInt(typeUnit.get(5));
         int visionRange=Integer.parseInt(typeUnit.get(6));
         int expirenceValue=Integer.parseInt(typeUnit.get(7));
         return new Monster(name,tile,health,attack,defence,visionRange,expirenceValue);
      }
      if(type.equals("Trap")){
         tile=typeUnit.get(2).charAt(0);
         health=Integer.parseInt(typeUnit.get(3));
         attack=Integer.parseInt(typeUnit.get(4));
         defence=Integer.parseInt(typeUnit.get(5));
         int visibilityTime=Integer.parseInt(typeUnit.get(7));
         int invisibilityTime=Integer.parseInt(typeUnit.get(8));
         int expirenceValue=Integer.parseInt(typeUnit.get(6));
         return new Trap(name,tile,health,attack,defence,expirenceValue,visibilityTime,invisibilityTime);
      }
      tile='@';
      health=Integer.parseInt(typeUnit.get(2));
      attack=Integer.parseInt(typeUnit.get(3));
      defence=Integer.parseInt(typeUnit.get(4));
      if(type.equals("Warrior")){
         int cooldown=Integer.parseInt(typeUnit.get(5));
         return new Warrior(name,tile,health,attack,defence,cooldown);
      }
      if(type.equals("Rogue")){
         int cost=Integer.parseInt(typeUnit.get(5));
         return new Rogue(name,tile,health,attack,defence,cost);
      }
      if(type.equals("Mage")){
         int manaPool=Integer.parseInt(typeUnit.get(5));
         int manaCost=Integer.parseInt(typeUnit.get(6));
         int spellPower=Integer.parseInt(typeUnit.get(7));
         int hitCount=Integer.parseInt(typeUnit.get(8));
         int range=Integer.parseInt(typeUnit.get(9));
         return new Mage(name,tile,health,attack,defence,manaPool,manaCost,spellPower,hitCount,range);
      }

      return null;
   }
}
