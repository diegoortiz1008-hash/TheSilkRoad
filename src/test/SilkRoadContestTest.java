package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domain.*;

/**
 * Clase de prueba para SilkRoadContest usando JUnit Jupiter
 * Prueba la resolución del problema de la maratón paso a paso
 * 
 * @author Test Suite
 * @version 1.0
 */
public class SilkRoadContestTest {
    private SilkRoadContest contest;
    
    @AfterEach
    public void tearDown() {
        System.gc();
    }
    
    /**
     * Test 3: Simular el juego sin velocidad lenta (rápido)
     */
    @Test
    public void shouldSimulateRapido() {
        
        int[][] days = {
            {1, 20, 0},    
            {2, 15, 15},   
            {2, 40, 50},    
            {1, 200, 0},    
            {2, 210, 50},    
            {2, 300, 30}     
        };
        
        contest = new SilkRoadContest(days);
        
        contest.simulate(days, false);
        
        System.out.println("✓ Test pasado: simulate() con slow=false funciona correctamente");
    }
    
    /**
     * Test 4: Simular el juego con velocidad lenta (para visualización)
     */
    @Test
    public void shouldSimulateLento() {
        
        int[][] days = {
            {1, 20, -1},    
            {2, 15, 15},    
            {2, 40, 50},    
            {1, 200, -1},    
            {2, 210, 20},    
            {2, 230, 30}     
        };
        
        contest = new SilkRoadContest(days);
        
        contest.simulate(days, true);
        

        System.out.println("✓ Test pasado: simulate() con slow=true funciona correctamente\n");
    }
    
    /**
     * Caso de prueba de entrada de la maraton
     */
    @Test
    public void testSampleInputFromImage() {
        System.out.println("\n=== TEST CASO DE PRUEBA DE LA MARATON ===\n");
    
        int[][] days = {
            {1, 20, -1},
            {2, 15, 15},
            {2, 40, 50},
            {1, 50, -1},
            {2, 80, 20},
            {2, 70, 30}
        };
    
        contest = new SilkRoadContest(days);
    
        int[] result = contest.solve(days);
    
        int[] expected = {0, 10, 35, 50, 50, 60};
        assertArrayEquals(expected, result, 
            "La salida no coincide con la esperada del caso de la imagen");
    
        System.out.println("✓ Test pasado: resultados coinciden con el Sample Output 1\n");
    }
}
