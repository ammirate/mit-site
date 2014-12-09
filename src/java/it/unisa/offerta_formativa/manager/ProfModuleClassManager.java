package it.unisa.offerta_formativa.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.unisa.offerta_formativa.beans.ProfModuleClass;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     *
     * @param profEmail
     * @param pmc
     * @return
     */
    public boolean updateProfessor(String profEmail, ProfModuleClass pmc) {
        pmc.setProfEmail(profEmail);
        ProfModuleClassManager manager = ProfModuleClassManager.getInstance();
        if (manager.delete(pmc)) {
            return manager.create(pmc);
        }
        return false;
    }

    /**
     *
     * @param pmc
     * @return
     */
    public boolean delete(ProfModuleClass pmc) {
        String esc = "\'";
        String query = "DELETE FROM " + TABLE + " WHERE "
                + "class_title= " + esc + pmc.getClassTitle() + esc + " and "
                + "teaching_matricula= " + esc + pmc.getTeachingMatricula() + esc + " and "
                + "module_title= " + esc + pmc.getModuleTitle() + esc + " and "
                + "email_account= " + esc + pmc.getProfEmail() + esc;
        try {
            stmt = DBConnector.openConnection();
            if (stmt.executeUpdate(query) == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfModuleClassManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnector.closeConnection();
        }
        return false;
    }

    /**
     *
     * @param teachingMatricula
     * @return
     */
    public List<ProfModuleClass> getByTeaching(String teachingMatricula) {
        String esc = "\'";
        List<ProfModuleClass> toReturn = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE
                + " WHERE " + "teaching_matricula= " + esc + teachingMatricula + esc;
        System.out.println(query);

        try {
            stmt = DBConnector.openConnection();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                ProfModuleClass pmc = getProfModuleClassFromRS(rs);
                toReturn.add(pmc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfModuleClassManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnector.closeConnection();
        }
        return toReturn;
    }
    
    
    /**
     *
     * @param teachingMatricula
     * @return
     */
    public List<ProfModuleClass> getByClass(String classTitle) {
        String esc = "\'";
        List<ProfModuleClass> toReturn = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE
                + " WHERE " + "class_title= " + esc + classTitle + esc;
        System.out.println(query);
        try {
            stmt = DBConnector.openConnection();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                ProfModuleClass pmc = getProfModuleClassFromRS(rs);
                toReturn.add(pmc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfModuleClassManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnector.closeConnection();
        }
        return toReturn;
    }
    
    
    
        /**
     *
     * @param teachingMatricula
     * @return
     */
    public List<ProfModuleClass> getByModule(String moduleTitle) {
        String esc = "\'";
        List<ProfModuleClass> toReturn = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE
                + " WHERE " + "module_title= " + esc + moduleTitle + esc;
        System.out.println(query);

        try {
            stmt = DBConnector.openConnection();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                ProfModuleClass pmc = getProfModuleClassFromRS(rs);
                toReturn.add(pmc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfModuleClassManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnector.closeConnection();
        }
        return toReturn;
    }
    
    
    
    
            /**
     *
     * @param teachingMatricula
     * @return
     */
    public List<ProfModuleClass> getByEmail(String email) {
        String esc = "\'";
        List<ProfModuleClass> toReturn = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE
                + " WHERE " + "email_account= " + esc + email + esc;
        System.out.println(query);

        try {
            stmt = DBConnector.openConnection();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                ProfModuleClass pmc = getProfModuleClassFromRS(rs);
                toReturn.add(pmc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfModuleClassManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnector.closeConnection();
        }
        return toReturn;
    }
    
    
    
    
}
