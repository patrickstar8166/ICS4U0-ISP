
/**
* Falling Object class, used in Minigame
* <h2>Course Info:</h2>
* ICS4U0 with Krasteva, V.
*
* @version 08-06-2023
* @author BLD Studios
*/

public class FallingObject{
   
   //coordinates
   private int x,y;
   //type of object
   private boolean bad;
   //marked for deletion
   private boolean delete;
   
   /**
   * class constructor
   * @param x The x coordinate of the falling object
   */
   public FallingObject(int x){
      this.x = x;
      y = -20;
      bad = (Math.random()<0.66667);
   }
   
   //get methods
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
   
   /**
   * Increase y of object, if object falls out of bounds mark for deletion
   */
   public void fall(){
      y+=8;
      if(y>680){
         delete = true;
      }  
   }
   
   //marks for deletion
   public void setDelete(){
      delete = true;
   }
   

}
