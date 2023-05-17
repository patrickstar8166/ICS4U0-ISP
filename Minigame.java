import java.awt.*;    
import java.awt.event.*;  
import javax.swing.*;

public class Minigame implements KeyListener{
   
   Drawing d = new Drawing();
   private boolean rightHeld, leftHeld,end;
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
         g.setColor(Color.blue);
         g.fillRect(p.getX(),p.getY(),50,100);
         g.setColor(Color.red);
         g.fillRect(p.getX()-50,p.getY()-30,150,30);
         for(int i = 0; i<f.size(); i++){
            g.fillRect(f.get(i).getX(),f.get(i).getY(),20,20);
         }
         
      }
   }
   public void display(){ //REALLY needs implementation of runnable, this is super jank
      while(!end){
         //System.out.println("X: "+p.getX()+ " Y: "+p.getY());
         timer+= 0.0001;
         if(rightHeld^leftHeld){
            p.move(rightHeld,leftHeld);
         }
         if(timer>1){
            timer = 0;
            f.add(generate());
         }
         
         d.repaint();
         
         
      }
   }
   
   public FallingObject generate(){
      return new FallingObject(Math.random()*1000);
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
         System.out.println("stopping movement");
      }
      if(e.getKeyCode() == e.VK_LEFT){
         leftHeld = false;
         System.out.println("stopping movement");
      }
   }
   
   public void keyTyped(KeyEvent e){
   
   }
   class Player{
      private double x,y;
      public Player(){
         x = 490;
         y = 420;
      }
      public Player(double x, double y){
         this.x = x;
         this.y = y;
      }
      
      public void move(boolean right, boolean left){
         if(left){
            x-=0.00008;
         }
         else if(right){
            x+=0.00008;
         }
         if(x<50){
            x = 50;
         }
         else if(x>900){
            x = 900;
         }
      }
      
      public int getX(){
         return (int)x;
      }
      
      public int getY(){
         return (int)y;
      }
   }
   
   class FallingObject{
      private int x;
      private double y;
      public FallingObject(int x){
         this.x = x;
         y = -20;
      }
      
      public int getX(){
         return x;
      }
      
      public int getY(){
         return (int) y;
      }
      public void fall(){
         y+=0.0001;
      }
      
   
   }
   
   public static void main(String[] args)
   {
      Minigame m = new Minigame();
      m.display();
      //m.display(player);
   }

}