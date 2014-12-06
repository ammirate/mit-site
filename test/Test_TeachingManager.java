import java.util.ArrayList;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import it.unisa.offerta_formativa.manager.TeachingManager;
import org.junit.Test;
import it.unisa.offerta_formativa.beans.Teaching;

/**
 *
 * @author Antonio
 *
 */
public class Test_TeachingManager extends TestCase {

    TeachingManager teachingManager;

    public Test_TeachingManager(String s) {
        super(s);
    }

    @Test
    public void test() {
        suite();
    }

    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
//        suite.addTest(new Test_TeachingManager("TC_3_1_getInstance"));
//        suite.addTest(new Test_TeachingManager("TC_3_2_createTeaching"));
//        suite.addTest(new Test_TeachingManager("TC_3_3_updateTeaching"));
//        suite.addTest(new Test_TeachingManager("TC_3_4_readTeaching"));
//        suite.addTest(new Test_TeachingManager("TC_3_5_deleteTeaching"));
        suite.addTest(new Test_TeachingManager("TC_3_6_getAllTeachings"));
        suite.addTest(new Test_TeachingManager("TC_3_7_getTeachingsByYear"));
        suite.addTest(new Test_TeachingManager("TC_3_8_getTeachingsBySemester"));
//        suite.addTest(new Test_TeachingManager("TC_3_9_areEqual"));

        return suite;
    }

    /**
     * TestCase 3.1 that test the connection to the DB
     */
    public void TC_3_1_getInstance() {
        System.out.print("Executing Test 3.1...");
        //check if instantiation is correct
        TeachingManager teachingManager = TeachingManager.getInstance();
        assertNotNull(teachingManager);

        //check if singleton pattern works properly
        TeachingManager b2_db = TeachingManager.getInstance();
        assertEquals("", teachingManager, b2_db);
        System.out.println("Done");
    }

    /**
     * TestCase 3.2 that test the creation of a new Teaching in the DB
     */
    public void TC_3_2_createTeaching() {
        System.out.print("Executing Test 3.2...");
        teachingManager = TeachingManager.getInstance();
        Teaching teaching = new Teaching("Management & Control System", "MCS", "0222500100", "www.mcsmdistra.unisa.it", 1, 1, true);
        assertTrue(teachingManager.createTeaching(teaching));
        System.out.println("Done");
    }

    /**
     * TestCase 3.3 that test the update of a Teaching in the DB
     */
    public void TC_3_3_updateTeaching() {
        System.out.print("Executing Test 3.3...");
        teachingManager = TeachingManager.getInstance();
        Teaching teaching = new Teaching("Management & Control System", "MCS", "0222500100", "www.mcsmdistra.unisa.it", 1, 1, true);
        assertTrue(teachingManager.updateTeaching(teaching));
        System.out.println("Done");
    }

    /**
     * TestCase 3.4 that test the read operation of a Teaching from the DB
     */
    public void TC_3_4_readTeaching() {
        System.out.print("Executing Test 3.4...");
        teachingManager = TeachingManager.getInstance();
        Teaching teaching = new Teaching("Management & Control System", "MCS", "0222500100", "www.mcsmdistra.unisa.it", 1, 1, true);
        Teaching t2 = teachingManager.readTeaching("0222500100");
        assertTrue(teaching.equal(t2));
        System.out.println("Done");
    }

    
    /**
     * TestCase 3.5 that test the deletion of a Teaching from the DB
     */
    public void TC_3_5_deleteTeaching() {
        System.out.print("Executing Test 3.5...");
        teachingManager = TeachingManager.getInstance();
        assertTrue(teachingManager.deleteTeaching("0222500100"));
        System.out.println("Done");
    }

    public void TC_3_6_getAllTeachings() {
        System.out.print("Executing Test 3.6...");
        teachingManager = TeachingManager.getInstance();
        ArrayList<Teaching> results = teachingManager.getAllTeachings();
        assertEquals(1, results.size());
        //there is already a teaching in the DB, testing version, so they are 3

        System.out.println("Done");
    }

    
    /**
     * 
     */
    public void TC_3_7_getTeachingsByYear() {
        System.out.print("Executing Test 3.7...");
        teachingManager = TeachingManager.getInstance();
//        Teaching t1 = new Teaching("IT Project and Service Management", "itpsm", "0222500010", "itpsm.it", 1, 1, true);
//        Teaching t2 = new Teaching("IManagement & Control systems", "mcs", "0222500011", "mcd.it", 1, 1, true);
//        Teaching t3 = new Teaching("Data Management", "dm", "0222500012", "itpsm.it", 1, 2, true);
//        Teaching t4 = new Teaching("Ing. Softw. : metriche e qualità", "mq", "0222500013", "mq.it", 2, 1, true);
//
//        teachingManager.createTeaching(t1);
//        teachingManager.createTeaching(t2);
//        teachingManager.createTeaching(t3);
//        teachingManager.createTeaching(t4);

        ArrayList<Teaching> results = teachingManager.getTeachingsByYear(1);

//        teachingManager.deleteTeaching(t1.getMatricula());
//        teachingManager.deleteTeaching(t2.getMatricula());
//        teachingManager.deleteTeaching(t3.getMatricula());
//        teachingManager.deleteTeaching(t4.getMatricula());
        assertEquals(1, results.size());
        System.out.println("Done");
    }

    
    /**
     * 
     */
    public void TC_3_8_getTeachingsBySemester() {
        System.out.print("Executing Test 3.8...");
        teachingManager = TeachingManager.getInstance();
//        Teaching t1 = new Teaching("IT Project and Service Management", "itpsm", "0222500020", "itpsm.it", 1, 1, true);
//        Teaching t2 = new Teaching("IManagement & Control systems", "mcs", "0222500021", "mcd.it", 1, 1, true);
//        Teaching t3 = new Teaching("Data Management", "dm", "0222500022", "itpsm.it", 1, 2, true);
//        Teaching t4 = new Teaching("Ing. Softw. : metriche e qualità", "mq", "0222500023", "mq.it", 2, 2, true);
//
//        teachingManager.createTeaching(t1);
//        teachingManager.createTeaching(t2);
//        teachingManager.createTeaching(t3);
//        teachingManager.createTeaching(t4);

        ArrayList<Teaching> results = teachingManager.getTeachingsBySemester(1);

//        teachingManager.deleteTeaching(t1.getMatricula());
//        teachingManager.deleteTeaching(t2.getMatricula());
//        teachingManager.deleteTeaching(t3.getMatricula());
//        teachingManager.deleteTeaching(t4.getMatricula());

        assertEquals(1, results.size());
        System.out.println("Done");
    }

    public void TC_3_9_areEqual() {
        Teaching t1 = new Teaching("IT Project and Service Management", "itpsm", "0222500003", "itpsm.it", 1, 1, true);
        Teaching t2 = new Teaching("IT Project and Service Management", "itpsm", "0222500003", "itpsm.it", 1, 1, true);
        assertTrue(t1.equal(t2));
    }
    

}
