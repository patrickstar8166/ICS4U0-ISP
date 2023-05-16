import java.awt.*;    
import java.awt.event.*;  
import javax.swing.*;

public class Minigame implements KeyListener{
   
   Drawing d = new Drawing();
   private int x,y;
   
   public Minigame(){
      x = 490;
      y = 500;
      JFrame frame = new JFrame("Minigame");
      frame.setSize(1000,680);
      frame.add(d);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      d.addKeyListener(this);

   }
   
   class Drawing extends JComponent
   {
      public void paint (Graphics g)
      {
         g.setColor(Color.blue);
         g.fillOval(x,y,20,20);
      }
   }
   
   public void keyPressed(KeyEvent e){
      if(e.getKeyCode()==e.VK_RIGHT){
         x++;
         System.out.println("moving right: "+x);
         d.repaint();
      }
      if(e.getKeyCode() == e.VK_LEFT){
         x--;
         System.out.println("moving left: "+x);
         d.repaint();
      }
   }
   
   public void keyReleased(KeyEvent e){
   
   }
   
   public void keyTyped(KeyEvent e){
   
   }
   
   public static void main(String[] args)
   {
      new Minigame();
   }

}