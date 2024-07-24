package shapes;

import java.util.Random;
import java.awt.*;
/* Lab 23, COMP161  2024
 * Shape.java
 * Represents a shape with a random colour, x and y coordinates
 * width and height.
 */


public class Shape{
   private int x;
   private int y;
   private int width;
   private int height;
   Color colour;
   int moveX = 1;
   int moveY = 1;

   /*returns random integer between high and low limits*/
   public int randomRange(int low, int high){
     
      Random generator = new Random();
      return generator.nextInt(Math.abs(high-low)+1) + Math.min(low, high);
   }
   /*constructor initialises data fields to random values*/
   public Shape(){
      width = randomRange(20, 30);
      height = width;
      x = randomRange(0, 400 - width);
      y = randomRange(0, 400 - height);
      colour = new Color(randomRange(0, 255),randomRange(0, 255),randomRange(0, 255));
   }

    /*Displays this shape
     * @param g a Graphics object allowing Graphics methods to be called to display this shape.
    */
   public  void display(Graphics g){
   g.setColor(colour);
   g.fillOval(x, y, width, height);
    
   }

   /** Moves this shape within a 400 x 400 space. Essentially 'bounces' the shape when an edge is reached.
   */
   public void move(){
      if (x >= 400-width || x <0){
         moveX = -moveX;
      }
      if (y >= 400-height || y <0){
         moveY = -moveY;
      }
      x += moveX;
      y += moveY;
   }

}