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
   private boolean rightHeld, leftHeld, upHeld, downHeld,  zHeld, interact,end;
   private int charX, charY, charW, buffer;
   private ArrayList<MazeObject> obj = new ArrayList<MazeObject>();
   
   public Level2() throws IOException{
      JFrame frame = new JFrame("Level 2");
      frame.setSize(1000,680);
      frame.add(d);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.addKeyListener(this);
      bg = ImageIO.read(new File("Images/lab bg.png"));
      end = false;
      charX = 490;
      charY = 400;
      charW = 20;
      buffer = 50;
      
   }
   
   public void game() throws InterruptedException{
      while(!end){
         move();
         d.repaint();
         Thread.sleep(16,666667);
      }
      
   }
   
   public void move(){
      if(leftHeld){
         charX-=5;
         System.out.println("charX: "+charX);
      }
      if(rightHeld){
         charX+=5;
         System.out.println("charX: "+charX);
      }
      if(upHeld){
         charY-=5;
         System.out.println("charY: "+charY);
      }
      if(downHeld){
         charY+=5;
         System.out.println("charY: "+charY);
      }
      if(charX>1000-charW){
         charX = 999-charW;
      }
      else if(charX<0){
         charX = 0;
      }
      if(charY>680-charW){
         charY = 679-charW;
      }
      else if(charY<0){
         charY = 0;
      }

   }
   
   public void interact(){
      if(!interact){
         for(int i = 0; i <obj.size(); i++){
            if(charX+charW+buffer>=obj.get(i).getX() && charX<= obj.get(i).getX()+obj.get(i).getW()+buffer){
               interact = true;
               leftHeld = false;
               rightHeld = false;
            }
         }
      }
      else{
         interact = false;
      }
      
   }
   
   public void run(){
      
      try{
         obj.add(new MazeObject(200,300,30,30,"Images/apple.jpg"));
         obj.add(new MazeObject(500,300,30,30, "Images/bomb.png"));
         game();
      }
      catch(Exception e){
         
      }
   }
   
   public void keyPressed(KeyEvent e){
   
      if(!interact){
         if(e.getKeyCode()==e.VK_RIGHT){
            rightHeld = true;
         }
         if(e.getKeyCode() == e.VK_LEFT){
            leftHeld = true;  
         }
         if(e.getKeyCode() == e.VK_UP){
            upHeld = true;
         }
         if(e.getKeyCode() == e.VK_DOWN){
            downHeld = true;
         }
      }
      
      if(e.getKeyChar() == 'z'&&!zHeld){
         interact();
         zHeld = true;
      }
   }
   public void keyReleased(KeyEvent e){
   
      if(e.getKeyCode()==e.VK_RIGHT){
         rightHeld = false;
      }
      if(e.getKeyCode() == e.VK_LEFT){
         leftHeld = false;
      }
      if(e.getKeyCode() == e.VK_UP){
         upHeld = false;
      }
      if(e.getKeyCode() == e.VK_DOWN){
         downHeld = false;
      }
      
      if(e.getKeyChar() == 'z' && zHeld){
         zHeld = false;
      }
   }
   public void keyTyped(KeyEvent e){
      
   }
   
   class Drawing extends JComponent{
      
      public void paintComponent(Graphics g){
         super.paintComponent(g);
         /*g.setColor(Color.black);

         for(int i = 0; i < obj.size(); i++){
            g.drawImage(obj.get(i).getImg(),obj.get(i).getX(),obj.get(i).getY(),this);
         }*/
         
         g.setColor(Color.blue);
         g.fillRect(charX,charY,charW,20);
         
        /* if(interact){
            g.setColor(new Color(0,0,0,100));
            g.fillRect(0,0,1000,680);
            g.setColor(Color.red);
            g.fillRect(450,290,50,50);
            
         }  */
         
            
      }
      
   }

}