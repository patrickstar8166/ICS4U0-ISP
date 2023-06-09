/**
* Level3 class, player is free-roaming and must apply their knowledge to win
* Code completed bty Nia Decaire with edits by Patrick Bian. Background and sprited done by Jonathan Liu
* <h2>Course Info:</h2>
* ICS4U0 with Krasteva, V.
*
* @version 08-06-2023
* @author BLD Studios
*/
import java.awt.*;    
import java.awt.event.*;  
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
public class Level3 extends JPanel implements Runnable{
   private Image bg, pcU, pcD, pcL, pcR, charImg, inv, endPlate, redX, bush, objImage;
   private boolean rightHeld, leftHeld, upHeld, downHeld, lockCamX, lockCamY, zHeld, iHeld, inventory, pause, end, run;
   private int charX, charY, charW, buffer, bgX, bgY, leftBound, rightBound, topBound, bottomBound, timer, score;
   private ArrayList<MazeObject> obj = new ArrayList<MazeObject>();
   public ArrayList<MazeObject> collected = new ArrayList<MazeObject>();
   private ArrayList<MazeObject> walls = new ArrayList<MazeObject>();
   private String badItem;
   private MazeObject currentObj;
   private JButton quit, restart;
   
   /**
   * Class constructor, intializes variables to default values, generates interactable objects and walls
   */
   public Level3() throws IOException{
      bg = Game.background3;
      pcU = Game.pcU;
      pcD = Game.pcD;
      pcL = Game.pcL;
      pcR = Game.pcR;
      inv = Game.inv;
      endPlate = Game.endPlate;
      bush = Game.bush;
      charImg = pcU;
      end = false;
      run = true;
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
      try{
         generateObjects();
      }catch(InterruptedException e){}
      addWalls();
      
      quit = new JButton("Quit");
      restart = new JButton("Restart");
      
      this.setLayout(null);
   
      quit.setBackground(Color.LIGHT_GRAY);
      quit.setBounds(850, 530, 100, 60);
   
      restart.setBackground(Color.LIGHT_GRAY);
      restart.setBounds(850, 455, 100, 60);
      
      this.add(quit);
      this.add(restart);
      quit.setVisible(false);
      restart.setVisible(false);
   }
   
   
   /**
   * Class constructor, used when returning to level 3 from minigame
   * @param timer The time remaining
   * @param charX The player's X position
   * @param charY The player's Y position
   * @param bgX The background's X position
   * @param bgY The background's Y position
   * @param lockCamX Whether the camera's X movement is locked
   * @param lockCamY Whether the camera's Y movement is locked
   * @param obj The list of interactable objects
   * @param collected The list of objects collected
   */
   public Level3(int timer, int charX, int charY, int bgX, int bgY, boolean lockCamX, boolean lockCamY, ArrayList<MazeObject> obj, ArrayList<MazeObject> collected) throws IOException{
      bg = Game.background3;
      pcU = Game.pcU;
      pcD = Game.pcD;
      pcL = Game.pcL;
      pcR = Game.pcR;
      inv = Game.inv;
      endPlate = Game.endPlate;
      bush = Game.bush;
      charImg = pcU;
      end = false;
      run = true;
      charW = 20;
      buffer = 50;
      leftBound = 495;
      rightBound = -495;
      topBound = 335;
      bottomBound = -335;
      addWalls();
      this.timer = timer;
      this.charX = charX;
      this.charY = charY;
      this.bgX = bgX;
      this.bgY = bgY;
      this.lockCamX = lockCamX;
      this.lockCamY = lockCamY;
      this.obj = obj;
      this.collected = collected;
      
      quit = new JButton("Quit");
      restart = new JButton("Restart");
      
      this.setLayout(null);
   
      quit.setBackground(Color.LIGHT_GRAY);
      quit.setBounds(850, 530, 100, 60);
   
      restart.setBackground(Color.LIGHT_GRAY);
      restart.setBounds(850, 455, 100, 60);
      
      this.add(quit);
      this.add(restart);
      quit.setVisible(false);
      restart.setVisible(false);
   }
   
   /**
   * Increments timer every loop, calculates score if timer runs out, calls movement and repaint methods every loop
   */
   public void game() throws InterruptedException{
      while(!end){
         if(!pause){
            timer++;
         }
         if(timer>7200){ //2 minutes
            end = true;
            calcScore();
         }
         move();
         repaint();
         Thread.sleep(16,666667);
      }
   }
   
   public boolean isRunning(){
      return run;
   }
   
   /**
   * Handles movement of player, scrolls screen if player is not near a wall, moves character if player is near a wall
   */
   public void move(){
   
      if(!lockCamX){
         if(rightHeld){
            if(collision(charX+5,charY))
               bgX-=5;
         }
         if(leftHeld){
            if(collision(charX-5,charY))
               bgX+=5;
         }
      }
      else{
         if(rightHeld){
            if(collision(charX+5,charY))
               charX+=5;
         }
         if(leftHeld){
            if(collision(charX-5,charY))
               charX-=5;
         }
      }
      
      if(!lockCamY){
         if(downHeld){
            if(collision(charX,charY+5))
               bgY-=5;
         }
         if(upHeld){
            if(collision(charX,charY-5))
               bgY+=5;
         }
      }
      else{
         if(downHeld){
            if(collision(charX,charY+5))
               charY+=5;
         }
         if(upHeld){
            if(collision(charX,charY-5))
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
   
   /**
   * Checks if player is colliding with a wall
   * @param x The x position of the player
   * @param y The y position of the player
   * @return Whether the player is colliding with a wall
   */
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
   /**
   * Adds a KeyListener, handles all input
   */
   public void keyInput(){
      this.addKeyListener(
         new KeyListener(){
            public void keyPressed(KeyEvent e){
               if(!inventory){
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
                  if(e.getKeyChar() == 'z'&&!zHeld){
                     interact();
                     zHeld = true;
                  }
               }
               if(e.getKeyChar() == 'i' && !iHeld){
                  inventory();
                  iHeld = true;
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
               
               if(e.getKeyChar() == 'i' && iHeld){
                  iHeld = false;
               }
               
            }
            public void keyTyped(KeyEvent e){
            }
         });
   }
      
   /**
   * Called when player hits interact key, checks if player is colliding with an interactable object. Changes screen to minigame if they are
   */
   public void interact(){
      for(int i = 0; i<obj.size(); i++){
         if(charX - bgX + charW>= obj.get(i).getX()  &&  charX - bgX<= obj.get(i).getX() + obj.get(i).getW() && charY - bgY + charW >= obj.get(i).getY() && charY - bgY <= obj.get(i).getY() + obj.get(i).getW()){
            
            Game.screenNum = 6;
            objImage = obj.get(i).getImg();
            currentObj = obj.get(i);
            obj.remove(obj.get(i));
            end = true;
            run = false;
            leftHeld = false;
            rightHeld = false;
            upHeld = false;
            downHeld = false;
            break;
         }
      }
   }
   
   /**
   * Called when player hits inventory key, pauses all movement and timer, switches visuals to inventory
   */
   
   public void inventory(){
      if(!inventory){
         inventory = true;
         leftHeld = false;
         rightHeld = false;
         upHeld = false;
         downHeld = false;
         pause = true;
      }
      else{
         inventory = false;
         pause = false;
      }
   }
   
   /**
   * Calculates which ending message should be displayed based on collected items
   */
   
   public void calcScore(){
      int count= 0;
      boolean badFound = false;
      for(int i = 0; i<collected.size(); i++){
         if(!collected.get(i).getPoison()){
            count++;
         }
         else{
            //System.out.println(collected.get(i).getPoison());
            score = 0;
            badItem = collected.get(i).getName();
            badFound = true;
         }
      }
      if(!badFound){
         if(count<6){
            score = 1;
         }
         else{
            score = 2;
         }
      }
   }
   
   /**
   * Generates object of each type of plant in a random preset position
   */
   
   public void generateObjects() throws IOException, InterruptedException{ //
      int[] tempX = {80,380,720,820,940,1140,1260,1300,1660,1820,180,860,1100,1460,1660,1940,1940,460,1340,1400,1700,300,600,900,1100,1780,1100,800,480,220,360,1340,1740,1360,500,980,1280,1300,1400,1940,1840,1720,1620,1400,1000,860,680,500,340};
      int[] tempY = {100,40,40,180,280,360,320,120,180,40,480,400,540,500,500,460,600,620,620,660,740,720,760,760,760,900,880,880,860,900,980,940,1060,1040,1080,1120,1180,1200,1120,1260,1260,1160,1200,1260,1260,1220,1260,1180,1200};
      ArrayList<Integer> x = new ArrayList<Integer>();
      ArrayList<Integer> y = new ArrayList<Integer>();
      ArrayList<MazeObject> plants = new ArrayList<MazeObject>();
      plants.add(new MazeObject(0,0,40,40,Game.img[0],"Saskatoon Berries",false));
      plants.add(new MazeObject(0,0,40,40,Game.img[1],"Dandelion",false));
      plants.add(new MazeObject(0,0,40,40,Game.img[2],"Crown Tipped Coral",false));
      plants.add(new MazeObject(0,0,40,40,Game.img[3],"Burdock",false));
      plants.add(new MazeObject(0,0,40,40,Game.img[4],"Jack Pine",false));
      plants.add(new MazeObject(0,0,40,40,Game.img[5],"Lobster Mushroom",false));
      plants.add(new MazeObject(0,0,40,40,Game.img[6],"Morel Mushroom",false));
      plants.add(new MazeObject(0,0,40,40,Game.img[7],"Cranberry",false));
      plants.add(new MazeObject(0,0,40,40,Game.img[8],"Raspberry",false));
      plants.add(new MazeObject(0,0,40,40,Game.img[9],"Cattail",false));
      plants.add(new MazeObject(0,0,40,40,Game.img[10],"Lily of the Valley",true));
      plants.add(new MazeObject(0,0,40,40,Game.img[11],"False Morel",true));
      plants.add(new MazeObject(0,0,40,40,Game.img[12],"Gilled Mushroom",true));
      plants.add(new MazeObject(0,0,40,40,Game.img[13],"Deadly Galerina",true));
      plants.add(new MazeObject(0,0,40,40,Game.img[14],"Poison Ivy",true));
      plants.add(new MazeObject(0,0,40,40,Game.img[15],"Baneberry Red",true));
      plants.add(new MazeObject(0,0,40,40,Game.img[16],"Blub-bearing Water Hemlock",true));
      plants.add(new MazeObject(0,0,40,40,Game.img[17],"Poison Hemlock",true));
      plants.add(new MazeObject(0,0,40,40,Game.img[18],"Water Hemlock",true));
      plants.add(new MazeObject(0,0,40,40,Game.img[19],"Blue Flag Iris",true));
      
      for(int i = 0; i<tempX.length; i++){
         x.add(tempX[i]);
         y.add(tempY[i]);
      }
      for(int i = 0; i < 20; i++){
         int num = (int)(Math.random()*x.size());
         plants.get(i).setX(x.get(num)-500);
         plants.get(i).setY(y.get(num)-340);
         x.remove(num);
         y.remove(num);
         obj.add(plants.get(i));
      } 
   }
   
   /**
   * Generates walls
   */
   
   public void addWalls() throws IOException{ 
      //top left corner: -500,-340
      walls.add(new MazeObject(-420,-320,40,40,bush));
      walls.add(new MazeObject(-400,-310+30,40,40,bush));
      walls.add(new MazeObject(-440+60,-290+50,40,40,bush));
      walls.add(new MazeObject(-440+60,-270+70,40,40,bush));
      walls.add(new MazeObject(-440+60,-250+90,40,40,bush));
      walls.add(new MazeObject(-440+60,-240+100,40,40,bush));
      walls.add(new MazeObject(-450+50,-230+110,40,40,bush));
      walls.add(new MazeObject(-460+40,-220+120,40,40,bush));
      
      walls.add(new MazeObject(-280+220,-320+20,40,40,bush));
      walls.add(new MazeObject(-290+210,-310+30,40,40,bush));
      walls.add(new MazeObject(-290+210,-300+40,40,40,bush));
      walls.add(new MazeObject(-300+200,-300+40,40,40,bush));
      walls.add(new MazeObject(-300+200,-290+50,40,40,bush));
      walls.add(new MazeObject(-310+190,-280+60,40,40,bush));
      walls.add(new MazeObject(-330+170,-270+70,40,40,bush));
      
      walls.add(new MazeObject(-500+640,-340+40,40,40,bush));
      walls.add(new MazeObject(-500+660,-340+60,40,40,bush));
      walls.add(new MazeObject(-500+700,-340+80,40,40,bush));
      walls.add(new MazeObject(-500+740,-340+100,40,40,bush));
      
      walls.add(new MazeObject(-500+920,-340+100,40,40,bush));
      walls.add(new MazeObject(-500+940,-340+80,40,40,bush));
      walls.add(new MazeObject(-500+960,-340+60,40,40,bush));
      walls.add(new MazeObject(-500+980,-340+40,40,40,bush));
      walls.add(new MazeObject(-500+1000,-340+20,40,40,bush));
      
      walls.add(new MazeObject(-500+610*2,-340+40*2,40,40,bush));
      walls.add(new MazeObject(-500+620*2,-340+60*2,40,40,bush));
      walls.add(new MazeObject(-500+630*2,-340+70*2,40,40,bush));
      walls.add(new MazeObject(-500+650*2,-340+80*2,40,40,bush));
      walls.add(new MazeObject(-500+670*2,-340+70*2,40,40,bush));
      walls.add(new MazeObject(-500+680*2,-340+60*2,40,40,bush));
      walls.add(new MazeObject(-500+700*2,-340+60*2,40,40,bush));
      walls.add(new MazeObject(-500+720*2,-340+70*2,40,40,bush));
      walls.add(new MazeObject(-500+740*2,-340+80*2,40,40,bush));
      walls.add(new MazeObject(-500+760*2,-340+90*2,40,40,bush));
      walls.add(new MazeObject(-500+770*2,-340+100*2,40,40,bush));
      walls.add(new MazeObject(-500+790*2,-340+110*2,40,40,bush));
      walls.add(new MazeObject(-500+810*2,-340+110*2,40,40,bush));
      walls.add(new MazeObject(-500+830*2,-340+110*2,40,40,bush));
      walls.add(new MazeObject(-500+850*2,-340+120*2,40,40,bush));
      walls.add(new MazeObject(-500+860*2,-340+120*2,40,40,bush));
      walls.add(new MazeObject(-500+870*2,-340+130*2,40,40,bush));
      walls.add(new MazeObject(-500+880*2,-340+140*2,40,40,bush));
      
      walls.add(new MazeObject(-500+1660,-340,40,40,bush));
      walls.add(new MazeObject(-500+1680,-340+40,40,40,bush));
      walls.add(new MazeObject(-500+1720,-340+40,40,40,bush));
      walls.add(new MazeObject(-500+1740,-340+60,40,40,bush));
      walls.add(new MazeObject(-500+1780,-340+80,40,40,bush));
      walls.add(new MazeObject(-500+1820,-340+100,40,40,bush));
      walls.add(new MazeObject(-500+1840,-340+100,40,40,bush));
      walls.add(new MazeObject(-500+1880,-340+120,40,40,bush));
      walls.add(new MazeObject(-500+1900,-340+160,40,40,bush));
      
      walls.add(new MazeObject(-500+180,-340+280,40,40,bush));
      walls.add(new MazeObject(-500+160,-340+300,40,40,bush));
      walls.add(new MazeObject(-500+160,-340+340,40,40,bush));
      walls.add(new MazeObject(-500+160,-340+380,40,40,bush));
      walls.add(new MazeObject(-500+160,-340+400,40,40,bush));
      walls.add(new MazeObject(-500+160,-340+420,40,40,bush));
      walls.add(new MazeObject(-500+140,-340+440,40,40,bush));
      walls.add(new MazeObject(-500+140,-340+460,40,40,bush));
      walls.add(new MazeObject(-500+120,-340+500,40,40,bush));
      walls.add(new MazeObject(-500+140,-340+520,40,40,bush));
      walls.add(new MazeObject(-500+160,-340+540,40,40,bush));
      walls.add(new MazeObject(-500+180,-340+560,40,40,bush));
      walls.add(new MazeObject(-500+200,-340+580,40,40,bush));
      walls.add(new MazeObject(-500+220,-340+620,40,40,bush));
      walls.add(new MazeObject(-500+200,-340+640,40,40,bush));
      walls.add(new MazeObject(-500+180,-340+660,40,40,bush));
      
      walls.add(new MazeObject(-500+320,-340+480,40,40,bush));
      walls.add(new MazeObject(-500+320,-340+500,40,40,bush));
      walls.add(new MazeObject(-500+300,-340+540,40,40,bush));
      walls.add(new MazeObject(-500+300,-340+560,40,40,bush));
      walls.add(new MazeObject(-500+280,-340+600,40,40,bush));
      walls.add(new MazeObject(-500+280,-340+620,40,40,bush));
      walls.add(new MazeObject(-500+300,-340+660,40,40,bush));
      walls.add(new MazeObject(-500+300,-340+680,40,40,bush));
      walls.add(new MazeObject(-500+340,-340+700,40,40,bush));
      
      walls.add(new MazeObject(-500+540,-340+300,40,40,bush));
      walls.add(new MazeObject(-500+540,-340+260,40,40,bush));
      walls.add(new MazeObject(-500+540,-340+240,40,40,bush));
      walls.add(new MazeObject(-500+560,-340+220,40,40,bush));
      walls.add(new MazeObject(-500+580,-340+200,40,40,bush));
      walls.add(new MazeObject(-500+620,-340+200,40,40,bush));
      walls.add(new MazeObject(-500+640,-340+180,40,40,bush));
      walls.add(new MazeObject(-500+680,-340+180,40,40,bush));
      walls.add(new MazeObject(-500+700,-340+180,40,40,bush));
      walls.add(new MazeObject(-500+720,-340+200,40,40,bush));
      walls.add(new MazeObject(-500+760,-340+220,40,40,bush));
      walls.add(new MazeObject(-500+800,-340+240,40,40,bush));
      walls.add(new MazeObject(-500+820,-340+220,40,40,bush));
      walls.add(new MazeObject(-500+860,-340+220,40,40,bush));
      walls.add(new MazeObject(-500+880,-340+200,40,40,bush));
      walls.add(new MazeObject(-500+900,-340+180,40,40,bush));
      
      walls.add(new MazeObject(-500+520,-340+480,40,40,bush));
      walls.add(new MazeObject(-500+560,-340+460,40,40,bush));
      walls.add(new MazeObject(-500+600,-340+440,40,40,bush));
      walls.add(new MazeObject(-500+640,-340+420,40,40,bush));
      walls.add(new MazeObject(-500+660,-340+400,40,40,bush));
      walls.add(new MazeObject(-500+680,-340+380,40,40,bush));
      walls.add(new MazeObject(-500+700,-340+360,40,40,bush));
      walls.add(new MazeObject(-500+740,-340+380,40,40,bush));
      walls.add(new MazeObject(-500+780,-340+400,40,40,bush));
      walls.add(new MazeObject(-500+800,-340+420,40,40,bush));
      walls.add(new MazeObject(-500+820,-340+440,40,40,bush));
      walls.add(new MazeObject(-500+860,-340+440,40,40,bush));
      walls.add(new MazeObject(-500+900,-340+440,40,40,bush));
      walls.add(new MazeObject(-500+900,-340+480,40,40,bush));
      walls.add(new MazeObject(-500+880,-340+520,40,40,bush));
      walls.add(new MazeObject(-500+860,-340+540,40,40,bush));
      walls.add(new MazeObject(-500+820,-340+560,40,40,bush));
      walls.add(new MazeObject(-500+900,-340+400,40,40,bush));
      walls.add(new MazeObject(-500+920,-340+360,40,40,bush));
      walls.add(new MazeObject(-500+940,-340+320,40,40,bush));
      walls.add(new MazeObject(-500+980,-340+300,40,40,bush));
      walls.add(new MazeObject(-500+1000,-340+280,40,40,bush));
      walls.add(new MazeObject(-500+1040,-340+260,40,40,bush));
      walls.add(new MazeObject(-500+1060,-340+240,40,40,bush));
      walls.add(new MazeObject(-500+1080,-340+240,40,40,bush));
      walls.add(new MazeObject(-500+1080,-340+220,40,40,bush));
      walls.add(new MazeObject(-500+1080,-340+240,40,40,bush));
      walls.add(new MazeObject(-500+1120,-340+240,40,40,bush));
      walls.add(new MazeObject(-500+1140,-340+280,40,40,bush));
      walls.add(new MazeObject(-500+1120,-340+300,40,40,bush));
      walls.add(new MazeObject(-500+1100,-340+340,40,40,bush));
      walls.add(new MazeObject(-500+1100,-340+360,40,40,bush));
      walls.add(new MazeObject(-500+1120,-340+400,40,40,bush));
      walls.add(new MazeObject(-500+1140,-340+440,40,40,bush));
      walls.add(new MazeObject(-500+1160,-340+480,40,40,bush));
      walls.add(new MazeObject(-500+1180,-340+520,40,40,bush));
      walls.add(new MazeObject(-500+1200,-340+560,40,40,bush));
      walls.add(new MazeObject(-500+1220,-340+580,40,40,bush));
      walls.add(new MazeObject(-500+1220,-340+620,40,40,bush));
      walls.add(new MazeObject(-500+1200,-340+660,40,40,bush));
      
      walls.add(new MazeObject(-500+1300,-340+420,40,40,bush));
      walls.add(new MazeObject(-500+1300,-340+400,40,40,bush));
      walls.add(new MazeObject(-500+1280,-340+400,40,40,bush));
      walls.add(new MazeObject(-500+1280,-340+360,40,40,bush));
      walls.add(new MazeObject(-500+1300,-340+340,40,40,bush));
      walls.add(new MazeObject(-500+1300,-340+300,40,40,bush));
      walls.add(new MazeObject(-500+1320,-340+280,40,40,bush));
      walls.add(new MazeObject(-500+1360,-340+260,40,40,bush));
      walls.add(new MazeObject(-500+1380,-340+260,40,40,bush));
      
      walls.add(new MazeObject(-500+1440,-340+380,40,40,bush));
      walls.add(new MazeObject(-500+1460,-340+400,40,40,bush));
      walls.add(new MazeObject(-500+1480,-340+420,40,40,bush));
      walls.add(new MazeObject(-500+1500,-340+460,40,40,bush));
      walls.add(new MazeObject(-500+1500,-340+500,40,40,bush));
      walls.add(new MazeObject(-500+1480,-340+540,40,40,bush));
      walls.add(new MazeObject(-500+1460,-340+560,40,40,bush));
      walls.add(new MazeObject(-500+1440,-340+600,40,40,bush));
      walls.add(new MazeObject(-500+1420,-340+620,40,40,bush));
      walls.add(new MazeObject(-500+1440,-340+660,40,40,bush));
      walls.add(new MazeObject(-500+1440,-340+700,40,40,bush));
      walls.add(new MazeObject(-500+1460,-340+740,40,40,bush));
      walls.add(new MazeObject(-500+1460,-340+760,40,40,bush));
      
      walls.add(new MazeObject(-500+1620,-340+820,40,40,bush));
      walls.add(new MazeObject(-500+1640,-340+820,40,40,bush));
      walls.add(new MazeObject(-500+1680,-340+800,40,40,bush));
      walls.add(new MazeObject(-500+1720,-340+780,40,40,bush));
      walls.add(new MazeObject(-500+1740,-340+760,40,40,bush));
      walls.add(new MazeObject(-500+1760,-340+720,40,40,bush));
      walls.add(new MazeObject(-500+1740,-340+680,40,40,bush));
      walls.add(new MazeObject(-500+1720,-340+640,40,40,bush));
      walls.add(new MazeObject(-500+1680,-340+600,40,40,bush));
      walls.add(new MazeObject(-500+1660,-340+580,40,40,bush));
      walls.add(new MazeObject(-500+1640,-340+540,40,40,bush));
      walls.add(new MazeObject(-500+1620,-340+500,40,40,bush));
      walls.add(new MazeObject(-500+1640,-340+460,40,40,bush));
      walls.add(new MazeObject(-500+1660,-340+420,40,40,bush));
      walls.add(new MazeObject(-500+1700,-340+400,40,40,bush));
      walls.add(new MazeObject(-500+1680,-340+380,40,40,bush));
      
      walls.add(new MazeObject(-500+1820,-340+440,40,40,bush));
      walls.add(new MazeObject(-500+1840,-340+420,40,40,bush));
      walls.add(new MazeObject(-500+1880,-340+400,40,40,bush));
      walls.add(new MazeObject(-500+1920,-340+400,40,40,bush));
      walls.add(new MazeObject(-500+1940,-340+380,40,40,bush));
      walls.add(new MazeObject(-500+1980,-340+380,40,40,bush));
      
      walls.add(new MazeObject(-500+1360,-340+720,40,40,bush));
      walls.add(new MazeObject(-500+1360,-340+700,40,40,bush));
      walls.add(new MazeObject(-500+1340,-340+660,40,40,bush));
      walls.add(new MazeObject(-500+1300,-340+640,40,40,bush));
      
      walls.add(new MazeObject(-500+1960,-340+680,40,40,bush));
      walls.add(new MazeObject(-500+1980,-340+660,40,40,bush));
      walls.add(new MazeObject(-500+1920,-340+660,40,40,bush));
      walls.add(new MazeObject(-500+1880,-340+640,40,40,bush));
      walls.add(new MazeObject(-500+1840,-340+620,40,40,bush));
      walls.add(new MazeObject(-500+1840,-340+600,40,40,bush));
      walls.add(new MazeObject(-500+1820,-340+580,40,40,bush));
      walls.add(new MazeObject(-500+1820,-340+560,40,40,bush));
      walls.add(new MazeObject(-500+1840,-340+540,40,40,bush));
      walls.add(new MazeObject(-500+1860,-340+560,40,40,bush));
      
      walls.add(new MazeObject(-500+1320,-340+900,40,40,bush));
      walls.add(new MazeObject(-500+1320,-340+860,40,40,bush));
      walls.add(new MazeObject(-500+1320,-340+820,40,40,bush));
      walls.add(new MazeObject(-500+1320,-340+780,40,40,bush));
      
      walls.add(new MazeObject(-500+860,-340+900,40,40,bush));
      walls.add(new MazeObject(-500+860,-340+880,40,40,bush));
      walls.add(new MazeObject(-500+840,-340+840,40,40,bush));
      walls.add(new MazeObject(-500+820,-340+840,40,40,bush));
      walls.add(new MazeObject(-500+820,-340+820,40,40,bush));
      walls.add(new MazeObject(-500+780,-340+820,40,40,bush));
      walls.add(new MazeObject(-500+800,-340+800,40,40,bush));
      walls.add(new MazeObject(-500+780,-340+780,40,40,bush));
      walls.add(new MazeObject(-500+760,-340+800,40,40,bush));
      walls.add(new MazeObject(-500+760,-340+760,40,40,bush));
      walls.add(new MazeObject(-500+720,-340+760,40,40,bush));
      walls.add(new MazeObject(-500+680,-340+760,40,40,bush));
      walls.add(new MazeObject(-500+720,-340+740,40,40,bush));
      walls.add(new MazeObject(-500+680,-340+740,40,40,bush));
      walls.add(new MazeObject(-500+640,-340+740,40,40,bush));
      walls.add(new MazeObject(-500+640,-340+900,40,40,bush));
      walls.add(new MazeObject(-500+640,-340+860,40,40,bush));
      walls.add(new MazeObject(-500+640,-340+820,40,40,bush));
      walls.add(new MazeObject(-500+640,-340+780,40,40,bush));
      walls.add(new MazeObject(-500+600,-340+720,40,40,bush));
      walls.add(new MazeObject(-500+560,-340+740,40,40,bush));
      walls.add(new MazeObject(-500+600,-340+700,40,40,bush));
      walls.add(new MazeObject(-500+620,-340+680,40,40,bush));
      walls.add(new MazeObject(-500+620,-340+660,40,40,bush));
      walls.add(new MazeObject(-500+660,-340+640,40,40,bush));
      
      walls.add(new MazeObject(-500+960,-340+1020,40,40,bush));
      walls.add(new MazeObject(-500+1000,-340+1000,40,40,bush));
      walls.add(new MazeObject(-500+1020,-340+980,40,40,bush));
      walls.add(new MazeObject(-500+1040,-340+960,40,40,bush));
      walls.add(new MazeObject(-500+1020,-340+920,40,40,bush));
      walls.add(new MazeObject(-500+1040,-340+880,40,40,bush));
      walls.add(new MazeObject(-500+1040,-340+840,40,40,bush));
      walls.add(new MazeObject(-500+1060,-340+820,40,40,bush));
      walls.add(new MazeObject(-500+1100,-340+800,40,40,bush));
      walls.add(new MazeObject(-500+1120,-340+780,40,40,bush));
      walls.add(new MazeObject(-500+1140,-340+760,40,40,bush));
      walls.add(new MazeObject(-500+1160,-340+720,40,40,bush));
      
      walls.add(new MazeObject(-500+80,-340+1020,40,40,bush));
      walls.add(new MazeObject(-500+60,-340+1000,40,40,bush));
      walls.add(new MazeObject(-500+60,-340+960,40,40,bush));
      walls.add(new MazeObject(-500+60,-340+920,40,40,bush));
      walls.add(new MazeObject(-500+60,-340+900,40,40,bush));
      walls.add(new MazeObject(-500+80,-340+880,40,40,bush));
      
      walls.add(new MazeObject(-500+120,-340+1300,40,40,bush));
      walls.add(new MazeObject(-500+100,-340+1260,40,40,bush));
      walls.add(new MazeObject(-500+100,-340+1240,40,40,bush));
      walls.add(new MazeObject(-500+80,-340+1220,40,40,bush));
      walls.add(new MazeObject(-500+60,-340+1180,40,40,bush));
      walls.add(new MazeObject(-500+80,-340+1160,40,40,bush));
      walls.add(new MazeObject(-500+120,-340+1140,40,40,bush));
      
      walls.add(new MazeObject(-500+760,-340+1320,40,40,bush));
      walls.add(new MazeObject(-500+720,-340+1300,40,40,bush));
      walls.add(new MazeObject(-500+680,-340+1300,40,40,bush));
      walls.add(new MazeObject(-500+660,-340+1300,40,40,bush));
      walls.add(new MazeObject(-500+620,-340+1280,40,40,bush));
      walls.add(new MazeObject(-500+600,-340+1260,40,40,bush));
      
      walls.add(new MazeObject(-500+440,-340+1300,40,40,bush));
      walls.add(new MazeObject(-500+420,-340+1320,40,40,bush));
      walls.add(new MazeObject(-500+380,-340+1320,40,40,bush));
      walls.add(new MazeObject(-500+340,-340+1300,40,40,bush));
      walls.add(new MazeObject(-500+320,-340+1280,40,40,bush));
      walls.add(new MazeObject(-500+300,-340+1280,40,40,bush));
      walls.add(new MazeObject(-500+320,-340+1240,40,40,bush));
      walls.add(new MazeObject(-500+300,-340+1200,40,40,bush));
      walls.add(new MazeObject(-500+300,-340+1180,40,40,bush));
      
      walls.add(new MazeObject(-500+840,-340+1300,40,40,bush));
      walls.add(new MazeObject(-500+860,-340+1280,40,40,bush));
      walls.add(new MazeObject(-500+880,-340+1260,40,40,bush));
      walls.add(new MazeObject(-500+900,-340+1220,40,40,bush));
      walls.add(new MazeObject(-500+900,-340+1180,40,40,bush));
      walls.add(new MazeObject(-500+920,-340+1160,40,40,bush));
      walls.add(new MazeObject(-500+940,-340+1140,40,40,bush));
      walls.add(new MazeObject(-500+980,-340+1160,40,40,bush));
      walls.add(new MazeObject(-500+1000,-340+1160,40,40,bush));
      walls.add(new MazeObject(-500+1020,-340+1180,40,40,bush));
      
      walls.add(new MazeObject(-500+680,-340+1100,40,40,bush));
      walls.add(new MazeObject(-500+700,-340+1060,40,40,bush));
      walls.add(new MazeObject(-500+700,-340+1040,40,40,bush));
      walls.add(new MazeObject(-500+720,-340+1000,40,40,bush));
      
      walls.add(new MazeObject(-500+1780,-340+1000,40,40,bush));
      walls.add(new MazeObject(-500+1800,-340+960,40,40,bush));
      walls.add(new MazeObject(-500+1800,-340+940,40,40,bush));
      walls.add(new MazeObject(-500+1820,-340+900,40,40,bush));
      walls.add(new MazeObject(-500+1820,-340+880,40,40,bush));
      walls.add(new MazeObject(-500+1800,-340+840,40,40,bush));
      
      walls.add(new MazeObject(-500+1700,-340+1220,40,40,bush));
      walls.add(new MazeObject(-500+1720,-340+1200,40,40,bush));
      walls.add(new MazeObject(-500+1760,-340+1180,40,40,bush));
      walls.add(new MazeObject(-500+1760,-340+1140,40,40,bush));
      walls.add(new MazeObject(-500+1780,-340+1120,40,40,bush));
      walls.add(new MazeObject(-500+1820,-340+1100,40,40,bush));
      walls.add(new MazeObject(-500+1860,-340+1080,40,40,bush));
      walls.add(new MazeObject(-500+1880,-340+1060,40,40,bush));
      walls.add(new MazeObject(-500+1920,-340+1040,40,40,bush));
      
      walls.add(new MazeObject(-500+1960,-340+1320,40,40,bush));
      walls.add(new MazeObject(-500+1920,-340+1300,40,40,bush));
      walls.add(new MazeObject(-500+1900,-340+1280,40,40,bush));
      walls.add(new MazeObject(-500+1880,-340+1240,40,40,bush));
      walls.add(new MazeObject(-500+1860,-340+1220,40,40,bush));
      walls.add(new MazeObject(-500+1840,-340+1220,40,40,bush));
      
      walls.add(new MazeObject(-500+1120,-340+1300,40,40,bush));
      walls.add(new MazeObject(-500+1120,-340+1280,40,40,bush));
      walls.add(new MazeObject(-500+1140,-340+1240,40,40,bush));
      walls.add(new MazeObject(-500+1160,-340+1200,40,40,bush));
      walls.add(new MazeObject(-500+1160,-340+1180,40,40,bush));
      walls.add(new MazeObject(-500+1180,-340+1140,40,40,bush));
      walls.add(new MazeObject(-500+1200,-340+1120,40,40,bush));
      walls.add(new MazeObject(-500+1220,-340+1120,40,40,bush));
      walls.add(new MazeObject(-500+1220,-340+1080,40,40,bush));
      walls.add(new MazeObject(-500+1200,-340+1060,40,40,bush));
      walls.add(new MazeObject(-500+1180,-340+1060,40,40,bush));
      walls.add(new MazeObject(-500+1180,-340+1040,40,40,bush));
      walls.add(new MazeObject(-500+1140,-340+1040,40,40,bush));
      walls.add(new MazeObject(-500+1240,-340+1140,40,40,bush));
      walls.add(new MazeObject(-500+1240,-340+1180,40,40,bush));
      walls.add(new MazeObject(-500+1240,-340+1200,40,40,bush));
      walls.add(new MazeObject(-500+1260,-340+1240,40,40,bush));
      walls.add(new MazeObject(-500+1300,-340+1260,40,40,bush));
      walls.add(new MazeObject(-500+1320,-340+1280,40,40,bush));
      walls.add(new MazeObject(-500+1340,-340+1240,40,40,bush));
      walls.add(new MazeObject(-500+1360,-340+1200,40,40,bush));
      walls.add(new MazeObject(-500+1360,-340+1180,40,40,bush));
      walls.add(new MazeObject(-500+1340,-340+1140,40,40,bush));
      walls.add(new MazeObject(-500+1360,-340+1100,40,40,bush));
      walls.add(new MazeObject(-500+1380,-340+1060,40,40,bush));
      walls.add(new MazeObject(-500+1400,-340+1020,40,40,bush));
      walls.add(new MazeObject(-500+1400,-340+1000,40,40,bush));
      walls.add(new MazeObject(-500+1360,-340+980,40,40,bush));
      walls.add(new MazeObject(-500+1340,-340+980,40,40,bush));
      walls.add(new MazeObject(-500+1300,-340+1000,40,40,bush));
      walls.add(new MazeObject(-500+1280,-340+1000,40,40,bush));
      walls.add(new MazeObject(-500+1260,-340+980,40,40,bush));
      walls.add(new MazeObject(-500+1240,-340+960,40,40,bush));
      walls.add(new MazeObject(-500+1360,-340+1300,40,40,bush));
      walls.add(new MazeObject(-500+1400,-340+1320,40,40,bush));
      walls.add(new MazeObject(-500+1440,-340+1320,40,40,bush));
      walls.add(new MazeObject(-500+1480,-340+1300,40,40,bush));
      walls.add(new MazeObject(-500+1520,-340+1300,40,40,bush));
      walls.add(new MazeObject(-500+1520,-340+1280,40,40,bush));
      walls.add(new MazeObject(-500+1540,-340+1280,40,40,bush));
      walls.add(new MazeObject(-500+1540,-340+1260,40,40,bush));
      walls.add(new MazeObject(-500+1560,-340+1240,40,40,bush));
      walls.add(new MazeObject(-500+1560,-340+1200,40,40,bush));
      walls.add(new MazeObject(-500+1560,-340+1180,40,40,bush));
      walls.add(new MazeObject(-500+1580,-340+1160,40,40,bush));
      walls.add(new MazeObject(-500+1600,-340+1120,40,40,bush));
      walls.add(new MazeObject(-500+1620,-340+1100,40,40,bush));
      walls.add(new MazeObject(-500+1620,-340+1080,40,40,bush));
      walls.add(new MazeObject(-500+1640,-340+1040,40,40,bush));
      walls.add(new MazeObject(-500+1680,-340+1040,40,40,bush));
      
      walls.add(new MazeObject(-500+760,-340+1200,40,40,bush));
      walls.add(new MazeObject(-500+740,-340+1200,40,40,bush));
      walls.add(new MazeObject(-500+720,-340+1180,40,40,bush));
      walls.add(new MazeObject(-500+680,-340+1180,40,40,bush));
      walls.add(new MazeObject(-500+660,-340+1160,40,40,bush));
      walls.add(new MazeObject(-500+620,-340+1160,40,40,bush));
      walls.add(new MazeObject(-500+580,-340+1160,40,40,bush));
      walls.add(new MazeObject(-500+540,-340+1160,40,40,bush));
      walls.add(new MazeObject(-500+520,-340+1140,40,40,bush));
      walls.add(new MazeObject(-500+480,-340+1140,40,40,bush));
      walls.add(new MazeObject(-500+440,-340+1120,40,40,bush));
      walls.add(new MazeObject(-500+400,-340+1080,40,40,bush));
      walls.add(new MazeObject(-500+380,-340+1100,40,40,bush));
      walls.add(new MazeObject(-500+360,-340+1060,40,40,bush));
      walls.add(new MazeObject(-500+340,-340+1060,40,40,bush));
      walls.add(new MazeObject(-500+340,-340+1040,40,40,bush));
      walls.add(new MazeObject(-500+320,-340+1020,40,40,bush));
      walls.add(new MazeObject(-500+300,-340+1000,40,40,bush));
      walls.add(new MazeObject(-500+320,-340+1000,40,40,bush));
      walls.add(new MazeObject(-500+320,-340+960,40,40,bush));
      walls.add(new MazeObject(-500+300,-340+960,40,40,bush));
      walls.add(new MazeObject(-500+300,-340+940,40,40,bush));
      walls.add(new MazeObject(-500+280,-340+940,40,40,bush));
      walls.add(new MazeObject(-500+280,-340+900,40,40,bush));
      walls.add(new MazeObject(-500+260,-340+900,40,40,bush));
      walls.add(new MazeObject(-500+260,-340+860,40,40,bush));
      walls.add(new MazeObject(-500+240,-340+860,40,40,bush));
      walls.add(new MazeObject(-500+260,-340+820,40,40,bush));
      walls.add(new MazeObject(-500+240,-340+820,40,40,bush));
      walls.add(new MazeObject(-500+220,-340+840,40,40,bush));
      walls.add(new MazeObject(-500+260,-340+780,40,40,bush));
      
      for(int i = 0; i<2000/40; i++){
         walls.add(new MazeObject(-500+i*40,-340,40,40,bush));
         walls.add(new MazeObject(-500+i*40,960,40,40,bush));
      }
      for(int i = 0; i<1360/40; i++){
         walls.add(new MazeObject(-500, -340+i*40,40,40,bush));
         walls.add(new MazeObject(1480, -340+i*40,40,40,bush));
      }
   }
   
   @Override
   /**
   * Displays visuals for level
   */
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      if(!end){
         g.drawImage(bg,bgX-500,bgY-340,2000,1360,this);
         
         for(int i = 0; i< obj.size(); i++){
            g.drawImage(obj.get(i).getImg(), obj.get(i).getX()+bgX, obj.get(i).getY()+bgY,40,40, this);
            g.setColor(Color.red);
            g.drawRect(obj.get(i).getX()+bgX,obj.get(i).getY()+bgY,40,40);
         }
         for(int i = 0; i<walls.size(); i++){
            g.drawImage(walls.get(i).getImg(),walls.get(i).getX()+bgX,walls.get(i).getY()+bgY,40,40,this);
         }
         
         g.setColor(Color.blue);
         g.drawImage(charImg,charX,charY,20,20,this);
         
         if(inventory){
            g.drawImage(inv,0,0,1000,680,this);
            for(int i = 0; i<collected.size(); i++){
               g.drawImage(collected.get(i).getImg(),i%8*55+285,i/8*55+245,50,50,this);
            }
         }
         g.setColor(Color.orange);
         g.fillRect(875,25,0+(7200-timer)/72,40);
         g.setColor(Color.black);
         g.drawRect(875,25,100,40);
      }
      
      if(end){
         g.drawImage(endPlate,0,0,1000,680,this);
         for(int i = 0; i<collected.size(); i++){
            g.drawImage(collected.get(i).getImg(),i%8*55+285,i/8*55+340,50,50,this);
         }
         g.setFont(new Font("Sans Serif", Font.BOLD, 30));
         if(score==0){
            g.drawImage(redX,400,50,this);
            g.drawString("You failed to forage a meal. You foraged the ",120,100);
            g.drawString(badItem+ ", which is inedible.",100,200);
         }
         else if(score ==1){
            g.drawString("You failed to forage a meal. You did not", 100,100);
            g.drawString("forage enough food. Forage at least 6 items.", 100,200);
         }
         else{
            g.drawString("You succeeded in foraging a meal!! Good job!!!",100,100);
         }
         quit.setVisible(true);
         restart.setVisible(true);
      }
   }
   
   public int getTimer(){
      return timer;
   }
   public int getCharX(){
      return charX;
   }
   public int getCharY(){
      return charY;
   }
   public int getBgX(){
      return bgX;
   }
   public int getBgY(){
      return bgY;
   }
   public boolean getLockCamX(){
      return lockCamX;
   }
   public boolean getLockCamY(){
      return lockCamY;
   }
   public ArrayList<MazeObject> getObj(){
      return obj;
   }
   public ArrayList<MazeObject> getCollected(){
      return collected;
   }
   public Image getObjImage(){
      return objImage;
   }
   public MazeObject getCurrentObj(){
      return currentObj;
   }
   
   public void addCollected(MazeObject m){
      collected.add(m);
   }
   
   /**
   * Called when Level3 Thread is started, calls keyInput and Game
   */
   
   public void run(){
      try{
         end = false;
         keyInput();
         game();
         quit.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  quit.removeActionListener(this);
                  quit.setVisible(false);
                  restart.setVisible(false);
                  Game.screenNum = 7;
                  run = false;
               }
            });
      
         restart.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  restart.removeActionListener(this);
                  quit.setVisible(false);
                  restart.setVisible(false);
                  Game.screenNum = 1;
                  run = false;
               }
            });
      
      }
      catch(Exception e){
      }
   }
   
}