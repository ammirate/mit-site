package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.beans.ClassPartition;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.beans.Module;
import it.unisa.offerta_formativa.beans.ProfModuleClass;
import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.facade.InsertTeachingFacade;
import it.unisa.offerta_formativa.manager.ClassManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import it.unisa.offerta_formativa.manager.ModuleManager;
import it.unisa.offerta_formativa.manager.TeachingManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleConnectionManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleDegreeManager;
import java.util.ArrayList;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/InsertTeachingServlet")
public class InsertTeachingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //private MoodleDegreeManager moodleDegreeMng = null;
    private DegreeManager degreeMng = null;
    private ClassManager classMng =null;
    private ModuleManager moduleMng=null;
    private TeachingManager teachingMng = null;
    
    //private MoodleConnectionManager moodleConnector = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertTeachingServlet() {
        super();
        //moodleConnector = MoodleConnectionManager.getInstance();
        int id = 1;
        //moodleDegreeMng = MoodleDegreeManager.getInstance(moodleConnector.getUrlMoodle(id), moodleConnector.getToken(id));
        degreeMng = DegreeManager.getInstance();
        teachingMng = TeachingManager.getInstance();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        
    }

    

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String matricula="";
        int classNumber=0;
        int moduleNumber=0;
        ArrayList<ClassPartition> listClasses = new ArrayList<ClassPartition>();
        ArrayList<Module> listModules = new ArrayList<Module>();
        InsertTeachingFacade facade = new InsertTeachingFacade();
        if(request.getParameterMap().containsKey("matricula")){
            matricula = request.getParameter("matricula");
            facade.setTeaching(new Teaching(request.getParameter("title"),
                    request.getParameter("abbreviation"),
                    matricula,
                    request.getParameter("link"),
                    Integer.parseInt(request.getParameter("year")),
                    Integer.parseInt(request.getParameter("semester")),true));
        }
        if(request.getParameterMap().containsKey("curriculum"))facade.setCurriculumMatricula(request.getParameter("curriculum"));
        if(request.getParameterMap().containsKey("moduleNumber")){
            moduleNumber =Integer.parseInt(request.getParameter("moduleNumber"));
            for(int i=1;i<=moduleNumber;i++){
                listModules.add(new Module(matricula,request.getParameter("moduleName"+i)));
            }
            facade.setModules(listModules);
        }
        
        if(request.getParameterMap().containsKey("classNumber")){
            
            classNumber =Integer.parseInt(request.getParameter("classNumber"));
            for(int j=1;j<=classNumber;j++){
                if(classNumber==1){
                    listClasses.add(new ClassPartition(matricula,request.getParameter("title")));
                }
                else{
                    listClasses.add(new ClassPartition(request.getParameter("matricula"),request.getParameter("className"+j)));
                }
            }
            facade.setClasses(listClasses);
        }
        ArrayList<ProfModuleClass> list = new ArrayList<ProfModuleClass>();
        for(int i=1;i<=classNumber;i++){
            for(int j=1;j<=moduleNumber;j++){
                list.add(new ProfModuleClass(listClasses.get(i-1).getTitle(),matricula,listModules.get(j-1).getTitle(),request.getParameter("docente"+i+"-"+j)));
            }
        }
        facade.setProfModuleClass(list);
        
        facade.storeInDB();
    }

}
