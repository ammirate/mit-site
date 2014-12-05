

import java.util.ArrayList;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import it.unisa.offerta_formativa.manager.DegreeManager;

import org.junit.Test;

import it.unisa.offerta_formativa.beans.Degree;


/**
 * 
 * @author Antonio
 *
 */
public class Test_DegreeManager extends TestCase{
	
	DegreeManager degreeManager;
	
	
	public Test_DegreeManager(String s) {
		super(s);
	}
	
	
	@Test
	public void test() {
		suite();
	}
	
	
	
	
	public static TestSuite suite() {
		TestSuite suite= new TestSuite();
		suite.addTest(new Test_DegreeManager("TC_2_1_getInstance"));
		suite.addTest(new Test_DegreeManager("TC_2_2_createDegree"));
		suite.addTest(new Test_DegreeManager("TC_2_3_updateDegree"));
		suite.addTest(new Test_DegreeManager("TC_2_4_readDegree"));
		suite.addTest(new Test_DegreeManager("TC_2_5_deleteDegree"));
		//suite.addTest(new Test_DegreeDB("TC_2_6_getAllDegrees"));
		//suite.addTest(new Test_DegreeDB("TC_2_7_getDegreesOfCycle"));

		return suite;
	}
	
	/**
	 * TestCase 1.1 that test the connection to the DB
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
	 * TestCase 1.2 that test the creation of a new Bachelor in the DB
	 */
	public void TC_2_2_createDegree() {
		System.out.print("Executing Test 2.2...");
		degreeManager = DegreeManager.getInstance();
		Degree degree = new Degree("02226", "www.unisa.it", "Tecnologie informatiche e Management", 2,"TIM");
		assertTrue(degreeManager.createDegree(degree));
		System.out.println("Done");
	}

	/**
	 * TestCase 1.3 that test the update of a Bachelor in the DB
	 */
	public void TC_2_3_updateDegree() {
		System.out.print("Executing Test 2.3...");
		degreeManager = DegreeManager.getInstance();
		Degree degree = new Degree("02226", "www.unisa.it", "Tecnologie informatiche e Management", 2,"TIM");
		assertTrue(degreeManager.updateDegree(degree));
		System.out.println("Done");
	}

	/**
	 * TestCase 1.4 that test the read operation of a Bachelor from the DB
	 */
	public void TC_2_4_readDegree() {
		System.out.print("Executing Test 2.4...");
		degreeManager = DegreeManager.getInstance();
		Degree degree = new Degree("02226", "www.unisa.it", "Tecnologie informatiche e Management", 2,"TIM");
		assertTrue(degree.equals(degreeManager.readDegree(degree.getMatricula())));
		System.out.println("Done");
	}

	
	/**
	 * TestCase 1.5 that test the deletion of a Bachelor from the DB
	 */
	public void TC_2_5_deleteDegree() {
		System.out.print("Executing Test 2.5...");
		degreeManager = DegreeManager.getInstance();
		Degree bach = new Degree("02226", "www.unisa.it", "Tecnologie informatiche e Management", 2,"TIM");
		assertTrue(degreeManager.deleteDegree(bach.getMatricula()));
		System.out.println("Done");
	}
	
	
	public void TC_2_6_getAllDegrees(){
		System.out.print("Executing Test 2.6...");
		degreeManager = DegreeManager.getInstance();
		Degree d1 = new Degree("02226", "www.unisa.it", "Tecnologie informatiche e Management", 2,"TIM");
		Degree d2 = new Degree("00001", "www.sito2.it", "ABCD", 2,"ABC");
		
		degreeManager.createDegree(d1);
		degreeManager.createDegree(d2);		
		
		ArrayList<Degree> results = degreeManager.getAllDegrees();
		assertEquals(2, results.size());
		System.out.println("Done");
		degreeManager.deleteDegree(d1.getMatricula());
		degreeManager.deleteDegree(d2.getMatricula());
		
	}
	
	
	public void TC_2_7_getDegreesOfCycle(){
		System.out.print("Executing Test 2.7...");
		degreeManager = DegreeManager.getInstance();
		Degree b1 = new Degree("00000", "www.sito1.it", "ABC", 1,"ABC");
		Degree b2 = new Degree("00001", "www.sito2.it", "DEF", 1,"DEF");
		Degree b3 = new Degree("00002", "www.sito3.it", "GHI", 1,"GHI");
		Degree b4 = new Degree("00003", "www.sito4.it", "ABCD", 2,"ABCD");
		Degree b5 = new Degree("00004", "www.sito5.it", "EFGH", 2,"EFGH");
		Degree b6 = new Degree("00005", "www.sito6.it", "IJKLM", 3,"IJKLM");
		Degree b7 = new Degree("00006", "www.sito7.it", "NOPQR", 3,"NOPQR");
		
		degreeManager.createDegree(b1);
		degreeManager.createDegree(b2);
		degreeManager.createDegree(b3);
		degreeManager.createDegree(b4);
		degreeManager.createDegree(b5);
		degreeManager.createDegree(b6);		
		degreeManager.createDegree(b7);		
		
		ArrayList<Degree> results = degreeManager.getDegreesByCycle(1);
		assertEquals(3, results.size());
		
		results = degreeManager.getDegreesByCycle(2);
		assertEquals(2, results.size());
		
		results = degreeManager.getDegreesByCycle(3);
		assertEquals(2, results.size());
		
		degreeManager.deleteDegree(b1.getMatricula());
		degreeManager.deleteDegree(b2.getMatricula());
		degreeManager.deleteDegree(b3.getMatricula());
		degreeManager.deleteDegree(b4.getMatricula());
		degreeManager.deleteDegree(b5.getMatricula());
		degreeManager.deleteDegree(b6.getMatricula());
		degreeManager.deleteDegree(b7.getMatricula());
		
		System.out.println("Done");

	}

	





}
