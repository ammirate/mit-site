/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.servlet;

import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.Account;
import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.Organization;
import it.unisa.tirocinio.beans.StudentInformation;
import it.unisa.tirocinio.beans.TrainingRequest;
import it.unisa.tirocinio.manager.concrete.ConcreteOrganization;
import it.unisa.tirocinio.manager.concrete.ConcreteStudentInformation;
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
@WebServlet(name = "getAllEntities", urlPatterns = {"/getAllEntities"})

public class getAllEntities extends HttpServlet {
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
            /* TODO output your page here. You may use following sample code. */
            ConcreteOrganization anOrganization = ConcreteOrganization.getInstance();
            PersonManager aPerson = PersonManager.getInstance();
            ArrayList<Person> student = aPerson.getAllPeople();
            
            JSONArray arrayOrganization = new JSONArray();
            JSONArray arrayProfessor = new JSONArray();
            JSONArray arrayPerson = new JSONArray();
            
            for(Person pers: student){
                Account account = pers.getAccount();
                JSONObject jsonTmpPro = new JSONObject();
                if(aPerson.isAProfessor(account.getEmail())){
                    jsonTmpPro.put("credential", pers.getName()+" "+pers.getSurname());
                    jsonTmpPro.put("SSN",pers.getSsn());
                    arrayProfessor.put(jsonTmpPro);
                    
                    ArrayList<Organization> organization = anOrganization.getOwnOrganizations(pers.getSsn());
                    
                    for( Organization orga: organization){
                        JSONObject jsonTmpOrg = new JSONObject();

                        jsonTmpOrg.put("vatNumber", orga.getVATNumber());
                        jsonTmpOrg.put("companyNa", orga.getCompanyName());
                        arrayOrganization.put(jsonTmpOrg);
                    }
                }  
            }
            
            for( Person stud: student ){
                Account account = stud.getAccount();
                if(aPerson.isAStudent(account.getEmail())){
                    ConcreteStudentInformation aStudentInformation = ConcreteStudentInformation.getInstance();
                    StudentInformation studentInformation = null;
                    try {
                        studentInformation = aStudentInformation.readAStudentInformation(stud.getSsn());
                    } catch (ConnectionException ex) {
                        Logger.getLogger(getAllEntities.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    ConcreteTrainingRequest aTrainingRequest = ConcreteTrainingRequest.getInstance();
                    TrainingRequest trainingRequest = aTrainingRequest.readTrainingRequestByStudent(stud.getSsn());
                    
                    if((studentInformation.getStudentStatus() == 2)&&(trainingRequest.getStudentSSN()==null)){
                        JSONObject jsonTmpStu = new JSONObject();
                        jsonTmpStu.put("credentialStudent", stud.getName()+" "+stud.getSurname());
                        jsonTmpStu.put("SSNStudent",stud.getSsn());
                        arrayPerson.put(jsonTmpStu);
                    }
                }
            }
            
            jsonObject.put("status", 1);
            jsonObject.put("organization", arrayOrganization);
            jsonObject.put("professor", arrayProfessor);
            jsonObject.put("student", arrayPerson);
            
            out.write(jsonObject.toString());
        } catch (JSONException ex) {
            Logger.getLogger(getAllEntities.class.getName()).log(Level.SEVERE, null, ex);
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
