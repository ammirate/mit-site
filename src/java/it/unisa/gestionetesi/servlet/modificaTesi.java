/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.servlet;

import it.unisa.gestionetesi.beans.Cronologia;
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
 * @author CosimoAlessandro
 */
@WebServlet(name = "modificaTesi", urlPatterns = {"/modificaTesi"})

public class modificaTesi extends HttpServlet {

    final static Logger logger = Logger.getLogger("richiestaCompletamentoTesi");
    private ManagerTesi manager_tesi;
    private ManagerCronologia manager_cronologia;
    private PersonManager manager_utente;
    private String testoNotifica, nomeStudente, nomeDocente;

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        try {

            int id_tesi = Integer.parseInt(request.getParameter("id_tesi"));
            String titolo = request.getParameter("titolo");
            String abstr = request.getParameter("abstract");
            String data_inizio = request.getParameter("data_inizio");
            String data_fine = request.getParameter("data_fine");
            String data_fine_prevista = request.getParameter("data_fine_prevista");

            manager_tesi = new ManagerTesi();

            manager_tesi.modificaTesi(id_tesi, titolo, abstr, data_inizio, data_fine_prevista, data_fine);

            Person p = (Person) session.getAttribute("person");
            String ssn_studente = p.getSsn();
            manager_utente = new PersonManager();
            manager_cronologia = new ManagerCronologia();
            nomeStudente = manager_utente.getPersonBySSN(ssn_studente).getSurname() + " " + manager_utente.getPersonBySSN(ssn_studente).getName();
            String ssn_professore = manager_tesi.selezionaRelatoriTesi(id_tesi).get(0).getId_docente();
            nomeDocente = manager_utente.getPersonBySSN(ssn_professore).getSurname() + " " + manager_utente.getPersonBySSN(ssn_professore).getName();
            testoNotifica = "lo studente " + nomeStudente + " ha apportato delle modifiche alla propria Tesi in Corso.";
            Cronologia cronoRichiesta = new Cronologia();
            cronoRichiesta.setTesto(testoNotifica);
            cronoRichiesta.setId_studente(ssn_studente);
            cronoRichiesta.setId_docente(ssn_professore);
            cronoRichiesta.setTipo("modifica");

            manager_cronologia.inserisciEvento(cronoRichiesta);
            // response.sendRedirect("gestioneTesi/index.jsp");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(modificaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(modificaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(modificaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(modificaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
            Logger.getLogger(modificaTesi.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
