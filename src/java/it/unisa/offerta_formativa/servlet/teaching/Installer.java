/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet.teaching;


import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleCallRestWebService;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleCategory;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleRestCourse;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleRestException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alessandro
 */
@WebServlet(name = "Installer", urlPatterns = {"/Installer"})
public class Installer extends HttpServlet {

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
        install();

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

    private Installer(String urlMoodle, String token) {
        MoodleCallRestWebService.init(urlMoodle, token, false);
    }

    /**
     *
     */
    public boolean install() {
        try {
            MoodleRestCourse.createCategory(new MoodleCategory(2, "Triennale"));
            MoodleRestCourse.createCategory(new MoodleCategory(3, "Magistrale"));
            MoodleRestCourse.createCategory(new MoodleCategory(4, "Dottorato"));
            return true;
        } catch (UnsupportedEncodingException | MoodleRestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        // TODO Auto-generated method stub
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
