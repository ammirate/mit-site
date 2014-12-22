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

    public InsertTeachingFacade() {
        teachingMng = TeachingManager.getInstance();
        classMng = ClassManager.getInstance();
        currMng = CurriculumManager.getInstance();
        moduleMng = ModuleManager.getInstance();
        pmcMng = ProfModuleClassManager.getInstance();
    }

    public void setDepartmentAbbreviation(String depAbb) {
        this.department_abbreviation = department_abbreviation;
    }

    public void setTeaching(Teaching t) {
        teaching = t;
    }

    public void setCurriculumMatricula(String matr) {
        curriculumMatricula = matr;
    }

    public void setModules(ArrayList<Module> list) {
        listModule = list;
    }

    public void setClasses(ArrayList<ClassPartition> list) {
        listClass = list;
    }

    public void setProfModuleClass(ArrayList<ProfModuleClass> list) {
        listProf = list;
    }

    public void storeInDB() throws Exception {
        if (teachingMng.readTeaching(teaching.getMatricula()) != null) {
            throw new Exception("Un insegnamento con questa matricola è già presente.");
        } else {
            try {
                teachingMng.createTeaching(teaching);
            } catch (Exception e) {
                throw new Exception("L'insegnamento " + teaching.getTitle() + " non è stato inserito");
            }

            try {
                currMng.setTeachingInCurriculum(teaching.getMatricula(), curriculumMatricula);
            } catch (Exception e) {
                throw new Exception("L'associazione tra l'insegnamento " + teaching.getTitle() + " e il curriculum "
                        + curriculumMatricula + "non è stata creata.");
            }

            for (int i = 0; i < listClass.size(); i++) {
                try {
                    classMng.createClass(listClass.get(i));
                } catch (Exception e) {
                    throw new Exception("La classe " + listClass.get(i).getTitle() + " non è stata inserita");
                }
            }
            for (int j = 0; j < listModule.size(); j++) {
                try {
                    moduleMng.createModule(listModule.get(j));
                } catch (Exception e) {
                    throw new Exception("Il modulo " + listModule.get(j).getTitle() + " non è stato inserito");
                }
            }
            for (int k = 0; k < listProf.size(); k++) {
                try {
                    pmcMng.create(listProf.get(k));
                } catch (Exception e) {
                    throw new Exception("L'associazione tra " + listProf.get(k).getProfEmail() + " e " + listProf.get(k).getClassTitle() + " - " + listProf.get(k).getModuleTitle() + "non è stata creata.");
                }
            }
        }
    }
}
