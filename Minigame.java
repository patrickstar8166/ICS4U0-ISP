import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Bibliography extends JPanel implements Runnable{ 
   private JButton exit;
   private boolean running = true;

   public Bibliography (){ 
      exit = new JButton("Exit");
   
      this.setLayout(null);
     
      exit.setFont(new Font("Sans Serif", Font.BOLD, 30));
      exit.setBackground(Color.LIGHT_GRAY);
      exit.setBounds(875, 550, 100, 60);
   
        this.add(exit);
        exit.setVisible(true);
   }

   public void run(){
    
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
              
         String[] words = Game.instructions[count].split("\\s+");
         StringBuilder line = new StringBuilder(words[0]);
         int location = 180;
         for (int i = 1; i < words.length; i++) {
            if (line.length() + words[i].length() + 1 <= 70) {
               line.append(" ").append(words[i]);
            } else {
               g.drawString(line.toString(), 120, location);
               location += 30;
               line = new StringBuilder(words[i]);
            }
         }
         g.drawString(line.toString(), 120, location);
       
         left.setVisible(false);
      }else if (count == 1){
         g.drawString("Level 1", 450, 50);
         g.setFont(new Font("Sans Serif",Font.BOLD, 20)); 
         g.drawString(" Welcome to the first level! ", 360,120);
         
         String[] words = Game.instructions[count].split("\\s+");
         StringBuilder line = new StringBuilder(words[0]);
         int location = 180;
         for (int i = 1; i < words.length; i++) {
            if (line.length() + words[i].length() + 1 <= 70) {
               line.append(" ").append(words[i]);
            } else {
               g.drawString(line.toString(), 120, location);
               location += 30;
               line = new StringBuilder(words[i]);
            }
         }
         g.drawString(line.toString(), 120, location);
         
         left.setVisible(true);
      }else if (count == 2){
         g.drawString("Level 2", 450, 50);
         g.setFont(new Font("Sans Serif",Font.BOLD, 20)); 
         g.drawString(" Welcome to the second level! ", 360,120);
         
         String[] words = Game.instructions[count].split("\\s+");
         StringBuilder line = new StringBuilder(words[0]);
         int location = 180;
         for (int i = 1; i < words.length; i++) {
            if (line.length() + words[i].length() + 1 <= 70) {
               line.append(" ").append(words[i]);
            } else {
               g.drawString(line.toString(), 120, location);
               location += 30;
               line = new StringBuilder(words[i]);
            }
         }
         g.drawString(line.toString(), 120, location);         
         right.setVisible(true);
         exit.setVisible(false);
      }else{            
         g.drawString("Level 3", 450, 50);
         g.setFont(new Font("Sans Serif",Font.BOLD, 20)); 
         g.drawString(" Welcome to the third level! ", 360,120);
         
         String[] words = Game.instructions[count].split("\\s+");
         StringBuilder line = new StringBuilder(words[0]);
         int location = 180;
         for (int i = 1; i < words.length; i++) {
            if (line.length() + words[i].length() + 1 <= 70) {
               line.append(" ").append(words[i]);
            } else {
               g.drawString(line.toString(), 120, location);
               location += 30;
               line = new StringBuilder(words[i]);
            }
         }
         g.drawString(line.toString(), 120, location);
                
         right.setVisible(false);
         exit.setVisible(true);
      }    
   }
}