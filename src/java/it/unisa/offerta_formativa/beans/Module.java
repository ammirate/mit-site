package it.unisa.offerta_formativa.beans;

/**
 *
 * @author Alessandro, Antonio
 *
 */
public class Module {

    private String teachingMatricula;
    private String title;

    public Module(String title, String teachingMatricula) {
        if (teachingMatricula != null && title != null) {
            if (!teachingMatricula.equals("") && !title.equals("")) {
                this.teachingMatricula = teachingMatricula;
                this.title = title;
            } else {
                throw new IllegalArgumentException("id teaching and title cannot be void");
            }
        } else {
            throw new IllegalArgumentException("id teaching and title cannot be null");
        }

    }

    /**
     *
     * @return the teaching serial number
     */
    public String getTeachingMatricula() {
        return teachingMatricula;
    }

    public void setTeachingMatricula(String serialNumber) {
        if (serialNumber != null) {
            if (!serialNumber.equals("")) {
                this.teachingMatricula = serialNumber;
            } else {
                throw new IllegalArgumentException("teaching serialNumber cannot be void");
            }
        } else {
            throw new IllegalArgumentException("teaching serialNumber cannot be null");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null) {
            if (!title.equals("")) {
                this.title = title;
            } else {
                throw new IllegalArgumentException("title cannot be void");
            }
        } else {
            throw new IllegalArgumentException("title cannot be null");
        }
    }

    @Override
    public String toString() {
        String esc = "\'";
        return "teaching_matricula=" + esc + teachingMatricula + esc + ","
                + "title=" + esc + title + esc;
    }

    public String toStringQueryInsert() {
        String esc = "\'";
        return esc + teachingMatricula + esc + "," + esc + title + esc;
    }

    @Override
    public boolean equals(Object arg0) {
        Module toComp = (Module) arg0;
        return this.teachingMatricula.equalsIgnoreCase(toComp.getTeachingMatricula())
                && this.title.equalsIgnoreCase(toComp.getTitle());
    }

}
