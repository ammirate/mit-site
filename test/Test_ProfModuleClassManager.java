import it.unisa.offerta_formativa.manager.ClassManager;

import org.junit.Test;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import it.unisa.offerta_formativa.beans.ClassPartition;
import it.unisa.offerta_formativa.beans.ProfModuleClass;
import it.unisa.offerta_formativa.manager.ProfModuleClassManager;

/**
 *
 * @author Alessandro, Antonio
 *
 */
public class Test_ProfModuleClassManager extends TestCase {

    public Test_ProfModuleClassManager(String s) {
        super(s);
    }

    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new Test_ProfModuleClassManager("TC_7_1_create"));
        return suite;
    }

    @Test
    public void test() {
        suite();
    }

    /**
     * insert a class into the DB
     */
    public void TC_7_1_create() {
        System.out.print("Executing TC_7_1...");
        ProfModuleClassManager pmcm = ProfModuleClassManager.getInstance();
        ProfModuleClass pmc = new ProfModuleClass("classe1", "0222500002", "ITPM", "m.rossi@unisa.it");
        assertTrue(pmcm.create(pmc));
        System.out.println("Done");
    }

    /**
     *
     */
    public void TC_7_2_getAllRelations() {
        System.out.print("Executing TC_7_2...");
        ProfModuleClassManager pmcm = ProfModuleClassManager.getInstance();
        assertEquals(1, pmcm.getAllRelations().size());
        System.out.println("Done");

    }
}
