import java.awt.*;    
import java.awt.event.*;  
import javax.swing.*;
public class Level2 extends JPanel{
   private boolean[][] maze = {{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, //true9 x truetrue
                              {true, true, true, true, true, true, true, true, false, true, true, true, true, true, false, true, true, true, false},
                              {false, true, false, false, false, false, false, true, false, true, false, false, false, true, false, false, false, true, false},
                              {false, true, true, true, false, true, true, true, false, true, true, true, false, true, false, true, true, true, false},
                              {false, false, false, true, false, true, false, false, false, false, false, true, false, true, false, true, false, true, false},
                              {false, true, false, true, false, true, true, true, true, true, true, true, false, true, true, true, false, true, false},
                              {false, true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false},
                              {false, true, false, true, true, true, false, true, true, true, false, true, true, true, true, true, true, true, false},
                              {false, true, false, false, false, false, false, true, false, true, false, true, false, false, false, false, false, false, false},
                              {false, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true, true, true, true},
                              {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}};
                              
   public Level2(){
      
   }
   
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      
      g.setColor(Color.black);
      for (int i = 0; i < 11; i++){
         for (int j = 0; j < 19; j++){
            if (!maze[i][j]){
               g.fillRect(j*50+25, i*50+50, 50, 50);
            }
         }
      }
   }
   
   public static void main(String[] args){
      JFrame f = new JFrame();
      Level2 l = new Level2();
      f.add(l);
      f.setSize(1000, 680);
      f.setVisible(true);
   }  
}