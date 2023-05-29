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
   private boolean rightHeld, leftHeld, zHeld, interact, freezeLeft, end;
   private int bgOffset, charX, charW, buffer, leftBound, rightBound;
   private ArrayList<BgObject> obj = new ArrayList<BgObject>();
   private int[] names= {2, 3};
   
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
      charX = 490;
      charW = 20;
      buffer = 50;
      leftBound = 1000;
      rightBound = -1000;
      freezeLeft = false;
      
   }
   
   public void game() throws InterruptedException{
      
      while(!end){
         if(!(bgOffset>1000||bgOffset<-1000)){
            if(rightHeld){
               bgOffset-=5;
            }
            if(leftHeld){
               bgOffset+=5;
            }
         }
         else{
         
            if(leftHeld){
               charX-=5;
               //System.out.println("test");
               if(!freezeLeft&&bgOffset>1000){
                  freezeLeft = true;
                  //System.out.println("test2");
               }
            }
            else if(rightHeld){
               charX+=5;
            }
         
            if(charX>1000-charW){
               charX = 979;
            }
            else if(charX<0){
               charX = 0;
            }
            //below code should unfreeze the camera but it doesnt work lmao
            if(charX>=490&& freezeLeft){
               charX = 490;
               freezeLeft = false;
               bgOffset-=5;
               System.out.println("first");
            }
            else if(charX<=490&&!freezeLeft){
               charX = 490;
               bgOffset+=5;
               System.out.println("second");
            }
         }
         
         
         d.repaint();
         Thread.sleep(16,666667);
      }
   }
   
   public void interact(){
      if(!interact){
         for(int i = 0; i <obj.size(); i++){
            if(charX-bgOffset+charW+buffer>=obj.get(i).getX() && charX-bgOffset<= obj.get(i).getX()+obj.get(i).getW()+buffer){
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
         obj.add(new BgObject(-200,300,30,30,"apple.jpg"));
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
      
      
      if(e.getKeyChar() == 'z' && zHeld){
         zHeld = false;
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
         /*for(int i = 0; i <10; i++){
            g.fillRect(-1000+i*200+bgOffset,400-i*10,30,30+i*10);
         }*/
         
         for(int i = 0; i < obj.size(); i++){
            g.drawImage(obj.get(i).getImg(),obj.get(i).getX()+bgOffset,obj.get(i).getY(),this);
         }
         
         g.setColor(Color.blue);
         g.fillRect(charX,400,charW,20);
         
         if(interact){
            g.setColor(new Color(0,0,0,100));
            g.fillRect(0,0,1000,680);
            g.setColor(Color.red);
            g.fillRect(450,290,50,50);
            
         }  
         
            
      }
      
   }

}