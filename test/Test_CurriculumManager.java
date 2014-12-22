import it.unisa.offerta_formativa.manager.CurriculumManager;

import org.junit.Test;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.manager.Exceptions.CurriculumException;
import it.unisa.offerta_formativa.manager.Exceptions.TeachingException;
import java.util.List;

/**
 *
 * @author Alessandro, Antonio
 *
 */
public class Test_CurriculumManager extends TestCase {

    public Test_CurriculumManager(String s) {
        super(s);
    }

    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
//        suite.addTest(new Test_CurriculumManager("TC_6_1_getInstance"));
//        suite.addTest(new Test_CurriculumManager("TC_6_2_createCurriculum"));
//        suite.addTest(new Test_CurriculumManager("TC_6_3_readCurriculum"));
//        suite.addTest(new Test_CurriculumManager("TC_6_4_UpdateCurriculum"));
//        suite.addTest(new Test_CurriculumManager("TC_6_5_DeleteCurriculum"));
//        suite.addTest(new Test_CurriculumManager("TC_6_6_getTeachingsByCurriculm"));
//        suite.addTest(new Test_CurriculumManager("TC_6_7_getAllCurriculums"));
//        suite.addTest(new Test_CurriculumManager("TC_6_8_curriculumHasTeaching"));
//        suite.addTest(new Test_CurriculumManager("TC_6_9_getCurriculumByDegree"));
//        suite.addTest(new Test_CurriculumManager("TC_6_10_setTeachingInCurriculum"));
//        suite.addTest(new Test_CurriculumManager("TC_6_11_getCurriculumsByTeaching"));
        suite.addTest(new Test_CurriculumManager("TC_6_12_setTeachingInCurriculum"));
        suite.addTest(new Test_CurriculumManager("TC_6_13_deleteCurriculumHasTeaching"));

        return suite;
    }

    @Test
    public void test() {
        suite();
    }

    public void TC_6_1_getInstance() {
        System.out.print("Executing TC_6_1...");
        //check if instantiation is correct
        CurriculumManager currManager = CurriculumManager.getInstance();
        assertNotNull(currManager);

        //check if singleton pattern works properly
        CurriculumManager cuurrManager2 = CurriculumManager.getInstance();
        assertEquals(currManager, cuurrManager2);
        System.out.println("Done");
    }

    /**
     * insert a curriculum into the DB
     */
    public void TC_6_2_createCurriculum() {
        System.out.print("Executing TC_6_2...");
        CurriculumManager currManager = CurriculumManager.getInstance();
        assertTrue(currManager.createCurriculum(new Curriculum("02225P0002", "Management - ", "02225")));
        System.out.println("Done");
    }

    /**
     * read a curriculum from the DB
     */
    public void TC_6_3_readCurriculum() throws CurriculumException {
        System.out.print("Executing TC_6_3....");
        CurriculumManager currManager = CurriculumManager.getInstance();
        Curriculum test = new Curriculum("02225P0002", "Management - ", "02225");
        Curriculum m = currManager.readCurriculum("02225P0002");
        assertTrue(test.equals(m));
        System.out.println("Done");
    }

    /**
     * update a curriculum into the db
     */
    public void TC_6_4_UpdateCurriculum() throws CurriculumException {
        System.out.print("Executing TC_6_4....");
        CurriculumManager currManager = CurriculumManager.getInstance();
        Curriculum newCurr = new Curriculum("02225P0002", "Management - ", "02225");
        assertTrue(currManager.updateCurriculum("02225P0002", newCurr));
        System.out.println("Done");
    }

    /**
     * Delete a curriculum from the DB
     */
    public void TC_6_5_DeleteCurriculum() throws CurriculumException {
        System.out.print("Executing TC_6_5....");
        CurriculumManager currManager = CurriculumManager.getInstance();
        assertTrue(currManager.deleteCurriculum("02225P0002"));
        System.out.println("Done");
    }

    /**
     * test the reading operation of teachings which belongs to a curriculum
     */
    public void TC_6_6_getTeachingsByCurriculm() throws TeachingException, CurriculumException {
        System.out.print("Executing TC_6_6....");
        CurriculumManager currManager = CurriculumManager.getInstance();

        List<Teaching> teachings = currManager.getTeachingsByCurriculm("02225P0001");
        assertEquals(1, teachings.size());
        //in the DB, testing version, there is already a teaching assigned to that curriculum, so the size is 

        System.out.println("Done");
    }

    /**
     *
     */
    public void TC_6_7_getAllCurriculums() throws CurriculumException {
        System.out.print("Executing TC_6_7....");
        CurriculumManager currManager = CurriculumManager.getInstance();
        currManager.createCurriculum(new Curriculum("02225P0010", "Curriculum prova1 - ", "02225"));
        currManager.createCurriculum(new Curriculum("02225P0011", "Curriculum prova2 - ", "02225"));
        assertEquals((2 + 1), currManager.getAllCurriculums().size());
        //in the DB, testing version, there is already a class, so they are 3

        currManager.deleteCurriculum("02225P0010");
        currManager.deleteCurriculum("02225P0011");

        System.out.println("Done");
    }

    /**
     *
     */
    public void TC_6_8_curriculumHasTeaching() throws CurriculumException {
        System.out.print("Executing TC_6_8....");
        CurriculumManager currManager = CurriculumManager.getInstance();

        assertTrue(currManager.curriculumHasTeaching("02225P0001", "0222500002"));

        System.out.println("Done");
    }

    /**
     *
     */
    public void TC_6_9_getCurriculumByDegree() {
        System.out.print("Executing TC_6_9....");
        CurriculumManager currManager = CurriculumManager.getInstance();

        assertEquals(1, currManager.getCurriculumByDegree("02225").size());

        System.out.println("Done");
    }

    /**
     *
     */
    public void TC_6_10_setTeachingInCurriculum() throws CurriculumException {
        System.out.print("Executing TC_6_10....");
        CurriculumManager currManager = CurriculumManager.getInstance();

        currManager.createCurriculum(new Curriculum("02225P0025", "Management -and IT ", "02225"));
        boolean insert = currManager.setTeachingInCurriculum("0222500002", "02225P0025");
        assertTrue(insert);

        System.out.println("Done");
    }

    /**
     *
     */
    public void TC_6_11_getCurriculumsByTeaching() throws CurriculumException {
        System.out.print("Executing TC_6_11....");

        //0222500002 teaching matricula in the DB
        Curriculum c1 = new Curriculum("02225P0010", "Curriculum1", "02225");
        Curriculum c2 = new Curriculum("02225P0011", "Curriculum2", "02225");
        CurriculumManager currManager = CurriculumManager.getInstance();
        currManager.createCurriculum(c1);
        currManager.createCurriculum(c2);

        currManager.setTeachingInCurriculum("0222500002", "02225P0010");
        currManager.setTeachingInCurriculum("0222500002", "02225P0011");

        List<Curriculum> currsOfTeaching;
        currsOfTeaching = currManager.getCurriculumsByTeaching("0222500002");

        assertEquals((2 + 1), currsOfTeaching.size());
        //int the DB, testing version, there is already a curriculum 
        //for that teaching, so they are 3

        currManager.deleteCurriculum(c1.getDegreeMatricula());
        currManager.deleteCurriculum(c2.getDegreeMatricula());

        System.out.println("Done");
    }

    public void TC_6_12_setTeachingInCurriculum() throws CurriculumException {
        System.out.print("Executing TC_6_12....");
        CurriculumManager currManager = CurriculumManager.getInstance();
        assertTrue(currManager.setTeachingInCurriculum("0222500002", "02225P0001"));
        System.out.println("Done");
    }

    public void TC_6_13_deleteCurriculumHasTeaching() throws TeachingException {
        System.out.print("Executing TC_6_13....");
        CurriculumManager currManager = CurriculumManager.getInstance();
        assertTrue(currManager.deleteCurriculumhasTeaching("0222500002", "02225P0001"));
        System.out.println("Done");
    }
}
