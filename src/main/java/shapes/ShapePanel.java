/*
 * ShapePanel.java
 *
 * Lab 23, COMP161  2023
 *
 * Sets up a GUI with a control panel to hold buttons, and a 400 x 400 drawing panel
 * on which to draw Shape objects (see Shape.java)
 */
package shapes;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class ShapePanel extends JPanel {
   ArrayList<Shape> shapes = new ArrayList<Shape>();
   JPanel controlPanel;
   JPanel drawPanel;
   JButton addShape;
   JButton start;
   JButton stop;
   JLabel invalidLbl = new JLabel("");

   JTextField showNum = new JTextField(2);
   JLabel countLabel;
   Timer timer;

   private JButton[] buttons = { new JButton("Circle"), new JButton("Square"), new JButton("Oval"),
         new JButton("Smiley"), new JButton("Swirl"), new JButton("Start"), new JButton("Stop"),
         new JButton("Remove") };

   private final int DELAY = 10;

   /*
    * Constructor -- createss a ShapePanel containing a control panel and a drawing
    * panel
    */
   public ShapePanel() {

      controlPanel = new JPanel();
      controlPanel.setPreferredSize(new Dimension(100, 400));
      controlPanel.setLayout(new FlowLayout());

      drawPanel = new DrawingPanel();

      countLabel = new JLabel("Count");
      showNum = new JTextField("Remove Which", 2);

      ButtonListener bl = new ButtonListener();

      timer = new Timer(DELAY, bl);

      controlPanel.add(countLabel);
      controlPanel.add(showNum);

      for (JButton button : buttons) {
         controlPanel.add(button);
         button.addActionListener(bl);
      }
      controlPanel.add(invalidLbl);

      add(controlPanel);
      add(drawPanel);

   }

   /* inner class providing drawing JPanel */
   private class DrawingPanel extends JPanel {
      private DrawingPanel() {
         setPreferredSize(new Dimension(400, 400));
         setBackground(Color.pink);
      }

      protected void paintComponent(Graphics g) {
         super.paintComponent(g);

         for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).display(g);
            shapes.get(i).showIndex(g, i);
         }

      }
   }

   /* inner class to provide responses to the timer event and any button press. */
   private class ButtonListener implements ActionListener {

      private boolean invalid;

      public void actionPerformed(ActionEvent e) {

         if (e.getSource() == timer)
            for (int i = 0; i < shapes.size(); i++) {
               shapes.get(i).move();
            }
         else { // its a button

            JButton b = (JButton) e.getSource();

            if (b.getText().equals("Circle")) {
               shapes.add(new Circle());
            } else if (b.getText().equals("Square")) {
               shapes.add(new Square());
            } else if (b.getText().equals("Oval")) {
               shapes.add(new Oval());
            } else if (b.getText().equals("Smiley")) {
               shapes.add(new Smiley());
            } else if (b.getText().equals("Swirl")) {
               shapes.add(new Swirl());
            } else if (b.getText().equals("Start")) {
               timer.start();
            } else if (b.getText().equals("Stop")) {
               timer.stop();
            } else if (b.getText().equals("Remove")) {
               try {

                  int numberToRemove = Integer.parseInt(showNum.getText());
                  shapes.remove(numberToRemove);
                  
               } catch (NumberFormatException ex) {
                  handleRemoveException();
               } catch (IndexOutOfBoundsException ex) {
                  handleRemoveException();
               }
            }
            if (shapes.isEmpty()) {
               showNum.setText(" ");
            } else {
               showNum.setText(Integer.toString(shapes.size() - 1));// "" + (shapes.size()-1)
            }

         }
         if (!invalid) {
            invalidLbl.setText("");
         }
         invalid = false;
         repaint();
      }

      private void handleRemoveException() {
         invalidLbl.setForeground(Color.RED);
         invalidLbl.setText("INVALID");
         invalid = true;
      }
   }
}
