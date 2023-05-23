public class FallingObject{
   
   //coordinates
   private int x,y;
   //type of object
   private boolean bad;
   //marked for deletion
   private boolean delete;
   
   //constructor, sets x coordinate
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
   
   //increases y, if object falls out of bounds mark for deletion
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
