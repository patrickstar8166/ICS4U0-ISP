import java.awt.*;    
import java.awt.event.*;  
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Level3 implements KeyListener, Runnable{
   private Drawing d = new Drawing();
   private Image bg, pcU, pcD, pcL, pcR, charImg;
   private boolean rightHeld, leftHeld, upHeld, downHeld, lockCamX, lockCamY, zHeld, interact, end;
   private int charX, charY, charW, buffer, bgX, bgY, leftBound, rightBound, topBound, bottomBound;
   private ArrayList<MazeObject> obj = new ArrayList<MazeObject>();
   
   public Level3() throws IOException{
      JFrame frame = new JFrame("Level 3");
      frame.setSize(1000,680);
      frame.add(d);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.addKeyListener(this);
      bg = ImageIO.read(new File("Images/lab bg.png")).getScaledInstance(2000,1360,Image.SCALE_SMOOTH);
      pcU = ImageIO.read(new File("Images/apple.jpg")).getScaledInstance(20,20,Image.SCALE_SMOOTH);
      pcD = ImageIO.read(new File("Images/bomb.png")).getScaledInstance(20,20,Image.SCALE_SMOOTH);
      pcL = ImageIO.read(new File("Images/Cranberry.jpg")).getScaledInstance(20,20,Image.SCALE_SMOOTH);
      pcR = ImageIO.read(new File("Images/Crown.jpg")).getScaledInstance(20,20,Image.SCALE_SMOOTH);
      charImg = pcU;
      end = false;
      charW = 20;
      charX = 500 - charW/2;
      charY = 340 - charW/2;
      buffer = 50;
      bgX = 0;
      bgY = 0;
      leftBound = 495;
      rightBound = -495;
      topBound = 335;
      bottomBound = -335;
   }
   public void game() throws InterruptedException{
      while(!end){
         move();
         d.repaint();
         Thread.sleep(16,666667);
      }
   }
   
   public void move(){
   
      if(!lockCamX){
         if(rightHeld){
            bgX-=5;
         }
         if(leftHeld){
            bgX+=5;
         }
      }
      else{
         if(rightHeld){
            charX+=5;
         }
         if(leftHeld){
            charX-=5;
         }
      }
      
      if(!lockCamY){
         if(downHeld){
            bgY-=5;
         }
         if(upHeld){
            bgY+=5;
         }
      }
      else{
         if(downHeld){
            charY+=5;
         }
         if(upHeld){
            charY-=5;
         }
      }
      
      if(bgY>topBound||bgY<bottomBound){
         lockCamY = true;
      }
      if(bgX>leftBound||bgX<rightBound){
         lockCamX = true;
      }  
      
      if(lockCamY){
         if(bgY>0&&charY>340){//top half
            charY = 340;
            lockCamY = false;
         }
         else if(bgY<0&&charY<340){//bottom half
            charY = 340;
            lockCamY = false;
         }     
      }
      
      if(lockCamX){
         if(bgX>0&&charX>500){//left half
            charX = 500;
            lockCamX = false;
         }
         else if(bgX<0&&charX<500){//right half
            charX = 500;
            lockCamX = false;
         }   
      }
   

      if(charX>1000-charW){
         charX = 999-charW;
      }
      else if(charX<0){
         charX = 0;
      }
      if(charY>660-charW){
         charY = 659-charW;
      }
      else if(charY<0){
         charY = 0;
      }
   }
   
   public void interact(){
      if(!interact){
         for(int i = 0; i<obj.size(); i++){
            if(charX - bgX + charW + buffer >= obj.get(i).getX()  &&  charX - bgX - charW <= obj.get(i).getX() + obj.get(i).getW() + buffer && charY - bgY + charW + buffer >= obj.get(i).getY() && charY - bgY - charW <= obj.get(i).getY() + obj.get(i).getW() + buffer){
               interact = true;
               leftHeld = false;
               rightHeld = false;
               upHeld = false;
               downHeld = false;
            }
         }
      }
      else{
         interact = false;
      }
   }
   
   class Drawing extends JComponent{

      public void paintComponent(Graphics g){
         super.paintComponent(g);
         
         /*if(bgX<0){
            g.drawImage(bg,1000+bgX%1000,0,this);
         }
         else if(bgX>0){
            g.drawImage(bg,-1000+bgX%1000,0,this);
         }
         
         if(bgY<0){
            g.drawImage(bg, 0, 680+bgY%1000,this);
         }
         if(bgY>0){
            g.drawImage(bg,0,-680+bgY%1000,this);
         }*/
         
         g.drawImage(bg,bgX-500,bgY-340,this);
         
         for(int i = 0; i< obj.size(); i++){
            g.drawImage(obj.get(i).getImg(), obj.get(i).getX()+bgX, obj.get(i).getY()+bgY, this);
         }
         
         g.setColor(Color.blue);
         g.drawImage(charImg,charX,charY,this);
         //g.fillRect(charX,charY,charW,charW);
         
         if(interact){
            g.setColor(new Color(0,0,0,100));
            g.fillRect(0,0,1000,680);
            g.setColor(Color.red);
            g.fillRect(450,290,50,50);
            
         } 
         
      }
   }
   
   public void keyPressed(KeyEvent e){
      if(!interact){
         if(e.getKeyCode()==e.VK_RIGHT){
            rightHeld = true;
            charImg = pcR;
         }
         else if(e.getKeyCode() == e.VK_LEFT){
            leftHeld = true;
            charImg = pcL;
         }
         else if(e.getKeyCode() == e.VK_DOWN){
            downHeld = true;
            charImg = pcD;
         }
         else if(e.getKeyCode() == e.VK_UP){
            upHeld = true;
            charImg = pcU;
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
   
   public void run(){
      try{
         obj.add(new MazeObject(200,300,30,30,"Images/apple.jpg"));
         obj.add(new MazeObject(500,300,30,30, "Images/bomb.png"));
         game();
         
      }
      catch(Exception e){
      }
   }
   
}