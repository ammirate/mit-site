import it.unisa.offerta_formativa.manager.CycleManager;

import org.junit.Test;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import it.unisa.offerta_formativa.beans.Cycle;
import it.unisa.offerta_formativa.beans.Teaching;
import java.util.List;

/**
 *
 * @author Alessandro, Antonio
 *
 */
public class Test_CycleManager extends TestCase {

    public Test_CycleManager(String s) {
        super(s);
    }

    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new Test_CycleManager("TC_8_1_getInstance"));
        return suite;
    }

    @Test
    public void test() {
        suite();
    }

    
    public void TC_8_1_getInstance() {
        System.out.print("Executing TC_8_1...");
        //check if instantiation is correct
        CycleManager cycleManager = CycleManager.getInstance();
        assertNotNull(cycleManager);

        //check if singleton pattern works properly
        CycleManager cycleManager2 = CycleManager.getInstance();
        assertEquals(cycleManager, cycleManager2);
        System.out.println("Done");
    }

    /**
     * insert a Cycle into the DB
     */
    public void TC_8_2_createCycle() {
        System.out.print("Executing TC_8_2...");
        CycleManager cycleManager = CycleManager.getInstance();
        assertTrue(cycleManager.createCycle(new Cycle(10,"decimo ciclo")));
        System.out.println("Done");
    }

    /**
     * read a Cycle from the DB
     */
    public void TC_8_3_readCycle() {
        System.out.print("Executing TC_8_3....");
        CycleManager cycleManager = CycleManager.getInstance();
        Cycle test = new Cycle(10,"decimo ciclo");
        Cycle m = cycleManager.readCycle(10);
        assertTrue(test.equals(m));
        System.out.println("Done");
    }

    /**
     * update a Cycle into the db
     */
    public void TC_8_4_UpdateCycle() {
        System.out.print("Executing TC_8_4....");
        CycleManager cycleManager = CycleManager.getInstance();
        Cycle newCycle = new Cycle(10,"ciclo decimo");
        assertTrue(cycleManager.updateCycle(10, newCycle));
        System.out.println("Done");
    }

    /**
     * Delete a Cycle from the DB
     */
    public void TC_8_5_DeleteCycle() {
        System.out.print("Executing TC_8_5....");
        CycleManager cycleManager = CycleManager.getInstance();
        assertTrue(cycleManager.deleteCycle(10));
        System.out.println("Done");
    }


}
