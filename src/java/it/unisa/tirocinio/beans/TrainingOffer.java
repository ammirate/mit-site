package it.unisa.tirocinio.beans;

/**
 *
 * @author johneisenheim
 */
public class TrainingOffer {
   
    private int idTrainingOffer;
    private String description;
    private String organization = null;
    private String personSSN = null;
    private String department = null;
    private String contact = null;
    private String organizationVAT = null;
    
    /**
     *
     */
    public TrainingOffer(){}
    
    /**
     *
     * @param idTrainingOffer
     * @param contact
     * @param description
     * @param organization
     * @param personSSN
     * @param department
     */
    public TrainingOffer(int idTrainingOffer,String contact, String description, String organization, String personSSN, String department){
        this.idTrainingOffer = idTrainingOffer;
        this.description = description;
        this.organization = organization;
        this.personSSN = personSSN;
        this.department = department;
        this.contact = contact;
    }

    /**
     *
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     *
     * @return contact
     */
    public String getContact() {
        return contact;
    }

    /**
     *
     * @return id training offer
     */
    public int getIdTrainingOffer() {
        return idTrainingOffer;
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
     * @return organization
     */
    public String getOrganization() {
        return organization;
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
     * @return department
     */
    public String getDepartment() {
        return department;
    }

    /**
     *
     * @param idTrainingOffer
     */
    public void setIdOfferTraining(int idTrainingOffer) {
        this.idTrainingOffer = idTrainingOffer;
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
     * @param organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
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
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void setIdTrainingOffer(int idTrainingOffer) {
        this.idTrainingOffer = idTrainingOffer;
    }

    public void setOrganizationVAT(String organizationVAT) {
        this.organizationVAT = organizationVAT;
    }

    public String getOrganizationVAT() {
        return organizationVAT;
    }
    
    
}
