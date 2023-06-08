import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Level1 extends JPanel implements Runnable{
   private Toolkit t = Toolkit.getDefaultToolkit();
   private int count = -1;
   private JButton left, right, exit;
   private boolean running = true;
   private int length = 0; 
   private int location = 375;
   private int counter = 0;

      
   public Level1(){
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
               Game.screenNum = 4;
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
      
      g.setFont(new Font("MonoSpaced", Font.BOLD, 40));   
         
      if (count == -1){
         left.setVisible(false);
         g.setColor(Color.green);
         g.fillRect(0, 0, 1000, 680);
         g.setColor(Color.black);
         g.drawString("Safe Plants", 400, 200);
      }else if (count == 0){
         left.setVisible(true);
      }else if (count == 8){
         right.setVisible(true);
         exit.setVisible(false);
      }else if (count == 10){
         g.setColor(Color.red);
         g.fillRect(0, 0, 1000, 680);
         g.setColor(Color.black);
         g.drawString("Dangerous Plants", 325, 200);
      }else if (count == 19){
         right.setVisible(false);
         exit.setVisible(true);
      }
            
      if (count != -1 && count != 10){     
         Image i = t.getImage("Images/Background 1.png");
         g.drawImage(i, 0, 0, 1000, i.getHeight(this)*1000/i.getWidth(this), this); 
         g.drawString(Game.names[count], 500-Game.names[count].length()*12, 40);
         g.drawImage(Game.img[count], 400, 70, 200, Game.img[count].getHeight(this)*200/Game.img[count].getWidth(this), this); 
         
         g.setFont(new Font("MonoSpaced", Font.BOLD, 20));
         
         
         length = Game.descriptions[count].length();
         counter = 0; 
         location = 375;
         
         while(length>60)
         {
          length -= 60;
          counter++; 
         }
         for(int j = 0; j <= counter; j++)
         {
          if(j!=counter)
          {
           g.drawString(Game.descriptions[count].substring(j*60,((j+1)*60+1)), 140, location);
           location += 20; 
          }
          else 
          {
            g.drawString(Game.descriptions[count].substring(j*60), 140, location);
          }
         }
      }
   }
   
   /*public static void main(String[] args){
      JFrame f = new JFrame();
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Level1 l1 = new Level1();
      Thread level = new Thread(l1);
      level.start();
      f.add(l1);
      
      f.setSize(1000, 680);
      f.setVisible(true);
   }*/
}
