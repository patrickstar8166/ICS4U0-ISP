import java.awt.*;    
import java.awt.event.*;  
import javax.swing.*;
import java.util.*;

public class Minigame implements KeyListener, Runnable{
   
   Drawing d = new Drawing();
   private boolean rightHeld, leftHeld, end, win;
   Player p = new Player();
   private double timer;
   ArrayList<FallingObject> f = new ArrayList<FallingObject>();
   
   public Minigame(){
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
      public void paint (Graphics g)
      {
         if(!end){
            for(int i = 0; i<f.size(); i++){
               if(f.get(i).getBad()){
                  g.setColor(Color.red);
               }
               else{
                  g.setColor(Color.green);
               }
               g.fillRect(f.get(i).getX(),f.get(i).getY(),20,20);
            }
            g.setColor(Color.blue);
            g.fillRect(p.getX(),p.getY(),50,100);
            g.setColor(Color.red);
            g.fillRect(p.getX()-50,p.getY()-30,150,30);
            
            g.setColor(Color.black);
            g.drawRect(29,49,101,31);
            g.setColor(Color.red);
            g.fillRect(30,50,10*p.getHealth(),30);
            
            g.setColor(Color.black);
            g.drawRect(829,49,101,31);
            g.setColor(Color.green);
            g.fillRect(830,50,(4*p.getCaught()),30);
         }
         else if(win){
            g.setColor(Color.green);
            g.drawString("You win", 300,400);
         }
         else{
            g.setColor(Color.red);
            g.drawString("You lose",300,400);
         }
         
         
         
      }
   }
   
   public void run(){
      display();
   }
   
   public void display(){ //REALLY needs implementation of runnable, this is super jank
      while(!end){
         //System.out.println("X: "+p.getX()+ " Y: "+p.getY());
         timer+= 0.0001;
         for(int i = 0; i<f.size(); i++){
            f.get(i).fall();
         }
         if(rightHeld^leftHeld){
            p.move(rightHeld,leftHeld);
         }
         if(timer>300){
            System.out.println("timer");
            timer = 0;
            f.add(generate());
         }
         
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
         
         if(p.getHealth()<1){
            end = true;
            win = false;
         }
         if(p.getCaught()>=25){
            end = true;
            win = true;
         }
         
         d.repaint();
         
         
      }
   }
   
   public FallingObject generate(){
      return new FallingObject((int)(Math.random()*1000));
   }
   
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
         //System.out.println("stopping movement");
      }
      if(e.getKeyCode() == e.VK_LEFT){
         leftHeld = false;
         //System.out.println("stopping movement");
      }
   }
   
   public void keyTyped(KeyEvent e){
   
   }
   public boolean getEnd(){
      return end;
   }
   
   class Player{
      private double x,y;
      private int caught;
      private int health;
      
      public Player(){
         x = 490;
         y = 420;
         caught = 0;
         health = 10;
      }
      public Player(double x, double y){
         this.x = x;
         this.y = y;
      }
      
      public void move(boolean right, boolean left){
         if(left){
            x-=0.00012;
         }
         else if(right){
            x+=0.00012;
         }
         if(x<50){
            x = 50;
         }
         else if(x>900){
            x = 900;
         }
      }
      
      public void hitDetect(FallingObject f){
         if(f.getX() >= x-70 && f.getX() <= x+100 && f.getY() >= y-30 && f.getY() <= y){
            if(f.getBad()){
               health --;
               System.out.println("Health: "+health);
            }
            else{
               caught++;
               System.out.println("caught: " +caught);
            }
            f.setDelete();
         }
         
      }
      
      public int getX(){
         return (int)x;
      }
      
      public int getY(){
         return (int)y;
      }
      
      public int getHealth(){
         return health;
      }
      
      public int getCaught(){
         return caught;
      }
      
   }
   
   class FallingObject{
      private int x;
      private double y;
      private boolean bad;
      private boolean delete;
      public FallingObject(int x){
         this.x = x;
         y = -20;
         bad = (Math.random()<0.40);
      }
      
      public int getX(){
         return x;
      }
      
      public int getY(){
         return (int) y;
      }
      
      public boolean getBad(){
         return bad;
      }
      
      public boolean getDelete(){
         return delete;
      }  
      
      public void fall(){
         y+=0.00004;
         if(y>680){
            delete = true;
         }  
      }
      public void setDelete(){
         delete = true;
      }
      
   
   }
   
   /*public static void main(String[] args)
   {
      Minigame m = new Minigame();
      m.display();
   }*/

}