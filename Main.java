/**
* Main class
* <h2>Course Info:</h2>
* ICS4U0 with Krasteva, V.
*
* @version 08-06-2023
* @author BLD Studios
*/

public class Main{
   /**
   * Main method, runs game thread
   */
   public static void main(String[] args){
      Game g = new Game();
      Thread t = new Thread(g);
      t.start();
   }
}
