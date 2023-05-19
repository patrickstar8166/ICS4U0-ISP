import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Instructions implements ActionListener{
   public JFrame frame;
   public Drawing draw; 
   public int count = 0;
   public JButton left, right, exit;
   public boolean run = true;
   
   public Instructions(){ 
      frame = new JFrame();
      draw = new Drawing();
      left = new JButton("\u2190");
      right = new JButton("\u2192");
      exit = new JButton("Exit");
   }

   public void display(){
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000, 680);
      
      //while (run){
         // frame.getContentPane().removeAll();
         if (count != 0){
            left.setFont(new Font("Sans Serif", Font.BOLD, 30));
            left.setBackground(Color.LIGHT_GRAY);
            left.setBounds(10, 300, 100, 60);
            left.addActionListener(
               new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                     count--;
                     System.out.println(count);
                     draw.repaint();
                  }
               });
            left.removeActionListener(this);   
            frame.add(left);
         }  
         
         if (count != 3){
            right.setFont(new Font("Sans Serif", Font.BOLD, 30));
            right.setBackground(Color.LIGHT_GRAY);
            right.setBounds(875, 300, 100, 60);
            right.addActionListener(
               new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                     count++;
                     System.out.println(count);
                     draw.repaint();
                  }
               });
            right.removeActionListener(this);           
            frame.add(right);
         }   
         
         if (count == 3){
            exit.setFont(new Font("Sans Serif", Font.BOLD, 30));
            exit.setBackground(Color.LIGHT_GRAY);
            exit.setBounds(875, 300, 100, 60);
            exit.addActionListener(
               new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                     run = false;
                  }
               });
            frame.add(exit);
         }
         
         frame.add(draw);
         frame.setVisible(true);
      //}
      
      //return frame;*/
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
         }else{            
            g.drawString("Level 3", 450, 50);
            right.setVisible(false);
         }  
      }
   }
   
   public void actionPerformed(ActionEvent e){}
   
   public static void main(String[] args){
      Instructions i = new Instructions();
      i.display();
   }
}