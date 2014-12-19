package it.unisa.offerta_formativa.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unisa.offerta_formativa.beans.Cycle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessandro, Antonio
 */
public class CycleManager {

    private static CycleManager instance = null;
    private final Connection conn = null;
    private Statement stmt;
    private ResultSet rs;
    private static final String TABLE = "cycle";
    private static final String PKEY = "cycle_number";

    private CycleManager() {
    }

    /**
     * Insert a cycle into the DB
     *
     * @param c the cycle to insert
     * @return true if the cycle has been inserted, else false
     */
    public boolean createCycle(Cycle c) {

        try {
            stmt = DBConnector.openConnection();

            String query = "INSERT INTO " + TABLE
                    + "(cycle_number,title) VALUES ("
                    + c.getInsertQuery() + ")";
            if (stmt.executeUpdate(query) == 1) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
           DBConnector.closeConnection();
        }
        return false;
    }

    /**
     * Read a cycle from the DB
     *
     * @param idCycle the cycle to insert
     * @return the cycle red
     */
    public Cycle readCycle(int idCycle) {

        try {
            stmt = DBConnector.openConnection();

            String query = "SELECT * FROM " + TABLE
                    + " WHERE " + PKEY + "=" + idCycle;
//            System.out.println("READ QUERY" + query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                return getCycleFromResultSet(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CurriculumManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           DBConnector.closeConnection();
        }
        return null;
    }

    /**
     * update an existent cycle into the DB
     *
     * @param idOldCycle
     * @param newCycle
     * @return
     */
    public boolean updateCycle(int idOldCycle, Cycle newCycle) {

        try {
            stmt = DBConnector.openConnection();

            String query = "UPDATE " + TABLE + " SET " + newCycle.toString() + " WHERE "
                    + PKEY + "=" + idOldCycle;
//            System.out.println("update QUERY " + query);

            if (stmt.executeUpdate(query) == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Update Query failed!");
        } finally {
           DBConnector.closeConnection();
        }
        return false;
    }

    public boolean deleteCycle(int c) {
        return false;
    }

    /**
     * return all the cycles in the DB
     *
     * @return a list of cycles
     */
    public ArrayList<Cycle> getAllCycles() {
        ArrayList<Cycle> toReturn = new ArrayList<Cycle>();

        try {
            stmt = DBConnector.openConnection();

//            System.out.println("SELECT * FROM " + TABLE);
            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " order by title");
            while (rs.next()) {
                toReturn.add(new Cycle(rs.getInt("cycle_number"), rs.getString("title")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
           DBConnector.closeConnection();
        }
        return toReturn;
    }

    /**
     * Singleton Pattern
     *
     * @return
     */
    public static CycleManager getInstance() {
        if (instance == null) {
            instance = new CycleManager();
        }
        return instance;
    }

    /**
     * Create a Cycle from a ResultSet object
     *
     * @param rs the result set which take the cycle from.
     * @return the cycle generated from @param rs
     */
    private Cycle getCycleFromResultSet(ResultSet rs) {

        int cycleNum;
        try {
            cycleNum = rs.getInt("cycle_number");
            String title = rs.getString("title");
            return new Cycle(cycleNum, title);
        } catch (SQLException ex) {
            Logger.getLogger(CycleManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
