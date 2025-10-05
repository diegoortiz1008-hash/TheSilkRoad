import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * SilkRoadContestTest - Pruebas unitarias para SilkRoadContest
 * 
 * @author DOPO Project
 * @version Ciclo 3 - 2025-2
 */
public class SilkRoadContestTest {
    
    private SilkRoadContest contest;
    
    @BeforeEach
    public void setUp() {
        // Se ejecuta antes de cada prueba
    }
    
    @AfterEach
    public void tearDown() {
        contest = null;
    }
    
    /**
     * Prueba el constructor básico
     */
    @Test
    public void testConstructor() {
        int[][] days = {{1, 0}};  // Un robot en posición 0
        contest = new SilkRoadContest(days);
        assertNotNull(contest, "Contest debería ser creado");
    }
    
    /**
     * Prueba con un escenario simple: 1 robot, 1 tienda
     */
    @Test
    public void testSimpleScenario() {
        int[][] days = {
            {1, 0},      // Día 1: Robot en posición 0
            {2, 2, 100}  // Día 2: Tienda en posición 2 con 100 tenges
        };
        
        contest = new SilkRoadContest(days);
        int[] profits = contest.solve();
        
        assertNotNull(profits, "Profits no debería ser null");
        assertEquals(2, profits.length, "Debería haber 2 días");
        assertEquals(0, profits[0], "Día 1: sin tiendas, profit = 0");
        assertTrue(profits[1] >= 0, "Día 2: profit debería ser >= 0");
    }
    
    /**
     * Prueba con múltiples robots y tiendas
     */
    @Test
    public void testMultipleRobotsAndStores() {
        int[][] days = {
            {1, 0},       // Robot en posición 0
            {1, 5},       // Robot en posición 5
            {2, 2, 50},   // Tienda en posición 2 con 50 tenges
            {2, 7, 75}    // Tienda en posición 7 con 75 tenges
        };
        
        contest = new SilkRoadContest(days);
        int[] profits = contest.solve();
        
        assertNotNull(profits);
        assertEquals(4, profits.length);
        assertTrue(profits[3] >= 0, "El último día debería tener profit válido");
    }
    
    /**
     * Prueba cuando no hay robots
     */
    @Test
    public void testNoRobots() {
        int[][] days = {
            {2, 1, 100}  // Solo tienda
        };
        
        contest = new SilkRoadContest(days);
        int[] profits = contest.solve();
        
        assertNotNull(profits);
        assertEquals(0, profits[0], "Sin robots, profit = 0");
    }
    
    /**
     * Prueba cuando no hay tiendas
     */
    @Test
    public void testNoStores() {
        int[][] days = {
            {1, 0},  // Solo robot
            {1, 5}
        };
        
        contest = new SilkRoadContest(days);
        int[] profits = contest.solve();
        
        assertNotNull(profits);
        assertEquals(2, profits.length);
        assertTrue(profits[0] == 0 && profits[1] == 0, "Sin tiendas, profits = 0");
    }
    
    /**
     * Prueba el método ok()
     */
    @Test
    public void testOk() {
        int[][] days = {{1, 0}};
        contest = new SilkRoadContest(days);
        
        assertFalse(contest.ok(), "ok() debería ser false antes de solve()");
        
        contest.solve();
        assertTrue(contest.ok(), "ok() debería ser true después de solve()");
    }
    
    /**
     * Prueba getMaxProfits()
     */
    @Test
    public void testGetMaxProfits() {
        int[][] days = {
            {1, 0},
            {2, 2, 100}
        };
        
        contest = new SilkRoadContest(days);
        contest.solve();
        
        int[] maxProfits = contest.getMaxProfits();
        assertNotNull(maxProfits);
        assertEquals(2, maxProfits.length);
    }
    
    /**
     * Prueba getSolutions()
     */
    @Test
    public void testGetSolutions() {
        int[][] days = {
            {1, 0},
            {2, 2, 100}
        };
        
        contest = new SilkRoadContest(days);
        contest.solve();
        
        assertNotNull(contest.getSolutions());
        assertTrue(contest.getSolutions().size() > 0);
    }
    
    /**
     * Prueba escenario complejo
     */
    @Test
    public void testComplexScenario() {
        int[][] days = {
            {1, 0},       // Robot 1
            {2, 3, 100},  // Tienda 1
            {1, 10},      // Robot 2
            {2, 8, 50},   // Tienda 2
            {2, 15, 200}  // Tienda 3
        };
        
        contest = new SilkRoadContest(days);
        int[] profits = contest.solve();
        
        assertNotNull(profits);
        assertEquals(5, profits.length);
        
        // Verificar que los profits son no negativos
        for (int profit : profits) {
            assertTrue(profit >= 0, "Todos los profits deben ser >= 0");
        }
    }
    
    /**
     * Prueba tiendas vacías
     */
    @Test
    public void testEmptyStores() {
        int[][] days = {
            {1, 0},
            {2, 2, 0}  // Tienda vacía
        };
        
        contest = new SilkRoadContest(days);
        int[] profits = contest.solve();
        
        assertEquals(0, profits[1], "Tienda vacía no genera profit");
    }
    
    /**
     * Prueba distancias largas
     */
    @Test
    public void testLongDistances() {
        int[][] days = {
            {1, 0},
            {2, 1000, 100}  // Tienda muy lejos
        };
        
        contest = new SilkRoadContest(days);
        int[] profits = contest.solve();
        
        assertNotNull(profits);
        // Si el robot no tiene suficientes tenges, no puede alcanzar la tienda
        assertTrue(profits[1] >= 0);
    }
}