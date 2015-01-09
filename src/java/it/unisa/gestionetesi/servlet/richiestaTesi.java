/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.servlet;

import it.unisa.gestionetesi.beans.Cronologia;
import it.unisa.gestionetesi.beans.RelatoreTesi;
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
import javax.servlet.RequestDispatcher;
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
@WebServlet("/richiestaTesi")
public class richiestaTesi extends HttpServlet {

    final static Logger logger = Logger.getLogger("richiestaTesi");
    ManagerTesi manager_tesi;
    ManagerCronologia manager_cronologia;
    PersonManager manager_utente;
    String testoNotifica, nomeStudente, nomeDocente;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        try {

            String professore = request.getParameter("professore");
            String messaggio = request.getParameter("messaggio");
            Person p = (Person) session.getAttribute("person");
            String ssn_utente = p.getSsn();

            Tesi T = inserisciTesi(messaggio, ssn_utente);
            manager_tesi = new ManagerTesi();
            manager_tesi.inserisciTesiQuery(T);
            int ultimaTesiInserita = manager_tesi.ultimaTesiInserita();

            RelatoreTesi relatoreTesi = inserisciRelatoreTesi(professore, ultimaTesiInserita);
            if (manager_tesi.inserisciRelatoreTesiQuery(relatoreTesi)) {
                logger.info("true");

                manager_utente = new PersonManager();
                manager_cronologia = new ManagerCronologia();
                nomeStudente = manager_utente.getPersonBySSN(ssn_utente).getSurname() + " " + manager_utente.getPersonBySSN(ssn_utente).getName();
                nomeDocente = manager_utente.getPersonBySSN(professore).getSurname() + " " + manager_utente.getPersonBySSN(professore).getName();
                testoNotifica = "lo studente " + nomeStudente + " ha inviato una richiesta di tesi al prof. " + nomeDocente + " con il seguente messaggio: " + messaggio;
                Cronologia cronoRichiesta = new Cronologia();
                cronoRichiesta.setTesto(testoNotifica);
                cronoRichiesta.setId_studente(ssn_utente);
                cronoRichiesta.setId_docente(professore);
                cronoRichiesta.setTipo("richiesta");

                manager_cronologia.inserisciEvento(cronoRichiesta);
            } else {
                logger.info("false");
            }

            //request.getRequestDispatcher("gestioneTesi.jsp").forward(request, response); // Forward to same page so that you can display error.
            response.sendRedirect("gestioneTesi/index.jsp");

        } catch (ConnectionException ex) {
            Logger.getLogger(richiestaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    public Tesi inserisciTesi(String messaggio, String ssn_studente) {
        Tesi T = new Tesi(messaggio, ssn_studente);
        return T;
    }

    public RelatoreTesi inserisciRelatoreTesi(String professore, int tesi) {
        RelatoreTesi RT = new RelatoreTesi(professore, tesi);

        return RT;
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
            Logger.getLogger(richiestaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(richiestaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(richiestaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(richiestaTesi.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(richiestaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(richiestaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(richiestaTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(richiestaTesi.class.getName()).log(Level.SEVERE, null, ex);
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
