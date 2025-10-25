<<<<<<< HEAD

/**
 * Write a description of class SilkRoad here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

=======
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
>>>>>>> master
import java.util.*;

public class SilkRoad
{
<<<<<<< HEAD
    private ArrayList<Road> roadOfSilkRoad;
    private ArrayList<Store> roadStore;
    private ArrayList<Robot> roadRobot;
    private int numRobot, numStore, startTenges, rightLimit, leftLimit,  downLimit, uptLimit;
    private ArrayList<int[]> movementHistory;
    
    public SilkRoad(){
        Canvas canvas = Canvas.getCanvas();
        roadOfSilkRoad = new ArrayList();
=======
    private ArrayList<Store> roadStore;
    private ArrayList<Robot> roadRobot;
    private int numRobot, numStore, startTenges, rightLimit, leftLimit,  downLimit, upLimit;
    
    /**
     * Constructor vacío. Crea una ruta sin robots ni tiendas.
     */
    public SilkRoad(){
        Canvas canvas = Canvas.getCanvas();
>>>>>>> master
        roadStore = new ArrayList();
        roadRobot = new ArrayList();
        rightLimit = canvas.getRightLimit();
        leftLimit = canvas.getLeftLimit();
        downLimit = canvas.getDownLimit();
<<<<<<< HEAD
        uptLimit = canvas.getUpLimit();
    }
    
    public void __(int[][] days){
=======
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
>>>>>>> master
        for(int i = 0; i < days.length; i++){
            if(days[i][0] == 1){
                placeRobot(days[i][1]);
            }
            else{
                placeStore(days[i][1],days[i][2]);
            }
        }
    }
    
<<<<<<< HEAD
    
    public void placeStore(int location, int tenges){
        Store storeX = new Store(location,tenges);
        roadStore.add(storeX);
        numStore += 1;
    }
    
=======
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
>>>>>>> master
    public void removeStore(int location){
        for(Store a: roadStore){
            if(a.getPosition() == location){
                roadStore.remove(a);
<<<<<<< HEAD
=======
                a.makeInvisible();
>>>>>>> master
            }
        }
    }
    
<<<<<<< HEAD
=======
    /**
     * Elimina un robot.
     * @param location Posición del robot
     */
>>>>>>> master
    public void removeRobot(int location){
        for(Robot a: roadRobot){
            if(a.getPosition() == location){
                roadRobot.remove(a);
<<<<<<< HEAD
=======
                a.makeInvisible();
>>>>>>> master
            }
        }
    }
    
<<<<<<< HEAD
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
=======
    /**
     * Mueve todos los robots automáticamente buscando maximizar ganancias.
     * Usa estrategia greedy: mejor relación ganancia/distancia.
     */
    public void moveRobots() {
        for (Robot robot : roadRobot) {
            boolean isBestMove = true;     
            int bestMove = findBestMove(robot);
            for (Robot r : roadRobot) {
                int newBestMove = Math.abs(findBestMove(r));
                if (newBestMove != 0 && newBestMove < bestMove){
                    isBestMove = false;
                }
            }
            if (bestMove != 0 && isBestMove == true) {
                moveRobot(robot.getPosition(), bestMove);
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
>>>>>>> master
                }
            
                robotProfitData.add(robotData);
            }
<<<<<<< HEAD
        }
    
        // Convertir ArrayList a array 2D
=======
>>>>>>> master
        int[][] result = new int[robotProfitData.size()][];
        for (int i = 0; i < robotProfitData.size(); i++) {
            result[i] = robotProfitData.get(i);
        }
<<<<<<< HEAD
    
        // Ordenar por posición (primera columna) de menor a mayor
        Arrays.sort(result, (a, b) -> Integer.compare(a[0], b[0]));
    
        return result;
    }
    
=======
        Arrays.sort(result, (a, b) -> Integer.compare(a[0], b[0]));
    
        return result;
       }
    
    /**
     * Obtiene cuántas veces cada tienda fue vaciada.
     * @return Arreglo [posición, veces_vaciada]
     */
>>>>>>> master
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
    
<<<<<<< HEAD
    public void placeRobot(int location){
        Robot robotX = new Robot(location);
        roadRobot.add(robotX);
        numRobot += 1;
    }
    
=======
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
>>>>>>> master
    public void moveRobot(int location, int meters){
        for(Robot a: roadRobot){
            if(a.getPosition() == location){
                a.advance(meters);
                for(Store c: roadStore){
<<<<<<< HEAD
                    if(c.getPosition() >= location && c.getPosition() <= location+meters){
                        a.setTenges(c.getTenges());
                        a.setProfit(c.getTenges());
                        a.addProfitToHistory(c.getTenges());
                        c.setTenges(0);
                        c.aumentNumEmptied();
                    }
                }
=======
                    if(c.getPosition() == a.getPosition()){
                        a.setTenges(c.getTenges());
                        a.setProfit(c.getTenges());
                        a.addProfitToHistory(c.getTenges());
                        c.empty();
                        c.increaseNumEmptied();
                    }
                }
                drawRobot(a);
>>>>>>> master
            }
        }
    }
    
<<<<<<< HEAD
=======
    /**
     * Restaura los tenges de todas las tiendas a su valor original.
     */
>>>>>>> master
    public void resupplyStores(){
        for(Store a:roadStore){
            a.restoreTenges();
        }   
<<<<<<< HEAD
        
    }
    
    public void returnRobots(){
        for(Robot a:roadRobot){
            a.restorePosition();
        }
    }
    
=======
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
>>>>>>> master
    public void reboot(){
        for(Store a:roadStore){
            a.restoreTenges();
        }
        ArrayList<Robot> resetRobots = new ArrayList<>();
        for(Robot a: roadRobot){
            resetRobots.add(new Robot(a.getOriginalPosition()));
        }
        roadRobot = resetRobots;
<<<<<<< HEAD
    }

    
=======
        for(Robot a: roadRobot){
            drawRobot(a);
        }
    }

    /**
     * Calcula la ganancia total de todos los robots.
     * @return Suma de ganancias
     */   
>>>>>>> master
    public int profit(){
        int total = 0;
        for(Robot a:roadRobot){
            total += a.getProfit();
        }
        return total;
    }
    
<<<<<<< HEAD
=======
    /**
     * Obtiene datos de todos los robots.
     * @return Arreglo [posición, tenges] por cada robot
     */    
>>>>>>> master
    public int[][] robots(){
        int[][] data = new int[roadRobot.size()][2]; 
        for (int i = 0; i < roadRobot.size(); i++) {
             data[i][0] = roadRobot.get(i).getPosition(); 
             data[i][1] = roadRobot.get(i).getTenges();
        }
        return data;
    }
    
<<<<<<< HEAD
=======
    /**
     * Obtiene datos de todas las tiendas.
     * @return Arreglo [posición, tenges] por cada tienda
     */
>>>>>>> master
    public int[][] store(){
        int[][] data = new int[roadStore.size()][2]; 
        for (int i = 0; i < roadStore.size(); i++) {
             data[i][0] = roadStore.get(i).getPosition(); 
             data[i][1] = roadStore.get(i).getTenges();
        }
        return data;
    }
    
<<<<<<< HEAD
=======
    /**
     * Hace visibles todos los elementos en el canvas.
     */
>>>>>>> master
    public void makeVisible(){
        for(Robot r: roadRobot){
            r.makeVisible();
        }
        
        for(Store s: roadStore){
            s.makeVisible();
        }
        
    }
    
<<<<<<< HEAD
=======
    /**
     * Hace invisibles todos los elementos en el canvas.
     */
>>>>>>> master
    public void makeInvisible(){
        for(Robot r: roadRobot){
            r.makeInvisible();
        }
        
        for(Store s: roadStore){
            s.makeInvisible();
        }        
    }
    
<<<<<<< HEAD
=======
    /**
     * Limpia y finaliza todo. Vacía colecciones.
     */
>>>>>>> master
    public void finish(){
        makeInvisible();
        roadRobot.clear();
        roadStore.clear();
    }

<<<<<<< HEAD
    
=======
    /**
     * Verifica integridad del sistema.
     * @return true si todo está bien, false si hay problemas
     */
>>>>>>> master
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