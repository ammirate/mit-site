/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.servlet;

import it.unisa.gestionetesi.beans.RelatoreTesi;
import it.unisa.gestionetesi.beans.Tesi;
import it.unisa.gestionetesi.manager.ManagerTesi;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Damiano
 */
public class RecuperaDatiTesi extends HttpServlet {
    private Logger logger = Logger.getLogger("db");
    ManagerTesi manager_tesi;
    private ArrayList<RelatoreTesi> listaRelatoriTesi = null;

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
            throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String id_studente = request.getParameter("id_studente");

            // Devo ricevere ID_Studente
            manager_tesi = new ManagerTesi();
            Tesi T = manager_tesi.selezionaTesi(id_studente);
            JSONObject dati_tesi = null;

            if (T != null) {

                dati_tesi = new JSONObject();
                dati_tesi.put("id_tesi", T.getId_tesi());
                dati_tesi.put("data_inizio", T.getData_inizio());
                dati_tesi.put("data_fine", T.getData_fine());
                dati_tesi.put("data_fine_prevista", T.getData_fine_prevista());
                dati_tesi.put("messaggio_richiesta", T.getDescrizione());
                dati_tesi.put("titolo", T.getTitolo());
                dati_tesi.put("stato_tesi", T.getStato_tesi());
                dati_tesi.put("abstract_tesi", T.getAbstract_tesi());

                if (T.getStato_tesi().equals("0") || T.getStato_tesi().equals("1") || T.getStato_tesi().equals("2")) {
                    
                    JSONArray jarrayRelatori = new JSONArray();

                    listaRelatoriTesi = manager_tesi.selezionaRelatoriTesi(T.getId_tesi());
                    if (listaRelatoriTesi != null) {
                        for (int i = 0; i < listaRelatoriTesi.size(); i++) {
                            JSONObject relatoreTesi = new JSONObject();

                            relatoreTesi.put("id_docente", listaRelatoriTesi.get(i).getId_docente());
                            relatoreTesi.put("nome_docente", listaRelatoriTesi.get(i).getNome());
                            relatoreTesi.put("cognome_docente", listaRelatoriTesi.get(i).getCognome());
                            
                             logger.info("Relatori: " + listaRelatoriTesi.get(i).getNome());

                            jarrayRelatori.put(i, relatoreTesi);

                        }

                        dati_tesi.put("relatori", jarrayRelatori);

                    }

                }

                out.print(dati_tesi.toString());

            } else {
                out.print("");
            }

        } catch (JSONException ex) {
            Logger.getLogger(RecuperaDatiTesi.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RecuperaDatiTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecuperaDatiTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RecuperaDatiTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RecuperaDatiTesi.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RecuperaDatiTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecuperaDatiTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RecuperaDatiTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RecuperaDatiTesi.class.getName()).log(Level.SEVERE, null, ex);
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
