package it.unisa.offerta_formativa.facade;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


import it.unisa.offerta_formativa.beans.ClassPartition;
import it.unisa.offerta_formativa.beans.Department;
import it.unisa.offerta_formativa.beans.Module;
import it.unisa.offerta_formativa.beans.ProfModuleClass;
import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.manager.ClassManager;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import it.unisa.offerta_formativa.manager.DepartmentManager;
import it.unisa.offerta_formativa.manager.ModuleManager;
import it.unisa.offerta_formativa.manager.ProfModuleClassManager;
import it.unisa.offerta_formativa.manager.TeachingManager;

public class InsertionTeachingFacade {
	
    private String department_abbreviation;
    private String degree_matricula;
    private String curriculum_matricula;
    private Teaching teaching;
    
    private ClassManager classMng;
    private ModuleManager moduleMng;
    private TeachingManager teachingMng;
    private CurriculumManager currMng;
    
    private ArrayList<Module> listModule;
    private ArrayList<ClassPartition> listClass;

    public void setDepartmentAbbreviation(String depAbb) {
        this.department_abbreviation = department_abbreviation;
    }
    
    public void setTeaching(Teaching t){
        teaching = t;
    }
    
    public void setModules(ArrayList<Module> list){
        listModule=list;
    }
	
    public void setClasses(ArrayList<ClassPartition> list){
        listClass=list;
    }
    
    public void setProfessorsID(ArrayList<String> list){
    
    }
    
    public boolean storeInDB(){
        teachingMng.createTeaching(teaching);
        for(int i=0;i<listModule.size();i++){
            moduleMng.createModule(listModule.get(i));
        }
        for(int i=0;i<listClass.size();i++){
            classMng.createClass(listClass.get(i));
        }
        currMng.setCurriculumTeaching(teaching.getMatricula());
        
    }
}