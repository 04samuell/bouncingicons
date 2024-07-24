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
import java.io.*;

public class ShapePanel extends JPanel {
   ArrayList<Shape> shapes = new ArrayList<Shape>();
   JPanel controlPanel;
   JPanel drawPanel;
   JButton addShape;
   JButton start;
   JButton stop;

   JTextField showNum = new JTextField(2);
   JLabel countLabel;
   Timer timer;
   
   private final int DELAY = 10;
   
   /* Constructor -- createss a ShapePanel containing a control panel and a drawing panel*/
   public ShapePanel() {
        
      controlPanel = new JPanel();
      controlPanel.setPreferredSize(new Dimension(100,400));
      controlPanel.setLayout(new FlowLayout());
      
      drawPanel = new DrawingPanel();   
      
      countLabel = new JLabel("Count");
      showNum = new JTextField(2);
    
      addShape =  new JButton ("Add Shape");
      start = new JButton("Start");
      stop =  new JButton("Stop");
      
      ButtonListener bl = new ButtonListener();
      
      addShape.addActionListener(bl);
      start.addActionListener(bl);
      stop.addActionListener(bl);
      
      timer = new Timer(DELAY, bl);
           
      controlPanel.add(addShape);
      controlPanel.add(start);
      controlPanel.add(stop);
      controlPanel.add(countLabel);
      controlPanel.add(showNum);
      
      add(controlPanel);
      add(drawPanel);
      
   }
   
   /*inner class providing drawing JPanel*/
   private class DrawingPanel extends JPanel{
      private DrawingPanel(){
         setPreferredSize(new Dimension(400,400));
         setBackground(Color.pink);
      }
      
      protected void paintComponent(Graphics g){
         super.paintComponent(g);
      
         for(int i=0 ;i < shapes.size(); i++){
            shapes.get(i).display(g);
         }
      
      }
   }
   
   /*inner class to provide responses to the timer event and any button press.*/
   private class ButtonListener implements ActionListener{
   
      public void actionPerformed(ActionEvent e){
         
         if (e.getSource() == timer)
            for(int i=0 ;i < shapes.size(); i++){
               shapes.get(i).move();
            }
         else{ //its a button
            
            JButton b = (JButton) e.getSource();
            
            if (b.getText() == "Add Shape"){
               shapes.add(new Shape());
            } else if (b.getText() == "Start"){
               timer.start();
            } else if (b.getText() == "Stop"){
               timer.stop();
            }         
            if (shapes.isEmpty()) {
               showNum.setText(" ");
            }
            else{
               showNum.setText(Integer.toString(shapes.size()-1));//"" + (shapes.size()-1)
            }
            
         }
         repaint();
      }
   }
}
