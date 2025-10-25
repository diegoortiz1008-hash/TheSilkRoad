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
    public void testPlaceStore() {
        silkRoad.placeStore(15, 50);
        
        int[][] stores = silkRoad.store();
        
        assertEquals(1, stores.length, "Debe haber exactamente 1 tienda");
        assertEquals(15, stores[0][0], "La posición de la tienda debe ser 15");
        assertEquals(50, stores[0][1], "Los tenges de la tienda deben ser 50");
        
        System.out.println("✓ Test 1 pasado: Se puede crear una tienda correctamente");
    }
    
    /**
     * Test 2: Verificar que se puede crear un robot
     */
    @Test
    public void testPlaceRobot() {
        silkRoad.placeRobot(10);
        
        int[][] robots = silkRoad.robots();
        
        assertEquals(1, robots.length, "Debe haber exactamente 1 robot");
        assertEquals(10, robots[0][0], "La posición del robot debe ser 10");
        assertEquals(0, robots[0][1], "Los tenges iniciales del robot deben ser 0");
        
        System.out.println("✓ Test 2 pasado: Se puede crear un robot correctamente");
    }
    
    /**
     * Test 3: Verificar que el robot solo puede robar si está en la posición exacta de la tienda
     */
    @Test
    public void testRobotStealOnlyAtExactPosition() {
        silkRoad.placeStore(20, 100);
        silkRoad.placeRobot(10);
        
        // Mover el robot pero no a la posición exacta de la tienda
        silkRoad.moveRobot(10, 5);
        
        int[][] robots = silkRoad.robots();
        int[][] stores = silkRoad.store();
        
        assertEquals(15, robots[0][0], "El robot debe estar en posición 15");
        assertEquals(100, stores[0][1], "La tienda debe mantener sus 100 tenges");
        assertEquals(0, silkRoad.profit(), "No debe haber ganancia sin llegar a la tienda");
        
        // Ahora mover el robot exactamente a la posición de la tienda
        silkRoad.moveRobot(15, 5);
        
        robots = silkRoad.robots();
        stores = silkRoad.store();
        
        assertEquals(20, robots[0][0], "El robot debe estar en posición 20");
        assertEquals(0, stores[0][1], "La tienda debe estar vacía");
        assertEquals(100, silkRoad.profit(), "Debe haber ganancia de 100 tenges");
        
        System.out.println("✓ Test 3 pasado: El robot solo roba en la posición exacta de la tienda");
    }
    
    /**
     * Test 4: Verificar que moveRobots() funciona correctamente
     */
    @Test
    public void testMoveRobots() {
        silkRoad.placeStore(25, 80);
        silkRoad.placeRobot(10);
        
        // Dar suficientes tenges al robot para que pueda moverse
        silkRoad.moveRobot(10, 0); // Esto debería darle tenges si hay una tienda en 10
        
        int[][] robotsInicial = silkRoad.robots();
        int posicionInicial = robotsInicial[0][0];
        
        silkRoad.moveRobots();
        
        int[][] robotsFinal = silkRoad.robots();
        
        // Verificar que moveRobots se ejecuta sin errores
        assertNotNull(robotsFinal, "Los robots deben existir después de moveRobots()");
        assertTrue(robotsFinal.length > 0, "Debe haber al menos un robot");
        
        System.out.println("✓ Test 4 pasado: moveRobots() funciona correctamente");
    }
    
    /**
     * Test 5: Verificar que reboot() funciona correctamente
     */
    @Test
    public void testReboot() {
        silkRoad.placeStore(30, 120);
        silkRoad.placeRobot(5);
        
        // Mover el robot y hacer que robe
        silkRoad.moveRobot(5, 25);
        
        int profitAntes = silkRoad.profit();
        int[][] storesAntes = silkRoad.store();
        
        assertTrue(profitAntes > 0 || storesAntes[0][1] == 0, "Debe haber actividad antes del reboot");
        
        // Ejecutar reboot
        silkRoad.reboot();
        
        int[][] robotsDespues = silkRoad.robots();
        int[][] storesDespues = silkRoad.store();
        
        assertEquals(5, robotsDespues[0][0], "El robot debe volver a su posición original");
        assertEquals(0, robotsDespues[0][1], "Los tenges del robot deben reiniciarse a 0");
        assertEquals(120, storesDespues[0][1], "La tienda debe restaurar sus tenges originales");
        
        System.out.println("✓ Test 5 pasado: reboot() reinicia correctamente el estado del juego");
    }
    
    /**
     * Test 6: Mini simulador simplificado - Un robot jugando partida completa
     * Los robots PUEDEN endeudarse temporalmente
    */
    @Test
    public void testMiniSimulator() {
    System.out.println("=== MINI SIMULADOR SIMPLIFICADO ===");
    
    // Configuración inicial - 1 robot, 2 tiendas
    silkRoad.placeStore(20, 60);
    silkRoad.placeStore(45, 80);
    silkRoad.placeRobot(5);
    
    System.out.println("Estado inicial:");
    printGameState(silkRoad);
    
    // Simulación automática con moveRobots()
    int maxTurnos = 8;
    System.out.println("\nINICIO SIMULACIÓN:");
    
    for (int turno = 1; turno <= maxTurnos; turno++) {
        System.out.println("\n--- TURNO " + turno + " ---");
        
        int[][] robotsAntes = silkRoad.robots();
        silkRoad.moveRobots();
        int[][] robotsDespues = silkRoad.robots();
        
        // Mostrar movimiento del robot
        if (robotsAntes[0][0] != robotsDespues[0][0]) {
            System.out.println("Robot movió: " + robotsAntes[0][0] + " → " + robotsDespues[0][0]);
            System.out.println("Tenges: " + robotsAntes[0][1] + " → " + robotsDespues[0][1]);
        } else {
            System.out.println("Robot sin movimiento");
        }
        
        printGameState(silkRoad);
        
        // Terminar si no hay movimiento
        if (robotsAntes[0][0] == robotsDespues[0][0]) {
            System.out.println("FIN - Sin movimientos posibles");
            break;
        }
    }
    
    System.out.println("\nRESUMEN FINAL:");
    System.out.println("Ganancia total: " + silkRoad.profit() + " tenges");
    
    assertTrue(silkRoad.profit() >= 0, "La ganancia debe ser no negativa");
    System.out.println("✅ Test 6 completado");
    }
     
    /**
     * Método auxiliar para hacer pausas y ver mejor la simulación
     */
    private void esperarSegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Método auxiliar para imprimir el estado del juego
     */
    private void printGameState(SilkRoad road) {
        int[][] robots = road.robots();
        int[][] stores = road.store();
        
        System.out.println("Robots:");
        for (int i = 0; i < robots.length; i++) {
            System.out.println("  - Posición: " + robots[i][0] + ", Tenges: " + robots[i][1]);
        }
        
        System.out.println("Tiendas:");
        for (int i = 0; i < stores.length; i++) {
            System.out.println("  - Posición: " + stores[i][0] + ", Tenges: " + stores[i][1]);
        }
        
        System.out.println("Ganancia total: " + road.profit() + " tenges");
    }
}