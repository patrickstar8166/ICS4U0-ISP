
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
/**
* Bibliography + Ending Screen class
* Code completed by Jonathan Liu and Patrick Bian
* <h2>Course Info:</h2>
* ICS4U0 with Krasteva, V.
*
* @version 08-06-2023
* @author BLD Studios
*/
public class Bibliography extends JPanel implements Runnable{ 
   private boolean running = true, finished = false;;
   private Toolkit t = Toolkit.getDefaultToolkit();
   private Image bg = Game.background1;


   /**
   * Class constructor
   */
   public Bibliography (){ 
   }
   
   /**
   * Called when thread is started, adds KeyListener and calls display
   */
   public void run(){
      this.addKeyListener(
         new KeyListener(){
            public void keyPressed(KeyEvent e) {
               if (finished) running = false;
            }
            
            public void keyReleased(KeyEvent e) { }
         
            public void keyTyped(KeyEvent e) { }
         });
      display();
   }
   
   /**
   * Tells Game whether the thread should be running
   * @return Whether the thread should be running
   */
   public boolean isRunning(){
      return running;
   }
   
   public void display(){
      try{
         Thread.sleep(5000);
         finished = true;
         repaint();
      }catch(InterruptedException e){}
   }
   
   @Override
   public void paintComponent(Graphics g){
      super.paintComponent(g);    
      if (!finished){
      //background
         g.setColor(new Color(0, 196, 255));
         g.fillRect(0, 0, 1000, 680);
      
         g.setColor(Color.black);
         g.setFont(new Font("Sans Serif", Font.BOLD, 40));
         g.drawString("Bibliography", 400, 50);
         g.setFont(new Font("Sans Serif",Font.BOLD, 10));           
         String[] words = Game.bib.split("\\s+");
         StringBuilder line = new StringBuilder(words[0]);
         int location = 180;
         for (int i = 1; i < words.length; i++) {
            if (line.length() + words[i].length() + 1 <= 70) {
               line.append(" ").append(words[i]);
            } else {
               g.drawString(line.toString(), 50, location);
               location += 30;
               line = new StringBuilder(words[i]);
            }
         }    
         g.drawString(line.toString(), 50, location);    
      }  
        
        
      if (finished){
         super.paintComponent(g);
         g.setColor(Color.black);
         g.setFont(new Font("Sans Serif", Font.BOLD, 30));
         g.drawImage(bg,0,0,1000,680,this);
         g.drawString("Thank you for playing! We hope you enjoyed!",200,100);
         g.setFont(new Font("Sans Serif", Font.BOLD, 10));
         g.drawString("Game developed by Nia Decaire, Patrick Bian, and Jonathan Liu for ICS4U0 with Ms. Krasteva",300,600);
         g.setFont(new Font("Sans Serif", Font.BOLD, 20));
         g.drawString("Press any key to exit the game.",50,550);
      }
   }
}

