import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Level1{
   public JInternalFrame frame;
   public Drawing draw; 
   
   public Level1(){ 
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
      
   class Drawing extends JComponent{
      public void paint(Graphics g){
         //background
         g.setColor(Color.blue);
         g.fillRect(0, 0, 1000, 680);
      }
   }
}