package it.unisa.tirocinio.beans;

/**
 *
 * @author johneisenheim
 */
public class StudentStatus {
    
    private int idStudentStatus;
    private String description = null;

    /**
     *
     */
    public StudentStatus() {}
    
    /**
     *
     * @param idStudentStatus
     * @param description
     */
    public StudentStatus( int idStudentStatus, String description ) {
        this.idStudentStatus = idStudentStatus;
        this.description = description;
    }

    /**
     *
     * @return id student status
     */
    public int getIdStudentStatus() {
        return idStudentStatus;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param idStudentStatus
     */
    public void setIdStudentStatus(int idStudentStatus) {
        this.idStudentStatus = idStudentStatus;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
