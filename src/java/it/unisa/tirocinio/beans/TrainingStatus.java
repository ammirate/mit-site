package it.unisa.tirocinio.beans;

/**
 *
 * @author johneisenheim
 */
public class TrainingStatus {
    
    private int idTrainingStatus;
    private String description = null;
    
    /**
     *
     */
    public TrainingStatus(){}
    
    /**
     *
     * @param idTrainingStatus
     * @param description
     */
    public TrainingStatus( int idTrainingStatus, String description ){
        this.idTrainingStatus = idTrainingStatus;
        this.description = description;
    }

    /**
     *
     * @return id training status
     */
    public int getIdTrainingStatus() {
        return idTrainingStatus;
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
     * @param idTrainingStatus
     */
    public void setIdTrainingStatus(int idTrainingStatus) {
        this.idTrainingStatus = idTrainingStatus;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
