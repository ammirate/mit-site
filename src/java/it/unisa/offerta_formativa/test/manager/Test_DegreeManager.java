package test.manager;

import java.util.ArrayList;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import manager.DegreeManager;

import org.junit.Test;

import beans.Degree;


/**
 * 
 * @author Antonio
 *
 */
public class Test_DegreeManager extends TestCase{
	
	DegreeManager b_db;
	
	
	public Test_DegreeManager(String s) {
		super(s);
	}
	
	
	@Test
	public void test() {
		suite();
	}
	
	
	
	
	public static TestSuite suite() {
		TestSuite suite= new TestSuite();
		suite.addTest(new Test_DegreeManager("TC_1_1_getInstance"));
		suite.addTest(new Test_DegreeManager("TC_1_2_createDegree"));
		suite.addTest(new Test_DegreeManager("TC_1_3_updateDegree"));
		suite.addTest(new Test_DegreeManager("TC_1_4_readDegree"));
		suite.addTest(new Test_DegreeManager("TC_1_5_deleteDegree"));
		//suite.addTest(new Test_DegreeDB("TC_1_6_getAllDegrees"));
		//suite.addTest(new Test_DegreeDB("TC_1_7_getDegreesOfCycle"));

		return suite;
	}
	
	/**
	 * TestCase 1.1 that test the connection to the DB
	 */
	public void TC_1_1_getInstance() {
		System.out.print("Executing Test 1.1...");
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
	public void TC_1_2_createDegree() {
		System.out.print("Executing Test 1.2...");
		b_db = DegreeManager.getInstance();
		Degree bach = new Degree("02226", "www.unisaaaa.it", "tech inf. e Management", 2,1);
		assertTrue(b_db.createDegree(bach));
		System.out.println("Done");
	}

	/**
	 * TestCase 1.3 that test the update of a Bachelor in the DB
	 */
	public void TC_1_3_updateDegree() {
		System.out.print("Executing Test 1.3...");
		b_db = DegreeManager.getInstance();
		Degree bach = new Degree("02226", "www.unisa.it", "Tecnologie informatiche e Management", 2,1);
		assertTrue(b_db.updateDegree(bach));
		System.out.println("Done");
	}

	/**
	 * TestCase 1.4 that test the read operation of a Bachelor from the DB
	 */
	public void TC_1_4_readDegree() {
		System.out.print("Executing Test 1.4...");
		b_db = DegreeManager.getInstance();
		Degree bach = new Degree("02226", "www.unisa.it", "Tecnologie informatiche e Management", 2,1);
		assertTrue(bach.equals(b_db.readDegree(bach.getMatricula())));
		System.out.println("Done");
	}

	
	/**
	 * TestCase 1.5 that test the deletion of a Bachelor from the DB
	 */
	public void TC_1_5_deleteDegree() {
		System.out.print("Executing Test 1.5...");
		b_db = DegreeManager.getInstance();
		Degree bach = new Degree("02226", "www.unisa.it", "Tecnologie informatiche e Management", 2,1);
		assertTrue(b_db.deleteDegree(bach.getMatricula()));
		System.out.println("Done");
	}
	
	
	public void TC_1_6_getAllDegrees(){
		System.out.print("Executing Test 1.6...");
		b_db = DegreeManager.getInstance();
		Degree b1 = new Degree("00000", "www.sito1.it", "ABC", 1,1);
		Degree b2 = new Degree("00001", "www.sito2.it", "ABCD", 2,1);
		
		b_db.createDegree(b1);
		b_db.createDegree(b2);		
		
		ArrayList<Degree> results = b_db.getAllDegrees();
		assertEquals(2, results.size());
		System.out.println("Done");
		b_db.deleteDegree(b1.getMatricula());
		b_db.deleteDegree(b2.getMatricula());
		
	}
	
	
	public void TC_1_7_getDegreesOfCycle(){
		System.out.print("Executing Test 1.7...");
		b_db = DegreeManager.getInstance();
		Degree b1 = new Degree("00000", "www.sito1.it", "ABC", 1,1);
		Degree b2 = new Degree("00001", "www.sito2.it", "DEF", 1,1);
		Degree b3 = new Degree("00002", "www.sito3.it", "GHI", 1,1);
		Degree b4 = new Degree("00003", "www.sito4.it", "ABCD", 2,1);
		Degree b5 = new Degree("00004", "www.sito5.it", "EFGH", 2,1);
		Degree b6 = new Degree("00005", "www.sito6.it", "IJKLM", 3,1);
		Degree b7 = new Degree("00006", "www.sito7.it", "NOPQR", 3,1);
		
		b_db.createDegree(b1);
		b_db.createDegree(b2);
		b_db.createDegree(b3);
		b_db.createDegree(b4);
		b_db.createDegree(b5);
		b_db.createDegree(b6);		
		b_db.createDegree(b7);		
		
		ArrayList<Degree> results = b_db.getDegreesByCycle(1);
		assertEquals(3, results.size());
		
		results = b_db.getDegreesByCycle(2);
		assertEquals(2, results.size());
		
		results = b_db.getDegreesByCycle(3);
		assertEquals(2, results.size());
		
		b_db.deleteDegree(b1.getMatricula());
		b_db.deleteDegree(b2.getMatricula());
		b_db.deleteDegree(b3.getMatricula());
		b_db.deleteDegree(b4.getMatricula());
		b_db.deleteDegree(b5.getMatricula());
		b_db.deleteDegree(b6.getMatricula());
		b_db.deleteDegree(b7.getMatricula());
		
		System.out.println("Done");

	}

	





}
