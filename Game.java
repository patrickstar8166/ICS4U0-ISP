import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

/**
* Game class, handles switching of screens and contains global variables used by screens
* <h2>Course Info:</h2>
* ICS4U0 with Krasteva, V.
*
* @version 08-06-2023
* @author BLD Studios
*/

public class Game extends JFrame implements Runnable{
   public static int screenNum = 0;
   public boolean running = true;
   private Level3 l3;
   private Minigame mi;
   private Toolkit t = Toolkit.getDefaultToolkit();
   public static String[] names = {"Saskatoon Berries", "Dandelion", "Crown Tipped Coral", "Burdock", "Jack Pine", "Lobster Mushroom", "Morel Mushroom", "Cranberry", "Raspberry", "Cattail", "Lily of the Valley", "False Morel", "Gilled Mushroom", "Deadly Galerina", "Poison Ivy", "Baneberry Red", " Blub-bearing Water Hemlock", "Poison Hemlock", "Water Hemlock", "Blue Flag Iris"};
   public static String[] images = {"Saskatoon.jpg", "Dandelion.jpg", "Crown.jpg", "Burdock.jpg", "Jack.jpg", "Lobster.png", "Morel.jpg", "Cranberry.jpg", "Raspberry.jpg", "Cattail.jpg","LilyValley.jpeg","FalseM.jpeg","DestroyingAngel.jpeg","DeadlyG.jpeg","PoisonIvy.jpeg","BaneBerryREd.jpeg","BulbBearingWater.jpeg", "PoisonHemlock.jpeg", "WaterHemlock.jpeg","BlueF.jpeg"  };
   public static Image[] img = new Image[images.length];
   public static String[] descriptions = {"Large purple-blue berries that grow on trees with gray bark and toothed leaves. Grow to about 5 meters tall and are about 3 meters wide. Berries ripen in June or early June.",
   "Distributed everywhere from lawns, roadsides, to gardens. Found in May and August. Bright yellow flower with milky white stem. 5-45 cm in height.",
   "Grows on wood. Branch tips have 3-6 points. Resembles sea coral or candelabra. Initially white and then becomes more pale/pink. Found in spring, summer, and fall.",
   "0.5 - 1.5 meters. Pink or purple flowers with large burrs (spiky envelopes of fruit). Food in roadsides and disturbed areas. Found during August to October.",
   "Found year-round in boreal forests and open areas. Short needles in clusters of two that are not twisted together, cones are closer and tight to the branch.", 
   "Bright red/orange mushroom with firm cap and stem. Irregular and deformed appearance. Cap size generally 5-12 cm. Harvested midsummer. Can be found in wooded areas.",
   "Forests, open meadows, and highly disturbed areas can contain Morel. Long heads with ridges and pitted chambers. Hollow inside from tip of cap to bottom of stalk. Harvested in spring.",
   "Mostly under 20 cm in height. Flowers are pink with red or purple berries. Found in wet areas near bogs, ponds, and lakes. Harversted from September to November.",
   "Up to 2m in height. Prickly stems with alternate compound leaves with white or green flowers. Found in moist temperate regions. Harvested around summer.",
   "1 to 3 meters in height. Long, slender, stiff leaves. Flowers form tight cylindrical clusters. Found in marshes, lakes, streams and calm water. Harvested in spring for flower and pollen, fall to spring for roots/shoots.",
   "Lily of the vallies have a really good scent but can be easily mistaken for wild garlic, they live in woodlands and appear during April to June. However, they are very toxic and can cause a variety of symptoms, such as vomiting, nausea, blurry vision, and disruptions to the heart beat.",
   "Lookalikes to edible morels. They mostly appear in the spring and summer. The caps are brown and sometimes yellow. Commonly found near man-made disturbances and contain a toxic compound which can cause vomiting, dizziness, diarrhea, and death.",
   "A white mushroom with a 12 cm diameter and 15 cm tall stalk. There is a key cup around the stalk surrounding the base. Only found in North America, however, is one of the deadliest mushrooms in Canada, with toxins damaging the liver and kidneys.",
   "Brown mushrooms which grow on rotting wood. The stalks are small and have a tender ring, while the caps are tan/brown and only about 5 cm in diameter. They are found in fall. Their toxins can damage the liver and kidneys.",
   "Varies from shrubs to vines and are found in a variety of places such as forests, field, and open areas. They can be found in Ontario and have leaves that are in groups of three. It is poisonous to touch and can cause rashes.", 
   "Bright red berries with tiny black dots. Found growing under shade within forests. As well, they are found in Ontario and grow to a heigh of 30-60 cm. The berries are poisonous and 5-6 berries consumed can cause severe illness, with even more causing death.", 
   "A spindly plant with narrow/fine leaves. Generally found in wet areas such as marhes, shores, and swamps in Ontario, growing to a height of 1-2 meters. Flowers are found in the summer and are white with 5 petals in umbrella-like clusters. The stem is hollow with many widely spaced branches. This plant is extremely poisonous.",
   "A plant with a spotted stem and white flowers occuring around summer. It contains 5 white petals and are located in fields and open areas in Ontario. Consuming them is deadly.",
   "A plant found in wet areas such as marshes, swamps, and shore lines. They flower during the summer and contain 5 white petals in an umbrella-like cluster. The stem is branched, smooth, and hollow. The leaves are pointed with many teeth, showing occasionally a slight red color. A very poisonous plant.",
   "A poisonous to eat plant and is found in wet areas around Ontario. They generally grow in the sun, being found in  summer with 3 blue or violet petals. They can grow to about 30-80 cm."
   };
   public static String[] instructions = {"Within this game, you’ll be exposed to three different levels of increasing skill and difficulty. To navigate please follow the instructions. To navigate on this page, please use the arrow buttons by clicking with a mouse/trackpad! This single-player game is intended to teach kids aged 5-10 about foraged plants in a fun and engaging way. As the game progresses through the three levels, the difficulty increases. As well, all levels must be played in sequential order, where the previous level must be successfully done.", 
   "This level involves various slides you will navigate through. They will provide detailed information about foods that are edible and inedible (this will be tested in the later levels). The main goal of this level is not to be tested, but to learn. After going through all given slides in this level, users will be given access to the next level in the game. Please navigate through this level simply by clicking the arrows on the screen. This will change the slides and change the content being displayed. Have fun!",
   "This level involves a pre-set path to navigate, using the arrow keys. Through the path, there will be checkpoints to test your knowledge on the different plants to be foraged (red squares). A screen will pop up asking for the answer. The answer can be selected by clicking the correct option. After you reach the end, the third level can be played. This will test you on your forage knowledge, so have fun!",
   "This level involves a free-roam path that you will navigate, using the arrow keys. On the map, there will be randomly spawned items. These items may be dangerous OR safe, so choose carefully! Whether the item is safe or not will not be directly prompted until the end screen, which will tell you if what you've chosen is good! During this time, you will have to collect the right items and enough items before the 2 minute timer (right corner) runs out! Afterwards, your results will be displayed. During the game you can view your inventory by pressing \"i\". To pick up an item, press \"z\". A mini-game will be displayed where you will be asked to collect enough of the item while avoiding getting stung by the bees. If you are stung too much, you will not be able to collect the item. You will have successfully collected the item if the green bar is full. Collect 6 edible items before the timer runs out! If you collect any inedible plants, you will lose."};
   public static String bib = "1.https://www.ediblewildfood.com/saskatoon.aspx https://northernontario.travel/outdoor-adventures/beginners-guide-foraging-ontario 2.https://ontarionature.org/wp-content/uploads/2017/10/Ontario_Nature_Forest_Foraging_Guide_official.pdf 3.https://northernbushcraft.com/books/wemon/wemon_spread_p56.png https://www.ontariopoisoncentre.ca/common-poisons/plants/ 4.https://www.wildfooduk.com/edible-wild-plants/lily-of-the-valley/ 5.https://www.mushroom-appreciation.com/false-morel.html 6.https://www.toronto.ca/wp-content/uploads/2020/05/8ef1-City-Planning-Mushrooms-of-Toronto-Biodiversity-Series.pdf 7.http://ontariowildflowers.com/main/species.php?id=2069 8.http://ontariowildflowers.com/main/species.php?id=18 9.http://ontariowildflowers.com/main/species.php?id=116 10.http://ontariowildflowers.com/main/species.php?id=150 9.http://ontariowildflowers.com/main/species.php?id=117 11.http://ontariowildflowers.com/main/species.php?id=508"; 
   public static Image[] imgs = new Image[20];
   public static Image bee, pcU, pcD, pcR, pcL, side1, side2, inv, endPlate, bush, sprite1, sprite2, background1, background2, background3, minigameChar, minigameBasket, minigameBg, minigameBee;
   public static int x = 0;
   
   /**
   * Class constructor, configures settings of JFrame, intializes images
   */
   public Game(){
      this.setTitle("Fresh Forage Adventure");
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(1000, 680);
   
      this.setVisible(true);
      
      bee = t.getImage("bee.png");
      pcU = t.getImage("charUp.png");
      pcD = t.getImage("charDown.png");
      pcR = t.getImage("charRight.png");
      pcL = t.getImage("charLeft.png");
      side1 = t.getImage("StandingStillView.png");
      side2 = t.getImage("WalkingView.png");
      inv = t.getImage("inv.png");
      endPlate = t.getImage("endPlate.png");
      bush = t.getImage("bush.png");
      sprite1 = t.getImage("StandingStillView.png");
      sprite2 = t.getImage("WalkingView.png");
      background1 = t.getImage("Background 1.png");
      background2 = t.getImage("MazeBackground.png");
      background3 = t.getImage("level3bg.png");
      minigameChar = t.getImage("MinigameView.png");
      minigameBasket = t.getImage("MinigameBasket.png");
      minigameBg = t.getImage("MinigameBackground.png");
      minigameBee = t.getImage("MinigameB.png");
      
      for (int i = 0; i < img.length; i++){
         img[i] = t.getImage(images[i]); 
      }
   }
   /**
   * Called when game thread is started, handles navigation of screens
   */
   
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
               
               l2.setFocusable(true);
               l2.requestFocusInWindow();
               this.setVisible(true);
               
               while(l2.isRunning()){
                  try {
                     Thread.sleep(100); // Add a small delay to reduce CPU usage
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
               
               level2.interrupt();
               break;
               
            case 5:
               this.getContentPane().removeAll();
               try{
                  if(l3 == null){
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
                  
                  l3.setFocusable(true);
                  l3.requestFocusInWindow();
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
               break;
            case 6:
               this.getContentPane().removeAll();
               try{
                  mi = new Minigame(l3.getCurrentObj());
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
                  if(mi.gameWon()){
                     l3.addCollected(mi.getObj());
                  }
                  minigame.interrupt();
               }
               catch(IOException e){
               }
               screenNum = 5;
               break;
               
            case 7: 
               this.getContentPane().removeAll();
               
               Bibliography biblio = new Bibliography();
               this.getContentPane().add(biblio);
               Thread bibliography = new Thread(biblio);
               bibliography.start();
               
               biblio.setFocusable(true);
               biblio.requestFocusInWindow();
               this.setVisible(true);
               
               while(biblio.isRunning()){
                  try {
                     Thread.sleep(100); // Add a small delay to reduce CPU usage
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
               
               bibliography.interrupt();
               System.exit(0);
               break;

         }
      }
   }
}