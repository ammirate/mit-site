package it.unisa.tirocinio.beans;

/**
 *
 * @author johneisenheim
 */
public class Organization {
    
    private String VATNumber = null;
    private String companyName = null;
    private String city = null;
    private String address = null;
    private String phone = null;
    private String email = null;
    private String professor = null;
    private String externalTutor = null;
    private String accountEmail = null;
    
    /**
     *
     */
    public Organization(){}
    
    /**
     *
     * @param VATNumber
     * @param companyName
     * @param city
     * @param address
     * @param phone
     * @param email
     * @param accountEmail
     * @param professor
     * @param externalTutor
     */
    public Organization(String VATNumber, String companyName, String city, String address, String phone, String email, String accountEmail, String professor, String externalTutor){
        this.VATNumber = VATNumber;
        this.companyName = companyName;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.accountEmail = accountEmail;
        this.professor = professor;
        this.externalTutor = externalTutor;
    }

    /**
     *
     * @return organization email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     *
     * @return company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     *
     * @return city where company is
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @return company address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     *
     * @return organization account email
     */
    public String getAccount() {
        return accountEmail;
    }

    /**
     *
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     *
     * @param accountEmail
     */
    public void setAccount(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    /**
     *
     * @return organization VAT Number
     */
    public String getVATNumber() {
        return VATNumber;
    }

    /**
     *
     * @param VATNumber
     */
    public void setVATNumber(String VATNumber) {
        this.VATNumber = VATNumber;
    }

    /**
     *
     * @return professor binded with this organization
     */
    public String getProfessor() {
        return professor;
    }

    /**
     *
     * @return the external tutor reference
     */
    public String getExternalTutor() {
        return externalTutor;
    }

    /**
     *
     * @return organization account email
     */
    public String getAccountEmail() {
        return accountEmail;
    }

    /**
     *
     * @param professor
     */
    public void setProfessor(String professor) {
        this.professor = professor;
    }

    /**
     *
     * @param externalTutor
     */
    public void setExternalTutor(String externalTutor) {
        this.externalTutor = externalTutor;
    }

    /**
     *
     * @param accountEmail
     */
    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }
    
}
