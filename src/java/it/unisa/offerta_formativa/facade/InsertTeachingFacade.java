package it.unisa.offerta_formativa.facade;
import it.unisa.integrazione.database.CycleManager;
import it.unisa.integrazione.database.DegreeManager;
import it.unisa.integrazione.database.DepartmentManager;
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
import it.unisa.offerta_formativa.manager.ParserHtmlManager;
import it.unisa.offerta_formativa.manager.ProfModuleClassManager;
import it.unisa.offerta_formativa.manager.TeachingManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleConnectionManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleCategoryManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleTeachingManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleUserManager;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleRestException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    private final MoodleConnectionManager moodleConn;
    private final DegreeManager degreeMng;
    private final ParserHtmlManager parseMng;
    private final CycleManager cycleMng;
    private final DepartmentManager deptMng;

    public InsertTeachingFacade() {
        cycleMng = CycleManager.getInstance();
        degreeMng = DegreeManager.getInstance();
        deptMng = DepartmentManager.getInstance();
        teachingMng = TeachingManager.getInstance();
        classMng = ClassManager.getInstance();
        currMng = CurriculumManager.getInstance();
        moduleMng = ModuleManager.getInstance();
        pmcMng = ProfModuleClassManager.getInstance();
        moodleConn = MoodleConnectionManager.getInstance();
        parseMng = ParserHtmlManager.getInstance();
        
    }

    public void setDepartmentAbbreviation(String depAbb) {
        this.department_abbreviation = depAbb;
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

    public void storeInDB() throws ClassPartitionException, ModuleException,TeachingException, MoodleRestException {
        if (teachingMng.readTeaching(teaching.getMatricula()) != null) {
            throw new TeachingException("Un insegnamento con questa matricola è già presente.");
        } else {
            try {
                teaching.setEsse3Content(parseMng.getHtml(teaching.getLink(), "column1of2"));
                teachingMng.createTeaching(teaching);
                teachingMng.setEsse3ContentForTeaching(teaching.getMatricula(), teaching.getEsse3Content());
            } catch (TeachingException e) {
                throw new TeachingException("L'insegnamento " + teaching.getTitle() + " non è stato inserito: "+e.getMessage());
            }

            try {
                currMng.setTeachingInCurriculum(teaching.getMatricula(), curriculumMatricula);
            } catch (Exception e) {
                throw new TeachingException("L'associazione tra l'insegnamento " + teaching.getTitle() + " e il curriculum "
                        + curriculumMatricula + "non è stata creata.");
            }

            for (int i = 0; i < listClass.size(); i++) {
                try {
                    classMng.createClass(listClass.get(i));
                } catch (Exception e) {
                    throw new ClassPartitionException("La classe " + listClass.get(i).getTitle() + " non è stata inserita");
                }
            }
            for (int j = 0; j < listModule.size(); j++) {
                try {
                    moduleMng.createModule(listModule.get(j));
                } catch (Exception e) {
                    throw new ModuleException("Il modulo " + listModule.get(j).getTitle() + " non è stato inserito");
                }
            }
            
            //SETTINGS FOR MOODLE*******************************************
            MoodleUserManager moodleUserMng = MoodleUserManager.getInstance(deptMng.getDepartmentByAbbreviation(department_abbreviation).getUrlMoodle(),deptMng.getDepartmentByAbbreviation(department_abbreviation).getToken());
            MoodleCategoryManager moodleDegree = MoodleCategoryManager.getInstance(deptMng.getDepartmentByAbbreviation(department_abbreviation).getUrlMoodle(),deptMng.getDepartmentByAbbreviation(department_abbreviation).getToken());
            MoodleTeachingManager moodleTeaching = MoodleTeachingManager.getInstance(deptMng.getDepartmentByAbbreviation(department_abbreviation).getUrlMoodle(),deptMng.getDepartmentByAbbreviation(department_abbreviation).getToken());
            int idCat = moodleDegree.getIdCategoryByParent(cycleMng.getCycleByCycleNumber(degreeMng.readDegree(degree_matricula).getCycle()).getTitle(),
                                                                degreeMng.readDegree(degree_matricula).getTitle()); //ottengo l'id del degree
            int idYearCat = moodleDegree.getIdCategory(getDateYear()); //ottengo l'id dell'anno
            if(idYearCat==0){ //crea la categoria
                moodleDegree.createCategory(getDateYear(), idCat);
                idYearCat = moodleDegree.getIdCategory(getDateYear());
            }
            //****************************************************************
            
            ArrayList<String> alreadyAdded = new ArrayList<>();
            for (int k = 0; k < listProf.size(); k++) {
                String toadd="";
                try {
                    if(!alreadyAdded.contains(listProf.get(k).getClassTitle())){ //se una classe è già presente in moodle non l'aggiungere
                        if(listClass.size()!=1)toadd=" - "+listProf.get(k).getClassTitle(); //se la classe è una sola allora non aggiunge niente
                        int coursid = moodleTeaching.createTeaching(teaching.getAbbreviation()+toadd, teaching.getTitle()+toadd, idYearCat);
                        int idprof = moodleUserMng.getIdByUsername(listProf.get(k).getProfEmail());
                        moodleTeaching.assignTeacher(idprof, coursid);
                        alreadyAdded.add(listProf.get(k).getClassTitle());
                    }
                    pmcMng.create(listProf.get(k));
                } catch (Exception e) {
                    throw new TeachingException("L'associazione tra " + listProf.get(k).getProfEmail() + " e " + listProf.get(k).getClassTitle() + " - " + listProf.get(k).getModuleTitle() + "non è stata creata.");
                }
            }
            
        }
    }

    public void setDegree(String parameter) {
        this.degree_matricula = parameter;
    }
    
    public String getDateYear(){
        Calendar cal = new GregorianCalendar();
        int year = cal.get(Calendar.YEAR);
        if(cal.get(Calendar.MONTH)>=9)return year+"/"+(year+1);
        else return (year-1)+"/"+(year);
    }
}