import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Instructions implements ActionListener{
   public JInternalFrame frame;
   public Drawing draw; 
   public int count = 0;
   public JButton left, right, exit;
   public boolean run = false;
   
   public Instructions(){ 
      frame = new JInternalFrame();
      draw = new Drawing();
      left = new JButton("\u2190");
      right = new JButton("\u2192");
      exit = new JButton("Exit");
   }

   public JInternalFrame display(){
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000, 680);
      
      left.setFont(new Font("Sans Serif", Font.BOLD, 30));
      left.setBackground(Color.LIGHT_GRAY);
      left.setBounds(10, 300, 100, 60);
      left.addActionListener(this);
      frame.add(left);
   
      right.setFont(new Font("Sans Serif", Font.BOLD, 30));
      right.setBackground(Color.LIGHT_GRAY);
      right.setBounds(875, 300, 100, 60);
      right.addActionListener(this);
      frame.add(right);
   
      exit.setFont(new Font("Sans Serif", Font.BOLD, 30));
      exit.setBackground(Color.LIGHT_GRAY);
      exit.setBounds(875, 550, 100, 60);
      exit.addActionListener(this);
      frame.add(exit);
      exit.setVisible(false);
         
      frame.add(draw);
      frame.setVisible(true);
      
      return frame;
   }
   
   public boolean exitClicked(){
      return run;
   }
        
   class Drawing extends JComponent{
      public void paint(Graphics g){
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
            left.setVisible(true);
            right.setVisible(true);
            exit.setVisible(false);
         }else{            
            g.drawString("Level 3", 450, 50);
            right.setVisible(false);
            exit.setVisible(true);
         }  
      }
   }
   
   public void actionPerformed(ActionEvent e){
      if (e.getSource() == left){
         count--;
         draw.repaint();
      }
      
      if (e.getSource() == right){
         count++;
         draw.repaint();
      }
      
      if (e.getSource() == exit){
         left.removeActionListener(this);
         right.removeActionListener(this);
         run = true;
      }
   }
}