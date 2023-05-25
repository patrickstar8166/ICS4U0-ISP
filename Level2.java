import java.awt.*;    
import java.awt.event.*;  
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Level2 implements KeyListener, Runnable{
   
   private Drawing d = new Drawing();
   private BufferedImage bg;
   private boolean rightHeld, leftHeld, zHeld, end;
   private int bgOffset;
   public Level2() throws IOException{
      JFrame frame = new JFrame("Minigame");
      frame.setSize(1000,680);
      frame.add(d);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.addKeyListener(this);
      bg = ImageIO.read(new File("lab bg.png"));
      end = false;
      bgOffset = 0;
   }
   
   public void game() throws InterruptedException{
      while(!end){
         if(rightHeld){
            bgOffset-=5;
         }
         if(leftHeld){
            bgOffset+=5;
         }
         d.repaint();
         Thread.sleep(16,666667);
      }
   }
   
   public void interact(){
      System.out.println("z pressed");
      if(bgOffset>=-1000&&bgOffset<=-970){
         System.out.println("interact object");
      }
      
   }
   
   public void run(){
      try{
         game();
      }
      catch(Exception e){
         
      }
   }
   
   public void keyPressed(KeyEvent e){
      
      if(e.getKeyChar() == 'z'&&!zHeld){
         interact();
         zHeld = true;
      }

      
      if(e.getKeyCode()==e.VK_RIGHT){
         rightHeld = true;
         
      }
      if(e.getKeyCode() == e.VK_LEFT){
         leftHeld = true;
         
      }
   }
   public void keyReleased(KeyEvent e){
      if(e.getKeyChar() == 'z' && zHeld){
         zHeld = false;
      }
      if(e.getKeyCode()==e.VK_RIGHT){
         rightHeld = false;
      }
      if(e.getKeyCode() == e.VK_LEFT){
         leftHeld = false;
      }
   }
   public void keyTyped(KeyEvent e){
      
   }
   
   class Drawing extends JComponent{
      
      public void paintComponent(Graphics g){
         super.paintComponent(g);
         if(bgOffset<0){
            g.drawImage(bg,1000+bgOffset%1000,0,this);
         }
         if(bgOffset>0){
            g.drawImage(bg,-1000+bgOffset%1000,0,this);
         }
         g.drawImage(bg,0+(bgOffset%1000),0,this);
         g.setColor(Color.black);
         for(int i = 0; i <10; i++){
            g.fillRect(-1000+i*200+bgOffset,400-i*10,30,30+i*10);
         }
         g.setColor(Color.blue);
         g.fillRect(490,400,20,20);
         
      }
      
   }

}