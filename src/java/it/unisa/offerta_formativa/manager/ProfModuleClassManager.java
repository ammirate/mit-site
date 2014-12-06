package it.unisa.offerta_formativa.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.unisa.offerta_formativa.beans.ProfModuleClass;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alessandro
 *
 */
public class ProfModuleClassManager {

    private static final String TABLE = "prof_module_class";
    private final Connection conn = null;
    private Statement stmt;
    private ResultSet rs;

    private static ProfModuleClassManager instance = null;

    private ProfModuleClassManager() {
    }

    public boolean create(ProfModuleClass pmc) {
        stmt = DBConnector.openConnection();

        try {
            String esc = "\'";
            String query = "INSERT INTO " + TABLE
                    + "(class_title, "
                    + "teaching_matricula, "
                    + "module_title,"
                    + "email_account) VALUES("
                    + esc + pmc.getClassTitle() + esc + ","
                    + esc + pmc.getTeachingMatricula() + esc + ","
                    + esc + pmc.getModuleTitle() + esc + ","
                    + esc + pmc.getProfEmail() + esc
                    + ")";
            if (stmt.executeUpdate(query) == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Insert Query Failed");
        } finally {
            DBConnector.closeConnection();
        }
        return false;
    }

    public static ProfModuleClassManager getInstance() {
        if (instance == null) {
            instance = new ProfModuleClassManager();
        }
        return instance;
    }

    
    /**
     * 
     * @return 
     */
    public List<ProfModuleClass> getAllRelations() {
        stmt = DBConnector.openConnection();
        List<ProfModuleClass> toReturn = new ArrayList<>();

        try {
            String esc = "\'";
            String query = "SELECT * FROM " + TABLE;
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                toReturn.add(getProfModuleClassFromRS(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Insert Query Failed");
        } finally {
            DBConnector.closeConnection();
        }
        return toReturn;
    }

    
    /**
     * 
     * @param rs
     * @return 
     */
    private ProfModuleClass getProfModuleClassFromRS(ResultSet rs) {
        try {
            String classTitle = rs.getString("class_title");
            String teachingMatricula = rs.getString("class_title");
            String moduleTitle = rs.getString("class_title");
            String profEmail = rs.getString("class_title");
            return new ProfModuleClass(classTitle, teachingMatricula, moduleTitle, profEmail);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    

}
