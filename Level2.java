
import java.awt.*;    
import java.awt.event.*;  
import javax.swing.*;
import java.util.*;
/**
* Level2 class, Maze level, tests player's knowledge
* Code completed by Patrick Bian with graphics and sprites done by Jonathan Liu
* <h2>Course Info:</h2>
* ICS4U0 with Krasteva, V.
*
* @version 08-06-2023
* @author BLD Studios
*/
public class Level2 extends JPanel implements Runnable{
   private int[][] maze = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},  //19x11
                           {1, 1, 1, 1, 2, 1, 1, 1, 0, 1, 2, 1, 1, 1, 0, 1, 1, 2, 0},
                           {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0},
                           {0, 2, 1, 1, 0, 2, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0},
                           {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                           {0, 1, 0, 1, 0, 1, 1, 1, 2, 1, 1, 1, 0, 2, 1, 1, 0, 1, 0},
                           {0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                           {0, 1, 0, 1, 1, 1, 0, 1, 2, 1, 0, 1, 1, 1, 1, 2, 1, 1, 0},
                           {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                           {0, 1, 1, 1, 2, 1, 1, 1, 0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1},
                           {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
   private ArrayList<Integer> pic = new ArrayList<Integer>();
   private ArrayList<Integer> img = new ArrayList<Integer>();
   private int x = 1, y = 0, count = -1;
   private boolean running = true, move = true, start = false, good = true, finished = false;
   private JButton yes, no, next;
   private Image draw = Game.sprite2;
   private Toolkit t = Toolkit.getDefaultToolkit();
   
   
   /**
   * Class constructor, initializes buttons and chooses a random set of plants to use
   */
   public Level2() {
      yes = new JButton("\u2714");
      no = new JButton("\u03A7");
      next = new JButton("Next");
      
      this.setLayout(null);
   
      yes.setFont(new Font("Sans Serif", Font.BOLD, 20));
      yes.setBackground(Color.LIGHT_GRAY);
      yes.setBounds(415, 550, 75, 75);
   
      no.setFont(new Font("Sans Serif", Font.BOLD, 20));
      no.setBackground(Color.LIGHT_GRAY);
      no.setBounds(510, 550, 75, 75);
      
      next.setFont(new Font("Sans Serif", Font.BOLD, 30));
      next.setBackground(Color.LIGHT_GRAY);
      next.setBounds(875, 550, 100, 60);
   
      this.add(next);
      this.add(yes);
      this.add(no);
      next.setVisible(false);
      yes.setVisible(false);
      no.setVisible(false);
      
      ArrayList<Integer> temp = new ArrayList<Integer>();
      for (int i = 0; i < 20; i++){
         temp.add(i);
      }
      
      Collections.shuffle(temp);
      
      for (int i = 0; i < 13; i++){
         img.add(temp.get(i));
      }
   }
   
   /**
   * Called when Level2 thread is run, adds KeyListener to handle player movement, ActionListener to buttons, and handles object collission + maze exit
   */
   
   public void run(){
      this.addKeyListener(
         new KeyListener(){
            public void keyPressed(KeyEvent e) {
               if (move){
                  if(e.getKeyCode() == e.VK_RIGHT && maze[x][y+1] != 0 && y+1 < 19){
                     y++;
                     if (draw == Game.sprite3 || draw == Game.sprite4) draw = Game.sprite1;
                  }
                  
                  if(e.getKeyCode() == e.VK_LEFT && maze[x][y-1] != 0 && y-1 > 0){
                     y--;
                     if (draw == Game.sprite2 || draw == Game.sprite1) draw = Game.sprite3;
                  }
                  
                  if(e.getKeyCode() == e.VK_DOWN && maze[x+1][y] != 0 && x+1 < 11){
                     x++;
                  }
                  
                  if(e.getKeyCode() == e.VK_UP && maze[x-1][y] != 0 && x-1 > 0){
                     x--;
                  }
               }
               
               repaint();
               
               if (y == 18 && x == 9){
                  finished = true;
                  removeKeyListener(this);
                  next.setVisible(true);
                  repaint();                  
                  JOptionPane.showMessageDialog(Level2.this, "Congrats on finishing Level 2! Now you're ready for Level 3.");
               }
               
               for (int i = 0; i < pic.size()-1; i += 2){
                  if (x == pic.get(i) && y == pic.get(i+1)){
                     move = false;
                     maze[x][y] = 1;
                     pic.remove(i);
                     pic.remove(i);
                     start = true;
                  }
               }
            }

            public void keyReleased(KeyEvent e) { }

            public void keyTyped(KeyEvent e) { }
         });
         
      yes.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent e){
               if (!move){
                  move = true;
                  yes.setVisible(false);
                  no.setVisible(false);
                  repaint();
               }
               
               if (good){
                  JOptionPane.showMessageDialog(Level2.this, "Correct!"); 
               }else{
                  JOptionPane.showMessageDialog(Level2.this, "Incorrect. This is not edible."); 
               }
            }
         });
   
      no.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent e){
               if (!move){
                  move = true;
                  yes.setVisible(false);
                  no.setVisible(false);
                  repaint();
               }
               
               if (!good){
                  JOptionPane.showMessageDialog(Level2.this, "Correct!"); 
               }else{
                  JOptionPane.showMessageDialog(Level2.this, "Incorrect. This is edible."); 
               }
            }
         });
         
      next.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent e){
               running = false;
               Game.screenNum = 5;
               next.removeActionListener(this);
               next.setVisible(false);
            }
         });
   }
   
   public boolean isRunning(){
      return running;
   }
   @Override
   /**
   * Draws maze and question prompts
   */
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      g.drawImage(Game.background2,0, 10, 1000, 680, this);
      if (move){
         for (int i = 0; i < 11; i++){
            for (int j = 0; j < 19; j++){
               if (maze[i][j] == 0){
                  g.setColor(Color.black);
                  g.drawImage(Game.bush,j*50+25,i*50+50,50,50,this);
               }
               
               if (maze[i][j] == 2){
                  g.setColor(Color.red);
                  g.fillRect(j*50+35, i*50+60, 30, 30);
                  pic.add(i); pic.add(j);
               }
            }
         }
         
         g.drawImage(draw, y*50+25, x*50+50, 50, 50, this);
         if (draw == Game.sprite1) draw = Game.sprite2;
         else if (draw == Game.sprite2) draw = Game.sprite1;
         else if (draw == Game.sprite3) draw = Game.sprite4; 
         else draw = Game.sprite3;
      }else{
         if (start && count < 12){
            count++;
            start = false;
         }
      
         g.setColor(Color.green);
         g.fillRect(0, 0, 1000, 680);
         yes.setVisible(true);
         no.setVisible(true);
         Image image = t.getImage(Game.images[img.get(count)]);
         g.drawImage(image, 400, 150, 200, image.getHeight(this)*200/image.getWidth(this), this);
         
         g.setColor(Color.black);
         g.setFont(new Font("MonoSpaced", Font.BOLD, 40));
         g.drawString(Game.names[img.get(count)], 500-Game.names[img.get(count)].length()*12, 400);
         g.drawString("Is this edible?", 340, 100);
         
         if (img.get(count) < 10) good = true;
         else good = false;
      }
      
      if (finished){
         g.setColor(new Color(0, 196, 255));
         g.fillRect(0, 0, 1000, 680);
         
         g.setColor(Color.black);
         g.setFont(new Font("Sans Serif",Font.BOLD, 40)); 
         g.drawString("Level 3", 450, 100);
         
         g.setFont(new Font("Sans Serif",Font.BOLD, 20)); 
         String[] words = Game.instructions[3].split("\\s+");
         StringBuilder line = new StringBuilder(words[0]);
         int location = 150;
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