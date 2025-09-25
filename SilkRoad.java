
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
    private int numRobot, numStore, startTenges, rightLimit, leftLimit,  downLimit, uptLimit;
    private ArrayList<int[]> movementHistory;
    
    public SilkRoad(){
        Canvas canvas = Canvas.getCanvas();
        roadOfSilkRoad = new ArrayList();
        roadStore = new ArrayList();
        roadRobot = new ArrayList();
        rightLimit = canvas.getRightLimit();
        leftLimit = canvas.getLeftLimit();
        downLimit = canvas.getDownLimit();
        uptLimit = canvas.getUpLimit();
    }
    
    public void __(int[][] days){
        for(int i = 0; i < days.length; i++){
            if(days[i][0] == 1){
                placeRobot(days[i][1]);
            }
            else{
                placeStore(days[i][1],days[i][2]);
            }
        }
    }
    
    
    public void placeStore(int location, int tenges){
        Store storeX = new Store(location,tenges);
        roadStore.add(storeX);
        numStore += 1;
        storeX.makeVisible();
    }
    
    public void removeStore(int location){
        for(Store a: roadStore){
            if(a.getPosition() == location){
                roadStore.remove(a);
            }
        }
    }
    
    public void removeRobot(int location){
        for(Robot a: roadRobot){
            if(a.getPosition() == location){
                roadRobot.remove(a);
            }
        }
    }
    
    public void moveRobots() {
        for (Robot robot : roadRobot) {
            if (!robot.NotAbleToMove()) continue;
        
            int bestMove = findBestMove(robot);
            if (bestMove != 0) {
                moveRobot(robot.getPosition(), bestMove);
            }
        }
    }

    private int findBestMove(Robot robot) {
        int currentPos = robot.getPosition();
        int bestMove = 0;
        double bestRatio = 0;
    
        for (Store store : roadStore) {
            if (store.isEmpty()) continue;
        
            int distance = Math.abs(store.getPosition() - currentPos);
            if (distance == 0 || distance > robot.getTenges()) continue;
        
            int potentialProfit = Math.min(robot.getTenges() - distance, store.getTenges());
            if (potentialProfit <= 0) continue;
        
            double ratio = (double) potentialProfit / distance;
            if (ratio > bestRatio) {
                bestRatio = ratio;
                int direction = distance;
            }
        }
    
        return bestMove;
    }

    
    public int[][] profitPerMove() {
        ArrayList<int[]> robotProfitData = new ArrayList<>();
    
        for (Robot robot : roadRobot) {
            if (robot.NotAbleToMove()) { 
                int currentPosition = robot.getPosition();
                ArrayList<Integer> history = robot.getProfitHistory(); 
            

                int[] robotData = new int[1 + history.size()]; 
                robotData[0] = currentPosition; 
            

                for (int i = 0; i < history.size(); i++) {
                    robotData[i + 1] = history.get(i); 
                }
            
                robotProfitData.add(robotData);
            }
        }
        int[][] result = new int[robotProfitData.size()][];
        for (int i = 0; i < robotProfitData.size(); i++) {
            result[i] = robotProfitData.get(i);
        }
        Arrays.sort(result, (a, b) -> Integer.compare(a[0], b[0]));
    
        return result;
    }
    
    public int[][] emptiedStores() {
        int[][] result = new int[roadStore.size()][2];
        int count = 0;
        for (Store store : roadStore) {
            result[count][0] = store.getPosition(); 
            result[count][1] = store.getNumEmptied();
            count += 1;
        }
        return result;
    }
    
    public void placeRobot(int location){
        Robot robotX = new Robot(location);
        roadRobot.add(robotX);
        numRobot += 1;
        robotX.makeVisible();
    }
    
    public void moveRobot(int location, int meters){
        for(Robot a: roadRobot){
            if(a.getPosition() == location){
                a.advance(meters);
                for(Store c: roadStore){
                    if(c.getPosition() >= location && c.getPosition() <= location+meters){
                        a.setTenges(c.getTenges());
                        a.setProfit(c.getTenges());
                        a.addProfitToHistory(c.getTenges());
                        c.setTenges(0);
                        c.aumentNumEmptied();
                    }
                }
            }
        }
    }
    
    public void resupplyStores(){
        for(Store a:roadStore){
            a.restoreTenges();
        }   
        
    }
    
    public void returnRobots(){
        for(Robot a:roadRobot){
            a.restorePosition();
        }
    }
    
    public void reboot(){
        for(Store a:roadStore){
            a.restoreTenges();
        }
        ArrayList<Robot> resetRobots = new ArrayList<>();
        for(Robot a: roadRobot){
            resetRobots.add(new Robot(a.getOriginalPosition()));
        }
        roadRobot = resetRobots;
    }

    
    public int profit(){
        int total = 0;
        for(Robot a:roadRobot){
            total += a.getProfit();
        }
        return total;
    }
    
    public int[][] robots(){
        int[][] data = new int[roadRobot.size()][2]; 
        for (int i = 0; i < roadRobot.size(); i++) {
             data[i][0] = roadRobot.get(i).getPosition(); 
             data[i][1] = roadRobot.get(i).getTenges();
        }
        return data;
    }
    
    public int[][] store(){
        int[][] data = new int[roadStore.size()][2]; 
        for (int i = 0; i < roadStore.size(); i++) {
             data[i][0] = roadStore.get(i).getPosition(); 
             data[i][1] = roadStore.get(i).getTenges();
        }
        return data;
    }
    
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
        makeInvisible();
        roadRobot.clear();
        roadStore.clear();
    }

    
    public boolean ok(){
        if (roadStore == null || roadRobot == null){
            return false;}
        if (roadStore.size() < numStore){
            return false;}
        if (roadRobot.size() < numRobot){
            return false;}
        return true;
    }
}