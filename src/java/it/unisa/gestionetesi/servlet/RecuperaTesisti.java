/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.servlet;

import it.unisa.gestionetesi.beans.Tesi;
import it.unisa.gestionetesi.manager.ManagerTesi;
import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.Person;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Damiano
 */
@WebServlet(name = "RecuperaTesisti", urlPatterns = {"/RecuperaTesisti"})

public class RecuperaTesisti extends HttpServlet {

    private ManagerTesi manager_tesi;
    private PersonManager manager_utente;
    private Person person;
    private ArrayList<Tesi> lista_tesi;
    private int lista_tesi_size = 0;
    private JSONArray jarray;
    private JSONObject tesi_data;
    private String id_stud;
    private String descr;
    private String[] nome_cognome;

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
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws org.json.JSONException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String id_docente = request.getParameter("id_docente");
            manager_tesi = new ManagerTesi();
            manager_utente = new PersonManager();
            lista_tesi = manager_tesi.elencaTesiDocente(id_docente);

            jarray = new JSONArray();   //JSON ARRAY

            lista_tesi_size = lista_tesi.size();

            nome_cognome = new String[lista_tesi_size]; //Array di Stringhe contenenti i nomi degli studenti

            System.out.print("lista_tesi_size: " + lista_tesi_size);

            for (int i = 0; i < lista_tesi_size; i++) {

                person = new Person();
                person = manager_utente.getPersonBySSN(lista_tesi.get(i).getId_studente());

                
                nome_cognome[i] = person.getName() + " " + person.getSurname();
               // System.out.print("Nome_cognome di i: " + i + nome_cognome[i]);

                tesi_data = new JSONObject();

                tesi_data.put("size", lista_tesi_size);
                tesi_data.put("id_tesi", lista_tesi.get(i).getId_tesi());
                tesi_data.put("data_inizio", lista_tesi.get(i).getData_inizio());
                tesi_data.put("data_fine", lista_tesi.get(i).getData_fine());
                tesi_data.put("data_fine_previstsa", lista_tesi.get(i).getData_fine_prevista());
                tesi_data.put("titolo", lista_tesi.get(i).getTitolo());
                tesi_data.put("abstract", lista_tesi.get(i).getAbstract_tesi());
                tesi_data.put("descrizione", lista_tesi.get(i).getDescrizione());
                tesi_data.put("id_studente", nome_cognome[i]);
                tesi_data.put("stato_tesi", lista_tesi.get(i).getStato_tesi());

                jarray.put(i, tesi_data);
            }

            //L'oggetto mainObj passa un JSON ARRAY di JSONObject alla JSP
            JSONObject mainObj = new JSONObject();
            mainObj.put("mainOb", jarray);

            out.print(mainObj.toString());

        } catch (ConnectionException ex) {
            Logger.getLogger(RecuperaTesisti.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RecuperaTesisti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecuperaTesisti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RecuperaTesisti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RecuperaTesisti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(RecuperaTesisti.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RecuperaTesisti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecuperaTesisti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RecuperaTesisti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RecuperaTesisti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(RecuperaTesisti.class.getName()).log(Level.SEVERE, null, ex);
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
