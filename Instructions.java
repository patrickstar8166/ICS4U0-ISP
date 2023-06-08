import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Instructions extends JPanel implements Runnable{
   private int count = 0;
   private JButton left, right, exit;
   private boolean running = true;
   private int counter = 0;
   private int length = 0;
   private int location = 180; 

   public Instructions(){ 
      left = new JButton("\u2190");
      right = new JButton("\u2192");
      exit = new JButton("Exit");
   
      this.setLayout(null);
   
      left.setFont(new Font("Sans Serif", Font.BOLD, 30));
      left.setBackground(Color.LIGHT_GRAY);
      left.setBounds(10, 300, 100, 60);
   
      right.setFont(new Font("Sans Serif", Font.BOLD, 30));
      right.setBackground(Color.LIGHT_GRAY);
      right.setBounds(875, 300, 100, 60);
   
      exit.setFont(new Font("Sans Serif", Font.BOLD, 30));
      exit.setBackground(Color.LIGHT_GRAY);
      exit.setBounds(875, 550, 100, 60);
   
      this.add(left);
      this.add(right);
      this.add(exit);
      left.setVisible(true);
      right.setVisible(true);
      exit.setVisible(false);
   }

   public void run(){
      left.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent e){
               count--;
               repaint();
            }
         });
   
      right.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent e){
               count++;
               repaint();
            }
         });
   
      exit.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent e){
               running = false;
               Game.screenNum = 1;
               left.removeActionListener(this);
               right.removeActionListener(this);
               exit.removeActionListener(this);
            }
         });
   }

   public boolean isRunning(){
      return running;
   }
  
   public void paintComponent(Graphics g){
      super.paintComponent(g);    
   
   //background
      g.setColor(new Color(0, 196, 255));
      g.fillRect(0, 0, 1000, 680);
   
   //text
      g.setColor(Color.black);
      g.setFont(new Font("Sans Serif", Font.BOLD, 40));
   
      if (count == 0){   
         g.drawString("About the Game", 350, 50);
         g.setFont(new Font("Sans Serif",Font.BOLD, 20)); 
         g.drawString(" Welcome to “Fresh Foraging Adventure! ", 300,120);      
         length = Game.instructions[count].length();
         counter = 0; 
         location = 180;     
         while(length>60)
         {
          length -= 60;
          counter++; 
         }
         for(int j = 0; j <= counter; j++)
         {
          if(j!=counter)
          {
           if(Game.instructions[count].substring((j+1)*60, ((j+1) * 60 + 1 )).equals(" "))
           {
             g.drawString(Game.instructions[count].substring(j*60,(j+1)*60), 200, location);
             location += 30; 
           }
           else
           {
            g.drawString(Game.instructions[count].substring(j*60,(j+1)*60) + "-", 200, location);
            location += 30;
           }
          } 
          else 
          {
            g.drawString(Game.instructions[count].substring(j*60), 200, location);
          }
         }
         left.setVisible(false);
          g.setFont(new Font("Sans Serif", Font.BOLD, 40));
       
           }else if (count == 1){
         g.drawString("Level 1", 450, 50);
         g.setFont(new Font("Sans Serif",Font.BOLD, 20)); 
         g.drawString(" Welcome to the first level! ", 360,120);
         length = Game.instructions[count].length();
         counter = 0; 
         location = 180;     
         while(length>60)
         {
          length -= 60;
          counter++; 
         }
         for(int j = 0; j <= counter; j++)
         {
          if(j!=counter)
          {
           if(Game.instructions[count].substring((j+1)*60, ((j+1) * 60 + 1 )).equals(" "))
           {
             g.drawString(Game.instructions[count].substring(j*60,(j+1)*60), 200, location);
             location += 30; 
           }
           else
           {
            g.drawString(Game.instructions[count].substring(j*60,(j+1)*60) + "-", 200, location);
            location += 30;
           }
          } 
          else 
          {
            g.drawString(Game.instructions[count].substring(j*60), 200, location);
          }
         }
         left.setVisible(true);
      }else if (count == 2){
         g.drawString("Level 2", 450, 50);
         g.setFont(new Font("Sans Serif",Font.BOLD, 20)); 
         g.drawString(" Welcome to the second level! ", 360,120);
         length = Game.instructions[count].length();
         counter = 0; 
         location = 180;     
         while(length>60)
         {
          length -= 60;
          counter++; 
         }
         for(int j = 0; j <= counter; j++)
         {
          if(j!=counter)
          {
           if(Game.instructions[count].substring((j+1)*60, ((j+1) * 60 + 1 )).equals(" "))
           {
             g.drawString(Game.instructions[count].substring(j*60,(j+1)*60), 200, location);
             location += 30; 
           }
           else
           {
            g.drawString(Game.instructions[count].substring(j*60,(j+1)*60) + "-", 200, location);
            location += 30;
           }
          } 
          else 
          {
            g.drawString(Game.instructions[count].substring(j*60), 200, location);
          }
         }

         right.setVisible(true);
         exit.setVisible(false);
      }else{            
         g.drawString("Level 3", 450, 50);
         g.setFont(new Font("Sans Serif",Font.BOLD, 20)); 
         g.drawString(" Welcome to the third level! ", 360,120);
         length = Game.instructions[count].length();
         counter = 0; 
         location = 160;     
         while(length>60)
         {
          length -= 60;
          counter++; 
         }
         for(int j = 0; j <= counter; j++)
         {
          if(j!=counter)
          {
           if(Game.instructions[count].substring((j+1)*60, ((j+1) * 60 + 1 )).equals(" "))
           {
             g.drawString(Game.instructions[count].substring(j*60,(j+1)*60), 200, location);
             location += 30; 
           }
           else
           {
            g.drawString(Game.instructions[count].substring(j*60,(j+1)*60) + "-", 200, location);
            location += 30;
           }
          } 
          else 
          {
            g.drawString(Game.instructions[count].substring(j*60), 200, location);
          }
         }


         right.setVisible(false);
         exit.setVisible(true);
      }    
   }
}