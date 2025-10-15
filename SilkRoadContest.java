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
    
    /**
     * Visualiza la simulación día a día en el canvas.
     * Crea un SilkRoad por cada día, ejecuta moveRobots() y lo muestra.
     * 
     * @param days Arreglo de eventos [tipo, posición, tenges]
     * @param slow Si es true, pausa 1 segundo entre días. Si es false, sin pausa.
     */
    public void simulate(int[][] days, boolean slow){
        if(slow){
            for(int i=0; i< days.length; i++){
                int [][] daysX = new int[i+1][3];
                for(int k=0; k < i+1; k++){
                    daysX[k]= days[k];
                }
                road = new SilkRoad(daysX);
                road.moveRobots();
                try {
                    Thread.sleep(1000); // Pausa de 1 segundo (1000 ms)
                    } catch (InterruptedException e) {
                    e.printStackTrace(); 
                }
                road.makeInvisible();
            }
            road.makeVisible();
        }
        else{
            for(int i=0; i< days.length; i++){
                int [][] daysX = new int[i+1][3];
                for(int k=0; k < i+1; k++){
                    daysX[k]= days[k];
                }
                road = new SilkRoad(daysX);
                road.moveRobots();
                road.makeInvisible();
            }
            road.makeVisible();
        }
    }
}