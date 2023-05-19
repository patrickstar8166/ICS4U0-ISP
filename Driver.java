
public class Driver extends Thread{
   
   public static void main(String[] args){
      Minigame m = new Minigame();
      Thread t = new Thread(m);
      t.start();
      while(!m.getEnd()){
         
         try{
            Thread.sleep(1000);
            
         }
         catch(Exception e){
         
         }
      }
   }


}