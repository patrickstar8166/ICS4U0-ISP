import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Splash{
   public JInternalFrame frame;
   public Drawing draw; 
   public int i = 0, a = 255;
   public boolean b = false, run = false; 
   
   public Splash(){ 
      frame = new JInternalFrame();
      draw = new Drawing();
   }

   public JInternalFrame display(){
      //frame
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000, 680);
      frame.add(draw);
      frame.setVisible(true);
      
      return frame;
   }
   
   public boolean isFinished(){
      return run;
   }
   
   class Drawing extends JComponent{
      public void paint(Graphics g){
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
               draw.repaint();
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
               draw.repaint();
            }
            if (a == 255) run = true;
         }    
      }
   }
}