package it.unisa.offerta_formativa.beans;

/**
 * 
 * @author Alessandro, Antonio
 *
 */
public class Module {
    
	private String teachingSN;
;	private String title;
	
	
	public Module(String idTeaching, String title) {
		if(idTeaching!=null && title!=null){
			if(!idTeaching.equals("") && !title.equals("")){
				this.teachingSN = idTeaching;
				this.title = title;
			}else{
				throw new IllegalArgumentException("id teaching and title cannot be void");
			}
		}else{throw new IllegalArgumentException("id teaching and title cannot be null");}
		
	}

        /**
         * 
         * @return the teaching serial number
         */
	public String getTeachingSN() {
		return teachingSN;
	}

        
	public void setTeachingSN(String serialNumber) {
		if(serialNumber!=null){
			if(!serialNumber.equals("")){
				this.teachingSN = serialNumber;
			}else{
				throw new IllegalArgumentException("teaching serialNumber cannot be void");
			}
		}else{throw new IllegalArgumentException("teaching serialNumber cannot be null");}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title!=null){
			if(!title.equals("")){
				this.title = title;
			}else{
				throw new IllegalArgumentException("title cannot be void");
			}
		}else{
			throw new IllegalArgumentException("title cannot be null");
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "idTeaching=\""+teachingSN+"\",title=\""+title+"\"";
	}
	
	public String toStringQueryInsert(){
		return "\""+teachingSN+"\",\""+title+"\"";
	}
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		Module toComp = (Module) arg0;
		if(this.teachingSN.equalsIgnoreCase(toComp.getTeachingSN()) &&
		   this.title.equalsIgnoreCase(toComp.getTitle())
		){
                    return true;
                }
		else {
                    return false;
                }
	}
	
	
}
