public class Main{
   public static void main(String[] args){
      Game g = new Game();
      Thread t = new Thread(g);
      t.start();
   }
}
