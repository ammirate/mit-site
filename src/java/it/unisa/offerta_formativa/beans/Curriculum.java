package it.unisa.offerta_formativa.beans;

/**
 *
 * @author Antonio
 */
public class Curriculum implements Comparable<Curriculum> {

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

    /**
     * state of the curriculum
     */
    private boolean active;

    /**
     * Constructor
     *
     * @param title
     * @param degreeId
     */
    public Curriculum(String matricula, String title, String degreeMatricula) {
        super();
        this.matricula = matricula;
        this.title = title;
        this.degree_matricula = degreeMatricula;
        this.active = true;
    }
    
    /**
     * Constructor
     *
     * @param title
     * @param degreeId
     */
    public Curriculum(String matricula, String title, String degreeMatricula, Boolean active) {
        super();
        this.matricula = matricula;
        this.title = title;
        this.degree_matricula = degreeMatricula;
        this.active = active;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
    public void setActive(int active) {
        this.active = (active > 0);
    }

    @Override
    public String toString() {
        String esc = "\'";
        return "title=" + esc + title + esc + ","
                + "matricula=" + esc + matricula + esc + ","
                + "degree_matricula=" + esc + degree_matricula + esc + ","
                + "active=" + esc + (this.active ? 1 : 0) + esc;
    }

    public String toStringQueryInsert() {
        String esc = "\'";
        return esc + matricula + esc + ","
                + esc + title + esc + ","
                + esc + degree_matricula + esc + ","
                + esc + (this.active ? 1 : 0) + esc;
    }
    
    
    @Override
    public boolean equals(Object o){
        Curriculum c = (Curriculum)o;
        if(this.title.equalsIgnoreCase(c.getTitle()) &&
           this.matricula.equalsIgnoreCase(c.getMatricula()) &&
           this.degree_matricula.equalsIgnoreCase(c.getDegreeMatricula()) ){
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Curriculum o) {
            return this.getTitle().compareTo(o.getTitle());    }

}
