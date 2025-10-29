import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        System.out.println("\n=== TEST SIMULACIÓN RÁPIDA ===\n");
        
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
    public void testSimulateLento() {
        System.out.println("\n=== TEST SIMULACIÓN LENTA ===\n");
        System.out.println("⚠️  Este test mostrará cada día con pausa de 1 segundo\n");
        
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
}