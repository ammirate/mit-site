package old;

import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import it.unisa.offerta_formativa.manager.old.AccountManager;

/**
 *
 * @author Alessandro, Antonio
 *
 */
public class Test_AccountManager extends TestCase {

    public Test_AccountManager(String s) {
        super(s);
    }

    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new Test_AccountManager("TC_9_1_getAllAccounts"));
        return suite;
    }

    @Test
    public void test() {
        suite();
    }

    /**
     * insert a class into the DB
     */
    public void TC_9_1_getAllAccounts() {
        System.out.print("Executing TC_9_1...");
        AccountManager am = AccountManager.getInstance();
        assertEquals(2, am.getAllAccounts().size());
        //in the DB, testing version there are already 2 accounts
        System.out.println("Done");
    }


}
