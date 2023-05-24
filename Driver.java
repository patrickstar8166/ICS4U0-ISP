import java.lang.*;


public class Driver extends Thread{
   
   public static void main(String[] args){
      try{
         Minigame m = new Minigame();
         Thread t = new Thread(m);
         t.start();
      }
      catch(Exception e){
      }
      
      
   }


}