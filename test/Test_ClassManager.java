import it.unisa.offerta_formativa.manager.ClassManager;

import org.junit.Test;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import it.unisa.offerta_formativa.beans.ClassPartition;

/**
 *
 * @author Alessandro, Antonio
 *
 */
public class Test_ClassManager extends TestCase {

    public Test_ClassManager(String s) {
        super(s);
    }

    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new Test_ClassManager("TC_7_1_getInstance"));
        suite.addTest(new Test_ClassManager("TC_7_2_createClass"));
        suite.addTest(new Test_ClassManager("TC_7_3_readClass"));
        suite.addTest(new Test_ClassManager("TC_7_4_UpdateClass"));
        suite.addTest(new Test_ClassManager("TC_7_5_DeleteClass"));
        suite.addTest(new Test_ClassManager("TC_7_6_getClassesByTeaching"));
        suite.addTest(new Test_ClassManager("TC_7_7_getAllClasses"));
        return suite;
    }

    @Test
    public void test() {
        suite();
    }

    public void TC_7_1_getInstance() {
        System.out.print("Executing TC_7_1...");
        //check if instantiation is correct
        ClassManager m_db = ClassManager.getInstance();
        assertNotNull(m_db);

        //check if singleton pattern works properly
        ClassManager m2_db = ClassManager.getInstance();
        assertEquals("", m_db, m2_db);
        System.out.println("Done");
    }

    /**
     * insert a class into the DB
     */
    public void TC_7_2_createClass() {
        System.out.print("Executing TC_7_2...");
        ClassManager mdb = ClassManager.getInstance();
        assertTrue(mdb.createClass(new ClassPartition("0222500002", "classe3")));
        System.out.println("Done");
    }

    public void TC_7_3_readClass() {
        System.out.print("Executing TC_7_3....");
        ClassManager mdb = ClassManager.getInstance();
        ClassPartition test = new ClassPartition("0222500002", "classe3");
        ClassPartition m = mdb.readClass("0222500002", "classe3");
        assertTrue(test.equals(m));
        System.out.println("Done");
    }

    public void TC_7_4_UpdateClass() {
        System.out.print("Executing TC_7_4....");
        ClassManager mdb = ClassManager.getInstance();
        ClassPartition old = new ClassPartition("0222500002", "classe3");
        ClassPartition newc = new ClassPartition("0222500002", "classe4");
        assertTrue(mdb.updateClass(old, newc));
        System.out.println("Done");
    }

    public void TC_7_5_DeleteClass() {
        System.out.print("Executing TC_7_5....");
        ClassManager mdb = ClassManager.getInstance();
        assertTrue(mdb.deleteClass("0222500002", "classe4"));
        System.out.println("Done");
    }

    public void TC_7_6_getClassesByTeaching() {
        // TODO Auto-generated method stub
        System.out.print("Executing TC_7_6....");
        ClassManager mdb = ClassManager.getInstance();
        mdb.createClass(new ClassPartition("0222500002", "congrua0"));
        mdb.createClass(new ClassPartition("0222500002", "congrua1"));
        assertEquals((2 + 1), mdb.getClassesByTeaching("0222500002").size());
        //in the DB, testing version, there is already a class, so they are 3

        System.out.println("Done");
        mdb.deleteClass("0222500002", "congrua0");
        mdb.deleteClass("0222500002", "congrua1");
    }

    public void TC_7_7_getAllClasses() {
        // TODO Auto-generated method stub
        System.out.print("Executing TC_7_7....");
        ClassManager mdb = ClassManager.getInstance();
        mdb.createClass(new ClassPartition("0222500002", "congrua0"));
        mdb.createClass(new ClassPartition("0222500002", "congrua1"));
        assertEquals((2 + 1), mdb.getClassesByTeaching("0222500002").size());
        //in the DB, testing version, there is already a class, so they are 3
        mdb.deleteClass("0222500002", "congrua0");
        mdb.deleteClass("0222500002", "congrua1");
        System.out.println("Done");
    }

}
