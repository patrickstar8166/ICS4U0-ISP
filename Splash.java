import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Splash extends Thread{
   public JFrame frame;
   public Drawing draw; 
   public int i = 0;
   
   public Splash(){ 
      frame = new JFrame();
      draw = new Drawing();
   }

   public void run(){
      //frame
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000, 680);
      frame.add(draw);
      frame.setVisible(true);
      
      //return frame;
   }
   
   class Drawing extends JComponent{
      public void paint (Graphics g){
         //background
         g.setColor(Color.green);
         g.fillRect(0, 0, 1000, 680);
         
         //image
         Toolkit t = Toolkit.getDefaultToolkit();  
         Image img = t.getImage("logo.png");  
         g.drawImage(img, 250, 75, this); 
         
         g.setColor(new Color(i, i, i));
         g.fillRect(300, 125, 200, 200);
         i += 1;
         try{
            Thread.sleep(100);
         } catch (Exception e){}
         if (i <= 255) draw.repaint();
      }
   }
   
   public static void main(String[] args){
      Splash s = new Splash();
      //s.display();
      Thread t = new Thread(s);
      t.start();
   }
}