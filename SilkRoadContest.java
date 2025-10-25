import java.util.*;

/**
 * SilkRoadContest - Resuelve el problema de optimización de la maratón
 * Calcula la máxima utilidad que los robots pueden obtener cada día
 * 
 * @author DOPO Project
 * @version Ciclo 3 - 2025-2
 */
public class SilkRoadContest {
    private SilkRoad road;
    private int[][] days;
    
    /**
     * Constructor que recibe los días a simular.
     * @param days Arreglo de eventos [tipo, posición, tenges]
     */
    public SilkRoadContest(int[][] days){
    }
    
    /**
     * Calcula la ganancia acumulada día a día.
     * Por cada día crea un SilkRoad con los eventos hasta ese día,
     * ejecuta moveRobots() y registra la ganancia total.
     * 
     * @param days Arreglo de eventos [tipo, posición, tenges]
     * @return Arreglo con la ganancia acumulada en cada día
     */
    public int[] solve(int[][] days){
        int[] profitXDay = new int[days.length];
        
        for(int i=0; i< days.length; i++){
            //crear un arreglo nuevo para cada dia en especifico i = dia
            int [][] daysX = new int[i+1][3];
            for(int k=0; k < i+1; k++){
                //añadir cada entrada, segun la cantidad de dias
                daysX[k]= days[k];
            }
            road = new SilkRoad(daysX);
            road.moveRobots();
            int profit = road.profit();
            profitXDay[i] = profit;
        }
        
        return profitXDay;
    }
    
    public void simulate(int[][] days, boolean slow){
        // Crear UN SOLO SilkRoad
        road = new SilkRoad();
    
        for(int i=0; i< days.length; i++){
            // Agregar evento del día
            if(days[i][0] == 1){
                road.placeRobot(days[i][1]);
            } else {
                road.placeStore(days[i][1], days[i][2]);
            }
        
            // Mover robots múltiples veces
            int profitAntes = road.profit();
            road.moveRobots();
            int profitDespues = road.profit();
        
            road.makeVisible();
        
            if(slow){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace(); 
                }
            }
        }
    }   
}