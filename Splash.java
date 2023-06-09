
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
* Splash class, short splashscreen of logo before main menu
* Code completed by Patrick Bian
* <h2>Course Info:</h2>
* ICS4U0 with Krasteva, V.
*
* @version 08-06-2023
* @author BLD Studios
*/
public class Splash extends JPanel{
   private int i = 0, a = 255;
   private boolean b = false, running = true; 

   public Splash(){}
      
   public boolean isRunning(){
      return running;
   }
   /**
   * Draws logo, changes transparency over time
   */
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      
      //background
      g.setColor(new Color(232, 220, 202));
      g.fillRect(0, 0, 1000, 680);
      
      //image
      Toolkit t = Toolkit.getDefaultToolkit();  
      Image img = t.getImage("logo.png");  
      g.drawImage(img, 250, 75, this); 
      
      g.setColor(new Color(232, 220, 202, a));
      g.fillRect(300, 125, 500, 500);
      
      if (!b){
         if (a > 0){
            i++;
            if (i%3 == 0){
               a -= 1;
            }
            repaint();
         } 
         if (a == 0){
            for (int j = 0; j < 5000; j++){
               g.setColor(new Color(232, 220, 202, 0));
               g.fillRect(300, 125, 500, 500);
            }
            b = true;
         }   
      }
      
      if (b){
         if (a < 255){
            i++;
            if (i%3 == 0){
               a += 1;
            }
            repaint();
         }
         if (a == 255) running = false;
      }    
   }
}
