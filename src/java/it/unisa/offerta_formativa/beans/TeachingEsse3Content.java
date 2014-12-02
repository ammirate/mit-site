package it.unisa.offerta_formativa.beans;

/**
 *
 * @author Antonio
 */
public class TeachingEsse3Content {
    
    private String teachingSN;
    
    private String content;
    
    public TeachingEsse3Content(String teachingSerialNumber){
        this.teachingSN = teachingSerialNumber;
    }

    public String getTeachingSN() {
        return teachingSN;
    }

    public void setTeachingSN(String teachingSN) {
        this.teachingSN = teachingSN;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
