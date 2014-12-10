/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet;


import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import it.unisa.offerta_formativa.manager.DepartmentManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetSyllabusServlet
 *
 * @author Davide
 */
@WebServlet("/ShowCurriculumServlet")
public class ShowCurriculumServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DepartmentManager depMng;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCurriculumServlet() {
        super();
        // TODO Auto-generated constructor stub   
        depMng = DepartmentManager.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }
    
      
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setAttribute("deps", depMng.getAllDepartments());
        request.getRequestDispatcher("/offertaFormativaJSP/amministratore/ShowCurriculumList.jsp").forward(request, response);
    }
}
