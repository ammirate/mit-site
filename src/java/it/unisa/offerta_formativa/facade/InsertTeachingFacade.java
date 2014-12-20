package it.unisa.offerta_formativa.facade;
import java.util.ArrayList;
import it.unisa.offerta_formativa.beans.ClassPartition;
import it.unisa.offerta_formativa.beans.Module;
import it.unisa.offerta_formativa.beans.ProfModuleClass;
import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.manager.ClassManager;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.offerta_formativa.manager.Exceptions.ClassPartitionException;
import it.unisa.offerta_formativa.manager.Exceptions.ModuleException;
import it.unisa.offerta_formativa.manager.Exceptions.TeachingException;
import it.unisa.offerta_formativa.manager.ModuleManager;
import it.unisa.offerta_formativa.manager.ProfModuleClassManager;
import it.unisa.offerta_formativa.manager.TeachingManager;

public class InsertTeachingFacade {
	
    private String department_abbreviation;
    private String degree_matricula;
    private String curriculumMatricula;
    private Teaching teaching;
    
    private ClassManager classMng;
    private ModuleManager moduleMng;
    private TeachingManager teachingMng;
    private CurriculumManager currMng;
    private ProfModuleClassManager pmcMng;
    
    private ArrayList<Module> listModule;
    private ArrayList<ClassPartition> listClass;
    private ArrayList<ProfModuleClass> listProf;

    public InsertTeachingFacade(){
        teachingMng = TeachingManager.getInstance();
        classMng = ClassManager.getInstance();
        currMng = CurriculumManager.getInstance();
        moduleMng = ModuleManager.getInstance();
        pmcMng = ProfModuleClassManager.getInstance();
    }
    
    public void setDepartmentAbbreviation(String depAbb) {
        this.department_abbreviation = department_abbreviation;
    }
    
    public void setTeaching(Teaching t){
        teaching = t;
    }
    
    public void setCurriculumMatricula(String matr){
        curriculumMatricula=matr;
    }
    
    public void setModules(ArrayList<Module> list){
        listModule=list;
    }
	
    public void setClasses(ArrayList<ClassPartition> list){
        listClass=list;
    }
    
    public void setProfModuleClass(ArrayList<ProfModuleClass> list){
        listProf = list;
    }
    
    public boolean storeInDB() throws TeachingException, ClassPartitionException, ModuleException{
        teachingMng.createTeaching(teaching);
        for(int i=0;i<listClass.size();i++){
            classMng.createClass(listClass.get(i)); 
        }
        for(int j=0;j<listModule.size();j++){
                moduleMng.createModule(listModule.get(j));
        }
        for(int k=0;k<listProf.size();k++){
            pmcMng.create(listProf.get(k));
        }
        
        currMng.setTeachingInCurriculum(teaching.getMatricula(),curriculumMatricula);
        return true;
    }
}