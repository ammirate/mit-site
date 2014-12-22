//import java.util.ArrayList;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import it.unisa.offerta_formativa.manager.DegreeManager;

import org.junit.Test;

import it.unisa.offerta_formativa.beans.Degree;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio
 *
 */
public class Test_DegreeManager extends TestCase {

    DegreeManager degreeManager;

    public Test_DegreeManager(String s) {
        super(s);
    }

    @Test
    public void test() {
        suite();
    }

    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
//        suite.addTest(new Test_DegreeManager("TC_2_1_getInstance"));
        suite.addTest(new Test_DegreeManager("TC_2_2_createDegree"));
//        suite.addTest(new Test_DegreeManager("TC_2_3_updateDegree"));
//        suite.addTest(new Test_DegreeManager("TC_2_4_readDegree"));
//        suite.addTest(new Test_DegreeManager("TC_2_5_deleteDegree"));
//        suite.addTest(new Test_DegreeManager("TC_2_6_getAllDegrees"));
//        suite.addTest(new Test_DegreeManager("TC_2_7_getDegreesByCycle"));
//        suite.addTest(new Test_DegreeManager("TC_2_8_getDegreesByDepartment"));
//        suite.addTest(new Test_DegreeManager("TC_2_9_getDegreesByDepartmentAndCycle"));
//        suite.addTest(new Test_DegreeManager("TC_2_10_setEsse3Content"));

        return suite;
    }

    /**
     * test the connection to the DB
     */
    public void TC_2_1_getInstance() {
        System.out.print("Executing Test 2.1...");
        //check if instantiation is correct
        DegreeManager b_db = DegreeManager.getInstance();
        assertNotNull(b_db);

        //check if singleton pattern works properly
        DegreeManager b2_db = DegreeManager.getInstance();
        assertEquals("", b_db, b2_db);
        System.out.println("Done");
    }

    /**
     * test the creation of a new Bachelor in the DB
     */
    public void TC_2_2_createDegree() {
        System.out.print("Executing Test 2.2...");
        degreeManager = DegreeManager.getInstance();
        Degree degree = new Degree("02226", "www.unisa.it", "Tecnologie informatiche e Management", 2, "DISTRA");
        assertTrue(degreeManager.createDegree(degree));
        System.out.println("Done");
    }

    /**
     * test the update of a Bachelor in the DB
     */
    public void TC_2_3_updateDegree() {
        System.out.print("Executing Test 2.3...");
        degreeManager = DegreeManager.getInstance();
        Degree degree = new Degree("02226", "www.distra.unisa.it", "Management and Information Technology", 2, "DISTRA");
        assertTrue(degreeManager.updateDegree("02226", degree));
        System.out.println("Done");
    }

    /**
     * test the read operation of a Bachelor from the DB
     */
    public void TC_2_4_readDegree() {
        System.out.print("Executing Test 2.4...");
        degreeManager = DegreeManager.getInstance();
        Degree degree = new Degree("02226", "www.distra.unisa.it", "Management and Information Technology", 2, "DISTRA");
        assertTrue(degree.equals(degreeManager.readDegree("02226")));
        System.out.println("Done");
    }

    /**
     * test the deletion of a Bachelor from the DB
     */
    public void TC_2_5_deleteDegree() {
        System.out.print("Executing Test 2.5...");
        degreeManager = DegreeManager.getInstance();
        Degree bach = new Degree("02226", "www.unisa.it", "Tecnologie informatiche e Management", 2, "DISTRA");
        assertTrue(degreeManager.deleteDegree(bach.getMatricula()));
        System.out.println("Done");
    }

    /**
     * test the 'select * from degree' query
     */
    public void TC_2_6_getAllDegrees() {
        System.out.print("Executing Test 2.6...");
        degreeManager = DegreeManager.getInstance();

        ArrayList<Degree> results = degreeManager.getAllDegrees();
        assertEquals(1, results.size());
        //in the DB, testing versio, there is already a degree
        System.out.println("Done");

    }

    /**
     *
     */
    public void TC_2_7_getDegreesByCycle() {
        System.out.print("Executing Test 2.7...");
        degreeManager = DegreeManager.getInstance();
        Degree b1 = new Degree("00000", "www.sito1.it", "ABC", 1, "DISTRA");
        Degree b2 = new Degree("00001", "www.sito2.it", "DEF", 1, "DISTRA");
        Degree b3 = new Degree("00002", "www.sito3.it", "GHI", 1, "DISTRA");
        Degree b4 = new Degree("00003", "www.sito4.it", "ABCD", 2, "DISTRA");
        Degree b5 = new Degree("00004", "www.sito5.it", "EFGH", 2, "DISTRA");

        degreeManager.createDegree(b1);
        degreeManager.createDegree(b2);
        degreeManager.createDegree(b3);
        degreeManager.createDegree(b4);
        degreeManager.createDegree(b5);

        ArrayList<Degree> results = degreeManager.getDegreesByCycle(1);
        assertEquals(3, results.size());

        results = degreeManager.getDegreesByCycle(2);
        assertEquals((2+1), results.size());
        //in the DB, testing version, there is already a degree

        degreeManager.deleteDegree(b1.getMatricula());
        degreeManager.deleteDegree(b2.getMatricula());
        degreeManager.deleteDegree(b3.getMatricula());
        degreeManager.deleteDegree(b4.getMatricula());
        degreeManager.deleteDegree(b5.getMatricula());

        System.out.println("Done");

    }

    public void TC_2_8_getDegreesByDepartment() {
        System.out.print("Executing Test 2.7...");
        degreeManager = DegreeManager.getInstance();

        
        Degree b1 = new Degree("00000", "www.sito1.it", "ABC", 1, "DISTRA");
        Degree b2 = new Degree("00001", "www.sito2.it", "DEF", 1, "DISTRA");
        degreeManager.createDegree(b2);
        degreeManager.createDegree(b1);
        
        List<Degree> list = degreeManager.getDegreesByDepartment("DISTRA");
        assertEquals((2+1), list.size());
        //there is already a degree in the DB, testing version, so they are 3
        degreeManager.deleteDegree(b1.getMatricula());
        degreeManager.deleteDegree(b2.getMatricula());
        
        System.out.println("Done");
    }
    
    
    /**
     * 
     */
     public void TC_2_9_getDegreesByDepartmentAndCycle() {
        System.out.print("Executing Test 2.7...");
        degreeManager = DegreeManager.getInstance();

        
        Degree b1 = new Degree("00000", "www.sito1.it", "ABC", 1, "DISTRA");
        Degree b2 = new Degree("00001", "www.sito2.it", "DEF", 2, "DISTRA");
        degreeManager.createDegree(b2);
        degreeManager.createDegree(b1);
        //now there are 3 degree into the DB which belong to DISTRA:
        //2 have cycle = 2 and 1 has cycle=1
        
        List<Degree> list = degreeManager.getDegreesByDepartmentAndCycle("DISTRA",1);
        assertEquals((1), list.size());
        //there is already a degree in the DB, testing version, so they are 2
        
        list = degreeManager.getDegreesByDepartmentAndCycle("DISTRA",2);
          assertEquals((2), list.size());
          
        degreeManager.deleteDegree(b1.getMatricula());
        degreeManager.deleteDegree(b2.getMatricula());
        
        System.out.println("Done");
    }
    
    
    
     public void TC_2_10_setEsse3Content(){
        System.out.print("Executing Test 2.10...");
        degreeManager = DegreeManager.getInstance();
        Degree degree = new Degree("02226", "www.unisa.it", "Tecnologie informatiche e Management", 2, "DISTRA");
        degreeManager.createDegree(degree);
        assertTrue(degreeManager.setEsse3ContentForDegree("02226", "esse3content"));
        degreeManager.deleteDegree("02226");
        System.out.println("Done");
     }

}
