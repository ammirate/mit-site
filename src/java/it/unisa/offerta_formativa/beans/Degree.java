/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.beans;

/**
 *
 * @author Alessandro
 */
public class Degree{
	/**
	 * id of the bachelr. it's composed of 5 digit.
	 */
	private String matricula;
	/**
	 * title of the degree
	 */
	private String title;
	
	/**
	 * link to the degree program on esse3
	 */
	private String link;
	
	private int idDepartment;
	
	
	/**
	 * cycle = 1 -> Bachelor degree
	 * cycle = 2 -> Degree
	 * cycle = 3 -> PhD
	 * others...
	 **/
    private int cycle;

    public Degree(){
    	this.matricula=null;
    	this.link=null;
    	this.title=null;
    	this.cycle=-1;
    	this.idDepartment = -1;
    }
    
    public int getDeptId() {
		return idDepartment;
	}

	public void setDeptId(int deptId) {
		this.idDepartment = deptId;
	}

	public Degree(String matricola, String link, String titolo, int ciclo, int dipartimento) {
        this.matricula = matricola;
        this.link = link;
        this.title = titolo;
        this.cycle = ciclo;
        this.idDepartment = dipartimento;
    }

    public String getMatricula() {
        return matricula;
    }

	public void setMatricula(String matricola) {
		if(matricola.equals("") || matricola.length()>5) 
			throw new RuntimeException("Empty serial number for new degree");
		this.matricula = matricola;
	}

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

	public void setTitle(String title) {
		if(title.equals("")) 
			throw new RuntimeException("Empty tile for new degree");
		this.title = title;
	}


    public int getCycle() {
        return cycle;
    }

	public void setCycle(int cycle) {
		if(cycle<=0)
			throw new IllegalArgumentException("Cycle cannot be less than 1");
		this.cycle = cycle;
	}

    @Override
    public String toString() {
        return "matricula=\"" + matricula + "\", link=\"" + link + "\", title=\"" + title + "\", cycle=\"" + cycle+"\", idDepartment="+idDepartment;
    }
    
    
    public String toStringQueryInsert(){
    	return "\"" + matricula + "\",\"" + link +  "\",\"" + title +  "\"," + cycle +","+ idDepartment +")";
    }
    
    
    public Boolean equals(Degree b){
    	if(this.matricula.equalsIgnoreCase(b.getMatricula()) &&
    			this.title.equalsIgnoreCase(b.getTitle()) &&
    			this.getLink().equals(b.getLink()) &&
    			this.getCycle() == b.getCycle()
    	   ){
    		return true;
    	}else 
    		return false;


    }
    
    
}
