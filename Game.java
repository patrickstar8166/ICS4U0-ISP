import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Game{
   public static JFrame frame = new JFrame("Fresh Forage Adventure");
   public static int screenNum = 1;
   public static JInternalFrame f;
   public static boolean run = true;
   
   public static void main(String[] args){
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLayout(null);
      frame.setSize(1000, 680);
      frame.setVisible(true);
      
      while (run){
         switch(screenNum){
            case 1:
               Menu m = new Menu();
               f = m.display();
               f.setLocation(-5, -30);
               frame.add(f);
               break;
            
            case 2:
               Level1 l = new Level1();
               f = l.display();
               frame.getContentPane().removeAll();
               f.setLocation(-5, -30);
               frame.add(f);
               run = false;
               break;                
         }
      }
   }
}