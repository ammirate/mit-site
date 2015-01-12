/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.servlet;

import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.model.Account;
import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.TrainingRequest;
import it.unisa.tirocinio.manager.concrete.ConcreteTrainingRequest;
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
@WebServlet(name = "updateTrainingRequestStatus", urlPatterns = {"/updateTrainingRequestStatus"})

public class updateTrainingRequestStatus extends HttpServlet {
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
            ArrayList<Person> student = new ArrayList<Person>();
            
            for(Person pers: person){
                Account account = pers.getAccount();
                if(aPerson.isAStudent(account.getEmail()))
                    student.add(pers);
            }
            
            ConcreteTrainingRequest aTrainingRequest = ConcreteTrainingRequest.getInstance();
           
            JSONArray array = new JSONArray();
            for( Person stud: student ){
                JSONObject jsonTmp = new JSONObject();
                jsonTmp.put("matricola", stud.getMatricula());
                jsonTmp.put("credenziali", stud.getName()+" "+stud.getSurname());
                
                TrainingRequest trainingRequest = aTrainingRequest.readTrainingRequestByStudent(stud.getSsn());
                if(trainingRequest.getTrainingStatus() == 2){
                    jsonTmp.put("titolo", trainingRequest.getTitle());
                    array.put(jsonTmp);
                }
            }
            
            if(array.length() == 0){
                jsonObject.put("status", 0);
            }else{
                jsonObject.put("status", 1);
                jsonObject.put("message", array);
            }
            response.getWriter().write(jsonObject.toString());            
        } catch (JSONException ex) {
            Logger.getLogger(updateTrainingRequestStatus.class.getName()).log(Level.SEVERE, null, ex);
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
