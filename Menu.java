import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Menu implements ActionListener{
   public JInternalFrame frame;
   public JButton start, instr;
   public Drawing draw;
   
   public Menu(){ 
      frame = new JInternalFrame("Menu");
      start = new JButton("Start");
      instr = new JButton("Instructions");
      draw = new Drawing();
   }

   public JInternalFrame display(){
      //frame
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000, 680);
      
      start.setBackground(Color.LIGHT_GRAY);
      start.setBounds(450, 300, 100, 40);
      start.addActionListener(this);
      frame.add(start);
      
      instr.setBackground(Color.LIGHT_GRAY);
      instr.setBounds(425, 375, 150, 40);
      instr.addActionListener(this);
      frame.add(instr);
      
      frame.add(draw);
      frame.setVisible(true);
      
      return frame;
   }
      
   class Drawing extends JComponent{
      public void paint(Graphics g){
         //background
         g.setColor(new Color(173, 216, 230));
         g.fillRect(0, 0, 1000, 680);
         //ground
         g.setColor(new Color(124, 252, 0));
         g.fillRect(0, 500, 1000, 180);
         //tree1
         g.setColor(new Color(66, 105, 47));
         int[] tree1x = {150, 75, 225};
         int[] tree1y = {250, 325, 325};
         g.fillPolygon(tree1x, tree1y, 3);
         int[] tree2x = {150, 50, 250};
         int[] tree2y = {300, 400, 400};
         g.fillPolygon(tree2x, tree2y, 3);
         //tree2
         for (int i = 0; i < 3; i++){
            tree1x[i] += 650;
            tree2x[i] += 650;
         }
         g.fillPolygon(tree1x, tree1y, 3);
         g.fillPolygon(tree2x, tree2y, 3);
         //trunk
         g.setColor(new Color(118, 92, 72));
         g.fillRect(125, 400, 50, 100);
         g.fillRect(775, 400, 50, 100);
         
         //text
         g.setColor(Color.black);
         g.setFont(new Font("Sans Serif", Font.BOLD, 40));
         g.drawString("Fresh Forage Adventure" , 275, 200);
      }
   }
      
   public void actionPerformed (ActionEvent e){
      if (e.getSource() == start){
         Game.screenNum++;
      }
      
      if (e.getSource() == instr){
         ////Instructions i = new Instructions();
         //i.display();
      }
   }
}
