package it.unisa.offerta_formativa.beans;

/**
 *
 * @author Alessandro
 *
 */
public class ProfModuleClass {

    private String classTitle;
    private String teachingMatricula;
    private String moduleTitle;
    private String profEmail;

    /**
     * Constructor which allow to know which prof teaches which module in which class
     * @param classTitle
     * @param teachingMatricula
     * @param moduleTitle
     * @param profEmail 
     */
    public ProfModuleClass(String classTitle, String teachingMatricula,
            String moduleTitle, String profEmail) {

        this.classTitle = classTitle;
        this.moduleTitle = moduleTitle;
        this.teachingMatricula = teachingMatricula;
        this.profEmail = profEmail;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public String getTeachingMatricula() {
        return teachingMatricula;
    }

    public void setTeachingMatricula(String teachingMatricula) {
        this.teachingMatricula = teachingMatricula;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public String getProfEmail() {
        return profEmail;
    }

    public void setProfEmail(String profEmail) {
        this.profEmail = profEmail;
    }

}
