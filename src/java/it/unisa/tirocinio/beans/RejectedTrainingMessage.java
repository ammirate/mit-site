package it.unisa.tirocinio.beans;

/**
 *
 * @author johneisenheim
 */
public class RejectedTrainingMessage {
 
    private int idRejectedTraingMessage;
    private String description = null;
    private String personSSN = null;
    
    /**
     *
     */
    public RejectedTrainingMessage(){}  

    /**
     *
     * @param idRejectedTraingMessage
     * @param description
     * @param personSSN
     */
    public RejectedTrainingMessage( int idRejectedTraingMessage, String description, String personSSN ){
        this.idRejectedTraingMessage = idRejectedTraingMessage;
        this.description = description;
        this.personSSN = personSSN;
    }  

    /**
     *
     * @return id rejected training message
     */
    public int getIdRejectedTraingMessage() {
        return idRejectedTraingMessage;
    }

    /**
     *
     * @param idRejectedTraingMessage
     */
    public void setIdRejectedTraingMessage(int idRejectedTraingMessage) {
        this.idRejectedTraingMessage = idRejectedTraingMessage;
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
     * @return person SSN
     */
    public String getPersonSSN() {
        return personSSN;
    }

    /**
     *
     * @param personSSN
     */
    public void setPersonSSN(String personSSN) {
        this.personSSN = personSSN;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
