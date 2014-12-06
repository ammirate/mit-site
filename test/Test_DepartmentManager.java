

import java.util.ArrayList;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import it.unisa.offerta_formativa.manager.DepartmentManager;
import org.junit.Test;
import it.unisa.offerta_formativa.beans.Department;


/**
 * 
 * @author Antonio
 *
 */
public class Test_DepartmentManager extends TestCase{
	
	DepartmentManager mng;
	
	
	public Test_DepartmentManager(String s) {
		super(s);
	}
	
	
	@Test
	public void test() {
		suite();
	}
	
	
	
	
	public static TestSuite suite() {
		TestSuite suite= new TestSuite();
		suite.addTest(new Test_DepartmentManager("TC_1_1_getInstance"));
		suite.addTest(new Test_DepartmentManager("TC_1_2_createDepartment"));
		suite.addTest(new Test_DepartmentManager("TC_1_3_updateDepartment"));
		suite.addTest(new Test_DepartmentManager("TC_1_4_readDepartment"));
		suite.addTest(new Test_DepartmentManager("TC_1_5_deleteDepartment"));
		suite.addTest(new Test_DepartmentManager("TC_1_6_getAllDepartment"));

		return suite;
	}
	
	/**
	 * TestCase 1.1 that test the connection to the DB
	 */
	public void TC_1_1_getInstance() {
		System.out.print("Executing Test 1.1...");
		//check if instantiation is correct
		DepartmentManager b_db = DepartmentManager.getInstance();
		assertNotNull(b_db);
		
		//check if singleton pattern works properly
		DepartmentManager b2_db = DepartmentManager.getInstance();
		assertEquals("", b_db, b2_db);
		System.out.println("Done");
	}

	/**
	 * TestCase 1.2 that test the creation of a new Department in the DB
	 */
	public void TC_1_2_createDepartment() {
		System.out.print("Executing Test 1.2...");
		mng = DepartmentManager.getInstance();
		Department degree = new Department("DI", "Dipartimento Informatica", "www.di.unisa.it","bresvgsss2651vgdtgch");
		assertTrue(mng.createDepartment(degree));
		System.out.println("Done");
	}

	/**
	 * TestCase 1.3 that test the update of a Department in the DB
	 */
	public void TC_1_3_updateDepartment() {
		System.out.print("Executing Test 1.3...");
		mng = DepartmentManager.getInstance();
		Department degree = new Department("DIA", "Dipartimento di Informatica Applicata", "www.di.unisa.it","bresvgsss2651vgdtgch");
		assertTrue(mng.updateDepartment("DI", degree));
		System.out.println("Done");
	}

	/**
	 * TestCase 1.4 that test the read a Department from the DB
	 */
	public void TC_1_4_readDepartment() {
		System.out.print("Executing Test 1.4...");
		mng = DepartmentManager.getInstance();
		Department degree = new Department("DIA", "Dipartimento di Informatica Applicata", "www.di.unisa.it","bresvgsss2651vgdtgch");
		assertTrue(degree.equals(mng.readDepartment("DIA")));
		System.out.println("Done");
	}

	
	/**
	 * TestCase 1.5 that test the deletion of a Department from the DB
	 */
	public void TC_1_5_deleteDepartment() {
		System.out.print("Executing Test 1.5...");
		mng = DepartmentManager.getInstance();
		assertTrue(mng.deleteDepartment("DIA"));
		System.out.println("Done");
	}
	
	
	public void TC_1_6_getAllDepartment(){
		System.out.print("Executing Test 1.6...");
		mng = DepartmentManager.getInstance();
		
                Department d1 = new Department("DIA", "Dipartimento di Informatica Applicata", "www.di.unisa.it","bresvgsss2651vgdtgch");
		mng.createDepartment(d1);
		
		ArrayList<Department> results = mng.getAllDepartments();
		assertEquals(2, results.size());
                //in the DB, testing version, there is already a department, so they are 2
		System.out.println("Done");
                
		mng.deleteDepartment(d1.getAbbreviation());
		
	}

}
