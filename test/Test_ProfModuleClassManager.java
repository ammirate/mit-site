import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
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
//        suite.addTest(new Test_ProfModuleClassManager("TC_7_1_create"));
//        suite.addTest(new Test_ProfModuleClassManager("TC_7_2_getAllRelations"));
//        suite.addTest(new Test_ProfModuleClassManager("TC_7_3_delete"));
        suite.addTest(new Test_ProfModuleClassManager("TC_7_4_getByTeaching"));

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

    public void TC_7_3_delete() {
        System.out.print("Executing TC_7_3...");
        ProfModuleClassManager pmcm = ProfModuleClassManager.getInstance();
        ProfModuleClass pmc = new ProfModuleClass("classe1", "0222500002", "ITPM", "m.rossi@unisa.it");
        assertTrue(pmcm.delete(pmc));
        System.out.println("Done");
    }

    
    
    public void TC_7_4_getByTeaching() {
        System.out.print("Executing TC_7_4...");
        ProfModuleClassManager pmcm = ProfModuleClassManager.getInstance();
        ProfModuleClass pmc1 = new ProfModuleClass("classe1", "0222500002", "ITPM", "m.rossi@unisa.it");
        ProfModuleClass pmc2 = new ProfModuleClass("classe1", "0222500002", "ITSM", "m.rossi@unisa.it");
        pmcm.create(pmc2);
        pmcm.create(pmc1);           
        assertEquals(2, pmcm.getByTeaching("0222500002").size());
        
        pmcm.delete(pmc2);
        pmcm.delete(pmc1);
        System.out.println("Done");
    }

}
