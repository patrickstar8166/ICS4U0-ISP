import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Game extends JFrame implements Runnable{
   public static int screenNum = 0;
   public boolean running = true;
   public static String[] names = {"Saskatoon Berries", "Dandelion", "Crown Tipped Coral", "Burdock", "Jack Pine", "Lobster Mushroom", "Morel Mushroom", "Cranberry", "Raspberry", "Cattail"};
   public static String[] images = {"Images/Saskatoon.jpg", "Images/Dandelion.jpg", "Images/Crown.jpg", "Images/Burdock.jpg", "Images/Jack.jpg", "Images/Lobster.png", "Images/Morel.jpg", "Images/Cranberry.jpg", "Images/Raspberry.jpg", "Images/Cattail.jpg"};
   public static String[] descriptions = {"Large purple-blue berries that grow on trees with gray bark and toothed leaves. Grow to about 5 meters tall and are about 3 meters wide. Berries ripen in June or early June.",
   "Distributed everywhere from lawns, roadsides, to gardens. Found in May and August. Bright yellow flower with milky white stem. 5 - 45 cm in height.",
   "Grows on wood. Branch tips have 3-6 points. Resembles sea coral or candelabra. Initially white and then becomes more pale/pink. Found in Spring, Summer, and Fall.",
   "0.5 - 1.5 meters. Pink or purple flowers with large burrs (spiky envelopes of fruit). Food in roadsides and disturbed areas. Found during August to October.",
   "Found year-round in boreal forests and open areas. Short needles in clusters of two that are not twisted together, cones are closer and tight to the branch.", 
   "Bright red/orange mushroom with firm cap/stem. Irregular/deformed appearance. Cap size generally 5-12 cm. Harvested midsummer. Can be found in wooded areas.",
   "Forests, open meadows, and highly disturbed areas can contain Morel. Long heads with ridges and pitted chambers. Hollow inside from tip of cap to bottom of stalk. Harvested in Spring.",
   "Mostly under 20cm in height. Flowers are pink with red/purple berries. Found in wet areas near bogs/ponds/lakes. Harversted from September to November.",
   "Up to 2m in height. Prickly stems with alternate compound leaves with white/green flowers. Found in moist temperate regions. Harvested around summer.",
   "1 to 3 meters in height. Long, slender, stiff leaves. Flowers form tight cyidnrical clusters. Found in marshes, lakes, streams and calm water. Harvested in spring for flower and pollen, fall to spring for roots/shoots."};

   public Game(){
      this.setTitle("Fresh Forage Adventure");
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(1000, 680);
   
      this.setVisible(true);
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
         }
      }
   }

   public static void main(String[] args){
      Game g = new Game();
      Thread t = new Thread(g);
      t.start();
   }
}