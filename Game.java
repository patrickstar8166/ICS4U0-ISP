import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Game extends JFrame implements Runnable{
   public static int screenNum = 1;
   public static boolean running = true;
   
   public Game(){
      this.setTitle("Fresh Forage Adventure");
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(1000, 680);
       
      this.setVisible(true);
   }
   
   public void run() {
      while (running) {
         switch (screenNum) {
            case 1:
               this.getContentPane().removeAll();
               
               Menu m = new Menu();
               this.getContentPane().add(m);
               Thread menu = new Thread(m);
               menu.start();
               
               this.setVisible(true);
               
               while (m.isRunning()) {
                  try {
                     Thread.sleep(100); // Add a small delay to reduce CPU usage
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
               
               menu.interrupt();
               break;
         
            case 2:
               this.getContentPane().removeAll();
               
               Level1 l1 = new Level1();
               this.getContentPane().add(l1);
               
               this.setVisible(true);
               
               running = false;
               break;
         
            case 3:
               this.getContentPane().removeAll();
               
               Instructions i = new Instructions();
               this.getContentPane().add(i);
               Thread instr = new Thread(i);
               instr.start();
               
               this.setVisible(true);
               
               while (i.isRunning()) {
                  try {
                     Thread.sleep(100); // Add a small delay to reduce CPU usage
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
               
               instr.interrupt();
               break;
         }
      }
   }
   
   public static void main(String[] args){
      Game g = new Game();
      Thread t = new Thread(g);
      t.start();
   }
}