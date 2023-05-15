import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Menu implements ActionListener{
   JButton start;
   JButton instructions;
   JFrame f;
   JPanel p;
   
   public Menu(){
      start = new JButton("Start");
      instructions = new JButton("Instructions");
      f = new JFrame();
      p = new JPanel();
   }

   public void display(){
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setSize(1000, 680);
      start.addActionListener(this);
      instructions.addActionListener(this);
      p.add(new Drawing());
      p.add(start);
      p.add(instructions);
      f.add(p,BorderLayout.CENTER);
      f.setVisible(true);
   }
   
   class Drawing extends JComponent{
      public void paint(Graphics g){
         g.setColor(Color.black);
         g.setFont(new Font("Sans Serif", Font.BOLD, 40));
         g.drawString("Fresh Forage Adventure",275,200);
      }
   }
   
   public void actionPerformed (ActionEvent e){
   }
}