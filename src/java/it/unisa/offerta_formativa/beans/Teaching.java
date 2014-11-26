/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.beans;

/**
 *
 * @author Alessandro, Antonio
 */
public class Teaching {

	/**
	 * title of the course
	 */
	private String title;

	/**
	 * abbreviation of the title. it's made by the first letter of each word of the title.
	 * If the title has just one word, it's made by the first 3 letters.
	 */
	private String abbreviation;

	/**
	 * id of the course. it's composed of 10 digit.
	 */
	private String matricula;

	/**
	 * link to the course program on esse3
	 */
	private String link;

	/**
	 * year in which the course is provided in its degree. it can't be greater than 3.
	 */
	private int year;

	/**
	 * semester in which the course is provided in its degree. it can't be greater than 2.
	 */
	private int semester;

	/**
	 * value that indicates whether the course is on or off
	 */
	private Boolean active;


	public Teaching(String titolo, String abbreviazione, String matricola, String link, int anno, int semestre, boolean attivo) {
		this.title = titolo;
		this.abbreviation = abbreviazione;
		this.matricula = matricola;
		this.link = link;
		this.year = anno;
		this.semester = semestre;
		this.active = attivo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title.equals("")) 
			throw new RuntimeException("Empty title for new degree");
		this.title = title;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		if(matricula.equals("")) 
			throw new RuntimeException("Empty serial number for new degree");
		this.matricula = matricula;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getYear() {
		return year;
	}

	/**
	 * Set the year of the teaching. if the teaching belongs to a bachelor degree,
	 * then 0<year<=3. If the teaching belongs to a degree, year can be  0<year<=2
	 * @param year 
	 */
	public void setYear(short year) {
		if(year<0 || year>3)
			throw new IllegalArgumentException("Year must be a number between 1 and 3");
		this.year = year;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(short semester) {
		if(year<0 || year>2)
			throw new IllegalArgumentException("Semester must be a number between 1 and 2");
		this.semester = semester;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


	public String toString() {
		String virg = "\"";
		return  "matricula=" + virg + matricula + virg + "," +
		"title=" + virg + title + virg + "," +
		"abbreviation=" + virg + abbreviation + virg + "," +
		"link=" + virg + link + virg + "," +
		"year= " + year + "," +
		"semester= " + semester + "," +
		"active=" +  (active==true ? "TRUE" : "FALSE") ;   		
	}

	/**
	 * Used to get the values of all attributes and put them in a query
	 * @return all the values of this object with \" at the strings
	 */
	public String toStringQueryInsert(){
		return "\"" + matricula +  "\",\"" + title +  "\",\"" + abbreviation +
				"\",\"" + link + "\"," + year + "," + semester + 
				"," + (active==true ? "TRUE" : "FALSE");
	}


	public boolean equal(Object arg0){
		Teaching t = (Teaching) arg0;
		if(this.title.equalsIgnoreCase(t.getTitle()) &&
    			this.abbreviation.equalsIgnoreCase(t.getAbbreviation()) &&
    			this.getMatricula().equalsIgnoreCase(t.getMatricula()) &&
    			this.getLink().equalsIgnoreCase(t.getLink()) &&
    			this.getYear() == t.getYear() &&
    			this.getSemester() == t.getSemester() &&
    			this.isActive() == t.isActive())
    		{
    		return true;
    	}
    	else return false;
	}


}    