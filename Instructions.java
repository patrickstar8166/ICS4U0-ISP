import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Instructions extends JPanel implements Runnable{
   private int count = 0;
   private JButton left, right, exit;
   private boolean running = true;

   public Instructions(){ 
      left = new JButton("\u2190");
      right = new JButton("\u2192");
      exit = new JButton("Exit");
   
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
               running = false;
               Game.screenNum = 1;
               left.removeActionListener(this);
               right.removeActionListener(this);
               exit.removeActionListener(this);
            }
         });
   }

   public boolean isRunning(){
      return running;
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
         g.setFont(new Font("Sans Serif",Font.BOLD, 20)); 
         g.drawString(" Welcome to “Fresh Foraging Adventure! ", 300,120);
         g.drawString("Within this game, you’ll be exposed to three different levels of increas-",85,180);
         g.drawString("-ing skill and difficulty. To navigate please follow the instructions. To",85,210);
         g.drawString("navigate on this page, please use the arrow buttons by right-clicking ",85,240);
         g.drawString("with a mouse/trackpad! This single-player game is intended to teach kids",85, 270);
         g.drawString("aged 5-10 about foraged plants in a fun and engaging way. As the game", 85, 300);
         g.drawString("progresses through the three levels the difficulty increases. As well all ", 85, 330);
         g.drawString("levels must be played in sequential order, where the previous level " ,85,360); 
         g.drawString("must be successfully done.", 85, 390); 
         left.setVisible(false);
          g.setFont(new Font("Sans Serif", Font.BOLD, 40));
       
           }else if (count == 1){
         g.drawString("Level 1", 450, 50);
         g.setFont(new Font("Sans Serif",Font.BOLD, 20)); 
         g.drawString(" Welcome to the first level! ", 300,120);
         g.drawString("This level involves various slides you will navigate through.",145,180);
         g.drawString("They will provide detailed information about foods that are",145,210);
         g.drawString("edible and inedible (this will be tested in the later levels). ",145,240);
         g.drawString("The main goal of this level is not to be tested, but to learn.",145, 270);
         g.drawString("After going through all given slides in this level, users will be", 145, 300);
         g.drawString("given access to the next level in the game. Please navigate", 145, 330);
         g.drawString("through this level simply by right clicking the arrows on the  " ,145,360); 
         g.drawString("screen. This will change the slides and change the content", 145, 390); 
         g.drawString("being displayed. Have fun! ", 145, 420);
         
         left.setVisible(true);
      }else if (count == 2){
         g.drawString("Level 2", 450, 50);
         g.setFont(new Font("Sans Serif",Font.BOLD, 20)); 
         g.drawString(" Welcome to the second level! ", 300,120);
         g.drawString("This level involves various a pre-set path to navigate.",145,180);
         g.drawString("Through the path, there will be checkpoints to test your",145,210);
         g.drawString("knowledge on the different plants to be foraged. A screen",145,240);
         g.drawString("will pop up asking for the answer, check means the food is ",145, 270);
         g.drawString("edible and an x means the food is not. The answer can be", 145, 300);
         g.drawString("selected by right clicking the correct option. If a wrong", 145, 330);
         g.drawString("answer is selected, a prompt will indicate to re-select." ,145,360); 
         g.drawString("As well, the maze can be navigated through with the 4 arrow", 145, 390); 
         g.drawString("keys! After you reach the end, the third level can be played.", 145, 420);
         g.drawString("This will test you on your forage knowledge, so have fun!", 145, 450);
         right.setVisible(true);
         exit.setVisible(false);
      }else{            
         g.drawString("Level 3", 450, 50);
         g.setFont(new Font("Sans Serif",Font.BOLD, 20)); 
         g.drawString(" Welcome to the third level! ", 300,120);
         g.drawString("This level involves a un-set path that you will navigate!",145,180);
         g.drawString("On the map there will have randomly spawned items.",145,210);
         g.drawString("These items may be dangerous OR safe so choose carefully!",145,240);
         g.drawString("Whether the item is safe or not will not be directly prompted ",145, 270);
         g.drawString("until the end screen, which will tell you if what you've chosen", 145, 300);
         g.drawString("is good! During this time, you will have to collect the right items", 145, 330);
         g.drawString("and enough items before the 3 minute timer (right corner) runs out!" ,145,360); 
         g.drawString("Afterwards, your results will be disapled. During the came you can", 145, 390); 
         g.drawString("view your inventory by pressing \"i\". To pick up an item please press", 145, 420);
         g.drawString("\"z\", a mini-game will be displayed where you will be asked to collect", 145, 450);
         g.drawString("enough of the item while avoiding getting stung by the bees. If you ", 145, 480);
         g.drawString("are stung too much, the game will end completely. You will have", 145, 510);
         g.drawString("successfully collecte the item if the green bar is full.", 145, 540);
         g.drawString("Then you will be back to the main map. You can ", 145, 570);
          g.drawString("navigate through the map using the 4 arrow keys!",145, 600);

         right.setVisible(false);
         exit.setVisible(true);
      }    
   }
}