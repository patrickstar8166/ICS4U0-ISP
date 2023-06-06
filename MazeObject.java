import java.awt.*;    
import java.awt.event.*;  
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


public class MazeObject{
   private int x, y, w, h;
   private boolean poison;
   private Image img;
   private String name;
<<<<<<< HEAD
=======
   
   public MazeObject(int x, int y, int w, int h, String img, boolean poison) throws IOException{
      this.x = x;
      this.y = y;
      this.w = w;
      this.poison = poison;
      BufferedImage tempImg = ImageIO.read(new File(img));
      this.img = tempImg.getScaledInstance(w,h,Image.SCALE_SMOOTH);
   }
>>>>>>> ba0da2f5444a169c365ab895af866a4d5be5fcea
   
   public MazeObject(int x, int y, int w, int h, String img) throws IOException{
      this.x = x;
      this.y = y;
      this.w = w;
<<<<<<< HEAD
      poison = false;
=======
>>>>>>> ba0da2f5444a169c365ab895af866a4d5be5fcea
      BufferedImage tempImg = ImageIO.read(new File(img));
      this.img = tempImg.getScaledInstance(w,h,Image.SCALE_SMOOTH);
   }
   
<<<<<<< HEAD
   public MazeObject(int x, int y, int w, int h, String img, String name, boolean poison) throws IOException{
=======
   public MazeObject(int x, int y, int w, int h, String img, String name) throws IOException{
>>>>>>> ba0da2f5444a169c365ab895af866a4d5be5fcea
      this.x = x;
      this.y = y;
      this.w = w;
      this.name = name;
<<<<<<< HEAD
      this.poison = poison;
=======
>>>>>>> ba0da2f5444a169c365ab895af866a4d5be5fcea
      BufferedImage tempImg = ImageIO.read(new File(img));
      this.img = tempImg.getScaledInstance(w,h,Image.SCALE_SMOOTH);
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
   
   public boolean getPoison(){
      return poison;
   }
   
}

