/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.model;


/**
 * 
 * @author Antonio
 */
public class Department implements Comparable<Department>{

	private String title;
	private String urlMoodle;
	private String token;
	private String abbreviation; //primary key
	
        /**
         * Constructor
         * @param abbreviation
         * @param title
         * @param urlMoodle
         * @param token 
         */
	public Department(String abbreviation, String title, String urlMoodle, String token){
		if(title.equalsIgnoreCase("")) throw new IllegalArgumentException("Title cannot be null");
		this.abbreviation = abbreviation;
                this.title=title;
		this.urlMoodle=urlMoodle;
		this.token=token;
	}

    public Department() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if(title.equalsIgnoreCase("")) throw new IllegalArgumentException("Title cannot be null");
		this.title = title;
	}
	public String getUrlMoodle() {
		return urlMoodle;
	}
	public void setUrlMoodle(String urlMoodle) {
		this.urlMoodle = urlMoodle;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String toStringQueryInsert() {
            String esc = "\'";		
            String toReturn =  esc + abbreviation + esc + "," + esc + title + esc + "," + esc + urlMoodle + esc + "," +  esc + token + esc;
            return toReturn;
	}
	
	
        @Override
	public String toString() {
                String esc = "\'";		

		return  "abbreviation=" + esc + abbreviation + esc + "," +
                        "title=" + esc + title + esc + "," +
                        "url_moodle=" + esc + urlMoodle + esc + "," +
                         "token=" + esc + token + esc;
	}

	@Override
	public boolean equals(Object dep) {
            Department obj = (Department) dep;
            if(this.getAbbreviation().equals(obj.getAbbreviation()) &&
		this.getTitle().equalsIgnoreCase(obj.getTitle()) &&
		this.getUrlMoodle().equalsIgnoreCase(obj.getUrlMoodle())){return true;}
            return false;
	}

    @Override
    public int compareTo(Department dep) {
         return this.getTitle().compareTo(dep.getTitle());

    }
	
	
	
	
}
