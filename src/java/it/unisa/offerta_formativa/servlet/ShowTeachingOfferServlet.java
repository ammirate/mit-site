/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.beans.Department;
import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.offerta_formativa.manager.CycleManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import it.unisa.offerta_formativa.manager.DepartmentManager;
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
 * Servlet implementation class GetSyllabusServlet
 *
 * @author Davide
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

    private HashMap CreateMap() {
        HashMap< Department, HashMap<Degree, HashMap<Curriculum, ArrayList<Teaching>>>> map;
        map = new HashMap< Department, HashMap< Degree, HashMap<Curriculum, ArrayList<Teaching>>>>();

        for (Department d : dm.getAllDepartments()) {
            map.put(d, new HashMap< Degree, HashMap<Curriculum, ArrayList<Teaching>>>());

            for (Degree deg : degreeMng.getDegreesByDepartment(d.getAbbreviation())) {

                map.get(d).put(deg, new HashMap<Curriculum, ArrayList<Teaching>>());

                for (Curriculum c : cm.getCurriculumByDegree(deg.getMatricula())) {
                    map.get(d).get(deg).put(c, new ArrayList<Teaching>());

                    for (Teaching t : tm.getTeachingsByCurriculum(c.getMatricula())) {
                        map.get(d).get(deg).get(c).add(t);
                    }
                }
            }
        }
        return map;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setAttribute("cycles", cym.getAllCycles());
        request.setAttribute("map", CreateMap());
        request.getRequestDispatcher("/offertaFormativaJSP/ShowTeachingOfferList.jsp").forward(request, response);
    }
}
