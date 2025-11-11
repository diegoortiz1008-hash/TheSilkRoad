package domain;

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
        this.days = days;
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
    
        road = new SilkRoad();
    
        for(int i = 0; i < days.length; i++){            
            if(i > 0) {
                road.reboot();
            }
            if(days[i][0] == 1){
                road.placeRobot("normal", days[i][1]);
            } else {
                road.placeStore("normal", days[i][1], days[i][2]);
            }
            road.moveRobots();
            profitXDay[i] = road.profit();
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
        road = new SilkRoad();
    
        for(int i = 0; i < days.length; i++){
            if(days[i][0] == 1){
                road.placeRobot("normal", days[i][1]);
            } else {
                road.placeStore("normal", days[i][1], days[i][2]);
            }
            
            road.moveRobots();
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
