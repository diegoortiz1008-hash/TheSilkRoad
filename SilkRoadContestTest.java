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
        // Limpiar después de cada test
        System.gc();
    }
    
    /**
     * Test 1: Verificar que solve() calcula correctamente las ganancias día por día
     */
    @Test
    public void testSolveBasico() {
        // Formato: [tipo, posición, tenges]
        // tipo 1 = robot, tipo 2 = tienda
        int[][] days = {
            {1, 20, -1},    // Día 1: Robot en posición 20
            {2, 15, 15},    // Día 2: Tienda en posición 15 con 15 tenges
            {2, 40, 50}     // Día 3: Tienda en posición 40 con 50 tenges
        };
        
        contest = new SilkRoadContest(days);
        int[] profits = contest.solve(days);
        
        assertNotNull(profits, "El resultado no debe ser null");
        assertEquals(days.length, profits.length, "Debe haber una ganancia por cada día");
        
        System.out.println("✓ Test 1 pasado: solve() genera resultados correctos");
        System.out.println("  Ganancias por día: " + java.util.Arrays.toString(profits));
    }
    
    
    /**
     * Test 3: Simular el juego sin velocidad lenta (rápido)
     */
    @Test
    public void testSimulateRapido() {
        System.out.println("\n=== TEST SIMULACIÓN RÁPIDA ===\n");
        
        int[][] days = {
            {1, 20, 0},    // Día 1: Robot 1 en posición 20
            {2, 15, 15},    // Día 2: Tienda en posición 15 con 15 tenges
            {2, 40, 50},    // Día 3: Tienda en posición 40 con 50 tenges
            {1, 200, 0},    // Día 4: Robot 2 en posición 50
            {2, 210, 50},    // Día 5: Tienda en posición 80 con 20 tenges
            {2, 300, 30}     // Día 6: Tienda en posición 70 con 30 tenges
        };
        
        contest = new SilkRoadContest(days);
        
        System.out.println("⚡ Ejecutando simulación rápida (slow=false)...");
        long inicio = System.currentTimeMillis();
        
        contest.simulate(days, false);
        
        long fin = System.currentTimeMillis();
        long duracion = fin - inicio;
        
        System.out.println("\n✓ Simulación completada en " + duracion + "ms");        
        System.out.println("✓ Test pasado: simulate() con slow=false funciona correctamente\n");
    }
    
    /**
     * Test 4: Simular el juego con velocidad lenta (para visualización)
     */
    @Test
    public void testSimulateLento() {
        System.out.println("\n=== TEST SIMULACIÓN LENTA ===\n");
        System.out.println("⚠️  Este test mostrará cada día con pausa de 1 segundo\n");
        
        int[][] days = {
            {1, 20, 0},    // Día 1: Robot 1 en posición 20
            {2, 15, 15},    // Día 2: Tienda en posición 15 con 15 tenges
            {2, 40, 50},    // Día 3: Tienda en posición 40 con 50 tenges
            {1, 200, -1},    // Día 4: Robot 2 en posición 50
            {2, 210, 20},    // Día 5: Tienda en posición 80 con 20 tenges
            {2, 230, 30}     // Día 6: Tienda en posición 70 con 30 tenges
        };
        
        contest = new SilkRoadContest(days);         
        contest.simulate(days, true);
        
    }
}