import java.util.*;
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
    private int originalPosition;
    private int originalTenges;
    private int position;
    private int profit;
<<<<<<< HEAD
    private ArrayList<Integer> profitHistory;
    
    public Robot(int location){
        shapeRobot = new Circle();
=======
    private ArrayList<Integer> profitHistory = new ArrayList<>();
    private ArrayList<Integer> movementHistory = new ArrayList<>();
    
    public Robot(int location){
        shapeRobot = new Circle();
        shapeRobot.changePositionX(location);
>>>>>>> master
        tenges = 0;
        xPosition = shapeRobot.getXPosition();
        yPosition = shapeRobot.getYPosition();
        color = "blue";
        position = location;
<<<<<<< HEAD
=======
        originalPosition = location;
>>>>>>> master
        profit = 0;
    }
    
    public boolean NotAbleToMove(){
        if(this.tenges <= 0){
            return false;
        }
        else{
            return true;
        }
    }
    
<<<<<<< HEAD
=======
    
>>>>>>> master
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
    
    
    public int getPosition(){
        return position;
    }
    
    public int getTenges(){
        return this.tenges;
    }
    
    public void advance(int meters){
        position += meters;
<<<<<<< HEAD
=======
        tenges -= Math.abs(meters);
>>>>>>> master
    }
    
    public int getOriginalTenges(){
        return originalTenges;
    }
    
    public int getProfit(){
        return profit;
    }
    
    public void setProfit(int sum){
        profit += sum;
    }
    
    public void setTenges(int tengesX){
        tenges += tengesX;
    }
    
    public int getOriginalPosition(){
        return this.originalPosition;
    }
    
    public void restorePosition(){
        position = originalPosition;
    }
    
    public void changePosition(int newPosition){
        this.position = newPosition;
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
    
<<<<<<< HEAD
=======
    public void changePositionX(int newXPosition){
        shapeRobot.changePositionX(newXPosition);
        this.xPosition = newXPosition;
    }
    
    public void changePositionY(int newYPosition){
        shapeRobot.changePositionY(newYPosition);
        this.yPosition = newYPosition;
    }
    
>>>>>>> master
    public ArrayList<Integer> getProfitHistory(){
        return this.profitHistory;
    }
    
    public void addProfitToHistory(int profit){
        profitHistory.add(profit);
    }
}