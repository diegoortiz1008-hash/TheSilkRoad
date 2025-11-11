package presentation;
import java.awt.*;
/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */


 
public class Rectangle extends Figure{

    public static int EDGES = 4;
    

    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(){
        height = 40;
        width = 40;
        xPosition = 0;
        yPosition = 0;
        color = "red";
        isVisible = false;
    }

    
    @Override 
    void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, 
                                       width, height));
            canvas.wait(10);
        }
    }

}

