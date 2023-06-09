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
               Game.screenNum = 1; //Insert number
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
         g.drawString("Bibliography", 300, 50);
         g.setFont(new Font("Sans Serif",Font.BOLD, 20));           
         String[] words = Game.bib[0].split("\\s+");
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
        }
}

