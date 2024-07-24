package shapes;
import javax.swing.*;

/**
 * Comp161 - Lab 23
 * A class to manage a GUI for animated shapes.
 */
public class ShapeApp{

   public static void main(String[]args){
      JFrame frame = new JFrame("Lab 23"); //creates a new JFrame with a title
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// Allows us to close the window
      //frame.setLayout(new FlowLayout());// Not technically necessary at this stage
 
      frame.getContentPane().add (new ShapePanel());
      frame.pack(); 
      frame.setVisible(true);
   }
}
