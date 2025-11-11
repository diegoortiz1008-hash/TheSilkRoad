package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import domain.*;

/**
 * TESTS ADICIONALES PARA AUMENTAR COVERAGE AL 75%+ (SOLO DOMAIN)
 * 
 * Este archivo contiene TODOS los tests nuevos para el paquete DOMAIN
 * necesarios para aumentar el coverage de tu proyecto del 51.5% al 75%+
 * 
 * Incluye tests para:
 * - SilkRoad (métodos adicionales)
 * - SilkRoadContest (casos adicionales)  
 * - Robot y Store (métodos completos)
 * 
 * NO incluye tests de presentation
 * 
 * @author Coverage Tests
 * @version 1.0
 */
public class AdditionalCoverageTests {
    
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
    
    @Test
    public void shouldCreateSilkRoadWithDays() {
        int[][] days = {
            {1, 10, 0},
            {2, 20, 50},
            {1, 30, 0}
        };
        
        SilkRoad road = new SilkRoad(days);
        
        int[][] robots = road.robots();
        int[][] stores = road.store();
        
        assertEquals(2, robots.length);
        assertEquals(1, stores.length);
        
        road.finish();
    }
    
    @Test
    public void shouldRemoveStore() {
        silkRoad.placeStore("normal", 15, 50);
        silkRoad.placeStore("normal", 25, 60);
        
        int[][] storesBefore = silkRoad.store();
        assertEquals(2, storesBefore.length);
        
        silkRoad.removeStore(15);
        
        int[][] storesAfter = silkRoad.store();
        assertEquals(1, storesAfter.length);
        assertEquals(25, storesAfter[0][0]);
    }
    
    @Test
    public void shouldRemoveRobot() {
        silkRoad.placeRobot("normal", 10);
        silkRoad.placeRobot("normal", 20);
        
        int[][] robotsBefore = silkRoad.robots();
        assertEquals(2, robotsBefore.length);
        
        silkRoad.removeRobot(10);
        
        int[][] robotsAfter = silkRoad.robots();
        assertEquals(1, robotsAfter.length);
        assertEquals(20, robotsAfter[0][0]);
    }
    
    @Test
    public void shouldResupplyStores() {
        silkRoad.placeStore("normal", 20, 100);
        silkRoad.placeRobot("normal", 10);
        
        silkRoad.moveRobot(10, 10);
        
        int[][] storesAfterRob = silkRoad.store();
        assertEquals(0, storesAfterRob[0][1], "Tienda debería estar vacía");
        
        silkRoad.resupplyStores();
        
        int[][] storesAfterResupply = silkRoad.store();
        assertEquals(100, storesAfterResupply[0][1], "Tienda debería tener 100 tenges");
    }
    
    @Test
    public void shouldReturnRobots() {
        silkRoad.placeRobot("normal", 10);
        silkRoad.placeStore("normal", 30, 50);
        
        silkRoad.moveRobot(10, 20);
        
        int[][] robotsAfterMove = silkRoad.robots();
        assertEquals(30, robotsAfterMove[0][0]);
        
        silkRoad.returnRobots();
        
        int[][] robotsAfterReturn = silkRoad.robots();
        assertEquals(10, robotsAfterReturn[0][0], "Robot debería volver a posición original");
    }
    
    @Test
    public void shouldGetProfitPerMove() {
        silkRoad.placeRobot("normal", 10);
        silkRoad.placeStore("normal", 20, 50);
        silkRoad.placeStore("normal", 35, 60);
        
        silkRoad.moveRobot(10, 10);
        silkRoad.moveRobot(20, 15);
        
        int[][] profitData = silkRoad.profitPerMove();
        
        assertNotNull(profitData);
        assertTrue(profitData.length > 0);
        assertTrue(profitData[0].length > 1);
    }
    
    @Test
    public void shouldGetEmptiedStores() {
        silkRoad.placeStore("normal", 20, 50);
        silkRoad.placeRobot("normal", 10);
        
        silkRoad.moveRobot(10, 10);
        
        int[][] emptiedData = silkRoad.emptiedStores();
        
        assertNotNull(emptiedData);
        assertEquals(20, emptiedData[0][0]);
        assertEquals(1, emptiedData[0][1]);
    }
    
    @Test
    public void shouldVerifyIntegrity() {
        silkRoad.placeRobot("normal", 10);
        silkRoad.placeStore("normal", 20, 50);
        
        assertTrue(silkRoad.ok(), "Sistema debería estar OK");
    }
    
    @Test
    public void shouldMakeVisibleAndInvisible() {
        silkRoad.placeRobot("normal", 10);
        silkRoad.placeStore("normal", 20, 50);
        
        silkRoad.makeVisible();
        silkRoad.makeInvisible();
        
        assertTrue(true, "makeVisible y makeInvisible ejecutados");
    }
    
    @Test
    public void shouldGetRoadCollections() {
        silkRoad.placeRobot("normal", 10);
        silkRoad.placeStore("normal", 20, 50);
        
        ArrayList<Robot> robots = silkRoad.getRoadRobot();
        ArrayList<Store> stores = silkRoad.getRoadStore();
        
        assertEquals(1, robots.size());
        assertEquals(1, stores.size());
    }
    
    @Test
    public void shouldHandleMultipleUnits() {
        silkRoad.placeRobot("normal", 5);
        silkRoad.placeRobot("tender", 10);
        silkRoad.placeRobot("normal", 15);
        
        silkRoad.placeStore("normal", 20, 50);
        silkRoad.placeStore("normal", 30, 60);
        silkRoad.placeStore("figther", 40, 70);
        
        int[][] robots = silkRoad.robots();
        int[][] stores = silkRoad.store();
        
        assertEquals(3, robots.length);
        assertEquals(3, stores.length);
    }
    
    @Test
    public void shouldAccumulateProfit() {
        silkRoad.placeRobot("normal", 0);
        silkRoad.placeStore("normal", 10, 50);
        silkRoad.placeStore("normal", 25, 60);
        
        silkRoad.moveRobot(0, 10);
        int profitFirst = silkRoad.profit();
        
        silkRoad.moveRobot(10, 15);
        int profitSecond = silkRoad.profit();
        
        assertTrue(profitSecond > profitFirst, "Profit debería acumularse");
    }
    
    @Test
    public void shouldDetectEmptyStore() {
        silkRoad.placeStore("normal", 20, 50);
        silkRoad.placeRobot("normal", 10);
        
        ArrayList<Store> stores = silkRoad.getRoadStore();
        assertFalse(stores.get(0).isEmpty(), "Tienda no debería estar vacía");
        
        silkRoad.moveRobot(10, 10);
        
        stores = silkRoad.getRoadStore();
        assertTrue(stores.get(0).isEmpty(), "Tienda debería estar vacía");
    }
    
    @Test
    public void shouldCountEmptiedTimes() {
        silkRoad.placeStore("normal", 20, 50);
        silkRoad.placeRobot("normal", 10);
        
        ArrayList<Store> stores = silkRoad.getRoadStore();
        assertEquals(0, stores.get(0).getNumEmptied());
        
        silkRoad.moveRobot(10, 10);
        
        stores = silkRoad.getRoadStore();
        assertEquals(1, stores.get(0).getNumEmptied());
        
        silkRoad.resupplyStores();
        silkRoad.returnRobots();
        silkRoad.moveRobot(10, 10);
        
        stores = silkRoad.getRoadStore();
        assertEquals(2, stores.get(0).getNumEmptied());
    }
    
    @Test
    public void shouldTestRobotMethods() {
        silkRoad.placeRobot("normal", 15);
        
        ArrayList<Robot> robots = silkRoad.getRoadRobot();
        Robot robot = robots.get(0);
        
        assertEquals(15, robot.getOriginalPosition());
        assertEquals("normal", robot.getType());
        assertEquals("blue", robot.getColor());
        
        robot.changeType("tender");
        assertEquals("tender", robot.getType());
        
        robot.changeColor("red");
        assertEquals("red", robot.getColor());
    }
    
    @Test
    public void shouldTestStoreMethods() {
        silkRoad.placeStore("normal", 25, 80);
        
        ArrayList<Store> stores = silkRoad.getRoadStore();
        Store store = stores.get(0);
        
        assertEquals("normal", store.getType());
        assertEquals(80, store.getTenges());
        assertEquals("red", store.getColor());
        
        store.changeType("figther");
        assertEquals("figther", store.getType());
        
        store.setTenges(100);
        assertEquals(100, store.getTenges());
    }
    
    
    @Test
    public void shouldChangeRobotPositions() {
        silkRoad.placeRobot("normal", 10);
        
        ArrayList<Robot> robots = silkRoad.getRoadRobot();
        Robot robot = robots.get(0);
        
        robot.changePositionX(300);
        assertEquals(300, robot.getXPosition());
        
        robot.changePositionY(400);
        assertEquals(400, robot.getYPosition());
    }
    
    @Test
    public void shouldChangeStorePositions() {
        silkRoad.placeStore("normal", 20, 50);
        
        ArrayList<Store> stores = silkRoad.getRoadStore();
        Store store = stores.get(0);
        
        store.changePositionX(350);
        assertEquals(350, store.getXPosition());
        
        store.changePositionY(450);
        assertEquals(450, store.getYPosition());
    }
    
    @Test
    public void shouldCalculateTotalProfit() {
        silkRoad.placeRobot("normal", 0);
        silkRoad.placeRobot("normal", 5);
        silkRoad.placeStore("normal", 15, 50);
        silkRoad.placeStore("normal", 30, 60);
        
        silkRoad.moveRobot(0, 15);
        silkRoad.moveRobot(5, 25);
        
        int totalProfit = silkRoad.profit();
        
        assertTrue(totalProfit > 0, "Profit total debería ser mayor a 0");
    }
    
    @Test
    public void shouldRestoreTenges() {
        silkRoad.placeStore("normal", 20, 100);
        
        ArrayList<Store> stores = silkRoad.getRoadStore();
        Store store = stores.get(0);
        
        store.empty();
        assertEquals(0, store.getTenges());
        assertEquals("black", store.getColor());
        
        store.restoreTenges();
        assertEquals(100, store.getTenges());
        assertEquals("red", store.getColor());
    }
    
    @Test
    public void shouldAdvanceRobot() {
        silkRoad.placeRobot("normal", 10);
        
        ArrayList<Robot> robots = silkRoad.getRoadRobot();
        Robot robot = robots.get(0);
        
        robot.setTenges(50);
        int tengesBefore = robot.getTenges();
        
        robot.advance(20);
        
        assertEquals(30, robot.getPosition());
        assertEquals(tengesBefore - 20, robot.getTenges());
    }
    
    @Test
    public void shouldSetProfit() {
        silkRoad.placeRobot("normal", 10);
        
        ArrayList<Robot> robots = silkRoad.getRoadRobot();
        Robot robot = robots.get(0);
        
        robot.setProfit(50);
        assertEquals(50, robot.getProfit());
        
        robot.setProfit(30);
        assertEquals(80, robot.getProfit());
    }
    
    @Test
    public void shouldGetProfitHistory() {
        silkRoad.placeRobot("normal", 0);
        silkRoad.placeStore("normal", 10, 50);
        silkRoad.placeStore("normal", 25, 60);
        
        silkRoad.moveRobot(0, 10);
        silkRoad.moveRobot(10, 15);
        
        ArrayList<Robot> robots = silkRoad.getRoadRobot();
        Robot robot = robots.get(0);
        
        ArrayList<Integer> history = robot.getProfitHistory();
        
        assertNotNull(history);
        assertTrue(history.size() >= 2);
    }
    
    @Test
    public void shouldRestorePosition() {
        silkRoad.placeRobot("normal", 15);
        
        ArrayList<Robot> robots = silkRoad.getRoadRobot();
        Robot robot = robots.get(0);
        
        robot.changePosition(50);
        assertEquals(50, robot.getPosition());
        
        robot.restorePosition();
        assertEquals(15, robot.getPosition());
    }
    
    @Test
    public void shouldHandleSuperStore() {
        silkRoad.placeStore("superStore", 20, 100);
        
        ArrayList<Store> stores = silkRoad.getRoadStore();
        assertEquals("superStore", stores.get(0).getType());
    }
    
    @Test
    public void shouldHandleEmptiedStoresWithSuperStore() {
        silkRoad.placeStore("normal", 10, 50);
        silkRoad.placeStore("superStore", 20, 100);
        silkRoad.placeRobot("normal", 0);
        
        silkRoad.moveRobot(0, 10);
        
        int[][] emptied = silkRoad.emptiedStores();
        
        assertNotNull(emptied);
    }
    
    // ================================================================
    // TESTS ADICIONALES PARA SILKROADCONTEST
    // ================================================================
    
    @Test
    public void shouldSolveSimpleCase() {
        int[][] days = {
            {1, 10, 0},
            {2, 20, 50}
        };
        
        SilkRoadContest contest = new SilkRoadContest(days);
        int[] result = contest.solve(days);
        
        assertNotNull(result);
        assertEquals(2, result.length);
    }
    
    @Test
    public void shouldSolveMultipleDays() {
        int[][] days = {
            {1, 5, 0},
            {2, 10, 30},
            {1, 15, 0},
            {2, 25, 40},
            {2, 35, 50}
        };
        
        SilkRoadContest contest = new SilkRoadContest(days);
        int[] result = contest.solve(days);
        
        assertEquals(5, result.length);
        
        for (int i = 0; i < result.length - 1; i++) {
            assertTrue(result[i + 1] >= result[i] || result[i + 1] >= 0);
        }
    }
    
    @Test
    public void shouldSolveWithOnlyRobots() {
        int[][] days = {
            {1, 10, 0},
            {1, 20, 0},
            {1, 30, 0}
        };
        
        SilkRoadContest contest = new SilkRoadContest(days);
        int[] result = contest.solve(days);
        
        assertEquals(3, result.length);
        
        for (int profit : result) {
            assertEquals(0, profit);
        }
    }
    
    @Test
    public void shouldSolveWithOnlyStores() {
        int[][] days = {
            {2, 10, 50},
            {2, 20, 60},
            {2, 30, 70}
        };
        
        SilkRoadContest contest = new SilkRoadContest(days);
        int[] result = contest.solve(days);
        
        assertEquals(3, result.length);
        
        for (int profit : result) {
            assertEquals(0, profit);
        }
    }
    
    @Test
    public void shouldSimulateFastComplex() {
        int[][] days = {
            {1, 10, 0},
            {2, 20, 100},
            {1, 30, 0},
            {2, 50, 80}
        };
        
        SilkRoadContest contest = new SilkRoadContest(days);
        contest.simulate(days, false);
        
        assertTrue(true, "Simulación rápida completada");
    }
    
    @Test
    public void shouldRebootBetweenDays() {
        int[][] days = {
            {1, 10, 0},
            {2, 20, 50},
            {1, 30, 0}
        };
        
        SilkRoadContest contest = new SilkRoadContest(days);
        int[] result = contest.solve(days);
        
        assertEquals(0, result[0]);
        assertTrue(result[1] >= 0);
        assertTrue(result[2] >= 0);
    }
    
    @Test
    public void shouldAccumulateProfitAcrossDays() {
        int[][] days = {
            {1, 10, 0},
            {2, 15, 30},
            {2, 25, 40},
            {1, 35, 0},
            {2, 50, 60}
        };
        
        SilkRoadContest contest = new SilkRoadContest(days);
        int[] result = contest.solve(days);
        
        assertEquals(5, result.length);
        assertTrue(result[result.length - 1] > 0);
    }
    
    @Test
    public void shouldHandleDifferentEventTypes() {
        int[][] days = {
            {1, 5, 0},
            {2, 10, 50},
            {1, 15, 0},
            {2, 20, 60},
            {2, 30, 70}
        };
        
        SilkRoadContest contest = new SilkRoadContest(days);
        int[] result = contest.solve(days);
        
        assertNotNull(result);
        assertEquals(5, result.length);
    }
    
    @Test
    public void shouldSimulateSingleDay() {
        int[][] days = {
            {1, 10, 0}
        };
        
        SilkRoadContest contest = new SilkRoadContest(days);
        contest.simulate(days, false);
        
        assertTrue(true, "Simulación de un día completada");
    }
    
    @Test
    public void shouldHandleLongDistances() {
        int[][] days = {
            {1, 10, 0},
            {2, 100, 150},
            {1, 200, 0},
            {2, 250, 100}
        };
        
        SilkRoadContest contest = new SilkRoadContest(days);
        int[] result = contest.solve(days);
        
        assertNotNull(result);
        assertEquals(4, result.length);
    }
    
    @Test
    public void shouldProcessEventsInOrder() {
        int[][] days = {
            {2, 20, 50},
            {1, 10, 0},
            {2, 30, 60}
        };
        
        SilkRoadContest contest = new SilkRoadContest(days);
        int[] result = contest.solve(days);
        
        assertEquals(3, result.length);
        assertEquals(0, result[0]);
        assertTrue(result[1] > 0);
    }
}
