import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Bibliography extends JPanel implements Runnable{ 
   private boolean running = true, finished = false;;

   public Bibliography (){ 
   }

   public void run(){
      this.addKeyListener(
         new KeyListener(){
            public void keyPressed(KeyEvent e) {
               if (finished) running = false;
            }
            
            public void keyReleased(KeyEvent e) { }
         
            public void keyTyped(KeyEvent e) { }
         });
   
   }

   public boolean isRunning(){
      return running;
   }
  
   public void paintComponent(Graphics g){
      super.paintComponent(g);    
      if (!finished){
      //background
         g.setColor(new Color(0, 196, 255));
         g.fillRect(0, 0, 1000, 680);
      
      //text
         g.setColor(Color.black);
         g.setFont(new Font("Sans Serif", Font.BOLD, 40));
         g.drawString("Bibliography", 300, 50);
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
        
      // Delay execution for 1 second before setting finished = true
      SwingUtilities.invokeLater(() -> {
            try {
               Thread.sleep(1000);
               finished = true;
               repaint();
                
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         });
        
      if (finished){
         g.setColor(Color.black);
         g.fillRect(0, 0, 1000, 680);
                // thank you and press any key to exit here
      }
   }
}

