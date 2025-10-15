/**
 * SilkRoad - Gestiona robots y tiendas en una ruta en espiral
 * 
 * Esta clase maneja robots que se mueven por una ruta para recolectar tenges de tiendas.
 * No simula día a día, solo provee métodos para manipular el sistema.
 * SilkRoadContest usa esta clase para hacer simulaciones día a día.
 * 
 * @author Mayorquin - Ortiz
 * @version Ciclo 3 - 2025-2
 */
import java.util.*;

public class SilkRoad
{
    private ArrayList<Store> roadStore;
    private ArrayList<Robot> roadRobot;
    private int numRobot, numStore, startTenges, rightLimit, leftLimit,  downLimit, upLimit;
    
    /**
     * Constructor vacío. Crea una ruta sin robots ni tiendas.
     */
    public SilkRoad(){
        Canvas canvas = Canvas.getCanvas();
        roadStore = new ArrayList();
        roadRobot = new ArrayList();
        rightLimit = canvas.getRightLimit();
        leftLimit = canvas.getLeftLimit();
        downLimit = canvas.getDownLimit();
        upLimit = canvas.getUpLimit();    
    }
    
    /**
     * Constructor que procesa eventos de días.
     * @param days Arreglo [tipo, posición, tenges]. tipo 1=robot, tipo 2=tienda
     */
    public SilkRoad(int[][] days){
        Canvas canvas = Canvas.getCanvas();
        roadStore = new ArrayList();
        roadRobot = new ArrayList();
        rightLimit = canvas.getRightLimit();
        leftLimit = canvas.getLeftLimit();
        downLimit = canvas.getDownLimit();
        upLimit = canvas.getUpLimit();
        for(int i = 0; i < days.length; i++){
            if(days[i][0] == 1){
                placeRobot(days[i][1]);
            }
            else{
                placeStore(days[i][1],days[i][2]);
            }
        }
    }
    
        /**
     * Dibuja un robot en el canvas según su posición en forma de espiral (método privado).
     * @param robot Robot a dibujar
     */
    private void drawRobot(Robot robot){
        int rightLimitX = rightLimit/45; 
        int downLimitY = downLimit/45; 
        int leftLimitX = leftLimit/45 ;
        int upLimitY = upLimit/45;
        int perimeter = 0;
        while(rightLimitX - leftLimitX  >= 0 && downLimitY - upLimitY >= 0 ){
            if(robot.getPosition() - perimeter <= rightLimitX - leftLimitX){
                robot.changePositionX(leftLimitX*45 + ((robot.getPosition()-perimeter)*45 - 40));
                robot.changePositionY(upLimitY*45 + 5);
                break;
            }
            else if(robot.getPosition()-perimeter <= rightLimitX - leftLimitX + downLimitY - upLimitY - 1){
                robot.changePositionX(rightLimitX*45 - 40);
                robot.changePositionY(upLimitY*45 + ((robot.getPosition()-perimeter)*45 - (rightLimitX-leftLimitX)*45 + 5 ));
                break;
            }
            else if(robot.getPosition()-perimeter <= 2*(rightLimitX - leftLimitX) + downLimitY - upLimitY - 2){
                robot.changePositionX(rightLimitX*45 - 45*(((robot.getPosition()-perimeter) + 1)-((rightLimitX-leftLimitX) + (downLimitY-upLimitY))) - 40);
                robot.changePositionY(downLimitY*45 - 40);
                break;
            }
            else if(robot.getPosition()-perimeter < 2*(rightLimitX - leftLimitX) + 2*(downLimitY - upLimitY) - 3){
                robot.changePositionX(leftLimitX*45 + 5);
                robot.changePositionY(downLimitY*45 - 45*(((robot.getPosition()-perimeter) + 1)-(2*(rightLimitX-leftLimitX) + (downLimitY-upLimitY) - 1)) - 40);
                break;
            }
            else{
                perimeter += (2*(rightLimitX-leftLimitX) + 2*(downLimitY - upLimitY - 2));
                rightLimitX -= 1;
                downLimitY -= 1;
                leftLimitX += 1;
                upLimitY += 1; 
            }
        }
        robot.makeVisible();
    }
    
    /**
     * Dibuja una tienda en el canvas según su posición en forma de espiral(método privado).
     * @param store Tienda a dibujar
     */
    private void drawStore(Store store){
        int rightLimitX = rightLimit/45; 
        int downLimitY = downLimit/45; 
        int leftLimitX = leftLimit/45 ;
        int upLimitY = upLimit/45;
        int perimeter = 0;
        while(rightLimitX - leftLimitX  >= 0 && downLimitY - upLimitY >= 0 ){
            if(store.getPosition() - perimeter <= rightLimitX - leftLimitX){
                store.changePositionX(leftLimitX*45 + ((store.getPosition()-perimeter)*45 - 45));
                store.changePositionY(upLimitY*45);
                break;
            }
            else if(store.getPosition()-perimeter <= rightLimitX - leftLimitX + downLimitY - upLimitY - 1){
                store.changePositionX(rightLimitX*45 - 45);
                store.changePositionY(upLimitY*45 + ((store.getPosition()-perimeter)*45 - (rightLimitX-leftLimitX)*45));
                break;
            }
            else if(store.getPosition()-perimeter <= 2*(rightLimitX - leftLimitX) + downLimitY - upLimitY - 2){
                store.changePositionX(rightLimitX*45 - 45*(((store.getPosition()-perimeter) + 1)-((rightLimitX-leftLimitX) + (downLimitY-upLimitY))) - 45);
                store.changePositionY(downLimitY*45 - 45);
                break;
            }
            else if(store.getPosition()-perimeter < 2*(rightLimitX - leftLimitX) + 2*(downLimitY - upLimitY) - 3){
                store.changePositionX(leftLimitX*45);
                store.changePositionY(downLimitY*45 - 45*(((store.getPosition()-perimeter) + 1)-(2*(rightLimitX-leftLimitX) + (downLimitY-upLimitY) - 1)) - 45);
                break;
            }
            else{
                perimeter += (2*(rightLimitX-leftLimitX) + 2*(downLimitY - upLimitY - 2));
                rightLimitX -= 1;
                downLimitY -= 1;
                leftLimitX += 1;
                upLimitY += 1; 
            }
        }
        store.makeVisible();
    }
    
    /**
     * Coloca una tienda en la ruta.
     * @param location Posición en la ruta
     * @param tenges Cantidad inicial de tenges
     */
    public void placeStore(int location, int tenges){
        Store storeX = new Store(location,tenges);
        roadStore.add(storeX);
        Collections.sort(roadStore, Comparator.comparingInt(s -> s.getPosition()));
        numStore += 1;
        drawStore(storeX);
    }
    
    /**
     * Elimina una tienda.
     * @param location Posición de la tienda
     */
    public void removeStore(int location){
        for(Store a: roadStore){
            if(a.getPosition() == location){
                roadStore.remove(a);
                a.makeInvisible();
            }
        }
    }
    
    /**
     * Elimina un robot.
     * @param location Posición del robot
     */
    public void removeRobot(int location){
        for(Robot a: roadRobot){
            if(a.getPosition() == location){
                roadRobot.remove(a);
                a.makeInvisible();
            }
        }
    }
    
    /**
     * Mueve todos los robots automáticamente buscando maximizar ganancias.
     * Usa estrategia greedy: mejor relación ganancia/distancia.
     */
    public void moveRobots() {
    for (Robot robot : roadRobot) {
        boolean sigueMoViendose = true;
        
        while (sigueMoViendose) {
            int bestMove = findBestMove(robot);
            if (bestMove != 0) {
                moveRobot(robot.getPosition(), bestMove);
                robot.makeVisible();
            } else {
                sigueMoViendose = false;  // No hay más movimientos rentables
            }
        }
    }
    }

    /**
     * Calcula el mejor movimiento para un robot (método privado).
     * @param robot Robot a evaluar
     * @return Distancia a moverse (0 si no hay buen movimiento)
     */
    private int findBestMove(Robot robot) {
        int currentPos = robot.getPosition();
        int bestMove = 0;
        double bestRatio = 1;

        for (Store store : roadStore) {
            if (store.isEmpty()) continue;
    
            int distance = store.getPosition() - currentPos;
            int absDistance = Math.abs(distance);
        
            if (absDistance == 0) continue;
    
            int potentialProfit = Math.max(absDistance, store.getTenges());
            if (potentialProfit <= 0) continue;
    
            double ratio = (double) potentialProfit / absDistance;  
            if (ratio > bestRatio) {
                bestRatio = ratio;
                bestMove = distance; 
            }
        }

        return bestMove;
    }   

    /**
     * Obtiene historial de ganancias por movimiento de cada robot.
     * @return Arreglo [posición_final, ganancia1, ganancia2, ...]
     */
    public int[][] profitPerMove() {
        ArrayList<int[]> robotProfitData = new ArrayList<>();
    
        for (Robot robot : roadRobot) {
                int currentPosition = robot.getPosition();
                ArrayList<Integer> history = robot.getProfitHistory(); 
            

                int[] robotData = new int[1 + history.size()]; 
                robotData[0] = currentPosition; 
            

                for (int i = 0; i < history.size(); i++) {
                    robotData[i + 1] = history.get(i); 
                }
            
                robotProfitData.add(robotData);
            }
        int[][] result = new int[robotProfitData.size()][];
        for (int i = 0; i < robotProfitData.size(); i++) {
            result[i] = robotProfitData.get(i);
        }
        Arrays.sort(result, (a, b) -> Integer.compare(a[0], b[0]));
    
        return result;
       }
    
    /**
     * Obtiene cuántas veces cada tienda fue vaciada.
     * @return Arreglo [posición, veces_vaciada]
     */
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
    
    /**
     * Coloca un robot en la ruta.
     * @param location Posición inicial
     */
    public void placeRobot(int location){
        Robot robotX = new Robot(location);
        roadRobot.add(robotX);
        Collections.sort(roadRobot, Comparator.comparingInt(r -> r.getPosition()));
        numRobot += 1;
        drawRobot(robotX);
        robotX.makeVisible();
    }
    
    /**
     * Mueve un robot específico. Si llega a una tienda, la roba automáticamente.
     * @param location Posición actual del robot
     * @param meters Distancia a moverse
     */
    public void moveRobot(int location, int meters){
        for(Robot a: roadRobot){
            if(a.getPosition() == location){
                a.advance(meters);
                for(Store c: roadStore){
                    if(c.getPosition() == a.getPosition()){
                        a.setTenges(c.getTenges());
                        a.setProfit(c.getTenges());
                        a.addProfitToHistory(c.getTenges());
                        c.empty();
                        c.increaseNumEmptied();
                    }
                }
                drawRobot(a);
            }
        }
    }
    
    /**
     * Restaura los tenges de todas las tiendas a su valor original.
     */
    public void resupplyStores(){
        for(Store a:roadStore){
            a.restoreTenges();
        }   
    }
    
    /**
     * Devuelve todos los robots a sus posiciones iniciales.
     */
    public void returnRobots(){
        for(Robot a:roadRobot){
            a.restorePosition();
            drawRobot(a);
        }
    }
    
    /**
     * Reinicia todo: restaura tiendas y crea robots nuevos en posiciones originales.
     */
    public void reboot(){
        for(Store a:roadStore){
            a.restoreTenges();
        }
        ArrayList<Robot> resetRobots = new ArrayList<>();
        for(Robot a: roadRobot){
            resetRobots.add(new Robot(a.getOriginalPosition()));
        }
        roadRobot = resetRobots;
        for(Robot a: roadRobot){
            drawRobot(a);
        }
    }

    /**
     * Calcula la ganancia total de todos los robots.
     * @return Suma de ganancias
     */   
    public int profit(){
        int total = 0;
        for(Robot a:roadRobot){
            total += a.getProfit();
        }
        return total;
    }
    
    /**
     * Obtiene datos de todos los robots.
     * @return Arreglo [posición, tenges] por cada robot
     */    
    public int[][] robots(){
        int[][] data = new int[roadRobot.size()][2]; 
        for (int i = 0; i < roadRobot.size(); i++) {
             data[i][0] = roadRobot.get(i).getPosition(); 
             data[i][1] = roadRobot.get(i).getTenges();
        }
        return data;
    }
    
    /**
     * Obtiene datos de todas las tiendas.
     * @return Arreglo [posición, tenges] por cada tienda
     */
    public int[][] store(){
        int[][] data = new int[roadStore.size()][2]; 
        for (int i = 0; i < roadStore.size(); i++) {
             data[i][0] = roadStore.get(i).getPosition(); 
             data[i][1] = roadStore.get(i).getTenges();
        }
        return data;
    }
    
    /**
     * Hace visibles todos los elementos en el canvas.
     */
    public void makeVisible(){
        for(Robot r: roadRobot){
            r.makeVisible();
        }
        
        for(Store s: roadStore){
            s.makeVisible();
        }
        
    }
    
    /**
     * Hace invisibles todos los elementos en el canvas.
     */
    public void makeInvisible(){
        for(Robot r: roadRobot){
            r.makeInvisible();
        }
        
        for(Store s: roadStore){
            s.makeInvisible();
        }        
    }
    
    /**
     * Limpia y finaliza todo. Vacía colecciones.
     */
    public void finish(){
        makeInvisible();
        roadRobot.clear();
        roadStore.clear();
    }

    /**
     * Verifica integridad del sistema.
     * @return true si todo está bien, false si hay problemas
     */
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