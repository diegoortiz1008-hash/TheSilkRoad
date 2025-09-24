
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
        // Procesar cada robot para encontrar su mejor movimiento
        for (int i = 0; i < roadRobot.size(); i++) {
            Robot robot = roadRobot.get(i);
        
            // Solo procesar robots que pueden moverse (tienen tenges)
            if (robot.NotAbleToMove()) {
                int currentPos = robot.getPosition();
                int bestPosition = currentPos;
                double bestRatio = 0; // Ratio ganancia/distancia
            
                // Evaluar todas las tiendas disponibles para encontrar la mejor
                for (Store store : roadStore) {
                    if (!store.isEmpty()) {
                        int storePos = store.getPosition();
                        int distance = Math.abs(storePos - currentPos);
                    
                        // Solo considerar tiendas que estén al alcance (el robot puede llegar)
                        if (distance > 0 && distance <= robot.getTenges()) {
                            // Calcular ganancia potencial
                            int potentialProfit = Math.min(robot.getTenges() - distance, store.getTenges());
                        
                            if (potentialProfit > 0) {
                                // Calcular ratio ganancia/distancia para encontrar la más eficiente
                                double ratio = (double) potentialProfit / distance;
                            
                                // Si esta tienda es más eficiente, o igual de eficiente pero más cercana
                                if (ratio > bestRatio || (ratio == bestRatio && distance < Math.abs(bestPosition - currentPos))) {
                                    bestRatio = ratio;
                                    // Mover hacia la tienda, pero no más de 2 posiciones por turno
                                    int maxMove = Math.min(2, Math.abs(storePos - currentPos));
                                    if (storePos > currentPos) {
                                        bestPosition = currentPos + maxMove;
                                    } else {
                                        bestPosition = currentPos - maxMove;
                                    }
                                }
                            }
                        }
                    }
                }
            
                // Solo mover si encontró una mejor posición
                if (bestPosition != currentPos) {
                    // Verificar que no haya otro robot en la posición destino
                    boolean positionFree = true;
                    for (Robot otherRobot : roadRobot) {
                        if (otherRobot != robot && otherRobot.getPosition() == bestPosition) {
                            positionFree = false;
                            break;
                        }
                    }
                
                    if (positionFree) {
                        // Calcular cuántos metros se va a mover
                        int metersToMove = bestPosition - currentPos;
                    
                        // Calcular ganancia REAL de este movimiento
                        int actualProfit = 0;
                        for (Store store : roadStore) {
                            if (store.getPosition() == bestPosition && !store.isEmpty()) {
                                int canBuy = Math.min(robot.getTenges() - Math.abs(metersToMove), store.getTenges());
                                if (canBuy > 0) {
                                    actualProfit = canBuy;
                                }
                                break;
                            }
                        }
                    
                        // Agregar la ganancia actual al historial completo del robot
                        robot.addProfitToHistory(actualProfit);
                    
                        // Usar el método moveRobot existente (location, meters)
                        // Este método ya se encarga de actualizar la posición del robot
                        moveRobot(currentPos, metersToMove);
                    
                        // Si llegó a una tienda, realizar la transacción automáticamente
                        for (Store store : roadStore) {
                            if (store.getPosition() == bestPosition && !store.isEmpty()) {
                                // Calcular cuánto puede comprar el robot
                                int canBuy = Math.min(robot.getTenges(), store.getTenges());
                            
                                if (canBuy > 0) {
                                    // Realizar la transacción
                                    robot.setTenges(robot.getTenges() + canBuy); // El robot gana tenges
                                    store.setTenges(store.getTenges() - canBuy);  // La tienda pierde tenges
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public int[][] profitPerMove() {
        ArrayList<int[]> robotProfitData = new ArrayList<>();
    
        // Recorrer todos los robots y obtener su historial completo de movimientos
        for (Robot robot : roadRobot) {
            if (robot.NotAbleToMove()) { // Solo robots activos
                int currentPosition = robot.getPosition();
                ArrayList<Integer> history = robot.getProfitHistory(); // Obtener historial completo
            
                // Crear array con posición actual + todas las ganancias
                int[] robotData = new int[1 + history.size()]; // 1 para posición + N ganancias
                robotData[0] = currentPosition; // Primera posición: posición actual
            
                // Agregar todas las ganancias del historial
                for (int i = 0; i < history.size(); i++) {
                    robotData[i + 1] = history.get(i); // Posiciones 1, 2, 3... N
                }
            
                robotProfitData.add(robotData);
            }
        }
    
        // Convertir ArrayList a array 2D
        int[][] result = new int[robotProfitData.size()][];
        for (int i = 0; i < robotProfitData.size(); i++) {
            result[i] = robotProfitData.get(i);
        }
    
        // Ordenar por posición (primera columna) de menor a mayor
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