package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import domain.*;

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
    public void ShouldPlaceRobot() {
        silkRoad.placeRobot("normal", 10);
        
        int[][] robots = silkRoad.robots();
        
        assertEquals(1, robots.length);
        assertEquals(10, robots[0][0]);
        assertEquals(0, robots[0][1]);
        
        System.out.println("✓ Test 2 pasado: Se puede crear un robot correctamente");
    }
    
    public void shouldRobotStealOnlyAtExactPosition() {
        silkRoad.placeStore("normal", 20, 100);
        silkRoad.placeRobot("normal", 10);
        
        silkRoad.moveRobot(10, 5);
        
        ArrayList<Robot> robots = silkRoad.getRoadRobot();
        ArrayList<Store> stores = silkRoad.getRoadStore();
        
        assertEquals(15, robots.get(0).getPosition(), "Robot debería estar en posición 15");
        assertEquals(5, robots.get(0).getTenges(), "Robot debería tener 10-5=5 tenges");
        assertEquals(100, stores.get(0).getTenges(), "Tienda NO debería ser robada aún");
        assertEquals(0, robots.get(0).getProfit(), "Robot NO debería tener profit aún");
        
        silkRoad.moveRobot(15, 5);
        
        robots = silkRoad.getRoadRobot();
        stores = silkRoad.getRoadStore();
        
        assertEquals(20, robots.get(0).getPosition(), "Robot debería estar en posición 20");
        assertEquals(0, stores.get(0).getTenges(), "Tienda debería estar vacía");
        assertEquals(95, robots.get(0).getProfit(), "Robot debería ganar 100-5=95");
        assertEquals(95, silkRoad.profit(), "Profit total debería ser 95");
        
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
    public void shouldReboot() {
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
    
    
    @Test
    public void ShouldNormalRobarANormal() {
        silkRoad.placeRobot("normal", 0);
        silkRoad.placeStore("normal", 5, 100);
        silkRoad.moveRobot(0, 5);

        ArrayList <Robot> robots = silkRoad.getRoadRobot();
        assertEquals(95, robots.get(0).getProfit(), "El robot normal debería tener tenges = 100 - 5");
    }
    
    @Test
    public void ShouldNormalNoRobaAFigther() {
        silkRoad.placeRobot("normal", 0);
        silkRoad.placeStore("figther", 5, 200);
        silkRoad.moveRobot(0, 5);

        ArrayList <Robot> robots = silkRoad.getRoadRobot();
        assertEquals(0, robots.get(0).getProfit(), 
            "El robot normal NO debería poder robar a una tienda figther con más tenges");
    }

    @Test
    public void ShouldNormalRobaASuperStore() {
        silkRoad.placeRobot("normal", 0);
        silkRoad.placeStore("superStore", 10, 200);
        silkRoad.moveRobot(0, 10);

        ArrayList <Robot> robots = silkRoad.getRoadRobot();
        assertEquals(190, robots.get(0).getProfit(), "El robot normal debería ganar tenges - metros al robar superStore");
    }

    @Test
    public void ShouldTenderRobaANormal() {
        silkRoad.placeRobot("tender", 0);
        silkRoad.placeStore("normal", 10, 100);
        silkRoad.moveRobot(0, 10);

        ArrayList <Robot> robots = silkRoad.getRoadRobot();
        assertEquals(40, robots.get(0).getProfit(), "El robot tender debería ganar la mitad de los tenges menos la distancia");
    }
    
    @Test
    public void ShouldTenderNoRobaAFigther() {
        silkRoad.placeRobot("tender", 0);
        silkRoad.placeStore("figther", 5, 80);
        silkRoad.moveRobot(0, 5);

        ArrayList <Robot> robots = silkRoad.getRoadRobot();
        assertEquals(0, robots.get(0).getProfit(),
            "El robot tender NO debería poder robar a una tienda figther con más tenges");
    }


    @Test
    public void shouldTenderRobaASuperStore() {
        silkRoad.placeRobot("tender", 0);
        silkRoad.placeStore("superStore", 10, 100);
        silkRoad.moveRobot(0, 10);

        ArrayList <Robot> robots = silkRoad.getRoadRobot();
        assertEquals(40, robots.get(0).getProfit(), "El tender debería ganar la mitad de los tenges menos la distancia con superStore");
    }

}
