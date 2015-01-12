
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.manager;
 
import it.unisa.gestionetesi.beans.Cronologia;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 *
 * @author ciro
 */
public class ManagerCronologia {
 
    Logger logger = Logger.getLogger("db");
//    private ConnectionDB aConnection;
//    private Connection db;
    Statement tesiStatement;
 
    public ManagerCronologia() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
 
    }
 
    public void inserisciEvento(Cronologia crono) throws SQLException {
               Connection db = DBConnection.getConnection();
 
        try {
            Statement aStatement = db.createStatement();
 
            String q = "INSERT INTO `chronology`(text, ID_Professor, ID_Student, Type)"
                    + "VALUES ( '" + crono.getTesto() + " ', '" + crono.getId_docente() + "' , '" + crono.getId_studente() + "', '" + crono.getTipo() + "')";
 
            aStatement.executeUpdate(q);
            db.commit();
        } catch (SQLException ex) {
            logger.info("query fallita: " + ex.getMessage());
            Logger.getLogger(ManagerCronologia.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
     
          DBConnection.releaseConnection(db);
         
        }
    }
 
    public boolean eliminaEvento(int idCronologia) {
        Connection db = null;
        boolean wellDone = true;
        try {
            db = DBConnection.getConnection();
            Statement aStatement = db.createStatement();
            String queryNuovoEvento = "DELETE FROM `db_distra`.`chronology` WHERE ID=" + idCronologia;
            wellDone = aStatement.execute(queryNuovoEvento);
             db.commit();

        } catch (SQLException ex) {
            logger.info("query fallita: " + ex.getMessage());
            wellDone = false;
            Logger.getLogger(ManagerCronologia.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
     
          DBConnection.releaseConnection(db);
         
        }
       
        return wellDone;
 
    }
 
    public Cronologia selezionaEvento(int idCronologia) {
        Connection db = null;
        Cronologia crono = null;
 
        ResultSet res = null;
 
        try {
            db = DBConnection.getConnection();
            Statement aStatement = db.createStatement();
            String query = "SELECT * FROM chronology WHERE ID='" + idCronologia + "' ";
 
            res = aStatement.executeQuery(query);
 
            int id_cronologia = 0;
            String testo = null, data_notifica = null, id_studente = null, id_docente = null, tipo = null;
 
            id_cronologia = res.getInt("ID");
            testo = res.getString("Text");
            data_notifica = res.getString("Notice_Date");
            id_studente = res.getString("ID_Student");
            id_docente = res.getString("ID_Professor");
            tipo = res.getString("Type");
            crono = new Cronologia(id_cronologia, testo, data_notifica, id_studente, id_docente, tipo);
 
        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
            logger.info("sono nel catch: " + ex.getErrorCode());
        }finally{
     
          DBConnection.releaseConnection(db);
         
        }
        return crono;
    }
 
    public ArrayList<Cronologia> elencaEventiStudente(String idStudente) {
                Connection db = null;
 
        ArrayList<Cronologia> cronos = new ArrayList<Cronologia>();
        Cronologia c;
        ResultSet res = null;
 
        try {
            db = DBConnection.getConnection();
            Statement aStatement = db.createStatement();
            String query = "SELECT * FROM chronology WHERE ID_Student='" + idStudente + "' ";
 
            res = aStatement.executeQuery(query);
 
            int id_cronologia = 0;
            String testo = null, data_notifica = null, id_studente = null, id_docente = null, tipo=null;
 
            while (res.next()) {
 
                id_cronologia = res.getInt("ID");
                testo = res.getString("Text");
                data_notifica = res.getString("Notice_Date");
                id_studente = res.getString("ID_Student");
                id_docente = res.getString("ID_Professor");
                tipo=res.getString("Type");
                logger.info("TESTO:" + testo);
                c = new Cronologia(id_cronologia, testo, data_notifica, id_studente, id_docente, tipo);
                cronos.add(c);
            }
 
        } catch (SQLException ex) {
            logger.info("FALLIMENTO SQL: " + ex.getMessage());
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
            logger.info("sono nel catch: " + ex.getErrorCode());
        }finally{
     
          DBConnection.releaseConnection(db);
         
        }
 
        System.out.print(cronos.toString());
        return cronos;
    }
 
    public ArrayList<Cronologia> elencaEventiDocente(String idDocente) throws SQLException {
                Connection db = null;
 
        ArrayList<Cronologia> cronos = new ArrayList<Cronologia>();
        Cronologia c;
        ResultSet res = null;
 
        try {
            db = DBConnection.getConnection();
            Statement aStatement = db.createStatement();
            String query = "SELECT * FROM chronology WHERE ID_Professor='" + idDocente + "' ";
 
            res = aStatement.executeQuery(query);
            int id_cronologia = 0;
            String testo = null, data_notifica = null, id_studente = null, id_docente = null, tipo = null;
 
            while (res.next()) {
                tipo = res.getString("Type");
                id_cronologia = res.getInt("ID");
                testo = res.getString("Text");
                data_notifica = res.getString("Notice_Date");
                id_studente = res.getString("ID_Student");
                id_docente = res.getString("ID_Professor");
                logger.info("TESTO:" + testo);
                c = new Cronologia(id_cronologia, testo, data_notifica, id_studente, id_docente, tipo);
                cronos.add(c);
            }
 
        } catch (SQLException ex) {
            logger.info("FALLIMENTO SQL: " + ex.getMessage());
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
            logger.info("sono nel catch: " + ex.getErrorCode());
        }finally{
     
          DBConnection.releaseConnection(db);
         
        }
        return cronos;
    }
 
}