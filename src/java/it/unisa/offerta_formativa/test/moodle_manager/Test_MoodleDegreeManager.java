package it.unisa.offerta_formativa.test.moodle_manager;

import it.unisa.offerta_formativa.moodle.manager.MoodleDegreeManager;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleCategory;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleCourse;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleRestCourse;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Davide
 */
public class Test_MoodleDegreeManager extends TestCase{
    
    public Test_MoodleDegreeManager(String s){
		super(s);
	}
    
    public static TestSuite suite(){
		TestSuite suite = new TestSuite();
		suite.addTest(new Test_MoodleDegreeManager("TC_1_1_getInstance"));
		suite.addTest(new Test_MoodleDegreeManager("TC_1_2_createDegree"));
		suite.addTest(new Test_MoodleDegreeManager("TC_1_3_updateDegree"));
                suite.addTest(new Test_MoodleDegreeManager("TC_1_4_deleteDegree"));
		suite.addTest(new Test_MoodleDegreeManager("TC_1_5_getIdDegree"));

		return suite;
	}
    
    /**
	 * TestCase 1.1 that test the connection to moodle
	 */
	public void TC_1_1_getInstance() {
		System.out.print("Executing Test 1.1...");
		//check if instantiation is correct
		MoodleDegreeManager b_dm = MoodleDegreeManager.getInstance("http://localhost/moodle/webservice/rest/server.php","2c6f2faa8aac80fc912cff9f6f8bad66");
		assertNotNull(b_dm);
		
		//check if singleton pattern works properly
		MoodleDegreeManager b2_dm = MoodleDegreeManager.getInstance("http://localhost/moodle/webservice/rest/server.php","2c6f2faa8aac80fc912cff9f6f8bad66");
		assertEquals("", b_dm, b2_dm);
		System.out.println("Done");
	}
        /**
	 * TestCase 1.2 that test the creation of a new Degree in moodle
	 */
	public void TC_1_2_createDegree() {
		System.out.print("Executing Test 1.2...");
		MoodleDegreeManager d_db = MoodleDegreeManager.getInstance("http://localhost/moodle/webservice/rest/server.php","2c6f2faa8aac80fc912cff9f6f8bad66");
		MoodleCategory cat= new MoodleCategory("Prova", Long.parseLong(String.valueOf(2)));
                d_db.createDegree("Prova",2);
                
		System.out.println("Done");
	}

	/**
	 * TestCase 1.3 that test the update of a Degree in moodle
	 */
	public void TC_1_3_updateDegree() {
		System.out.print("Executing Test 1.3...");
		MoodleDegreeManager d_db = MoodleDegreeManager.getInstance("http://localhost/moodle/webservice/rest/server.php","2c6f2faa8aac80fc912cff9f6f8bad66");
		assertTrue(d_db.updateDegree("Prova2",2));
		System.out.println("Done");
	}

	/**
	 * TestCase 1.4 that test the deletion of a Degree in moodle
	 */
	public void TC_1_4_deleteDegree() {
		System.out.print("Executing Test 1.4...");
		MoodleDegreeManager d_db = MoodleDegreeManager.getInstance("http://localhost/moodle/webservice/rest/server.php","2c6f2faa8aac80fc912cff9f6f8bad66");
                assertTrue(d_db.deleteDegree("Prova2",d_db.getIdDegree("Prova2")));
		System.out.println("Done");
	}
        
        /**
	 * TestCase 1.5 that test the get of a Degree in moodle
	 */
        public void TC_1_5_getIdDegree(){
		System.out.print("Executing Test 1.6...");
		MoodleDegreeManager d_db = MoodleDegreeManager.getInstance("http://localhost/moodle/webservice/rest/server.php","2c6f2faa8aac80fc912cff9f6f8bad66");
		
		
	}
    
}
