/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.model.Degree;
import it.unisa.model.Department;
import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.integrazione.database.CycleManager;
import it.unisa.integrazione.database.DegreeManager;
import it.unisa.integrazione.database.DepartmentManager;
import it.unisa.offerta_formativa.manager.TeachingManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Davide
 * Class to show ShowTeachingOfferList.jsp
 */
@WebServlet("/ShowTeachingOfferServlet")
public class ShowTeachingOfferServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DepartmentManager dm;
    private DegreeManager degreeMng;
    private TeachingManager tm;
    private CurriculumManager cm;
    private CycleManager cym;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTeachingOfferServlet() {
        super();
        // TODO Auto-generated constructor stub   
        dm = DepartmentManager.getInstance();
        degreeMng = DegreeManager.getInstance();
        tm = TeachingManager.getInstance();
        cm = CurriculumManager.getInstance();
        cym = CycleManager.getInstance();
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
        request.getRequestDispatcher("/offertaFormativa/ShowTeachingOfferList.jsp").forward(request, response);
    }
}
