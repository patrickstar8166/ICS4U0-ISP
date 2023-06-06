import java.lang.*;


public class Driver extends Thread{
   
   public static void main(String[] args){
      try{
         Minigame m = new Minigame();
         //Level2 l = new Level2();
         //Level2Backup lB = new Level2Backup();
         Thread t = new Thread(m);
         t.start();
      }
      catch(Exception e){
      }
      
      
   }


}