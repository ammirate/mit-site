package beans;

/**
 * 
 * @author Alessandro
 *
 */
public class Module {
	private int idModule;
	private String idTeaching;
	private String title;
	
	public Module(){
		idModule=-1;
		idTeaching=null;
		title=null;
	}
	
	public Module(int idModule,String idTeaching, String title){
		this(idTeaching, title);
		this.idModule=idModule;
	}
	
	public Module(String idTeaching, String title) {
		if(idTeaching!=null && title!=null){
			if(!idTeaching.equals("") && !title.equals("")){
				this.idTeaching = idTeaching;
				this.title = title;
			}else{
				throw new IllegalArgumentException("id teaching and title cannot be void");
			}
		}else{throw new IllegalArgumentException("id teaching and title cannot be null");}
		this.idModule=-1;
	}

	public int getIdModule() {
		return idModule;
	}

	public void setIdModule(int idModule) {
		if(idModule>0){
			this.idModule = idModule;
		}else{
			throw new IllegalArgumentException("id module cannot be negative");
		}
	}

	public String getIdTeaching() {
		return idTeaching;
	}

	public void setIdTeaching(String idTeaching) {
		if(idTeaching!=null){
			if(!idTeaching.equals("")){
				this.idTeaching = idTeaching;
			}else{
				throw new IllegalArgumentException("id teaching cannot be void");
			}
		}else{throw new IllegalArgumentException("id teaching cannot be null");}
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
		return "idModule="+idModule+", idTeaching=\""+idTeaching+"\",title=\""+title+"\"";
	}
	
	public String toStringQueryInsert(){
		return "\""+idTeaching+"\",\""+title+"\"";
	}
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		Module toComp = (Module) arg0;
		if(this.idModule==toComp.getIdModule() &&
			this.idTeaching.equalsIgnoreCase(toComp.getIdTeaching()) &&
			this.title.equalsIgnoreCase(toComp.getTitle())
		)return true;
		else return false;
	}
	
	
}
