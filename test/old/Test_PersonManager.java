package old;

import it.unisa.integrazione.model.Person;
import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import it.unisa.offerta_formativa.manager.old.PersonManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alessandro, Antonio
 *
 */
public class Test_PersonManager extends TestCase {

    public Test_PersonManager(String s) {
        super(s);
    }

    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new Test_PersonManager("TC_11_1_getPersonByEmail"));
        suite.addTest(new Test_PersonManager("TC_11_2_getProfessorsByDepartment"));
        
        return suite;
    }

    @Test
    public void test() {
        suite();
    }

    /**
     * 
     */
    public void TC_11_1_getPersonByEmail() {
        System.out.print("Executing TC_10_2...");
          PersonManager pm = PersonManager.getInstance();
          String email = "m.rossi@unisa.it";
          Person p = pm.getPersonByEmail(email);
          assertTrue(email.equalsIgnoreCase(p.getAccount().getEmail()));
        System.out.println("Done");
    }
    
    /**
     * 
     */
    public void TC_11_2_getProfessorsByDepartment(){
        List<Person> list = new ArrayList<>();
        PersonManager pm = PersonManager.getInstance();
        list = pm.getProfessorsByDepartment("DISTRA");
        assertEquals(1, list.size());
        //there is 1 professor which belongs to DISTRA in the DB, testing version
    }
}
