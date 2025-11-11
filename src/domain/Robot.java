package domain;

import presentation.Circle;
import java.util.*;

/**
 * Write a description of class Robot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robot implements Unit
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
    private ArrayList<Integer> profitHistory = new ArrayList<>();
    private ArrayList<Integer> movementHistory = new ArrayList<>();
    private String type;
    private boolean isBlinking = false;
    
    /**
     * Crea un nuevo robot con las especificaciones dadas.
     */
    public Robot(String typeX, int location){
        shapeRobot = new Circle();
        shapeRobot.changePositionX(location);
        tenges = 0;
        xPosition = shapeRobot.getXPosition();
        yPosition = shapeRobot.getYPosition();
        color = "blue";
        position = location;
        originalPosition = location;
        type = typeX;
        profit = 0;
    }
    

    
    public String getType(){
        return type;
    }
    
    public void changeType(String newType){
        this.type = newType;
    }

    /**
     * Cambia las posiciones verticales y horizontales del robot
     * @Param x, nueva posicion vertical
     * @Param y, nueva posicion horizontal
     */
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
    
    /**
     * Cambia la posicion del robot y descuenta los tenges segun la distancia movida
     * @Param x, nueva posicion vertical
     */
    public void advance(int meters){
        position += meters;
        tenges -= Math.abs(meters);
    }
    
    public int getOriginalTenges(){
        return originalTenges;
    }
    
    public int getProfit(){
        return profit;
    }
    
    public void setProfit(int sum){
        profit += sum;
        addProfitHistory(sum);
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
    
    public void changePositionX(int newXPosition){
        shapeRobot.changePositionX(newXPosition);
        this.xPosition = newXPosition;
    }
    
    public void changePositionY(int newYPosition){
        shapeRobot.changePositionY(newYPosition);
        this.yPosition = newYPosition;
    }
    
    public ArrayList<Integer> getProfitHistory(){
        return this.profitHistory;
    }
    
    /**
     *Añade una nueva ganancia al historial de ganancias del robot
     *@Param profit, nueva ganancia
     */
    public void addProfitHistory(int profit){
        profitHistory.add(profit);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Activa o desactiva el parpadeo del robot.
     * @param active true para empezar a parpadear, false para detenerlo.
     */
    public void blink(boolean active) {
        if (active && !isBlinking) {
            isBlinking = true;
            Thread blinkThread = new Thread(() -> {
                boolean visible = true;
                while (isBlinking) {
                    if (visible) {
                        shapeRobot.makeInvisible();
                    } else {
                        shapeRobot.makeVisible();
                    }
                    visible = !visible;
                    sleep(200);
                }
                shapeRobot.makeVisible();
            });
            blinkThread.setDaemon(true);
            blinkThread.start();
        } else if (!active) {
            isBlinking = false;
        }
    }
}
