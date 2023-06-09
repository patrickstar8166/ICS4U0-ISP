/**
* Minigame class, small game used in Level3 whenever harvest is attempted
* Code completed by Nia Decaire with edits by Jonathan Liu
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

public class Minigame extends JPanel implements Runnable{

   private boolean rightHeld, leftHeld, end, win, run;
   Player p = new Player(); //creates player object
   private double timer; //timer for object generation
   ArrayList<FallingObject> f = new ArrayList<FallingObject>(); //array list of all active falling objects
   private Image plantImg;
   private MazeObject obj;
   private Toolkit t = Toolkit.getDefaultToolkit();
   private Image bee = Game.minigameBee;
   private Image background = Game.minigameBg;
   
   /**
   * Class constructor
   * @param m The MazeObject representing the plant that the player is harvesting
   */
   
   public Minigame(MazeObject m) throws IOException{//constructor, generates frame and initializes variables
      timer = 0;
      rightHeld = false; 
      leftHeld = false;
      end = false;
      run = true;
      obj = m;
      plantImg = m.getImg();

   }

   @Override
   /**
   * Displays visuals
   */
   public void paintComponent (Graphics g) //drawing method
   {
      if(!end){
         super.paintComponent(g);
         g.drawImage(background,0,0,1000,680,this);

         //draws all falling objects in the array list
         //checks what falling object type it is before displaying   
         for(int i = 0; i<f.size(); i++){
            if(f.get(i).getBad()){
               g.drawImage(bee,f.get(i).getX(),f.get(i).getY(),40,40,this); 
            }
            else{
               g.drawImage(plantImg,f.get(i).getX(),f.get(i).getY(),40,40,this);
            }
            
         }
         
         //player entity
         g.drawImage(Game.minigameChar,p.getX(),p.getY(),50,100,this);
         g.drawImage(Game.minigameBasket,p.getX()-50,p.getY()-30,150,30,this);
         
         
         //health bar
         g.setColor(Color.black);
         g.drawRect(29,49,101,31);
         g.setColor(Color.red);
         g.fillRect(30,50,10*p.getHealth(),30);
         
         //progress bar
         g.setColor(Color.black);
         g.drawRect(829,49,101,31);
         g.setColor(Color.green);
         g.fillRect(830,50,(4*p.getCaught()),30);
      }
      //win state
      else if(win){
          
         super.paintComponent(g);
         g.drawImage(background,0,0,1000,680,this);
         g.setColor(Color.green);
         g.setFont(new Font("Sans Serif", Font.BOLD, 80));
         g.drawString("You win!", 348,150);
         g.setFont(new Font("Sans Serif", Font.BOLD, 40));
         g.drawString("You got the item below!",240,320);
            if(plantImg != null){
               g.drawImage(plantImg,470,370,100,100,this);
            }

      }
      //lose state
      else{
        
         super.paintComponent(g);
        g.drawImage(background,0,0,1000,680,this);
         g.setColor(Color.red);
         g.setFont(new Font("Sans Serif", Font.BOLD, 80));
         g.drawString("You lose",350,150);
         g.setFont(new Font("Sans Serif", Font.BOLD, 40));
         g.drawString("You did not get the item below:",250,320);
            if(plantImg != null){
               g.drawImage(plantImg,470,370,100,100,this);
            }
      }

      
   }
   /**
   * Called when Minigame thread starts, calls keyInput and game
   */
   public void run(){
      try{
         keyInput();
         game();
      }
      catch(Exception e){
         
      }
      
   }
   
   /**
   * Adds KeyListener to handle input
   */
   
   public void keyInput(){
      this.addKeyListener(
         new KeyListener(){
            public void keyPressed(KeyEvent e){
               if(e.getKeyCode()==e.VK_RIGHT){
                  rightHeld = true;
         
               }
               if(e.getKeyCode() == e.VK_LEFT){
                  leftHeld = true;
                  
               }
            }
   
            public void keyReleased(KeyEvent e){
            
               if(e.getKeyCode()==e.VK_RIGHT){
                  rightHeld = false;
               }
               if(e.getKeyCode() == e.VK_LEFT){
                  leftHeld = false;
               }
            }
            public void keyTyped(KeyEvent e){
            }
         });
      }

   
   
   /**
   * handles updates and display, runs on 60 fps
   */
   public void game() throws InterruptedException{ 
      
      while(!end){
         //calls fall method on every falling object         
         for(int i = 0; i<f.size(); i++){
            f.get(i).fall();
         }
         
         //checks if right xor left is held, calls move method
         if(rightHeld^leftHeld){
            p.move(rightHeld,leftHeld);
         }
         
         //increments timer
         timer+= 1;
         //every 8 frames, resets timer and spawns falling object
         if(timer>8){
            //System.out.println("timer");
            timer = 0;
            f.add(generate());
         }
         
         //feeds player hit detection method every falling object
         //checks if objects are marked for deletion
         int i = 0;
         while(i< f.size()){
            p.hitDetect(f.get(i));
            if(f.get(i).getDelete()){
               f.remove(i);
            }
            else{
               i++;
            }
         }  
         
         //game end conditions
         if(p.getHealth()<1){
            end = true;
            win = false;
         }
         if(p.getCaught()>=25){
            end = true;
            win = true;
         }
         
         //updates frame
         //System.out.println("repaint");
         repaint();
         //System.out.println("repaint2");

         //delay for 1/60th second
         Thread.sleep(16,666667);
         //Thread and runnable learned from https://www.geeksforgeeks.org/runnable-interface-in-java/
      }
      Thread.sleep(1000);
      run = false;
   }
   
   
   /**
   * generates a new falling object with x coordinate within bounds of the screen
   */
   public FallingObject generate(){
      return new FallingObject((int)(Math.random()*980));
   }
   
   //gets the end state of the game
   public boolean isRunning(){
      return run;
   }
   public boolean gameWon(){
      return win;
   }
   public MazeObject getObj(){
      return obj;
   }
   
      
}