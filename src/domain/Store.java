package domain;

import presentation.Rectangle;
import java.util.*;

/**
 * Write a description of class Store here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Store implements Unit
{
    private Rectangle shapeStore;
    private int tenges;
    private String color;
    private int xPosition;
    private int yPosition;
    private int initialTenges;
    private int position;
    private int originalTenges;
    private int numEmptied;
    private String type;
    
    /**
     * Create un nuevo robot con las especificaciones dadas.
     */
    public Store(String typeX, int location, int tengesX){
        shapeStore = new Rectangle();
        shapeStore.changePositionX(location);
        tenges = tengesX;
        color = "red";
        xPosition = shapeStore.getXPosition();
        yPosition = shapeStore.getYPosition();
        position = location;
        originalTenges = tengesX;
        tenges = tengesX;
        type = typeX;
        autonomous();
    }
    
    /**
     * Verifica si la tienda esta vacia
     * @Return true, si la tienda esta vacia
     */
    public boolean isEmpty(){
        if(this.tenges <= 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public String getType(){
        return type;
    }
    
    /**
     * La tienda al ser de tipo autonomus, se ubica por si sola en una posicion aleatoria en cualquier ubicacion del camino.
     * 
     */
    public void autonomous(){
        if(type=="autonomous"){
            Random random = new Random();
            this.changePosition(random.nextInt(100000000));
        }
    }
    
    public void changeType(String newType){
        this.type = newType;
    }
    
    public void moveTo(int x, int y){
        shapeStore.changePositionX(x);
        shapeStore.changePositionY(y);
    }
    
    public void makeVisible(){
        shapeStore.makeVisible();
    }
    
    public void makeInvisible(){
        shapeStore.makeInvisible();
    }
    
    public int getTenges(){
        return this.tenges;
    }
    
    public void setTenges(int newTenges){
        tenges = newTenges;
    }
    
    /**
     * Vacia la tienda completamente
     */
    public void empty(){
        tenges = 0;
        this.changeColor("black");
        increaseNumEmptied();
    }
    
    /**
     * Restaura la tienda con sus tenges originales
     */
    public void restoreTenges(){
        this.changeColor("red");
        tenges = originalTenges;
    }

    public String getColor(){
        // da el color de la celda
        return this.color;
    }
    
    public void changeColor(String color){
        // cambia el color de la celda
        shapeStore.changeColor(color);
        this.color = color;
    }

    public int getXPosition(){
        return this.xPosition;
    }
    
    public int getYPosition(){
        return this.yPosition;
    }
    
    public void changePositionX(int newXPosition){
        shapeStore.changePositionX(newXPosition);
        this.xPosition = newXPosition;
    }
    
    public void changePositionY(int newYPosition){
        shapeStore.changePositionY(newYPosition);
        this.yPosition = newYPosition;
    }
    
    public int getPosition(){
        return this.position;
    }
    
    private void changePosition(int newPosition){
        position = newPosition;
    }
    
    
    public int getNumEmptied(){
        return numEmptied;
    }
    
    /**
     * Aumenta el numero de veces que la tienda ha sido vaciada
     */
    public void increaseNumEmptied(){
        numEmptied += 1;
    }
    
    
}
