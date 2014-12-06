import it.unisa.offerta_formativa.manager.ModuleManager;

import org.junit.Test;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import it.unisa.offerta_formativa.beans.Module;

/**
 *
 * @author Alessandro
 *
 */
public class Test_ModuleManager extends TestCase {

    public Test_ModuleManager(String s) {
        super(s);
    }

    @Test
    public void test() {
        suite();
    }

    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new Test_ModuleManager("TC_5_1_getInstance"));
        suite.addTest(new Test_ModuleManager("TC_5_2_createModule"));
        suite.addTest(new Test_ModuleManager("TC_5_3_readModule"));
        suite.addTest(new Test_ModuleManager("TC_5_4_UpdateModule"));
        suite.addTest(new Test_ModuleManager("TC_5_5_DeleteModule"));
        suite.addTest(new Test_ModuleManager("TC_5_6_getModulesByTeaching"));
        suite.addTest(new Test_ModuleManager("TC_5_7_getAllModules"));
        return suite;
    }

    public void TC_5_1_getInstance() {
        System.out.println("Executing TC_5_1...");
        //check if instantiation is correct
        ModuleManager m_db = ModuleManager.getInstance();
        assertNotNull(m_db);

        //check if singleton pattern works properly
        ModuleManager m2_db = ModuleManager.getInstance();
        assertEquals("", m_db, m2_db);
        System.out.print("Done");
    }

    public void TC_5_2_createModule() {
        System.out.println("Executing TC_5_2...");
        ModuleManager moduleManager = ModuleManager.getInstance();
        assertTrue(moduleManager.createModule(new Module("Service Management", "0222500002")));
        System.out.print("Done");
    }

    public void TC_5_3_readModule() {
        System.out.println("Executing TC_5_3....");
        ModuleManager moduleManager = ModuleManager.getInstance();
        Module m = moduleManager.readModule("Service Management", "0222500002");
        System.out.println(m);
        Module test = new Module("Service Management", "0222500002");
        System.out.println(test);
        assertTrue(m.equals(test));
        System.out.print("Done");
    }

    public void TC_5_4_UpdateModule() {
        System.out.println("Executing TC_5_4....");
        ModuleManager moduleManager = ModuleManager.getInstance();
        Module m = new Module("Service Management", "0222500002");
        assertTrue(moduleManager.updateModule(m, "Project Management"));
        System.out.print("Done");
    }

    public void TC_5_5_DeleteModule() {
        System.out.println("Executing TC_5_5....");
        ModuleManager moduleManager = ModuleManager.getInstance();
        assertTrue(moduleManager.deleteModule("Project Management", "0222500002"));
        System.out.print("Done");
    }

    public void TC_5_6_getModulesByTeaching() {
        // TODO Auto-generated method stub
        System.out.println("Executing TC_5_6....");
        ModuleManager moduleManager = ModuleManager.getInstance();
        moduleManager.createModule(new Module("Service Management", "0222500002"));
        moduleManager.createModule(new Module("Project Management", "0222500002"));
        assertEquals((2 + 2), moduleManager.getModulesByTeaching("0222500002").size());
        //there are alreadt 2 modules in the db, testing version, so they are 4
        System.out.print("Done");
        moduleManager.deleteModule("Service Management", "0222500002");
        moduleManager.deleteModule("Project Management", "0222500002");
    }

    public void TC_5_7_getAllModules() {
        // TODO Auto-generated method stub
        System.out.println("Executing TC_5_7....");
        ModuleManager moduleManager = ModuleManager.getInstance();
        moduleManager.createModule(new Module("Service Management", "0222500002"));
        moduleManager.createModule(new Module("Project Management", "0222500002"));
        assertEquals(4, moduleManager.getAllModules().size());
        //there are alreadt 2 modules in the db, testing version, so they are 4

        System.out.print("Done");
        moduleManager.deleteModule("Service Management", "0222500002");
        moduleManager.deleteModule("Project Management", "0222500002");
    }

}
