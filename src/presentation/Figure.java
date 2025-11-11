package presentation;
import java.awt.*;
/**
 * Write a description of class Figure here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Figure
{
    protected int height;
    protected int width;
    protected int xPosition;
    protected int yPosition;
    protected String color;
    protected boolean isVisible;
    
    public void makeVisible(){
        isVisible = true;
        draw();
    }

    abstract void draw();

    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    
    public int getXPosition(){
        return this.xPosition;
    }
    
    public int getYPosition(){
        return this.yPosition;
    }
    
    public void changePositionX(int x){
        erase(); 
        xPosition = x;
        draw();
    }
    
    public void changePositionY(int y){
        erase(); 
        yPosition = y;
        draw();
    }
    
    /**
     * Move the Figure horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the Figure vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }
    
    /**
     * Make this Figure invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Move the Figure a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the Figure a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the Figure a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }
    
    /**
     * Move the Figure a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }
    
    /**
     * Slowly move the Figure horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the triangle vertically.
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }
}