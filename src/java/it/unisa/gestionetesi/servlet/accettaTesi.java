/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.servlet;

import it.unisa.gestionetesi.beans.Cronologia;
import it.unisa.gestionetesi.beans.Tesi;
import it.unisa.gestionetesi.manager.ManagerCronologia;
import it.unisa.gestionetesi.manager.ManagerTesi;
import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.Person;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ciro
 */
@WebServlet(name = "accettaTesi", urlPatterns = {"/accettaTesi"})
public class accettaTesi extends HttpServlet {

    private ManagerTesi manager_tesi;
    private Tesi T = null;
    private int id_tesi;
    private int stato_tesi;
    final static Logger logger = Logger.getLogger("richiestaTesi");
    ManagerCronologia manager_cronologia;
    PersonManager manager_utente;
    String testoNotifica, nomeStudente, nomeDocente;
    Cronologia cronoAccetta;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {

            /* Inizio Generazione notifica nella timeline */
            Person docente = (Person) session.getAttribute("person");
            Person studente = null;

            String ssn_utente = docente.getSsn();
            manager_utente = new PersonManager();
            manager_cronologia = new ManagerCronologia();

            /* Fine Generazione notifica nella timeline */
            String accetta_tesi = request.getParameter("accetta");
            String rifiuta_tesi = request.getParameter("rifiuta");

            manager_tesi = new ManagerTesi();

            if (accetta_tesi != null) {

                id_tesi = Integer.parseInt(accetta_tesi);
                T = manager_tesi.recuperaTesi(id_tesi);
                stato_tesi = Integer.parseInt(T.getStato_tesi());
                String id_studente = T.getId_studente();
                studente = manager_utente.getPersonBySSN(id_studente);

                nomeStudente = studente.getSurname() + " " + studente.getName();
                nomeDocente = docente.getSurname() + " " + docente.getName();

                if (stato_tesi == 0) {
                    if (manager_tesi.accettaTesi(id_tesi)) {

                        testoNotifica = "il prof." + nomeDocente + " ha accettato la richiesta di " + nomeStudente + " per avviare un lavoro di tesi";

                        cronoAccetta = new Cronologia();
                        cronoAccetta.setTesto(testoNotifica);
                        cronoAccetta.setId_docente(ssn_utente);
                        cronoAccetta.setId_studente(id_studente);
                        cronoAccetta.setTipo("accetta");
                        manager_cronologia.inserisciEvento(cronoAccetta);
                    }
                }

                if (stato_tesi == 2) {
                    if (manager_tesi.accettaCompletamentoTesi(id_tesi)) {
                        if ((T.getData_fine() != null)) {
                            String data_fine = T.getData_fine();
                            logger.info("data fine:" + data_fine);
                            testoNotifica = "il prof." + nomeDocente + " ha accettato la richiesta di " + nomeStudente + " per avviare un lavoro di tesi. La seduta di laurea è prevista il giorno " + data_fine;
                        } else {
                            testoNotifica = "il prof." + nomeDocente + " ha convalidato la richiesta di " + nomeStudente + " per confermare il completamento del lavoro di tesi. ";
                        }
                        cronoAccetta = new Cronologia();
                        cronoAccetta.setTesto(testoNotifica);
                        cronoAccetta.setId_docente(ssn_utente);
                        cronoAccetta.setId_studente(id_studente);
                        cronoAccetta.setTipo("accetta");

                        manager_cronologia.inserisciEvento(cronoAccetta);
                    }
                }
            }

            if (rifiuta_tesi != null) {
                id_tesi = Integer.parseInt(rifiuta_tesi);
                T = manager_tesi.recuperaTesi(id_tesi);
                stato_tesi = Integer.parseInt(T.getStato_tesi());
                String id_studente = T.getId_studente();
                studente = manager_utente.getPersonBySSN(id_studente);
                nomeStudente = studente.getSurname() + " " + studente.getName();
                nomeDocente = docente.getSurname() + " " + docente.getName();

                if (stato_tesi == 0) {
                    if (manager_tesi.rifiutaTesi(id_tesi)) {
                        logger.info("true");
                        testoNotifica = "il prof." + nomeDocente + " ha rifiutato la richiesta di " + nomeStudente + " per avviare un lavoro di tesi";

                        cronoAccetta = new Cronologia();
                        cronoAccetta.setTesto(testoNotifica);
                        cronoAccetta.setId_docente(ssn_utente);
                        cronoAccetta.setId_studente(id_studente);
                        cronoAccetta.setTipo("rifiuta");

                        manager_cronologia.inserisciEvento(cronoAccetta);
                    } else {
                        logger.info("false");
                    }
                }

                if (stato_tesi == 2) {
                    if (manager_tesi.rifiutaCompletamentoTesi(id_tesi)) {
                        logger.info("true");
                        testoNotifica = "il prof." + nomeDocente + " ha rifiutato la richiesta di " + nomeStudente + " di completamento del lavoro di tesi. Controlla che tutti i campi siano stati compilati correttamente o prova a contattare il docente.";

                        cronoAccetta = new Cronologia();
                        cronoAccetta.setTesto(testoNotifica);
                        cronoAccetta.setId_docente(ssn_utente);
                        cronoAccetta.setId_studente(id_studente);
                                                cronoAccetta.setTipo("rifiuta");

                        manager_cronologia.inserisciEvento(cronoAccetta);
                    }
                    logger.info("false");
                }
            }

            response.sendRedirect("gestioneTesi/index.jsp");

        } catch (ConnectionException ex) {
            Logger.getLogger(accettaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(accettaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(accettaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(accettaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(accettaTesi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(accettaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(accettaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(accettaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(accettaTesi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
