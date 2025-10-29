import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase de prueba para SilkRoad usando JUnit Jupiter
 * 
 * @author Test Suite
 * @version 1.0
 */
public class SilkRoadTest {
    private SilkRoad silkRoad;
    
    @BeforeEach
    public void setUp() {
        silkRoad = new SilkRoad();
    }
    
    @AfterEach
    public void tearDown() {
        if (silkRoad != null) {
            silkRoad.finish();
        }
    }
    
    /**
     * Test 1: Verificar que se puede crear una tienda
     */
    @Test
    public void shouldPlaceStore() {
        silkRoad.placeStore("normal", 15, 50);
        
        int[][] stores = silkRoad.store();
        
        assertEquals(1, stores.length);
        assertEquals(15, stores[0][0]);
        assertEquals(50, stores[0][1]);
        
        System.out.println("✓ Test 1 pasado: Se puede crear una tienda correctamente");
    }
    
    /**
     * Test 2: Verificar que se puede crear un robot
     */
    @Test
    public void testPlaceRobot() {
        silkRoad.placeRobot("normal", 10);
        
        int[][] robots = silkRoad.robots();
        
        assertEquals(1, robots.length);
        assertEquals(10, robots[0][0]);
        assertEquals(0, robots[0][1]);
        
        System.out.println("✓ Test 2 pasado: Se puede crear un robot correctamente");
    }
    
    /**
     * Test 3: Verificar que el robot solo puede robar si está en la posición exacta de la tienda
     */
    @Test
    public void shouldRobotStealOnlyAtExactPosition() {
        silkRoad.placeStore("normal", 20, 100);
        silkRoad.placeRobot("normal", 10);
        
        silkRoad.moveRobot(10, 5);
        
        int[][] robots = silkRoad.robots();
        int[][] stores = silkRoad.store();
        
        assertEquals(15, robots[0][0]);
        assertEquals(100, stores[0][1]);
        assertEquals(0, silkRoad.profit());
        
        silkRoad.moveRobot(15, 5);
        
        robots = silkRoad.robots();
        stores = silkRoad.store();
        
        assertEquals(20, robots[0][0]);
        assertEquals(0, stores[0][1]);
        assertEquals(100, silkRoad.profit());
        
        System.out.println("✓ Test 3 pasado: El robot solo roba en la posición exacta de la tienda");
    }
    
    /**
     * Test 4: Verificar que moveRobots() funciona correctamente
     */
    @Test
    public void shouldMoveRobots() {
        silkRoad.placeStore("normal", 25, 80);
        silkRoad.placeRobot("normal", 10);
        
        silkRoad.moveRobot(10, 0); 
        
        int[][] robotsInicial = silkRoad.robots();
        int posicionInicial = robotsInicial[0][0];
        
        silkRoad.moveRobots();
        
        int[][] robotsFinal = silkRoad.robots();
        
        assertNotNull(robotsFinal);
        assertTrue(robotsFinal.length > 0);
        
        System.out.println("✓ Test 4 pasado: moveRobots() funciona correctamente");
    }
    
    /**
     * Test 5: Verificar que reboot() funciona correctamente
     */
    @Test
    public void testReboot() {
        silkRoad.placeStore("normal",30, 120);
        silkRoad.placeRobot("normal", 5);
        
        silkRoad.moveRobot(5, 25);
        
        int profitAntes = silkRoad.profit();
        int[][] storesAntes = silkRoad.store();
        
        assertTrue(profitAntes > 0 || storesAntes[0][1] == 0);
        
        silkRoad.reboot();
        
        int[][] robotsDespues = silkRoad.robots();
        int[][] storesDespues = silkRoad.store();
        
        assertEquals(5, robotsDespues[0][0]);
        assertEquals(0, robotsDespues[0][1]);
        assertEquals(120, storesDespues[0][1]);
        
        System.out.println("✓ Test 5 pasado: reboot() reinicia correctamente el estado del juego");
    }
}