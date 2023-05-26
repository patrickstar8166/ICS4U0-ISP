import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Level1{
   private Drawing draw; 
   private boolean run = false;
   
   public Level1(){ 
      draw = new Drawing();
   }

   public void display(JFrame frame){
      //frame
      frame.add(draw);
      
     // while(true){
       //  if(isFinished()) break;
      //}
   }
   
   private boolean isFinished(){
      return run;
   }
      
   class Drawing extends JComponent{
      public void paint(Graphics g){
         //background
         g.setColor(Color.blue);
         g.fillRect(0, 0, 1000, 680);
      }
   }
}