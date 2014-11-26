package test.manager;

import manager.ModuleManager;

import org.junit.Test;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import beans.Module;

/**
 * 
 * @author Alessandro
 *
 */
public class Test_ModuleManager extends TestCase{
	
	public Test_ModuleManager(String s) {
		super(s);
	}
	
	@Test
	public void test() {
		suite();
	}
	
	public static TestSuite suite() {
		TestSuite suite= new TestSuite();
		suite.addTest(new Test_ModuleManager("TC_5_1_getInstance"));
		suite.addTest(new Test_ModuleManager("TC_5_2_createModule"));
		suite.addTest(new Test_ModuleManager("TC_5_3_readModule"));
		suite.addTest(new Test_ModuleManager("TC_5_4_UpdateModule"));
		suite.addTest(new Test_ModuleManager("TC_5_5_DeleteModule"));
		suite.addTest(new Test_ModuleManager("TC_5_6_getModulesByTeaching"));
		suite.addTest(new Test_ModuleManager("TC_5_7_getAllModules"));
		return suite;
	}

	public void TC_5_1_getInstance(){
		System.out.println("Executing TC_5_1...");
		//check if instantiation is correct
		ModuleManager m_db = ModuleManager.getInstance();
		assertNotNull(m_db);
		
		//check if singleton pattern works properly
		ModuleManager m2_db = ModuleManager.getInstance();
		assertEquals("", m_db, m2_db);
		System.out.print("Done");
	}
	
	public void TC_5_2_createModule(){
		System.out.println("Executing TC_5_2...");
		ModuleManager mdb = ModuleManager.getInstance();
		assertTrue(mdb.createModule(new Module("0510201111","Service Management")));
		System.out.print("Done");
	}
	
	public void TC_5_3_readModule(){
		System.out.println("Executing TC_5_3....");
		ModuleManager mdb = ModuleManager.getInstance();
		Module m = mdb.readModule(1);
		Module test = new Module(1,"0510201111","Service Management");
		assertTrue(m.equals(test));
		System.out.print("Done");
	}
	
	public void TC_5_4_UpdateModule(){
		System.out.println("Executing TC_5_4....");
		ModuleManager mdb = ModuleManager.getInstance();
		assertTrue(mdb.updateModule(new Module(1,"0510201111","Project Management")));
		System.out.print("Done");
	}
	
	public void TC_5_5_DeleteModule(){
		System.out.println("Executing TC_5_5....");
		ModuleManager mdb = ModuleManager.getInstance();
		assertTrue(mdb.deleteModule(1));
		System.out.print("Done");
	}
	
	public void TC_5_6_getModulesByTeaching() {
		// TODO Auto-generated method stub
		System.out.println("Executing TC_5_6....");
		ModuleManager mdb = ModuleManager.getInstance();
		mdb.createModule(new Module("0510201111","Service Management"));
		mdb.createModule(new Module("0510201111","Project Management"));
		assertEquals(2,mdb.getModulesByTeaching("0510201111").size());
		System.out.print("Done");
		mdb.deleteModule(2);
		mdb.deleteModule(3);
	}
	
	public void TC_5_7_getAllModules() {
		// TODO Auto-generated method stub
		System.out.println("Executing TC_5_7....");
		ModuleManager mdb = ModuleManager.getInstance();
		mdb.createModule(new Module("0510201111","Service Management"));
		mdb.createModule(new Module("0510201111","Project Management"));
		assertEquals(2,mdb.getAllModules().size());
		System.out.print("Done");
		mdb.deleteModule(4);
		mdb.deleteModule(5);
	}
	
}
