
/**
 * Write a description of class SilkRoad here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SilkRoad
{
    private ArrayList<Road> roadOfSilkRoad;
    private ArrayList<Store> roadStore;
    private ArrayList<Robot> roadRobot;
    private int numRobot;
    private int numStore;
    private ArrayList<Integer> originalRobotPosition;
    private ArrayList<Integer> storePosition;
    private int startTenges;
    
    public SilkRoad(int nRobots, int nStores){
        roadOfSilkRoad = new ArrayList();
        numRobot = nRobots;
        numStore = nStores;
        originalRobotPosition = new ArrayList();
        storePosition = new ArrayList();
    }
    
    public void create(){
        roadStore = new ArrayList();
        roadRobot = new ArrayList();
        int positionX = 0;
        for(int i=0; i<=21; i++){
            roadStore.add(new Store());
            roadRobot.add(new Robot());
            roadStore.get(i).moveTo(roadStore.get(i).getXPosition()+positionX, roadStore.get(i).getYPosition());
            roadRobot.get(i).moveTo(roadRobot.get(i).getXPosition()+positionX, roadRobot.get(i).getYPosition());
            positionX += 45;
        }
    }
    
    //FALTA SILK ROAD
    
    public void placeStore(int location, int tenges){
        Random random = new Random();
        int position = random.nextInt(20);
        startTenges = tenges;
        
        for(int k=0; k<numStore; k++){
            while(!roadRobot.get(position).getColor().equals("black") && !roadStore.get(position).getColor().equals("black")){
                position = random.nextInt(0,20);
            }
            roadStore.get(position).changeColor("red");
            roadStore.get(position).setTenges(tenges);
            storePosition.add(position);
        }
    }
    
    public void removeStore(int location){
        roadStore.get(location).changeColor("black");
        roadStore.get(location).setTenges(0);
    }
    
    public void placeRobot(int location){
        Random random = new Random();
        int position = random.nextInt(20);
        
        for(int i=0; i< numRobot; i++){
            while(!roadRobot.get(position).getColor().equals("black")){
                position = random.nextInt(0,20);
            }
            roadRobot.get(position).changeColor("blue");
            originalRobotPosition.add(position);
            roadRobot.get(position).setTenges(5);
        }
        
    }
    
    public void moveRobot(int location, int meters){
        roadRobot.get(location).changeColor("black");
        roadRobot.get(location+meters).changeColor("blue");
        roadRobot.get(location+meters).setTenges(roadRobot.get(location).getTenges());
        roadRobot.get(location).setTenges(0);
    }
    
    public void resupplyStores(){
        for(Store a:roadStore){
            if(!a.getColor().equals("black")){
                a.setTenges(startTenges);
            }
        }
        
    }
    
    public void returnRobots(){
        for(Robot a:roadRobot){
            a.changeColor("black");
            a.setTenges(0);
        }
        
        for(int u: originalRobotPosition){
            roadRobot.get(u).changeColor("blue");
            roadRobot.get(u).setTenges(5);
        }
    }
    
    public void reboot(int locationRobot, int locationStore, int tenges){
        create();
        placeRobot(locationRobot);
        placeStore(locationStore, tenges);
    }
    
    public int profit(){
        int total = 0;
        for(Robot a:roadRobot){
            if(!a.getColor().equals("black")){
                total += a.getTenges();
            }
        }
        return total;
    }
    
    // FALTA STROES Y ROBOTS
    
    public void makeVisible(){
        for(Robot r: roadRobot){
            r.makeVisible();
        }
        
        for(Store s: roadStore){
            s.makeVisible();
        }
        
    }
    
    public void makeInvisible(){
        for(Robot r: roadRobot){
            r.makeInvisible();
        }
        
        for(Store s: roadStore){
            s.makeInvisible();
        }        
    }
    
    public void finish(){
        
    }
    
    public boolean ok(){
     return true;
    }
}