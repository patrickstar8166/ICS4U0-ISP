import java.lang.*;


public class Driver extends Thread{
   
   public static void main(String[] args){
      try{
         //Minigame m = new Minigame();
         Level2 l = new Level2();
         Thread t = new Thread(l);
         t.start();
      }
      catch(Exception e){
      }
      
      
   }


}