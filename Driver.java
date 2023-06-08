import java.lang.*;


public class Driver extends Thread{
   
   public static void main(String[] args){
      try{
         Minigame2 m = new Minigame2();
         //Level2 l = new Level2();
         //Level3 l = new Level3();
         Thread t = new Thread(m);
         t.start();
      }
      catch(Exception e){
      }
      
      
   }


}