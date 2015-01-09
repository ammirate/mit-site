/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet.classpartition;

import it.unisa.integrazione.database.CycleManager;
import it.unisa.integrazione.database.DegreeManager;
import it.unisa.integrazione.database.DepartmentManager;
import it.unisa.offerta_formativa.beans.ClassPartition;
import it.unisa.offerta_formativa.beans.Module;
import it.unisa.offerta_formativa.beans.ProfModuleClass;
import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.manager.ClassManager;
import it.unisa.offerta_formativa.manager.Exceptions.ClassPartitionException;
import it.unisa.offerta_formativa.manager.Exceptions.ModuleException;
import it.unisa.offerta_formativa.manager.Exceptions.TeachingException;
import it.unisa.offerta_formativa.manager.ModuleManager;
import it.unisa.offerta_formativa.manager.ProfModuleClassManager;
import it.unisa.offerta_formativa.manager.TeachingManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleConnectionManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleCategoryManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleTeachingManager;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleRestException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alessandro
 */
@WebServlet(name = "InsertClassServlet", urlPatterns = {"/InsertClassServlet"})
public class InsertClassServlet extends HttpServlet {
    private final ClassManager classMng;
    private ModuleManager modMng;
    private ArrayList<Module> mlist;
    private final ProfModuleClassManager pmcMng;
    private final DegreeManager degreeMng;
    private final MoodleConnectionManager moodleConn;
    private final TeachingManager teachingMng;
    private final DepartmentManager deptMng;
    private final CycleManager cycleMng;

    public InsertClassServlet() {
        cycleMng = CycleManager.getInstance();
        teachingMng = TeachingManager.getInstance();
        deptMng = DepartmentManager.getInstance();
        modMng = ModuleManager.getInstance();
        classMng = ClassManager.getInstance();
        pmcMng = ProfModuleClassManager.getInstance();
        degreeMng = DegreeManager.getInstance();
        moodleConn = MoodleConnectionManager.getInstance();
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
        doPost(request,response);
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
        String path="/offertaFormativa/amministratore/classmodule/";
        if(request.getParameterMap().containsKey("teaching") && request.getParameterMap().containsKey("classname")){
            try {
                if(!checkFields(request).equalsIgnoreCase("")) throw new ClassPartitionException(checkFields(request));
                String matricula = request.getParameter("teaching");
                String classname = request.getParameter("classname");
                String degree_matricula = request.getParameter("degree");
                String department_abbreviation = request.getParameter("department");
            
                if(!classMng.readClass(matricula, classname).getTitle().equalsIgnoreCase("")) throw new ClassPartitionException("Esiste già una classe con questo nome");
                classMng.createClass(new ClassPartition(matricula,request.getParameter("classname")));
                mlist = modMng.getModulesByTeaching(matricula);
                
                //SETTINGS FOR MOODLE*******************************************
                MoodleCategoryManager moodleDegree = MoodleCategoryManager.getInstance(deptMng.getDepartmentByAbbreviation(department_abbreviation).getUrlMoodle(),deptMng.getDepartmentByAbbreviation(department_abbreviation).getToken());
                MoodleTeachingManager moodleTeaching = MoodleTeachingManager.getInstance(deptMng.getDepartmentByAbbreviation(department_abbreviation).getUrlMoodle(),deptMng.getDepartmentByAbbreviation(department_abbreviation).getToken());
                int idCat = moodleDegree.getIdCategoryByParent(cycleMng.getCycleByCycleNumber(degreeMng.readDegree(degree_matricula).getCycle()).getTitle(),
                                                                degreeMng.readDegree(degree_matricula).getTitle()); //ottengo l'id del degree
                int idYearCat = moodleDegree.getIdCategory(getDateYear()); //ottengo l'id dell'anno
                if(idYearCat==0){ //crea la categoria se non esiste
                    moodleDegree.createCategory(getDateYear(), idCat);
                    idYearCat = moodleDegree.getIdCategory(getDateYear());
                }
                //*******************************************************************
                
                Teaching teaching = teachingMng.readTeaching(matricula);
                for(Module m : mlist){
                    moodleTeaching.createTeaching(teaching.getAbbreviation()+classname, teaching.getTitle()+" -"+classname, idYearCat); //inserimento in moodle
                    pmcMng.create(new ProfModuleClass(request.getParameter("classname"),
                                                    matricula,
                                                    m.getTitle(),
                                                    request.getParameter(m.getTitle())
                    ));
                }
                
            } catch (ClassPartitionException ex) {
                request.setAttribute("errorMessage", "Errore classe: "+ex.getMessage());
                request.setAttribute("error", true);
                request.getRequestDispatcher(path+"insertClass.jsp").forward(request, response);
            } catch (ModuleException ex) {
                request.setAttribute("errorMessage", "Errore lettura moduli: "+ex.getMessage());
                request.setAttribute("error", true);
                request.getRequestDispatcher(path+"insertClass.jsp").forward(request, response);
            } catch (TeachingException ex) {
                request.setAttribute("errorMessage", "Errore lettura insegnamento: "+ex.getMessage());
                request.setAttribute("error", true);
                request.getRequestDispatcher(path+"insertClass.jsp").forward(request, response);
            } catch (MoodleRestException ex) {
                request.setAttribute("errorMessage", "Errore API Rest Moodle: "+ex.getMessage());
                request.setAttribute("error", true);
                request.getRequestDispatcher(path+"insertClass.jsp").forward(request, response);
            }
            request.setAttribute("success", true);
            request.setAttribute("successMessage", "Inserimento effettuato con successo");
            request.getRequestDispatcher(path+"insertClass.jsp").forward(request, response);
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

    private String checkFields(HttpServletRequest request) {
        if(request.getParameter("teaching").length()!=10) return "La matricola deve essere di 10 caratteri.";
        if(request.getParameter("classname").length()<=2) return "Il titolo della classe deve essere composto da almeno 2 caratteri.";
        return "";
    }
    
    public String getDateYear(){
        Calendar cal = new GregorianCalendar();
        int year = cal.get(Calendar.YEAR);
        if(cal.get(Calendar.MONTH)>=9)return year+"/"+(year+1);
        else return (year-1)+"/"+(year);
    }

}
