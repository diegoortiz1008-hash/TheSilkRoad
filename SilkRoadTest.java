import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SilkRoadTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SilkRoadTest
{
    private SilkRoad silkRoad;
    
    /**
     * Default constructor for test class SilkRoadTest
     */
    public SilkRoadTest()
    {
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        silkRoad = new SilkRoad();
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        if (silkRoad != null) {
            silkRoad.finish();
            silkRoad = null;
        }
    }
    
    /**
     * Test constructor and initial state
     */
    @Test
    public void testConstructorAndInitialState()
    {
        assertNotNull(silkRoad, "SilkRoad should be created");
        assertNotNull(silkRoad.robots(), "Robots array should exist");
        assertNotNull(silkRoad.store(), "Store array should exist");
        assertEquals(0, silkRoad.robots().length, "No robots initially");
        assertEquals(0, silkRoad.store().length, "No stores initially");
        assertEquals(0, silkRoad.profit(), "Initial profit should be 0");
    }
    
    /**
     * Test placing stores
     */
    @Test
    public void testPlaceStores()
    {
        silkRoad.placeStore(1, 100);
        silkRoad.placeStore(3, 50);
        
        int[][] stores = silkRoad.store();
        assertEquals(2, stores.length, "Should have 2 stores");
        
        boolean found100 = false, found50 = false;
        for (int[] store : stores) {
            if (store[1] == 100) found100 = true;
            if (store[1] == 50) found50 = true;
        }
        assertTrue(found100, "Store with 100 tenges should exist");
        assertTrue(found50, "Store with 50 tenges should exist");
    }
    
    /**
     * Test placing robots
     */
    @Test
    public void testPlaceRobots()
    {
        silkRoad.placeRobot(0);
        silkRoad.placeRobot(2);
        
        int[][] robots = silkRoad.robots();
        assertEquals(2, robots.length, "Should have 2 robots");
        
        for (int[] robot : robots) {
            assertEquals(0, robot[1], "Robot should start with 0 tenges");
        }
    }
    
    /**
     * Test manual robot movement
     */
    @Test
    public void testMoveRobotManually()
    {
        silkRoad.placeRobot(0);
        silkRoad.placeStore(2, 100);
        
        assertDoesNotThrow(() -> {
            silkRoad.moveRobot(0, 2);
        }, "Manual robot movement should not throw exception");
        
        assertTrue(silkRoad.profit() >= 0, "Profit should be valid after movement");
    }
    
    /**
     * Test empty stores detection
     */
    @Test
    public void testEmptyStores()
    {
        silkRoad.placeStore(1, 0);   // Empty store
        silkRoad.placeStore(2, 100); // Store with tenges
        silkRoad.placeStore(3, 0);   // Another empty store
        
        int[][] emptyInfo = silkRoad.emptiedStores();
        assertEquals(3, emptyInfo.length, "Should have info for all 3 stores");
        
        // Check that we get position and emptied count for each store
        for (int[] storeInfo : emptyInfo) {
            assertTrue(storeInfo.length >= 2, "Store info should have position and emptied count");
        }
    }
    
    /**
     * Test system OK status
     */
    @Test
    public void testSystemOK()
    {
        assertTrue(silkRoad.ok(), "System should be OK initially");
        
        silkRoad.placeStore(1, 100);
        silkRoad.placeRobot(0);
        
        assertTrue(silkRoad.ok(), "System should still be OK with elements");
    }
    
    /**
     * Test visibility functions
     */
    @Test
    public void testVisibility()
    {
        silkRoad.placeStore(1, 100);
        silkRoad.placeRobot(0);
        
        assertDoesNotThrow(() -> {
            silkRoad.makeVisible();
        }, "makeVisible should not throw exception");
        
        assertDoesNotThrow(() -> {
            silkRoad.makeInvisible();
        }, "makeInvisible should not throw exception");
    }
    
    /**
     * Test resupply stores
     */
    @Test
    public void testResupplyStores()
    {
        silkRoad.placeStore(1, 100);
        silkRoad.placeStore(2, 50);
        
        int beforeCount = silkRoad.store().length;
        
        assertDoesNotThrow(() -> {
            silkRoad.resupplyStores();
        }, "resupplyStores should not throw exception");
        
        assertEquals(beforeCount, silkRoad.store().length, "Store count should remain same");
    }
    
    /**
     * Test return robots
     */
    @Test
    public void testReturnRobots()
    {
        silkRoad.placeRobot(0);
        silkRoad.placeRobot(5);
        
        silkRoad.moveRobot(0, 2);
        
        assertDoesNotThrow(() -> {
            silkRoad.returnRobots();
        }, "returnRobots should not throw exception");
    }
    
    /**
     * Test system reboot
     */
    @Test
    public void testReboot()
    {
        silkRoad.placeStore(1, 100);
        silkRoad.placeRobot(0);
        
        int robotsBefore = silkRoad.robots().length;
        int storesBefore = silkRoad.store().length;
        
        assertDoesNotThrow(() -> {
            silkRoad.reboot();
        }, "reboot should not throw exception");
        
        assertEquals(robotsBefore, silkRoad.robots().length, "Robot count should be maintained");
        assertEquals(storesBefore, silkRoad.store().length, "Store count should be maintained");
    }
    
    /**
     * Test finish functionality
     */
    @Test
    public void testFinish()
    {
        silkRoad.placeStore(1, 100);
        silkRoad.placeRobot(0);
        
        assertTrue(silkRoad.store().length > 0, "Should have stores before finish");
        assertTrue(silkRoad.robots().length > 0, "Should have robots before finish");
        
        silkRoad.finish();
        
        assertEquals(0, silkRoad.store().length, "Should have no stores after finish");
        assertEquals(0, silkRoad.robots().length, "Should have no robots after finish");
    }
    
    /**
     * Test automatic robot movement
     */
    @Test
    public void testMoveRobotsAutomatic()
    {
        silkRoad.placeStore(3, 100);
        silkRoad.placeRobot(0);
        
        assertDoesNotThrow(() -> {
            silkRoad.moveRobots();
        }, "Automatic robot movement should not throw exception");
    }
    
    /**
     * Test profit per move functionality
     */
    @Test
    public void testProfitPerMove()
    {
        silkRoad.placeRobot(0);
        silkRoad.placeRobot(2);
        
        assertDoesNotThrow(() -> {
            int[][] profitHistory = silkRoad.profitPerMove();
            assertNotNull(profitHistory, "Profit history should not be null");
        }, "profitPerMove should not throw exception");
    }
    
    /**
     * Integration test - complete scenario
     */
    @Test
    public void testCompleteScenario()
    {
        // Setup complete scenario
        silkRoad.placeStore(2, 100);
        silkRoad.placeStore(5, 75);
        silkRoad.placeRobot(0);
        silkRoad.placeRobot(3);
        
        // Make visible
        silkRoad.makeVisible();
        
        // Execute operations
        silkRoad.moveRobot(0, 1);
        silkRoad.moveRobots();
        
        // Verify system state
        assertTrue(silkRoad.ok(), "System should be OK");
        assertTrue(silkRoad.profit() >= 0, "Profit should be valid");
        
        // Get information
        assertNotNull(silkRoad.profitPerMove(), "Profit history should be available");
        assertNotNull(silkRoad.emptiedStores(), "Empty stores info should be available");
        
        // Clean up
        silkRoad.makeInvisible();
    }
}