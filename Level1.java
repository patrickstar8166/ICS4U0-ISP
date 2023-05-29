import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
public class Level1 extends JPanel{
   private boolean running = true;
   private String[] names = {"Saskatoon Berries", "Dandelion", "Crown Tipped Coral", "Burdock", "Jack Pine", "Lobster Mushroom", "Morel Mushroom", "Cranberry", "Raspberry", "Cattail"};
   private String[] images = {"Images/Saskatoon.jpg", "Images/Dandelion.jpg", "Images/Crown.jpg", "Images/Burdock.jpg", "Images/Jack.jpg", "Images/Lobster.jpg", "Images/Morel.jpg", "Images/Cranberry.jpg", "Images/Cranberry.jpg", "Images/Raspberry.jpg", "Images/Cattail.jpg"};
   private String[] descriptions = {"Large purple-blue berries that grow on trees with gray bark and toothed leaves. Grow to about 5 meters tall and are about 3 meters wide. Berries ripen in June or early June.",
   "Distributed everywhere from lawns, roadsides, to gardens. Found in May and August. Bright yellow flower with milky white stem. 5 - 45 cm in height.",
   "Grows on wood. Branch tips have 3-6 points. Resembles sea coral or candelabra. Initially white and then becomes more pale/pink. Found in Spring, Summer, and Fall.",
   "0.5 - 1.5 meters. Pink or purple flowers with large burrs (spiky envelopes of fruit). Food in roadsides and disturbed areas. Found during August to October.",
   "Found year-round in boreal forests and open areas. Short needles in clusters of two that are not twisted together, cones are closer and tight to the branch.", 
   "Bright red/orange mushroom with firm cap/stem. Irregular/deformed appearance. Cap size generally 5-12 cm. Harvested midsummer. Can be found in wooded areas.",
   "Forests, open meadows, and highly disturbed areas can contain Morel. Long heads with ridges and pitted chambers. Hollow inside from tip of cap to bottom of stalk. Harvested in Spring.",
   "Mostly under 20cm in height. Flowers are pink with red/purple berries. Found in wet areas near bogs/ponds/lakes. Harversted from September to November.",
   "Up to 2m in height. Prickly stems with alternate compound leaves with white/green flowers. Found in moist temperate regions. Harvested around summer.",
   "1 to 3 meters in height. Long, slender, stiff leaves. Flowers form tight cyidnrical clusters. Found in marshes, lakes, streams and calm water. Harvested in spring for flower and pollen, fall to spring for roots/shoots."};
   private Image[] img = new Image[10];
   private Toolkit t = Toolkit.getDefaultToolkit();
   
   public Level1(){
      for (int i = 0; i < 10; i++){
         img[i] = t.getImage(images[i]); 
      }
   }
      
   public boolean isRunning(){
      return running;
   }
      
   public void paintComponent(Graphics g){
      super.paintComponent(g); 
                
      //background
      g.setColor(Color.blue);
      g.fillRect(0, 0, 1000, 680);
      
      for (int i = 0; i < 10; i++){
         g.drawImage(img[i], 250, 75, 100, 100 , this); 
      }
   }
   
   public static void main(String[] args){
      JFrame f = new JFrame();
      Level1 l1 = new Level1();
      f.add(l1);
      
      f.setSize(1000, 680);
      f.setVisible(true);
   }
}