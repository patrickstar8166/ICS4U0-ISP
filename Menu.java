
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
* Menu class, main menu of game
* Code completed by Patrick Bian
* 
* <h2>Course Info:</h2>
* ICS4U0 with Krasteva, V.
*
* @version 08-06-2023
* @author BLD Studios
*/
public class Menu extends JPanel implements Runnable {
   private JButton start, instr;
   private boolean running = true, finished = false;
   private JButton next;
   
   
   /**
   * Class constructor, initalizes buttons
   */
   public Menu() {
      start = new JButton("Start");
      instr = new JButton("Instructions");
      next = new JButton("Next");
   
      this.setLayout(null);
   
      start.setBackground(Color.LIGHT_GRAY);
      start.setBounds(450, 300, 100, 40);
   
      instr.setBackground(Color.LIGHT_GRAY);
      instr.setBounds(425, 375, 150, 40);
      
      next.setBackground(Color.LIGHT_GRAY);
      next.setBounds(875, 550, 100, 60);
   
      // Add buttons to the panel
      this.add(start);
      this.add(instr);
      this.add(next);
      next.setVisible(false);
   }
   
   /**
   * Called when Menu thread is started, adds ActionListener to buttons
   */
   
   public void run() {
      start.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               start.removeActionListener(this);
               finished = true;
               next.setVisible(true);
               start.setVisible(false);
               instr.setVisible(false);
               repaint();
            }
         });
   
      instr.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Game.screenNum = 3;
               running = false;
               instr.removeActionListener(this);
            }
         });
         
      next.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Game.screenNum = 2;
               running = false;
               next.removeActionListener(this);
            }
         });   
   }
   
   public boolean isRunning() {
      return running;
   }
   @Override
   /**
   * Displays visuals
   */   
   public void paintComponent(Graphics g){
      super.paintComponent(g); 
                
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
      g.drawString("Fresh Forage Adventure", 275, 200);
      
      if (finished){
         g.setColor(new Color(0, 196, 255));
         g.fillRect(0, 0, 1000, 680);
         
         g.setColor(Color.black);
         g.setFont(new Font("Sans Serif",Font.BOLD, 40)); 
         g.drawString("Level 1", 450, 100);
         
         g.setFont(new Font("Sans Serif",Font.BOLD, 20)); 
         String[] words = Game.instructions[1].split("\\s+");
         StringBuilder line = new StringBuilder(words[0]);
         int location = 200;
         for (int i = 1; i < words.length; i++) {
            if (line.length() + words[i].length() + 1 <= 60) {
               line.append(" ").append(words[i]);
            } else {
               g.drawString(line.toString(), 200, location);
               location += 30;
               line = new StringBuilder(words[i]);
            }
         }
         g.drawString(line.toString(), 200, location);
      }
   }
}
