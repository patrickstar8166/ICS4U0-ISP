import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Menu extends JFrame implements ActionListener{
   JButton start, instr;
   JLayeredPane pane;
   Drawing draw; 
   
   public Menu(){ 
      start = new JButton("Start");
      instr = new JButton("Instructions");
      pane = getLayeredPane();
      draw = new Drawing();
   }

   public void display(){
      //frame
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(1000, 680);
      
      start.setBackground(Color.LIGHT_GRAY);
      start.setBounds(450, 300, 100, 40);
      pane.add(start, 1);
      
      instr.setBackground(Color.LIGHT_GRAY);
      instr.setBounds(425, 375, 150, 40);
      pane.add(instr, 1);
      
      this.add(draw);
      this.setVisible(true);
   }
      
   class Drawing extends JComponent{
      public void paint(Graphics g){
         //background
         g.setColor(new Color(173, 216, 230));
         g.fillRect(0, 0, 1000, 680);
         
         //text
         g.setColor(Color.black);
         g.setFont(new Font("Sans Serif", Font.BOLD, 40));
         g.drawString("Fresh Forage Adventure", 275, 200);
      }
   }
      
   public void actionPerformed (ActionEvent e){
   }
}