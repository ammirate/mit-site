
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.manager;

import it.unisa.gestionetesi.beans.Allegati;
import it.unisa.gestionetesi.beans.RelatoreTesi;
import it.unisa.gestionetesi.beans.Tag;
import it.unisa.gestionetesi.beans.Tesi;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.exception.ConnectionException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Damiano
 */
public class ManagerTesi {

    Logger logger = Logger.getLogger("db");
   
    Statement tesiStatement;

    public ManagerTesi() throws ClassNotFoundException, SQLException, IOException, InstantiationException, IllegalAccessException {
       
    }

    public void inserisciTesiQuery(Tesi T) throws SQLException {
        Connection db = null;
        try {
            db = DBConnection.getConnection();
            Tesi tesi = T;

            tesiStatement = db.createStatement();
            String query = "INSERT INTO thesis (Description, ID_Student, Thesis_Status)"
                    + "VALUES ('" + tesi.getDescrizione() + "', '" + tesi.getId_studente() + "' , '0')";
           tesiStatement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            db.commit();
            logger.info(" eseguo la query di inserimento tesi " );

        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            DBConnection.releaseConnection(db);

        }
    }

    public boolean inserisciRelatoreTesiQuery(RelatoreTesi rt) throws SQLException {
        boolean success = false;
        Connection db = null;
        try {
            db = DBConnection.getConnection();
            RelatoreTesi relatoreTesi = rt;

            Statement aStatement = db.createStatement();
            String query = "INSERT INTO `thesis_supervisor`(ID_Thesis, ID_Professor)"
                    + "VALUES ('" + relatoreTesi.getId_tesi() + "', '" + relatoreTesi.getId_docente() + "')";
            aStatement.executeUpdate(query);
            db.commit();
            success = true;
        } catch (SQLException ex) {
            success = false;
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
            logger.info(ex.getMessage());
        } finally {

            DBConnection.releaseConnection(db);

        }

        return success;
    }

    public int ultimaTesiInserita() {

        Connection db = null;
        int ultimaTesiInserita = -1;

        try {
            db = DBConnection.getConnection();
            Statement aStatement = db.createStatement();
            //         ResultSet rs = aStatement.getGeneratedKeys();

            String query = "SELECT * FROM thesis";

            ResultSet rs = aStatement.executeQuery(query);

            while (rs.next()) {

                ultimaTesiInserita = rs.getInt("ID");

            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            DBConnection.releaseConnection(db);

        }

        return ultimaTesiInserita;
    }

    public void inserisciTagQuery(Tag t) throws ClassNotFoundException, SQLException, IOException {
        Connection db = DBConnection.getConnection();
        try {
            Statement aStatement = db.createStatement();
            String query = "INSERT INTO `Tag` (Nome) VALUES (`" + t.getNomeTag() + ")";
            aStatement.executeQuery(query);
            db.commit();

        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            DBConnection.releaseConnection(db);
        }
    }

    public void inserisciAllegatiQuery(Allegati al) throws ClassNotFoundException, SQLException, IOException {
        Connection db = DBConnection.getConnection();
        try {
            Statement aStatement = db.createStatement();
            String query = "INSERT INTO `Attachment` (Object,ID_Thesis,Status) VALUES (`" + al.getLinkOggetto() + "`, `" + al.getIdTesi() + "`, `" + al.getStato() + "`)";
            aStatement.executeQuery(query);
            db.commit();

        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            DBConnection.releaseConnection(db);
        }
    }

    public void inserisciTagTesiQuery(Tesi tesi, Tag tag) throws ClassNotFoundException, SQLException, IOException {
        Connection db = DBConnection.getConnection();
        try {
            Statement aStatement = db.createStatement();
            String query = "INSERT INTO `tag_thesis`(ID_thesis,ID_tag) VALUES ([`" + tesi.getId_tesi() + "`],[`" + tag.getId() + ")";
            aStatement.executeQuery(query);
            db.commit();

        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            DBConnection.releaseConnection(db);
        }
    }

    public Tesi selezionaTesi(String id_student) {
        Connection db = null;
        Tesi T = null;

        ResultSet rs = null;

        try {
            db = DBConnection.getConnection();
            Statement aStatement = db.createStatement();
            String query = "SELECT * FROM thesis WHERE ID_Student= '" + id_student + "' ";

            rs = aStatement.executeQuery(query);

            int id_tesi = 0;
            String data_inizio = null;
            String data_fine = null;
            String data_fine_prevista = null;
            String titolo = null;
            String abstractTesi = null;
            String descrizione = null;
            String id_studente = null;
            String stato_tesi = null;

            while (rs.next()) {
                id_tesi = rs.getInt("ID");
                data_inizio = rs.getString("Start_Date");
                data_fine = rs.getString("End_Date");
                data_fine_prevista = rs.getString("Expected_End_Date");
                titolo = rs.getString("Title");
                abstractTesi = rs.getString("Abstract");
                descrizione = rs.getString("Description");
                id_studente = rs.getString("ID_Student");
                stato_tesi = rs.getString("Thesis_Status");
            }

            if (stato_tesi != null) {
                T = new Tesi(id_tesi, data_inizio, data_fine, data_fine_prevista, titolo, abstractTesi, descrizione, id_studente, stato_tesi);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
            logger.info("sono nel carch" + ex.getErrorCode());
        } finally {

            DBConnection.releaseConnection(db);

        }
        return T;
    }

    public ArrayList<RelatoreTesi> selezionaRelatoriTesi(int id_tesi) {
        Connection db = null;
        RelatoreTesi rt = null;
        ArrayList<RelatoreTesi> listaRelatori = null;
        ResultSet rs = null;

        try {

            listaRelatori = new ArrayList<RelatoreTesi>();
            db = DBConnection.getConnection();
            Statement aStatement = db.createStatement();
            String query = "SELECT * FROM thesis_supervisor JOIN person ON thesis_supervisor.ID_Professor=person.SSN WHERE ID_Thesis= '" + id_tesi + "' ";
            rs = aStatement.executeQuery(query);

            while (rs.next()) {
                rt = new RelatoreTesi();
                rt.setId_docente(rs.getString("ID_Professor"));
                rt.setNome(rs.getString("name"));
                rt.setCognome(rs.getString("surname"));
                rt.setId_tesi(rs.getInt("ID_Thesis"));

                listaRelatori.add(rt);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            DBConnection.releaseConnection(db);

        }

        return listaRelatori;
    }

    public boolean accettaTesi(int idTesi) throws SQLException {
        Connection db = null;
        boolean success = false;
        try {
            db = DBConnection.getConnection();
            Statement aStatement = db.createStatement();
            String accetta = "UPDATE `db_distra`.`thesis` SET `Description` = '', `Thesis_Status` = '1' WHERE `thesis`.`ID` =" + idTesi;
            aStatement.executeUpdate(accetta);
            db.commit();
            success = true;

        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
            logger.info("errore nell'sql: " + ex.getErrorCode());
            success = false;
        } finally {

            DBConnection.releaseConnection(db);

        }
        return success;
    }

    public boolean accettaCompletamentoTesi(int idTesi) throws SQLException {
        Connection db = null;
        boolean success = false;
        try {
            db = DBConnection.getConnection();
            Statement aStatement = db.createStatement();
            String accetta = "UPDATE `db_distra`.`thesis` SET `Thesis_Status` = '3' WHERE `thesis`.`ID` =" + idTesi;
            aStatement.executeUpdate(accetta);
            db.commit();

            success = true;
        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
            logger.info("sei nel catch" + ex.getErrorCode());
            success = false;
        } finally {

            DBConnection.releaseConnection(db);

        }
        return success;

    }

    public boolean rifiutaCompletamentoTesi(int idTesi) throws SQLException {
        Connection db = null;
        boolean success = false;
        try {
            db = DBConnection.getConnection();
            Statement aStatement = db.createStatement();
            String accetta = "UPDATE `db_distra`.`thesis` SET `Thesis_Status` = '1' WHERE `thesis`.`ID` =" + idTesi;
            aStatement.executeUpdate(accetta);
            logger.info("sei nel try di rifiuta completamento tesi");
            db.commit();

            success = true;
        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
            logger.info("sei nel catch di rifiuta completamento tesi" + ex.getErrorCode());
            success = false;
        } finally {

            DBConnection.releaseConnection(db);

        }
        return success;
    }

    public ArrayList<Tesi> elencaTesiDocente(String idRelatore) {
        Connection db = null;
        /*
         *   return: elenco tesi associati al prof
         */
        ArrayList<Tesi> elencoTesi = new ArrayList<Tesi>();
        try {
            db = DBConnection.getConnection();
            Statement aStatement = db.createStatement();
            Tesi T;

            String queryCercaTesi = "SELECT * FROM thesis_supervisor, thesis WHERE thesis_supervisor.ID_Thesis=thesis.ID AND thesis_supervisor.ID_Professor='" + idRelatore + "'";

            ResultSet res;
            res = aStatement.executeQuery(queryCercaTesi);

            int id_tesi = 0;
            String data_inizio = null;
            String data_fine = null;
            String data_fine_prevista = null;
            String titolo = null;
            String abstractTesi = null;
            String descrizione = null;
            String id_studente = null;
            String stato_tesi = null;
            logger.info("elencaTesiDocente la query non crasha");
            while (res.next()) {

                id_tesi = res.getInt("ID");
                data_inizio = res.getString("Start_Date");
                data_fine = res.getString("End_Date");
                data_fine_prevista = res.getString("Expected_End_Date");
                titolo = res.getString("Title");
                abstractTesi = res.getString("Abstract");
                descrizione = res.getString("Description");
                id_studente = res.getString("ID_Student");
                stato_tesi = res.getString("Thesis_Status");

                if (stato_tesi != null) {
                    T = new Tesi(id_tesi, data_inizio, data_fine, data_fine_prevista, titolo, abstractTesi, descrizione, id_studente, stato_tesi);
                    elencoTesi.add(T);

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class
                    .getName()).log(Level.SEVERE, null, ex);
            logger.info(
                    "la query crasha" + ex.getErrorCode());
        } finally {

            DBConnection.releaseConnection(db);

        }
        return elencoTesi;

    }

    public Tesi recuperaTesi(int idTesi) {
        Connection db = null;
        Tesi T = null;
        ResultSet rs = null;

        try {
            db = DBConnection.getConnection();
            Statement aStatement = db.createStatement();
            String query = "SELECT * FROM thesis WHERE ID= '" + idTesi + "' ";

            rs = aStatement.executeQuery(query);

            logger.info("Numero di righe: " + rs.getRow());

            int id_tesi = 0;
            String data_inizio = null;
            String data_fine = null;
            String data_fine_prevista = null;
            String titolo = null;
            String abstractTesi = null;
            String descrizione = null;
            String id_studente = null;
            String stato_tesi = null;

            while (rs.next()) {
                id_tesi = rs.getInt("ID");
                data_inizio = rs.getString("Start_Date");
                data_fine = rs.getString("End_Date");
                data_fine_prevista = rs.getString("Expected_End_Date");
                titolo = rs.getString("Title");
                abstractTesi = rs.getString("Abstract");
                descrizione = rs.getString("Description");
                id_studente = rs.getString("ID_Student");
                stato_tesi = rs.getString("Thesis_Status");
            }

            if (stato_tesi != null) {
                T = new Tesi(id_tesi, data_inizio, data_fine, data_fine_prevista, titolo, abstractTesi, descrizione, id_studente, stato_tesi);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class
                    .getName()).log(Level.SEVERE, null, ex);
            logger.info(
                    "sono nel carch" + ex.getErrorCode());
        } finally {

            DBConnection.releaseConnection(db);

        }
        return T;
    }

    public boolean inAttesaConferma(int idTesi) throws SQLException {
        Connection db = null;
        ResultSet res = null;
        try {
            db = DBConnection.getConnection();
            Statement aStatement = db.createStatement();
            String queryStatoTesi = "SELECT thesis_status FROM thesis WHERE ID='" + idTesi + "'";
            res = aStatement.executeQuery(queryStatoTesi);
        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            DBConnection.releaseConnection(db);

        }
        if (res != null) {
            return res.getInt("thesis_status") == 0;
        } else {
            return false;
        }
    }

    public boolean inAttesaCompletamento(int idTesi) throws SQLException {
        Connection db = null;

        ResultSet res = null;
        try {
            db = DBConnection.getConnection();
            Statement aStatement = db.createStatement();
            String queryStatoTesi = "SELECT thesis_status FROM thesis WHERE ID='" + idTesi + "'";
            res = aStatement.executeQuery(queryStatoTesi);
        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            DBConnection.releaseConnection(db);

        }
        if (res != null) {
            return res.getInt("thesis_status") == 2;
        } else {
            return false;
        }
    }

    public boolean rifiutaTesi(int idTesi) throws SQLException {
        Connection db = DBConnection.getConnection();
        boolean success = false;
        try {
            Statement aStatement = db.createStatement();

            String rifiuta = "DELETE FROM thesis WHERE ID=" + idTesi;
            aStatement.executeUpdate(rifiuta);
            db.commit();

            success = true;
        } catch (SQLException ex) {
            success = false;
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
            logger.info("sei nel catch" + ex.getErrorCode());
        } finally {

            DBConnection.releaseConnection(db);

        }
        return success;
    }

    public void richiestaCompletamentoTesi(int idTesi) throws SQLException {
        Connection db = null;
        try {
            db = DBConnection.getConnection();
            Statement aStatement = db.createStatement();
            String completa = "UPDATE thesis SET Thesis_Status = '2' WHERE ID=" + idTesi;
            aStatement.executeUpdate(completa);
            logger.info("completa tesi sei nel try");
            db.commit();

        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
            logger.info("comple tesi sei nel catch" + ex.getErrorCode());
        } finally {
            DBConnection.releaseConnection(db);
        }

    }

    public void modificaTesi(int id_tesi, String titolo, String abstr, String data_inizio, String data_fine_prevista, String data_fine) {
        Connection db = null;
        try {
            db = DBConnection.getConnection();

            Statement aStatement = db.createStatement();
            
            String update = "UPDATE thesis SET Title = '" + titolo + "' , Abstract = ' " + abstr + "', Start_Date = '" + data_inizio + "', Expected_End_Date = '" + data_fine_prevista + "', End_Date = '" + data_fine + "' WHERE ID = " + id_tesi;
	    System.out.println(update);
            aStatement.executeUpdate(update);
	    db.commit();

        } catch (SQLException ex) {

            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
            logger.info("sei nel catch_" + ex.getMessage());
        } finally {
            DBConnection.releaseConnection(db);
        }

    }
    
    /**
     * Ricerca le tesi in base ad uno dei seguenti criteri di ricerca:
     * <ol>
     * <li> Dipartimento </li>
     * <li> Corso di laurea </li>
     * <li> Professore </li>
     * <li> Studente </li>
     * <li> Titolo </li>
     * <li> Anno di laurea </li>
     * <li> Tag </li>
     * </ol>
     * @param researchCriteria La serie di parametri di ricerca.
     * @return Una lista di tesi, corrispondenti ad un insieme dei parametri di ricerca suddetti.
     */
    public List<Tesi> getThesisByResearchCriteria(String[] researchCriteria) throws ConnectionException, SQLException {
        List<Tesi> T = new ArrayList<Tesi>();
        Tesi thesis = null;
	Connection connection = null;
	Statement stmt = null;
	ResultSet rs = null;
        try {
            String query = "";
	    
            query += "SELECT thesis_professor_student.id_thesis, thesis_professor_student.Title, " +
		    "thesis_professor_student.End_Date, thesis_professor_student.abstract, " +
		    "thesis_professor_student.Description, thesis_professor_student.id_tag, " +
		    "thesis_professor_student.tag_name, thesis_professor_student.ID_Professor, " +
		    "thesis_professor_student.department, thesis_professor_student.degree, " +
		    "person.name as prof_name, person.surname as prof_surname, " +
		    "thesis_professor_student.ID_Student, thesis_professor_student.student_name, " +
		    "thesis_professor_student.student_surname \n" +
            "FROM " +
	    "	(SELECT thesis_student.id_thesis, thesis_student.Title, thesis_student.End_Date, " +
		    "thesis_student.abstract, thesis_student.Description, thesis_student.id_tag, " +
		    "thesis_student.tag_name, thesis_student.department, " +
		    "thesis_student.degree, thesis_student.ID_Student, " +
		    "thesis_student.student_name, thesis_student.student_surname, " +
		    "thesis_supervisor.ID_Professor \n" +
	    "	FROM " +
	    "	    (SELECT thesis_tag.id as id_thesis, thesis_tag.Title, thesis_tag.End_Date, " +
		    "thesis_tag.abstract, thesis_tag.Description, thesis_tag.id_tag, thesis_tag.tag_name, " +
		    "thesis_tag.ID_Student, person.Department_abbreviation as department, " +
		    "person.degree_matricula as degree, person.name as student_name, " +
		    "person.surname as student_surname \n" + 
	    "	    FROM " +
	    "		(SELECT * " +
	    "		FROM thesis JOIN " +
	    "		(SELECT ID_thesis, ID_tag, Name as tag_name " +
	    "		    FROM thesis_tag JOIN tag ON ID_tag = tag.id) AS tmp_tag " +
	    "		ON thesis.id = tmp_tag.ID_thesis) AS thesis_tag \n" +
	    "	    JOIN person " +
	    "	    ON ID_Student = ssn " +
	    "	    WHERE thesis_tag.Thesis_Status > 0) AS thesis_student " +
	    "	JOIN thesis_supervisor " +
	    "	ON thesis_student.id_thesis = thesis_supervisor.id_thesis) AS thesis_professor_student " +
            "JOIN person ON thesis_professor_student.ID_Professor = person.ssn ";
            
	    if(researchCriteria != null) {
		boolean checkQuery = false;

		if(!researchCriteria[0].isEmpty()) {
		    query += "WHERE department = '" + researchCriteria[0] + "'";
		    checkQuery = true;
		}

		if(!researchCriteria[1].isEmpty()) {
		    if(!checkQuery) {
			query += "WHERE ";
			checkQuery = true;
		    } else {
			query += "AND ";
		    }

		    query += "degree = '" + researchCriteria[1] + "'";
		}

		if(!researchCriteria[2].isEmpty()) {
		    if(!checkQuery) {
			query += "WHERE ";
			checkQuery = true;
		    } else {
			query += "AND ";
		    }

		    query += "ID_Professor = \'" + researchCriteria[2] + "\'";
		}

		if(!researchCriteria[3].isEmpty()) {
		    if(!checkQuery) {
			query += "WHERE ";
			checkQuery = true;
		    } else {
			query += "AND ";
		    }

		    query += "ID_Student = \'" + researchCriteria[3] + "\'";
		}

		if(!researchCriteria[4].isEmpty()) {
		    if(!checkQuery) {
			query += "WHERE ";
			checkQuery = true;
		    } else {
			query += "AND ";
		    }

		    query += "id_thesis = '" + researchCriteria[4] + "'";
		}

		if(!researchCriteria[5].isEmpty()) {
		    if(!checkQuery) {
			query += "WHERE ";
			checkQuery = true;
		    } else {
			query += "AND ";
		    }

		    query += "id_tag = '" + researchCriteria[5] + "'";
		}

		if(!researchCriteria[6].isEmpty()) {
		    if(!checkQuery) {
			query += "WHERE ";
			checkQuery = true;
		    } else {
			query += "AND ";
		    }

		    query += "End_Date LIKE '" + researchCriteria[6] + "%'";
		}
	    }
	    System.out.println("Query di ricerca per tesi:\n" + query);
	    
            connection = DBConnection.getConnection();
            
            if (connection == null) {
                throw new ConnectionException();
            }

            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
	    
            while (rs.next()) {
		thesis = new Tesi(rs.getInt("id_thesis"), "", 
			rs.getString("End_Date"), "", 
			rs.getString("Title"), rs.getString("Abstract"),
			rs.getString("Description"), rs.getString("ID_Student"), 
			"");
		T.add(thesis);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerTesi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return T;
    }
}
