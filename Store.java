
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
    private int initialTenges;
    private int position;
    private int originalTenges;
    private int numEmptied;
    
    
    public Store(int location, int tengesX){
        shapeStore = new Rectangle();
        shapeStore.changePositionX(location);
        tenges = tengesX;
        color = "red";
        xPosition = shapeStore.getXPosition();
        yPosition = shapeStore.getYPosition();
        position = location;
        originalTenges = tengesX;
        tenges = tengesX;
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
    
    public void empty(){
        tenges = 0;
        this.changeColor("black");
    }
    
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
    
    public int getNumEmptied(){
        return numEmptied;
    }
    
    public void increaseNumEmptied(){
        numEmptied += 1;
    }
    
    
}