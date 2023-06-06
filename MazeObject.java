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
   
   public MazeObject(int x, int y, int w, int h, String img, boolean poison) throws IOException{
      this.x = x;
      this.y = y;
      this.w = w;
      this.poison = poison;
      BufferedImage tempImg = ImageIO.read(new File(img));
      this.img = tempImg.getScaledInstance(w,h,Image.SCALE_SMOOTH);
   }
   
   public MazeObject(int x, int y, int w, int h, String img) throws IOException{
      this.x = x;
      this.y = y;
      this.w = w;
      BufferedImage tempImg = ImageIO.read(new File(img));
      this.img = tempImg.getScaledInstance(w,h,Image.SCALE_SMOOTH);
   }
   
   public MazeObject(int x, int y, int w, int h, String img, String name) throws IOException{
      this.x = x;
      this.y = y;
      this.w = w;
      this.name = name;
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

