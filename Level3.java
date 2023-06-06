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
   private Image bg, pcU, pcD, pcL, pcR, charImg, inv;
   private boolean rightHeld, leftHeld, upHeld, downHeld, lockCamX, lockCamY, zHeld, iHeld, interact, inventory, end;
   private int charX, charY, charW, buffer, bgX, bgY, leftBound, rightBound, topBound, bottomBound, timer, score;
   private ArrayList<MazeObject> obj = new ArrayList<MazeObject>();
   private ArrayList<MazeObject> collected = new ArrayList<MazeObject>();
   private ArrayList<MazeObject> walls = new ArrayList<MazeObject>();
   
   public Level3() throws IOException{
      JFrame frame = new JFrame("Level 3");
      frame.setSize(2000,1360);
      frame.add(d);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.addKeyListener(this);
      bg = ImageIO.read(new File("Images/lab bg.png")).getScaledInstance(2000,1360,Image.SCALE_SMOOTH);
      pcU = ImageIO.read(new File("Images/apple.jpg")).getScaledInstance(20,20,Image.SCALE_SMOOTH);
      pcD = ImageIO.read(new File("Images/bomb.png")).getScaledInstance(20,20,Image.SCALE_SMOOTH);
      pcL = ImageIO.read(new File("Images/Cranberry.jpg")).getScaledInstance(20,20,Image.SCALE_SMOOTH);
      pcR = ImageIO.read(new File("Images/Crown.jpg")).getScaledInstance(20,20,Image.SCALE_SMOOTH);
      inv = ImageIO.read(new File("Images/inv.png")).getScaledInstance(1000,680,Image.SCALE_SMOOTH);
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
      timer = 0;
   }
   public void game() throws InterruptedException{
      while(!end){
         timer++;
         if(timer>10800){ //3 minutes
            end = true;
            calcScore();
         }
         move();
         d.repaint();
         Thread.sleep(16,666667);
      }
   }
   
   public void move(){
   
      if(!lockCamX){
         if(rightHeld){
            if(collision(charX+4,charY))
               bgX-=5;
         }
         if(leftHeld){
            if(collision(charX-4,charY))
               bgX+=5;
         }
      }
      else{
         if(rightHeld){
            if(collision(charX+4,charY))
               charX+=5;
         }
         if(leftHeld){
            if(collision(charX-4,charY))
               charX-=5;
         }
      }
      
      if(!lockCamY){
         if(downHeld){
            if(collision(charX,charY+4))
               bgY-=5;
         }
         if(upHeld){
            if(collision(charX,charY-4))
               bgY+=5;
         }
      }
      else{
         if(downHeld){
            if(collision(charX,charY+4))
               charY+=5;
         }
         if(upHeld){
            if(collision(charX,charY-4))
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
   
   public boolean collision(int x, int y){
      for(int i = 0; i<walls.size(); i++){
         if(x - bgX + charW > walls.get(i).getX() && 
            x - bgX < walls.get(i).getX() + walls.get(i).getW() && 
            y - bgY + charW > walls.get(i).getY() && 
            y - bgY < walls.get(i).getY() + walls.get(i).getW()){
            return false;
         }
      }
      return true;
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
   
   public void inventory(){
      if(!inventory){
         inventory = true;
         leftHeld = false;
         rightHeld = false;
         upHeld = false;
         downHeld = false;
      }
      else{
         inventory = false;
      }
   }
   
   public void calcScore(){
      for(int i = 0; i<collected.size(); i++){
         if(!collected.get(i).getPoison()){
            score++;
         }
         else{
            score = -1;
            //String poisonItem = the item that is poisonous magically
            break;
         }
      }
   }
   
   class Drawing extends JComponent{

      public void paintComponent(Graphics g){
         super.paintComponent(g);
         g.drawImage(bg,bgX-500,bgY-340,this);
         
         for(int i = 0; i< obj.size(); i++){
            g.drawImage(obj.get(i).getImg(), obj.get(i).getX()+bgX, obj.get(i).getY()+bgY, this);
         }
         for(int i = 0; i<walls.size(); i++){
            g.drawImage(walls.get(i).getImg(),walls.get(i).getX()+bgX,walls.get(i).getY()+bgY,this);
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
         
         if(inventory){
            g.drawImage(inv,0,0,this);
            //for(int row = 0; row< collected.size()/5+1; row++){
               //for(int col = 0; col<5 && col<collected.size(); col++){
                //  g.drawImage(collected.get(row*5+col).getImg(),col*100,row*100,this);
              // }
            //}
            for(int i = 0; i<collected.size(); i++){
               g.drawImage(collected.get(i).getImg(),i%8*55+285,i/8*55+245,this);
            }
            
         }
         
         
      }
   }
   
   public void keyPressed(KeyEvent e){
      if(!interact&&!inventory){
         if(e.getKeyCode()==e.VK_RIGHT){
            rightHeld = true;
            charImg = pcR;
         }
         if(e.getKeyCode() == e.VK_LEFT){
            leftHeld = true;
            charImg = pcL;
         }
         if(e.getKeyCode() == e.VK_DOWN){
            downHeld = true;
            charImg = pcD;
         }
         if(e.getKeyCode() == e.VK_UP){
            upHeld = true;
            charImg = pcU;
         }
         
         
      }
      if(!inventory){
         if(e.getKeyChar() == 'z'&&!zHeld){
            interact();
            zHeld = true;
         }
      }
      
      if(!interact){
         if(e.getKeyChar() == 'i' && !iHeld){
            inventory();
            iHeld = true;
         }
      }
      /*if(e.getKeyChar() =='e'){
         bgX = 0;
         bgY = 0;
      }*/
      
      
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
      
      if(e.getKeyChar() == 'i' && iHeld){
         iHeld = false;
      }
      
   }
   public void keyTyped(KeyEvent e){
      
   }
   
   public void run(){
      try{
         obj.add(new MazeObject(200,300,30,30,"Images/apple.jpg"));
         obj.add(new MazeObject(500,300,30,30, "Images/bomb.png"));
         collected.add(new MazeObject(-200,300,50,50,"Images/apple.jpg"));
         collected.add(new MazeObject(-200,300,50,50,"Images/Cranberry.jpg"));
         collected.add(new MazeObject(-200,300,50,50,"Images/Cranberry.jpg"));
         collected.add(new MazeObject(-200,300,50,50,"Images/Cranberry.jpg"));
         collected.add(new MazeObject(-200,300,50,50,"Images/Cranberry.jpg"));
         collected.add(new MazeObject(-200,300,50,50,"Images/Cranberry.jpg"));
         collected.add(new MazeObject(-200,300,50,50,"Images/apple.jpg"));
         collected.add(new MazeObject(-200,300,50,50,"Images/Cranberry.jpg"));
         collected.add(new MazeObject(-200,300,50,50,"Images/Cranberry.jpg"));
         collected.add(new MazeObject(-200,300,50,50,"Images/Cranberry.jpg"));
         collected.add(new MazeObject(-200,300,50,50,"Images/Cranberry.jpg"));
         collected.add(new MazeObject(-200,300,50,50,"Images/Cranberry.jpg"));
         //walls.add(new MazeObject(-200,300,50,50,"Images/bomb.png"));
         walls.add(new MazeObject(250,300,50,50,"Images/apple.jpg"));
         walls.add(new MazeObject(200,300,50,50,"Images/apple.jpg"));
         walls.add(new MazeObject(250,350,50,50,"Images/apple.jpg"));
         game();
         
      }
      catch(Exception e){
      }
   }
   
}