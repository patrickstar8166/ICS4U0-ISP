import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Game extends JFrame{
   public static int screenNum = 1;
   public static boolean run = true;
   
   public Game(){
      this.setTitle("Fresh Forage Adventure");
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(1000, 680);
       
      while(run){
         switch(screenNum){
            case 1:
               Menu m = new Menu();
               this.getContentPane().add(m);
               //while(m.isFinished()){}
               //run = false;
               break;
               
            case 2: 
               this.getContentPane().removeAll();
               Instructions i = new Instructions();
               this.getContentPane().add(i);
               //while(i.exitClicked()){}
               run = false;
               break;
         }
      }
         
      this.setVisible(true);
   }
   
   public static void main(String[] args){
      Game g = new Game();
   }
}