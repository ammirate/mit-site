package it.unisa.tirocinio.beans;

/**
 *
 * @author Valentino
 */
public class Questionnaire {
    
    private String organizationName = null;
    private String organizationType = null;
    private String firstQuestion = null;
    private String secondQuestion = null;
    private String thirdQuestion = null;
    private String fourthQuestion = null;
    private String fifthQuestion = null;
    private String sixthQuestion = null;
    private String seventhQuestion = null;
    private String studentSSN = null;

    /**
     *
     * @param organizationName
     * @param organizationType
     * @param firstQuestion
     * @param secondQuestion
     * @param studentSSN
     * @param thirdQuestion
     * @param fourthQuestion
     * @param fifthQuestion
     * @param sixthQuestion
     * @param seventhQuestion
     */
    public Questionnaire(String organizationName, String organizationType, String firstQuestion, String secondQuestion, String studentSSN, String thirdQuestion, String fourthQuestion, String fifthQuestion, String sixthQuestion, String seventhQuestion) {
        this.organizationName = organizationName;
        this.organizationType = organizationType;
        this.firstQuestion = firstQuestion;
        this.secondQuestion = secondQuestion;
        this.thirdQuestion = thirdQuestion;
        this.fourthQuestion = fourthQuestion;
        this.fifthQuestion = fifthQuestion;
        this.sixthQuestion = sixthQuestion;
        this.seventhQuestion = seventhQuestion;
        this.studentSSN = studentSSN;
    }
    
        
    /**
     *
     */
    public Questionnaire() {
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
     * @param studentSSN
     */
    public void setStudentSSN(String studentSSN) {
        this.studentSSN = studentSSN;
    }

    /**
     *
     * @return organization name
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     *
     * @return organization type
     */
    public String getOrganizationType() {
        return organizationType;
    }

    /**
     *
     * @return first question answer
     */
    public String getFirstQuestion() {
        return firstQuestion;
    }

    /**
     *
     * @return second question answer
     */
    public String getSecondQuestion() {
        return secondQuestion;
    }

    /**
     *
     * @return third question answer
     */
    public String getThirdQuestion() {
        return thirdQuestion;
    }

    /**
     *
     * @return fourth question answer
     */
    public String getFourthQuestion() {
        return fourthQuestion;
    }

    /**
     *
     * @return fifth question answer
     */
    public String getFifthQuestion() {
        return fifthQuestion;
    }

    /**
     *
     * @return sixth question answer
     */
    public String getSixthQuestion() {
        return sixthQuestion;
    }

    /**
     *
     * @return seventh question answer
     */
    public String getSeventhQuestion() {
        return seventhQuestion;
    }

    /**
     *
     * @param organizationName
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    /**
     *
     * @param organizationType
     */
    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    /**
     *
     * @param firstQuestion
     */
    public void setFirstQuestion(String firstQuestion) {
        this.firstQuestion = firstQuestion;
    }

    /**
     *
     * @param secondQuestion
     */
    public void setSecondQuestion(String secondQuestion) {
        this.secondQuestion = secondQuestion;
    }

    /**
     *
     * @param thirdQuestion
     */
    public void setThirdQuestion(String thirdQuestion) {
        this.thirdQuestion = thirdQuestion;
    }

    /**
     *
     * @param fourthQuestion
     */
    public void setFourthQuestion(String fourthQuestion) {
        this.fourthQuestion = fourthQuestion;
    }

    /**
     *
     * @param fifthQuestion
     */
    public void setFifthQuestion(String fifthQuestion) {
        this.fifthQuestion = fifthQuestion;
    }

    /**
     *
     * @param sixthQuestion
     */
    public void setSixthQuestion(String sixthQuestion) {
        this.sixthQuestion = sixthQuestion;
    }

    /**
     *
     * @param seventhQuestion
     */
    public void setSeventhQuestion(String seventhQuestion) {
        this.seventhQuestion = seventhQuestion;
    }
}
