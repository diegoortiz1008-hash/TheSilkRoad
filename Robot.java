
/**
 * Write a description of class Robot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robot
{
    private Circle shapeRobot;
    private int tenges;
    private int xPosition;
    private int yPosition;
    private String color;
    
    public Robot(){
        shapeRobot = new Circle();
        tenges = 0;
        xPosition = shapeRobot.getXPosition();
        yPosition = shapeRobot.getYPosition();
        color = "black";
    }
    
    public boolean NotAbleToMove(){
        if(this.tenges <= 0){
            return false;
        }
        else{
            return true;
        }
    }
    
    public void moveTo(int x, int y){
        shapeRobot.changePositionX(x);
        shapeRobot.changePositionY(y);
    }
    
    public void makeVisible(){
        shapeRobot.makeVisible();
    }
    
    public void makeInvisible(){
        shapeRobot.makeInvisible();
    }
    
    public int getTenges(){
        return this.tenges;
    }
    
    public void setTenges(int newTenges){
        tenges += newTenges;
    }
    
    public void changeColor(String color){
        // cambia el color de la celda
        shapeRobot.changeColor(color);
        this.color = color;
    }
    
    public String getColor(){
        // da el color de la celda
        return this.color;
    }

    public int getXPosition(){
        return this.xPosition;
    }
    
    public int getYPosition(){
        return this.yPosition;
    }
    
    
    
}