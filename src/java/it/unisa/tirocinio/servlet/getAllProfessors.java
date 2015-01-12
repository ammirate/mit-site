/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.servlet;

import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.model.Account;
import it.unisa.integrazione.model.Person;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Valentino
 */
@WebServlet(name = "getAllProfessors", urlPatterns = {"/getAllProfessors"})
public class getAllProfessors extends HttpServlet {
    private final JSONObject jsonObject = new JSONObject();

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
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        try {
            PersonManager aPerson = PersonManager.getInstance();
            ArrayList<Person> person = aPerson.getAllPeople();
            
            ArrayList<Person> allProfessor = new ArrayList<Person>();
            
            for (Person person1 : person) {
                Account account = person1.getAccount();
                if (aPerson.isAProfessor(account.getEmail())) {
                    allProfessor.add(person1);
                }
            }
            
            if(allProfessor.isEmpty()){
                jsonObject.put("status", 0);
            }else{
                JSONArray array = new JSONArray();
                for( Person prof: allProfessor ){
                    JSONObject jsonTmp = new JSONObject();
                    jsonTmp.put("NameANDSurname", prof.getName()+" "+prof.getSurname());
                    jsonTmp.put("SSNProf", prof.getSsn());
                    array.put(jsonTmp);
                }
                jsonObject.put("status", 1);
                jsonObject.put("message", array);
            }
            response.getWriter().write(jsonObject.toString());
        } catch (JSONException ex) {
            Logger.getLogger(getAllProfessors.class.getName()).log(Level.SEVERE, null, ex);
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
