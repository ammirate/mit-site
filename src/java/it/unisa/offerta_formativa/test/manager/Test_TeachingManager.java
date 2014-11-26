package test.manager;

import java.util.ArrayList;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import manager.TeachingManager;

import org.junit.Test;

import beans.Teaching;
import beans.Teaching;


/**
 * 
 * @author Antonio
 *
 */
public class Test_TeachingManager extends TestCase{
	
	TeachingManager t_db;
	
	
	public Test_TeachingManager(String s) {
		super(s);
	}
	
	
	@Test
	public void test() {
		suite();
	}
	
	
	
	
	public static TestSuite suite() {
		TestSuite suite= new TestSuite();
		suite.addTest(new Test_TeachingManager("TC_3_1_getInstance"));
		suite.addTest(new Test_TeachingManager("TC_3_2_createTeaching"));
		suite.addTest(new Test_TeachingManager("TC_3_3_updateTeaching"));
		suite.addTest(new Test_TeachingManager("TC_3_4_readTeaching"));
		suite.addTest(new Test_TeachingManager("TC_3_5_deleteTeaching"));
		suite.addTest(new Test_TeachingManager("TC_3_6_getAllTeachings"));
		suite.addTest(new Test_TeachingManager("TC_3_7_getTeachingsOfYear"));
		suite.addTest(new Test_TeachingManager("TC_3_8_getTeachingsOfSemester"));		
		suite.addTest(new Test_TeachingManager("TC_3_9_areEqual"));

		return suite;
	}
	
	/**
	 * TestCase 3.1 that test the connection to the DB
	 */
	public void TC_3_1_getInstance() {
		System.out.print("Executing Test 3.1...");
		//check if instantiation is correct
		TeachingManager t_db = TeachingManager.getInstance();
		assertNotNull(t_db);
		
		//check if singleton pattern works properly
		TeachingManager b2_db = TeachingManager.getInstance();
		assertEquals("", t_db, b2_db);
		System.out.println("Done");
	}

	/**
	 * TestCase 3.2 that test the creation of a new Teaching in the DB
	 */
	public void TC_3_2_createTeaching() {
		System.out.print("Executing Test 3.2...");
		t_db = TeachingManager.getInstance();
		Teaching teaching = new Teaching("itpsm", "itpsm", "0222500002" , "itpsm.it", 1, 1, true);
		assertTrue(t_db.createTeaching(teaching));
		System.out.println("Done");
	}

	/**
	 * TestCase 3.3 that test the update of a Teaching in the DB
	 */
	public void TC_3_3_updateTeaching() {
		System.out.print("Executing Test 3.3...");
		t_db = TeachingManager.getInstance();
		Teaching teaching = new Teaching("IT Project and Service Management", "itpsm", "0222500002" , "itpsm.it", 1, 1, true);
		assertTrue(t_db.updateTeaching(teaching));
		System.out.println("Done");
	}

	/**
	 * TestCase 3.4 that test the read operation of a Teaching from the DB
	 */
	public void TC_3_4_readTeaching() {
		System.out.print("Executing Test 3.4...");
		t_db = TeachingManager.getInstance();
		Teaching teaching = new Teaching("IT Project and Service Management", "itpsm", "0222500002" , "itpsm.it", 1, 1, true);
		Teaching t2 =t_db.readTeaching("0222500002");
		assertTrue(teaching.equal(t2));
		System.out.println("Done");
	}

	
	/**
	 * TestCase 3.5 that test the deletion of a Teaching from the DB
	 */
	public void TC_3_5_deleteTeaching() {
		System.out.print("Executing Test 3.5...");
		t_db = TeachingManager.getInstance();
		Teaching teaching = new Teaching("IT Project and Service Management", "itpsm", "0222500002" , "itpsm.it", 1, 1, true);
		assertTrue(t_db.deleteTeaching(teaching.getMatricula()));
		System.out.println("Done");
	}
	
	
	public void TC_3_6_getAllTeachings(){
		System.out.print("Executing Test 3.6...");
		t_db = TeachingManager.getInstance();
		Teaching t1 = new Teaching("IT Project and Service Management", "itpsm", "0222500008" , "itpsm.it", 1, 1, true);
		Teaching t2 = new Teaching("IManagement & Control systems", "mcs", "0222500009" , "mcd.it", 1, 1, true);
		
		t_db.createTeaching(t1);
		t_db.createTeaching(t2);		
		
		ArrayList<Teaching> results = t_db.getAllTeachings();
		assertEquals(2, results.size());
		System.out.println("Done");
		t_db.deleteTeaching(t1.getMatricula());
		t_db.deleteTeaching(t2.getMatricula());
		
	}
	
	
	public void TC_3_7_getTeachingsOfYear(){
		System.out.print("Executing Test 3.7...");
		t_db = TeachingManager.getInstance();
		Teaching t1 = new Teaching("IT Project and Service Management", "itpsm", "0222500010" , "itpsm.it", 1, 1, true);
		Teaching t2 = new Teaching("IManagement & Control systems", "mcs", "0222500011" , "mcd.it", 1, 1, true);
		Teaching t3 = new Teaching("Data Management", "dm", "0222500014" , "itpsm.it", 1, 2, true);
		Teaching t4 = new Teaching("Ing. Softw. : metriche e qualità", "mq", "0222500015" , "mq.it", 2, 1, true);	
		
		t_db.createTeaching(t1);
		t_db.createTeaching(t2);
		t_db.createTeaching(t3);
		t_db.createTeaching(t4);
		
		ArrayList<Teaching> results = t_db.getTeachingsByYear(1);
		assertEquals(3, results.size());
		
		results = t_db.getTeachingsByYear(2);
		assertEquals(1, results.size());
		
		t_db.deleteTeaching(t1.getMatricula());
		t_db.deleteTeaching(t2.getMatricula());
		t_db.deleteTeaching(t3.getMatricula());
		t_db.deleteTeaching(t4.getMatricula());
		
		System.out.println("Done");
	}
	
	
	
	public void TC_3_8_getTeachingsOfSemester(){
		System.out.print("Executing Test 3.8...");
		t_db = TeachingManager.getInstance();
		Teaching t1 = new Teaching("IT Project and Service Management", "itpsm", "0222500003" , "itpsm.it", 1, 1, true);
		Teaching t2 = new Teaching("IManagement & Control systems", "mcs", "0222500001" , "mcd.it", 1, 1, true);
		Teaching t3 = new Teaching("Data Management", "dm", "0222500004" , "itpsm.it", 1, 2, true);
		Teaching t4 = new Teaching("Ing. Softw. : metriche e qualità", "mq", "0222500005" , "mq.it", 2, 2, true);	
		
		t_db.createTeaching(t1);
		t_db.createTeaching(t2);
		t_db.createTeaching(t3);
		t_db.createTeaching(t4);
		
		ArrayList<Teaching> results = t_db.getTeachingsBySemester(1);
		assertEquals(2, results.size());
		
		results = t_db.getTeachingsBySemester(2);
		assertEquals(2, results.size());
		
		t_db.deleteTeaching(t1.getMatricula());
		t_db.deleteTeaching(t2.getMatricula());
		t_db.deleteTeaching(t3.getMatricula());
		t_db.deleteTeaching(t4.getMatricula());
		
		System.out.println("Done");
	}

	
	public void TC_3_9_areEqual(){
		Teaching t1 = new Teaching("IT Project and Service Management", "itpsm", "0222500003" , "itpsm.it", 1, 1, true);
		Teaching t2 = new Teaching("IT Project and Service Management", "itpsm", "0222500003" , "itpsm.it", 1, 1, true);
		assertTrue(t1.equal(t2));
	}
	





}
