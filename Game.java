import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
public class Game extends JFrame implements Runnable{
   public static int screenNum = 5;
   public boolean running = true;
   private Level3 l3;
   private Toolkit t = Toolkit.getDefaultToolkit();
   public static String[] names = {"Saskatoon Berries", "Dandelion", "Crown Tipped Coral", "Burdock", "Jack Pine", "Lobster Mushroom", "Morel Mushroom", "Cranberry", "Raspberry", "Cattail", "Lily of the Valley", "False Morel", "Gilled Mushroom", "Deadly Galerina", "Poison Ivy", "Baneberry Red", " Blub-bearing Water Hemlock", "Poison Hemlock", "Water Hemlock", "Blue Flag Iris"};
   public static String[] images = {"Images/Saskatoon.jpg", "Images/Dandelion.jpg", "Images/Crown.jpg", "Images/Burdock.jpg", "Images/Jack.jpg", "Images/Lobster.png", "Images/Morel.jpg", "Images/Cranberry.jpg", "Images/Raspberry.jpg", "Images/Cattail.jpg","Images/LilyValley.jpeg","Images/FalseMorel.jpeg","Images/DestroyingAngel.jpeg","Images/DeadlyG.jpeg","Images/PoisonIvy.jpeg","Images/BaneBerryREd.jpeg","Images/BulbBearingWater.jpeg", "Images/PoisonHemlock.jpeg", "Images/WaterHemlock.jpeg","Images/BlueFlagIris.jpeg"  };
   public static Image[] img = new Image[images.length];
   public static String[] descriptions = {"Large purple-blue berries that grow on trees with gray bark and toothed leaves. Grow to about 5 meters tall and are about 3 meters wide. Berries ripen in June or early June.",
   "Distributed everywhere from lawns, roadsides, to gardens. Found in May and August. Bright yellow flower with milky white stem. 5 - 45 cm in height.",
   "Grows on wood. Branch tips have 3-6 points. Resembles sea coral or candelabra. Initially white and then becomes more pale/pink. Found in Spring, Summer, and Fall.",
   "0.5 - 1.5 meters. Pink or purple flowers with large burrs (spiky envelopes of fruit). Food in roadsides and disturbed areas. Found during August to October.",
   "Found year-round in boreal forests and open areas. Short needles in clusters of two that are not twisted together, cones are closer and tight to the branch.", 
   "Bright red/orange mushroom with firm cap/stem. Irregular/deformed appearance. Cap size generally 5-12 cm. Harvested midsummer. Can be found in wooded areas.",
   "Forests, open meadows, and highly disturbed areas can contain Morel. Long heads with ridges and pitted chambers. Hollow inside from tip of cap to bottom of stalk. Harvested in Spring.",
   "Mostly under 20cm in height. Flowers are pink with red/purple berries. Found in wet areas near bogs/ponds/lakes. Harversted from September to November.",
   "Up to 2m in height. Prickly stems with alternate compound leaves with white/green flowers. Found in moist temperate regions. Harvested around summer.",
   "1 to 3 meters in height. Long, slender, stiff leaves. Flowers form tight cyidnrical clusters. Found in marshes, lakes, streams and calm water. Harvested in spring for flower and pollen, fall to spring for roots/shoots.",
   "Lily of the vallies have a really good scent but can be easily mistaken for wild garlic, they live in woodlands and appear during April-June. However, they are very toxic and can cause a variety of symptoms, such as vomiting, nausea, blurry vision, and disruptions to the heart beat.",
   "They consist of many different species but all are lookalikes to edible morels. They mostly appear in the spring and summer growing from the ground. The caps are brown/red-brown and sometimes yellow. The stems have a ligheter colour and the caps are not attached to the stems and caps. As well, a nonedible morel will have a hollow stem instead of an edible Morel's fibrous stem. They are commonly found near man-made disturbancnces and contain a toxic compound which can cause vomiting, dizziness, diarrhea, and death",
   "The destroying angel is a white mushroom with a 12 cm diameter and 15 cm tall stalk. There is a key cup around the stalk surrounding the base. This type of mushroom is only found in North America, however, is one of the deadliest mushrooms in Canada, with toxins damaging the liver/kidneys.",
   "Deadly Galerina's are brown little mushrooms which grow on rotting wood. The stalks are small and have a tender ring, while the caps are tan/brown and only about 5 cm in diameter. They are found in fall. Their toxins can damage the liver and kidneys.",
   "Poison Ivy varies from shrubs to vines and are found in a variety of places such as forests, field, and open areas. They can be found in Ontario and have leaves that are in groups of three. The poison ivy is poisonous to touch and can cause rashes.", 
   "Baneberry red contains bright red berries with tiny black dots on eahc one, they are found growing under shade within forests. As well, they are found in Ontario and grow to a heigh of 30-60cm. The berries are poisonous and 5-6 berries consumed can cause severe illness, with even more causing death.", 
   "The Blub-bearing water Hemlock is a spindly plant with narrow/fine leaves. They are generally found in wet areas such as marhes, shores, and swamps in Ontario, growing to a height of 1-2 meters. Flowers are found in the summer and are white with 5 petals in umbrella-like clusters. The stem is also hollow as well with many widely spaced branches coming from the stem. This plant is deadly poisonous.",
   "The Poison Hemlock is a plant with a spotted stem and white flowers occuring around summer. It contains 5 white petals and are located in fields and open areas in Ontario. Consuming them is deadly, as they are deadly poisonous.",
   "The Water Hemlock is a plant found in wet areas such as marshes, swamps, and shore lines. They flower during the summer and contain 5 white petals in an umbrella-like cluster. The stem is branched, smooth, and hollow. The leaves are pointed with many teeth, showing occasionally a slight red color. They are a deadly poisonous plant.",
   "The Blue Flag Iris is a poisonous to eat plant and is found in wet areas around Ontario. They generally grow in the sun, being found in the summer with 3 blue/violet petals. They can grow to about 30-80 cm."
   };
   public static Image[] imgs = new Image[20];
   public static int x = 0;

   public Game(){
      
      this.setTitle("Fresh Forage Adventure");
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(1000, 680);
   
      this.setVisible(true);
      
      for (int i = 0; i < img.length; i++){
         img[i] = t.getImage(images[i]); 
      }
   }

   public void run() {     
      while (running) {
         switch (screenNum) {
            case 0:
               Splash s = new Splash();
               this.getContentPane().add(s);
            
               this.setVisible(true);
            
               while (s.isRunning()) {
                  try {
                     Thread.sleep(100); // Add a small delay to reduce CPU usage
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
            
               screenNum = 1;
               break;
         
            case 1:
               this.getContentPane().removeAll();
            
               Menu m = new Menu();
               this.getContentPane().add(m);
               Thread menu = new Thread(m);
               menu.start();
            
               this.setVisible(true);
            
               while (m.isRunning()) {

                  try {
                     Thread.sleep(100); // Add a small delay to reduce CPU usage
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
            
               menu.interrupt();
               break;
         
            case 2:
               this.getContentPane().removeAll();
            
               Level1 l1 = new Level1();
               this.getContentPane().add(l1);
               Thread level1 = new Thread(l1);
               level1.start();
            
               this.setVisible(true);
            
               while (l1.isRunning()) {
                  try {
                     Thread.sleep(100); // Add a small delay to reduce CPU usage
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
            
               level1.interrupt();
               break;
         
            case 3:
               this.getContentPane().removeAll();
            
               Instructions i = new Instructions();
               this.getContentPane().add(i);
               Thread instr = new Thread(i);
               instr.start();
            
               this.setVisible(true);
            
               while (i.isRunning()) {
                  try {
                     Thread.sleep(100); // Add a small delay to reduce CPU usage
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
            
               instr.interrupt();
               break;
            case 4:
               this.getContentPane().removeAll();
               
               Level2 l2 = new Level2();
               this.getContentPane().add(l2);
               Thread level2 = new Thread(l2);
               level2.start();
               
               this.setVisible(true);
               
               l2.setFocusable(true);
               l2.requestFocusInWindow();
               
               while(l2.isRunning()){
                  try {
                     Thread.sleep(100); // Add a small delay to reduce CPU usage
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
               
               level2.interrupt();
               running = false;
               break;
               
           case 5:
               this.getContentPane().removeAll();
               try{
                  if(l3== null){
                     l3 = new Level3();
                  }
                  else{
                     int timer = l3.getTimer();
                     int charX = l3.getCharX();
                     int charY = l3.getCharY();
                     int bgX = l3.getBgX();
                     int bgY = l3.getBgY();
                     boolean lockCamX = l3.getLockCamX();
                     boolean lockCamY = l3.getLockCamY();
                     ArrayList<MazeObject> obj = l3.getObj();
                     ArrayList<MazeObject> collected = l3.getCollected();
                     
                     
                     l3 = new Level3(timer, charX, charY, bgX, bgY, lockCamX, lockCamY, obj, collected);
                  }
                  this.getContentPane().add(l3);
                  Thread level3 = new Thread(l3);
                  level3.start();
                  this.setVisible(true);
                  
                  l3.setFocusable(true);
                  l3.requestFocusInWindow();
                  while(l3.isRunning()){
                     try {
                        Thread.sleep(100); // Add a small delay to reduce CPU usage
                     } catch (InterruptedException e) {
                        e.printStackTrace();
                     }
                  }
                  
                  level3.interrupt();
               }
               catch(IOException e){
               }
               //running = false;
               break;
            case 6:
               this.getContentPane().removeAll();
               try{
                  Minigame mi = new Minigame(l3.getObjImage());
                  this.add(mi);
                  Thread minigame = new Thread(mi);
                  minigame.start();
                  this.setVisible(true);
                  mi.setFocusable(true);
                  mi.requestFocusInWindow();
                  while(mi.isRunning()){
                     try {
                        Thread.sleep(100); // Add a small delay to reduce CPU usage
                     } catch (InterruptedException e) {
                        e.printStackTrace();
                     }
                  }
                  minigame.interrupt();
               }
               catch(IOException e){
               }
               //running = false;
               screenNum = 5;
               break;
         }
      }
   }

   public static void main(String[] args){
      Game g = new Game();
      Thread t = new Thread(g);
      t.start();
   }
}