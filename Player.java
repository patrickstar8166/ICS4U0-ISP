
/**
* Player class, used in Minigame for hit detection, movement, etc
* <h2>Course Info:</h2>
* ICS4U0 with Krasteva, V.
*
* @version 08-06-2023
* @author BLD Studios
*/

public class Player{
   
   //coordinate position
   private int x,y;
   //number of good objects caught
   private int caught;
   //player health
   private int health;
   
   /**
   * constructor, intializes starting values
   */
   public Player(){
      x = 490;
      y = 420;
      caught = 0;
      health = 10;
   }
   
   /**
   * Updates position of player
   * @param right Whether right is held
   * @param left Whether left is held
   */
   
   public void move(boolean right, boolean left){ //movement method, updates x coordinate
      
      //updates horizontal position based on value fed
      if(left){
         x-=18;
      }
      else if(right){
         x+=18;
      }
      
      //keeps player within bounds      
      if(x<50){
         x = 50;
      }
      else if(x>900){
         x = 900;
      }
   }
   
   /**
   * checks if player hitbox is intersecting with object
   */
   public void hitDetect(FallingObject f){
      
      //coordinates intersect
      if(f.getX() >= x-75 && f.getX() <= x+110 && f.getY() >= y-75 && f.getY() <= y){
         //bad object, decrement health
         if(f.getBad()){
            health --;
            //System.out.println("Health: "+health);
         }
         //good object, increments progress
         else{
            caught++;
            //System.out.println("caught: " +caught);
         }
         //marks for deletion
         f.setDelete();
      }
      
   }
   //get methods
   public int getX(){
      return x;
   }
   
   public int getY(){
      return y;
   }
   
   public int getHealth(){
      return health;
   }
   
   public int getCaught(){
      return caught;
   }
   
}
