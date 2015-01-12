package it.unisa.tirocinio.beans;

import java.sql.Date;

/**
 *
 * @author johneisenheim
 */
public class PendingAcceptance {
    
    private int idPendingAcceptance;
    private Date requestDate;
    private String personSSN = null;
    
    /**
     *
     */
    public PendingAcceptance(){}
    
    /**
     *
     * @param idPendingAcceptance
     * @param requestDate
     * @param personSSN
     */
    public PendingAcceptance( int idPendingAcceptance, Date requestDate, String personSSN ){
        this.idPendingAcceptance = idPendingAcceptance;
        this.requestDate = requestDate;
        this.personSSN = personSSN;
    }

    /**
     *
     * @return pendingAcceptance table id
     */
    public int getIdPendingAcceptance() {
        return idPendingAcceptance;
    }

    /**
     *
     * @return training request date 
     */
    public Date getRequestDate() {
        return requestDate;
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
     * @param idPendingAcceptance
     */
    public void setIdPendingAcceptance(int idPendingAcceptance) {
        this.idPendingAcceptance = idPendingAcceptance;
    }

    /**
     *
     * @param requestDate
     */
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    /**
     *
     * @param personSSN
     */
    public void setPersonSSN(String personSSN) {
        this.personSSN = personSSN;
    }
    
}
