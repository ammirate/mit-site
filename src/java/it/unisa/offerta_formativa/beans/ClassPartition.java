/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Alessandro
 */
public class ClassPartition {
    private String title=null;
    private String idTeaching=null;
    private int idClass=-1;

    public ClassPartition(){
    	
    }
    
    public ClassPartition(String idTeaching, String titolo) {
    	if(idTeaching!=null && titolo!=null){
    		if(idTeaching.equals("") || titolo.equals("")) throw new IllegalArgumentException("Parameters cannot be void");
	        this.title = titolo;
	        this.idTeaching = idTeaching;
    	}else{
    		throw new IllegalArgumentException("Parameters cannot be null");
    	}
    }
    
    
    
    public ClassPartition(int idClass, String idTeaching, String title) {
    	this(idTeaching,title);
		this.idClass = idClass;
	}



	public String getIdTeaching() {
		return idTeaching;
	}

	public void setIdTeaching(String idTeaching) {
		if(idTeaching!=null){
			if(!idTeaching.equals("")){
				this.idTeaching = idTeaching;
			}else{throw new IllegalArgumentException("id teaching cannot be void");}
		}else{throw new IllegalArgumentException("id teaching cannot be null");}
	}

	

    public String getTitle() {
        return title;
    }

    
    public void setTitle(String title) {
    	if(title!=null){
    		if(!title.equals("")){
    			this.title = title;
    		}else{throw new IllegalArgumentException("title cannot be void");}
    	}else{throw new IllegalArgumentException("title cannot be null");}
    }
    
    public int getIdClass() {
		return idClass;
	}

	public void setIdClass(int idClass) {
		if(idClass>0){
			this.idClass = idClass;
		}else{throw new IllegalArgumentException("id class cannot be negative");}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "idClass="+idClass+",idTeaching=\""+idTeaching+"\", title=\""+title+"\"";
	}  
	
	public String toStringQueryInsert(){
		return "\""+idTeaching+"\", \""+title+"\"";
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		ClassPartition toComp = (ClassPartition) arg0;
		if(this.idClass==toComp.getIdClass() &&
			this.idTeaching.equalsIgnoreCase(toComp.getIdTeaching()) &&
			this.getTitle().equals(toComp.getTitle())
		)return true;
		else return false;
	}

	
    
    
}
