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
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author ciro
 */
public class RecuperaCronologiaDocente extends HttpServlet {
    
    final static Logger logger = Logger.getLogger("RecuperaCronologiaStud");
    private ManagerCronologia managerCronologia;
    private PersonManager managerUtente;
    private ArrayList<Cronologia> cronologia;
    private int crono_size = 0;
    private JSONArray jarray;
    private JSONObject crono_data;
    private Person p;

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
            throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        try {
            
            String id_docente = request.getParameter("id_docente");
            managerCronologia = new ManagerCronologia();
            managerUtente = new PersonManager();
            logger.info(id_docente);
            cronologia = managerCronologia.elencaEventiDocente(id_docente);
            logger.info("RCD size cronologia:" + cronologia.size());
            if (!cronologia.isEmpty()) {
                jarray = new JSONArray();
                crono_size = cronologia.size();
                for (int i = 0; i < crono_size; i++) {
                    logger.info(cronologia.get(i).getId_studente());
                    
                    p = managerUtente.getPersonBySSN(cronologia.get(i).getId_studente());
                    String nomeStudente = p.getSurname() + " " + p.getName();
                    logger.info(nomeStudente);
                    crono_data = new JSONObject();
                    crono_data.put("size", crono_size);
                    crono_data.put("testo", cronologia.get(i).getTesto());
                    crono_data.put("ID_Studente", cronologia.get(i).getId_studente());
                    crono_data.put("ID_Docente", cronologia.get(i).getId_docente());
                    crono_data.put("Data_Notifica", cronologia.get(i).getData_notifica());
                    crono_data.put("nomeStudente", nomeStudente);
                    crono_data.put("tipoNotifica", cronologia.get(i).getTipo());
                    
                    jarray.put(i, crono_data);
                    
                }
                
                JSONObject mainObj = new JSONObject();
                mainObj.put("employees", jarray);
                
                System.out.println("contenuto jarray RCD: " + mainObj.toString());
                
                logger.info("RCD è pieno");
                out.print(mainObj.toString());
            } else {
                logger.info("RCD è vuoto");
                out.print("null");
            }
        } catch (ConnectionException ex) {
            Logger.getLogger(RecuperaCronologiaDocente.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RecuperaCronologiaStudente.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecuperaCronologiaStudente.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RecuperaCronologiaStudente.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RecuperaCronologiaStudente.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(RecuperaCronologiaStudente.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RecuperaCronologiaStudente.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecuperaCronologiaStudente.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RecuperaCronologiaStudente.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RecuperaCronologiaStudente.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(RecuperaCronologiaStudente.class
                    .getName()).log(Level.SEVERE, null, ex);
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
