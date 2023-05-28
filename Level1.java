import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Level1 extends JPanel{
   private boolean running = true;
   
   public Level1(){ 
   }
      
   public boolean isRunning(){
      return running;
   }
      
   public void paintComponent(Graphics g){
      super.paintComponent(g); 
                
      //background
      g.setColor(Color.blue);
      g.fillRect(0, 0, 1000, 680);
   }
}