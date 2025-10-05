import java.util.*;

/**
 * SilkRoadContest - Resuelve el problema de optimización de la maratón
 * Calcula la máxima utilidad que los robots pueden obtener cada día
 * 
 * @author DOPO Project
 * @version Ciclo 3 - 2025-2
 */
public class SilkRoadContest {
    
    private int[][] dayData;  // Información de cada día
    private int[] maxProfits; // Máxima utilidad por día
    private List<int[][]> solutions; // Soluciones (movimientos) por día
    
    /**
     * Constructor
     * @param days Array con información de cada día
     *        days[i][0] = 1 (robot) o 2 (tienda)
     *        days[i][1] = ubicación
     *        days[i][2] = tenges (solo para tiendas)
     */
    public SilkRoadContest(int[][] days) {
        this.dayData = days;
        this.solutions = new ArrayList<>();
    }
    
    /**
     * Resuelve el problema de optimización
     * Calcula la máxima utilidad diaria
     * @return Array con la máxima utilidad de cada día
     */
    public int[] solve() {
        List<Integer> profitsList = new ArrayList<>();
        
        // Procesar cada día
        List<Robot> robots = new ArrayList<>();
        List<Store> stores = new ArrayList<>();
        
        for (int[] event : dayData) {
            if (event[0] == 1) {
                // Es un robot
                robots.add(new Robot(event[1]));
            } else {
                // Es una tienda
                stores.add(new Store(event[1], event[2]));
            }
            
            // Calcular utilidad máxima para este día
            int maxProfit = calculateMaxProfit(new ArrayList<>(robots), new ArrayList<>(stores));
            profitsList.add(maxProfit);
            
            // Guardar solución
            int[][] movements = findOptimalMovements(new ArrayList<>(robots), new ArrayList<>(stores));
            solutions.add(movements);
        }
        
        // Convertir lista a array
        maxProfits = new int[profitsList.size()];
        for (int i = 0; i < profitsList.size(); i++) {
            maxProfits[i] = profitsList.get(i);
        }
        
        return maxProfits;
    }
    
    /**
     * Calcula la máxima utilidad posible dados robots y tiendas
     */
    private int calculateMaxProfit(List<Robot> robots, List<Store> stores) {
        if (robots.isEmpty() || stores.isEmpty()) {
            return 0;
        }
        
        int totalProfit = 0;
        
        // Para cada robot, encontrar la mejor tienda
        List<Store> availableStores = new ArrayList<>(stores);
        
        for (Robot robot : robots) {
            int bestProfit = 0;
            Store bestStore = null;
            
            for (Store store : availableStores) {
                if (store.isEmpty()) continue;
                
                int distance = Math.abs(store.getPosition() - robot.getPosition());
                int availableTenges = robot.getTenges();
                
                // El robot puede recoger tenges si tiene suficiente capacidad después de moverse
                if (distance <= availableTenges) {
                    int profit = Math.min(availableTenges - distance, store.getTenges());
                    if (profit > bestProfit) {
                        bestProfit = profit;
                        bestStore = store;
                    }
                }
            }
            
            if (bestStore != null) {
                totalProfit += bestProfit;
                // Marcar tienda como usada
                availableStores.remove(bestStore);
            }
        }
        
        return totalProfit;
    }
    
    /**
     * Encuentra los movimientos óptimos para maximizar la utilidad
     */
    private int[][] findOptimalMovements(List<Robot> robots, List<Store> stores) {
        List<int[]> movements = new ArrayList<>();
        List<Store> availableStores = new ArrayList<>(stores);
        
        for (Robot robot : robots) {
            int bestMove = 0;
            Store bestStore = null;
            int bestProfit = 0;
            
            for (Store store : availableStores) {
                if (store.isEmpty()) continue;
                
                int distance = Math.abs(store.getPosition() - robot.getPosition());
                int direction = store.getPosition() - robot.getPosition();
                int availableTenges = robot.getTenges();
                
                if (distance <= availableTenges) {
                    int profit = Math.min(availableTenges - distance, store.getTenges());
                    if (profit > bestProfit) {
                        bestProfit = profit;
                        bestStore = store;
                        bestMove = direction;
                    }
                }
            }
            
            if (bestStore != null && bestMove != 0) {
                movements.add(new int[]{robot.getPosition(), bestMove});
                availableStores.remove(bestStore);
            }
        }
        
        return movements.toArray(new int[0][]);
    }
    
    /**
     * Simula la solución usando SilkRoad
     */
    public void simulate() {
        SilkRoad simulation = new SilkRoad();
        
        // Configurar el día con todos los elementos
        simulation.__(dayData);
        simulation.makeVisible();
        
        // Ejecutar movimientos óptimos
        for (int day = 0; day < solutions.size(); day++) {
            System.out.println("Día " + (day + 1) + " - Utilidad máxima: " + maxProfits[day]);
            
            int[][] movements = solutions.get(day);
            for (int[] move : movements) {
                simulation.moveRobot(move[0], move[1]);
            }
            
            // Pausa para visualizar
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println("Utilidad obtenida: " + simulation.profit());
        }
        
        simulation.makeInvisible();
    }
    
    /**
     * Obtiene las utilidades máximas calculadas
     */
    public int[] getMaxProfits() {
        return maxProfits;
    }
    
    /**
     * Obtiene los movimientos óptimos por día
     */
    public List<int[][]> getSolutions() {
        return solutions;
    }
    
    /**
     * Verifica si la solución es válida
     */
    public boolean ok() {
        if (dayData == null || dayData.length == 0) {
            return false;
        }
        if (maxProfits == null) {
            return false;
        }
        return maxProfits.length > 0;
    }
    
    /**
     * Clase interna para representar robots en el cálculo
     */
    private static class Robot {
        private int position;
        private int tenges;
        
        public Robot(int position) {
            this.position = position;
            this.tenges = 0;
        }
        
        public int getPosition() {
            return position;
        }
        
        public int getTenges() {
            return tenges;
        }
        
        public void setTenges(int tenges) {
            this.tenges = tenges;
        }
    }
    
    /**
     * Clase interna para representar tiendas en el cálculo
     */
    private static class Store {
        private int position;
        private int tenges;
        
        public Store(int position, int tenges) {
            this.position = position;
            this.tenges = tenges;
        }
        
        public int getPosition() {
            return position;
        }
        
        public int getTenges() {
            return tenges;
        }
        
        public boolean isEmpty() {
            return tenges <= 0;
        }
        
        public void setTenges(int tenges) {
            this.tenges = tenges;
        }
    }
}