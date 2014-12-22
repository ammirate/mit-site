/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet.getter;

import com.google.gson.Gson;
import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.manager.TeachingManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alessandro
 */
@WebServlet(name = "GetTeachingServlet", urlPatterns = {"/GetTeachingServlet"})
public class GetTeachingServlet extends HttpServlet {

    private TeachingManager teachingMng;

    public GetTeachingServlet() {
        teachingMng = TeachingManager.getInstance();
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

        ArrayList<Teaching> teachings = new ArrayList<Teaching>();
        if (request.getParameterMap().containsKey("curriculum")) {

            teachings = (ArrayList<Teaching>) teachingMng.getTeachingsByCurriculum(request.getParameter("curriculum"));
            /**
             * response.setContentType("text/plain");
             * response.setCharacterEncoding("UTF-8"); String toRet="";
             * for(Teaching t :
             * teachingMng.getTeachingsByCurriculum(curriculum)){ toRet+="<tr>"
             * + "                                      <td>"+t.getMatricula()+"</td>" + "                                      <td>"+t.getTitle()+"</td>"
             * + "                                      <td>"+t.getAbbreviation()+"</td>" + "
             * <td>"+t.getLink()+"</td>" + "                                      <td>"+t.getYear()+"</td>" + "
             * <td>"+t.getSemester()+"</td>" + "
             * <td>"+((t.isActive())?"Attivo":"Disattivo")+"</td>"+ * "<td><a href=ShowModifyTeachingServlet?matricula="+t.getMatricula()+
             * "&curriculumMatricula="+curriculum+">Modifica</a></td>"+ * "<td><a href=ShowAssociationServlet?matricula="+t.getMatricula()+
             * " >Dettagli</a></td></tr>>"; } response.getWriter().write(toRet);
             */
            //****************************************

        } else {
            teachings = teachingMng.getAllTeachings();
        }
        ArrayList<HashMap<String, String>> arrlist = new ArrayList<HashMap<String, String>>();
        Collections.sort(teachings);
        for (Teaching t : teachings){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("matricula", t.getMatricula());
            map.put("title", t.getTitle());
            map.put("abbreviation", t.getAbbreviation());
            map.put("link", t.getLink());
            map.put("year", "" + t.getYear());
            map.put("semester", "" + t.getSemester());
            map.put("active", ((t.isActive()) ? "Attivo" : "Disattivo"));
            arrlist.add(map);
        }
        String finalJSON = new Gson().toJson(arrlist);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(finalJSON);

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
        doGet(request, response);
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
