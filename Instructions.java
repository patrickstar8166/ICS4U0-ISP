import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Instructions{
   public Drawing draw; 
   
   public Instructions(){ 
      frame = new JFrame("Instructions");
      draw = new Drawing();
   }

   public void display(){
      //frame
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000, 680);
      frame.add(draw);
      frame.setVisible(true);
   }
      
   class Drawing extends JComponent{
      public void paint(Graphics g){
         //background
         g.setColor(Color.black);
         g.fillRect(0, 0, 1000, 680);
      }
   }
}