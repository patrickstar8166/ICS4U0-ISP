
import java.awt.*;    
import java.awt.event.*;  
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
/**
* MazeObject class, used in Level2, Level3, Minigame, to faciliate transfer of several types of information, like position, name, poisonous, etc
* Code Completed by Nia Decaire
* <h2>Course Info:</h2>
* ICS4U0 with Krasteva, V.
*
* @version 08-06-2023
* @author BLD Studios
*/
public class MazeObject{
   private int x, y, w, h;
   private boolean poison;
   private Image img, bigImg;
   private String name;
   
   
   /**
   * Class constructor, used for walls in level 3
   * @param x The x position of the object
   * @param y The y position of the object
   * @param w The width of the object
   * @param h The height of the object
   * @param img The image used for the object
   */
   public MazeObject(int x, int y, int w, int h, Image img) throws IOException{
      this.x = x;
      this.y = y;
      this.w = w;
      poison = false;
      this.img = img;
   }
   
   /**
   * Class constructor, used for interactable and collected objects in level 3
   * @param x The x position of the object
   * @param y The y position of the object
   * @param w The width of the object
   * @param h The height of the object
   * @param img The image of the plant the object represents
   * @param name The name of the plant the object represents
   * @param poison Whether or not the plant the object represents is poisonous
   */
   
   public MazeObject(int x, int y, int w, int h, Image img, String name, boolean poison) throws IOException{
      this.x = x;
      this.y = y;
      this.w = w;
      this.name = name;
      this.poison = poison;
      this.img = img;
   }
   public int getX(){
      return x;  
   }
   
   public int getY(){
      return y;
   }
   public int getW(){
      return w;
   }
   
   public int getH(){
      return h;
   }
   public String getName(){
      return name;
   }
   
   public Image getImg(){
      return img;
   }
   
   public Image getBigImg(){
      return bigImg;
   }
   
   public boolean getPoison(){
      return poison;
   }
   
   public void setX(int x){
      this.x = x; 
   }
   
   public void setY(int y){
      this.y = y;
   }
   
}

