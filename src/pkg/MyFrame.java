package pkg;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
   MyFrame(){
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize (400,600);
      setLayout(null);
      add(new MyPanel());
      
      setVisible(true);
   }
}
