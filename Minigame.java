import java.awt.*;    
import java.awt.event.*;  
import javax.swing.*;
import java.util.*;
import java.lang.*;

public class Minigame implements KeyListener, Runnable{
   
   Drawing d = new Drawing();
   private boolean rightHeld, leftHeld, end, win;
   Player p = new Player(); //creates player object
   private double timer; //timer for object generation
   ArrayList<FallingObject> f = new ArrayList<FallingObject>(); //array list of all active falling objects
   
   public Minigame(){//constructor, generates frame and initializes variables
      timer = 0;
      rightHeld = false; 
      leftHeld = false;
      end = false;
      JFrame frame = new JFrame("Minigame");
      frame.setSize(1000,680);
      frame.add(d);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.addKeyListener(this);
      
   }
   
   class Drawing extends JComponent
   {
      public void paint (Graphics g)//drawing method
      {
         if(!end){
         
            //draws all falling objects in the array list
            //checks what falling object type it is before displaying
            for(int i = 0; i<f.size(); i++){
               if(f.get(i).getBad()){
                  g.setColor(Color.red);
               }
               else{
                  g.setColor(Color.green);
               }
               g.fillRect(f.get(i).getX(),f.get(i).getY(),20,20);
            }
            
            //player entity
            g.setColor(Color.blue);
            g.fillRect(p.getX(),p.getY(),50,100);
            g.setColor(Color.red);
            g.fillRect(p.getX()-50,p.getY()-30,150,30);
            
            
            //health bar
            g.setColor(Color.black);
            g.drawRect(29,49,101,31);
            g.setColor(Color.red);
            g.fillRect(30,50,20*p.getHealth(),30);
            
            //progress bar
            g.setColor(Color.black);
            g.drawRect(829,49,101,31);
            g.setColor(Color.green);
            g.fillRect(830,50,(4*p.getCaught()),30);
         }
         //win state
         else if(win){
            g.setColor(Color.green);
            g.drawString("You win", 300,400);
         }
         //lose state
         else{
            g.setColor(Color.red);
            g.drawString("You lose",300,400);
         }
         
         
         
      }
   }
   
   public void run(){
      try{
         game();
      }
      catch(Exception e){
         
      }
   }
   
   //handles updates and display, runs on 60 fps
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
         d.repaint();
         
         //delay for 1/60th second
         Thread.sleep(16,666667);
      }
   }
   
   
   //generates a new falling object with x coordinate within bounds of the screen
   public FallingObject generate(){
      return new FallingObject((int)(Math.random()*980));
   }
   
   //uses keyPressed and keyReleased in conjunction to avoid movement delay
   
   
   //moves direction pressed until key is released
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
   //gets the end state of the game
   public boolean getEnd(){
      return end;
   }
   
      

}