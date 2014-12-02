package it.unisa.offerta_formativa.beans;

/**
 * 
 * @author Antonio
 */
public class Curriculum {

    /**
     * title of the curriculum
     */
    String title;   
    
    /**
     * serial number of the degree related to this curriculum
     */
    String degreeId; 

    /**
     * Constructor
     * @param title
     * @param degreeId 
     */
    public Curriculum(String title, String degreeId) {
        super();
        this.title = title;
        this.degreeId = degreeId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(String degreeId) {
        this.degreeId = degreeId;
    }

    @Override
    public String toString() {
        String esc = "\'";
        return "title=" + esc + title + esc + ","
                + "degree=" + esc + degreeId + esc;
    }

    public String toStringQueryInsert() {
        String esc = "\'";
        return esc + title + esc + "," + esc + degreeId + esc;
    }

}
