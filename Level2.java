import java.awt.*;    
import java.awt.event.*;  
import javax.swing.*;
public class Level2 extends JPanel implements Runnable{
   private int[][] maze = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},  //19x11
                           {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0},
                           {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0},
                           {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0},
                           {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                           {0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
                           {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                           {0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                           {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                           {0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                           {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
   private int x = 0, y = 0;
   private boolean running = true;
                              
   public Level2(){
   }
   
   public void run(){
      this.addKeyListener(
         new KeyListener(){
            public void keyPressed(KeyEvent e) {
               if(e.getKeyCode() == e.VK_RIGHT && maze[x+1][y] == 1 && x+1 < 19){
                  x++;
               }
               
               if(e.getKeyCode() == e.VK_LEFT && maze[x-1][y] == 1 && x-1 > 0){
                  x--;
               }
               
               if(e.getKeyCode() == e.VK_UP && maze[x][y-1] == 1 && y-1 > 0){
                  y--;
               }
               
               if(e.getKeyCode() == e.VK_DOWN && maze[x][y+1] == 1 && y+1 < 11){
                  y++;
               }
               
               if (x == 18 && y == 9){
                  running = false;
                  removeKeyListener(this);
               }
               
               repaint();
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
      
      g.setColor(Color.black);
      for (int i = 0; i < 11; i++){
         for (int j = 0; j < 19; j++){
            if (maze[i][j] == 0){
               g.fillRect(j*50+25, i*50+50, 50, 50);
            }
         }
      }
      
      g.setColor(Color.green);
      g.fillRect(x*50+25, y*50+100, 50, 50);
   }
}