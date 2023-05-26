import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Instructions extends JPanel implements ActionListener{
   private int count = 0;
   private JButton left, right, exit;
   private boolean run = true;
   
   public Instructions(){ 
      left = new JButton("\u2190");
      right = new JButton("\u2192");
      exit = new JButton("Exit");
      
      this.setLayout(null);
      
      left.setFont(new Font("Sans Serif", Font.BOLD, 30));
      left.setBackground(Color.LIGHT_GRAY);
      left.setBounds(10, 300, 100, 60);
      left.addActionListener(this);
      add(left);
   
      right.setFont(new Font("Sans Serif", Font.BOLD, 30));
      right.setBackground(Color.LIGHT_GRAY);
      right.setBounds(875, 300, 100, 60);
      right.addActionListener(this);
      add(right);
   
      exit.setFont(new Font("Sans Serif", Font.BOLD, 30));
      exit.setBackground(Color.LIGHT_GRAY);
      exit.setBounds(875, 550, 100, 60);
      exit.addActionListener(this);
      add(exit);
      exit.setVisible(false);
   }

   public boolean exitClicked(){
      return run;
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
         left.setVisible(false);
      }else if (count == 1){
         g.drawString("Level 1", 450, 50);
         left.setVisible(true);
      }else if (count == 2){
         g.drawString("Level 2", 450, 50);
         right.setVisible(true);
         exit.setVisible(false);
      }else{            
         g.drawString("Level 3", 450, 50);
         right.setVisible(false);
         exit.setVisible(true);
      } 
      
   }
   
   public void actionPerformed(ActionEvent e){
      if (e.getSource() == left){
         count--;
         repaint();
      }
      
      if (e.getSource() == right){
         count++;
         repaint();
      }
      
      if (e.getSource() == exit){
         run = false;
      }
   }
}