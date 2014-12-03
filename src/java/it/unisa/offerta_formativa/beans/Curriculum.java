package it.unisa.offerta_formativa.beans;

/**
 * 
 * @author Antonio
 */
public class Curriculum {

    /**
     * title of the curriculum
     */
    private String title;   
    
    /**
     * serial number of the degree related to this curriculum
     */
    private String degree_matricula; 
    /**
     * matricula of curriculum
     */
    private String matricula; 

    public String getDegreeMatricula() {
        return degree_matricula;
    }

    public void setDegreeMatricula(String degree_matricula) {
        this.degree_matricula = degree_matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Constructor
     * @param title
     * @param degreeId 
     */
    public Curriculum(String matricula,String title, String degreeMatricula) {
        super();
        this.matricula=matricula;
        this.title = title;
        this.degree_matricula = degreeMatricula;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        String esc = "\'";
        return "title=" + esc + title + esc + ","
                + "degree_matricula=" + esc + degree_matricula + esc + "," + 
                "matricula="+esc+matricula+esc;
    }

    public String toStringQueryInsert() {
        String esc = "\'";
        return esc+ matricula +esc+ "," + esc + title + esc + "," + esc + degree_matricula + esc;
    }

}
