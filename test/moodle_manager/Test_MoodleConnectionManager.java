package moodle_manager;

import it.unisa.offerta_formativa.moodle.manager.MoodleConnectionManager;
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
public class Test_MoodleConnectionManager extends TestCase{
    
    public Test_MoodleConnectionManager(String s){
		super(s);
	}
    
    public static TestSuite suite(){
		TestSuite suite = new TestSuite();
		suite.addTest(new Test_MoodleConnectionManager("TC_1_1_getInstance"));
		suite.addTest(new Test_MoodleConnectionManager("TC_1_2_getToken"));
		suite.addTest(new Test_MoodleConnectionManager("TC_1_3_getUrlMoodle"));
		return suite;
	}
    
    /**
	 * TestCase 1.1 that test the connection to the DB
	 */
	public void TC_1_1_getInstance() {
		System.out.print("Executing Test 1.1...");
		//check if instantiation is correct
		MoodleConnectionManager b_db = MoodleConnectionManager.getInstance();
		assertNotNull(b_db);
		
		//check if singleton pattern works properly
		MoodleConnectionManager b2_db = MoodleConnectionManager.getInstance();
		assertEquals("", b_db, b2_db);
		System.out.println("Done");
	}
        /**
	 * TestCase 1.4 that test the get of token
	 */
	public void TC_1_2_getToken() {
		System.out.print("Executing Test 1.4...");
		MoodleConnectionManager mng = MoodleConnectionManager.getInstance();
		MoodleConnectionManager connect=null;
                String token = connect.getToken(1);
		assertTrue(token.equals(mng.getToken(1)));
		System.out.println("Done");
	}
        
        /**
	 * TestCase 1.4 that test the get of url Moodle
	 */
	public void TC_1_3_getUrlMoodle() {
		System.out.print("Executing Test 1.4...");
		MoodleConnectionManager mng = MoodleConnectionManager.getInstance();
		MoodleConnectionManager connect=null;
                String url = connect.getUrlMoodle(1);
		assertTrue(url.equals(mng.getToken(1)));
		System.out.println("Done");
	}
        
    
}
