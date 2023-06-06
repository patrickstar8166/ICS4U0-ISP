import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Level1 extends JPanel implements Runnable{
   private Image[] img = new Image[10];
   private Toolkit t = Toolkit.getDefaultToolkit();
   private int count = -1;
   private JButton left, right, exit;
   private boolean running = true;
      
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
      
      for (int i = 0; i < 10; i++){
         img[i] = t.getImage(Game.images[i]); 
      }
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
         
         right.setVisible(false);
         exit.setVisible(true);
      }
            
      if (count != -1 && count != 10){     
         Image i = t.getImage("Images/Background 1.png");
         g.drawImage(i, 0, 0, 1000, i.getHeight(this)*1000/i.getWidth(this), this); 
         g.drawString(Game.names[count], 500-Game.names[count].length()*12, 40);
         g.drawImage(img[count], 400, 70, 200, img[count].getHeight(this)*200/img[count].getWidth(this), this); 
         
         g.setFont(new Font("MonoSpaced", Font.BOLD, 20));
         g.drawString(Game.descriptions[count], 150, 275);
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
