
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
* Level1 class, teaching level of game
* Code completed by Patrick Bian
* <h2>Course Info:</h2>
* ICS4U0 with Krasteva, V.
*
* @version 08-06-2023
* @author BLD Studios
*/
public class Level1 extends JPanel implements Runnable{
   private Toolkit t = Toolkit.getDefaultToolkit();
   private int count = -1;
   private JButton left, right, exit;
   private boolean running = true, finished = false;

      
   /**
   * Class constructor, initializes buttons
   */   
   public Level1(){
      left = new JButton("\u2190");
      right = new JButton("\u2192");
      exit = new JButton("Next");
      
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
      
   /**
   * Called when Level1 thread is called, adds ActionListener to buttons, which allow for navigation between slides
   */   
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
               if (!finished){
                  left.removeActionListener(this);
                  right.removeActionListener(this);
                  
                  finished = true;
                  JOptionPane.showMessageDialog(Level1.this, "Congrats on finishing Level 1! Now you're ready for Level 2.");
                  repaint();
                  
                  left.setVisible(false);
                  right.setVisible(false);
               }else{
                  running = false;
                  Game.screenNum = 4;
                  exit.setVisible(false);
                  exit.removeActionListener(this);
               }
            }
         });
   }
   
   public boolean isRunning(){
      return running;
   }
   @Override
   /**
   * Displays learning slides for each plant used in the game
   */
      
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
      }else if (count == 18){
         right.setVisible(true);
         exit.setVisible(false);
      }else if (count == 19){
         right.setVisible(false);
         exit.setVisible(true);
      }
            
      if (count != -1 && count != 10){     
         Image image = Game.background1;
         g.drawImage(image, 0, 0, 1000, image.getHeight(this)*1000/image.getWidth(this), this); 
         g.drawString(Game.names[count], 500-Game.names[count].length()*12, 40);
         g.drawImage(Game.img[count], 400, 70, 200, Game.img[count].getHeight(this)*200/Game.img[count].getWidth(this), this); 
         
         g.setFont(new Font("MonoSpaced", Font.BOLD, 20));
         
         
         String[] words = Game.descriptions[count].split("\\s+");
         StringBuilder line = new StringBuilder(words[0]);
         int location = 300;
         for (int i = 1; i < words.length; i++) {
            if (line.length() + words[i].length() + 1 <= 60) {
               line.append(" ").append(words[i]);
            } else {
               g.drawString(line.toString(), 130, location);
               location += 30;
               line = new StringBuilder(words[i]);
            }
         }
         g.drawString(line.toString(), 130, location);
      }
      
      if (finished){
         g.setColor(new Color(0, 196, 255));
         g.fillRect(0, 0, 1000, 680);
         
         g.setColor(Color.black);
         g.setFont(new Font("Sans Serif",Font.BOLD, 40)); 
         g.drawString("Level 2", 450, 100);
         
         g.setFont(new Font("Sans Serif",Font.BOLD, 20)); 
         String[] words = Game.instructions[2].split("\\s+");
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
