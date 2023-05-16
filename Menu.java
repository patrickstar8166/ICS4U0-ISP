import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Menu implements ActionListener{
   JButton start, instr;
   JFrame frame;
   JPanel panel1, panel2;
   Drawing draw;
   
   public Menu(){ 
      start = new JButton("Start");
      instr = new JButton("Instructions");
      frame = new JFrame("Menu");
      panel1 = new JPanel();
      panel2 = new JPanel();
      draw = new Drawing();
   }

   public void display(){
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000, 680);
      
      panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
      panel1.setPreferredSize(1000, 400);
      start.setAlignmentX(Component.CENTER_ALIGNMENT);
      start.setPreferredSize(new Dimension(100, 40));
      panel1.add(start);
      panel1.add(Box.createRigidArea(new Dimension(0, 20)));
      instr.setAlignmentX(Component.CENTER_ALIGNMENT);
      instr.setPreferredSize(new Dimension(100, 1000));
      panel1.add(instr);  
      
      panel2.setLayout(new GridLayout(2, 1));
      panel2.add(draw);
      panel2.add(panel1);
      
      frame.add(panel2, BorderLayout.CENTER);
      frame.setVisible(true);
   }
      
   class Drawing extends JComponent{
      public void paint(Graphics g){
         g.setColor(Color.black);
         g.setFont(new Font("Sans Serif", Font.BOLD, 40));
         g.drawString("Fresh Forage Adventure", 275, 200);
      }
   }
   
   public void actionPerformed (ActionEvent e){
   }
}