package it.unisa.tirocinio.beans;

/**
 *
 * @author johneisenheim
 */
public class TrainingRequest {
    
    private int idTrainingRequest;
    private String description = null;
    private String title = null;
    private int trainingStatus;
    private String professorSSN = null;
    private String organizationVATNumber = null;
    private String studentSSN = null;
    
    /**
     *
     */
    public TrainingRequest(){}
    
    /**
     *
     * @param idTrainingRequest
     * @param description
     * @param title
     * @param trainingStatus
     * @param professor
     * @param organizationVATNumber
     * @param studentSSN
     */
    public TrainingRequest(int idTrainingRequest, String description, String title, int trainingStatus, String professor, String organizationVATNumber, String studentSSN){
        this.idTrainingRequest = idTrainingRequest;
        this.description = description;
        this.title = title;
        this.trainingStatus = trainingStatus;
        this.professorSSN = professor;
        this.organizationVATNumber = organizationVATNumber;
        this.studentSSN = studentSSN;
    }

    /**
     *
     * @return id training request
     */
    public int getIdTrainingRequest() {
        return idTrainingRequest;
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
     * @return title 
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return training status
     */
    public int getTrainingStatus() {
        return trainingStatus;
    }

    /**
     *
     * @return person SSN
     */
    public String getPersonSSN() {
        return professorSSN;
    }

    /**
     *
     * @return organization VAT Number
     */
    public String getOrganizationVATNumber() {
        return organizationVATNumber;
    }

    /**
     *
     * @return student SSN
     */
    public String getStudentSSN() {
        return studentSSN;
    }

    /**
     *
     * @param idTrainingRequest
     */
    public void setIdTrainingRequest(int idTrainingRequest) {
        this.idTrainingRequest = idTrainingRequest;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @param trainingStatus
     */
    public void setTrainingStatus(int trainingStatus) {
        this.trainingStatus = trainingStatus;
    }

    /**
     *
     * @param personSSN
     */
    public void setPersonSSN(String personSSN) {
        this.professorSSN = personSSN;
    }

    /**
     *
     * @param organizationVATNumber
     */
    public void setOrganizationVATNumber(String organizationVATNumber) {
        this.organizationVATNumber = organizationVATNumber;
    }

    /**
     *
     * @param studentSSN
     */
    public void setStudentSSN(String studentSSN) {
        this.studentSSN = studentSSN;
    }

}
