package it.unisa.tirocinio.beans;

/**
 *
 * @author johneisenheim
 */
public class StudentInformation {
    
    private String studentSSN = null;
    private String CVPath = null;
    private String ATPath = null;
    private int studentStatus;
    private String matricula = null;
    private String emailStudent = null;
    
    /**
     *
     */
    public StudentInformation(){}
    
    /**
     *
     * @param emailStudent
     * @param matricula
     * @param studentSSN
     * @param CVPath
     * @param ATPath
     * @param studentStatus
     */
    public StudentInformation(String emailStudent, String matricula, String studentSSN, String CVPath, String ATPath, int studentStatus ){
        this.ATPath = ATPath;
        this.CVPath = CVPath;
        this.studentSSN = studentSSN;
        this.studentStatus = studentStatus;
        this.matricula = matricula;
        this.emailStudent = emailStudent;
    }
    
    /**
     *
     * @return student email address
     */
    public String getEmailStudent() {
        return emailStudent;
    }

    /**
     *
     * @param emailStudent
     */
    public void setEmailStudent(String emailStudent) {
        this.emailStudent = emailStudent;
    }

    /**
     *
     * @param matricula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     *
     * @return student matricula
     */
    public String getMatricula() {
        return matricula;
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
     * @return curriculum vitae file path
     */
    public String getCVPath() {
        return CVPath;
    }

    /**
     *
     * @return accademic transcript file path
     */
    public String getATPath() {
        return ATPath;
    }

    /**
     *
     * @return student status
     */
    public int getStudentStatus() {
        return studentStatus;
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
     * @param CVPath
     */
    public void setCVPath(String CVPath) {
        this.CVPath = CVPath;
    }

    /**
     *
     * @param ATPath
     */
    public void setATPath(String ATPath) {
        this.ATPath = ATPath;
    }

    /**
     *
     * @param studentStatus
     */
    public void setStudentStatus(int studentStatus) {
        this.studentStatus = studentStatus;
    }
    
}
