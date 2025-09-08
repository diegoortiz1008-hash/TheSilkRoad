
/**
 * Write a description of class Store here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Store
{
    private Rectangle shapeStore;
    private int tenges;
    private String color;
    private int xPosition;
    private int yPosition;
    
    public Store(){
        shapeStore = new Rectangle();
        tenges = 0;
        color = "black";
        xPosition = 10;
        yPosition = 10;
        
    }
    public boolean isEmpty(){
        if(this.tenges <= 0){
            return true;
        }
        else{
            return false;
        }
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
}