package it.unisa.offerta_formativa.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unisa.offerta_formativa.beans.Module;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessandro, Antonio
 *
 */
public class ModuleManager {

    private final Connection conn = null;
    private Statement stmt;
    private static final String TABLE = "module";
    private static final String PKEY = "idModule";
    private ResultSet rs;

    private static ModuleManager instance = null;

    private ModuleManager() {
    }

    /**
     * Create a new module DB record
     *
     * @param m
     * @return true if done.
     */
    public boolean createModule(Module m) {

        try {
            stmt = DBConnection.getConnection().createStatement();

            String query = "INSERT INTO " + TABLE
                    + "(teaching_matricula,title) VALUES("
                    + m.toStringQueryInsert() + ")";

            if (stmt.executeUpdate(query) == 1) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(conn);
        }
        return false;
    }

    /**
     * Get the module with the given id
     *
     * @param title
     * @param teachinMatricula
     * @return Module bean read. Empty if not found any.
     */
    public Module readModule(String title, String teachinMatricula) {

        try {
            stmt = DBConnection.getConnection().createStatement();

            String esc = "\'";
            String query = "SELECT * FROM " + TABLE + " WHERE title=" + esc + title + esc
                    + " and " + "teaching_matricula=" + esc + teachinMatricula + esc;
            //System.out.println(query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                return getModuleFromResultSet(rs);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(conn);
        }
        return null;
    }

    /**
     * Update Module information with the given Module bean
     *
     * @param m
     * @return true if done.
     */
    public boolean updateModule(Module oldModule, String newTitle) {

        try {
            stmt = DBConnection.getConnection().createStatement();

            String esc = "\'";
            String query = "UPDATE " + TABLE + " SET title="
                    + esc + newTitle + esc
                    + " WHERE teaching_matricula=" + esc + oldModule.getTeachingMatricula() + esc
                    + " AND " + "title=" + esc + oldModule.getTitle() + esc;
            System.out.println(query);
            if (stmt.executeUpdate(query) == 1) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(conn);
        }
        return false;
    }

    /**
     * Delete the record with the given idModule field
     *
     * @param idModule
     * @return true if deleted.
     */
    public boolean deleteModule(String title, String teachingMatricula) {

        try {
            stmt = DBConnection.getConnection().createStatement();

            String esc = "\'";
            String query = "DELETE FROM " + TABLE + " WHERE title=" + esc + title + esc
                    + " and " + "teaching_matricula=" + esc + teachingMatricula + esc;
            if (stmt.executeUpdate(query) == 1) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(conn);
        }
        return false;
    }

    /**
     * Singleton Pattern
     *
     * @return
     */
    public static ModuleManager getInstance() {
        if (instance == null) {
            instance = new ModuleManager();
        }
        return instance;
    }

    /**
     * Given the teaching_matricula returns a collection of related Modules
     *
     * @param teaching_matricula
     * @return ArrayList<Module>, empty if it has not found any
     */
    public ArrayList<Module> getModulesByTeaching(String teaching_matricula) {

        ArrayList<Module> toReturn = new ArrayList<Module>();
        if (teaching_matricula == null) {
            throw new IllegalArgumentException("id cannot be null!");
        } else {
            try {
                stmt = DBConnection.getConnection().createStatement();

                String query = "SELECT * FROM " + TABLE
                        + " WHERE teaching_matricula=\""
                        + teaching_matricula + "\""
                        + " order by title";

                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    toReturn.add(getModuleFromResultSet(rs));
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                DBConnection.releaseConnection(conn);
            }
        }
        return toReturn;
    }

    /**
     * Get the collection of Modules
     *
     * @return ArrayList of Module. Empty if not found any.
     */
    public ArrayList<Module> getAllModules() {
        ArrayList<Module> toReturn = new ArrayList<Module>();
        try {
            stmt = DBConnection.getConnection().createStatement();

            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " order by title");
            while (rs.next()) {
                toReturn.add(new Module(rs.getString("teaching_matricula"), rs.getString("title")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(conn);
        }
        return toReturn;
    }

    private Module getModuleFromResultSet(ResultSet rs) {
        String title;
        try {
            title = rs.getString("title");
            String teach_matr = rs.getString("teaching_matricula");
            return new Module(title, teach_matr);
        } catch (SQLException ex) {
            Logger.getLogger(ModuleManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
