package shapes;

import java.awt.Graphics;

public class Square extends Shape {

    public Square() {
        super();
    }

    @Override
    public void display(Graphics g) {
        g.setColor(colour);
        g.fillRect(x, y, width, height);
    }
    
}
